package com.msg.dao;

import com.msg.domain.EmailDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmailDetailsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EmailDetails record);

    EmailDetails selectByPrimaryKey(Long id);

    List<EmailDetails> selectAll(@Param("templateId") Long templateId,
                                 @Param("userIds") List<Long> userIds);

    int updateByPrimaryKey(EmailDetails record);
}