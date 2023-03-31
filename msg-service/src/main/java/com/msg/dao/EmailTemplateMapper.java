package com.msg.dao;

import com.msg.domain.EmailTemplate;

import java.util.List;

public interface EmailTemplateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EmailTemplate record);

    EmailTemplate selectByPrimaryKey(Long id);

    List<EmailTemplate> selectAll();

    int updateByPrimaryKey(EmailTemplate record);
}