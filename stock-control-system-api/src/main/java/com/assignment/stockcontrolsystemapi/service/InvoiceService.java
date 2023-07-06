package com.assignment.stockcontrolsystemapi.service;

import com.assignment.stockcontrolsystemapi.model.ConsumerItem;
import com.assignment.stockcontrolsystemapi.model.Inventory;
import com.assignment.stockcontrolsystemapi.model.Item;
import com.assignment.stockcontrolsystemapi.model.User;
import com.assignment.stockcontrolsystemapi.repository.ConsumerItemRepository;
import com.assignment.stockcontrolsystemapi.repository.InventoryRepository;
import com.assignment.stockcontrolsystemapi.repository.ItemRepository;
import com.assignment.stockcontrolsystemapi.repository.UserRepository;
import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

@Service
public class InvoiceService {

    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConsumerItemRepository consumerItemRepository;
    @Value("${admin.user}")
    private String username;


    public void generateSupplierInvoice(String stockId, HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(12);
        Paragraph paragraph = new Paragraph("Stock Control System Invoice", font);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);


        Inventory inventory = inventoryRepository.findById(stockId).get();
        User admin = userRepository.findById(username).get();
        User supplier = userRepository.findById(inventory.getSupplierId()).get();
        Item item = itemRepository.findById(inventory.getItemId()).get();

        Font inventoryDetailsFont = FontFactory.getFont(FontFactory.COURIER);
        inventoryDetailsFont.setSize(10);
        Paragraph  inventoryDetails = new Paragraph(
            "INVENTORY DETAILS \n" +
                "Item Name: " + item.getName() + "\n" +
                "Count: " + inventory.getCount() + "\n" +
                "Total Price: " + inventory.getTotalPrice() + "\n"
            , inventoryDetailsFont);
        inventoryDetails.setAlignment(Paragraph.ALIGN_CENTER);

        Font sellerDetailsFont = FontFactory.getFont(FontFactory.COURIER);
        sellerDetailsFont.setSize(10);
        Paragraph sellerDetails = new Paragraph(
            "SUPPLIER DETAILS \n" +
                "Name: " + supplier.getUsername() + "\n" +
                "Address: " + supplier.getAddress() + "\n" +
                "Email: " + supplier.getEmail() + "\n" +
                "Phone Number: " + supplier.getPhoneNumber() + "\n"
            , sellerDetailsFont);
        sellerDetails.setAlignment(Paragraph.ALIGN_CENTER);

        Font debitAccountDetailsFont = FontFactory.getFont(FontFactory.COURIER_BOLD);
        debitAccountDetailsFont.setSize(10);
        Paragraph debitAccountDetails = new Paragraph(
            "DEBIT ACCOUNT DETAILS \n" +
                "Bank Code: " + admin.getBankCode() + "\n" +
                "Bank Account Number: " + admin.getBankAccountNumber() + "\n"
            , debitAccountDetailsFont);
        debitAccountDetails.setAlignment(Paragraph.ALIGN_CENTER);

        Font creditAccountDetailsFont = FontFactory.getFont(FontFactory.COURIER_BOLD);
        creditAccountDetailsFont.setSize(10);
        Paragraph creditAccountDetails = new Paragraph(
            "CREDIT ACCOUNT DETAILS \n" +
                "Bank Code: " + supplier.getBankCode() + "\n" +
                "Bank Account Number: " + supplier.getBankAccountNumber() + "\n"
            , creditAccountDetailsFont);
        creditAccountDetails.setAlignment(Paragraph.ALIGN_CENTER);

        PdfPTable accountDetailsTable = new PdfPTable(2);
        accountDetailsTable.setWidthPercentage(100f);
        accountDetailsTable.setSpacingBefore(10);

        PdfPCell cell = new PdfPCell();
        cell.setPadding(5);
        cell.setBorder(0);
        Font tableFont = FontFactory.getFont(FontFactory.COURIER);
        tableFont.setColor(Color.BLACK);

        cell.setPhrase(debitAccountDetails);
        accountDetailsTable.addCell(cell);

        cell.setPhrase(creditAccountDetails);
        accountDetailsTable.addCell(cell);

        PdfPTable stockDetailsTable = new PdfPTable(2);
        stockDetailsTable.setWidthPercentage(100f);
        stockDetailsTable.setSpacingBefore(10);

        cell.setPhrase(inventoryDetails);
        stockDetailsTable.addCell(cell);

        cell.setPhrase(sellerDetails);
        stockDetailsTable.addCell(cell);

        document.add(stockDetailsTable);
        document.add(accountDetailsTable);
        document.close();
    }


    public void generateConsumerInvoice(String consumerItemId, HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(12);
        Paragraph paragraph = new Paragraph("Stock Control System Invoice", font);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);


        ConsumerItem consumerItem = consumerItemRepository.findById(consumerItemId).get();
        User admin = userRepository.findById(username).get();
        User consumer = userRepository.findById(consumerItem.getConsumerId()).get();
        Item item = itemRepository.findById(consumerItem.getItemId()).get();

        Font inventoryDetailsFont = FontFactory.getFont(FontFactory.COURIER);
        inventoryDetailsFont.setSize(10);
        Paragraph  inventoryDetails = new Paragraph(
            "INVENTORY DETAILS \n" +
                "Item Name: " + item.getName() + "\n" +
                "Count: " + consumerItem.getCount() + "\n" +
                "Total Price: " + consumerItem.getTotalPrice() + "\n"
            , inventoryDetailsFont);
        inventoryDetails.setAlignment(Paragraph.ALIGN_CENTER);

        Font sellerDetailsFont = FontFactory.getFont(FontFactory.COURIER);
        sellerDetailsFont.setSize(10);
        Paragraph sellerDetails = new Paragraph(
            "CONSUMER DETAILS \n" +
                "Name: " + consumer.getUsername() + "\n" +
                "Address: " + consumer.getAddress() + "\n" +
                "Email: " + consumer.getEmail() + "\n" +
                "Phone Number: " + consumer.getPhoneNumber() + "\n"
            , sellerDetailsFont);
        sellerDetails.setAlignment(Paragraph.ALIGN_CENTER);

        Font debitAccountDetailsFont = FontFactory.getFont(FontFactory.COURIER_BOLD);
        debitAccountDetailsFont.setSize(10);
        Paragraph debitAccountDetails = new Paragraph(
            "DEBIT ACCOUNT DETAILS \n" +
                "Bank Code: " + consumer.getBankCode() + "\n" +
                "Bank Account Number: " + consumer.getBankAccountNumber() + "\n"
            , debitAccountDetailsFont);
        debitAccountDetails.setAlignment(Paragraph.ALIGN_CENTER);

        Font creditAccountDetailsFont = FontFactory.getFont(FontFactory.COURIER_BOLD);
        creditAccountDetailsFont.setSize(10);
        Paragraph creditAccountDetails = new Paragraph(
            "CREDIT ACCOUNT DETAILS \n" +
                "Bank Code: " + admin.getBankCode() + "\n" +
                "Bank Account Number: " + admin.getBankAccountNumber() + "\n"
            , creditAccountDetailsFont);
        creditAccountDetails.setAlignment(Paragraph.ALIGN_CENTER);

        PdfPTable accountDetailsTable = new PdfPTable(2);
        accountDetailsTable.setWidthPercentage(100f);
        accountDetailsTable.setSpacingBefore(10);

        PdfPCell cell = new PdfPCell();
        cell.setPadding(5);
        cell.setBorder(0);
        Font tableFont = FontFactory.getFont(FontFactory.COURIER);
        tableFont.setColor(Color.BLACK);

        cell.setPhrase(debitAccountDetails);
        accountDetailsTable.addCell(cell);

        cell.setPhrase(creditAccountDetails);
        accountDetailsTable.addCell(cell);

        PdfPTable stockDetailsTable = new PdfPTable(2);
        stockDetailsTable.setWidthPercentage(100f);
        stockDetailsTable.setSpacingBefore(10);

        cell.setPhrase(inventoryDetails);
        stockDetailsTable.addCell(cell);

        cell.setPhrase(sellerDetails);
        stockDetailsTable.addCell(cell);

        document.add(stockDetailsTable);
        document.add(accountDetailsTable);
        document.close();
    }
}
