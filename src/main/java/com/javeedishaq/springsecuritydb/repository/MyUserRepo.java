package com.javeedishaq.springsecuritydb.repository;

import com.javeedishaq.springsecuritydb.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyUserRepo extends JpaRepository<MyUser, Long> {

    Optional<MyUser> findByUsername(String username);
}
