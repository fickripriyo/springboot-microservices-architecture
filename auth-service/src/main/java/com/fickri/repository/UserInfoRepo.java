package com.fickri.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fickri.entity.UserInfo;

public interface UserInfoRepo extends JpaRepository<UserInfo, Long>{
    Optional<UserInfo> findByname(String name); 
}
