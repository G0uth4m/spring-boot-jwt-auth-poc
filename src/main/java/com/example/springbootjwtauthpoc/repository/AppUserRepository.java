package com.example.springbootjwtauthpoc.repository;

import com.example.springbootjwtauthpoc.entity.AppUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
  Optional<AppUser> findByUsername(String username);
  Boolean existsByUsername(String username);
}
