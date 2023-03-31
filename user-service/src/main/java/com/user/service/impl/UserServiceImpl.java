package com.user.service.impl;

import com.common.PageDateInfo;
import com.common.Status;
import com.common.error.BusinessException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.msg.domain.EmailDetails;
import com.msg.service.MsgService;
import com.user.dao.UserInfoMapper;
import com.user.domain.UserInfo;
import com.user.enums.ValidEnum;
import com.user.req.UserInfoReq;
import com.user.req.UserPageReq;
import com.user.response.UserInfoExtra;
import com.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final static Long REGISTER_EMAIL_TEMPLATE_ID = 1000L;

    @Resource
    private UserInfoMapper userInfoMapper;

    /**
     * 邮件服务拆分后可以采用远程调用
     */
    @Resource
    private MsgService msgService;

    @Override
    public void addUser(UserInfoReq userInfoReq) {
        // 校验是否注册
        UserInfo u = userInfoMapper.selectByEmail(userInfoReq.getEmail());
        if (!Objects.isNull(u)) {// 已存在
            throw new BusinessException(Status.PARAM_NOT_EXIST.getCode(),"用户已存在");
        }


        UserInfo userInfo = reqConvertor(userInfoReq);
        int r = userInfoMapper.insert(userInfo);
        if (r > 0) {// 新增成功发送邮件,该步骤可异步s
            Map param = new HashMap();
            param.put("templateId", REGISTER_EMAIL_TEMPLATE_ID);
            param.put("userId", userInfo.getId());
            param.put("userName", userInfo.getUserName());
            param.put("email", userInfo.getEmail());
            msgService.sendEmail(param);

            // 回写邮件发送成功状态
            userInfoMapper.updateSendFlag(userInfo.getId());
        }
    }

    @Override
    public UserInfo getUserById(Long id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public void invalidUserByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            throw new BusinessException(Status.PARAM_ERROR);
        }

        userInfoMapper.invalidByPrimaryKey(ids);
    }

    @Override
    public void updateUser(Long id, UserInfoReq userInfoReq) {
        UserInfo u = userInfoMapper.selectByPrimaryKey(id);
        if (Objects.isNull(u)) {//校验id合法性
            throw new BusinessException(Status.PARAM_NOT_EXIST.getCode(),"用户已存在");
        }

        // 如果有邮箱变更校验是否已经关联了其他人
        UserInfo userInfo = userInfoMapper.selectByEmail(userInfoReq.getEmail());
        if (!Objects.isNull(u) && !userInfo.getId().equals(id)) {
            throw new BusinessException(Status.PARAM_NOT_EXIST.getCode(),"用户已存在");
        }

        UserInfo modify = new UserInfo();
        modify.setId(id);
        modify.setUserName(userInfoReq.getUserName());
        modify.setAge(userInfoReq.getAge());
        modify.setEmail(userInfoReq.getEmail());
        userInfoMapper.updateByPrimaryKey(modify);
    }

    @Override
    public PageDateInfo<UserInfoExtra> listUsers(UserPageReq userPageReq) {
        PageHelper.startPage(userPageReq.getPageNum(), userPageReq.getPageSize());
        List<UserInfo> userInfos = userInfoMapper.selectAll();
        PageInfo pageInfo = new PageInfo(userInfos);


        //1. 将userId提取出来，批量查询已发送的邮件内容
        List<Long> userIds = null;
        if (CollectionUtils.isNotEmpty(userInfos)) {
            userIds = userInfos.stream()
                    .filter(userInfo -> userInfo.getSendFlag().equals(ValidEnum.VALID.getStatus()))
                    .map(UserInfo::getId).collect(Collectors.toList());
        }

        //2. 将已发送邮件内容转化为map结构，方便做数据匹配
        Map<Long, EmailDetails> emailDetailsMap = null;
        if (CollectionUtils.isNotEmpty(userIds)) {
            List<EmailDetails> userEmailDetails = msgService.getEmailDetails(REGISTER_EMAIL_TEMPLATE_ID, userIds);
            emailDetailsMap = userEmailDetails.stream().collect(Collectors.toMap(EmailDetails::getUserId, email -> email));
        }

        //3. 用户列表展示已发送的邮件内容组装
        List<UserInfoExtra> userInfoExtras = new ArrayList<>();
        for (UserInfo userInfo : userInfos) {
            String emailContent = "";
            if (userInfo.getSendFlag().equals(ValidEnum.VALID.getStatus())
                    && MapUtils.isNotEmpty(emailDetailsMap)
                    && emailDetailsMap.containsKey(userInfo.getId())) {
                EmailDetails emailDetails = emailDetailsMap.get(userInfo.getId());
                emailContent = emailDetails.getContent();
            }
            UserInfoExtra userInfoExtra = extraConvertor(userInfo, emailContent);
            userInfoExtras.add(userInfoExtra);
        }

        return PageDateInfo.of(userInfoExtras, pageInfo.getTotal(), pageInfo.getPageNum(), pageInfo.getPageSize());
    }

    private UserInfo reqConvertor(UserInfoReq userInfoReq) {
        UserInfo userInfo = new UserInfo();
        userInfo.setAge(userInfoReq.getAge());
        userInfo.setUserName(userInfoReq.getUserName());
        userInfo.setEmail(userInfoReq.getEmail());
        userInfo.setValidFlag(ValidEnum.VALID.getStatus());
        userInfo.setSendFlag(ValidEnum.INVALID.getStatus());
        return userInfo;
    }

    private UserInfoExtra extraConvertor(UserInfo userInfo, String emailContent) {
        UserInfoExtra userInfoExtra = new UserInfoExtra();
        userInfoExtra.setId(userInfo.getId());
        userInfoExtra.setAge(userInfo.getAge());
        userInfoExtra.setEmail(userInfo.getEmail());
        userInfoExtra.setUserName(userInfo.getUserName());
        userInfoExtra.setValidFlag(ValidEnum.VALID.getStatus());
        userInfoExtra.setSendFlag(ValidEnum.INVALID.getStatus());
        userInfoExtra.setEmailContext(emailContent);
        userInfoExtra.setCreateDate(userInfo.getCreateDate());
        return userInfoExtra;
    }
}
