package com.example.projectprm392.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprm392.R;
import com.example.projectprm392.model.Order;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<Order> cartItemList;

    public CartAdapter(List<Order> cartItemList) {
        this.cartItemList = cartItemList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
//        CartItem cartItem = cartItemList.get(position);
        // Cập nhật giao diện người dùng để hiển thị thông tin mặt hàng trong giỏ hàng
        // Ví dụ: holder.foodNameTextView.setText(cartItem.getName());
        // Ví dụ: holder.priceTextView.setText("$ " + cartItem.getPrice());
        // Ví dụ: holder.quantityTextView.setText(String.valueOf(cartItem.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        // Khai báo các thành phần giao diện (TextViews, ImageViews) ở đây
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            // Ánh xạ và thiết lập các thành phần giao diện
        }
    }
}

