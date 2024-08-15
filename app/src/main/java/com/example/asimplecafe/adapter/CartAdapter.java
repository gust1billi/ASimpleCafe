package com.example.asimplecafe.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asimplecafe.R;
import com.example.asimplecafe.Utils;
import com.example.asimplecafe.model.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private static final String TAG = "CART ADAPTER";
    Context ctx;

    List<Cart> cartList;

    public CartAdapter(Context ctx, List<Cart> list) {
        this.ctx = ctx;
        this.cartList = list;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_cart,
                parent, false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart item = cartList.get(position);
        holder.name.setText( item.getName( ) );
        holder.price.setText( String.valueOf( item.getPrice( ) ) );
        holder.quantity.setText( String.valueOf( item.getQty( ) ) );
        holder.itemView.setOnClickListener(
                view -> Utils.showToast( ctx, "p" ) );
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView name, quantity, price;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.cart_product_name);
            quantity = itemView.findViewById(R.id.cart_qty);
            price = itemView.findViewById(R.id.cart_price);
        }
    }

    public void newItemAdded(int positionStack) {
        notifyItemInserted( positionStack );
        notifyItemRangeChanged(0, positionStack );
    }

    public void itemAdded(int positionStack) {
        Log.e(TAG, "size: " + cartList.size() );

        notifyItemChanged( positionStack );
//        notifyItemRangeChanged(0, positionStack );
    }

    public void itemUpdated(int positionStack) {
        notifyItemChanged( positionStack );
    }

    public void itemRemoved(int position) {
        notifyItemRemoved(position);
        notifyItemRangeChanged( position -1, cartList.size() );
    }

    public void itemPurged(int lastPosition){
        notifyItemRangeRemoved(0, lastPosition);
    }
}
