package com.msg.service;

import com.msg.domain.EmailDetails;
import com.msg.domain.EmailTemplate;

import java.util.List;
import java.util.Map;

public interface MsgService {
    void addTemplate(EmailTemplate emailTemplate);

    /**
     * 此处采用map，可以灵活匹配模版中的动态内容
     * @param param
     */
    void sendEmail(Map param);

    List<EmailDetails> getEmailDetails(Long templateId, List<Long> userIds);
}
