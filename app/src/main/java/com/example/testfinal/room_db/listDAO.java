package com.example.testfinal.room_db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.testfinal.model.BankCustomer;
import com.example.testfinal.model.BankCustomer;

import java.util.List;

@Dao
public interface listDAO {
    @Query("SELECT * FROM bank")
    List<BankCustomer> getAll();

    @Insert
    void insert(BankCustomer fd);

    @Query("DELETE FROM bank")
    void deleteAll();
}
