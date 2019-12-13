package com.ztkj.sys.dao;

import com.ztkj.sys.model.City;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: HuangKL
 * @Date: 2019/11/22 17:21
 */
public interface AreaDao {

    /**
     * @description: 模糊分页查询
     * @param: [startIndex, endIndex, user]
     * @return: java.util.List<com.ztkj.sys.model.User>
     * @auther: HuangKL
     * @date: 2019/11/21 17:44
     */
    public List<City> findByPage(@Param("startIndex") int startIndex,
                                 @Param("endIndex") int endIndex, @Param("city") City city);

    /**
     * @description: 模糊分页统计
     * @param: [user]
     * @return: int
     * @auther: HuangKL
     * @date: 2019/11/21 17:48
     */
    public int countPage(@Param("city") City city);

}
