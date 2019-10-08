package com.ankit_pro.autoquotecompare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

public class view_coverages extends AppCompatActivity {

    public int IMAGE, Company_Name;
    public String Name, Postal_Code, Quote_Value;
    TextView number_of_vehicles, collision_amount, comprehensive_amount, amount_Per_Person, amount_Per_Accident, amount_Per_Accident_Pro_damage, amount_For_Medical_Payment;
    TextView amount_Motorist_Per_Person, amount_Motorist_Per_Accident, vehicles_Include_IN_Rental;
    TextView show_Name, show_PostalCode, show_Quote_Value;
    ImageView view_Selected_Image ;
    Button button;
    public String link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_coverages);

        view_Selected_Image = (ImageView)findViewById(R.id.view_selected_image);
        number_of_vehicles = (TextView)findViewById(R.id.number_of_vehicles_text);
        collision_amount = (TextView)findViewById(R.id.amount_deductible);
        comprehensive_amount = (TextView)findViewById(R.id.amount_deductible_comprehensive);
        amount_Per_Person = (TextView)findViewById(R.id.amount_per_person);
        amount_Per_Accident = (TextView)findViewById(R.id.amount_per_accident);
        amount_Per_Accident_Pro_damage = (TextView)findViewById(R.id.amount_per_accident_1);
        amount_For_Medical_Payment = (TextView)findViewById(R.id.amount_for_medical_payment);
        amount_Motorist_Per_Person = (TextView)findViewById(R.id.amount_motorist_per_person);
        amount_Motorist_Per_Accident = (TextView)findViewById(R.id.amount_motorist_per_accident);
        vehicles_Include_IN_Rental = (TextView)findViewById(R.id.vehicles_included_number);
        show_Name = (TextView)findViewById(R.id.show_name);
        show_PostalCode = (TextView)findViewById(R.id.show_postalcode);
        show_Quote_Value = (TextView)findViewById(R.id.show_final_quote);
        button = (Button) findViewById(R.id.MyButton);
        number_of_vehicles.setText("1 of 1 vehicle included");                                          // setting values for number of vehicles and deductibles for comprehensive and collision
        collision_amount.setText("$500 deductible");
        comprehensive_amount.setText("$500 deductible");
        amount_Per_Person.setText("$1,00,000 Per Person");
        amount_Per_Accident.setText("$3,00,000 Per Accident");
        amount_Per_Accident_Pro_damage.setText("$1,00,000 Per Accident");
        amount_For_Medical_Payment.setText("$5,000 Per Person");
        amount_Motorist_Per_Person.setText("$1,00,000 Per Person");
        amount_Motorist_Per_Accident.setText("$3,00,000 Per Accident");
        vehicles_Include_IN_Rental.setText("0 of 1 vehicles included");

        Bundle extras = getIntent().getExtras();

        IMAGE = extras.getInt("Images");
        Name = extras.getString("Names");
        Postal_Code = extras.getString("Postal_Code");
        Quote_Value = extras.getString("Quote_values");
        Company_Name = extras.getInt("company_number");
        Log.d("Company number:::::", "company_number:::"+Company_Name);
        Log.d("IMAGE:", "image number:"+IMAGE);
        Log.d("NAME::::", "Name::::::"+Name);


        view_Selected_Image.setImageResource(IMAGE);                                       // showing image on imageview
        show_Name.setText(Name);
        show_PostalCode.setText(Postal_Code);
        show_Quote_Value.setText("$"+Quote_Value);

        switch(Name) {                                                    // create link according to selected insurance company
            case "American Family":
                link = "https://www.amfam.com/";
                break;
            case "Progressive":
                link = "https://www.progressive.com/home/home/";
                break;
            case "Statefarm":
                link = "https://www.statefarm.com/";
                break;
            case "Liberty Mutual":
                link = "https://www.libertymutual.com/";
                break;
            case "AllState":
                link = "https://www.allstate.com/";
                break;
            case "GEICO":
                link = "https://www.geico.com/";
                break;
            case "USAA":
                link = "https://www.usaa.com/";
                break;
        }


        button.setOnClickListener(new View.OnClickListener() {                                  // onclick event on button
            @Override
            public void onClick(View view) {

                Log.d("link:::::", "link is :::::::::"+link);
                Intent intent1 = new Intent(view_coverages.this, insuranceautowebview.class);      // button click intent to webview activity
                intent1.putExtra("url", link);                                           // sending link to webview activity
                startActivity(intent1);
            }
        });



    }
}
