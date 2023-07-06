package com.assignment.stockcontrolsystemapi.controller;

import com.assignment.stockcontrolsystemapi.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController()
@RequestMapping("/api/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;


    @PostMapping("/generate/supplier/{stockId}")
    public void generateSupplierInvoice(@PathVariable String stockId, HttpServletResponse response) throws Exception{
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=supplier_" + stockId + ".pdf";
        response.setHeader(headerKey, headerValue);
        invoiceService.generateSupplierInvoice(stockId, response);
    }

    @PostMapping("/generate/consumer/{consumerItemId}")
    public void generateConsumerInvoice(@PathVariable String consumerItemId, HttpServletResponse response) throws Exception{
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=consumer_" + consumerItemId + ".pdf";
        response.setHeader(headerKey, headerValue);
        invoiceService.generateConsumerInvoice(consumerItemId, response);
    }

}
