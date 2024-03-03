package com.example.organicagriculture;

import static com.example.organicagriculture.DatabaseHelper.USER_MOB;
import static com.example.organicagriculture.DatabaseHelper.USER_PASS;
import static com.example.organicagriculture.DatabaseHelper.USER_TABLE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogIn extends AppCompatActivity {

    private EditText mob,pass;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        TextView signupMove=(TextView) findViewById(R.id.textView5);
        Button login = (Button) findViewById(R.id.login);

//        db = new DatabaseHelper(this);
        db = DatabaseHelper.getInstace(this);

        login.setOnClickListener(view -> {
            String str="",mobileNo,passText;

            mob = (EditText) findViewById(R.id.username);
            pass =(EditText) findViewById(R.id.password);

            mobileNo =mob.getText().toString();
            passText = pass.getText().toString();

            //here we will only check in database if user with mobile no. is register or not
            //if it is then we will validate over password
            if(mobileNo.isEmpty()){
                Toast.makeText(this, "Enter mobile no.", Toast.LENGTH_SHORT).show();
                return;
            }
            else if(passText.isEmpty()){
                Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
                return;
            }
            else {
                try {

                    //fetching data from database and check user is registered or not.
                    SQLiteDatabase sqldb = db.getReadableDatabase();

                    Cursor result = sqldb.query(USER_TABLE, new String[]{USER_MOB, USER_PASS}, USER_MOB + "=" + mobileNo+" and "+ USER_PASS+"="+passText, null, null, null, null);

                    if(result!=null) {
                        result.moveToFirst();
                        if (result.getString(1).equals(passText)) {
                            Intent intent = new Intent(this, HomePage.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                catch (SQLiteException sqlex){
                    Toast.makeText(this, ""+sqlex, Toast.LENGTH_SHORT).show();
                }
                catch (CursorIndexOutOfBoundsException cursorexc){
                    Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
                }

            }
        });

        signupMove.setOnClickListener(view -> {
            Intent signup = new Intent(LogIn.this,SignUp.class);
            startActivity(signup);
        });
    }
}