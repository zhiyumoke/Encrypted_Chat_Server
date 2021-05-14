package jilei.springserverdemo.controller;

import com.alibaba.fastjson.JSONObject;
import jilei.springserverdemo.FileTools;
import jilei.springserverdemo.entity.User;
import jilei.springserverdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
public class RegisterController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/Register.do", method = RequestMethod.POST)
    @ResponseBody
    public String Register(@RequestParam Map<String, String> map) {
        System.out.println("注册请求：" + map);
        String username=map.get("username");
        String password=map.get("password");
        Random rand=new Random();
        String imgPath = "E:\\Code\\Images\\User\\"+rand.nextInt(10)+"-man.png";

        User user = new User(username, password, FileTools.ReadFileToBytes(imgPath));
        try {
            userService.InsertUser(user);
            //注册成功
            return "1";
        } catch (Exception e) {
            //注册失败
            return "2";
        }
    }
}
