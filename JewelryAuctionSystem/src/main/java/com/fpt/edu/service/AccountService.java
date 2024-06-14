package com.fpt.edu.service;
import com.fpt.edu.dto.AccountDTO;
import com.fpt.edu.entity.Account;
import com.fpt.edu.entity.InvalidatedToken;
import com.fpt.edu.mapper.AccountMapper;
import com.fpt.edu.mapper.AuthenticationMapper;
import com.fpt.edu.repository.IAccountRepository;
import com.fpt.edu.repository.InvalidatedTokenRepository;
import com.fpt.edu.security.request.IntrospectRequest;
import com.fpt.edu.security.request.LogoutRequest;
import com.fpt.edu.security.response.AuthenticationResponse;
import com.fpt.edu.security.response.IntrospectResponse;
import com.fpt.edu.entity.Member;
import com.fpt.edu.entity.Role;
import com.fpt.edu.exception.EmailExistedException;
import com.fpt.edu.exception.UsernameExistedException;
import com.fpt.edu.repository.IMemberRepository;
import com.fpt.edu.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.experimental.NonFinal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountService.class);
    private final IAccountRepository accountRepository;
    private final IMemberRepository memberRepository;
    private final IRoleRepository roleRepository;
    private final InvalidatedTokenRepository invalidatedTokenRepository;


    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;


    @Override
    public AuthenticationResponse login(String username, String password) throws JOSEException {

        Account account = accountRepository.findByUsernameAndPassword(username, password).orElseThrow(
                () -> new RuntimeException("Account not found")
        );

        String role = account.getRole().getName();
        Integer accountId = account.getId();
        String token = generateToken(username,role,accountId);

            return AuthenticationMapper.toAuthenticationResponse(account, token);

    }
    // dung de xac thuc token co hop le hay khong. neu co tra ve true neu khong tra ve false
    @Override
    public IntrospectResponse introspect(IntrospectRequest introspectRequest) throws JOSEException, ParseException {

        String token = introspectRequest.getToken();
        boolean isvalid = true;
        try {
            verifyToken(token);
        }catch (JOSEException e){
            isvalid = false;
        }


        return new IntrospectResponse(isvalid);

    }
    @Override
    public AccountDTO getAccountFromToken(IntrospectRequest introspectRequest) throws ParseException, JOSEException {
        String token = introspectRequest.getToken();

        if(!(introspect(new IntrospectRequest(token)).isValid())) {
            throw new JOSEException("JWT token verification failed");
        }
        SignedJWT signedJWT = SignedJWT.parse(token);
        Long accountId = (Long) signedJWT.getJWTClaimsSet().getClaim("accountId");

        Account account = accountRepository.findById(accountId.intValue()).orElseThrow(
                () -> new RuntimeException("Account not found")
        );

        if(account.getMembers() != null){
            return AccountMapper.toAccountMemberDTO(account);
        }
            //staff
            return AccountMapper.toAccountDTO(account);

    }



    @Override
    public Account createAccount(String username, String password, String fullName, String email, String phone, String address) {
        Account account = new Account();
        Member member = new Member();
        if(accountRepository.findByUsername(username).isPresent()){
            throw new UsernameExistedException("Username is existed");
        }else if(memberRepository.findByEmail(email).isPresent()){
            throw new EmailExistedException("Email is existed");
        }else{
            account.setUsername(username);
            account.setPassword(password);
            account.setCreateDate(LocalDateTime.now());
            Role role = roleRepository.findByName("MEMBER");
            account.setRole(role);
            member.setFullname(fullName);
            member.setEmail(email);
            member.setPhone(phone);
            member.setAddress(address);
            account.setMembers(member);
            memberRepository.save(member);
            accountRepository.save(account);
        }
        return account;
    }


    public void logout(LogoutRequest request) throws ParseException, JOSEException {

        var signToken = verifyToken(request.getToken());
        String jit = signToken.getJWTClaimsSet().getJWTID();
        Date expirationTime = signToken.getJWTClaimsSet().getExpirationTime();
        InvalidatedToken invalidatedToken = new InvalidatedToken();
        invalidatedToken.setId(jit);
        invalidatedToken.setExpiredAt(expirationTime);
        invalidatedTokenRepository.save(invalidatedToken);

    }

    private SignedJWT verifyToken(String token) throws JOSEException, ParseException {
        JWSVerifier jwsVerifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        boolean verified =  signedJWT.verify(jwsVerifier);

        if(!(verified && expirationTime.after(new Date()))) {
            throw new JOSEException("JWT token verification failed");
        }

        if(invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())){
            throw new JOSEException("Token is invalidated");
        }

        return signedJWT;
    }


    private String generateToken(String username,String role, Integer accountId) throws JOSEException {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("http://localhost:8080")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                )).claim("scope", role)
                .claim("accountId",accountId)
                .jwtID(UUID.randomUUID().toString())
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Error create token", e);
            throw new RuntimeException();
        }

    }


}

