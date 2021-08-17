package yhp.dao.impl;

import yhp.bean.AirInfo;
import yhp.dao.AirInfoDao;
import yhp.utils.DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirInfoDaoImpl extends DBUtils implements AirInfoDao {
    @Override
    public List<AirInfo> findAll() {
        ArrayList<AirInfo> arrayList = new ArrayList<AirInfo>();

        try {
            String sql = "select * from airinfo";
            resultSet = query(sql, null);
            while(resultSet.next()){
                AirInfo airInfo = new AirInfo();
                airInfo.setNumber(resultSet.getString("number"));
                airInfo.setAddress(resultSet.getString("address"));
                airInfo.setBeginTime(resultSet.getDate("begintime"));
                arrayList.add(airInfo);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
        return arrayList;
    }

    @Override
    public List<AirInfo> findByTime(String time) {
        ArrayList arrayList = new ArrayList();//存储结果集

        try {
            String sql = "select * from airinfo where begintime=?";
            ArrayList params = new ArrayList();
            params.add(time);
            resultSet = query(sql, params);


            while(resultSet.next()){
                AirInfo airInfo = new AirInfo();
                airInfo.setNumber(resultSet.getString("number"));
                airInfo.setAddress(resultSet.getString("address"));
                airInfo.setBeginTime(resultSet.getDate("begintime"));
                arrayList.add(airInfo);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
        return arrayList;
    }

    @Override
    public List<AirInfo> findByAddress(String address) {
        ArrayList arrayList = new ArrayList();//存储结果集

        try {
            String sql = "select * from airinfo where address=?";
            ArrayList params = new ArrayList();
            params.add(address);
            resultSet = query(sql, params);


            while(resultSet.next()){
                AirInfo airInfo = new AirInfo();
                airInfo.setNumber(resultSet.getString("number"));
                airInfo.setAddress(resultSet.getString("address"));
                airInfo.setBeginTime(resultSet.getDate("begintime"));
                arrayList.add(airInfo);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
        return arrayList;
    }

    @Override
    public int delete(String number) {
        int count = 0;//存储影响行数
        try {
            String sql = "delete from airinfo where number=?";
            ArrayList params = new ArrayList();
            params.add(number);
            count = update(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return count;
    }

    @Override
    public int update(AirInfo airInfo,String time) {
        String sql = "update airinfo set address=?,begintime=? where number=?";
        ArrayList params = new ArrayList();
        params.add(airInfo.getAddress());
        params.add(time);
        params.add(airInfo.getNumber());
        int result = update(sql,params);
        return result;
    }

    @Override
    public AirInfo findByNumber(String number) {
        AirInfo airInfo = null;
        try {
            String sql = "select * from airinfo where number=?";
            ArrayList params = new ArrayList();
            params.add(number);
            resultSet = query(sql, params);

            while(resultSet.next()){
                airInfo = new AirInfo();
                airInfo.setNumber(resultSet.getString("number"));
                airInfo.setAddress(resultSet.getString("address"));
                airInfo.setBeginTime(resultSet.getDate("begintime"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }

        return airInfo;
    }
}
