package com.linln.api.swagger;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.linln.component.jwt.annotation.JwtPermissions;

/**
 * @author pengzizi
 * @date 2018/12/9
 */
@Controller
public class SwaggerController {

    @GetMapping("/api/swagger")
    @RequiresPermissions("/api/swagger")
    public String index(){
        return "redirect:/swagger-ui.html";
    }
    
    
    @GetMapping("/api/test")
    public String test(){
    	System.out.println("执行咯test");
        return "redirect:/swagger-ui.html";
    }
    
    
    @GetMapping("/api/login")
    @JwtPermissions
    public String login(){
    	System.out.println("执行咯login");
        return "redirect:/swagger-ui.html";
    }
}
