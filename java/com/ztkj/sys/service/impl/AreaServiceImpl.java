package com.ztkj.sys.service.impl;

import com.ztkj.sys.dao.AreaDao;
import com.ztkj.sys.model.City;
import com.ztkj.sys.service.AreaService;
import com.ztkj.utils.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: HuangKL
 * @Date: 2019/11/24 10:48
 */
@Service("areaService")
public class AreaServiceImpl implements AreaService {
    @Resource
    private AreaDao areaDao;

    /**
     * @description: 分页模糊查询
     * @param: [page, city]
     * @return: com.ztkj.utils.Page
     * @auther: HuangKL
     * @date: 2019/11/24 10:47
     */
    public Page queryByPage(Page page, City city) {
        List<City> cityList = areaDao.findByPage(page.getStartIndex(), page.getEndIndex(), city);
        int rowCount = areaDao.countPage(city);
        page.setResult(cityList,rowCount);
        return page;
    }
}
