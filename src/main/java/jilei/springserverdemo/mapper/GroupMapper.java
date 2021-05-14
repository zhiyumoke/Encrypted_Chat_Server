package jilei.springserverdemo.mapper;

import jilei.springserverdemo.entity.Group;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GroupMapper {
    void InsertGroup(Group group);
    void InsertGroupUser(@Param("id") String id, @Param("username") String username);
    List<Group> QueryGroup(String username);
}
