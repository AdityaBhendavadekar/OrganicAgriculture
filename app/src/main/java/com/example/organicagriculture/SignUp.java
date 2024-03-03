package com.example.organicagriculture;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    DatabaseHelper db;
    private EditText mob,pass1,pass2;
    String mobText,p1,p2;
    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

//        db = new DatabaseHelper(this);
        db = DatabaseHelper.getInstace(this);
        TextView loginMove=(TextView) findViewById(R.id.textView7);
        signup= findViewById(R.id.signup);

        userRegistrationValidation();

        loginMove.setOnClickListener(view -> {
            Intent login = new Intent(SignUp.this,LogIn.class);
            startActivity(login);
            finish();
        });

    }

    private void userRegistrationValidation() {
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Boolean mobFlag = false, pass1Flag = false, pass2Flag = false;
                String str = "";

                mob = SignUp.this.findViewById(R.id.mobileNo);
                pass1 = SignUp.this.findViewById(R.id.pass1);
                pass2 = SignUp.this.findViewById(R.id.pass2);

                p1 = pass1.getText().toString();
                p2 = pass2.getText().toString();
                mobText = mob.getText().toString();

                if (mobText.isEmpty()) {
                    Toast.makeText(SignUp.this, "Please enter mobile number.", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (isValidMobileNo(mobText))
                        mobFlag = true;
                    else
                        mobFlag = false;
                }

                if (mobFlag) {
                    //if mobile no. is valid then check both passwords. if passwords are equal then register the user and go to log in page.
                    //this code will be written over here. in if statement.
                    if (p1.equals(p2)) {
                        if (p1.length() > 6 || p2.length() > 6) {
                            Toast.makeText(SignUp.this, "Pass length must be less than 6", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (p1.length() < 3 || p2.length() < 3) {
                            Toast.makeText(SignUp.this, "Pass length must be greater than 3", Toast.LENGTH_SHORT).show();
                            return;
                        } else {

                            //code to insert data into table
                            boolean isRegistered=db.registerUser(mobText, p1);

                            if (isRegistered) {
                                Toast.makeText(SignUp.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUp.this, LogIn.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(SignUp.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Toast.makeText(SignUp.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignUp.this, "Invalid Mobile", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public static boolean isValidMobileNo(String str){
        Pattern ptrn = Pattern.compile("(0/91)?[7-9][0-9]{9}");
        Matcher match = ptrn.matcher(str);
        return (match.find() && match.group().equals(str));
    }
}