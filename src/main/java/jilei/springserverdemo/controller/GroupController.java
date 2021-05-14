package jilei.springserverdemo.controller;

import com.alibaba.fastjson.JSON;
import jilei.springserverdemo.FileTools;
import jilei.springserverdemo.entity.Group;
import jilei.springserverdemo.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class GroupController {
    @Autowired
    GroupService groupService;

    @RequestMapping(value = "/InsertGroup.do", method = RequestMethod.POST)
    @ResponseBody
    public String InsertGroup(@RequestParam Map<String, String> map) {
        System.out.println("添加群组请求：" + map.toString());
        String groupname = map.get("groupname");
        String imgPath = "E:\\Code\\Images\\群聊.png";
        String uuid = UUID.randomUUID().toString();
        Group group = new Group(uuid, groupname, FileTools.ReadFileToBytes(imgPath));
        System.out.println(group);
        try {
            groupService.InsertGroup(group);
            //添加群组成功
            return "1";
        } catch (Exception e) {
            //添加失败
            return "0";
        }
    }

    @RequestMapping(value = "/CreateGroup.do", method = RequestMethod.POST)
    @ResponseBody
    public String CreateGroup(@RequestParam Map<String, String> map) {
        System.out.println("创建群组请求：" + map.toString());
        String groupname = map.get("groupName");
        List<String> usernames = JSON.parseArray(map.get("groupMembers"), String.class);

        String imgPath = "E:\\Code\\Images\\群聊.png";
        String uuid = UUID.randomUUID().toString();
        Group group = new Group(uuid, groupname, FileTools.ReadFileToBytes(imgPath));

        try {
            groupService.InsertGroup(group);
            for (String username : usernames) {
                groupService.InsertGroupUser(uuid, username);
            }
            //添加群组成功
            return "1";
        } catch (Exception e) {
            //添加失败
            return "0";
        }

    }

    @RequestMapping(value = "/QueryGroup.do", method = RequestMethod.POST)
    @ResponseBody
    public String QueryGroup(@RequestParam Map<String, String> map) {
        System.out.println("查询群组请求：" + map.toString());
        String username = map.get("username");
        List<Group> groups = groupService.QueryGroup(username);
        return JSON.toJSONString(groups);
    }
}
