package com.example.asimplecafe.fragment;

import android.content.Context;
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

import com.example.asimplecafe.R;
import com.example.asimplecafe.Utils;
import com.example.asimplecafe.adapter.CartAdapter;
import com.example.asimplecafe.adapter.ProductAdapter;
import com.example.asimplecafe.model.Cart;
import com.example.asimplecafe.model.ConstantValues;
import com.example.asimplecafe.model.Product;

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
    List<Cart> cartList; List<Product> productList;

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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cartRV = view.findViewById(R.id.frag_home_cart_RecyclerView);
        productRV = view.findViewById(R.id.frag_home_product_RecyclerView);
        cartList = ConstantValues.getDummyCartItems(); // TEST ONLY
        productList = ConstantValues.getDummyProducts(); // TEST ONLY
        cartAdapter = new CartAdapter( getContext() , cartList);
        productAdapter = new ProductAdapter( HomeFragment.this, productList );
        cartLayoutManager = new LinearLayoutManager( getContext() );
        productLayoutManager = new LinearLayoutManager( getContext() );

        cartRV.setAdapter( cartAdapter ); cartRV.setLayoutManager( cartLayoutManager );
        productRV.setAdapter( productAdapter ); productRV.setLayoutManager( productLayoutManager );
    }


    public void addToCart(String product_name, int product_price) {
//         Utils.showToast( getContext() , "p" );
        Log.e(TAG, "pepe");
    }
}