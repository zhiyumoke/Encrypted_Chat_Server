package jilei.springserverdemo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jilei.springserverdemo.entity.User;
import jilei.springserverdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "Login.do", method = RequestMethod.POST)
    @ResponseBody
    public String Login(@RequestParam Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        System.out.println("登录请求：" + map.toString());
        List<User> users = userService.QueryUser(username);
        User user=users.get(0);
        if (user!=null) {
            JSONObject userJson=new JSONObject();
            userJson.put("username",user.getUsername());
            userJson.put("image",user.getImage());
            if (user.getPassword().equals(password)) {
                System.out.println("登录成功");
                return userJson.toJSONString();
            } else {
                System.out.println("登录失败");
                return "1";
            }
        } else {
            System.out.println("用户不存在");
            return "2";
        }

    }
}
