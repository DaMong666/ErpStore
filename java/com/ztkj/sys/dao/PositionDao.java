package com.ztkj.sys.dao;

import com.ztkj.sys.model.Position;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: HuangKL
 * @Date: 2019/11/25 10:33
 */
public interface PositionDao {

    /**
     * @description: 模糊分页查询
     * @param: [startIndex, endIndex, Position]
     * @return: java.util.List<com.ztkj.sys.model.Position>
     * @auther: HuangKL
     * @date: 2019/11/24 13:49
     */
    public List<Position> findByPage(@Param("startIndex") int startIndex,
                                 @Param("endIndex") int endIndex, @Param("position") Position position);

    /**
     * @description: 分页统计
     * @param: [Position]
     * @return: int
     * @auther: HuangKL
     * @date: 2019/11/24 13:49
     */
    public int countPage(@Param("position") Position position);

    /**
     * @description: 添加
     * @param: [Position]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/24 13:58
     */
    public void add(@Param("position") Position position);

    /**
     * @description: 修改
     * @param: [Position]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/24 14:00
     */
    public void update(@Param("position") Position position);

    /**
     * @description: 注销
     * @param: [no]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/25 10:36
     */
    public void cancel(@Param("no") String no);

    /**
     * @description: 恢复
     * @param: [no]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/25 10:36
     */
    public void recover(@Param("no") String no);

    /**
     * @description: 获取职位编号
     * @param: [deptNo]
     * @return: Integer
     * @auther: HuangKL
     * @date: 2019/11/25 10:36
     */
    public Integer getNo(String deptNo);

    /**
     * @description: 根据编号查询
     * @param: [no]
     * @return: com.ztkj.sys.model.Position
     * @auther: HuangKL
     * @date: 2019/11/25 10:35
     */
    public Position getByNo(@Param("no") String no);

    /**
     * @description: 获取上级
     * @param: [deptNo]
     * @return: java.util.List<com.ztkj.sys.model.Position>
     * @auther: HuangKL
     * @date: 2019/11/25 13:51
     */
    public List<Position> findMgr(@Param("deptNo") String deptNo);
    
}
