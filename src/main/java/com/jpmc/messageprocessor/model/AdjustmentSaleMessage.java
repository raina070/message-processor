package com.jpmc.messageprocessor.model;


import java.math.BigDecimal;

public class AdjustmentSaleMessage extends SingleSaleMessage {


    public AdjustmentSaleMessage(String product, BigDecimal value,String messageType){
        super(product,value,messageType);
    }

    public AdjustmentOperation getAdjustmentType() {
        return adjustmentOperation;
    }

    public void setAdjustmentType(AdjustmentOperation adjustmentType) {
        this.adjustmentOperation = adjustmentType;
    }

    public AdjustmentOperation getAdjustmentOperation() {
        return adjustmentOperation;
    }

    public void setAdjustmentOperation(AdjustmentOperation adjustmentOperation) {
        this.adjustmentOperation = adjustmentOperation;
    }

    private AdjustmentOperation adjustmentOperation;


}
