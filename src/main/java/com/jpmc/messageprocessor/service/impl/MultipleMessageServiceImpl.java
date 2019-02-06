package com.jpmc.messageprocessor.service.impl;

import com.jpmc.messageprocessor.exceptions.MessageHandlerException;
import com.jpmc.messageprocessor.model.MultipleOccurenceSaleMessage;
import com.jpmc.messageprocessor.model.Sale;
import com.jpmc.messageprocessor.model.SingleSaleMessage;
import com.jpmc.messageprocessor.repository.SaleRepository;
import com.jpmc.messageprocessor.service.MessageService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class MultipleMessageServiceImpl implements MessageService {



    @Override
    public boolean processMessage(SingleSaleMessage message) throws MessageHandlerException {

        MultipleOccurenceSaleMessage multipleOccurenceSaleMessage = (MultipleOccurenceSaleMessage) message;
        try{
            List<Sale> sales = new ArrayList<Sale>();
            IntStream.range(0, multipleOccurenceSaleMessage.getNumberOfOccurences()).forEach(occurences -> sales.add(new Sale(message.getProduct(), message.getValue())));
            SaleRepository.saveMultipleOcurrenceSale(sales);
        }catch (Exception ex){
            System.out.println("Unable to process multi sale message");
            return false;
        }
        return true;
    }
}
