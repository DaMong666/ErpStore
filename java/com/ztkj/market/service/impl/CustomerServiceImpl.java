package com.ztkj.market.service.impl;

import com.ztkj.market.dao.CustomerDao;
import com.ztkj.market.model.Customer;
import com.ztkj.market.service.CustomerService;
import com.ztkj.sys.model.City;
import com.ztkj.sys.model.Position;
import com.ztkj.sys.model.Province;
import com.ztkj.sys.model.User;
import com.ztkj.utils.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 客户
 * @Auther: YDM
 * @Date: 2019/11/20 19:30
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private CustomerDao customerDao;

    /**
     * @description: 客户分页查询
     * @param: [page, customer]
     * @return: com.ztkj.utils.Page
     * @auther: YDM
     * @date: 2019/11/20 19:39
     */
    @Override
    public Page queryByPage(Page page, Customer customer, Integer provinceId, Integer cityId,Integer userIsNull,User user) {
        List<Customer> customerList = customerDao.findByPage(customer, provinceId, cityId, page.getStartIndex(), page.getEndIndex(),userIsNull,user);
        int rowCount = customerDao.countByPage(customer, provinceId, cityId,userIsNull,user);
        page.setResult(customerList, rowCount);
        return page;
    }

    /**
     * @description: 根据Id查询客户
     * @param: [id]
     * @return: com.ztkj.market.model.Customer
     * @auther: YDM
     * @date: 2019/11/21 19:39
     */
    @Override
    public Customer queryById(int id) {
        Customer customer = customerDao.findById(id);
        return customer;
    }

    /**
     * @description: 添加客户
     * @param: [customer]
     * @return: void
     * @auther: YDM
     * @date: 2019/11/22 10:20
     */
    @Override
    @Transactional
    public void addCus(Customer customer) {
        customerDao.addCus(customer);
    }

    /*
     *查询所有省份
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.Province>
     * @auther: huangx
     * @date: 2019/11/20 20:10
     */
    public List<Province> queryAllProvince(){
        return customerDao.findAllProvince();
    }
    /*
     *查询所有的城市
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.City>
     * @auther: huangx
     * @date: 2019/11/21 18:34
     */
    public List<City> queryAllCity(){
        return customerDao.findAllCity();
    }
    /*
     *根据省份id查询所有城市
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.City>
     * @auther: huangx
     * @date: 2019/11/20 20:11
     */
    public List<City> queryAllCityBypId(int provinceId){
        return customerDao.findAllCityBypId(provinceId);
    }

    /**
     * @description: 更新客户信息
     * @param: [customer]
     * @return: void
     * @auther: YDM
     * @date: 2019/11/22 16:37
     */
    @Override
    public void updateCus(Customer customer) {
        customerDao.updateCus(customer);
    }

    /**
     *查询可用客户
     * @param: []
     * @return: java.util.List<com.ztkj.market.model.Customer>
     * @author: liss
     * @date: 2019/11/25 14:18
     */

    public List<Customer> queryNiceCustomer(User user){
        return customerDao.findNiceCustomer(user);
    }

    @Override
    @Transactional
    public void updateStatusUp(int id) {
         customerDao.updateStatusUp(id);
    }

    @Override
    @Transactional
    public void updateStatusDown(int id) {
        customerDao.updateStatusDown(id);
    }

    @Override
    @Transactional
    public void updateUser(int id, User userid) {
        customerDao.updateUser(id,userid);
    }

    @Override
    public List<Position> queryPosition() {
        return customerDao.findPosition();
    }

    @Override
    public List<User> queryCheckUser(String positionno) {
        return customerDao.findCheckUser(positionno);
    }
}
