package com.example.onlinemedicalregistrationback.repository;

import com.example.onlinemedicalregistrationback.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
