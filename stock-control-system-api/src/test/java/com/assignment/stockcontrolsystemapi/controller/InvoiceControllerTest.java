package com.assignment.stockcontrolsystemapi.controller;

import com.assignment.stockcontrolsystemapi.service.InvoiceService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InvoiceControllerTest {

    @InjectMocks
    private InvoiceController invoiceController;

    @Mock
    private InvoiceService invoiceService;

    @Test
    void test_generateSupplierInvoice() throws Exception {
        HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);

        invoiceController.generateConsumerInvoice("consumerItemId", httpServletResponse);
    }

    @Test
    void test_generateConsumerInvoice() throws Exception {
        HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);

        invoiceController.generateSupplierInvoice("stockId", httpServletResponse);
    }
}