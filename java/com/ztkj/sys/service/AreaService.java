package com.ztkj.sys.service;

import com.ztkj.sys.model.City;
import com.ztkj.utils.Page;

/**
 * @Description:
 * @Author: HuangKL
 * @Date: 2019/11/24 10:46
 */
public interface AreaService {

    /**
     * @description: 分页模糊查询
     * @param: [page, city]
     * @return: com.ztkj.utils.Page
     * @auther: HuangKL
     * @date: 2019/11/24 10:47
     */
    public Page queryByPage(Page page, City city);

}
