package com.example.testfinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.testfinal.adapter.adapterRecyclerView;
import com.example.testfinal.db.DatabaseHelper;
import com.example.testfinal.model.BankCustomer;
import com.example.testfinal.room_db.LedgerRepository;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {
    private List<BankCustomer> mBankCustomer = new ArrayList<>();
    DatabaseHelper dbHelper;
    SQLiteDatabase bankDB;
    Cursor mCursor;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);



        //Reset Button
        Button resetButton = findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(RecyclerActivity.this)
                        .setTitle("Reset")
                        .setMessage("Do you want reset now ?")
                        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                LedgerRepository repo = new LedgerRepository(RecyclerActivity.this);
                                repo.deleteLedger(new LedgerRepository.deleteCallback() {
                                    @Override
                                    public void deleteLedger() {
                                        onResume();
                                    }
                                });
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .show();

            }
        });

    }
    @Override
    protected void onResume(){
        super.onResume();
        reloadData();
    }
    private void reloadData(){
        LedgerRepository repo= new LedgerRepository(RecyclerActivity.this);
        repo.getLedger(new LedgerRepository.Callback() {
            @Override
            public void onGetLedger(List<BankCustomer> itemList) {
                int totalCal = 0;
                for (BankCustomer item : itemList) {
                    totalCal += item.amount;
                }
                TextView BalanceTextView = findViewById(R.id.balance_text_view);
                BalanceTextView.setText("Total Amount "+  totalCal +" $");
                RecyclerView recyclerView = findViewById(R.id.recyclerView);
                adapterRecyclerView adapter = new adapterRecyclerView(
                        RecyclerActivity.this,
                        R.layout.item_list,
                        itemList
                );
                recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerActivity.this));
                recyclerView.setAdapter(adapter);
            }
        });
    }
}
