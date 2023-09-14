package com.hugo.service.impl;

import com.hugo.mapper.DeptMapper;
import com.hugo.pojo.Dept;
import com.hugo.service.DeptService;
import com.hugo.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpService empService;

    @Override
    public List<Dept> list() {
        return deptMapper.getList();
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        deptMapper.deleteById(id);
        empService.deleteByDeptId(id);
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
