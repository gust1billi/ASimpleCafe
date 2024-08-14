package com.example.asimplecafe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asimplecafe.R;
import com.example.asimplecafe.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private static final String TAG = "PRODUCT ADAPTER";
    Context ctx;

    List<Product> productList = new ArrayList<>();

    public ProductAdapter(Context ctx) {
        this.ctx = ctx;
    }

    public ProductAdapter(Context ctx, List<Product> list) {
        this.ctx = ctx;
        this.productList = list;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_rv,
                parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView tv_1, tv_2; // TODO: VISUALS FIRST

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
