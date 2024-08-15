package com.example.asimplecafe.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.asimplecafe.R;
import com.example.asimplecafe.fragment.HomeFragment;
import com.example.asimplecafe.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private static final String TAG = "PRODUCT ADAPTER";
    Fragment fragment;

    List<Product> productList = new ArrayList<>();

    public ProductAdapter(Fragment fragment, List<Product> list) {
        this.fragment = fragment;
        this.productList = list;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_product,
                parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product item = productList.get(position);

        Glide.with(fragment).load(Uri.parse( item.getImageURI( ) ))
                .placeholder(R.drawable.ic_baseline_image_24 )
                .into( holder.image );

        holder.name.setText( item.getName( ) );
        holder.price.setText( String.valueOf( item.getPrice( ) ));
        holder.itemView.setOnClickListener(view ->
                ((HomeFragment)fragment).addToCart(
                        item.getName(),
                        item.getPrice()
                ) );
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView name, price; ImageView image;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.rv_product_title);
            price = itemView.findViewById(R.id.rv_product_price_number);
            image = itemView.findViewById(R.id.rv_product_image);
        }
    }

    public void setFilteredList( List<Product> filteredList ){
        this.productList = filteredList; notifyDataSetChanged();
    }
}
