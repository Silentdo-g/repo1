package yhp.dao;

import yhp.bean.AirInfo;

import java.util.List;

public interface AirInfoDao {

    //1.查询所有航班
    public List<AirInfo> findAll();
    //2.根据时间查询
    public List<AirInfo> findByTime(String time);
    //3.根据目的地查询
    public List<AirInfo> findByAddress(String address);
    //4.根据航班号删除航班
    public int delete(String number);
    //5.更新航班(航班是否存在)
    public int update(AirInfo airInfo,String time);
    //6.查询航班是否存在(航班号)
    public AirInfo findByNumber(String number);
}
