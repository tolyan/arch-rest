package pro.example.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;

/**
 * Author: Anatoly Rybalchenko
 * Date: 11/29/13
 * Time: 1:18 PM
 */
public class MyBatisSqlSessionFactory {
    private static SqlSessionFactory sqlSessionFactory;
    private static final String resource = "mybatis.xml";

    public static SqlSessionFactory getSqlSessionFactory(){
        if (sqlSessionFactory == null){
                InputStream reader = MyBatisSqlSessionFactory.class.getClassLoader().getResourceAsStream(resource);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
       }
        return sqlSessionFactory;
    }
}
