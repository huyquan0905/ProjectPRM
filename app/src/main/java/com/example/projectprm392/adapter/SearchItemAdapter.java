package com.example.projectprm392.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.projectprm392.R;
import com.example.projectprm392.model.Item;

import java.util.List;

public class SearchItemAdapter extends RecyclerView.Adapter<SearchItemAdapter.SearchItemViewHolder> {

    private List<Item> itemList;
    private Context context;
    private int selectedPosition = RecyclerView.NO_POSITION;

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

        holder.itemView.setSelected(position == selectedPosition);

        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = holder.getAdapterPosition(); // Lấy vị trí mục đã chọn
                if (itemPosition != RecyclerView.NO_POSITION) {
                    if (onAddButtonClickListener != null) {
                        onAddButtonClickListener.onAddButtonClick(itemPosition);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
    public int getSelectedPosition() {
        return selectedPosition;
    }
    public Item getSelectedItem() {
        if (selectedPosition != RecyclerView.NO_POSITION) {
            return itemList.get(selectedPosition);
        }
        return null;
    }
    public class SearchItemViewHolder extends RecyclerView.ViewHolder {
        private TextView itemNameTextView;
        private TextView itemPriceTextView;
        private ImageView itemFoodImage;
        private Button addButton;

        public SearchItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.foodNameTextView);
            itemPriceTextView = itemView.findViewById(R.id.PriceTextView);
            itemFoodImage = itemView.findViewById(R.id.foodImageView);
            addButton = itemView.findViewById(R.id.addButton);
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
        notifyDataSetChanged();
    }

    public List<Item> getItemList() {
        return itemList;
    }
    public void setSelectedPosition(int position) {
        if (selectedPosition != position) {
            int previousSelected = selectedPosition;
            selectedPosition = position;
            notifyItemChanged(previousSelected);
            notifyItemChanged(selectedPosition);
        }
    }

    public interface OnAddButtonClickListener {
        void onAddButtonClick(int position);
    }

    private OnAddButtonClickListener onAddButtonClickListener;

    public void setOnAddButtonClickListener(OnAddButtonClickListener listener) {
        this.onAddButtonClickListener = listener;
    }


}
