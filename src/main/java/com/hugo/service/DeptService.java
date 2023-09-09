package com.hugo.service;

import com.hugo.pojo.Dept;

import java.util.List;

/**
 * 部门管理
 */
public interface DeptService {

    List<Dept> list();

    void delete(Integer id);

    void add(Dept dept);

    void update(Dept dept);
}
