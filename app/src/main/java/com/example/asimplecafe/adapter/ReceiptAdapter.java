package com.example.asimplecafe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asimplecafe.R;
import com.example.asimplecafe.model.Receipt;

import java.util.ArrayList;
import java.util.List;

public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.ReceiptViewHolder> {
    private static final String TAG = "RECEIPT ADAPTER";
    Context ctx;

    List<Receipt> receiptList = new ArrayList<>();

    @NonNull
    @Override
    public ReceiptViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.receipt_rv,
                parent, false);
        return new ReceiptAdapter.ReceiptViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return receiptList.size();
    }

    public static class ReceiptViewHolder extends RecyclerView.ViewHolder {
        TextView tv_1, tv_2; // TODO: VISUALS FIRST

        public ReceiptViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
