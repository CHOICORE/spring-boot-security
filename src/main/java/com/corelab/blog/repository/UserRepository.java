package com.corelab.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corelab.blog.entity.User;

//@Repository JpaRepository 내부에 선언되어있음 어노테이션을 붙여주지 않아도 됨
public interface UserRepository extends JpaRepository<User, Long> {

}
