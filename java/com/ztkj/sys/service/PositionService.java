package com.ztkj.sys.service;

import com.ztkj.sys.model.Position;
import com.ztkj.utils.Page;

import java.util.List;

/**
 * @Description:
 * @Author: HuangKL
 * @Date: 2019/11/25 11:39
 */
public interface PositionService {

    /**
     * @description: 分页查询
     * @param: [page, position]
     * @return: com.ztkj.utils.Page
     * @auther: HuangKL
     * @date: 2019/11/24 14:28
     */
    public Page queryByPage(Page page, Position position);

    /**
     * @description: 添加部门
     * @param: [position]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/24 14:29
     */
    public void addPosition(Position position);

    /**
     * @description: 修改信息
     * @param: [position]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/24 14:30
     */
    public void updatePosition(Position position);

    /**
     * @description: 注销部门
     * @param: [no]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/24 14:30
     */
    public void cancelPosition(int no);

    /**
     * @description: 恢复部门
     * @param: [no]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/24 14:32
     */
    public void recoverPosition(int no);

    /**
     * @description: 自动生成部门编号
     * @param: []
     * @return: java.lang.String
     * @auther: HuangKL
     * @date: 2019/11/24 14:58
     */
    public String queryPositionNo(String deptNo);

    /**
     * @description: 根据No获取部门
     * @param: [no]
     * @return: com.ztkj.sys.model.Position
     * @auther: HuangKL
     * @date: 2019/11/25 09:29
     */
    public Position queryPositionByNo(String no);

    /**
     * @description: 查询上级
     * @param: [deptNo, no]
     * @return: java.util.List<com.ztkj.sys.model.Position>
     * @auther: HuangKL
     * @date: 2019/11/25 13:57
     */
    public List<Position> queryMgr(String deptNo, String no);

}
