package com.loginByToken.demo.Controller;


import com.alibaba.fastjson.JSONObject;
import com.loginByToken.demo.Annotation.UserLoginToken;
import com.loginByToken.demo.Mapping.UserMapping;
import com.loginByToken.demo.Model.User;
import com.loginByToken.demo.Service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class ApiController {

    @Autowired
    private UserMapping userMapping;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    @ResponseBody
    public Object apiLogin(@RequestParam(value = "username") String username,
                           @RequestParam(value = "password") String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        JSONObject jsonObject=new JSONObject();
        User userForBase = userMapping.findUserUsername(username);
        if (userForBase==null){
            jsonObject.put("message","登录失败,用户不存在");
            return jsonObject;
        }else{
            if (!userForBase.getPassword().equals(user.getPassword())){
                jsonObject.put("message","登录失败,密码错误");
            }else{
                String token = tokenService.getToken(userForBase);
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);
            }
            return jsonObject;
        }
    }

    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }
}
