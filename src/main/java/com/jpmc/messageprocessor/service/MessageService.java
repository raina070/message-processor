package com.jpmc.messageprocessor.service;

import com.jpmc.messageprocessor.exceptions.MessageHandlerException;
import com.jpmc.messageprocessor.model.SingleSaleMessage;

public interface MessageService {
    public boolean processMessage(SingleSaleMessage message) throws MessageHandlerException;



}
