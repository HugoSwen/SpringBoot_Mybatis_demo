package com.hugo.service;

import com.hugo.pojo.Emp;
import com.hugo.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.LongAccumulator;

public interface EmpService {
    /**
     * 员工登录
     *
     * @param emp
     * @return
     */

    Emp login(Emp emp);

    PageBean pageList(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    /**
     * 批量删除
     *
     * @param ids
     */
    void deleteList(List<Integer> ids);

    /**
     * 部门删除对应员工删除
     *
     * @param id
     */
    void deleteByDeptId(Integer id);

    /**
     * 新增员工
     *
     * @param emp
     */
    void add(Emp emp);

    /**
     * 根据ID查询员工
     *
     * @param id
     * @return
     */
    Emp getById(Integer id);

    /**
     * 更新员工
     *
     * @param emp
     */
    void update(Emp emp);
}
