package com.techlearn.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.techlearn.DB.SQL_DB;
import com.techlearn.R;

import static com.techlearn.Constants.PASSWORD_ID_EXTRA;
import static com.techlearn.Constants.PASSWORD_TITLE_EXTRA;
import static com.techlearn.Constants.PASSWORD_VALUE_EXTRA;

public class PasswordInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_info);

        final SQL_DB db = new SQL_DB(this);

        Intent bundle = getIntent();
        assert bundle != null;
        String title = bundle.getStringExtra(PASSWORD_TITLE_EXTRA);
        String password = bundle.getStringExtra(PASSWORD_VALUE_EXTRA);
        final int id  = bundle.getIntExtra(PASSWORD_ID_EXTRA,0);

        TextView tvTitle = findViewById(R.id.tv_title);
        TextView tvPassword = findViewById(R.id.tv_password);
        Button btnDelete = findViewById(R.id.btn_delete);

        tvTitle.setText(title);
        tvPassword.setText(password);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(db.deleteTitle(id)){
                    Toast.makeText(PasswordInfoActivity.this, "DONE ", Toast.LENGTH_SHORT).show();
                    onBackPressed();

                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(PasswordInfoActivity.this, MainActivity.class));
        finish();
    }
}
