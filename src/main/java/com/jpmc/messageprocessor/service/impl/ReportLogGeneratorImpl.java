package com.jpmc.messageprocessor.service.impl;

import com.jpmc.messageprocessor.model.Adjustment;
import com.jpmc.messageprocessor.model.Sale;
import com.jpmc.messageprocessor.repository.SaleRepository;
import com.jpmc.messageprocessor.service.ReportLogGenerator;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class ReportLogGeneratorImpl implements ReportLogGenerator {


    @Override
    public void generateSalesReport() {
        Map<String, List<Sale>> sales = SaleRepository.getAllSalesGroupedByProductType();

        System.out.println("================= Reporting Sales =================");

        for (String product: new TreeSet<String>(sales.keySet())){
            int noOfSales = sales.get(product).size();
            BigDecimal totalValueOfSales = new BigDecimal(0);

            for(Sale sale :sales.get(product))
            {
                BigDecimal value = sale.getValue();
                totalValueOfSales = totalValueOfSales.add(value);
            }

            DecimalFormat df = new DecimalFormat();
            df.setMinimumFractionDigits(2);
            df.setMaximumFractionDigits(10);
            df.setGroupingUsed(false);

            System.out.println(product+" sales Quantity: "+noOfSales+", total value: "+ df.format(totalValueOfSales));
        }
        System.out.println("===================================================");
    }

    @Override
    public void generateAdjustmentsReport() {
        List<Adjustment> adjustments = SaleRepository.getAllAdjustments();

        System.out.println();
        System.out.println();

        System.out.println("================= Reporting Adjustments =================");

        for (Adjustment adjustment : adjustments) {
            System.out.println(adjustment.getProductName() + " adjustments with operation : " + adjustment.getAdjustmentOperation().toString() + " with an adjustment of:" + adjustment.getAmount());
        }
        System.out.println("===================================================");

        System.out.println("===================================================");
        System.out.println("==         The Application Is Now Paused         ==");
        System.out.println("===================================================");
    }
}
