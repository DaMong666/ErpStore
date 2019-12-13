package com.ztkj.sys.service.impl;

import com.ztkj.sys.dao.PositionDao;
import com.ztkj.sys.model.Position;
import com.ztkj.sys.service.PositionService;
import com.ztkj.utils.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.List;

/**
 * @Description:
 * @Author: HuangKL
 * @Date: 2019/11/25 11:42
 */
@Service("positionService")
public class PositionServiceImpl implements PositionService {
    @Resource
    private PositionDao positionDao;

    /**
     * @description: 分页查询
     * @param: [page, position]
     * @return: com.ztkj.utils.Page
     * @auther: HuangKL
     * @date: 2019/11/24 14:28
     */
    public Page queryByPage(Page page, Position position) {
        List<Position> positionList = positionDao.findByPage(page.getStartIndex(), page.getEndIndex(), position);
        int rows = positionDao.countPage(position);
        page.setResult(positionList, rows);
        return page;
    }

    /**
     * @description: 添加部门
     * @param: [position]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/24 14:29
     */
    @Transactional
    public void addPosition(Position position) {
        positionDao.add(position);
    }

    /**
     * @description: 修改信息
     * @param: [position]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/24 14:30
     */
    @Transactional
    public void updatePosition(Position position) {
        positionDao.update(position);
    }

    /**
     * @description: 注销部门
     * @param: [no]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/24 14:30
     */
    @Transactional
    public void cancelPosition(int no) {
        System.out.println("1w"+no);
        DecimalFormat df = new DecimalFormat("00000");
        positionDao.cancel(df.format(no));
    }

    /**
     * @description: 恢复部门
     * @param: [no]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/24 14:32
     */
    @Transactional
    public void recoverPosition(int no) {
        System.out.println("1w"+no);
        DecimalFormat df = new DecimalFormat("00000");
        positionDao.recover(df.format(no));
    }

    /**
     * @description: 自动生成部门编号
     * @param: []
     * @return: java.lang.String
     * @auther: HuangKL
     * @date: 2019/11/24 14:58
     */
    public String queryPositionNo(String deptNo) {
        DecimalFormat df = new DecimalFormat("00000");
        Integer no = positionDao.getNo(deptNo);
        if (no == null) {
            no = Integer.valueOf(deptNo + "001");
        }
        return df.format(no);
    }

    /**
     * @description: 根据No获取部门
     * @param: [no]
     * @return: com.ztkj.sys.model.Position
     * @auther: HuangKL
     * @date: 2019/11/25 09:29
     */
    public Position queryPositionByNo(String no) {
        return positionDao.getByNo(no);
    }

    /**
     * @description: 查询上级
     * @param: [deptNo, no]
     * @return: java.util.List<com.ztkj.sys.model.Position>
     * @auther: HuangKL
     * @date: 2019/11/25 13:57
     */
    public List<Position> queryMgr(String deptNo, String no) {
        return positionDao.findMgr(deptNo);
    }
}
