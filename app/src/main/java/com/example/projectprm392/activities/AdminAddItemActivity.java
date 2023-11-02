package com.example.projectprm392.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.projectprm392.R;
import com.example.projectprm392.Database.ItemDatabase;
import com.example.projectprm392.model.Item;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.util.Log;

public class AdminAddItemActivity extends AppCompatActivity {
    private EditText nameEditText;
    private EditText priceEditText;
    private ImageView imageView;
    private static final int PICK_IMAGE_REQUEST = 1;

    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_item);

        ImageButton backButton = findViewById(R.id.imageButton);
        nameEditText = findViewById(R.id.enterFoodName);
        priceEditText = findViewById(R.id.enterFoodPrice);
        imageView = findViewById(R.id.selectedImage);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Sử dụng onBackPressed() để quay lại màn hình trước đó
            }
        });

        findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveItem();
            }
        });

        findViewById(R.id.selectImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();
            }
        });
    }

    private void saveItem() {
        String name = nameEditText.getText().toString();
        String priceStr = priceEditText.getText().toString();

        if (name.isEmpty() || priceStr.isEmpty()) {
            Toast.makeText(this, "Please enter name's item and price", Toast.LENGTH_SHORT).show();
        } else {
            double price = Double.parseDouble(priceStr);
            String imagePath = saveImageAndGetPath();

            Item newItem = new Item();
            newItem.setName(name);
            newItem.setPrice(price);
            newItem.setImagePath(imagePath);

            new AsyncTask<Item, Void, Void>() {
                @Override
                protected Void doInBackground(Item... items) {
                    ItemDatabase database = ItemDatabase.getInstance(AdminAddItemActivity.this);
                    database.itemDAO().insertItem(items[0]);
                    return null;
                }

                @Override
                protected void onPostExecute(Void result) {
                    super.onPostExecute(result);
                    Toast.makeText(AdminAddItemActivity.this, "Add Item susscessfully!!", Toast.LENGTH_SHORT).show();
                    onBackPressed(); // Quay lại màn hình AdminDashboardActivity
                }
            }.execute(newItem);
        }
    }

    private String saveImageAndGetPath() {
        String imagePath = "";
        if (selectedImageUri != null) {
            try {
                // Sử dụng getContentResolver() để mở InputStream từ selectedImageUri
                InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);

                if (inputStream != null) {
                    // Tạo tên tệp duy nhất bằng cách sử dụng thời gian hệ thống
                    String fileName = "item_image_" + System.currentTimeMillis() + ".png";

                    // Tạo File trong thư mục ứng dụng và mở FileOutputStream
                    File imageFile = new File(getExternalFilesDir(null), fileName);
                    FileOutputStream fos = new FileOutputStream(imageFile);

                    // Đọc dữ liệu từ InputStream và ghi vào FileOutputStream
                    byte[] buffer = new byte[8192];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        fos.write(buffer, 0, bytesRead);
                    }

                    // Đóng InputStream và FileOutputStream
                    inputStream.close();
                    fos.close();

                    // Đặt đường dẫn của tệp vào imagePath
                    imagePath = imageFile.getAbsolutePath();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return imagePath;
    }

    private String getPathFromUri(Uri uri) {
        String path = uri.getPath();
        return path;
    }

    private File saveBitmapToFile(Bitmap bitmap) throws IOException {
        String fileName = "item_image.png";
        File imageFile = new File(getExternalFilesDir(null), fileName);

        FileOutputStream fos = new FileOutputStream(imageFile);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        fos.close();

        return imageFile;
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            Log.d("MyApp", "Ảnh đã chọn:" + selectedImageUri);
            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImageUri, projection, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                String imagePath = cursor.getString(columnIndex);
                imageView.setImageURI(selectedImageUri);
                cursor.close();
            } else {
                Toast.makeText(this, "Error while get path image", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
