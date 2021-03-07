package mapper;

import bean.Department;
import bean.Employee;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @program: mybatis
 * @description: TODO
 * @author: HiBrandt
 * @create: 2021-02-23 21:33
 *
 * 1.自定义映射:
 * POJO中的属性可能会是一个对象,我们可以使用联合查询，并以级联属性的方式封装对象.使用association标签定义对象的封装规则
 * association 用于映射复杂的数据类型
 * <id column="did" property="id"/> 主键的映射
 * <result column="deptName" property="deptName"/> 列的映射
 * <resultMap id="myEmpAndDept" type="bean.Employee"> id用于和<select>的resultMap绑定 type查询的是哪个bean(属性是某个POJO对象哦)
 * 2.分布查询：通过Employee的id 查到 Employee的pojo属性dept(Department)的值 有点子查询的味道，部门信息在另一张表里
 *   select = "mapper.DepartmentMapper.getDeptById" column="did">
 *   ①select 指定 DepartmentMapper接口的getDeptById的方法 然后去找 DepartmentMapper.xml的<select>
 *      把 DepartmentMapper.xml的<select>查询结果封装到Employee
 *   ②column="did" 其实就是查询出来的Employee的did 用这个did去作为条件查DepartmentMapper.xml的<select>
 *   注意：resultType和resultMap 别写错了  记得注册DepartmentMapper.xml
 * 3.分布查询使用延迟加载。在分布查询时，如果不需要查询涉及的分布字段比如：empByIdStep.getEmail() 开启延迟加载提高性能
 *    ①在全局配置文件添加：（全局配置就是全局有效）
 *    <!--分布查询开启延迟加载(两个配置搭配使用)-->
        <setting name="lazyLoadingEnabled" value="true"/>
      <!--是否需要加载全部字段-->
        <setting name="aggressiveLazyLoading" value="false"/>
      ②分布查询懒加载单个有效：
      在映射文件 association标签 或者 collection标签 中设置fetchType="eager" 不开启懒加载 fetchType=”lazy”开启懒加载
   4.当我们查询的bean的属性包含其他pojo的时候 就用resultMap (association标签 或者 collection标签)
   5.一对多。一个部门多个员工 collection标签 收集自己的一堆 联想到hive查询时用到的
      <collection property="emps" ofType="bean.Employee">
       property: 关联的属性名
       ofType: 集合中元素的类型
   扩展：当分布查询需要传递多个参数时候 id=xxx，name=xxx 可以把参数封装成map。语法如下: {k1=v1, k2=v2....}
        其中k1和k2为参数名字  v1和v2为参数值
    select = "mapper.DepartmentMapper.getDeptById" column="did"> --> {did=did,dname=dname....} 传递给前面的select，名字要一致
 *
 *
 *
 *
 *
 **/
public class TestMybatis02 {


    @Test
    public void test1() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        //自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        //自定义映射
        /*List<Employee> employeeAndDept = mapper.getEmployeeAndDept();
        System.out.println(employeeAndDept);*/

        //分布查询
        /*Employee empByIdStep = mapper.getEmpByIdStep("4");
        System.out.println(empByIdStep);*/

        //分布查询延迟加载
        /*Employee empByIdStep = mapper.getEmpByIdStep("4");
        System.out.println(empByIdStep.getEmail());*/

        //一对多
        DepartmentMapper deptMapper = sqlSession.getMapper(DepartmentMapper.class);
        Department dept = deptMapper.getDeptEmpsByID(1);
        System.out.println(dept);

    }

}
