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
import com.example.projectprm392.adapter.SearchItemAdapter;
import com.example.projectprm392.model.Item;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    private RecyclerView recyclerView;
    private SearchItemAdapter searchItemAdapter;
    private List<Item> originalItemList;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        recyclerView = view.findViewById(R.id.MenuSearch);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Khởi tạo SearchItemAdapter cho fragment tìm kiếm nếu chưa tồn tại
        if (searchItemAdapter == null) {
            searchItemAdapter = new SearchItemAdapter(getActivity(), new ArrayList<>());
            recyclerView.setAdapter(searchItemAdapter);
        }

        // Sử dụng một background thread để lấy danh sách mục sản phẩm từ cơ sở dữ liệu
        new Thread(new Runnable() {
            @Override
            public void run() {
                originalItemList = ItemDatabase.getInstance(getActivity()).itemDAO().getAllItems();

                // Sau khi lấy dữ liệu, cập nhật RecyclerView trên luồng giao diện
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Cập nhật itemList của SearchItemAdapter
                        searchItemAdapter.setItemList(originalItemList);
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

    private void performSearch(String query) {
        if (searchItemAdapter != null) {
            List<Item> searchResults = searchItems(query);
            searchItemAdapter.setItemList(searchResults);
        }
    }

    private List<Item> searchItems(String keyword) {
        List<Item> searchResults = new ArrayList<>();
        if (originalItemList != null) {
            for (Item item : originalItemList) {
                if (item.getName().toLowerCase().contains(keyword.toLowerCase())) {
                    searchResults.add(item);
                }
            }
        }
        return searchResults;
    }
}
