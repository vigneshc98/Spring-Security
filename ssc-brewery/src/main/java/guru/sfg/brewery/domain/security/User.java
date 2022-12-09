package guru.sfg.brewery.domain.security;

import lombok.*;
import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User {
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

    @Singular  //provide a singular method for adding an role
    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name="user_role",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
    private Set<Role> roles;

    @Transient
    private  Set<Authority> authorities;

    public Set<Authority> getAuthorities() {
        return this.roles.stream()
                .map(Role::getAuthorities)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }
}
