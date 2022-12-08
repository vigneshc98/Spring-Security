package guru.sfg.brewery.domain.security;

import lombok.*;
import javax.persistence.*;
import java.util.Set;

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

    @Singular  //provide a singular method for adding an authority
    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name="user_authority",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
    private  Set<Authority> authorities;
}
