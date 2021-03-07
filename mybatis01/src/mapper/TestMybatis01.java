package mapper;

import bean.Employee;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: mybatis
 * @description: TODO
 * @author: HiBrandt
 * @create: 2021-02-17 15:58
 *
 * Mybatis：优秀的持久层ORM（Object  Relation  Mapping）框架
 * 1.搭建Mybatis的步骤：
 *    1)jar包 Mybatis的只有一个jar包 但是同时需要jdbc以及log4J
 *    2)导入log4J的配置文件，只是固定格式内容的
 *    3)创建Mybatis全局配置文件mybatis-config.xml 名称一般都是固定的。(设置数据库配置)(与XxxMapper.xml映射文件绑定)
 *    4)创建映射文件XxxMapper.xml，并配置:
 *        -->两个绑定：
 *        ①接口全限定名要和映射文件的namespace保持一致
 *        ②接口中方法名和SQL标签的id保持一致(id是唯一的)
 *    5)获取mybatis操作数据库的会话对象SqlSession，通过SqlSession对象的getMapper()方法获取接口的动态代理实现类
 *  2.上面的绑定有点多啊梳理一遍
 *     ①全局配置文件mybatis-config.xml要和与XxxMapper.xml映射文件绑定。在全局配置文件中注册
 *       如果批量注册，这种方式要求Mapper映射文件名必须和接口名相同并且在同一目录下
 *     ②映射文件要和Mapper接口、实体类绑定。
 *       -->和Mapper接口绑定：接口全限定名要和映射文件的namespace保持一致 接口中方法名和SQL标签的id保持一致(id是唯一的)
 *       -->和实体类绑定："是在查询的时候" resultType标签属性和实体类包名.类名保持一致 (其实就是接口方法返回值,没返回值就没resultType~~~~)
 *  3.全局配置文件的是有顺序的 标签顺序不能乱了
 *      configuration 配置
        properties 属性 ：配置数据库配置，①properties子元素配置 ②通过引入外部配置文件配置jdbc.properties
        settings 设置： <setting name="mapUnderscoreToCamelCase" value="false"/> 将数据库的字段名的下划线替换成驼峰,还是字段名和属性名一致的东西
        typeAliases 类型命名 ： 为 Java 类型设置一个短的名字  没啥用感觉
        typeHandlers 类型处理器
        objectFactory 对象工厂
        plugins 插件
        environments 环境
        environment 环境变量
        transactionManager 事务管理器
        dataSource 数据源
        databaseIdProvider 数据库厂商标识
        mappers 映射器
     4.原来JDBC的提交和回滚是需要手动commit和rollback的
     5.注意：<!--查询一条记录且返回值类型为map的时候，resultType必须要有值-->
     6.	#{}和${} 的区别   #{}获取的是预编译的preparestatement ${}是statement
        #{key}：获取参数的值，预编译到SQL中。安全。 通过通配符赋值，通配符和名字没关系 和顺序有关系
     	${key}：获取参数的值，拼接到SQL中。有SQL注入问题。
        模糊查询和批量删除使用${key}，其余情况使用#{key}
     7.参数传递的方式：
        ①当传递单个参数时：MyBatis可直接使用这个参数，不需要经过任何处理。
        ②当传递多个参数时(比如where是两个条件)：
         MyBatis会把参数装入一个Map集合，集合的key为：param1，param2...值就是参数的值 或者key为：0，1…，值就是参数的值
         此时在映射的配置文件中参数就要写成 #{param1},#{param2}...或者 #{0},#{1} 这样才能获取到参数值
        ③针对情况②：如果我们不想写#{param1},#{param2}...或者 #{0},#{1} 那我们可以用注解@Param 为参数起一个名字
         这样传过去的map的键就是我们指定的名字了，可以写成#{map的key}
        ④POJO :直接传递POJO
        ⑤当传输参数为list或者Array时，mybatis会将list或者array放在map中，List以list为键，Array以array为键
            再讲使用foreach批量操作那里讲了


     8.获取主键的值: 需求: 插入一条新数据，立马查询这条数据的主键值
         -->标签加上两个属性 useGeneratedKeys="true"  keyProperty="id"
         -->keyProperty="id" 就是把返回的主键id 映射到属性id上

 *
 *
 **/
public class TestMybatis01 {

    /**
     * 小试牛刀
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {

        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //getMapper():会通过动态代理动态生成UserMapper的代理实现类
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        //查一条
        Employee employee = mapper.getEmployeeById("1");
        System.out.println(employee);
        sqlSession.close();

    }

    /**
     * 测试增删改查
     */
    @Test
    public void testCRUD() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        // 手动提交事务 SqlSession sqlSession = sqlSessionFactory.openSession();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);  //自动提交事务
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        //查多条
        /*List<Employee> allEmployee = mapper.getAllEmployee();
        System.out.println(allEmployee);*/
        //增加一条
        //id直接用null代替 反正我要的是后面的数据
        // jdbc 需要手动 提交或者回滚
        /*mapper.addEmployee(new Employee(null,"王柳","667@qq.com","女"));*/
        //sqlSession.commit(); 手动提交事务

        //删除
        /*Boolean aBoolean = mapper.deleteEmployee("7");
        System.out.println(aBoolean);*/

        //更新
        /*Integer integer = mapper.updateEmployee(new Employee(8, "网吧", "667@qq.com", "男"));
        System.out.println(integer);*/

        //查询一条记录返回map
        /*Map<String, Object> employeeByIdReturnMap = mapper.getEmployeeByIdReturnMap("2");
        System.out.println(employeeByIdReturnMap);*/

        //查询多条记录返回map
        /*Map<String, Object> allEmployeeByIdReturnMap = mapper.getAllEmployeeByIdReturnMap();
        System.out.println(allEmployeeByIdReturnMap);*/

        //插入一条新数据，立马查询这条数据键值
        /*Employee employee = new Employee(null,"王哈哈","667@qq.com","女");
        mapper.addEmployee(employee);
        System.out.println(employee.getId());  //把主键值映射到了id上*/

        //传递多个参数
        Employee employee = mapper.paramsByIDAndName("9", "女");
        System.out.println(employee);


    }
}
