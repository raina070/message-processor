package com.jpmc.messageprocessor.service.impl;

import com.jpmc.messageprocessor.exceptions.EmptyMessageQueueException;
import com.jpmc.messageprocessor.exceptions.MessageHandlerException;
import com.jpmc.messageprocessor.factory.MessageProcessorFactory;
import com.jpmc.messageprocessor.model.SingleSaleMessage;
import com.jpmc.messageprocessor.producer.IncomingMessageQueue;
import com.jpmc.messageprocessor.service.MessageProcessor;
import com.jpmc.messageprocessor.service.ReportLogGenerator;
import com.jpmc.messageprocessor.service.MessageService;


public class SalesMessageProcessorImpl implements MessageProcessor {

    IncomingMessageQueue incomingMessageQueue;

    ReportLogGenerator reportLogGenerator;


    public SalesMessageProcessorImpl(final ReportLogGenerator reportLogGenerator,IncomingMessageQueue incomingMessageQueue) {
        this.reportLogGenerator = reportLogGenerator;
        this.incomingMessageQueue = incomingMessageQueue;
    }

    @Override
    public void processMessages() {

        System.out.println("Message processor has started!");

        int recievedMessages = 0;
        while (!incomingMessageQueue.isQueueEmpty()) {
            SingleSaleMessage message;
            try {
                message = incomingMessageQueue.getMessageFromQueue();
            } catch (EmptyMessageQueueException e) {
                System.err.println(e.getMessage());
                return;
            }

            boolean success = processMessage(message);
            if (!success) {
                continue;
            }


            recievedMessages++;

            System.out.println("Received Message No:- " + recievedMessages );
            if (recievedMessages % 10 == 0) {
                reportLogGenerator.generateSalesReport();

            }
            if (recievedMessages == 50) {
                reportLogGenerator.generateAdjustmentsReport();
                break;
            }
        }

        System.out.println("Message processor has finished.");

    }


    private boolean processMessage(SingleSaleMessage message) {

        MessageService processor = MessageProcessorFactory.getMessageProcessor(message.getMessageType());
        if (processor == null) {
            System.err.println("Error while getting processor impl for type=" + message.getMessageType());
            return false;
        }

        try {
            processor.processMessage(message);
        } catch (MessageHandlerException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;


    }
}
