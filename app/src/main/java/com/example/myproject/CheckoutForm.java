package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CheckoutForm extends AppCompatActivity {
    DatabaseHelper myDb;
    LinearLayout cardNumberLayout, cardExpiryLayout, cardCVVLayout;
    EditText editName, editEmail, editPhone, editAddress, editProductName, editQuantity, editAmount, editCardNumber, editCardExpiry, editCardCVV;
    RadioGroup editPaymentMode;
    RadioButton paymentModeRadio;
    Button buttonBuy, buttonReset;
    String prodName, prodPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_form);
        cardNumberLayout = findViewById(R.id.cardNumberLayout);
        cardExpiryLayout = findViewById(R.id.cardExpiryLayout);
        cardCVVLayout = findViewById(R.id.cardCVVLayout);

        myDb = new DatabaseHelper(this);
        buttonBuy = findViewById(R.id.buttonBuy);
        buttonReset = findViewById(R.id.buttonReset);
        editPaymentMode = (RadioGroup)findViewById(R.id.editPaymentMode);

        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editPhone = findViewById(R.id.editPhone);
        editAddress = findViewById(R.id.editAddress);
        editProductName = findViewById(R.id.editProductName);
        editQuantity = findViewById(R.id.editQuantity);
        editAmount = findViewById(R.id.editAmount);
        editCardNumber = findViewById(R.id.editCardNumber);
        editCardExpiry = findViewById(R.id.editCardExpiry);
        editCardCVV = findViewById(R.id.editCardCVV);

        int selectedId = editPaymentMode.getCheckedRadioButtonId();
        paymentModeRadio = (RadioButton) findViewById(selectedId);

        //Function called on change of quantity so that amount changes accordingly.
        editQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() != 0) {
                    Integer newQuantity = Integer.parseInt(editQuantity.getText().toString());
                    String existAmt = prodPrice;
                    float amt = Float.parseFloat(existAmt.substring(1));
                    float quan = newQuantity * amt;

                    editAmount.setText("$"+Float.toString(quan));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        Intent callingIntent = getIntent();
        if(callingIntent != null) {
            prodName = callingIntent.getStringExtra("productName");
            prodPrice = callingIntent.getStringExtra("productPrice");
            editProductName.setText(prodName);
            editAmount.setText(prodPrice);
        }

        AddData();
        ClearFormData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.icHome:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.products:
                Intent intent1 = new Intent(this, Products.class);
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //getting which radio is selected so that layout can be shown accordingly.
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButton1:
                if (checked)
                    paymentModeRadio = findViewById(R.id.radioButton1);
                    cardNumberLayout.setVisibility(View.INVISIBLE);
                    cardExpiryLayout.setVisibility(View.INVISIBLE);
                    cardCVVLayout.setVisibility(View.INVISIBLE);
                    break;
            case R.id.radioButton2:
                if (checked)
                    paymentModeRadio = findViewById(R.id.radioButton2);
                    cardNumberLayout.setVisibility(View.VISIBLE);
                    cardExpiryLayout.setVisibility(View.VISIBLE);
                    cardCVVLayout.setVisibility(View.VISIBLE);
                    break;
            case R.id.radioButton3:
                if (checked)
                    paymentModeRadio = findViewById(R.id.radioButton3);
                    cardNumberLayout.setVisibility(View.VISIBLE);
                    cardExpiryLayout.setVisibility(View.VISIBLE);
                    cardCVVLayout.setVisibility(View.VISIBLE);
                    break;
        }
    }
    //to insert data on buy click event
    public void AddData()
    {
        buttonBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = editName.getText().toString();
                String userEmail = editEmail.getText().toString();
                String userPhone = editPhone.getText().toString();
                String userAddress = editAddress.getText().toString();
                String userProduct = editProductName.getText().toString();
                String userQuantity = editQuantity.getText().toString();
                String userPaymentMode = paymentModeRadio.getText().toString();
                String userAmount = editAmount.getText().toString();
                String userCardNumber = editCardNumber.getText().toString();
                String userCardExpiry = editCardExpiry.getText().toString();
                String userCardCVV = editCardCVV.getText().toString();
                //Toast.makeText(CheckoutForm.this, "" + userPaymentMode, Toast.LENGTH_SHORT).show();

                //to validate if the fields are filled or not.
                if(userName.matches("") || userEmail.matches("") || userPhone.matches("") || userAddress.matches("") || userProduct.matches("") || userQuantity.matches("") || userPaymentMode.matches("") || userAmount.matches("") ) {
                    Toast.makeText(CheckoutForm.this, "Please Fill all the fields", Toast.LENGTH_LONG).show();
                }
                //to validate the email
                else if(!userEmail.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                    Toast.makeText(CheckoutForm.this, "Enter a valid Email Address", Toast.LENGTH_SHORT).show();
                }
                //to check if paymentMode is not cash then enter the card details
                else if(!userPaymentMode.matches("Cash") && (userCardNumber.matches("") || userCardExpiry.matches("") || userCardCVV.matches(""))) {
                    Toast.makeText(CheckoutForm.this, "Enter your Card Details", Toast.LENGTH_SHORT).show();
                }
                //to insert the data in the database if every field is validated
                else {
                    if(userPaymentMode.matches("Cash")) {
                        userCardNumber = " ";
                        userCardExpiry = " ";
                        userCardCVV = " ";
                    }
                    boolean isInserted = myDb.insertData(userName, userEmail, userPhone, userAddress, userProduct, userQuantity, userPaymentMode, userAmount,
                            userCardNumber, userCardExpiry, userCardCVV);
                    if (isInserted == true) {
                        Toast.makeText(CheckoutForm.this, "You have successfully placed an Order!!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(CheckoutForm.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(CheckoutForm.this, "Cannot place an Order - try again", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    // to clear the form data
    public  void ClearFormData() {
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editName.setText("");
                editEmail.setText("");
                editPhone.setText("");
                editAddress.setText("");
                editQuantity.setText("");
                editCardNumber.setText("");
                editCardExpiry.setText("");
                editCardCVV.setText("");
                Toast.makeText(CheckoutForm.this, "Form Data Cleared", Toast.LENGTH_SHORT).show();
            }
        });
    }
}