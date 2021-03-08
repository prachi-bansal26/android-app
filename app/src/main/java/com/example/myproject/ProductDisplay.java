package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductDisplay extends AppCompatActivity {
    ImageView imgv;
    TextView textViewName, textViewPrice, textViewDesc;
    Button checkoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_display);

        imgv = (ImageView)findViewById(R.id.imgIconSingle);
        textViewName = (TextView)findViewById(R.id.textView2);
        textViewPrice = (TextView)findViewById(R.id.textViewPrice);
        textViewDesc = (TextView)findViewById(R.id.tvDesc);
        checkoutBtn = (Button)findViewById(R.id.checkoutBtn);
        Intent callingIntent = getIntent();

        if(callingIntent != null) {
            String prodName = callingIntent.getStringExtra("name");
            Integer img = callingIntent.getIntExtra("img", 1);
            String prodPrice = callingIntent.getStringExtra("price");
            String prodDesc = callingIntent.getStringExtra("desc");
            textViewName.setText(prodName);
            textViewPrice.setText(prodPrice);
            textViewDesc.setText(prodDesc);
            imgv.setImageResource(img);

        }
        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductDisplay.this, CheckoutForm.class);
                intent.putExtra("productName", textViewName.getText().toString());
                intent.putExtra("productPrice", textViewPrice.getText().toString());
                startActivity(intent);
            }
        });
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
}