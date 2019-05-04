package com.techlearn.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.techlearn.Activity.PasswordActivity;
import com.techlearn.R;

import java.util.Objects;
import java.util.Random;

import static com.techlearn.Constants.PASSWORD_EXTRA;


/**
 * A simple {@link Fragment} subclass.
 */
public class BottomFragment extends BottomSheetDialogFragment {


    public BottomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bottom, container, false);

        final EditText edLength = view.findViewById(R.id.ed_length);
        Button btnGenerate = view.findViewById(R.id.btn_generate);

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edLength.getText())){
                    Toast.makeText(getActivity(), "Enter Password Lenght", Toast.LENGTH_SHORT).show();
                    return;
                }
                int length = Integer.parseInt(edLength.getText().toString());
                String password = String.valueOf(passwordGenerator(length));
                startActivity(password);

            }
        });

        return view;
    }

    private void startActivity(String password){
        Intent intent = new Intent(getActivity(), PasswordActivity.class);
        intent.putExtra(PASSWORD_EXTRA,password);
        startActivity(intent);
        Objects.requireNonNull(getActivity()).finish();
    }

    private static char[] passwordGenerator(int length){

        String lCaps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String LSmall = "abcdefghijklmnopqrstuvwxyz";
        String number = "0123456789";
        String symbols = "!@#$%^&*/=+-.?<>)";

        String passWordSym= lCaps + LSmall + number + symbols;

        Random random = new Random();

        char[] password = new char[length];
        for(int i = 0 ; i < length ; i++){
            password[i] = passWordSym.charAt(random.nextInt(passWordSym.length()));
        }
        return password;
    }

}
