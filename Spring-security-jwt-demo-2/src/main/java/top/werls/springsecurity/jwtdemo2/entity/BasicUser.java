package top.werls.springsecurity.jwtdemo2.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author leejiawei
 */
@Table()
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class BasicUser implements Serializable {
    public static final long ID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String username;
    private String password;

    @ManyToMany(mappedBy = "users")
    @ToString.Exclude
    private List<BasicRules> authorities;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;

}
