package jilei.springserverdemo.controller;

import com.alibaba.fastjson.JSON;
import jilei.springserverdemo.entity.User;
import jilei.springserverdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class FriendController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/QueryUser.do", method = RequestMethod.POST)
    @ResponseBody
    public String QueryUser(@RequestParam Map<String, String> map) {
        String username = map.get("username");
        List<User> userList = userService.QueryUser(username);
        User user = userList.get(0);
        if (user != null) {
            return JSON.toJSONString(user);
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/QueryFriend.do", method = RequestMethod.POST)
    @ResponseBody
    public String QueryFriend(@RequestParam Map<String, String> map) {
        System.out.println("查询好友请求：" + map.toString());
        String username = map.get("username");
        List<User> users = userService.QueryFriends(username);
        return JSON.toJSONString(users);
    }

    @RequestMapping(value = "/CreateFriend.do", method = RequestMethod.POST)
    @ResponseBody
    public String CreateFriend(@RequestParam Map<String, String> map) {
        System.out.println("添加好友请求：" + map.toString());
        String myUsername = map.get("myUsername");
        String hisUsername = map.get("hisUsername");
        try {
            userService.InsertFriend(myUsername, hisUsername);
            return "1";
        } catch (Exception e) {
            return "0";
        }
    }
}
