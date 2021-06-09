package top.werls.springsecurity.jwtdemo2.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author leejiawei
 */
@Table()
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class DemoUser implements Serializable {
    public static final long ID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String username;
    private String password;

    @ManyToMany(mappedBy = "users")
    @ToString.Exclude
    private List<DemoRules> authorities;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;

}
