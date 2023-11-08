package com.example.projectprm392.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprm392.Database.ItemDatabase;
import com.example.projectprm392.R;
import com.example.projectprm392.adapter.SearchItemAdapter;
import com.example.projectprm392.adapter.ImageAdapter;
import com.example.projectprm392.model.Item;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private ImageAdapter imageAdapter;

    private SearchItemAdapter searchItemAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.HomeItem);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        searchItemAdapter = new SearchItemAdapter(getActivity(), new ArrayList<>());
        recyclerView.setAdapter(searchItemAdapter);

        new LoadItemsTask().execute();

        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("https://i.pinimg.com/564x/5b/ef/69/5bef6908acefd6d439757f8d701ae1d9.jpg");
        arrayList.add("https://i.pinimg.com/564x/a3/39/39/a339392dd5e5dad1bd6cc81e4aa85a19.jpg");
        arrayList.add("https://i.pinimg.com/564x/a7/46/19/a746197f0f52304405c8404b24e87b85.jpg");
        arrayList.add("https://i.pinimg.com/736x/71/be/38/71be38e661f54b84d359cf5bb4250624.jpg");
        arrayList.add("https://i.pinimg.com/564x/3b/2d/73/3b2d73abb4c375e73cc672087025b508.jpg");
        arrayList.add("https://i.pinimg.com/564x/f4/cc/c1/f4ccc1d592968067b5676be2817edc43.jpg");
        arrayList.add("https://i.pinimg.com/564x/04/b6/f9/04b6f958f5f053fbaefccddd1007aa48.jpg");
        ImageAdapter adapter = new ImageAdapter(requireContext(), arrayList);
        recyclerView.setAdapter(adapter);
        return view;
    }

    private class LoadItemsTask extends AsyncTask<Void, Void, List<Item>> {
        @Override
        protected List<Item> doInBackground(Void... voids) {
            return ItemDatabase.getInstance(getActivity()).itemDAO().getAllItems();
        }

        @Override
        protected void onPostExecute(List<Item> itemList) {
            if (itemList.isEmpty()) {
                Log.d("MyApp", "Danh sách rỗng.");
            } else {
                for (Item item : itemList) {
                    Log.d("MyApp", "Tên: " + item.getName());
                    Log.d("MyApp", "Giá: " + item.getPrice());
                    Log.d("MyApp", "Đường dẫn hình ảnh: " + item.getImagePath());
                }
                searchItemAdapter.setItemList(itemList);
            }
        }
    }

}
