package com.frozen.fimserver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Function:
 *
 * @author frozen
 * Date: 2018/12/26 11:15
 * @since JDK 1.8
 */
@Service
public class MsgHandler{
    private final static Logger LOGGER = LoggerFactory.getLogger(MsgHandler.class);

    private boolean aiModel = false;
    @Autowired
    private InnerCommandContext innerCommandContext ;

    public void sendMsg(String msg) {
        if (aiModel) {
            aiChat(msg);
        } else {
            normalChat(msg);
        }
    }

    /**
     * 正常聊天
     *
     * @param msg
     */
    private void normalChat(String msg) {
        String[] totalMsg = msg.split(";;");
        if (totalMsg.length > 1) {
            //私聊
            try {
                //p2pChat(p2PReqVO);
            } catch (Exception e) {
                LOGGER.error("Exception", e);
            }

        } else {
            //群聊
            try {
               // groupChat(groupReqVO);
            } catch (Exception e) {
                LOGGER.error("Exception", e);
            }
        }
    }

    /**
     * AI model
     *
     * @param msg
     */
    private void aiChat(String msg) {
        msg = msg.replace("吗", "");
        msg = msg.replace("嘛", "");
        msg = msg.replace("?", "!");
        msg = msg.replace("？", "!");
        msg = msg.replace("你", "我");
        System.out.println("AI:\033[31;4m" + msg + "\033[0m");
    }


    public boolean checkMsg(String msg) {
        if (StringUtils.isEmpty(msg)) {
            LOGGER.warn("不能发送空消息！");
            return true;
        }
        return false;
    }


    public boolean innerCommand(String msg) {

        if (msg.startsWith(":")) {
            InnerCommand instance = innerCommandContext.getInstance(msg);
            instance.process(msg) ;

            return true;

        } else {
            return false;
        }


    }


    /**
     * 模糊匹配
     *
     * @param msg
     */
    private void prefixSearch(String msg) {
        try {
            String[] split = msg.split(" ");
            String key = split[1];
            List<String> list = new ArrayList<>();

            for (String res : list) {
                res = res.replace(key, "\033[31;4m" + key + "\033[0m");
                System.out.println(res);
            }

        } catch (Exception e) {
            LOGGER.error("Exception", e);
        }
    }

    /**
     * 查询聊天记录
     *
     * @param msg
     */
    private void queryChatHistory(String msg) {
        String[] split = msg.split(" ");

        System.out.println();
    }

    /**
     * 打印在线用户
     */
    private void printOnlineUsers() {
        try {

            LOGGER.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        } catch (Exception e) {
            LOGGER.error("Exception", e);
        }
    }



    public void openAIModel() {
        aiModel = true;
    }


    public void closeAIModel() {
        aiModel = false ;
    }

    private void printAllCommand(Map<String, String> allStatusCode) {
        LOGGER.warn("====================================");
        for (Map.Entry<String, String> stringStringEntry : allStatusCode.entrySet()) {
            String key = stringStringEntry.getKey();
            String value = stringStringEntry.getValue();
            LOGGER.warn(key + "----->" + value);
        }
        LOGGER.warn("====================================");
    }
}
