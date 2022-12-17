package com.example.springbootjwtauthpoc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserRoleException extends RuntimeException {

  public UserRoleException(String message) {
    super(message);
  }
}
