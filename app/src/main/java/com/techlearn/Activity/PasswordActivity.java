package com.techlearn.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.techlearn.DB.SQL_DB;
import com.techlearn.Model.Password;
import com.techlearn.R;

import java.util.Objects;

import static com.techlearn.Constants.LOWERCASE;
import static com.techlearn.Constants.NUMBERS;
import static com.techlearn.Constants.PASSWORD_VALUE_EXTRA;
import static com.techlearn.Constants.SYMBOLS;
import static com.techlearn.Constants.UPPERCASE;

public class PasswordActivity extends AppCompatActivity {

    private EditText passwordTitle;
    private EditText edPassword;
    private SQL_DB sqlDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        //back arrow in the toolbar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        if (!intent.hasExtra(PASSWORD_VALUE_EXTRA)) {
            return;
        }
        sqlDb = new SQL_DB(this);

        final String password = intent.getStringExtra(PASSWORD_VALUE_EXTRA);
        edPassword = findViewById(R.id.ed_password);
        edPassword.setText(password);

        passwordTitle = findViewById(R.id.ed_title);
        Button btnSave = findViewById(R.id.btn_save);

        ProgressBar bar = findViewById(R.id.progress_horizontal);
        bar.setProgress(calculatePasswordStrength(password));


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(passwordTitle.getText())) {
                    Toast.makeText(PasswordActivity.this, getResources().getString(R.string.enter_password_title), Toast.LENGTH_SHORT).show();
                    return;
                }
                String title = passwordTitle.getText().toString();
                String password = edPassword.getText().toString();

                Password password1 = new Password(title, password);
                long result = sqlDb.insertInto(password1);
                if (result > -1) {
                    Toast.makeText(PasswordActivity.this, getResources().getString(R.string.saved), Toast.LENGTH_SHORT).show();
                    onBackPressed();
                } else {
                    Toast.makeText(PasswordActivity.this, getResources().getString(R.string.fail_to_save), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private static int calculatePasswordStrength(String password) {


        int passwordStrength = 0;

        if (password.length() < 7)
            return 1;
        else if (password.length() >= 10)
            passwordStrength += 2;
        else
            passwordStrength += 1;

        if (password.matches(NUMBERS))
            passwordStrength += 2;

        if (password.matches(LOWERCASE))
            passwordStrength += 2;

        if (password.matches(UPPERCASE))
            passwordStrength += 2;

        if (password.matches(SYMBOLS))
            passwordStrength += 2;

        return passwordStrength;

    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(PasswordActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
