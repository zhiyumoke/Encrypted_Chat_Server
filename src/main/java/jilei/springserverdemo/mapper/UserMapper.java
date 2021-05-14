package jilei.springserverdemo.mapper;

import jilei.springserverdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    List<User> QueryUser(String username);
    void InsertUser(User user);
    List<User> QueryFriends(String username);
}
