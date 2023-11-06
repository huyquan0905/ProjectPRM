package com.example.projectprm392.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprm392.Database.ItemDatabase;
import com.example.projectprm392.R;
import com.example.projectprm392.adapter.ItemAdapter;
import com.example.projectprm392.model.Item;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private List<Item> originalItemList; // Danh sách mục sản phẩm gốc

    public SearchFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        recyclerView = view.findViewById(R.id.MenuSearch);

        // Sử dụng một background thread để lấy danh sách mục sản phẩm từ cơ sở dữ liệu
        new Thread(new Runnable() {
            @Override
            public void run() {
                originalItemList = ItemDatabase.getInstance(getActivity()).itemDAO().getAllItems();

                // Sau khi lấy dữ liệu, cập nhật RecyclerView trên luồng giao diện
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Khởi tạo và gán itemList cho itemAdapter
                        itemAdapter = new ItemAdapter(originalItemList, null);
                        recyclerView.setAdapter(itemAdapter);

                        // Thiết lập LayoutManager
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        recyclerView.setLayoutManager(layoutManager);
                    }
                });
            }
        }).start();

        // Tìm kiếm theo từ khóa khi người dùng thay đổi SearchView
        SearchView searchView = view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                performSearch(newText);
                return true;
            }
        });

        return view;
    }

    // Phương thức thực hiện tìm kiếm và cập nhật RecyclerView
    private void performSearch(String query) {
        List<Item> searchResults = searchItems(query);
        if (recyclerView != null) {
            itemAdapter = new ItemAdapter(searchResults, null);
            recyclerView.setAdapter(itemAdapter);
            itemAdapter.notifyDataSetChanged();
        }
    }

    // Phương thức tìm kiếm mục sản phẩm dựa trên từ khóa
    private List<Item> searchItems(String keyword) {
        List<Item> searchResults = new ArrayList<>();
        for (Item item : originalItemList) {
            if (item.getName().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(item);
            }
        }
        return searchResults;
    }
}
