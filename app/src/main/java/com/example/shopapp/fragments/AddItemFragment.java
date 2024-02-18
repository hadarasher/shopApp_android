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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddItemFragment newInstance(String param1, String param2) {
        AddItemFragment fragment = new AddItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
                int amount=Integer.parseInt(textViewAmount.getText().toString());

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