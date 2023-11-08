package com.example.projectprm392.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectprm392.R;
import com.example.projectprm392.model.Item;

import java.util.ArrayList;
import java.util.List;

public class HomeItemAdapter extends RecyclerView.Adapter<HomeItemAdapter.ViewHolder> {
    private List<Item> itemList;
    private List<Item> selectedItems;

    private Context context;
    private OnAddButtonClickListener onAddButtonClickListener;;

    public HomeItemAdapter(Context context ,List<Item> itemList ) {
        this.itemList = itemList;
        this.context = context;
        this.selectedItems = new ArrayList<>();
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public List<Item> getSelectedItems() {
        return selectedItems;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    public interface OnAddButtonClickListener {
        void onAddButtonClick(int position);
    }

    public void setOnAddButtonClickListener(OnAddButtonClickListener listener) {
        this.onAddButtonClickListener = listener;
    }
    public int getSelectedPosition() {
        return -1;
    }

    public void addItemToSelectedList(Item item) {
        selectedItems.add(item);
    }

    public void addItem(Item item) {
        selectedItems.add(item);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.bind(item, context);

        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onAddButtonClickListener != null) {
                    onAddButtonClickListener.onAddButtonClick(holder.getAdapterPosition());
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemNameTextView;
        public TextView itemPriceTextView;
        private ImageView itemFoodImage;
        private Button addButton;

        public ViewHolder(View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.foodNameTextView);
            itemPriceTextView = itemView.findViewById(R.id.PriceTextView);
            itemFoodImage = itemView.findViewById(R.id.foodImageView);
            addButton = itemView.findViewById(R.id.addButton);
        }

        public void bind(Item item, Context context) {
            itemNameTextView.setText(item.getName());
            itemPriceTextView.setText("$" + item.getPrice());
            Glide.with(context)
                    .load(item.getImagePath())
                    .into(itemFoodImage);
        }

    }
}
