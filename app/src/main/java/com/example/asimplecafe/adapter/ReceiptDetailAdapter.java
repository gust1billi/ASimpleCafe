package com.example.asimplecafe.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asimplecafe.R;
import com.example.asimplecafe.model.Cart;

import java.util.List;

public class ReceiptDetailAdapter
        extends RecyclerView.Adapter<ReceiptDetailAdapter.ReceiptDetailViewHolder>{
    Fragment fragment;
    List<Cart> cartList;

    public ReceiptDetailAdapter(Fragment fragment, List<Cart> cartList) {
        this.fragment = fragment;
        this.cartList = cartList;
    }


    @NonNull
    @Override
    public ReceiptDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext( ) )
                .inflate( R.layout.rv_receipt_details,
                parent, false );
        return new ReceiptDetailAdapter.ReceiptDetailViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptDetailViewHolder holder, int position) {
        Cart item = cartList.get(position);
        holder.name.setText( item.getName( ) );
        holder.price.setText( String.valueOf( item.getPrice( ) ) );
        holder.quantity.setText( String.valueOf( item.getQty( ) ) );
//        holder.itemView.setOnClickListener(
//                view -> Utils.showToast( ctx, "p" ) );
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public static class ReceiptDetailViewHolder extends RecyclerView.ViewHolder {
        TextView name, quantity, price;

        public ReceiptDetailViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.rv_receipt_detail_name);
            quantity = itemView.findViewById(R.id.rv_receipt_detail_qty);
            price = itemView.findViewById(R.id.rv_receipt_detail_price_number);
        }
    }
}
