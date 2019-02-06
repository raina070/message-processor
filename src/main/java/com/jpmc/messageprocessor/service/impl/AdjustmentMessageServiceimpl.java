package com.jpmc.messageprocessor.service.impl;

import com.jpmc.messageprocessor.exceptions.MessageHandlerException;
import com.jpmc.messageprocessor.model.*;
import com.jpmc.messageprocessor.repository.SaleRepository;
import com.jpmc.messageprocessor.service.MessageService;
import java.math.BigDecimal;
import java.util.List;

public class AdjustmentMessageServiceimpl implements MessageService {


    @Override
    public boolean processMessage(SingleSaleMessage message) throws MessageHandlerException {

        AdjustmentSaleMessage adjustmentSaleMessage = (AdjustmentSaleMessage) message;
        List<Sale> sales = SaleRepository.getSalesByProductType(adjustmentSaleMessage.getProduct());
        sales.forEach(sale -> performOperation(adjustmentSaleMessage.getAdjustmentOperation(), adjustmentSaleMessage.getValue(), sale));
        SaleRepository.saveMultipleOcurrenceSale(sales);

        Adjustment adjustment = new Adjustment(adjustmentSaleMessage.getAdjustmentOperation(), message.getProduct(), message.getValue());
        SaleRepository.saveAdjustment(adjustment);
        return true;

    }



    private void performOperation(AdjustmentOperation operation, BigDecimal value, Sale sale) {
        BigDecimal originalValue = sale.getValue();
        BigDecimal newValue = null;

        switch (operation) {
            case ADD:
                newValue = originalValue.add(value);
                break;
            case SUBTRACT:
                newValue = originalValue.subtract(value);
                break;
            case MULTIPLY:
                newValue = originalValue.multiply(value);
                break;
            default:
                break;
        }
        sale.setValue(newValue);
    }
}
