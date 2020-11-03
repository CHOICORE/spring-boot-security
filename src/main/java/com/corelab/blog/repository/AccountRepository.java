package com.corelab.blog.repository;

import com.corelab.blog.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {
    // SELECT * FROM user WHERE username =1?;
    Optional<Account> findByUsername(String username);
}
