package com.hugo.controller;

import com.hugo.pojo.Emp;
import com.hugo.pojo.Result;
import com.hugo.service.EmpService;
import com.hugo.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("员工登录:{}", emp);

        Emp e = empService.login(emp);

        if (e != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("username", e.getUsername());
            claims.put("name", e.getName());

            String jwt = JwtUtils.generateJWT(claims);
            return Result.success(jwt);
        } else
            return Result.error("用户名或密码错误");
    }

}
