package com.phimhay.gioithieuphim.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.phimhay.gioithieuphim.Domain.GenresItem;
import com.phimhay.gioithieuphim.R;

import java.util.ArrayList;

public class CategoryListApdater extends RecyclerView.Adapter<CategoryListApdater.ViewHolder> {
    ArrayList<GenresItem> items;
    Context context;

    public CategoryListApdater(ArrayList<GenresItem> items) {
        this.items = items;
    }



    @NonNull
    @Override
    public CategoryListApdater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryListApdater.ViewHolder holder, int position) {
        holder.titleTxt.setText(items.get(position).getName());


        holder.itemView.setOnClickListener(v -> {

        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleTxt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt=itemView.findViewById(R.id.TitleTxt);
        }
    }
}
