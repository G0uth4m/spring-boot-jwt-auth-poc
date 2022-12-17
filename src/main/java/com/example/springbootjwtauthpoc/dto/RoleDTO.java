package com.example.springbootjwtauthpoc.dto;

import com.example.springbootjwtauthpoc.enums.UserRole;
import com.example.springbootjwtauthpoc.validation.constraints.ValueOfEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {

  @ValueOfEnum(enumClass = UserRole.class, message = "Invalid role")
  private String role;
}
