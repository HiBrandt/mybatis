package mapper;

import bean.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @program: mybatis
 * @description: TODO
 * @author: HiBrandt
 * @create: 2021-02-27 14:57
 *
 *  Mybatis的缓存机制：一级缓存、二级缓存、三级缓存（第三方缓存）
 *  一级缓存：本地缓存，默认开启。
 *    ①"作用域默认为 "sqlSession"。当Session flush 或close后, 该Session中的所有Cache将被清空。"
 *    ②一级缓存失效的情况：
 *     1)不同的SqlSession对应不同的一级缓存
 *     2)同一个SqlSession但是查询条件不同sql语句不同
 *     3)同一个SqlSession两次查询期间执行了任何一次增删改操作
 *     4)同一个SqlSession两次查询期间手动清空了缓存
 *  二级缓存：全局作用域缓存，默认不开启
 *    ①开启步骤：
 *     1)全局配置文件中开启二级缓存<setting name="cacheEnabled" value="true"/>
 *     2)需要使用二级缓存的映射文件处使用cache配置缓存<cache />
 *     3)POJO需要实现Serializable接口
 *    ②二级缓存<cache />的几个属性：	eviction 缓存策略LRU(移除最不经常用的缓存)、FIFO (先进先出) ...其他的没啥重要的
 *
 *    注意：sqlSession.clearCache()：只是用来清除一级缓存。二级缓存在 SqlSession 关闭或提交之后才会生效
 *  三级缓存：第三方缓存，纯Java的进程内缓存框架
 *    ①开启步骤：导入ehcache包，以及整合包，日志包
 *    ②	编写ehcache.xml配置文件
 *    ③	配置cache标签 <cache type="org.mybatis.caches.ehcache.EhcacheCache"></cache>
 *    注意：三级缓存不用实现Serializable接口
 *
 **/
public class TestMybatis04 {

    @Test
    public void  test1() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = mapper.getEmployeeByID("3");
        System.out.println(employee);
        //提交或关闭才会生效
        sqlSession.commit();
        System.out.println("+++++++++++++++++++++");
        EmployeeMapper mapper2 = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee2 = mapper.getEmployeeByID("3");
        System.out.println(employee2);

    }

}
