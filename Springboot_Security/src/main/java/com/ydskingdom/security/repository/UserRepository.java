package com.ydskingdom.security.repository;

import com.ydskingdom.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//CRUD 함수를 JpaRepository가 들고있음
//@Repository라는 어노테이션이 없어도 IoC 가능, 이유는 JpaRepository를 상속했기 떄문
public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByUsername(String username);
}
