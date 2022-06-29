package top.werls.dynamicdatabase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.werls.dynamicdatabase.entity.SysUser;
import top.werls.dynamicdatabase.mapper.SysUserMapper;

import java.util.List;

/**
 * @author Jiawei Lee
 * @version TODO
 * @since on  2022/6/27
 */

public interface SysUserService extends IService<SysUser> {

    void add(SysUser user);

    List<SysUser> get();
}
