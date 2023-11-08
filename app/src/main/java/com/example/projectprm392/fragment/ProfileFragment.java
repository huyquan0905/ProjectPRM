package com.example.projectprm392.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.projectprm392.Database.UserDatabase;
import com.example.projectprm392.R;
import com.example.projectprm392.dao.UserDAO;
import com.example.projectprm392.model.User;
import com.google.android.material.textfield.TextInputLayout;

public class ProfileFragment extends Fragment {
    private TextInputLayout nameInput;
    private TextInputLayout emailInput;
    private TextInputLayout phoneInput;
    private TextInputLayout addressInput;
    private Button submitButton;
    private Button logoutButton;

    private String nameValue = "";
    private String emailValue = "";
    private String phoneValue = "";
    private String addressValue = "";

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Ánh xạ các thành phần giao diện
        nameInput = view.findViewById(R.id.nameInput);
        emailInput = view.findViewById(R.id.emailInput);
        phoneInput = view.findViewById(R.id.phoneInput);
        addressInput = view.findViewById(R.id.addressInput);
        submitButton = view.findViewById(R.id.elevatedButton);
        logoutButton = view.findViewById(R.id.logoutButton);


        // Gắn lắng nghe sự kiện cho nút "Submit"
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Trích xuất dữ liệu từ các TextInputLayout
                nameValue = nameInput.getEditText().getText().toString();
                emailValue = emailInput.getEditText().getText().toString();
                phoneValue = phoneInput.getEditText().getText().toString();
                addressValue = addressInput.getEditText().getText().toString();

                // Tạo một đối tượng User mới
                User newUser = new User(0, nameValue, emailValue, addressValue, phoneValue, "image_path");

                // Gọi phương thức để thêm người dùng trong background thread
                new InsertUserTask().execute(newUser);
            }
        });

        // Gắn lắng nghe sự kiện cho nút "Log out"
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đặt mã xử lý đăng xuất ở đây (nếu cần).

                // Ví dụ: Chuyển người dùng đăng xuất khỏi ứng dụng.
            }
        });

        return view;
    }

    private class InsertUserTask extends AsyncTask<User, Void, Void> {
        @Override
        protected Void doInBackground(User... users) {
            // Thực hiện thêm dữ liệu vào cơ sở dữ liệu trong background thread
            UserDatabase database = UserDatabase.getInstance(getContext());
            UserDAO userDAO = database.userDAO();
            userDAO.insert(users[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // Sau khi thêm dữ liệu, bạn có thể cập nhật giao diện hoặc hiển thị thông báo.
            // Ví dụ: Đặt các trường nhập về giá trị rỗng
            nameInput.getEditText().setText("");
            emailInput.getEditText().setText("");
            phoneInput.getEditText().setText("");
            addressInput.getEditText().setText("");
        }
    }
}
