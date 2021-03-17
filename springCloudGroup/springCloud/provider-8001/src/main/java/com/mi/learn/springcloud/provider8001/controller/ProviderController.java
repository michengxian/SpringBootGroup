package com.mi.learn.springcloud.provider8001.controller;

import com.alibaba.fastjson.JSONObject;
import com.mi.learn.springcloud.base.bean.RequestBean;
import com.mi.learn.springcloud.base.bean.ResponseBean;
import com.mi.learn.springcloud.bean.UserInfoBean;
import com.mi.learn.springcloud.provider8001.server.ProviderServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    ProviderServer providerServer;

    @RequestMapping(value = "/queryUserById",method = RequestMethod.GET)
    public ResponseBean<UserInfoBean> queryUserById(RequestBean<String> requestBean, ResponseBean<UserInfoBean> responseBean){
        log.info("ProviderController queryUserById req : {}",JSONObject.toJSON(requestBean));
        UserInfoBean userInfoBean = providerServer.queryUserById(Long.valueOf(requestBean.getRequest()));
        log.info("ProviderController queryUserById res : {}", JSONObject.toJSON(userInfoBean));
        responseBean.setResponse(userInfoBean);
        return responseBean;
    }



    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public ResponseBean<Boolean> addUser(@RequestBody RequestBean<UserInfoBean> requestBean, ResponseBean<Boolean> responseBean){
        log.info("ProviderController addUser req : {}",JSONObject.toJSON(requestBean));
        Boolean flag = providerServer.addUser(requestBean.getRequest());
        log.info("ProviderController addUser res : {}", JSONObject.toJSON(flag));
        responseBean.setResponse(flag);
        return responseBean;
    }
}