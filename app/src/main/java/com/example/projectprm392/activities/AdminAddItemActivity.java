package com.example.projectprm392.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
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

public class AdminAddItemActivity extends AppCompatActivity {
    private EditText nameEditText;
    private EditText priceEditText;
    private ImageView imageView;
    private static final int PICK_IMAGE_REQUEST = 1; // Định nghĩa hằng số PICK_IMAGE_REQUEST

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_item);

        ImageButton backButton = findViewById(R.id.imageButton);
        nameEditText = findViewById(R.id.enterFoodName);
        priceEditText = findViewById(R.id.enterFoodPrice);
        imageView = findViewById(R.id.selectedImage);

        // Back lại AdminDashboard
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminAddItemActivity.this, AdminDashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Thêm sự kiện nghe cho nút "Add Item"
        findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveItem();
            }
        });

        // Thêm sự kiện nghe cho nút "Select Item"
        findViewById(R.id.selectImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mở cửa sổ chọn ảnh
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });
    }

    // Hàm này thực hiện việc lưu một mục mới vào cơ sở dữ liệu
    private void saveItem() {
        String name = nameEditText.getText().toString();
        String priceStr = priceEditText.getText().toString();

        if (name.isEmpty() || priceStr.isEmpty()) {
            Toast.makeText(this, "Please enter name and price", Toast.LENGTH_SHORT).show();
        } else {
            double price = Double.parseDouble(priceStr);

            // Lưu ảnh và đường dẫn vào cơ sở dữ liệu Room
            String imagePath = saveImageAndGetPath();

            // Tạo đối tượng Item và thiết lập dữ liệu
            Item newItem = new Item();
            newItem.setName(name);
            newItem.setPrice(price);
            newItem.setImagePath(imagePath);

            // Sử dụng AsyncTask để thực hiện lưu đối tượng Item vào cơ sở dữ liệu Room
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
                    Toast.makeText(AdminAddItemActivity.this, "Item added successfully and saved to the database", Toast.LENGTH_SHORT).show();

                    // Chuyển trở lại màn hình AdminDashboardActivity
                    Intent intent = new Intent(AdminAddItemActivity.this, AdminDashboardActivity.class);
                    startActivity(intent);
                    finish();
                }
            }.execute(newItem);
        }
    }


    // triển khai phương thức lưu ảnh và trả về đường dẫn
    private String saveImageAndGetPath() {
        String imagePath = "";

        // Kiểm tra xem imageView có hình ảnh hay không
        if (imageView.getDrawable() != null) {
            Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            try {
                // Lưu hình ảnh vào bộ nhớ thiết bị và lấy đường dẫn
                File imageFile = saveBitmapToFile(bitmap);
                if (imageFile != null) {
                    imagePath = imageFile.getAbsolutePath();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return imagePath;
    }

    // Phương thức này lưu hình ảnh vào thiết bị và trả về đường dẫn
    private File saveBitmapToFile(Bitmap bitmap) throws IOException {
        String fileName = "item_image.png";
        File imageFile = new File(getExternalFilesDir(null), fileName);

        FileOutputStream fos = new FileOutputStream(imageFile);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        fos.close();

        return imageFile;
    }

    // Xử lý kết quả khi người dùng chọn ảnh
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            // Lấy đường dẫn của ảnh từ Intent
            // Uri imageUri = data.getData();
            Uri imageUri = data.getData();
            // Hiển thị ảnh đã chọn trong ImageView
            imageView.setImageURI(imageUri);
        }
    }
}
