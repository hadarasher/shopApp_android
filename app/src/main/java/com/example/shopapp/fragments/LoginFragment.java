package com.example.shopapp.fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopapp.R;
import com.example.shopapp.data.MyData;

import java.util.HashMap;

public class LoginFragment extends Fragment {

    private Dialog registrationDialog;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        //TODO: add try catch
        EditText usernameEdit=view.findViewById(R.id.usernameEditText);
        EditText passwordEdit=view.findViewById(R.id.passwordEditText);
        Button loginBtn=view.findViewById(R.id.buttonLogin);
        Button registerBtn=view.findViewById(R.id.buttonRegister);
        TextView errorMsg=view.findViewById(R.id.errorMsg);
        registrationDialog = new Dialog(view.getContext());
        Bundle bundle=new Bundle();


        /*set button listeners for login and register button.
        on login click - check user details. if found on list go to shop fragment, else get error message.
        on register click - opens dialog for registration, and add the user to the list (if username available).
         */
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=usernameEdit.getText().toString();
                String password=passwordEdit.getText().toString();

                if(MyData.users.containsKey(username)){
                    if(password.equals(MyData.users.get(username))){
                        bundle.putString("username",username);
                        Navigation.findNavController(view).navigate(R.id.action_loginFragment2_to_shopFragment,bundle);
                    }else {errorMsg.setVisibility(View.VISIBLE);}

                }else{errorMsg.setVisibility(View.VISIBLE);}
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrationDialog.setContentView(R.layout.register_dialog_layout);
                registrationDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                ImageView closeImg=registrationDialog.findViewById(R.id.buttonClose);
                EditText regUsernameEdit = registrationDialog.findViewById(R.id.regUsernameEditText);
                EditText regPasswordEdit = registrationDialog.findViewById(R.id.regPasswordEditText);
                EditText regPhoneEdit = registrationDialog.findViewById(R.id.regPhoneEditText);
                TextView regErrorMsg = registrationDialog.findViewById(R.id.usernameError);
                Button buttonReg=registrationDialog.findViewById(R.id.buttonRegisterLayout);

                buttonReg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String username = regUsernameEdit.getText().toString();
                        String password = regPasswordEdit.getText().toString();
                        String phone = regPhoneEdit.getText().toString();

                        //check if all user put all details. than check username availability. if available add user to users list
                        if(!username.isEmpty() && !password.isEmpty() && phone.length()==10)
                        {
                            if(MyData.users.containsKey(username)){
                                regErrorMsg.setVisibility(View.VISIBLE);
                            }else{
                                MyData.users.put(username,password);
                                bundle.putString("username",username);
                                registrationDialog.dismiss();
                                Navigation.findNavController(view).navigate(R.id.action_loginFragment2_to_shopFragment,bundle);
                            }
                        }else{regErrorMsg.setVisibility(View.VISIBLE);}
                    }
                });
                closeImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        registrationDialog.dismiss();
                    }
                });

                registrationDialog.show();
            }
        });




        return view;
    }

}