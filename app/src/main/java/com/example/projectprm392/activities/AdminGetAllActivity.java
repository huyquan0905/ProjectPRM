package com.example.projectprm392.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprm392.Database.ItemDatabase;
import com.example.projectprm392.R;
import com.example.projectprm392.adapter.ItemAdapter;
import com.example.projectprm392.model.Item;

import java.util.List;

public class AdminGetAllActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_get_all);

        recyclerView = findViewById(R.id.MenuRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ImageButton backButton = findViewById(R.id.imageButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminGetAllActivity.this, AdminDashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });
        new LoadItemsTask().execute();
    }

    private class LoadItemsTask extends AsyncTask<Void, Void, List<Item>> {
        @Override
        protected List<Item> doInBackground(Void... voids) {
            return ItemDatabase.getInstance(AdminGetAllActivity.this).itemDAO().getAllItems();
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
            Item itemToDelete = items[0];
            ItemDatabase.getInstance(AdminGetAllActivity.this).itemDAO().deleteItem(itemToDelete);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            itemAdapter.notifyDataSetChanged();
        }
    }
}