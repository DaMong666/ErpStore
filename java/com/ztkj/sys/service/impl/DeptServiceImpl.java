package com.ztkj.sys.service.impl;

import com.ztkj.sys.dao.DeptDao;
import com.ztkj.sys.model.Dept;
import com.ztkj.sys.service.DeptService;
import com.ztkj.utils.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.List;

/**
 * @Description:
 * @Author: HuangKL
 * @Date: 2019/11/24 14:33
 */
@Service("deptService")
public class DeptServiceImpl implements DeptService {
    @Resource
    private DeptDao deptDao;

    /**
     * @description: 分页查询
     * @param: [page, dept]
     * @return: com.ztkj.utils.Page
     * @auther: HuangKL
     * @date: 2019/11/24 14:28
     */
    public Page queryByPage(Page page, Dept dept) {
        List<Dept> deptList = deptDao.findByPage(page.getStartIndex(), page.getEndIndex(), dept);
        int rows = deptDao.countPage(dept);
        page.setResult(deptList, rows);
        return page;
    }

    /**
     * @description: 添加部门
     * @param: [dept]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/24 14:29
     */
    @Transactional
    public void addDept(Dept dept) {
        deptDao.add(dept);
    }

    /**
     * @description: 修改信息
     * @param: [dept]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/24 14:30
     */
    @Transactional
    public void updateDept(Dept dept) {
        deptDao.update(dept);
    }

    /**
     * @description: 注销部门
     * @param: [no]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/24 14:30
     */
    @Transactional
    public void cancelDept(int id) {
        DecimalFormat df = new DecimalFormat("00");
        deptDao.cancel(df.format(id));
    }

    /**
     * @description: 恢复部门
     * @param: [no]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/24 14:32
     */
    @Transactional
    public void recoverDept(int id) {
        DecimalFormat df = new DecimalFormat("00");
        deptDao.recover(df.format(id));
    }

    /**
     * @description: 自动生成部门编号
     * @param: []
     * @return: java.lang.String
     * @auther: HuangKL
     * @date: 2019/11/24 14:58
     */
    public String queryDeptNo() {
        DecimalFormat df = new DecimalFormat("00");
        return df.format(deptDao.getNo());
    }

    /**
     * @description: 根据No获取部门
     * @param: [no]
     * @return: com.ztkj.sys.model.Dept
     * @auther: HuangKL
     * @date: 2019/11/25 09:29
     */
    public Dept queryDeptByNo(String no) {
        return deptDao.getByNo(no);
    }
}
