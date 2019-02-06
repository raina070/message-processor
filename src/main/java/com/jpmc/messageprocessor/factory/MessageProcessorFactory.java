package com.jpmc.messageprocessor.factory;

import com.jpmc.messageprocessor.service.MessageService;
import com.jpmc.messageprocessor.service.impl.AdjustmentMessageServiceimpl;
import com.jpmc.messageprocessor.service.impl.MultipleMessageServiceImpl;
import com.jpmc.messageprocessor.service.impl.SalesMessageServiceImpl;

import java.util.HashMap;
import java.util.Map;


public class MessageProcessorFactory {
    private static Map<String, MessageService> messageProcessorMap;

    static {
        registerMessageProcessors();
    }

    public static MessageService getMessageProcessor(String type) {
        final MessageService messageService = messageProcessorMap.get(type);

        if (messageService == null) {
            System.err.println("Unrecognized message type " + type + ". Ignoring the message");
        }

        return messageService;
    }


    private static void registerMessageProcessors() {

        messageProcessorMap = new HashMap<>();
        messageProcessorMap.put("SINGLE", new SalesMessageServiceImpl());
        messageProcessorMap.put("MULTI", new MultipleMessageServiceImpl());
        messageProcessorMap.put("ADJUSTMENT", new AdjustmentMessageServiceimpl());
    }
}
