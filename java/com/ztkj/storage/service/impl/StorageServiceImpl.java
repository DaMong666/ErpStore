package com.ztkj.storage.service.impl;

import com.ztkj.storage.dao.StorageDao;
import com.ztkj.storage.model.Storage;
import com.ztkj.storage.service.StorageService;
import com.ztkj.sys.model.City;
import com.ztkj.sys.model.Province;
import com.ztkj.sys.model.User;
import com.ztkj.utils.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:仓库类服务实现层
 * @Auther: huangx
 * @Date: 2019/11/20 19:39
 */
@Service("storageService")
public class StorageServiceImpl implements StorageService {
    @Resource
    private StorageDao storageDao;
    /*
     *模糊查询+分页
     * @param: [page, storageName, provinceId, cityId]
     * @return: com.ztkj.utils.Page
     * @auther: huangx
     * @date: 2019/11/20 19:37
     */
    public Page queryByPage(Page page, String storageName, Integer provinceId, Integer cityId,Integer principalId){
        List<Storage> storageList = storageDao.findAllByPage(storageName, provinceId, cityId, page.getStartIndex(),page.getEndIndex(),principalId);
        int rowCount = storageDao.countByPage(storageName, provinceId, cityId,principalId);
        page.setResult(storageList, rowCount);
        return page;
    }
    /*
     *查询所有省份
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.Province>
     * @auther: huangx
     * @date: 2019/11/20 20:10
     */
    public List<Province> queryAllProvince(){
        return storageDao.findAllProvince();
    }
    /*
     *查询所有的城市
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.City>
     * @auther: huangx
     * @date: 2019/11/21 18:34
     */
    public List<City> queryAllCity(){
        return storageDao.findAllCity();
    }
    /*
     *根据省份id查询所有城市
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.City>
     * @auther: huangx
     * @date: 2019/11/20 20:11
     */
    public List<City> queryAllCityBypId(int provinceId){
        return storageDao.findAllCityBypId(provinceId);
    }
    /*
     *查询所有负责人（仓库主管级别）
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.User>
     * @auther: huangx
     * @date: 2019/11/21 12:53
     */
    public List<User> queryAllPrincipalPerson(){
        return storageDao.findAllPrincipalPerson();
    }
    /*
     *添加仓库
     * @param: [storage]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/21 11:35
     */
    @Transactional
    public void addStorage(Storage storage){
        storageDao.addStorage(storage);
    }
    /*
     *根据id查询仓库信息
     * @param: [storageId]
     * @return: com.ztkj.storage.model.Storage
     * @auther: huangx
     * @date: 2019/11/21 15:58
     */
    public Storage queryStorageById(int storageId){
        return storageDao.findStorageById(storageId);
    }

    /*
     *修改仓库
     * @param: [storage]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/21 11:35
     */
    @Transactional
    public void updateStorage(Storage storage){
        storageDao.updateStorage(storage);
    }
    /*
     *注销仓库
     * @param: [storageId]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/22 9:42
     */
    @Transactional
    public void cancelStorage(int storageId){
        storageDao.cancleStorage(storageId);
    }
    /*
     *恢复仓库
     * @param: [storageId]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/22 9:42
     */
    @Transactional
    public void recoverStorage(int storageId){
        storageDao.recoverStorage(storageId);
    }
}
