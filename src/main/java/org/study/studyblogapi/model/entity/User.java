package org.study.studyblogapi.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.study.studyblogapi.model.enums.Role;

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
  @JoinTable(
          name = "followers",
          joinColumns = @JoinColumn(name = "follower_id",nullable = false),
          inverseJoinColumns = @JoinColumn(name="following_id",nullable = false)
  )
  private List<User> following;

  @JsonIgnore
  @ManyToMany(mappedBy = "following")
  private List<User> followers;

  @OneToMany(mappedBy = "user")
  private List<Token> tokens;

  @OneToMany(mappedBy = "author",cascade = CascadeType.ALL,orphanRemoval = true)
  private List<BlogPost> blogPosts;

  @ManyToMany(mappedBy = "likedByUsers", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  private List<BlogPost> likedPosts;


  @OneToMany(mappedBy = "commenter", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Comment> comments;

  @OneToOne(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
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
    role=Role.USER;
  }
}
