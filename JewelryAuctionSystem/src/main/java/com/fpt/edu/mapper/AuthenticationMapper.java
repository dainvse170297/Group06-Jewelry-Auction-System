package com.fpt.edu.mapper;

import com.fpt.edu.dto.AccountDTO;
import com.fpt.edu.entity.Account;
import com.fpt.edu.response.AuthenticationResponse;
import org.springframework.stereotype.Component;



@Component
public class AuthenticationMapper {

    public static AuthenticationResponse toAuthenticationResponse(Account account, String token) {

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        authenticationResponse.setToken(token);
        if(account.getMembers() != null){
            authenticationResponse.setAccount(AccountMapper.toAccountMemberDTO(account));
       }else if(account.getStaff() != null){
            authenticationResponse.setAccount(AccountMapper.toAccountDTO(account));
        }

        return authenticationResponse;
    }
}