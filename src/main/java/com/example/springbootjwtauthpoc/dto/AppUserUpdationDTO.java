package com.example.springbootjwtauthpoc.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppUserUpdationDTO {

  @NotBlank(message = "Name cannot be empty")
  private String name;
  private String profilePicUrl;
}
