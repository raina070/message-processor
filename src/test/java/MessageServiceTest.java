import com.jpmc.messageprocessor.model.*;
import com.jpmc.messageprocessor.repository.SaleRepository;
import com.jpmc.messageprocessor.service.impl.AdjustmentMessageServiceimpl;
import com.jpmc.messageprocessor.service.impl.MultipleMessageServiceImpl;
import com.jpmc.messageprocessor.service.impl.SalesMessageServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class MessageServiceTest {


    private MultipleMessageServiceImpl multipleMessageServiceImpl;
    private SalesMessageServiceImpl salesMessageServiceImpl;
    private AdjustmentMessageServiceimpl adjustmentMessageServiceimpl;
    private List<Sale> sales;
    private Sale sale1 = new Sale("Apple", BigDecimal.valueOf(0.50));
    private Sale sale2 = new Sale("Apple", BigDecimal.valueOf(2.50));
    private Adjustment adjustment = new Adjustment("Apple", BigDecimal.valueOf(5.5));

    @Before
    public void setUp() {
        multipleMessageServiceImpl = new MultipleMessageServiceImpl();
        adjustmentMessageServiceimpl = new AdjustmentMessageServiceimpl();
        salesMessageServiceImpl = new SalesMessageServiceImpl();
        sales = new ArrayList<>();

    }

    @Test
    public void testSingleMessageProcessing() throws Exception {
        SingleSaleMessage message = new SingleSaleMessage("Apple", BigDecimal.valueOf(0.50), "SINGLE");

        salesMessageServiceImpl.processMessage(message);
        assertEquals(sale1, SaleRepository.getAllSales().get(0));
    }

    @Test
    public void testMultiMessageProcessing() throws Exception {
        MultipleOccurenceSaleMessage message = new MultipleOccurenceSaleMessage("Apple", BigDecimal.valueOf(0.50), "MULTI");
        message.setNumberOfOccurences(4);
        multipleMessageServiceImpl.processMessage(message);
        assertEquals(sale1, SaleRepository.getAllSales().get(0));
    }

    @Test
    public void testAdjustmentMessageProcessing() throws Exception {
        SaleRepository.saveSale(sale1);
        SaleRepository.saveSale(sale2);
        AdjustmentSaleMessage message = new AdjustmentSaleMessage("Apple", BigDecimal.valueOf(5.50), "ADJUSTMENT");
        message.setAdjustmentOperation(AdjustmentOperation.ADD);
        adjustmentMessageServiceimpl.processMessage(message);
        assertEquals(BigDecimal.valueOf(6.0), SaleRepository.getAllSales().get(0).getValue());
        assertEquals(BigDecimal.valueOf(8.0), SaleRepository.getAllSales().get(1).getValue());
    }


}
