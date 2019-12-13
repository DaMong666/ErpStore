package com.ztkj.storage.controller;

import com.ztkj.storage.model.Storage;
import com.ztkj.storage.service.StorageService;
import com.ztkj.sys.model.City;
import com.ztkj.sys.model.Province;
import com.ztkj.sys.model.User;
import com.ztkj.utils.DateUtil;
import com.ztkj.utils.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @Description:仓库类控制层
 * @Auther: huangx
 * @Date: 2019/11/20 19:34
 */
@Controller
@RequestMapping("storage/storage")
public class StorageController {
    @Resource
    private StorageService storageService;


    /*
     *查询所有省份下拉框
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.Province>
     * @auther: huangx
     * @date: 2019/11/21 9:55
     */
    @RequestMapping("province")
    public @ResponseBody List<Province> queryProvinceList(){
        //@ResponseBody把List<Employee>转json字符串
        // 先到浏览器上访问验证
        return storageService.queryAllProvince();
    }
    /*
     *查询所有省份下拉框
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.Province>
     * @auther: huangx
     * @date: 2019/11/21 9:55
     */
    @RequestMapping("loadcity")
    public @ResponseBody List<City> queryCity(){
        return storageService.queryAllCity();
    }
    /*
     *查询所有城市下拉框
     * @param: [provinceId]
     * @return: java.util.List<com.ztkj.sys.model.City>
     * @auther: huangx
     * @date: 2019/11/21 18:38
     */
    @RequestMapping("city")
    public @ResponseBody List<City> queryCityList(int provinceId){
        return storageService.queryAllCityBypId(provinceId);
    }

    /*
     *仓库模糊+分页
     * @param: [pageNo, storageName, provinceId, cityId]
     * @return: com.ztkj.utils.Page
     * @auther: huangx
     * @date: 2019/11/20 20:13
     */
    @RequestMapping("storagepage")
    public @ResponseBody Page queryPage(Integer pageNo,String storageName,Integer provinceId,Integer cityId,HttpSession session){
        if(pageNo == null || pageNo < 1){
            pageNo = 1;
        }
        Page page = new Page(pageNo,5);//当前页，每页5条
        //获取当前登录对象
        User user = (User) session.getAttribute("loginUser");
        //获取到登录对象的id主键（负责人）
        int principalId = user.getId();
        Integer.valueOf(principalId);
        System.out.println("==========Integer.valueOf(principalId);============"+Integer.valueOf(principalId));
        if(user.getNo().substring(0, 7).equals("0400500")){//一组组员04005001
            principalId = 101;//老马 一组主管
        }
        if(user.getNo().substring(0, 7).equals("0400700")){//二组组员04007001
            principalId = 6;//吕布 二组主管
        }
        page = storageService.queryByPage(page, storageName, provinceId, cityId,principalId);
        return page;
    }
    /*
     *判断是否是经理级别的  是才有添加权限
     * @param: []
     * @return: java.lang.String
     * @auther: huangx
     * @date: 2019/11/21 13:24
     */
    /*@RequestMapping("ismgr")
    public @ResponseBody String isMgr(){

    }*/
    /*
     *查询仓库负责人下拉框（仓库主管级别）
     * @param: []
     * @return: java.util.List<User>
     * @auther: huangx
     * @date: 2019/11/21 13:06
     */
    @RequestMapping("principal")
    public @ResponseBody List<User> queryPrincipalList(){
        return storageService.queryAllPrincipalPerson();
    }
    /*
     *获取当前用户
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.User>
     * @auther: huangx
     * @date: 2019/11/21 13:51
     */
    @RequestMapping("getcurruser")
    public @ResponseBody User queryGetCurrUser(HttpSession session){
        User user = (User) session.getAttribute("loginUser");
        return user;
    }
    /*
     *获取当前时间
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.User>
     * @auther: huangx
     * @date: 2019/11/21 13:51
     */
    @RequestMapping("getcurrtime")
    public @ResponseBody String queryGetCurrTime(){
        return DateUtil.currentDate("yyyy-MM-dd HH:mm:ss");
    }
    /*
     *添加一个仓库
     * @param: [storage]
     * @return: java.lang.String
     * @auther: huangx
     * @date: 2019/11/21 14:52
     */
    @RequestMapping("addStorage")
    public @ResponseBody String addStorage(Storage storage,@RequestParam("createTime") String createTime,HttpSession session){
        User user = (User) session.getAttribute("loginUser");
        storage.setCreatePerson(user);//获取当前对象
        storage.setCreateTime(DateUtil.stringToTimestamp(createTime));//把页面的时间转换成Date
        String info = "";
        try{
            storageService.addStorage(storage);
            info = "仓库信息添加成功！";
        }catch (Exception e){
            e.printStackTrace();
            info = "仓库信息添加失败";
        }
        return info;
    }
    /*
     *查询出仓库信息 到查看详情页面
     * @param: [empNo]
     * @return: com.ztkj.storage.model.Storage
     * @auther: huangx
     * @date: 2019/11/21 16:01
     */
    @RequestMapping("toview")
    public @ResponseBody Storage queryToView(int storageId){
        Storage storage = storageService.queryStorageById(storageId);
        return storage;
    }
    /*
     *修改一个仓库
     * @param: [storage]
     * @return: java.lang.String
     * @auther: huangx
     * @date: 2019/11/21 14:52
     */
    @RequestMapping("updateStorage")
    public @ResponseBody String updateStorage(Storage storage,@RequestParam("createTime") String createTime,
                                       HttpSession session){
        User user = new User();
        user.setId(storage.getCreatePerson().getId());
        user.setName(storage.getCreatePerson().getName());
        storage.setCreatePerson(user);
        storage.setCreateTime(DateUtil.stringToTimestamp(createTime));//把页面的时间转换成Date
        String info = "";
        try{
            storageService.updateStorage(storage);
            info = "仓库信息修改成功！";
        }catch (Exception e){
            e.printStackTrace();
            info = "仓库信息修改失败";
        }
        return info;
    }
    /*
     *注销仓库
     * @param: [storageId]
     * @return: java.lang.String
     * @auther: huangx
     * @date: 2019/11/22 9:48
     */
    @RequestMapping("can")
    public @ResponseBody String cancelStorage(int id){
        String info = "";
        try{
            storageService.cancelStorage(id);
            info = "1";
        }catch (Exception e){
            e.printStackTrace();
            info = "0";
        }
        return info;
    }
    /*
     *恢复仓库
     * @param: [storageId]
     * @return: java.lang.String
     * @auther: huangx
     * @date: 2019/11/22 9:48
     */
    @RequestMapping("rec")
    public @ResponseBody String recoverStorage(int id){
        String info = "";
        try{
            storageService.recoverStorage(id);
            info = "1";
        }catch (Exception e){
            e.printStackTrace();
            info = "0";
        }
        return info;
    }
}
