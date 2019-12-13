package com.ztkj.market.service;

import com.ztkj.market.model.Customer;
import com.ztkj.sys.model.City;
import com.ztkj.sys.model.Position;
import com.ztkj.sys.model.Province;
import com.ztkj.sys.model.User;
import com.ztkj.utils.Page;

import java.util.List;

/**
 * @Description:
 * @Auther: YDM
 * @Date: 2019/11/20 19:29
 */
public interface CustomerService {
    /**
     * @description: 客户分页查询
     * @param: [page, customer]
     * @return: com.ztkj.utils.Page
     * @auther: YDM
     * @date: 2019/11/20 19:39
     */
    public Page queryByPage(Page page, Customer customer, Integer provinceId, Integer cityId,Integer userIsNull,User user);

    /**
     * @description: 根据Id查询客户
     * @param: [id]
     * @return: com.ztkj.market.model.Customer
     * @auther: YDM
     * @date: 2019/11/21 19:37
     */
    public Customer queryById(int id);

    /**
     * @description: 添加客户
     * @param: [customer]
     * @return: void
     * @auther: YDM
     * @date: 2019/11/22 10:18
     */
    public void addCus(Customer customer);

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

    /**
     * @description: 更新客户信息
     * @param: [customer]
     * @return: void
     * @auther: YDM
     * @date: 2019/11/22 16:37
     */
    public void updateCus(Customer customer);

    /**
     *查询可用客户
     * @param: []
     * @return: java.util.List<com.ztkj.market.model.Customer>
     * @author: liss
     * @date: 2019/11/25 14:18
     */

    public List<Customer> queryNiceCustomer(User user);

    /*注销，恢复，分配*/
    public void updateStatusUp(int id);
    public void updateStatusDown(int id);
    public void updateUser(int id, User userid);
    public List<Position> queryPosition();
    public List<User> queryCheckUser(String positionno);
}
