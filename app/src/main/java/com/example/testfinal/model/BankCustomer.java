package com.example.testfinal.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bank")
public class BankCustomer {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "phone")
    public String phone;

    @ColumnInfo(name = "amount")
    public int amount;


    public BankCustomer(int id, String name, String phone, int amount) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.amount = amount;
    }
}
