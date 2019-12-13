package com.ztkj.storage.dao;

import com.ztkj.storage.model.Storage;
import com.ztkj.sys.model.City;
import com.ztkj.sys.model.Province;
import com.ztkj.sys.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:仓库表接口
 * @Auther: huangx
 * @Date: 2019/11/20 17:39
 */
public interface StorageDao {
    /*
     *分页+模糊查询
     * @param: [storageName, provinceId, cityId, startIndex, endIndex]
     * @return: java.util.List<com.ztkj.storage.model.Storage>
     * @auther: huangx
     * @date: 2019/11/20 17:44
     */
    public List<Storage> findAllByPage(@Param("storageName") String storageName,
                                       @Param("provinceId") Integer provinceId,
                                       @Param("cityId") Integer cityId,
                                       @Param("startIndex") int startIndex,
                                       @Param("endIndex")int endIndex,
                                       @Param("principalId") Integer principalId);
    /*
     *分页统计
     * @param: [storageName, provinceId, cityId]
     * @return: int
     * @auther: huangx
     * @date: 2019/11/20 18:56
     */
    public int countByPage(@Param("storageName") String storageName,
                           @Param("provinceId") Integer provinceId,
                           @Param("cityId") Integer cityId,
                           @Param("principalId") Integer principalId);
    /*
     *查询所有的省份
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.Province>
     * @auther: huangx
     * @date: 2019/11/20 19:53
     */
    public List<Province> findAllProvince();
    /*
     *查询所有的城市
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.City>
     * @auther: huangx
     * @date: 2019/11/21 18:32
     */
    public List<City> findAllCity();
    /*
     *根据省份id查询所有的城市
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.City>
     * @auther: huangx
     * @date: 2019/11/20 19:53
     */
    public List<City> findAllCityBypId(@Param("provinceId") int provinceId);

    /*
     *判断是否是经理
     * @param: []
     * @return: boolean
     * @auther: huangx
     * @date: 2019/11/21 13:26
     *//*
    public boolean ifisMgr();*/
    /*
     *查询所有的负责人（必须是主管级别）
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.User>
     * @auther: huangx
     * @date: 2019/11/21 12:27
     */
    public List<User> findAllPrincipalPerson();

    /*
     *添加仓库
     * @param: [storage]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/21 11:35
     */
    public void addStorage(@Param("storage") Storage storage);
    /*
     *根据仓库id查询仓库信息
     * @param: [storageId]
     * @return: com.ztkj.storage.model.Storage
     * @auther: huangx
     * @date: 2019/11/21 15:53
     */
    public Storage findStorageById(@Param("storageId")int storageId);
    /*
     *修改仓库
     * @param: [storage]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/21 11:35
     */
    public void updateStorage(@Param("storage") Storage storage);
    /*
     *注销仓库
     * @param: [storageId]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/22 9:31
     */
    public void cancleStorage(@Param("storageId")int storageId);
    /*
     *恢复仓库
     * @param: [storageId]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/22 9:32
     */
    public void recoverStorage(@Param("storageId")int storageId);
}
