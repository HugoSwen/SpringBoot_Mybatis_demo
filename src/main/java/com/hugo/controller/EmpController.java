package com.hugo.controller;

import com.hugo.annotation.Log;
import com.hugo.pojo.Emp;
import com.hugo.pojo.PageBean;
import com.hugo.pojo.Result;
import com.hugo.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理Controller
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * 员工分页查询
     */
    @GetMapping
    public Result pageList(@RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "5") Integer pageSize,
                           String name, Short gender,
                           @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                           @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end
    ) {
        log.info("分页查询参数：{},{},{},{},{},{}", page, pageSize, name, gender, begin, end);
        PageBean pageBean = empService.pageList(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }


    /**
     * 批量删除
     */
    @Log
    @DeleteMapping("/{ids}")
    public Result deleteList(@PathVariable List<Integer> ids) {
        log.info("批量删除操作, ids:{}", ids);
        empService.deleteList(ids);
        return Result.success();
    }

    /**
     * 增加员工
     */
    @Log
    @PostMapping
    public Result add(@RequestBody Emp emp) {
        log.info("新增员工, emp: {}", emp);
        empService.add(emp);
        return Result.success();
    }

    /**
     * 根据id查询员工
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据ID查询员工信息, id: {}", id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    /**
     * 更新员工信息
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("更新员工信息 : {}", emp);
        empService.update(emp);
        return Result.success();
    }
}





