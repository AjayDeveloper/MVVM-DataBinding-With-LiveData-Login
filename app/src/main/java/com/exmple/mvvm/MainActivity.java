package com.exmple.mvvm;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.exmple.mvvm.databinding.ActivityMainBinding;
import com.exmple.mvvm.model.Login;
import com.exmple.mvvm.viewmodel.LoginViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private LoginViewModel loginViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        binding.setLoginViewModel(loginViewModel);
        loginViewModel.getUser().observe(this, new Observer<Login>() {
            @Override
            public void onChanged(Login login) {

                if (TextUtils.isEmpty(login.getEmail())) {
                    binding.edtUserName.setError("Enter your Email id");
                    binding.edtUserName.requestFocus();

                } else if (!login.isEmailValid()) {
                    binding.edtUserName.setError("Enter a Valid E-mail Address");
                    binding.edtUserName.requestFocus();

                } else if (TextUtils.isEmpty(login.getPassword())) {
                    binding.edtPassword.setError("Enter your password");
                    binding.edtPassword.requestFocus();

                } else if (!login.isPasswordGreaterThanFive()) {
                    binding.edtPassword.setError("Enter a password greater than 5 char");
                    binding.edtPassword.requestFocus();
                } else {
                    binding.edtUserName.setText(login.getEmail());
                    binding.edtPassword.setText(login.getPassword());
                }


            }
        });

    }
}