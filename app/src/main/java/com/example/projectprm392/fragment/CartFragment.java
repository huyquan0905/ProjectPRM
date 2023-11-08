package com.example.projectprm392.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprm392.R;
import com.example.projectprm392.adapter.HomeItemAdapter;
import com.example.projectprm392.model.Item;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {
    private RecyclerView cartRecyclerView;
    private HomeItemAdapter homeItemAdapter;

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        cartRecyclerView = view.findViewById(R.id.cartRecyclerView);

        homeItemAdapter = new HomeItemAdapter(requireContext(), new ArrayList<>());

        // Lấy danh sách đã chọn từ Bundle và cập nhật danh sách giỏ hàng
        Bundle args = getArguments();
        if (args != null) {
            ArrayList<Parcelable> parcelableList = args.getParcelableArrayList("selectedItems");
            if (parcelableList != null) {
                List<Item> selectedItems = new ArrayList<>();
                for (Parcelable parcelable : parcelableList) {
                    if (parcelable instanceof Item) {
                        selectedItems.add((Item) parcelable);
                    }
                }

                homeItemAdapter.setItemList(selectedItems);
            }
        }

        cartRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        cartRecyclerView.setAdapter(homeItemAdapter);

        return view;
    }
}
