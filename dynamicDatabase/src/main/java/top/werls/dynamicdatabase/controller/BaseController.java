package top.werls.dynamicdatabase.controller;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.werls.dynamicdatabase.entity.SysUser;
import top.werls.dynamicdatabase.mapper.SysUserMapper;
import top.werls.dynamicdatabase.service.SysUserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jiawei Lee
 * @version TODO
 * @since on  2022/6/28
 */
@RestController
@Log4j2
public class BaseController {

    @Resource
    public SysUserService service;

    @Resource
    public SysUserMapper mapper;


    @GetMapping("/write")
    public String write(String i) {
        var user = new SysUser();
        user.setUsername("test");
        user.setUsername(i);
        service.add(user);
        log.info(i);
        return "ok";
    }

    @GetMapping("/get")
    public List<SysUser> get() {
        var s = service.getById("1541796492998545408");
        log.error(s);
        return service.get();
    }
}
