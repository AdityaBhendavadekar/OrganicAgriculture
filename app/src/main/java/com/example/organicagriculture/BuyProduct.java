package com.example.organicagriculture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.organicagriculture.R.id;

import org.w3c.dom.Text;

public class BuyProduct extends AppCompatActivity {

    Bundle bundle;
    String product,price,productQuantity;
    int imageResorce,totalamount;

    TextView productName,productPrice1,productPrice2,totalAmt;
    ImageView imageView;

    Button placeOrder;
    EditText quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_product);

        bundle= getIntent().getExtras();
        product = bundle.getString("pName");
        price = bundle.getString("pPrice");
        imageResorce = bundle.getInt("image");

        productName = (TextView) findViewById(id.proname);
        productPrice1= (TextView) findViewById(id.proprice);
        imageView = (ImageView) findViewById(id.img);
        productPrice2 = findViewById(id.price2);
        quantity = findViewById(id.Quantity);
        totalAmt = findViewById(id.totalBill);

        placeOrder = findViewById(id.buyButton);


        quantity.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_ENTER) {
                    productQuantity = quantity.getText().toString();

                    int purePrice = Integer.parseInt(price);
                    int prQuantity =Integer.parseInt(productQuantity);
                    int total = purePrice*prQuantity+40;

                    totalAmt.setText(""+total);
//                    quantity.setFocusable(false);

                    placeOrder.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(BuyProduct.this,Order_Confirmation.class);
                            startActivity(intent);
                            finish();

                        }
                    });

                return false;
                }
                return false;
            }
        });

        imageView.setImageResource(imageResorce);
        productPrice1.setText(price);
        productPrice2.setText(price);
        productName.setText(product);





//        Toast.makeText(this, ""+total, Toast.LENGTH_SHORT).show();



    }
}