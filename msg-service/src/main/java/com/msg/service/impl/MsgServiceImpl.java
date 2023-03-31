package com.msg.service.impl;

import com.msg.common.SendMail;
import com.msg.common.StringFormatUtil;
import com.msg.dao.EmailDetailsMapper;
import com.msg.dao.EmailTemplateMapper;
import com.msg.domain.EmailDetails;
import com.msg.domain.EmailTemplate;
import com.msg.service.MsgService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class MsgServiceImpl implements MsgService {

    @Resource
    private EmailTemplateMapper emailTemplateMapper;

    @Resource
    private EmailDetailsMapper emailDetailsMapper;

    @Resource
    private SendMail sendMail;

    @Override
    public void addTemplate(EmailTemplate emailTemplate) {
        emailTemplateMapper.insert(emailTemplate);
    }

    @Override
    public void sendEmail(Map param) {
        Long templateId = MapUtils.getLong(param, "templateId");
        Long userId = MapUtils.getLong(param, "userId");
        String email = MapUtils.getString(param, "email");

        if (templateId == null
                || userId == null
                || StringUtils.isEmpty(email)) {
            throw new RuntimeException("模版ID或者用户ID为空.");
        }

        // 将邮件模版中的变量替换为变化属性值
        EmailTemplate template = emailTemplateMapper.selectByPrimaryKey(templateId);
        if (Objects.isNull(template)) {
            throw new RuntimeException("模版ID不存在.");
        }

        // 邮件发送
        String emailContent = StringFormatUtil.format(template.getContent(), param);
        sendMail.sendEmail(email, template.getSubject(), emailContent);

        // 记录发送结果
        EmailDetails record = new EmailDetails();
        record.setContent(emailContent);
        record.setTemplateId(templateId);
        record.setUserId(userId);
        emailDetailsMapper.insert(record);
    }

    @Override
    public List<EmailDetails> getEmailDetails(Long templateId, List<Long> userIds) {
        return emailDetailsMapper.selectAll(templateId, userIds);
    }
}
