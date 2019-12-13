package com.ztkj.sys.service;

import com.ztkj.sys.model.Log;
import com.ztkj.sys.param.LogParam;
import com.ztkj.utils.Page;

/**
 * @Description:
 * @Author: HuangKL
 * @Date: 2019/11/26 14:28
 */
public interface LogService {

    /**
     * @description: 分页查询
     * @param: [page, logParam]
     * @return: com.ztkj.utils.Page
     * @auther: HuangKL
     * @date: 2019/11/26 15:07
     */
    public Page queryByPage(Page page, LogParam logParam);

    /**
     * @description: 添加日志
     * @param: [log]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/26 15:07
     */
    public void addLog(Log log);

    /**
     * @description: 根据Id查询单个
     * @param: [id]
     * @return: com.ztkj.sys.model.Log
     * @auther: HuangKL
     * @date: 2019/11/26 17:19
     */
    public Log queryById(int id);

}
