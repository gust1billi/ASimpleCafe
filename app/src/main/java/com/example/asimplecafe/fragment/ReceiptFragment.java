package com.example.asimplecafe.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asimplecafe.R;
import com.example.asimplecafe.adapter.ReceiptAdapter;
import com.example.asimplecafe.adapter.ReceiptDetailAdapter;
import com.example.asimplecafe.model.Cart;
import com.example.asimplecafe.model.ConstantValues;
import com.example.asimplecafe.model.Receipt;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReceiptFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReceiptFragment extends Fragment {
    private static final String TAG = "Receipt Fragment";

    RecyclerView detailRV, receiptRV;
    LinearLayoutManager detailLayoutManager, receiptLayoutManager;
    ReceiptDetailAdapter detailAdapter; ReceiptAdapter receiptAdapter;
    List<Cart> detailList; List<Receipt> receiptList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReceiptFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReceiptFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReceiptFragment newInstance(String param1, String param2) {
        ReceiptFragment fragment = new ReceiptFragment();
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
        View view = inflater.inflate(R.layout.fragment_receipt, container, false);
        receiptRV = view.findViewById(R.id.frag_receipt_RecyclerView);
        detailRV = view.findViewById(R.id.frag_receipt_detail_RecyclerView);
        detailList = ConstantValues.getDummyCartItems(); // TEST ONLY
        receiptList = ConstantValues.getDummyReceipts(); // TEST ONLY
        receiptAdapter = new ReceiptAdapter( ReceiptFragment.this, receiptList );
        detailAdapter = new ReceiptDetailAdapter( ReceiptFragment.this, detailList);
        detailLayoutManager = new LinearLayoutManager( getContext() );
        receiptLayoutManager = new LinearLayoutManager( getContext() );

        detailRV.setAdapter( detailAdapter ); detailRV.setLayoutManager( detailLayoutManager );
        receiptRV.setAdapter( receiptAdapter ); receiptRV.setLayoutManager( receiptLayoutManager );

        return view;
    }
}