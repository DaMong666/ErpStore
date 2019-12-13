package com.ztkj.sys.dao;

import com.ztkj.sys.model.Auth;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: HuangKL
 * @Date: 2019/11/25 23:27
 */
public interface AuthDao {

    /**
     * @description: 根据职位No查询
     * @param: [positionNo]
     * @return: java.util.List<com.ztkj.sys.model.Auth>
     * @auther: HuangKL
     * @date: 2019/11/25 23:29
     */
    public List<Auth> findById(@Param("positionNo") String positionNo);

    /**
     * @description: 根据职位No删除
     * @param: [positionNo]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/25 23:39
     */
    public void delete(@Param("positionNo") String positionNo);

    /**
     * @description: 添加
     * @param: [auth]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/25 23:40
     */
    public void add(@Param("positionNo") String positionNo,@Param("auth") Auth auth);

}
