package com.jpmc.messageprocessor.model;


import java.math.BigDecimal;

public class Adjustment {

    private String productName;
    private BigDecimal amount;
    private AdjustmentOperation adjustmentOperation;


    public Adjustment(AdjustmentOperation adjustmentOperation, String productName, BigDecimal amount) {
        this.adjustmentOperation = adjustmentOperation;
        this.productName = productName;
        this.amount = amount;
    }


    public Adjustment(String productName, BigDecimal amount) {

        this.productName = productName;
        this.amount = amount;
    }


    public AdjustmentOperation getAdjustmentOperation() {
        return adjustmentOperation;
    }

    public void setAdjustmentOperation(AdjustmentOperation adjustmentOperation) {
        this.adjustmentOperation = adjustmentOperation;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Adjustment that = (Adjustment) o;

        if (adjustmentOperation != that.adjustmentOperation) return false;
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;
        return amount != null ? amount.equals(that.amount) : that.amount == null;
    }

    @Override
    public int hashCode() {
        int result = adjustmentOperation != null ? adjustmentOperation.hashCode() : 0;
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Adjustment{" +
                "adjustmentOperation=" + adjustmentOperation +
                ", productName='" + productName + '\'' +
                ", amount=" + amount +
                '}';
    }
}
