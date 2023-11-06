package com.example.projectprm392.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.projectprm392.Database.AccountDatabase;
import com.example.projectprm392.Database.ItemDatabase;
import com.example.projectprm392.adapter.ItemAdapter;
import com.example.projectprm392.model.Item;

import com.example.projectprm392.R;

import java.util.List;

public class ListItem extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button btnAddCart;
    private ItemAdapter itemAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);

        recyclerView = findViewById(R.id.MenuRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new LoadItemsTask().execute();
    }

    private class LoadItemsTask extends AsyncTask<Void, Void, List<Item>> {
        @Override
        protected List<Item> doInBackground(Void... voids) {
            return ItemDatabase.getInstance(ListItem.this).itemDAO().getAllItems();
        }

        @Override
        protected void onPostExecute(List<Item> itemList) {
            itemAdapter = new ItemAdapter(itemList, new ItemAdapter.OnDeleteItemClickListener() {
                @Override
                public void onDeleteItemClick(Item item) {
                    new DeleteItemTask().execute(item);
                }
            });
            recyclerView.setAdapter(itemAdapter);
        }
    }

    private class DeleteItemTask extends AsyncTask<Item, Void, Void> {
        @Override
        protected Void doInBackground(Item... items) {
            ItemDatabase.getInstance(ListItem.this).itemDAO().deleteItem(items[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            new LoadItemsTask().execute(); // Refresh the list after deletion
        }
    }


}

