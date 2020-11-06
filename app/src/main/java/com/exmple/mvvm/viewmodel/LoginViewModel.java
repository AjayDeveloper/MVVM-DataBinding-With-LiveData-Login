package com.exmple.mvvm.viewmodel;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.exmple.mvvm.model.Login;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<String> emailAddress = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();




    private MutableLiveData<Login> loginMutableLiveData ;

    public MutableLiveData<Login> getUser() {
          if(loginMutableLiveData==null){
              loginMutableLiveData = new MutableLiveData<>();
          }
        return loginMutableLiveData;
    }

    public void onClick(View view){
        Login login = new Login(emailAddress.getValue(),password.getValue());
        loginMutableLiveData.setValue(login);

    }
}
