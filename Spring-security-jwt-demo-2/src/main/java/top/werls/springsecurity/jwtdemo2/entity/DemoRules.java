package top.werls.springsecurity.jwtdemo2.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author leejiawei
 */

@Table
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class DemoRules implements Serializable {
    public static final long ID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String authorities;

    @ManyToMany(cascade = {CascadeType.REFRESH})
    @JoinTable(name = "role_user",
            joinColumns = {@JoinColumn(name = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "id")})
    @ToString.Exclude
    private List<DemoUser> users;
}
