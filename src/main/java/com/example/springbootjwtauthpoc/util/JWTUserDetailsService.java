package com.example.springbootjwtauthpoc.util;

import com.example.springbootjwtauthpoc.entity.AppUser;
import com.example.springbootjwtauthpoc.repository.AppUserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JWTUserDetailsService implements UserDetailsService {

  private final AppUserRepository appUserRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    AppUser appUser = appUserRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User: " + username + " not  found"));
    List<SimpleGrantedAuthority> authorities = appUser.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
        .collect(Collectors.toList());
    return new User(appUser.getUsername(), appUser.getPassword(), authorities);
  }
}
