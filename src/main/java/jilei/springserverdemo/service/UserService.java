package jilei.springserverdemo.service;

import jilei.springserverdemo.entity.User;
import jilei.springserverdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public List<User> QueryUser(String username) {
        return userMapper.QueryUser(username);
    }

    public void InsertUser(User user) {
        userMapper.InsertUser(user);
    }

    public List<User> QueryFriends(String username) {
        return userMapper.QueryFriends(username);
    }

    public void InsertFriend(String myUsername, String hisUsername) {
        userMapper.InsertFriend(myUsername, hisUsername);
        userMapper.InsertFriend(hisUsername, myUsername);
    }
}
