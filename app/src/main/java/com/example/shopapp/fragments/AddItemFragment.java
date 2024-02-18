package com.example.shopapp.fragments;

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
import com.example.shopapp.data.MyData;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddItemFragment extends Fragment {

    public static AddItemFragment newInstance() {
        AddItemFragment fragment = new AddItemFragment();

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
        View view = inflater.inflate(R.layout.fragment_add_item, container, false);

        EditText textViewItemName=view.findViewById(R.id.itemNameEditText);
        EditText textViewDescription=view.findViewById(R.id.descriptionEditText);
        EditText textViewAmount=view.findViewById(R.id.amountEditText);
        Button addItemBtn=view.findViewById(R.id.buttonAdd);
        Button cancelBtn=view.findViewById(R.id.buttonCancel);
        TextView errorMsg=view.findViewById(R.id.errorAddText);

        Bundle bundle=new Bundle();
        bundle.putString("username",getArguments().getString("username"));

        //add listener to cancel button. If clicked - returns to shop fragment
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_addItemFragment_to_shopFragment,bundle);
            }
        });

        //add click listener to add item button. If clicked check all values and if valid add item to data set, then returns to shop fragment
        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName=textViewItemName.getText().toString();
                String description=textViewDescription.getText().toString();
                int amount;
                try{
                    amount=Integer.parseInt(textViewAmount.getText().toString());
                }catch (NumberFormatException e){ amount=-1;}

                if (!itemName.isEmpty() && !description.isEmpty() && amount >= 0) {
                    MyData.itemsName.add(itemName);
                    MyData.itemsDescription.add(description);
                    MyData.itemsAmount.add(amount);

                    Navigation.findNavController(view).navigate(R.id.action_addItemFragment_to_shopFragment,bundle);
                } else {
                    errorMsg.setVisibility(View.VISIBLE);
                }
            }
        });

        return view;
    }
}