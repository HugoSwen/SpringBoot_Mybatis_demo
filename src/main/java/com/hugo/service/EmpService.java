package com.hugo.service;

import com.hugo.pojo.Emp;
import com.hugo.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.LongAccumulator;

public interface EmpService {

    Emp login(Emp emp);

    PageBean pageList(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    void deleteList(List<Integer> ids);

    void deleteByDeptId(Integer id);

    void add(Emp emp);

    Emp getById(Integer id);

    void update(Emp emp);
}
