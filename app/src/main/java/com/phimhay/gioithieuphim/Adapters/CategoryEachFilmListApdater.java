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
import java.util.List;

public class CategoryEachFilmListApdater extends RecyclerView.Adapter<CategoryEachFilmListApdater.ViewHolder> {
    List<String> items;
    Context context;

    public CategoryEachFilmListApdater(List<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CategoryEachFilmListApdater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryEachFilmListApdater.ViewHolder holder, int position) {
        holder.titleTxt.setText(items.get(position));

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
