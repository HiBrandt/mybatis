package mapper;

import bean.Department;

/**
 * @program: mybatis
 * @description: TODO
 * @author: HiBrandt
 * @create: 2021-02-23 21:32
 **/
public interface DepartmentMapper {

     Department getDeptById(Integer id); //分布查询用到的

     Department getDeptEmpsByID(Integer id); //一对多

}
