package com.example.testfinal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testfinal.R;
import com.example.testfinal.model.BankCustomer;

import java.util.List;

public class adapterRecyclerView extends RecyclerView.Adapter<adapterRecyclerView.MyViewHolder> {

    private Context mContext;
    private int mLayoutResId;
    private List<BankCustomer> foodItemList;

    public adapterRecyclerView(Context mContext, int mLayoutResId, List<BankCustomer> foodItemList) {
        this.mContext = mContext;
        this.mLayoutResId = mLayoutResId;
        this.foodItemList = foodItemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(mLayoutResId, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BankCustomer item  = foodItemList.get(position);

        holder.BC=item;
        holder.nameTextView.setText(item.name);
        holder.phoneTextView.setText(item.phone);
        holder.calTextView.setText(String.valueOf(item.amount));

    }

    @Override
    public int getItemCount() {
        return foodItemList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView;
        private TextView calTextView;
        private TextView phoneTextView;

        private BankCustomer BC;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.nameTextView = itemView.findViewById(R.id.name_text_view);
            this.phoneTextView = itemView.findViewById(R.id.phone_textView);
            this.calTextView = itemView.findViewById(R.id.amount_text_view);


        }
    }



}
