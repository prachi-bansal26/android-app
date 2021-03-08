package com.example.myproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;


public class myAdapter extends RecyclerView.Adapter<myAdapter.myViewHolder> {
    private String[] data;
    private Integer[] imgdata;
    private String[] prices;
    private String[] description;
    Context context;

    public myAdapter(String[] data, Integer[] imgdata, String[] prices, String[] description) {
        this.data = data;
        this.imgdata = imgdata;
        this.prices = prices;
        this.description = description;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_products, parent, false);
        return new myViewHolder(view);
    }
    //Binding data with the Holder and setting text in TextView and imgResource in ImageView
    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        String title = data[position];
        Integer imgStr = imgdata[position];
        String price = "$"+prices[position];
        String desc = description[position];

        holder.txtTitle.setText(title);
        holder.txtPrice.setText(price);
        holder.txtDesc.setText(desc);
        holder.imgIcon.setImageResource(imgStr);
        holder.imgIcon.setTag(imgStr);
    }
    //counting items in array
    @Override
    public int getItemCount() {
        return data.length;
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        ImageView imgIcon;
        TextView txtTitle, txtPrice, txtDesc;
        RatingBar prodRating;
        Button buyBtn;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            imgIcon = (ImageView) itemView.findViewById(R.id.imgIcon);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);
            txtDesc = (TextView) itemView.findViewById(R.id.txtDesc);
            buyBtn = (Button) itemView.findViewById(R.id.buyNowBtn);

            context = itemView.getContext();
            //On buy Button click, loading another intent and passing data to Intent so that we can show it on our layout.
            buyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int itemPosition = getLayoutPosition();
                    Intent intent = new Intent(context, ProductDisplay.class);
                    intent.putExtra("name", txtTitle.getText().toString());
                    intent.putExtra("img", (Integer)imgIcon.getTag());
                    intent.putExtra("price", txtPrice.getText().toString());
                    intent.putExtra("desc", txtDesc.getText().toString());
                    context.startActivity(intent);

                }
            });
        }
    }
}
