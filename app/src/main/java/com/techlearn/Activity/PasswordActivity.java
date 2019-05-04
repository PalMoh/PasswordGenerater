package com.techlearn.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.techlearn.DB.SQL_DB;
import com.techlearn.Model.Password;
import com.techlearn.R;

import static com.techlearn.Constants.PASSWORD_EXTRA;

public class PasswordActivity extends AppCompatActivity {

    private EditText passwordTitle;
    private EditText tvPassword;
    private SQL_DB sqlDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        Intent intent = getIntent();
        if(!intent.hasExtra(PASSWORD_EXTRA)){
            return;
        }
        sqlDb = new SQL_DB(this);
        final String password = intent.getStringExtra(PASSWORD_EXTRA);
        tvPassword = findViewById(R.id.tv_password);
        tvPassword.setText(password);

        passwordTitle = findViewById(R.id.ed_title);
        Button btnSave = findViewById(R.id.btn_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(passwordTitle.getText())){
                    Toast.makeText(PasswordActivity.this, "Need password title", Toast.LENGTH_SHORT).show();
                    return;
                }
                String title = passwordTitle.getText().toString();
                String password = tvPassword.getText().toString();
                // new instant of Password Object
                Password password1 = new Password(title,password);
                long result = sqlDb.insertInto(password1);
                if(result > -1){
                    Toast.makeText(PasswordActivity.this, "SAVED :)", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }else {
                    Toast.makeText(PasswordActivity.this, "FAIL TO SAVE", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(PasswordActivity.this, MainActivity.class));
        finish();
    }
}
