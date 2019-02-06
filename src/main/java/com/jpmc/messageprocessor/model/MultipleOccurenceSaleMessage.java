package com.jpmc.messageprocessor.model;

import java.math.BigDecimal;

public class MultipleOccurenceSaleMessage extends SingleSaleMessage {

    public Integer getNumberOfOccurences() {
        return numberOfOccurences;
    }

    public MultipleOccurenceSaleMessage(String product, BigDecimal value,String messageType){
        super(product,value,messageType);
    }

    public void setNumberOfOccurences(Integer numberOfOccurences) {
        this.numberOfOccurences = numberOfOccurences;
    }

    private Integer numberOfOccurences;


}

