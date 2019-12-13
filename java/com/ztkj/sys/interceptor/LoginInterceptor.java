package com.ztkj.sys.interceptor;

import com.ztkj.sys.model.Auth;
import com.ztkj.sys.model.User;
import com.ztkj.sys.service.AuthService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**
 * @Description:
 * @Author: HuangKL
 * @Date: 2019/11/26 19:05
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Resource
    private AuthService authService;

    /**
     * @description: 进入Controller之前先判断
     * @param: [request, response, handler]
     * @return: boolean
     * @auther: HuangKL
     * @date: 2019/11/26 19:07
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("loginUser");
        if (user != null) {
            String uri = request.getRequestURI();
            String[] baseHtml = {"/left.html", "/main.html", "/top.html", "/footer.html", "/index.html","stockView.html","deliveryView.html"};
            System.out.println(uri);
            for (String s : baseHtml) {
                if (uri.contains(s)) {
                    return true;
                }
            }
            if (uri.contains(".html")) {
                List<Auth> authList = authService.findById(user.getPositionNo().getNo());
                //假设不可以访问
                boolean flag = false;
                for (Auth auth : authList) {
                    //判断请求路径是否在权限内
                    //System.out.println(uri.substring(uri.indexOf("/", 2),(uri.indexOf(".")-6))+"="+auth.getModuleId().getUrl());
                    //System.out.println("1="+auth.getModuleId().getUrl().contains(uri.substring(uri.indexOf("/", 2),(uri.indexOf(".")-6))));
                    if (auth.getModuleId().getUrl().contains(uri.substring(uri.indexOf("/", 2),(uri.indexOf(".")-6)))) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    //强行访问，清除session并退出
                    request.getSession().invalidate();
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter out = response.getWriter();
                    String html = "<script type='text/javascript'>" +
                            "alert('您没有权限查看，已强制退出！！强行访问3次以上将拉入黑名单！！');" +
                            "window.top.location.href ='" + request.getContextPath() + "/login.html';" +
                            "</script>";
                    out.print(html);
                    out.close();
                    return false;
                }
            }
            return true;
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String html = "<script type='text/javascript'>" +
                "alert('请先登录！');" +
                "window.top.location.href ='" + request.getContextPath() + "/login.html';" +
                "</script>";
        out.print(html);
        out.close();
        return false;
    }
}
