package com.hugo.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hugo.mapper.EmpMapper;
import com.hugo.pojo.Emp;
import com.hugo.pojo.PageBean;
import com.hugo.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    /**
     * 员工登录
     *
     * @param emp
     * @return Emp
     */
    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUsernameAndPassword(emp);
    }

    @Override
    public PageBean pageList(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        PageHelper.startPage(page, pageSize);
        List<Emp> empList = empMapper.getList(name, gender, begin, end);
        Page<Emp> empPage = (Page<Emp>) empList;
        return new PageBean(empPage.getTotal(), empPage.getResult());
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    @Override
    public void deleteList(List<Integer> ids) {
        empMapper.deleteList(ids);
    }

    /**
     * 按部门删除员工
     *
     * @param id
     */
    @Override
    public void deleteByDeptId(Integer id) {
        empMapper.deleteByDeptId(id);
    }

    /**
     * 增加员工
     *
     * @param emp
     */
    @Override
    public void add(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    /**
     * 根据id获取员工信息
     *
     * @param id
     * @return Emp
     */
    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    /**
     * 更新员工信息
     *
     * @param emp
     */
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

}
