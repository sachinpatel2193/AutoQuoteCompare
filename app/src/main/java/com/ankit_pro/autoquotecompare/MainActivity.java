package com.ankit_pro.autoquotecompare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    Button getstarted;
    EditText Postal_Code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getstarted = (Button)findViewById(R.id.get_started);
        Postal_Code = (EditText)findViewById(R.id.postalcode);

        getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                                  // On Button OnClick Event
                String PostalCode = Postal_Code.getText().toString();
                if(PostalCode.isEmpty()){                                       // Null Validation for Postal Code
                    Postal_Code.setError("Postal Code can not be empty");
                }

                String regex = "^[0-9]{5}(?:-[0-9]{4})?$";                      // Regular Expression for Postal Code Validation

                Pattern pattern = Pattern.compile(regex);                       // Postal code Validation

                Matcher matcher = pattern.matcher(PostalCode);                 // Postal code Validation
                if(matcher.matches()){                                         // If postal code is valid then send user to GetInformation Page
                    Intent intent = new Intent(MainActivity.this, GetInformation.class);
                    intent.putExtra("PostalCode", PostalCode);          // sending value of Postal Code to Next activity
                    startActivity(intent);
                }
                else{
                    Postal_Code.setError("Enter Valid Postal Code");             // If postal code is invalid then give validation error
                }
            }
        });
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {                                               // User will have to press back button twice to exit application
        if (doubleBackToExitPressedOnce) {
            MainActivity.super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(MainActivity.this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();          // Toast Notification to inform user to press back button twice to exit app when user press back button home page of application

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

}

