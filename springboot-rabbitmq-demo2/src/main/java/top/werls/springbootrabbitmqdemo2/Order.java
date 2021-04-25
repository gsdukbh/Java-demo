package top.werls.springbootrabbitmqdemo2;

import lombok.Data;

import java.io.Serializable;

/**
 * @author leejiawei
 */
@Data
public class Order implements Serializable {

    private Long id;
    private String name;
}
