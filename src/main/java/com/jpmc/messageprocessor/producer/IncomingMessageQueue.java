package com.jpmc.messageprocessor.producer;

import com.jpmc.messageprocessor.exceptions.EmptyMessageQueueException;
import com.jpmc.messageprocessor.model.SingleSaleMessage;


import java.util.Queue;


public class IncomingMessageQueue {
    public IncomingMessageQueue(Queue<SingleSaleMessage> messageQueue) {
        this.messageQueue = messageQueue;
    }

    public Queue<SingleSaleMessage> getMessageQueue() {
        return messageQueue;
    }

    public void setMessageQueue(Queue<SingleSaleMessage> messageQueue) {
        this.messageQueue = messageQueue;
    }

    private  Queue<SingleSaleMessage> messageQueue ;
    public SingleSaleMessage getMessageFromQueue() throws EmptyMessageQueueException {
        if (messageQueue.isEmpty()) {
            throw new EmptyMessageQueueException("The event queue is empty");
        }
        return messageQueue.poll();
    }


    public  boolean isQueueEmpty() {
        return messageQueue.isEmpty();
    }
}
