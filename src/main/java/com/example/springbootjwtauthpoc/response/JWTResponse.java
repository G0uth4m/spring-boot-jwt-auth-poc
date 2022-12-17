package com.example.springbootjwtauthpoc.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTResponse {
  private String token;
}
