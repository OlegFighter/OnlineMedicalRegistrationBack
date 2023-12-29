package com.example.onlinemedicalregistrationback.repository;

import com.example.onlinemedicalregistrationback.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface UserRepository extends JpaRepository<User, Long> {
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update users u set password = :newPassword where u.policy =:userPolicy",
    nativeQuery = true)
    void setPassword(@Param("newPassword") String newPassword, @Param("userPolicy") String userPolicy);

    @Query(value = "select * from users u where u.policy =:userPolicy", nativeQuery = true)
    User findByPolicy(@Param("userPolicy") String userPolicy);
}
