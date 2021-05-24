package jilei.springserverdemo.service;

import jilei.springserverdemo.entity.Group;
import jilei.springserverdemo.entity.GroupKey;
import jilei.springserverdemo.mapper.GroupMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    GroupMapper groupMapper;

    public void InsertGroup(Group group) {
        groupMapper.InsertGroup(group);
    }

    public void InsertGroupUser(String id, String username) {
        groupMapper.InsertGroupUser(id, username);
    }

    public void InsertGroupKey(String id, String key, String iv) {
        groupMapper.InsertGroupKey(id, key, iv);
    }

    public GroupKey QueryKeyByGroupId(@Param("id") String id) {
        return groupMapper.QueryKeyByGroupId(id);
    }

    public List<Group> QueryGroup(String username) {
        return groupMapper.QueryGroup(username);
    }

}
