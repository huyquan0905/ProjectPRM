package com.example.projectprm392.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "history_orders")
public class OrderHistory {
    @PrimaryKey(autoGenerate = true)
    private int orderId;
    private int itemId;
    private int userId;
    private int quantity;
    private long orderDate;

    public OrderHistory() {
    }

    public OrderHistory(int orderId, int itemId, int userId, int quantity, long orderDate) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.userId = userId;
        this.quantity = quantity;
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(long orderDate) {
        this.orderDate = orderDate;
    }
}