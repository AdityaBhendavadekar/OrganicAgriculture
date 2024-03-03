package com.example.organicagriculture;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    Context context;
    ArrayList<Model> data;

    public ProductAdapter(Context context,ArrayList<Model> data) {
        this.context=context;
        this.data = data;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
//        View view = inflater.inflate(R.layout.productlayout,parent,false);
//        return new ProductViewHolder(view);

        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.productlayout,parent,false);
        return new ProductViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.name.setText(data.get(position).getProductName());
        holder.price.setText(data.get(position).getPrice());
        holder.img.setImageResource(data.get(position).getImgname());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String product = data.get(position).getProductName();
                Intent intent= new Intent(view.getContext(), ProductDetails.class);
                intent.putExtra("product",product);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
}
