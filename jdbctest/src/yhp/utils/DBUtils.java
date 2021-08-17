package yhp.utils;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

public class DBUtils {//工具类
    //定义变量
    private Connection connection;
    protected ResultSet resultSet;
    private PreparedStatement pps;
    private int count;

    private static String url;
    private static String userName;
    private static String userPass;
    private static String driverName;

    //德鲁伊
    private static DruidDataSource dataSource = new DruidDataSource();

    //加载驱动
    static{
        ResourceBundle bundle = ResourceBundle.getBundle("db");
        driverName = bundle.getString("driverclass");
        url = bundle.getString("url");
        userName = bundle.getString("uname");
        userPass = bundle.getString("upass");

        dataSource.setUsername(userName);
        dataSource.setPassword(userPass);
        dataSource.setDriverClassName(driverName);
        dataSource.setUrl(url);
    }
    //获得链接
    protected  Connection getConnection() {
        try{
            connection = dataSource.getConnection();
//            connection = DriverManager.getConnection(url, userName, userPass);
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return connection;
    }

    //得到预状态通道
    protected  PreparedStatement getPps(String sql){
        try{
            getConnection();
            pps= connection.prepareStatement(sql);
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return pps;
    }

    //绑定参数 List表示给占位符所赋的值
    protected void param(List list){
        if(list!=null && list.size()>0){
            for(int i=0;i<list.size();i++){
                try {
                    pps.setObject(i+1,list.get(i));
                }catch(SQLException throwables){
                    throwables.printStackTrace();
                }
            }
        }
    }

    //执行操作
    protected int update(String sql,List list){
        getPps(sql);//得到pps
        param(list);//绑定值
        try {
            count=pps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    //查询
    protected ResultSet query(String sql,List list){
        getPps(sql);//得到pps
        param(list);//绑定值
        try {
            resultSet=pps.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    //关闭资源
    protected void closeAll(){
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (connection != null) {
                connection.close();
            }

            if (pps != null) {
                pps.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }




}
