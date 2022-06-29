package top.werls.dynamicdatabase.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.werls.dynamicdatabase.entity.SysUser;

import java.util.List;

/**
 * @author Jiawei Lee
 * @version TODO
 * @since on  2022/6/27
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    @Select(value = "SELECT  * FROM sys_user WHERE username like '%l%' ")
    List<SysUser> get();

    @Select(value = "SELECT * FROM sys_user  where username =#{username} ")
    List<SysUser> getByUsername(String username);
}
