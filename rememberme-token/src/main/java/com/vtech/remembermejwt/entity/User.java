package com.vtech.remembermejwt.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User implements UserDetails, CredentialsContainer {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private  String username;
    private String password;
    @Builder.Default  //bcz @Builder will not pick up default properties
    private  Boolean accountNonExpired = true;

    @Builder.Default  //bcz @Builder will not pick up default properties
    private  Boolean accountNonLocked = true;

    @Builder.Default  //bcz @Builder will not pick up default properties
    private  Boolean credentialsNonExpired = true;

    @Builder.Default  //bcz @Builder will not pick up default properties
    private  Boolean enabled = true;

    @Transient
    @Override
    public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> set = new HashSet<>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
        SimpleGrantedAuthority authority2 = new SimpleGrantedAuthority("ROLE_USER");

        set.add(authority);
        set.add(authority2);

        return set;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp lastModifiedDate;
}
