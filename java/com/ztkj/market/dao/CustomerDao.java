package com.ztkj.market.dao;

import com.ztkj.market.model.Customer;
import com.ztkj.sys.model.City;
import com.ztkj.sys.model.Position;
import com.ztkj.sys.model.Province;
import com.ztkj.sys.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 顾客Dao
 * @Auther: YDM
 * @Date: 2019/11/20 17:37
 */
public interface CustomerDao {
    /**
     * @description: 客户分页查询
     * @param: [customer, startIndex, endIndex]
     * @return: java.util.List<com.ztkj.market.model.Customer>
     * @auther: YDM
     * @date: 2019/11/20 19:36
     */
    public List<Customer> findByPage(@Param("customer") Customer customer,
                                     @Param("provinceId") Integer provinceId,
                                     @Param("cityId") Integer cityId,
                                     @Param("startIndex") int startIndex,
                                     @Param("endIndex") int endIndex,
                                     @Param("userIsNull") Integer userIsNull,
                                     @Param("user") User user);

    /**
     * @description: 客户分页统计
     * @param: [customer]
     * @return: int
     * @auther: YDM
     * @date: 2019/11/20 19:37
     */
    public int countByPage(@Param("customer") Customer customer, @Param("provinceId") Integer provinceId,
                           @Param("cityId") Integer cityId, @Param("userIsNull") Integer userIsNull,@Param("user") User user);

    /**
     * @description: 根据Id查询
     * @param: [id]
     * @return: com.ztkj.market.model.Customer
     * @auther: YDM
     * @date: 2019/11/21 19:32
     */
    public Customer findById(@Param("id") int id);

    /**
     * @description: 添加客户
     * @param: [customer]
     * @return: void
     * @auther: YDM
     * @date: 2019/11/22 10:17
     */
    public void addCus(@Param("customer") Customer customer);

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

    /**
     * @description: 更新客户信息
     * @param: [customer]
     * @return: void
     * @auther: YDM
     * @date: 2019/11/22 16:22
     */
    public void updateCus(@Param("customer") Customer customer);

    /**
     *查询可用客户
     * @param: []
     * @return: com.ztkj.market.model.Customer
     * @author: liss
     * @date: 2019/11/25 14:17
     */

    public List<Customer> findNiceCustomer(@Param("user") User user);

    /*注销，恢复，分配*/
    public void updateStatusUp(@Param("id") int id);
    public void updateStatusDown(@Param("id") int id);
    public void updateUser(@Param("id") int id, @Param("userid") User userid);
    public List<Position> findPosition();
    public List<User> findCheckUser(@Param("positionno") String positionno);
}
