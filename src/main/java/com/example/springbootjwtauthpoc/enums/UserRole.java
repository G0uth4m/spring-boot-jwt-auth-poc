package com.example.springbootjwtauthpoc.enums;

import com.example.springbootjwtauthpoc.entity.AppUserRole;

public enum UserRole {

  ROLE_USER(new AppUserRole(1, "ROLE_USER")),
  ROLE_ADMIN(new AppUserRole(2, "ROLE_ADMIN"));

  private final AppUserRole appUserRole;

  UserRole(AppUserRole appUserRole) {
    this.appUserRole = appUserRole;
  }

  public AppUserRole value() {
    return this.appUserRole;
  }
}
