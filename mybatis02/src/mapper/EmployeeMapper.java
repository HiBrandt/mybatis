package mapper;

import bean.Employee;

import java.util.List;

/**
 * @program: mybatis
 * @description: TODO
 * @author: HiBrandt
 * @create: 2021-02-23 21:28
 **/
public interface EmployeeMapper {


    //自定义映射
    List<Employee> getEmployeeAndDept();

    //分布查询
    Employee getEmpByIdStep(String id);

    //动态sql,根据多个where查询 注意不是分布的根据多个where
    List<Employee> getdynamicSQl(Employee employee);

    //动态sql,根据单个where查询 类似于java中的switch case,只会满足所有分支中的一个
    List<Employee> getdynamicSQlChoose(Employee employee);

    //动态sql，用choose实现 男:女 ->0:1的映射
    Boolean insetDataByChoose(Employee employee);

    //批量删除 原始方法 用$了。也可以用动态sql的foreach
    void batchDelete(String eids);

    //批量查询foreach就不写了 写个批量添加foreach就行了 俩是差不多的
    void batchInsert(List<Employee> emps);

    //批量修改也是一样的  只不过需要再修改下jdbc链接的参数

    //测试缓存
    Employee getEmployeeByID(String id);

    //测试分页插件
    List<Employee> getEmployeesByPage();




}
