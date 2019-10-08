package com.ankit_pro.autoquotecompare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GetInformation extends AppCompatActivity {

    private Spinner carYear;                                                        // created variables
    private Spinner carMake, marital_status;
    private EditText carModel, annual_milage, firstName, lastName, BirthDate;
    private RadioGroup radioOwnorLeaseGroup;
    private RadioButton radioOwnorLeaseButton;
    private RadioGroup radioMaleorFemaleGroup;
    private RadioButton radioMaleorFemalButton;
    private Button get_quote;
    public int Age;
    public String car_Year, car_Make, Marital_Status, Postal_Code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_information);

        Bundle extras= getIntent().getExtras();                             // getting value of Postal Code from MainActivity's Intent
        Postal_Code = extras.getString("PostalCode");

        radioOwnorLeaseGroup = (RadioGroup) findViewById(R.id.radioOwnorLease);             // Linking input attributes to particular varibles
        radioMaleorFemaleGroup = (RadioGroup) findViewById(R.id.radioSex);
        carYear = (Spinner)findViewById(R.id.car_year);
        carYear.requestFocus();
        carMake = (Spinner)findViewById(R.id.car_make);
        marital_status = (Spinner) findViewById(R.id.marital_status);
        carModel = (EditText) findViewById(R.id.car_model);
        annual_milage = (EditText) findViewById(R.id.annual_milage);
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        BirthDate = (EditText) findViewById(R.id.birth_date);
        get_quote = (Button) findViewById(R.id.get_quote);

        get_quote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                                            // on Button Onclick event

                int selectOwnoeLeaseId = radioOwnorLeaseGroup.getCheckedRadioButtonId();    // getting values which are entered by user in input form
                radioOwnorLeaseButton = (RadioButton) findViewById(selectOwnoeLeaseId);
                String radio_Own_or_Lease = radioOwnorLeaseButton.getText().toString();
                int selectMaleorFemaleId = radioMaleorFemaleGroup.getCheckedRadioButtonId();
                radioMaleorFemalButton = (RadioButton) findViewById(selectMaleorFemaleId);
                String radio_Male_or_Female = radioMaleorFemalButton.getText().toString();

                String Car_Model = carModel.getText().toString();
                int Annual_Milage = Integer.parseInt(annual_milage.getText().toString());
                String first_Name = firstName.getText().toString();
                String last_Name = lastName.getText().toString();
                String Birth_Date = BirthDate.getText().toString();
                car_Year = carYear.getSelectedItem().toString();
                car_Make= carMake.getSelectedItem().toString();
                Marital_Status = marital_status.getSelectedItem().toString();

                if(Car_Model.isEmpty()){                                                    // Setting validation to make sure values are not null
                    carModel.setError("Enter Car Model");
                } else if(Annual_Milage==0){                                                // Setting validation to make sure values are not null
                    annual_milage.setError("Enter your expected Annual Milage");
                } else if(first_Name.isEmpty()){                                            // Setting validation to make sure values are not null
                    firstName.setError("Enter First Name");
                } else if(last_Name.isEmpty()){                                             // Setting validation to make sure values are not null
                    lastName.setError("Enter Last Name");
                } else if(Birth_Date.isEmpty()) {                                           // Setting validation to make sure values are not null
                    BirthDate.setError("Enter Birthdate");
                } else if(car_Year.isEmpty() || car_Year.equals("Choose Year")){                        // Setting validation to make sure values are not null
                    Toast.makeText(GetInformation.this,"Please Choose Year", Toast.LENGTH_SHORT).show();            // Show Toast message if user don't select anything in dropdown
                } else if(car_Make.isEmpty() || car_Make.equals("Choose Company")){                                // Setting validation to make sure values are not null
                    Toast.makeText(GetInformation.this,"Please Choose Company", Toast.LENGTH_SHORT).show();         // Show Toast message if user don't select anything in dropdown
                }else if(Marital_Status.isEmpty() || Marital_Status.equals("Choose Marital Status")){                   // Setting validation to make sure values are not null
                    Toast.makeText(GetInformation.this,"Please Choose Marital Status", Toast.LENGTH_SHORT).show();          // Show Toast message if user don't select anything in dropdown
                }
                else{
                    Age=getAge(Birth_Date);                                     // Calling getAge() method to get Age from Date of Birth

//                    Log.d("Postal Code:",Postal_Code);
//                    Log.d("car model:", Car_Model);
//                    Log.d("annual milage", "annual millage"+Annual_Milage);
//                    Log.d("First Name", first_Name);
//                    Log.d("Last Name", last_Name);
//                    Log.d("Birth Date", Birth_Date);
//                    Log.d("Car Year", car_Year);
//                    Log.d("Car Make", car_Make);
//                    Log.d("Marital Status", Marital_Status);
//                    Log.d("Own or Lease", radio_Own_or_Lease);
//                    Log.d("Male or Female", radio_Male_or_Female);
//                    Log.d("age", "age:"+Age);


                    Intent intent = new Intent(GetInformation.this, display_Companies.class);   // If user have entered all the values correct then send user to next page which is showing quote
                    intent.putExtra("Postal_Code", Postal_Code);                                // sending values to display_companies page
                    intent.putExtra("Car_Model", Car_Model);
                    intent.putExtra("Annual_Milage", Annual_Milage);
                    intent.putExtra("FirstName", first_Name);
                    intent.putExtra("LastName", last_Name);
                    intent.putExtra("BirthDate", Birth_Date);
                    intent.putExtra("CarYear", car_Year);
                    intent.putExtra("CarMake", car_Make);
                    intent.putExtra("MaritalStatus", Marital_Status);
                    intent.putExtra("age", Age);
                    startActivity(intent);
                }



            }
        });

        List<String> Years =new ArrayList<>();                                  // Creating Arraylist for dropdown for List of Car Year
        Years.add(0, "Choose Year");
        Years.add("2020");
        Years.add("2019");
        Years.add("2018");
        Years.add("2017");
        Years.add("2016");
        Years.add("2015");
        Years.add("2014");
        Years.add("2013");
        Years.add("2012");
        Years.add("2011");
        Years.add("2010");
        Years.add("2009");
        Years.add("2008");
        Years.add("2007");
        Years.add("2006");
        Years.add("2005");
        Years.add("2004");
        Years.add("2003");
        Years.add("2002");
        Years.add("2001");
        Years.add("2000");
        Years.add("1999");
        Years.add("1998");
        Years.add("1997");
        Years.add("1996");
        Years.add("1995");
        Years.add("1994");
        Years.add("1993");
        Years.add("1992");
        Years.add("1991");
        Years.add("1990");
        Years.add("1989");
        Years.add("1988");
        Years.add("1987");
        Years.add("1986");
        Years.add("1985");
        Years.add("1984");
        Years.add("1983");
        Years.add("1982");
        Years.add("1981");
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(GetInformation.this, android.R.layout.simple_list_item_1, Years);         // created object of ArrayAdapter to make drop down
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carYear.setAdapter(myAdapter);                                                                      // integrated object of array adapter in spinner
        //carYear.setPrompt("Select Car Year");
        carYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {                        // onselect event for car year spinner
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("Choose Year")) {                               // It will not do anything if user select "Choose Year", and as per code above it will show Toast Notification
//                    Toast.makeText(GetInformation.this, "Please Choose Car Year", Toast.LENGTH_SHORT).show();
//                    carYear.requestFocus();
                }
                else{
                    car_Year=adapterView.getItemAtPosition(i).toString();                                       // Getting selected value in car_Year variable
                    //Toast.makeText(GetInformation.this, car_Year, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {                                         // When user don't select anything
                Toast.makeText(GetInformation.this, "Please Choose Car Year", Toast.LENGTH_SHORT).show();

            }
        });

        List<String> list_carMake = new ArrayList<>();                                                  // Creating ArrayList for Car Company drop down
        list_carMake.add(0, "Choose Company");
        list_carMake.add( "ACURA");
        list_carMake.add( "AUDI");
        list_carMake.add( "BMW");
        list_carMake.add( "BIOCK");
        list_carMake.add( "CADILLAC");
        list_carMake.add( "CHEVROLET");
        list_carMake.add( "CHRYSLER");
        list_carMake.add( "DODGE");
        list_carMake.add( "FORD");
        list_carMake.add( "GLOBAL ELECTRIC MOTORS");
        list_carMake.add( "GMC");
        list_carMake.add( "HONDA");
        list_carMake.add( "HUMMER");
        list_carMake.add( "HYUNDAI");
        list_carMake.add( "INFINITI");
        list_carMake.add( "ISUZU");
        list_carMake.add( "JAGUAR");
        list_carMake.add( "JEEP");
        list_carMake.add( "KIA");
        list_carMake.add( "LAND ROVER");
        list_carMake.add( "LEXUS");
        list_carMake.add( "LINCOLN");
        list_carMake.add( "MAZDA");
        list_carMake.add( "MERCEDES-BENZ");
        list_carMake.add( "MERCURY");
        list_carMake.add( "MINI");
        list_carMake.add( "MITSUBISHI");
        list_carMake.add( "NISSAN");
        list_carMake.add( "PONTIAC");
        list_carMake.add( "PORSCHE");
        list_carMake.add( "SAAB");
        list_carMake.add( "SATURN");
        list_carMake.add( "SUBARU");
        list_carMake.add( "SUZUKI");
        list_carMake.add( "TOYOTA");
        list_carMake.add( "VOLKSWAGEN");
        list_carMake.add( "VOLVO");


        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(GetInformation.this, android.R.layout.simple_list_item_1, list_carMake);                                 // making object of ArrayAdapter for car Company
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carMake.setAdapter(myAdapter2);
//        carMake.setPrompt("Select Car Company");

        carMake.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {                                                    // onselect event for carcompany spinner
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("Choose Company")) {                                            // If user select "Choose Company", it won't do anything, just shows Toast Notification as per above validation
//                    Toast.makeText(GetInformation.this, "Please Choose Car Year", Toast.LENGTH_SHORT).show();
//                    carYear.requestFocus();
                }
                else{
                    car_Make=adapterView.getItemAtPosition(i).toString();                                               // Getting selected value in car_Make variable
                   // Toast.makeText(GetInformation.this, car_Make, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {                                                     // When user don't select anything
                Toast.makeText(GetInformation.this, "Please Choose Company", Toast.LENGTH_SHORT).show();


            }
        });


        List<String> list_marital_status = new ArrayList<>();                                                               // Making object of ArrayList for Marital Status Drop down
        list_marital_status.add(0, "Choose Marital Status");
        list_marital_status.add( "Married");
        list_marital_status.add( "Single (never married)");
        list_marital_status.add( "Divorced");
        list_marital_status.add( "Widowed");
        list_marital_status.add( "Seperated");

        ArrayAdapter<String> myAdapter3 = new ArrayAdapter<String>(GetInformation.this, android.R.layout.simple_list_item_1, list_marital_status);                          // ArrayAdapter's object for Marital Status Dropdown
        myAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        marital_status.setAdapter(myAdapter3);
        //marital_status.setPrompt("Select Marital Status");

         marital_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {                            // onselect event for marital status spinner
             @Override
             public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 if (adapterView.getItemAtPosition(i).equals("Choose Marital Status")) {
//                    Toast.makeText(GetInformation.this, "Please Choose Car Year", Toast.LENGTH_SHORT).show();
//                    carYear.requestFocus();
                 }
                 else{
                     Marital_Status=adapterView.getItemAtPosition(i).toString();
                    // Toast.makeText(GetInformation.this, Marital_Status, Toast.LENGTH_SHORT).show();
                 }
             }

             @Override
             public void onNothingSelected(AdapterView<?> adapterView) {
                 Toast.makeText(GetInformation.this, "Please Choose Marital Status", Toast.LENGTH_SHORT).show();
             }
         });

    }

    private int getAge(String Birth_Date){                                                        // method which calculates age from date of birth

        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("MM/DD/YYYY");
        try {
            date = sdf.parse(Birth_Date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(date == null) return 0;

        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.setTime(date);

        int year = dob.get(Calendar.YEAR);
        int month = dob.get(Calendar.MONTH);
        int day = dob.get(Calendar.DAY_OF_MONTH);

        dob.set(year, month+1, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }
        return age;
    }

}
