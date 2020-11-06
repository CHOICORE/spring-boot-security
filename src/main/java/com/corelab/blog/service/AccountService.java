package com.corelab.blog.service;


import com.corelab.blog.entity.Account;
import com.corelab.blog.model.AccountRoleType;
import com.corelab.blog.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(Account account){
        account.setRole(AccountRoleType.ADMIN);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
    }

}
