package com.ankit_pro.autoquotecompare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

public class display_Companies extends AppCompatActivity {

    int[] IMAGES = {R.drawable.am_fam_1, R.drawable.progressive, R.drawable.statefarm, R.drawable.library, R.drawable.allstate_logo, R.drawable.geico_logo, R.drawable.usaa_logo};   // Array for logos of insurance companies
    String[] NAMES = {"American Family", "Progressive", "Statefarm", "Liberty Mutual", "AllState", "GEICO", "USAA"};                                                    // Array for names of insurance companies
    int[] amounts= {100,200,300,400,500,600,700};
    public int random_first, random_second;
    public String final_quote_value_string[] = new String[amounts.length];                          // making array to store amounts of quotes
    public String FirstName, LastName, Postal_Code;
    public int Annual_Milage, age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display__companies);

        Intent intent = getIntent();                                                                // getting values from previous activity through intent
        Postal_Code = intent.getStringExtra("Postal_Code");
        String Car_Model = intent.getStringExtra("Car_Model");
        Annual_Milage = intent.getIntExtra("Annual_Milage", 0);
        FirstName = intent.getStringExtra("FirstName");
        LastName = intent.getStringExtra("LastName");
        String BirthDate = intent.getStringExtra("BirthDate");
        String CarYear = intent.getStringExtra("CarYear");
        String MaritalStatus = intent.getStringExtra("MaritalStatus");
        age = intent.getIntExtra("age", 0);

        Log.d("Postal Code:", Postal_Code);
        Log.d("age", "age: from new class"+age);
        Log.d("annual milage", "annual milage:::::::::"+Annual_Milage);

        if(age > 16 && age <= 25){                                                                      // creating random numbers based on age and milage per year
            if(Annual_Milage>=0 && Annual_Milage<=5000){
                Log.d("where I am: ", "I am at"+1);
                random_first = new Random().nextInt(21) + 100; // [0, 200] + 175 => [175, 200]
            }else if(Annual_Milage>=5001 && Annual_Milage <=10000){
                Log.d("where I am: ", "I am at"+2);
                random_first = new Random().nextInt(31) + 150; // [0, 200] + 175 => [175, 200]
            }else if(Annual_Milage>=10001 && Annual_Milage <=20000){
                Log.d("where I am: ", "I am at"+3);
                random_first = new Random().nextInt(26) + 175; // [0, 200] + 175 => [175, 200]
            }else if(Annual_Milage>=20001 && Annual_Milage <=50000){
                Log.d("where I am: ", "I am at"+4);
                random_first = new Random().nextInt(26) + 200; // [0, 200] + 175 => [175, 200]
            }else{
                Log.d("where I am: ", "I am at"+5);
                random_first = new Random().nextInt(26) + 225;
            }
        } else if(age >=26 && age <= 50){
            if(Annual_Milage>=0 && Annual_Milage<=5000){
                Log.d("where I am: ", "I am at"+6);
                random_first = new Random().nextInt(16) + 90; // [0, 200] + 175 => [175, 200]
            }else if(Annual_Milage>=5001 && Annual_Milage <=10000){
                Log.d("where I am: ", "I am at"+7);
                random_first = new Random().nextInt(11) + 100; // [0, 200] + 175 => [175, 200]
            }else if(Annual_Milage>=10001 && Annual_Milage <=20000){
                Log.d("where I am: ", "I am at"+8);
                random_first = new Random().nextInt(16) + 115; // [0, 200] + 175 => [175, 200]
            }else if(Annual_Milage>=20001 && Annual_Milage <=50000){
                Log.d("where I am: ", "I am at"+9);
                random_first = new Random().nextInt(16) + 130; // [0, 200] + 175 => [175, 200]
            }else{
                Log.d("where I am: ", "I am at"+10);
                random_first = new Random().nextInt(21) + 150;
            }
        } else
        {
            Log.d("where I am: ", "I am at"+11);
            random_first = new Random().nextInt(36) + 75;
        }

        for(int i=0; i<=6; i++) {
            random_second = new Random().nextInt(44) + 1;
            Log.d("random number 1", "randomnumber1 "+random_first);
            Log.d("random number 2", "randomnumber2 "+random_second);
            amounts[i]=random_first+random_second;
            final_quote_value_string[i]=String.valueOf(amounts[i]);
        }


        //final_quote_value_string = Integer.toString(final_quote_value);

        //Log.d("quote value:", final_quote_value_string);

        String length = Integer.toString(IMAGES.length);
        String images = Integer.toString(IMAGES[1]);

        Log.d("length of Images array", length);
        Log.d("image number 1", images);

        ListView listView = (ListView) findViewById(R.id.listview);                     // Listview to make Custom Listview

        CustomAdapter customAdapter=new CustomAdapter();                                    // object of Custom Adapter to display a Custom Listview
        listView.setAdapter(customAdapter);

    }

    protected class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return IMAGES.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            view =getLayoutInflater().inflate(R.layout.customlayout, null);

            ImageView image_View = (ImageView) view.findViewById(R.id.imageView);
            TextView textview_company_name = (TextView)view.findViewById(R.id.textView_company_name);
            TextView textview_rate = (TextView)view.findViewById(R.id.textView_rate);
            TextView textView_customer_info = (TextView)view.findViewById(R.id.textView_customer_name);

            image_View.setImageResource(IMAGES[i]);                                         // setting image in Imageview of custom listview
            textview_company_name.setText(NAMES[i]);                                        // setting name in textview of custom listview
            textview_rate.setText("$"+final_quote_value_string[i]);                         // setting amounts in textview of final qoute for particular company
            textView_customer_info.setText("For "+FirstName+" "+LastName+" with age of "+age+" will be driving "+Annual_Milage+" Miles");


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(display_Companies.this, view_coverages.class);     // sending values to next activity
                    intent.putExtra("Postal_Code", Postal_Code);
                    intent.putExtra("Images", IMAGES[i]);
                    intent.putExtra("Names", NAMES[i]);
                    intent.putExtra("Quote_values", final_quote_value_string[i]);
                    intent.putExtra("company_number", i);
                    startActivity(intent);
                }
            });

            return view;
        }
    }
}
