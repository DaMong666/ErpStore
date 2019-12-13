package com.ztkj.sys.interceptor;

import com.ztkj.sys.model.Log;
import com.ztkj.sys.model.User;
import com.ztkj.sys.service.LogService;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: HuangKL
 * @Date: 2019/11/27 16:00
 */
@Component
public class LogInterceptor extends HandlerInterceptorAdapter {
    @Resource
    private LogService logService;

    /**
     * @description: 访问Controller之前添加日志
     * @param: [request, response, handler]
     * @return: boolean
     * @auther: HuangKL
     * @date: 2019/11/27 16:07
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler.getClass() != HandlerMethod.class) {
            return true;
        }
        HandlerMethod hm = (HandlerMethod) handler;
//        System.out.println(request.getRequestURI());
//        System.out.println("Controller类名：" + hm.getBeanType());//class com.ztkj.sys.controller.UserController
//        System.out.println("Controller类调用的方法名：" + hm.getMethod().getName());//queryDept
        String className = hm.getBeanType().toString();
        User user = (User) request.getSession().getAttribute("loginUser");
        StringBuffer content = new StringBuffer("进行了");
        String methodName = hm.getMethod().getName();
        if (className.contains("UserController")) {
            String moduleName = "用户管理";
            if (methodName.contains("add")) {
                addLog(user,moduleName,content.append("添加用户"));
            } else if (methodName.contains("update")) {
                addLog(user,moduleName,content.append("修改用户"));
            } else if (methodName.contains("cancel")) {
                addLog(user,moduleName,content.append("注销用户"));
            } else if (methodName.contains("recover")) {
                addLog(user,moduleName,content.append("恢复用户"));
            }
        } else if (className.contains("DeptController")) {
            String moduleName = "部门管理";
            if (methodName.contains("add")) {
                addLog(user,moduleName,content.append("添加部门"));
            } else if (methodName.contains("update")) {
                addLog(user,moduleName,content.append("修改部门"));
            } else if (methodName.contains("cancel")) {
                addLog(user,moduleName,content.append("注销部门"));
            } else if (methodName.contains("recover")) {
                addLog(user,moduleName,content.append("恢复部门"));
            }
        } else if (className.contains("PositionController")) {
            String moduleName = "职位管理";
            if (methodName.contains("add")) {
                addLog(user,moduleName,content.append("添加职位"));
            } else if (methodName.contains("update")) {
                addLog(user,moduleName,content.append("修改职位"));
            } else if (methodName.contains("cancel")) {
                addLog(user,moduleName,content.append("注销职位"));
            } else if (methodName.contains("recover")) {
                addLog(user,moduleName,content.append("恢复职位"));
            }
        } else if (className.contains("AuthController")) {
            String moduleName = "职位管理";
            if (methodName.contains("update")) {
                addLog(user,moduleName,content.append("职位赋权"));
            }
        } else if (className.contains("ModuleController")) {
            String moduleName = "模块管理";
            if (methodName.contains("add")) {
                addLog(user,moduleName,content.append("添加模块"));
            } else if (methodName.contains("update")) {
                addLog(user,moduleName,content.append("修改模块"));
            } else if (methodName.contains("cancel")) {
                addLog(user,moduleName,content.append("注销模块"));
            } else if (methodName.contains("recover")) {
                addLog(user,moduleName,content.append("恢复模块"));
            }
        } else if (className.contains("CustomerController")) {
            String moduleName = "客户管理";
            if (methodName.contains("add")) {
                addLog(user,moduleName,content.append("添加客户"));
            } else if (methodName.contains("update")) {
                addLog(user,moduleName,content.append("修改客户"));
            } else if (methodName.contains("cancel")) {
                addLog(user,moduleName,content.append("注销客户"));
            } else if (methodName.contains("recover")) {
                addLog(user,moduleName,content.append("恢复客户"));
            }
        } else if (className.contains("OrderController")) {
            String moduleName = "订单管理";
            if (methodName.contains("add")) {
                addLog(user,moduleName,content.append("添加订单"));
            } else if (methodName.contains("update")) {
                addLog(user,moduleName,content.append("修改订单"));
            } else if (methodName.contains("cancel")) {
                addLog(user,moduleName,content.append("注销订单"));
            } else if (methodName.contains("recover")) {
                addLog(user,moduleName,content.append("恢复订单"));
            } else if (methodName.contains("del")) {
                addLog(user,moduleName,content.append("删除订单"));
            }
        } else if (className.contains("BrandController")) {
            String moduleName = "品牌管理";
            if (methodName.contains("add")) {
                addLog(user,moduleName,content.append("添加品牌"));
            } else if (methodName.contains("update")) {
                addLog(user,moduleName,content.append("修改品牌"));
            } else if (methodName.contains("cancel")) {
                addLog(user,moduleName,content.append("注销品牌"));
            } else if (methodName.contains("recover")) {
                addLog(user,moduleName,content.append("恢复品牌"));
            } else if (methodName.contains("del")) {
                addLog(user,moduleName,content.append("删除品牌"));
            }
        } else if (className.contains("GoodsController")) {
            String moduleName = "商品管理";
            if (methodName.contains("add")) {
                addLog(user,moduleName,content.append("添加商品"));
            } else if (methodName.contains("update")) {
                addLog(user,moduleName,content.append("修改商品"));
            } else if (methodName.contains("cancel")) {
                addLog(user,moduleName,content.append("注销商品"));
            } else if (methodName.contains("recover")) {
                addLog(user,moduleName,content.append("恢复商品"));
            } else if (methodName.contains("del")) {
                addLog(user,moduleName,content.append("删除商品"));
            }
        } else if (className.contains("GoodsTypeController")) {
            String moduleName = "商品类型管理";
            if (methodName.contains("add")) {
                addLog(user,moduleName,content.append("添加商品类型"));
            } else if (methodName.contains("update")) {
                addLog(user,moduleName,content.append("修改商品类型"));
            } else if (methodName.contains("cancel")) {
                addLog(user,moduleName,content.append("注销商品类型"));
            } else if (methodName.contains("recover")) {
                addLog(user,moduleName,content.append("恢复商品类型"));
            } else if (methodName.contains("del")) {
                addLog(user,moduleName,content.append("删除商品类型"));
            }
        } else if (className.contains("PurchaseController")) {
            String moduleName = "采购单管理";
            if (methodName.contains("add")) {
                addLog(user,moduleName,content.append("添加采购单"));
            } else if (methodName.contains("update")) {
                addLog(user,moduleName,content.append("修改采购单"));
            } else if (methodName.contains("cancel")) {
                addLog(user,moduleName,content.append("注销采购单"));
            } else if (methodName.contains("recover")) {
                addLog(user,moduleName,content.append("恢复采购单"));
            } else if (methodName.contains("del")) {
                addLog(user,moduleName,content.append("删除采购单"));
            }
        } else if (className.contains("VendorController")) {
            String moduleName = "厂商管理";
            if (methodName.contains("add")) {
                addLog(user,moduleName,content.append("添加厂商"));
            } else if (methodName.contains("update")) {
                addLog(user,moduleName,content.append("修改厂商"));
            } else if (methodName.contains("cancel")) {
                addLog(user,moduleName,content.append("注销厂商"));
            } else if (methodName.contains("recover")) {
                addLog(user,moduleName,content.append("恢复厂商"));
            } else if (methodName.contains("del")) {
                addLog(user,moduleName,content.append("删除厂商"));
            }
        } else if (className.contains("OutStorageController")) {
            String moduleName = "出库管理";
            if (methodName.contains("add")) {
                addLog(user,moduleName,content.append("添加出库"));
            } else if (methodName.contains("update")) {
                addLog(user,moduleName,content.append("修改出库"));
            } else if (methodName.contains("cancel")) {
                addLog(user,moduleName,content.append("注销出库"));
            } else if (methodName.contains("recover")) {
                addLog(user,moduleName,content.append("恢复出库"));
            } else if (methodName.contains("del")) {
                addLog(user,moduleName,content.append("删除出库"));
            }
        } else if (className.contains("StorageController")) {
            String moduleName = "仓库管理";
            if (methodName.contains("add")) {
                addLog(user,moduleName,content.append("添加仓库"));
            } else if (methodName.contains("update")) {
                addLog(user,moduleName,content.append("修改仓库"));
            } else if (methodName.contains("cancel")) {
                addLog(user,moduleName,content.append("注销仓库"));
            } else if (methodName.contains("recover")) {
                addLog(user,moduleName,content.append("恢复仓库"));
            } else if (methodName.contains("del")) {
                addLog(user,moduleName,content.append("删除仓库"));
            }
        } else if (className.contains("InStorageController")) {
            String moduleName = "入库管理";
            if (methodName.contains("add")) {
                addLog(user,moduleName,content.append("添加入库"));
            } else if (methodName.contains("update")) {
                addLog(user,moduleName,content.append("修改入库"));
            } else if (methodName.contains("cancel")) {
                addLog(user,moduleName,content.append("注销入库"));
            } else if (methodName.contains("recover")) {
                addLog(user,moduleName,content.append("恢复入库"));
            } else if (methodName.contains("del")) {
                addLog(user,moduleName,content.append("删除入库"));
            }
        }
        return true;
    }

    /**
     * @description: 添加日志
     * @param: []
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/27 19:08
     */
    public void addLog(User user,String moduleName,StringBuffer content) {
        System.out.println("=========+++================+++=============+++=============");
        Log log = new Log(user,moduleName,content.append("的操作").toString());
        logService.addLog(log);
    }

}
