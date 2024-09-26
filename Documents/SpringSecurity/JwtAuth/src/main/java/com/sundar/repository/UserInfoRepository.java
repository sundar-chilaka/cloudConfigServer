package com.sundar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sundar.model.UserInfo;
import com.sundar.pojo.LoginRequst;

@Repository
public interface UserInfoRepository extends JpaRepository<LoginRequst, Long> {

	Optional<UserInfo> findByUname(String uname);
}
