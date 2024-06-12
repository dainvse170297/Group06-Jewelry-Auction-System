package com.fpt.edu.controller;

import com.fpt.edu.entity.Account;
import com.fpt.edu.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/")
    public String getAccount() {
        return "Account";
    }

    @PostMapping("/register")
    public ResponseEntity<Account> createAccount(@RequestParam("username") String username,
                                                 @RequestParam("password")String password,
                                                 @RequestParam("fullName")String fullName,
                                                 @RequestParam("email")String email,
                                                 @RequestParam("phone")String phone,
                                                 @RequestParam("address")String address){

        return ResponseEntity.ok().body(accountService.createAccount(username,password,fullName,email,phone,address));
    }
}
