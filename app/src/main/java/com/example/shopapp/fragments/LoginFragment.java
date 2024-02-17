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
import android.widget.TextView;

import com.example.shopapp.R;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private static HashMap<String,String> users;
    private Dialog registrationDialog;


    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        users=new HashMap<>();
        fragment.setArguments(args);
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
                String username=usernameEdit.toString();
                String password=passwordEdit.toString();

                if(users.containsKey(username)){
                    if(password.equals(users.get(username))){
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

                String username=registrationDialog.findViewById(R.id.regUsernameEditText).toString();
                String password=registrationDialog.findViewById(R.id.regPasswordEditText).toString();
                String phone=registrationDialog.findViewById(R.id.regPhoneEditText).toString();
                TextView errorMsg=registrationDialog.findViewById(R.id.errorMsg);

                //check if all user put all details. than check username availability. if available add user to users list
                if(username!=null&&password!=null&&phone!=null)
                {
                    if(users.containsKey(username)){
                        errorMsg.setVisibility(View.VISIBLE);
                    }else{
                        users.put(username,password);
                        bundle.putString("username",username);
                        Navigation.findNavController(view).navigate(R.id.action_loginFragment2_to_shopFragment,bundle);
                    }
                }

            }
        });


        return view;
    }

    public void openRegistrationDialog(){

    }
}