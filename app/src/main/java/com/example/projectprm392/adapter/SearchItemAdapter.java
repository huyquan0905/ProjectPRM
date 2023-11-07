package com.example.projectprm392.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.projectprm392.R;
import com.example.projectprm392.model.Item;

import java.util.List;

public class SearchItemAdapter extends RecyclerView.Adapter<SearchItemAdapter.SearchItemViewHolder> {

    private List<Item> itemList;
    private Context context;

    public SearchItemAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public SearchItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        return new SearchItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchItemViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class SearchItemViewHolder extends RecyclerView.ViewHolder {
        private TextView itemNameTextView;
        private TextView itemPriceTextView;
        private ImageView itemFoodImage;

        public SearchItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.foodNameTextView);
            itemPriceTextView = itemView.findViewById(R.id.PriceTextView);
            itemFoodImage = itemView.findViewById(R.id.foodImageView);
        }

        public void bind(Item item) {
            itemNameTextView.setText(item.getName());
            itemPriceTextView.setText("$" + item.getPrice());
            Glide.with(context)
                    .load(item.getImagePath())
                    .into(itemFoodImage);
        }
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged(); // Notify the adapter that the data has changed
    }
}
