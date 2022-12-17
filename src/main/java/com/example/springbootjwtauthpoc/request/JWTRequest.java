package com.example.springbootjwtauthpoc.request;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTRequest {

  @NotBlank(message = "Username cannot be empty")
  private String username;

  @NotBlank(message = "Password cannot be empty")
  private String password;
}
