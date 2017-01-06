package com.em248.example.repository;

import com.em248.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by tian on 2017/1/6.
 */
public interface UserRepository extends JpaRepository<User,Long> {
}
