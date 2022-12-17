package com.example.springbootjwtauthpoc.controller;

import com.example.springbootjwtauthpoc.dto.AppUserCreationDTO;
import com.example.springbootjwtauthpoc.dto.AppUserDTO;
import com.example.springbootjwtauthpoc.dto.AppUserUpdationDTO;
import com.example.springbootjwtauthpoc.dto.RoleDTO;
import com.example.springbootjwtauthpoc.request.JWTRequest;
import com.example.springbootjwtauthpoc.response.JWTResponse;
import com.example.springbootjwtauthpoc.service.AppUserService;
import com.example.springbootjwtauthpoc.util.TokenManager;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/v1/api/users")
@RequiredArgsConstructor
public class AppUserController {

  private final AuthenticationManager authenticationManager;
  private final TokenManager tokenManager;
  private final AppUserService appUserService;

  @PostMapping("/login")
  public JWTResponse login(@Valid @RequestBody JWTRequest jwtRequest) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),
            jwtRequest.getPassword()));
    String jwt = tokenManager.generateJWT(authentication);
    return new JWTResponse(jwt);
  }

  @PostMapping
  public AppUserDTO signup(@Valid @RequestBody AppUserCreationDTO appUser) {
    return appUserService.createUser(appUser);
  }

  @PreAuthorize("#username == authentication.principal.username")
  @PutMapping("/{username}")
  public AppUserDTO editUser(@PathVariable String username,
      @Valid @RequestBody AppUserUpdationDTO appUserUpdationDTO) {
    return appUserService.editUser(username, appUserUpdationDTO);
  }

  @GetMapping("/{username}")
  public AppUserDTO getUser(@PathVariable String username) {
    return appUserService.getUser(username);
  }

  @GetMapping
  public List<AppUserDTO> getUsers(Pageable pageable) {
    return appUserService.getUsers(pageable);
  }

  @PreAuthorize("#username == authentication.principal.username or hasRole('ROLE_ADMIN')")
  @DeleteMapping("/{username}")
  public void deleteUser(@PathVariable String username) {
    appUserService.deleteUser(username);
  }

  @Secured("ROLE_ADMIN")
  @PostMapping("/{username}/roles")
  public void addRole(@PathVariable String username, @Valid @RequestBody RoleDTO roleDTO) {
    appUserService.addRole(username, roleDTO);
  }

  @Secured("ROLE_ADMIN")
  @DeleteMapping("/{username}/roles/{role}")
  public void removeRole(@PathVariable String username, @PathVariable String role) {
    appUserService.removeRole(username, role);
  }

}
