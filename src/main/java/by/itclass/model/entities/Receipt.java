package by.itclass.model.entities;

import lombok.Data;

import java.util.List;

@Data
public class Receipt {
    private Order order;
    private List<ReceiptItem> receiptItems;
    private double total;
}
