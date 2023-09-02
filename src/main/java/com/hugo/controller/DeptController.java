package com.hugo.controller;

import com.hugo.annotation.Log;
import com.hugo.pojo.Dept;
import com.hugo.pojo.Result;
import com.hugo.service.DeptService;
import com.hugo.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理Controller
 */
@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private EmpService empService;

    /**
     * 查询部门数据
     *
     * @return
     */
    @GetMapping
    public Result list() {
        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }


    /**
     * 删除部门
     *
     * @return
     */
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("根据id删除部门:{}", id);
        deptService.delete(id);
        empService.deleteByDeptId(id);
        return Result.success();
    }


    /**
     * 新增部门
     *
     * @return
     */
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        log.info("新增部门: {}", dept);
        deptService.add(dept);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Dept dept) {
        log.info("更新部门: {}", dept);
        deptService.update(dept);
        return Result.success();
    }
}
