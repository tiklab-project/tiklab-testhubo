package io.thoughtware.teston.common;

import io.thoughtware.user.user.model.User;
import io.thoughtware.user.user.service.UserService;
import io.thoughtware.eam.common.context.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 公共方法
 */
@Service
public class TestOnUnit {

    @Autowired
    UserService userService;

    /**
     * 获取当前用户的信息
     * @return
     */
    public User getUser(){
        String userId = LoginContext.getLoginId();

        return userService.findUser(userId);
    }


}
