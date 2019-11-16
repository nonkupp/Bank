package com.example.testfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testfinal.db.DatabaseHelper;
import com.example.testfinal.model.BankCustomer;
import com.example.testfinal.model.employee;
import com.example.testfinal.room_db.LedgerRepository;

import java.util.ArrayList;
import java.util.List;

public class InputBioActivity extends AppCompatActivity {
    private List<BankCustomer> mBankCustomer = new ArrayList<>();
    DatabaseHelper dbHelper;
    SQLiteDatabase bankDB;
    Cursor mCursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_bio);


        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        dbHelper = new DatabaseHelper(this);
        bankDB = dbHelper.getWritableDatabase();

        mCursor = bankDB.rawQuery("SELECT " + DatabaseHelper.COL_NAME+", "+DatabaseHelper.COL_PHONE+", " +DatabaseHelper.COL_AMOUNT+" FROM "+ DatabaseHelper.TABLE_BANK, null);

        mCursor.moveToFirst();
        BankCustomer fd ;

        while(!mCursor.isAfterLast()){
            fd = new BankCustomer(0,mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COL_NAME)),mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COL_PHONE)),mCursor.getInt(Integer.valueOf(mCursor.getColumnIndex(DatabaseHelper.COL_AMOUNT))));
            mBankCustomer.add(fd);
            mCursor.moveToNext();

        }

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextName = findViewById(R.id.name_editText);
                final String inputname = editTextName.getText().toString();
                EditText editTextPhone = findViewById(R.id.phone_editText);
                final String inputphone = editTextPhone.getText().toString();
                EditText editTextamount = findViewById(R.id.depositamount_editText);
                final int inputAmount = Integer.parseInt(editTextamount.getText().toString());

                BankCustomer bc = null;
                for (BankCustomer f : mBankCustomer) {
                    if (inputname.equals(f)) {
                        bc = f;
                        //System.out.println(bankcus.cal+" "+bankcus.name);

                    }
                }
                if (bc != null) {
                    LedgerRepository repo = new LedgerRepository(InputBioActivity.this);
                    repo.insertLedger(bc, new LedgerRepository.InsertCallback() {
                        @Override
                        public void onInsertSuccess() {
                            onResume();
                        }
                    });
                } else {
                    Toast.makeText(InputBioActivity.this,/*String*/"Same Data in DB", Toast.LENGTH_LONG).show();
                }
                Intent intent = new Intent(InputBioActivity.this, RecyclerActivity.class);
                startActivity(intent);
            }
        });
    }

}






