package com.ontology.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ontology.model.AppUser;
public class UserDetailsImpl implements UserDetails {
  private static final long serialVersionUID = 1L;

  private String id;
  
  private String role;

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  private String username;

  private String email;

  @JsonIgnore
  private String password;

  private Collection<? extends GrantedAuthority> authorities;

//  public UserDetailsImpl(String id, String username, String email, String password,
//      Collection<? extends GrantedAuthority> authorities) {
//    this.id = id;
//    this.username = username;
//    this.email = email;
//    this.password = password;
//    this.authorities = authorities;
//  }

  public UserDetailsImpl(String id, String username, String email, String password,String role) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.role = role;
  }
  public static UserDetailsImpl build(AppUser user) {
//    List<GrantedAuthority> authorities =
//        user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName().name()))
//            .collect(Collectors.toList());

    return new UserDetailsImpl(user.getId(), user.getUsername(), user.getName(),
        user.getPassword(), user.getRole());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }



  @Override
  public boolean isAccountNonExpired() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isEnabled() {
    // TODO Auto-generated method stub
    return true;
  }
  
  @Override
  public boolean equals(Object o) {
      if (this == o)
          return true;
      if (o == null || getClass() != o.getClass())
          return false;
      UserDetailsImpl user = (UserDetailsImpl) o;
      return Objects.equals(id, user.id);
  }
}
