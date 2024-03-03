package com.example.organicagriculture;

import static com.example.organicagriculture.DatabaseHelper.LURE_ACRE;
import static com.example.organicagriculture.DatabaseHelper.LURE_CROP;
import static com.example.organicagriculture.DatabaseHelper.LURE_IMG;
import static com.example.organicagriculture.DatabaseHelper.LURE_NAME;
import static com.example.organicagriculture.DatabaseHelper.LURE_PRICE;
import static com.example.organicagriculture.DatabaseHelper.LURE_REPLACEMENT;
import static com.example.organicagriculture.DatabaseHelper.LURE_SUITABLETRAP;
import static com.example.organicagriculture.DatabaseHelper.LURE_TABLE;
import static com.example.organicagriculture.DatabaseHelper.TRAP_IMG;
import static com.example.organicagriculture.DatabaseHelper.TRAP_NAME;
import static com.example.organicagriculture.DatabaseHelper.TRAP_PRICE;
import static com.example.organicagriculture.DatabaseHelper.TRAP_SUITABLELURE;
import static com.example.organicagriculture.DatabaseHelper.TRAP_TABLE;
import static com.example.organicagriculture.DatabaseHelper.USER_MOB;
import static com.example.organicagriculture.DatabaseHelper.USER_PASS;
import static com.example.organicagriculture.DatabaseHelper.USER_TABLE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductDetails extends AppCompatActivity {

    Bundle bundle;
    String product;
    DatabaseHelper db;
    Cursor prodInfo;
    int Img;
    String trapName,suitableLure,Price;
    String lureName,suitableTrap,hostCrop,lureReplacementDay,perAcre;
    Button buybtn,cartbtn;
    ImageView productImg;

    TextView hName,hsuitable,hCrop,hReplacement,hAcre,hPrice;
    TextView nameTxt,suitableTxt,hostTxt,replacementTxt,acreTxt,priceTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        buybtn= findViewById(R.id.buybtn);
        productImg = findViewById(R.id.imageView2);

        hName = findViewById(R.id.textView8);
        hsuitable=findViewById(R.id.textView22);
        hCrop = findViewById(R.id.textView24);
        hReplacement = findViewById(R.id.textView25);
        hAcre = findViewById(R.id.textView28);
        hPrice = findViewById(R.id.hPrice);

        nameTxt = findViewById(R.id.nameTxt);
        hostTxt = findViewById(R.id.hostCropText);
        replacementTxt = findViewById(R.id.textView27);
        acreTxt = findViewById(R.id.perAcreTxt);
        suitableTxt= findViewById(R.id.suitableTraptxt);
        priceTxt = findViewById(R.id.priceTxt);


        db = DatabaseHelper.getInstace(this);
        bundle = getIntent().getExtras();
        product = bundle.getString("product");

        Pattern pattern = Pattern.compile(" Trap", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(product);
        boolean matchFound = matcher.find();

        if(matchFound) {
            try {

                SQLiteDatabase sqldb = db.getReadableDatabase();
                String trapcols[]={TRAP_IMG,TRAP_NAME,TRAP_PRICE,TRAP_SUITABLELURE};

                Cursor result = sqldb.query(TRAP_TABLE,trapcols,TRAP_NAME+"="+"'"+product+"'",null,null,null,null);

                if(result!=null){
                    result.moveToFirst();

//                    trapImg = Integer.parseInt(result.getString(0));
                    Context c = getApplicationContext();
                    Img = c.getResources().getIdentifier("drawable/"+result.getString(0),null,c.getPackageName());

                    trapName = result.getString(1);
                    Price = result.getString(2);
                    suitableLure = result.getString(3);

                    productImg.setImageResource(Img);
                    nameTxt.setText(trapName);
                    suitableTxt.setText(suitableLure);
                    priceTxt.setText(Price+" INR");
                    hsuitable.setText("Suitable lure:");

                    hostTxt.setVisibility(View.GONE);
                    hCrop.setVisibility(View.GONE);
                    hReplacement.setVisibility(View.GONE);
                    hAcre.setVisibility(View.GONE);
                    replacementTxt.setVisibility(View.GONE);
                    acreTxt.setVisibility(View.GONE);

                }
            }catch (SQLiteException sqlex){
                Toast.makeText(this, ""+sqlex, Toast.LENGTH_SHORT).show();
            }catch (CursorIndexOutOfBoundsException cursorexc){
                Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            try {
                SQLiteDatabase sqldb = db.getReadableDatabase();
                String lurecols[]={LURE_IMG,LURE_NAME,LURE_PRICE,LURE_SUITABLETRAP,LURE_CROP,LURE_REPLACEMENT,LURE_ACRE};

                Cursor result = sqldb.query(LURE_TABLE,lurecols,LURE_NAME+"="+"'"+product+"'",null,null,null,null);

                if(result!=null){
                    result.moveToFirst();

//                    trapImg = Integer.parseInt(result.getString(0));
                    Context c = getApplicationContext();
                    Img = c.getResources().getIdentifier("drawable/"+result.getString(0),null,c.getPackageName());

                    lureName = result.getString(1);
                    Price = result.getString(2);
                    suitableTrap = result.getString(3);
                    hostCrop = result.getString(4);
                    lureReplacementDay=result.getString(5);
                    perAcre=result.getString(6);

                    productImg.setImageResource(Img);
                    nameTxt.setText(lureName);
                    suitableTxt.setText(suitableTrap);
                    priceTxt.setText(Price+" INR");
                    hostTxt.setText(hostCrop);
                    replacementTxt.setText(lureReplacementDay);
                    acreTxt.setText(perAcre);


                }
            }catch (SQLiteException sqlex){
                Toast.makeText(this, ""+sqlex, Toast.LENGTH_SHORT).show();
            }catch (CursorIndexOutOfBoundsException cursorexc){
                Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show();
            }
        }
        buybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductDetails.this,BuyProduct.class);
                intent.putExtra("pName",product);
                intent.putExtra("pPrice",Price);
                intent.putExtra("image",Img);
                startActivity(intent);
            }
        });
    }
}