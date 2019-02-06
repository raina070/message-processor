package com.jpmc.messageprocessor.producer;

import com.jpmc.messageprocessor.model.AdjustmentOperation;
import com.jpmc.messageprocessor.model.AdjustmentSaleMessage;
import com.jpmc.messageprocessor.model.MultipleOccurenceSaleMessage;
import com.jpmc.messageprocessor.model.SingleSaleMessage;

import java.math.BigDecimal;
import java.util.*;

public class TestData {


    //IncomingMessageQueue queue;
    Set<String> products = new HashSet<>(Arrays.asList("Apple", "Orange", "Banana", "Pear", "Mango"));
    Set<BigDecimal> adjustmentPrices = new HashSet<>(Arrays.asList(BigDecimal.valueOf(0.50),
            BigDecimal.valueOf(1.50), BigDecimal.valueOf(0.30)));
    Set<Integer> amounts = new HashSet<>(Arrays.asList(3, 5, 7));
    Set<AdjustmentOperation> adjustmentOperation = new HashSet<>(Arrays.asList(AdjustmentOperation.ADD, AdjustmentOperation.MULTIPLY, AdjustmentOperation.SUBTRACT));

    Map<String, BigDecimal> productPrices = new HashMap<String, BigDecimal>() {{
        put("Apple", BigDecimal.valueOf(0.50));
        put("Banana", BigDecimal.valueOf(0.20));
        put("Orange", BigDecimal.valueOf(0.40));
        put("Pear", BigDecimal.valueOf(0.45));
        put("Mango", BigDecimal.valueOf(1.00));
    }};


    public void populateQueue(IncomingMessageQueue queue) {


        for (int i = 1; i < 60; i++) {
            if (i % 11 == 0) {

                AdjustmentSaleMessage adjustmentSaleMessage = new AdjustmentSaleMessage(
                        (String) getRandom(products),
                        (BigDecimal) getRandom(adjustmentPrices), "ADJUSTMENT");

                adjustmentSaleMessage.setAdjustmentOperation((AdjustmentOperation) getRandom(adjustmentOperation));
                queue.getMessageQueue().add(adjustmentSaleMessage);
            } else if (i % 5 == 0) {
                String product = (String) getRandom(products);
                MultipleOccurenceSaleMessage message = new MultipleOccurenceSaleMessage(product,
                        productPrices.get(product), "MULTI");
                message.setNumberOfOccurences((Integer) getRandom(amounts));
                queue.getMessageQueue().add(message);
            } else {
                String product = (String) getRandom(products);
                queue.getMessageQueue().add(
                        new SingleSaleMessage(product,
                                productPrices.get(product), "SINGLE"));


            }
        }


    }

    private Object getRandom(Collection e) {
        return e.stream()
                .skip((int) (e.size() * Math.random()))
                .findFirst().get();
    }
}
