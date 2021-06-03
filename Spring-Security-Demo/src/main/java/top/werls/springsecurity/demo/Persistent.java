package top.werls.springsecurity.demo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author leejiawei
 */
@Entity
@Table(name = "persistent_logins")
@Data
public class Persistent {
    @Id
    private String series;
    private String username;
    private String token;
    private Date lastUsed;
}
