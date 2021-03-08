package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Products extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prod);

        RecyclerView myNewList = (RecyclerView)findViewById(R.id.myNewList);

        myNewList.setLayoutManager(new LinearLayoutManager(this));
        //defining products data
        String[] CosmeticNames = {"Olay Regenerist Micro-Sculpting Cream", "Maybelline Super Stay Matte Lipstick", "Neutrogena Hydro Boost Sleeping Mask", "Plum Green Tea Pore Cleansing Face Wash", "The Face Shop Chia Seed Hydro Cream", "The Face Shop Real Nature Face Mask"};
        Integer[] CosmeticImages = {R.drawable.product1, R.drawable.product2, R.drawable.product3, R.drawable.product4, R.drawable.product5, R.drawable.product6};
        //Integer[] ProductRatings = {4, 5, 2, 4, 3, 4};
        String[] CosmeticPrices = {"15.99", "16.92", "12.55", "15.44", "13.99", "$7.99"};
        String[] CosmeticDesc = {"Olay Regenerist Micro-Sculpting Cream hydrates to improve elasticity and firm skin for a lifted look. Supercharged with skin-plumping Amino-Peptide Complex II, this cream moisturizer smoothes and corrects the look of fine lines and wrinkles.",
        "SuperStay Matte Ink is a liquid lipstick that gives you a flawless matte finish in a range of super saturated shades.Ink your lips in up to 16 HR saturated liquid matte. This long lasting lipstick features a unique arrow applicator for precise application.",
        "This overnight face mask helps diminish the signs of dryness by wrapping skin in a deep moisture gel cream while you sleep. The hydrogel formula contains hyaluronic acid, a compound found naturally in skin, and provides skin with the moisture it needs.",
        "Plum Green Tea Pore Cleansing Face Wash offers a clear and flawless looking skin. It is ideally designed for people with oily and acne prone skin. The gentle formula lathers well and gives you a refreshed feel.",
        "Formulated with Chia Seed and Pink Vitamin for intense, upgraded hydrating care compared to original Chia Seed line. This is a revamped Chia Seed Cream with added Vitamin B12 that delivers instant nourishment to exhausted and damaged skin. ",
        "The Face Shop Real Nature Face Mask is created by fresh natural plants, the different types of natural plants mildly take care of healthy skin everyday. The masks keep skin moisturized for long time with its excellent adherence. 20 options available."};

        myNewList.setAdapter(new myAdapter(CosmeticNames, CosmeticImages, CosmeticPrices, CosmeticDesc));
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