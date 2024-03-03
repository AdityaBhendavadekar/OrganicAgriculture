package com.example.organicagriculture;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class Order_Confirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_info)
                .setTitle("Confirm Order").setMessage("Please Confirm Your Order.")
                .setPositiveButton("Buy Now", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        Toast.makeText(Order_Confirmation.this, "Your order is placed succefully",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }).show();

    }
}