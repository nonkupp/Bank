package com.example.testfinal.room_db;

import android.content.Context;
import android.os.AsyncTask;

import com.example.testfinal.model.BankCustomer;

import java.util.List;

public class LedgerRepository {

    private Context mContext;


    public LedgerRepository(Context mContext) {
        this.mContext = mContext;
    }

    public void getLedger(Callback callback){
        GetTask getTask = new GetTask(mContext,callback);
        getTask.execute();

    }
    public void insertLedger(BankCustomer item, InsertCallback callback){
        InsertTask insrInsertTask = new InsertTask(mContext,callback);
        insrInsertTask.execute(item);
    }

    public void deleteLedger(deleteCallback callback){
        DeleteTask deleteTask = new DeleteTask(mContext,callback);
        deleteTask.execute();

    }
    private static class GetTask extends AsyncTask<Void , Void , List<BankCustomer>>{

        private Context mContext;
        private Callback mCallback;

        public GetTask(Context context, Callback callback){
            this.mContext = context;
            this.mCallback = callback;
        }

        @Override
        protected List<BankCustomer> doInBackground(Void... voids) {
            AppDatabase db = AppDatabase.getInstance(mContext);
            List<BankCustomer> itemList = db.ledgerDAO().getAll();
            return itemList;
        }

        @Override
        protected void onPostExecute(List<BankCustomer> ledgerItems) {

            super.onPostExecute(ledgerItems);
            mCallback.onGetLedger(ledgerItems);
        }
    }

    public interface Callback {
        void onGetLedger(List<BankCustomer> itemList);
    }

    private static class InsertTask extends AsyncTask<BankCustomer,Void,Void > {

        private Context mContext;
        private InsertCallback mCallback;
        public InsertTask(Context context, InsertCallback callback){
            this.mContext = context;
            this.mCallback=callback;
        }



        @Override
        protected Void doInBackground(BankCustomer... ledgerItems) {
            AppDatabase db = AppDatabase.getInstance(mContext);
            db.ledgerDAO().insert(ledgerItems[0]);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mCallback.onInsertSuccess();

        }
    }

    public interface InsertCallback{
        void onInsertSuccess();
    }





    private static class DeleteTask extends AsyncTask<Void,Void,Void > {

        private Context mContext;
        private deleteCallback mCallback;
        public DeleteTask(Context context, deleteCallback callback){
            this.mContext = context;
            this.mCallback= callback;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mCallback.deleteLedger();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            AppDatabase db = AppDatabase.getInstance(mContext);
            db.ledgerDAO().deleteAll();
            return null;
        }
    }

    public interface deleteCallback {
        void deleteLedger();
    }


}
