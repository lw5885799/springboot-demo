package com.example.demo.auth;

import com.alibaba.fastjson.JSON;
import com.example.demo.domain.Resp;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author: create by LiWeichen
 * @version: v1.0
 * @description:
 * @date:2019-3-20
 */
public class CustomExpiredSessionStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        Resp error = Resp.error("forced logout: " + event.getSessionInformation().getLastRequest());
        event.getResponse().setContentType("application/json;charset=UTF-8");
        event.getResponse().getWriter().write(JSON.toJSONString(error));
    }
}
