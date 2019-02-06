package com.jpmc.messageprocessor.model;

import java.math.BigDecimal;


public class SingleSaleMessage {

    private String product;
    private BigDecimal value;
    private String messageType;

    public SingleSaleMessage(String product, BigDecimal value, String messageType) {
        this.product = product;
        this.value = value;
        this.messageType = messageType;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }


    public String getProduct() {
        return product;
    }


    public void setProduct(String product) {
        this.product = product;
    }


    public BigDecimal getValue() {
        return value;
    }


    public void setValue(BigDecimal price) {
        this.value = price;
    }
}