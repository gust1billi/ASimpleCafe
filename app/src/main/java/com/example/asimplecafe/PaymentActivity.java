package com.example.asimplecafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.asimplecafe.adapter.CartAdapter;
import com.example.asimplecafe.database.DBHandler;
import com.example.asimplecafe.model.Cart;

import java.util.List;

public class PaymentActivity extends AppCompatActivity {
    private static final String TAG = "PAYMENT ACTIVITY";

    DBHandler dbHandler;

    RecyclerView cartRV;
    CartAdapter cartAdapter;
    LinearLayoutManager cartLayoutManager;

    List<Cart> cartList;

    TextView paymentNumber, productQty, paymentTotalPrice;

    Button exactPaidBtn, paymentBtn, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,
            btnC, btn0, btn00, btn000; ImageButton btnBackspace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        dbHandler = new DBHandler(PaymentActivity.this);
//        int total_price = Integer.parseInt( getIntent().getStringExtra("paid") );

        productQty = findViewById(R.id.payment_product_qty);
        paymentTotalPrice = findViewById(R.id.total_payment_to_pay);
        exactPaidBtn = findViewById( R.id.exact_paid_btn );
        paymentBtn = findViewById( R.id.pay_btn );

        productQty.append( getIntent().getStringExtra("qty" ) );
        paymentTotalPrice.append( getIntent().getStringExtra("paid" ) );
        exactPaidBtn.setOnClickListener( view ->
                paymentNumber.setText( getIntent().getStringExtra("paid").substring(3) )
        );
        Log.e(TAG, "||" + getIntent().getStringExtra("paid").substring(3) );
        paymentBtn.setOnClickListener(view ->
                checkValidPayment() );

        paymentNumber = findViewById(R.id.payment_number);
        initRecyclerView(); keyboardInit();
    }

    private void checkValidPayment() {
        if ( paymentNumber.getText().equals("-")){
            Utils.showToast(PaymentActivity.this, "Please input the number paid" );
        } else if ( Integer.parseInt( paymentNumber.getText().toString( ) )
                <
                Integer.parseInt( getIntent().getStringExtra("paid").substring(3) )
        ) {
            Utils.showToast(PaymentActivity.this, "Payment amount not valid" );
        } else {
            // EXECUTE SQL
            int payment, paid, receipt_id, product_id;
            payment = Integer.parseInt( getIntent().getStringExtra("paid").substring(3) );
            paid = Integer.parseInt( paymentNumber.getText().toString() );
            receipt_id = dbHandler.addReceipt(
                    payment, paid, paid - payment
            );
            for ( Cart item: cartList ) {
                product_id = dbHandler.getProductId( item.getName() );
                dbHandler.addReceiptProduct(
                        receipt_id,
                        product_id,
                        item.getQty()
                );
            } finish();
        }
    }

    private void keyboardInit() {
        btn0 = findViewById(R.id.btn0);
        btn0.setOnClickListener(view -> editPaymentAmount("0"));
        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(view -> editPaymentAmount("1"));
        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(view -> editPaymentAmount("2"));
        btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(view -> editPaymentAmount("3"));
        btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(view -> editPaymentAmount("4"));
        btn5 = findViewById(R.id.btn5);
        btn5.setOnClickListener(view -> editPaymentAmount("5"));
        btn6 = findViewById(R.id.btn6);
        btn6.setOnClickListener(view -> editPaymentAmount("6"));
        btn7 = findViewById(R.id.btn7);
        btn7.setOnClickListener(view -> editPaymentAmount("7"));
        btn8 = findViewById(R.id.btn8);
        btn8.setOnClickListener(view -> editPaymentAmount("8"));
        btn9 = findViewById(R.id.btn9);
        btn9.setOnClickListener(view -> editPaymentAmount("9"));
        btn00 = findViewById(R.id.btn00);
        btn00.setOnClickListener(view -> editPaymentAmount("00"));
        btn000 = findViewById(R.id.btn000);
        btn000.setOnClickListener(view -> editPaymentAmount("000"));
        btnC = findViewById(R.id.btnClear);
        btnC.setOnClickListener(view -> editPaymentAmount("C"));
        btnBackspace = findViewById(R.id.btnBackspace);
        btnBackspace.setOnClickListener(view -> editPaymentAmount("del"));
    }

    private void editPaymentAmount(String s) {
        if ( s.equals("C") ){
            paymentNumber.setText("");
        } else if ( s.equals("del") ){
            if ( paymentNumber.length() <= 1  ){
                paymentNumber.setText("");
            } else paymentNumber.setText( Utils.chop(
                    String.valueOf(paymentNumber.getText( ) ) ) );
        } else {
            if ( paymentNumber.getText().equals("-") ) paymentNumber.setText("");
            paymentNumber.append(s);
        }
    }

    private void initRecyclerView() {
        cartLayoutManager = new LinearLayoutManager(PaymentActivity.this);
        cartRV = findViewById( R.id.payment_recycler_view );

        Intent i = getIntent();
        cartList = (List<Cart>) i.getSerializableExtra("cart");

        cartAdapter = new CartAdapter( cartList );
        cartRV.setAdapter(cartAdapter);
        cartRV.setLayoutManager(cartLayoutManager);
    }
}