package org.myfun.tms_server.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.map.MapUtil;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.myfun.tms_server.common.lang.Const;
import org.myfun.tms_server.common.lang.Result;
import org.myfun.tms_server.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;


@Slf4j
@RestController
@Api(tags="验证码接口",value = "登录LoginController")
public class AuthController extends BaseController{
    @Autowired
    private Producer producer;

    /**
     * 图片验证码
     */
    @ApiOperation("获取验证码图片接口")
    @GetMapping("/captcha")
    public Result captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = producer.createText();
        String key = UUID.randomUUID().toString();
        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        BASE64Encoder encoder = new BASE64Encoder();
        String str = "data:image/jpeg;base64,";
        String base64Img = str + encoder.encode(outputStream.toByteArray());

        // 存储到redis中
        redisUtil.hset(Const.CAPTCHA_KEY, key, code, 120);
        log.info("验证码 -- {} - {}", key, code);
        return Result.ok("操作成功",
                MapUtil.builder()
                        .put("token", key)
                        .put("captchaImg", base64Img)
                        .build()
        );
    }

    /**
     * 获取用户信息接口
     * @param principal
     * @return
     */
    @GetMapping("/sys/userInfo")
    public Result userInfo(Principal principal) {

        SysUser sysUser = sysUserService.getByUsername(principal.getName());

        return Result.ok("操作成功",MapUtil.builder()
                .put("id", sysUser.getId())
                .put("username", sysUser.getUsername())
                .put("avatar", sysUser.getAvatar())
                .put("created", sysUser.getCreated())
                .map()
        );
    }
}
