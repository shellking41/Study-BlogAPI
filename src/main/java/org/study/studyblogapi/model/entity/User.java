package org.study.studyblogapi.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.study.studyblogapi.model.enums.Role;
import org.study.studyblogapi.model.enums.UsageType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String firstname;
  private String lastname;

  @Column(unique = true)
  private String email;
  private String password;

  @Enumerated(EnumType.STRING)
  private Role role;

  @ManyToMany
  @ToString.Exclude
  @JoinTable(
          name = "followers",
          joinColumns = @JoinColumn(name = "follower_id",nullable = false),
          inverseJoinColumns = @JoinColumn(name="following_id",nullable = false)
  )
  private List<User> following;

  @JsonIgnore
  @ToString.Exclude
  @ManyToMany(mappedBy = "following")
  private List<User> followers;

  @OneToMany(mappedBy = "user")
  @ToString.Exclude
  private List<Token> tokens;

  @OneToMany(mappedBy = "author",cascade = CascadeType.ALL,orphanRemoval = true)
  @ToString.Exclude
  private List<BlogPost> blogPosts;

  @ManyToMany(mappedBy = "likedByUsers", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @ToString.Exclude
  private List<BlogPost> likedPosts;

  @OneToMany(mappedBy = "commenter", cascade = CascadeType.ALL, orphanRemoval = true)
  @ToString.Exclude
  private List<Comment> comments;

  @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
  @ToString.Exclude
  @JoinColumn(name="media_file_id")
  private MediaFile userIcon;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return role.getAuthorities();
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }


  //megcsinalja azokat  a dolgokat amiket a adatbazis feltotelse elott kellene csinalni
  @PrePersist
  protected void onCreate(){
    role=Role.USER;}
}
