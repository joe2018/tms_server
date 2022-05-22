package org.myfun.tms_server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.myfun.tms_server.common.lang.Result;
import org.myfun.tms_server.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  登录控制器
 * </p>
 *
 * 对象属性         @ApiModelProperty     用在参数对象的字段上
 * 协议集描述       @Api                  用在Conntroller类上
 * 协议描述         @ApiOperation         用在controller方法上
 * Response集      @ApiResponses         用在controller方法上
 * Response        @ApiResponse          用在@ApiResponses里面
 * 非对象参数集      @ApilmplicitParams    用在controller方法上
 * @author Joe
 * @since 2022-05-21
 */
@RestController
@Api(tags="登录相关接口",value = "登录LoginController")
public class LoginController extends BaseController {

    @Autowired
    SysUserService sysUserService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @ApiOperation("测试获取用户信息")
    @GetMapping("/test")
    public Result test(){
        return Result.ok("操作成功",sysUserService.list());
    }

    // 普通用户、超级管理员
    @PreAuthorize("hasAuthority('sys:user:list')")
    @GetMapping("/pass")
    public Result pass() {

        // 加密后密码
        String password = bCryptPasswordEncoder.encode("123456");

        boolean matches = bCryptPasswordEncoder.matches("123456", password);

        System.out.println("匹配结果：" + matches);

        return Result.ok("生成成功",password);
    }


}
