package mapper;

import bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: mybatis
 * @description: 增删改查
 * @author: HiBrandt
 * @create: 2021-02-18 21:58
 **/
public interface EmployeeMapper {


    //根据ID查询一条记录
    Employee getEmployeeById(String id);

    //查询所有记录
    List<Employee> getAllEmployee();

    //增加一条记录
    //也可以设置返回值 布尔或者影响记录数
    void addEmployee(Employee employee);

    //删除一条记录
    //也可以设置返回值 布尔或者影响记录数
    Boolean deleteEmployee(String id);

    //修改一条记录
    //也可以设置返回值 布尔或者影响记录数
    Integer updateEmployee(Employee employee);


    //查询单条记录返回map  (key:属性名 value:属性值)
    Map<String,Object> getEmployeeByIdReturnMap(String id);

    //查询多条记录返回map
    @MapKey("id") //设置map的键
    Map<String,Object> getAllEmployeeByIdReturnMap();

    //传递多个参数
    //映射文件的必须写成 #{param1},#{param2}...或者 #{0},#{1}
    /*Employee paramsByIDAndName(String id,String gender);*/
    //映射文件的必须写成 #{id},#{gender}
    Employee paramsByIDAndName(@Param("id") String id, @Param("gender")String gender);



}
