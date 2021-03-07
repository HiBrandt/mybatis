package mapper;


import bean.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: mybatis
 * @description: TODO
 * @author: HiBrandt
 * @create: 2021-02-23 21:33
 * 1.动态sql:灵活的拼装设置sql
 *   若页面中没有设置没有设置此条件，那么sql语句中一定不能出现该条件。
 *   如果没有给属性赋值，而这个sql的where条件中有该属性，那么就会将属性的默认值(0,null,1...)传给sql，这样查询出来的数据是错误的
 * 2. <where> <if test="条件"></if> <where/>   string类型判断!=null and !='' 写的时候记得双引号里面只能写单引号不能写双引号
 * 3.Trim 可以在条件判断完的SQL语句前后 添加或者去掉指定的字符 (懒的没写 工作用的时候再说吧)
     prefix: 添加前缀
     prefixOverrides: 去掉前缀
     suffix: 添加后缀
     suffixOverrides: 去掉后缀
   4. choose标签：主要是用于分支判断，类似于java中的switch case,只会满足所有分支中的一个
     <choose> <when><when/> <otherwise><otherwise/>  <choose/>  所有when不符合就走otherwise
   5.foreach 标签:用于循环迭代 支持.出来的 例如循环的元素对象.属性 emp.lastName
        <foreach collection="list" separator="," item="emp">
        (null,#{emp.lastName},#{emp.email},#{emp.gender})
        </foreach>
        collection: 要迭代的集合
        item: 当前从集合中迭代出的元素
        open: 开始字符
        close:结束字符
        separator: 循环体和循环体之间的分隔符。例如添加用逗号valuse(),(),() 批量更新用;因为是多条sql语句
        index:
        迭代的是List集合: index表示的当前元素的下标
        迭代的Map集合:  index表示的当前元素的key

   6.批量删除用${} 的例子来了。
   7.sql 标签：就是写个经常会用到的sql,然后再其他地方引用一下，减少重复工作。可以多次引用
        <sql id="selectSQL">
        select id , last_name, email ,gender from tbl_employee
        </sql>

        <include refid="selectSQL"></include>

 *
 **/
public class TestMybatis03 {


    @Test
    public void test1() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        //自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

        /*Employee employee = new Employee();
        employee.setId(1);
        employee.setLastName("张大大");
        employee.setEmail("3863@qq.com");
        employee.setGender("1");*/
        // where标签
        /*List<Employee> employees = mapper.getdynamicSQl(employee); */
        // choose标签 查询
        /*List<Employee> employees = mapper.getdynamicSQlChoose(employee);
        for (Employee emp : employees) {
            System.out.println(emp);
        }*/
        // choose标签 添加
        /*Boolean aBoolean = mapper.insetDataByChoose(employee);
        System.out.println(aBoolean);*/

        // 批量删除 原始方法 ${value}
        /*String eids = "9,10,11";
        mapper.batchDelete(eids);*/

        // 批量添加 foreach
        /*Employee employee = new Employee(null,"aaa","123@qq.com","男",null,null);
        Employee employee2 = new Employee(null,"aaa","123@qq.com","男",null,null);
        Employee employee3 = new Employee(null,"aaa","123@qq.com","男",null,null);
        List<Employee> emps = new ArrayList<>();
        emps.add(employee);
        emps.add(employee2);
        emps.add(employee3);
        mapper.batchInsert(emps);
*/



    }

}
