package mapper;

import bean.Employee;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @program: mybatis
 * @description: TODO
 * @author: HiBrandt
 * @create: 2021-02-28 09:42
 *
 * 1.Mybatis分页插件 PageHelper
 *   ①jar包
 *   ②全局配置文件：需要注意全局配置文件是有顺序的
 *   <plugins><plugin interceptor="com.github.pagehelper.PageInterceptor"></plugin></plugins>
 * 2.Page对象和PageInfo对象。（PageInfo对象比Page对象更详细一点）
 *   Page对象:把所有的对象查出来了 按照自己设置的分页信息进行部分结果的返回
 *   PageInfo对象：PageInfo对象封装查询结果。更详细点，以后再说吧
 *
 *
 *
 **/
public class TestMybatis05 {

    @Test
    public void  test1() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

        //设置分页信息
        Page<Object> page = PageHelper.startPage(3, 1);
        List<Employee> employeesList = mapper.getEmployeesByPage();
        //pageinfo  一共可以返回几页数据
        PageInfo<Employee> info  = new PageInfo<>(employeesList,5);
        for (Employee employee : employeesList) {
            System.out.println(employee);
        }

        System.out.println("=============获取分页相关的信息=================");
        System.out.println("当前页: " + page.getPageNum());
        System.out.println("总页码: " + page.getPages());
        System.out.println("总条数: " + page.getTotal());
        System.out.println("每页显示的条数: " + page.getPageSize());

        //具体返回哪几页
        int [] nums = info.getNavigatepageNums();
        System.out.println(Arrays.toString(nums));


    }
}
