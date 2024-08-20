package com.example.asimplecafe.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asimplecafe.PaymentActivity;
import com.example.asimplecafe.R;
import com.example.asimplecafe.Utils;
import com.example.asimplecafe.adapter.CartAdapter;
import com.example.asimplecafe.adapter.ProductAdapter;
import com.example.asimplecafe.api.APIHandler;
import com.example.asimplecafe.database.DBHandler;
import com.example.asimplecafe.dialog.DialogEditQuantity;
import com.example.asimplecafe.model.Cart;
import com.example.asimplecafe.model.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private static final String TAG = "Home Fragment";

    RecyclerView cartRV, productRV;
    LinearLayoutManager cartLayoutManager, productLayoutManager;
    CartAdapter cartAdapter; ProductAdapter productAdapter;

    List<Cart> cartList; List<Product> productList; List<String> titleList;

    TextView frag_home_total_paid, frag_home_total_qty;
    ImageView purgeImage;

    Button payBtn;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() { /* Required empty public constructor */ }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cartList = new ArrayList<>( );
        productList = new ArrayList<>( );
        titleList = new ArrayList<>( );

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
//        introTV = view.findViewById( R.id.frag_home_intro ); introTV.setText( TAG );
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View contentView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(contentView, savedInstanceState);

        cartRV = contentView.findViewById(R.id.frag_home_cart_RecyclerView);
        productRV = contentView.findViewById(R.id.frag_home_product_RecyclerView);
        frag_home_total_paid = contentView.findViewById(R.id.cart_price_number);
        frag_home_total_qty = contentView.findViewById(R.id.cart_quantity_number);

        purgeImage = contentView.findViewById( R.id.cart_purge_image );
        purgeImage.setOnClickListener( view -> clearCart( ) );
        payBtn = contentView.findViewById(R.id.frag_home_payment_button);
        payBtn.setOnClickListener( view -> callPaymentActivity() );
//        cartList = ConstantValues.getDummyCartItems(); // TEST ONLY
//        productList = ConstantValues.getDummyProducts(); // TEST ONLY
        checkDB();

        cartAdapter = new CartAdapter( HomeFragment.this , cartList);
        productAdapter = new ProductAdapter( HomeFragment.this, productList );
        cartLayoutManager = new LinearLayoutManager( getContext() );
        productLayoutManager = new LinearLayoutManager( getContext() );

        cartRV.setAdapter( cartAdapter ); cartRV.setLayoutManager( cartLayoutManager );
        productRV.setAdapter( productAdapter ); productRV.setLayoutManager( productLayoutManager );
    }

    private void checkDB() {
        DBHandler dbHandler = new DBHandler( getContext() );
        Cursor cursor = dbHandler.readAllProducts();
        productList.clear();

        if ( cursor.getCount() != 0 ) {
            while ( cursor.moveToNext( ) ) {
                productList.add( new Product(
                        cursor.getString(1),
                        cursor.getInt(2)
                ) );
            }
        } else APIHandler.doLogin( getContext(), HomeFragment.this );
    }

    public void addToCart(String product_name, int product_price) {
        // JIKA JUDUL BELUM PERNAH MASUK KE CART
        if ( !titleList.contains( product_name ) ){
            titleList.add( product_name );
            // Pakai title list karena List<Object>.get(position).contains pusing
            Log.e(TAG, "TITLE LIST: " + titleList.size());
            cartList.add( new Cart( product_name, product_price ) );
            cartAdapter.newItemAdded( titleList.indexOf( product_name ) );
        } else {
            cartList.get( titleList.indexOf( product_name ) ).addQuantity();
            cartAdapter.itemQtyAdded( titleList.indexOf( product_name ) );
        }
        update_total();
    }

    private void update_total() {
        int total_price = 0;
        int total_qty = 0;

        if ( titleList.size() != 0) {
            for (int i = 0; i < titleList.size() ; i++) {
                total_price += cartList.get(i).getPrice( )
                        * cartList.get(i).getQty();
                total_qty += cartList.get(i).getQty();
            }
        }

        frag_home_total_paid.setText( R.string.rupiah_denominator );
        frag_home_total_paid.append( "" + total_price );
        frag_home_total_qty.setText( String.valueOf( total_qty ) );
    }

    public void setEditQuantityDialog(String product_name, String currentQty, int position) {
        /* DIALOG FRAGMENT */
        DialogEditQuantity dialogFragment =
                new DialogEditQuantity(
                        HomeFragment.this,
                        product_name,
                        currentQty,
                        position
                );
        dialogFragment.show( requireActivity().getSupportFragmentManager(), TAG );
    }

    public void setProductQty(int updatedQty, int position) {
        if (updatedQty != 0){
            cartList.get(position).setQty( updatedQty );
            update_total(); cartAdapter.itemUpdated( position );
        } else {
            if ( cartList.size() == 1){
                cartList.remove( 0 );
                titleList.remove( 0 );
            }else {
                cartList.remove( position );
                titleList.remove(position);
            }
            update_total();
            cartAdapter.itemRemoved( position );
        }
    }

    private void callPaymentActivity(){
        if ( !cartList.isEmpty() ){
            Intent i = new Intent( getContext(), PaymentActivity.class);
            i.putExtra("cart", (Serializable) cartList);
            Log.e(TAG, "Price: " + frag_home_total_paid.getText() );
            i.putExtra("qty", frag_home_total_qty.getText().toString() );
            i.putExtra("paid", frag_home_total_paid.getText().toString() );
            startActivity(i);
            clearCart();
        } else Utils.showToast( requireActivity(), getString( R.string.cart_empty_toast ) );
    }

    private void clearCart() {
        if ( !cartList.isEmpty() ){
            int lastPost = cartList.size();

            cartList.clear(); titleList.clear(); update_total();
            cartAdapter.itemPurged( lastPost );
        } else Utils.showToast( getContext(), getString( R.string.cart_empty_toast ) );
    }

    // CALLED FROM API HANDLER;
    // THIS MAKES SURE THE DATA IS FINISHED LOADING
    public void notifyFirstInitialization(){
        productAdapter.notifyItemRangeInserted(0, productList.size() );
    }

}