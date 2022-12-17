package com.example.springbootjwtauthpoc.entity;

import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;
  private String username;
  private String name;
  private String password;
  private String email;
  private String profilePicUrl;

  @ManyToMany(fetch = FetchType.EAGER)
  private Set<AppUserRole> roles;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
