package com.yunjipin.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yunjipin.test.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
