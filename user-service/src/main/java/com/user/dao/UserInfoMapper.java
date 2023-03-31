package com.user.dao;

import com.user.domain.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoMapper {
    int invalidByPrimaryKey(@Param("ids") List<Long> ids);

    int insert(UserInfo record);

    UserInfo selectByPrimaryKey(Long id);

    UserInfo selectByEmail(@Param("email") String email);

    List<UserInfo> selectAll();

    int updateByPrimaryKey(UserInfo record);

    int updateSendFlag(@Param("id") Long id);
}