package org.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.demo.model.ComparableEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "users")
@Getter @Setter
public class User extends ComparableEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    @NotBlank
    @Size(max = 20)
    @Column(name = "username")
    private String username;
     @NotBlank
    @Size(max = 120)
    @Column(name = "password")
    private String password;
    @NotBlank
    @Column(name = "enabled")
    private boolean enabled;
    @OneToMany(mappedBy = "user",fetch=FetchType.EAGER)
    private Set<Role> authorities = new HashSet<>();

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
    @Override
    public Integer getId(){
        return getId();
    }
}
