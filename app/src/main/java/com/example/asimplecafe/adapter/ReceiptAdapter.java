package com.example.asimplecafe.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asimplecafe.R;
import com.example.asimplecafe.model.Receipt;

import java.util.List;

public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.ReceiptViewHolder> {
    private static final String TAG = "RECEIPT ADAPTER";

    Fragment fragment;
    List<Receipt> receiptList;

    public ReceiptAdapter(Fragment frag, List<Receipt> receiptList) {
        this.receiptList = receiptList;
        this.fragment = frag;
    }

    @NonNull
    @Override
    public ReceiptViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_receipt,
                parent, false);
        return new ReceiptAdapter.ReceiptViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptViewHolder holder, int position) {
        Receipt item = receiptList.get(position);

        holder.id.setText( String.valueOf( item.getId( ) ));
        holder.price.setText( R.string.rupiah_denominator );
        holder.price.append( String.valueOf( item.getProfit( ) ));
        holder.date.setText( item.getDate( ) );
    }

    @Override
    public int getItemCount() {
        return receiptList.size();
    }

    public static class ReceiptViewHolder extends RecyclerView.ViewHolder {
        TextView id, price, date;

        public ReceiptViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.rv_receipt_id);
            price = itemView.findViewById(R.id.rv_receipt_payment_rp_number);
            date = itemView.findViewById(R.id.rv_receipt_datetime);
        }
    }
}
