package com.techlearn.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techlearn.Adapter.RecyclerAdapter;
import com.techlearn.CustomeClick;
import com.techlearn.DB.SQL_DB;
import com.techlearn.Fragments.BottomFragment;
import com.techlearn.Model.Password;
import com.techlearn.R;

import static com.techlearn.Constants.PASSWORD_OBJECT_EXTRA;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQL_DB sql_db = new SQL_DB(this);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerAdapter adapter = new RecyclerAdapter(this, sql_db.savedPassword(), new CustomeClick() {
            @Override
            public void onClick(Password p) {

                Intent intent = new Intent(MainActivity.this,PasswordInfoActivity.class);
                intent.putExtra(PASSWORD_OBJECT_EXTRA,p);
                startActivity(intent);
                finish();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        findViewById(R.id.fab_create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomFragment fragment = new BottomFragment();
                fragment.show(getSupportFragmentManager(),"length");
            }
        });

    }


}
