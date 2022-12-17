package com.example.springbootjwtauthpoc.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse implements Serializable {
  private LocalDateTime timestamp;
  private Integer status;
  private String error;
  private Object message;
  private String path;
}
