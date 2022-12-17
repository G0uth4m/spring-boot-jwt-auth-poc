package com.example.springbootjwtauthpoc.service;

import com.example.springbootjwtauthpoc.dto.AppUserCreationDTO;
import com.example.springbootjwtauthpoc.dto.AppUserDTO;
import com.example.springbootjwtauthpoc.dto.AppUserUpdationDTO;
import com.example.springbootjwtauthpoc.dto.RoleDTO;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface AppUserService {

  AppUserDTO createUser(AppUserCreationDTO appUserCreationDTO);

  AppUserDTO editUser(String username, AppUserUpdationDTO appUserUpdationDTO);

  AppUserDTO getUser(String username);

  List<AppUserDTO> getUsers(Pageable pageable);

  void deleteUser(String username);

  void addRole(String username, RoleDTO roleDTO);

  void removeRole(String username, String role);

}
