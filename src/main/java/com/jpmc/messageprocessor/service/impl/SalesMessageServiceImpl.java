package com.jpmc.messageprocessor.service.impl;

import com.jpmc.messageprocessor.model.*;
import com.jpmc.messageprocessor.repository.SaleRepository;
import com.jpmc.messageprocessor.service.MessageService;

public class SalesMessageServiceImpl implements MessageService {

    @Override
    public boolean processMessage(SingleSaleMessage message) {


        try {
            Sale sale = new Sale(message.getProduct(), message.getValue());
            SaleRepository.saveSale(sale);
        } catch (Exception ex) {
            System.out.println("Unable to process sale message");
            return false;
        }

        return true;
    }


}
