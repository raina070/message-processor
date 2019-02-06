package com.jpmc.messageprocessor;

import com.jpmc.messageprocessor.model.SingleSaleMessage;
import com.jpmc.messageprocessor.producer.IncomingMessageQueue;
import com.jpmc.messageprocessor.producer.TestData;
import com.jpmc.messageprocessor.service.MessageProcessor;
import com.jpmc.messageprocessor.service.ReportLogGenerator;
import com.jpmc.messageprocessor.service.impl.ReportLogGeneratorImpl;
import com.jpmc.messageprocessor.service.impl.SalesMessageProcessorImpl;

import java.util.LinkedList;
import java.util.Queue;

public class ApplicationMain {

    public static void main(String[] args) {




        ReportLogGenerator reportLogGenerator = new ReportLogGeneratorImpl();
        Queue<SingleSaleMessage> messageQueue = new LinkedList<>();
        IncomingMessageQueue queue = new IncomingMessageQueue(messageQueue);
        TestData data = new TestData();
        data.populateQueue(queue);
        MessageProcessor messageProcessor = new SalesMessageProcessorImpl(reportLogGenerator,queue);


        messageProcessor.processMessages();
    }
}
