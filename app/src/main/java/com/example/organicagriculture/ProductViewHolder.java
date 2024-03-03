package com.example.organicagriculture;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductViewHolder extends RecyclerView.ViewHolder
{
    ImageView img;
    TextView name,price;
    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        img = (ImageView) itemView.findViewById(R.id.image);
        name = (TextView) itemView.findViewById(R.id.label);
        price = (TextView) itemView.findViewById(R.id.price);
    }
}
