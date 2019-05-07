package com.techlearn.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.techlearn.DB.SQL_DB;
import com.techlearn.Model.Password;
import com.techlearn.R;

import java.util.Objects;

import static com.techlearn.Constants.PASSWORD_OBJECT_EXTRA;

public class PasswordInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_info);

        //back arrow in the toolbar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final SQL_DB db = new SQL_DB(this);

        Intent bundle = getIntent();
        if (!bundle.hasExtra(PASSWORD_OBJECT_EXTRA))
            return;

        final Password password = (Password) bundle.getSerializableExtra(PASSWORD_OBJECT_EXTRA);

        TextView tvTitle = findViewById(R.id.tv_title);
        TextView tvPassword = findViewById(R.id.ed_password);
        Button btnDelete = findViewById(R.id.btn_delete);

        tvTitle.setText(password.getTitle());
        tvPassword.setText(password.getPassword());

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db.deletePassword(password.getId())) {
                    Toast.makeText(PasswordInfoActivity.this, getResources().getString(R.string.delete_done), Toast.LENGTH_SHORT).show();
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
