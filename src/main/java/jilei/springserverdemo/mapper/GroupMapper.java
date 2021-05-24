package jilei.springserverdemo.mapper;

import jilei.springserverdemo.entity.Group;
import jilei.springserverdemo.entity.GroupKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GroupMapper {
    void InsertGroup(Group group);

    void InsertGroupUser(@Param("id") String id, @Param("username") String username);

    void InsertGroupKey(@Param("id") String id, @Param("key") String key, @Param("iv") String iv);

    GroupKey QueryKeyByGroupId(@Param("id") String id);

    List<Group> QueryGroup(String username);
}
