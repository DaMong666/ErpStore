package com.ztkj.sys.service.impl;

import com.ztkj.sys.dao.LogDao;
import com.ztkj.sys.model.Log;
import com.ztkj.sys.param.LogParam;
import com.ztkj.sys.service.LogService;
import com.ztkj.utils.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: HuangKL
 * @Date: 2019/11/26 17:06
 */
@Service("logService")
public class LogServiceImpl implements LogService {
    @Resource
    private LogDao logDao;

    /**
     * @description: 分页查询
     * @param: [page, logParam]
     * @return: com.ztkj.utils.Page
     * @auther: HuangKL
     * @date: 2019/11/26 15:07
     */
    public Page queryByPage(Page page, LogParam logParam) {
        List<Log> logList = logDao.findByPage(page.getStartIndex(), page.getEndIndex(), logParam);
        int rows = logDao.countPage(logParam);
        page.setResult(logList, rows);
        return page;
    }

    /**
     * @description: 添加日志
     * @param: [log]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/26 15:07
     */
    @Transactional
    public void addLog(Log log) {
        logDao.add(log);
    }

    /**
     * @description: 根据Id查询单个
     * @param: [id]
     * @return: com.ztkj.sys.model.Log
     * @auther: HuangKL
     * @date: 2019/11/26 17:19
     */
    public Log queryById(int id) {
        return logDao.getById(id);
    }
}
