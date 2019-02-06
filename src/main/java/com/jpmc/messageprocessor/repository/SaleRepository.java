package com.jpmc.messageprocessor.repository;

import com.jpmc.messageprocessor.model.Adjustment;
import com.jpmc.messageprocessor.model.Sale;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SaleRepository{


    private static List<Sale> saleList = new ArrayList<>();
    private static List<Adjustment> adjustmentList = new ArrayList<>();

    public static void saveMultipleOcurrenceSale(List<Sale> sales) {
        sales.forEach(sale -> saveSale(sale));
    }



    public static void saveSale(Sale objectToSave) {
        saleList.add(objectToSave);
    }


    public static void saveAdjustment(Adjustment objectToSave) {
        adjustmentList.add(objectToSave);
    }


    public static List<Sale> getAllSales() {
        return saleList;
    }


    public static List<Adjustment> getAllAdjustments() {
        return adjustmentList;
    }


    public static List<Sale> getSalesByProductType(String productType) {
        List<Sale> result = getAllSales().stream()
                .filter(sale -> sale.getProductType().equals(productType))
                .collect(Collectors.toList());
        return result;
    }

    public static Map<String, List<Sale>> getAllSalesGroupedByProductType() {
        Map<String, List<Sale>> result = getAllSales().stream().collect(Collectors.groupingBy(Sale::getProductType));
        return result;
    }


}
