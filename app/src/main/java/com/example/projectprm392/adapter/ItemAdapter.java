package com.example.projectprm392.adapter;

import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectprm392.Database.ItemDatabase;
import com.example.projectprm392.R;
import com.example.projectprm392.model.Item;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    public List<Item> itemList;
    private OnDeleteItemClickListener onDeleteItemClickListener;

    public interface OnDeleteItemClickListener {
        void onDeleteItemClick(Item item);
    }

    public ItemAdapter(List<Item> itemList, OnDeleteItemClickListener onDeleteItemClickListener) {
        this.itemList = itemList;
        this.onDeleteItemClickListener = onDeleteItemClickListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.bind(item);
        Log.d("MyApp", "Danh sách các mục sản phẩm:");
        for (Item items : itemList) {
            Log.d("MyApp", "Tên: " + item.getName());
            Log.d("MyApp", "Giá: " + item.getPrice());
            Log.d("MyApp", "Đường dẫn hình ảnh: " + item.getImagePath());
        }
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = holder.getAdapterPosition();
                if (itemPosition != RecyclerView.NO_POSITION) {
                    Item itemToDelete = itemList.get(itemPosition);

                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            // xóa mục sản phẩm từ cơ sở dữ liệu trong luồng nền
                            ItemDatabase.getInstance(v.getContext()).itemDAO().deleteItem(itemToDelete);
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            // Cập nhật danh sách bằng cách loại bỏ mục sản phẩm đã xóa
                            itemList.remove(itemPosition);
                            // Thông báo cho RecyclerView rằng mục sản phẩm đã bị xóa
                            notifyItemRemoved(itemPosition);
                        }
                    }.execute();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public View deleteButton;
        private TextView itemNameTextView;
        private TextView itemPriceTextView;
        private ImageView itemFoodImage;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.foodNameTextView);
            itemPriceTextView = itemView.findViewById(R.id.PriceTextView);
            itemFoodImage = itemView.findViewById(R.id.foodImageView);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }

        public void bind(Item item) {
            itemNameTextView.setText(item.getName());
            itemPriceTextView.setText("$" + item.getPrice());
            Glide.with(itemFoodImage.getContext())
                    .load(item.getImagePath())
                    .into(itemFoodImage);
        }
    }

}
