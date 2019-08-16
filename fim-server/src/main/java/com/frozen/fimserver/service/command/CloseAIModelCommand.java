package com.frozen.fimserver.service.command;

import com.frozen.fimserver.service.InnerCommand;
import com.frozen.fimserver.service.MsgHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Function:
 *
 * @author frozen
 * Date: 2019-01-27 19:37
 * @since JDK 1.8
 */
@Service
public class CloseAIModelCommand implements InnerCommand {
    private final static Logger LOGGER = LoggerFactory.getLogger(CloseAIModelCommand.class);


    @Autowired
    private MsgHandler msgHandle ;

    @Override
    public void process(String msg) {
        msgHandle.closeAIModel();
        System.out.println("\033[31;4m" + "｡ﾟ(ﾟ´ω`ﾟ)ﾟ｡  AI 下线了！" + "\033[0m");
    }
}
