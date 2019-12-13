package com.ztkj.sys.dao;

import com.ztkj.sys.model.Log;
import com.ztkj.sys.param.LogParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: HuangKL
 * @Date: 2019/11/26 14:28
 */
public interface LogDao {

    /**
     * @description: 模糊分页查询
     * @param: [startIndex, endIndex, logParam]
     * @return: java.util.List<com.ztkj.sys.model.Log>
     * @auther: HuangKL
     * @date: 2019/11/26 15:05
     */
    public List<Log> findByPage(@Param("startIndex") int startIndex,
                                @Param("endIndex") int endIndex, @Param("logParam") LogParam logParam);

    /**
     * @description: 分页统计
     * @param: [log]
     * @return: int
     * @auther: HuangKL
     * @date: 2019/11/26 15:05
     */
    public int countPage(@Param("logParam") LogParam logParam);

    /**
     * @description: 添加
     * @param: [log]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/26 15:06
     */
    public void add(@Param("log") Log log);

    /**
     * @description: 根据Id获取
     * @param: [id]
     * @return: com.ztkj.sys.model.Log
     * @auther: HuangKL
     * @date: 2019/11/26 17:16
     */
    public Log getById(@Param("id") int id);

}
