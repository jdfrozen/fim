package com.frozen.fimserver.service.command;

import com.frozen.fimserver.service.InnerCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Function:
 *
 * @author frozen
 * Date: 2019-01-27 19:37
 * @since JDK 1.8
 */
@Service
public class PrintOnlineUsersCommand implements InnerCommand {
    private final static Logger LOGGER = LoggerFactory.getLogger(PrintOnlineUsersCommand.class);


    @Override
    public void process(String msg) {
        try {

            LOGGER.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            LOGGER.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        } catch (Exception e) {
            LOGGER.error("Exception", e);
        }
    }
}
