package top.werls.dynamicdatabase.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.werls.dynamicdatabase.entity.SysUser;
import top.werls.dynamicdatabase.mapper.SysUserMapper;
import top.werls.dynamicdatabase.service.SysUserService;

import java.util.List;

/**
 * @author Jiawei Lee
 * @version TODO
 * @since on  2022/6/28
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {


    @Override
    public void add(SysUser user) {
        this.save(user);
    }
// 开启事务会在主库执行。
//    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<SysUser> get() {
        return list();
    }


}
