package top.werls.dynamicdatabase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Jiawei Lee
 * @version TODO
 * @since on  2022/6/27
 */
@Data
@TableName("sys_user")
public class SysUser implements Serializable {
    public static final long ID = 123L;
    @TableId(type= IdType.ASSIGN_ID)
    private Long id;
    private String username;
    private String password;
}
