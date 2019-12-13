package com.ztkj.storage.service;

import com.ztkj.storage.model.Storage;
import com.ztkj.sys.model.City;
import com.ztkj.sys.model.Province;
import com.ztkj.sys.model.User;
import com.ztkj.utils.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:仓库类服务层
 * @Auther: huangx
 * @Date: 2019/11/20 19:35
 */
public interface StorageService {
    /*
     *模糊查询+分页
     * @param: [page, storageName, provinceId, cityId]
     * @return: com.ztkj.utils.Page
     * @auther: huangx
     * @date: 2019/11/20 19:37
     */
    public Page queryByPage(Page page, String storageName,Integer provinceId,Integer cityId,Integer principalId);
    /*
     *查询所有省份
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.Province>
     * @auther: huangx
     * @date: 2019/11/20 20:10
     */
    public List<Province> queryAllProvince();
    /*
     *查询所有的城市
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.City>
     * @auther: huangx
     * @date: 2019/11/21 18:34
     */
    public List<City> queryAllCity();
    /*
     *根据省份id查询所有城市
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.City>
     * @auther: huangx
     * @date: 2019/11/20 20:11
     */
    public List<City> queryAllCityBypId(int provinceId);
    /*
     *查询所有负责人（仓库主管级别）
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.User>
     * @auther: huangx
     * @date: 2019/11/21 12:53
     */
    public List<User> queryAllPrincipalPerson();
    /*
     *添加仓库
     * @param: [storage]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/21 11:35
     */
    public void addStorage(Storage storage);
    /*
     *根据id查询仓库信息
     * @param: [storageId]
     * @return: com.ztkj.storage.model.Storage
     * @auther: huangx
     * @date: 2019/11/21 15:58
     */
    public Storage queryStorageById(int storageId);

    /*
     *修改仓库
     * @param: [storage]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/21 11:35
     */
    public void updateStorage(Storage storage);
    /*
     *注销仓库
     * @param: [storageId]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/22 9:42
     */
    public void cancelStorage(int storageId);
    /*
     *恢复仓库
     * @param: [storageId]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/22 9:42
     */
    public void recoverStorage(int storageId);

}
