package by.itclass.model.entities;

import lombok.Value;

@Value
public class ReceiptItem {
    String itemInfo;
    double itemPrice;
    int quantity;
    double itemAmount;
}
