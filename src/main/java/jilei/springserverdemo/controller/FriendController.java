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
public class FriendController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/QueryFriend.do",method = RequestMethod.POST)
    @ResponseBody
    public String QueryFriend(@RequestParam Map<String, String> map){
        System.out.println("查询好友请求：" + map.toString());
        String username=map.get("username");
        List<User> users=userService.QueryFriends(username);
        return JSON.toJSONString(users);
    }
}
