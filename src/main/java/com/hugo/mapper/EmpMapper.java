package com.hugo.mapper;

import com.hugo.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理
 */
@Mapper
public interface EmpMapper {

    /**
     * 总员工数
     *
     * @return
     */
    @Select("select count(*) from emp")
    long count();

    /**
     * 批量查询员工
     *
     * @param name
     * @param gender
     * @param begin
     * @param end
     * @return
     */
    List<Emp> getList(String name, Short gender, LocalDate begin, LocalDate end);

    /**
     * 根据ID查询员工
     *
     * @param id
     * @return
     */
    @Select("select * from emp where  id = #{id}")
    Emp getById(Integer id);

    /**
     * 账号密码查询员工
     *
     * @param emp
     * @return
     */
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getByUsernameAndPassword(Emp emp);

    /**
     * 新增员工
     *
     * @param emp
     */
    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "value (#{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{dept_id}, #{create_time}, #{update_time})")
    void insert(Emp emp);

    /**
     * 批量删除员工
     *
     * @param ids
     */
    void deleteList(List<Integer> ids);

    /**
     * 根据部门id删除员工
     *
     * @param deptId
     */
    @Delete("delete from emp where dept_id = #{deptId}")
    void deleteByDeptId(Integer deptId);

    /**
     * 更新员工
     *
     * @param emp
     */
    void update(Emp emp);
}
