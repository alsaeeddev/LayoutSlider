package com.alsaeed.imageslider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class LayoutSliderAdapter extends RecyclerView.Adapter<LayoutSliderAdapter.ViewHolder> {

    private int[] images;
    private String[] titles;
    private String[] descriptions;
    private int[] colors;

    public LayoutSliderAdapter(int[] images, String[] titles, String[] descriptions) {
        this.images = images;
        this.titles = titles;
        this.descriptions = descriptions;
        colors = new int[]{R.color.col1,R.color.col2,R.color.col3};
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.rootItem.setBackgroundColor(ContextCompat.getColor(holder.rootItem.getContext(),colors[position]));
        holder.imageView.setImageResource(images[position]);
        holder.titleTextView.setText(titles[position]);
        holder.descriptionTextView.setText(descriptions[position]);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout rootItem;
        ImageView imageView;
        TextView titleTextView;
        TextView descriptionTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rootItem = itemView.findViewById(R.id.rootItem);
            imageView = itemView.findViewById(R.id.imageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        }
    }
}
