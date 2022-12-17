package com.example.springbootjwtauthpoc.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AppUserRole {

  @Id
  private Integer roleId;
  private String roleName;
}
