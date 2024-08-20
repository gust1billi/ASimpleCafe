package com.example.asimplecafe.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.asimplecafe.R;
import com.example.asimplecafe.fragment.HomeFragment;

import java.util.Objects;

public class DialogEditQuantity extends DialogFragment {
    private static final String TAG = "DialogEditQuantityFragment";
    Fragment fragment;

    String title, currentQuantity;

    TextView dialogQuantity, dialogConfirm, dialogDecline;
    ImageButton dialogDecrease, dialogIncrease;

    int position;

    public DialogEditQuantity(Fragment frag, String title, String currentQty, int position ) {
        this.fragment = frag;
        this.title = title;
        this.currentQuantity = currentQty;
        this.position = position;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View contentView = getActivity().getLayoutInflater().inflate(
                R.layout.dialog_cart_edit_quantity, null
        );
        dialogQuantity = contentView.findViewById(R.id.dialog_cart_edit_quantity_text);
        dialogIncrease = contentView.findViewById(R.id.dialog_cart_edit_quantity_increment);
        dialogDecrease = contentView.findViewById(R.id.dialog_cart_edit_quantity_decrement);
        dialogConfirm = contentView.findViewById(R.id.dialog_quantity_confirm);
        dialogDecline = contentView.findViewById(R.id.dialog_quantity_decline);

        dialogQuantity.setText( currentQuantity );
        dialogIncrease.setOnClickListener( view ->
                        dialogQuantity.setText( increment() )
                );
        dialogDecrease.setOnClickListener( view -> 
                        dialogQuantity.setText( decrement() )
                );
        dialogConfirm.setOnClickListener( view -> {
            ((HomeFragment) fragment).setProductQty(
                    Integer.parseInt(dialogQuantity.getText().toString()),
                    position
            );
            Objects.requireNonNull( getDialog( ) ).dismiss( );
        } );
        dialogDecline.setOnClickListener( view ->
                Objects.requireNonNull( getDialog( ) ).dismiss( )
                );

        Dialog builder = new Dialog( getActivity() );
        builder.setTitle( title );
        builder.setContentView( contentView );
        return builder;
    }

    private String decrement() {
        int integerQty = Integer.parseInt( dialogQuantity.getText().toString( ) );
        if ( integerQty != 0 ){
            integerQty--;
        } return "" + integerQty;
    }

    private String increment( ){
        return String.valueOf( 
                Integer.parseInt( dialogQuantity.getText().toString() ) + 1 
        );
    }
}
