package com.example.springbootjwtauthpoc.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDTO {
  private Long userId;
  private String name;
  private String username;
  private String email;
  private String profilePicUrl;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
