package com.example.springbootjwtauthpoc.service.impl;

import com.example.springbootjwtauthpoc.dto.AppUserCreationDTO;
import com.example.springbootjwtauthpoc.dto.AppUserDTO;
import com.example.springbootjwtauthpoc.dto.AppUserUpdationDTO;
import com.example.springbootjwtauthpoc.dto.RoleDTO;
import com.example.springbootjwtauthpoc.entity.AppUser;
import com.example.springbootjwtauthpoc.entity.AppUserRole;
import com.example.springbootjwtauthpoc.enums.UserRole;
import com.example.springbootjwtauthpoc.exception.UserAlreadyExistsException;
import com.example.springbootjwtauthpoc.exception.UserRoleException;
import com.example.springbootjwtauthpoc.exception.ResourceNotFoundException;
import com.example.springbootjwtauthpoc.repository.AppUserRepository;
import com.example.springbootjwtauthpoc.service.AppUserService;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

  private final AppUserRepository appUserRepository;
  private final PasswordEncoder passwordEncoder;
  private final ModelMapper modelMapper;

  @Override
  public AppUserDTO createUser(AppUserCreationDTO appUserCreationDTO) {
    if (appUserRepository.existsByUsername(appUserCreationDTO.getUsername())) {
      throw new UserAlreadyExistsException("Username already taken");
    }
    LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
    AppUser appUser = AppUser.builder()
        .username(appUserCreationDTO.getUsername())
        .name(appUserCreationDTO.getName())
        .password(passwordEncoder.encode(appUserCreationDTO.getPassword()))
        .email(appUserCreationDTO.getEmail())
        .profilePicUrl(appUserCreationDTO.getProfilePicUrl())
        .roles(Collections.singleton(UserRole.ROLE_USER.value()))
        .createdAt(now)
        .updatedAt(now)
        .build();
    appUser = appUserRepository.save(appUser);
    return modelMapper.map(appUser, AppUserDTO.class);
  }

  @Override
  public AppUserDTO editUser(String username, AppUserUpdationDTO appUserUpdationDTO) {
    AppUser appUser = appUserRepository.findByUsername(username)
        .orElseThrow(() -> new ResourceNotFoundException("User does not exist"));
    appUser.setName(appUserUpdationDTO.getName());
    appUser.setProfilePicUrl(appUserUpdationDTO.getProfilePicUrl());
    appUser.setUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));
    appUser = appUserRepository.save(appUser);
    return modelMapper.map(appUser, AppUserDTO.class);
  }

  @Override
  public AppUserDTO getUser(String username) {
    AppUser appUser = appUserRepository.findByUsername(username)
        .orElseThrow(() -> new ResourceNotFoundException("User does not exist"));
    return modelMapper.map(appUser, AppUserDTO.class);
  }

  @Override
  public List<AppUserDTO> getUsers(Pageable pageable) {
    return appUserRepository.findAll(pageable).stream()
        .map(appUser -> modelMapper.map(appUser, AppUserDTO.class))
        .collect(Collectors.toList());
  }

  @Override
  public void deleteUser(String username) {
    AppUser appUser = appUserRepository.findByUsername(username)
        .orElseThrow(() -> new ResourceNotFoundException("User does not exist"));
    appUserRepository.deleteById(appUser.getUserId());
  }

  @Override
  public void addRole(String username, RoleDTO roleDTO) {
    AppUser appUser = appUserRepository.findByUsername(username)
        .orElseThrow(() -> new ResourceNotFoundException("User does not exist"));
    AppUserRole role = UserRole.valueOf(roleDTO.getRole()).value();
    if (appUser.getRoles().contains(role)) {
      throw new UserRoleException("User already has the given role");
    }
    appUser.getRoles().add(role);
    appUserRepository.save(appUser);
  }

  @Override
  public void removeRole(String username, String role) {
    UserRole userRole;
    try {
      userRole = UserRole.valueOf(role);
    } catch (IllegalArgumentException e) {
      throw new ResourceNotFoundException("Invalid role");
    }
    AppUser appUser = appUserRepository.findByUsername(username)
        .orElseThrow(() -> new ResourceNotFoundException("User does not exist"));
    if (!appUser.getRoles().contains(userRole.value())) {
      throw new ResourceNotFoundException("User does not have the given role");
    }
    appUser.getRoles().remove(userRole.value());
    appUserRepository.save(appUser);
  }
}
