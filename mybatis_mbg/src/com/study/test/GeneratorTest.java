package com.study.test;

import com.study.beans.Employee;
import com.study.beans.EmployeeExample;
import com.study.mapper.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: mybatis
 * @description: TODO
 * @author: HiBrandt
 * @create: 2021-02-27 19:35
 *
 * 逆向工程：自动生成mapper、bean、以及映射配置文件
 * https://blog.csdn.net/qq_39056805/article/details/80585941
 * 过程：①导入逆向工程jar包 ②编写MBG配置文件 ③运行代码生成器生成代码
 * 就很简单 ~~~~
 *
 *
 *
 **/
public class GeneratorTest {

    /**
     * 代码生成器
     * @throws Exception
     */
    @Test
    public void testMBG() throws Exception {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File("./conf/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
                callback, warnings);
        myBatisGenerator.generate(null);
    }

    /**
     *
     *测试增删改查
     */
    @Test
    public void testCRUD() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        //EmployeeExample 里面包含了很多的方法
        EmployeeExample example = new EmployeeExample();
        //Criteria就是设置条件
        EmployeeExample.Criteria c1 = example.createCriteria();
        c1.andIdBetween(0, 3);

        EmployeeExample.Criteria c2 = example.createCriteria();
        c2.andLastNameEqualTo("网吧");

        example.or(c2);  //c1和c2两个条件合并
        List<Employee> employees = mapper.selectByExample(example);
        for (Employee employee : employees) {
            System.out.println(employee);
        }

        System.out.println("++++++++++++++");
        //设置为null查询全部
        List<Employee> employees1 = mapper.selectByExample(null);
        for (Employee employee : employees1) {
            System.out.println(employee);
        }


    }

    public SqlSessionFactory getSqlSessionFactory() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        return sqlSessionFactory;
    }




}
