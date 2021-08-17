package yhp.test;

import yhp.bean.AirInfo;
import yhp.dao.AirInfoDao;
import yhp.dao.impl.AirInfoDaoImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) throws ParseException {
        //输出菜单
        int num = 0;
        AirInfoDao infoDao = new AirInfoDaoImpl();

        do{
            System.out.println("*****************欢迎使用航班信息管理系统*****************");
            System.out.println("1.列出所有航班信息 2.按起飞时间查询 3.按目的地查询 4.删除航班 5.更新航班 6.离开程序");
            System.out.println("请输入选择:");
            Scanner sc = new Scanner(System.in);
            num = sc.nextInt();
            switch(num){
                case 1:
                    List<AirInfo> infos = infoDao.findAll();
                    System.out.println("编号\t航班号\t目的地\t起飞时间");
                    for(int i = 0;i < infos.size();i++){
                        AirInfo airInfo = infos.get(i);
                        System.out.println((i+1)+"\t"+airInfo.getNumber()+"\t"+airInfo.getAddress()+"\t"+airInfo.getBeginTime());
                    }
                    break;
                case 2:
                    System.out.println("请输入起飞时间");
                    String time = sc.next();
                    List<AirInfo> airInfos = infoDao.findByTime(time);
                    System.out.println("编号\t航班号\t目的地\t起飞时间");
                    for(int i = 0;i < airInfos.size();i++){
                        AirInfo airInfo = airInfos.get(i);
                        System.out.println((i+1)+"\t"+airInfo.getNumber()+"\t"+airInfo.getAddress()+"\t"+airInfo.getBeginTime());
                    }
                    break;
                case 3:
                    System.out.println("请输入目的地");
                    String address = sc.next();
                    List<AirInfo> airs = infoDao.findByAddress(address);
                    System.out.println("编号\t航班号\t目的地\t起飞时间");
                    for(int i = 0;i < airs.size();i++){
                        AirInfo airInfo = airs.get(i);
                        System.out.println((i+1)+"\t"+airInfo.getNumber()+"\t"+airInfo.getAddress()+"\t"+airInfo.getBeginTime());
                    }
                    break;
                case 4:
                    System.out.println("请输入要删除的航班的编号");
                    String number = sc.next();
                    int count = infoDao.delete(number);
                    if(count > 0){
                        System.out.println("删除成功");
                    }else{
                        System.out.println("删除失败");
                    }
                    break;
                case 5:
                    System.out.println("请输入航班编号");
                    String number2 = sc.next();
                    AirInfo air = infoDao.findByNumber(number2);
                    if(air == null){
                        System.out.println("航班号错误");
                    }else{
                        System.out.println("请输入新的航班目的地:");
                        String addr = sc.next();
                        System.out.println("请输入新的航班起飞时间");
                        String time2 = sc.next();
                        AirInfo airInfo = new AirInfo();
                        airInfo.setNumber(number2);
                        airInfo.setAddress(addr);
//                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                        Date date = simpleDateFormat.parse(time2);
//                        airInfo.setBeginTime(date);

                        int result = infoDao.update(airInfo,time2);
                        if (result>0){
                            System.out.println("修改成功");
                        }else{
                            System.out.println("修改失败");
                        }
                    }
                    break;
                case 6:
                    break;
                default:
                    System.out.println("请输入正确的选项:");
        }
        }while(num!=6);
        System.out.println("已退出");
    }
}
