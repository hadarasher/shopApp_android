package com.example.shopapp.fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.shopapp.R;
import com.example.shopapp.activities.CustomAdapter;
import com.example.shopapp.activities.SelectListener;
import com.example.shopapp.data.ItemDataModel;
import com.example.shopapp.data.MyData;

import java.util.ArrayList;


public class ShopFragment extends Fragment implements SelectListener {

    private String username;
    private CustomAdapter adapter;
    private ArrayList<ItemDataModel> dataSet;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private Dialog dialogDeleteItem;

    public ShopFragment() {
        // Required empty public constructor
    }


    public static ShopFragment newInstance() {
        ShopFragment fragment = new ShopFragment();

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
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        dataSet=new ArrayList<>();
        dialogDeleteItem=new Dialog(this.getContext());
        try{
            username=getArguments().getString("username");
        }catch(NullPointerException e){
            username="";}
        TextView textViewHello=view.findViewById(R.id.helloText);
        textViewHello.setText("Hello, "+username);

        Button  buttonAddItem=view.findViewById(R.id.buttonAddItem);

        recyclerView=view.findViewById(R.id.shopRecyclerView);
        layoutManager=new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //add all data to data set
        for (int i = 0; i < MyData.itemsName.size(); i++) {
            dataSet.add(new ItemDataModel(
                    MyData.itemsName.get(i),
                    MyData.itemsDescription.get(i),
                    MyData.itemsAmount.get(i)
            ));
        }

        //set adapter
        adapter=new CustomAdapter(dataSet,this);
        recyclerView.setAdapter(adapter);

        buttonAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("username",username);
                Navigation.findNavController(view).navigate(R.id.action_shopFragment_to_addItemFragment,bundle);
            }
        });
        return view;
    }

    @Override
    public void onItemClick(ItemDataModel dataModel) {openCardDialog(dataModel);    }

    private void openCardDialog(ItemDataModel dataModel) {
        dialogDeleteItem.setContentView(R.layout.delete_item_dialog_layout);
        dialogDeleteItem.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView textViewItemName=dialogDeleteItem.findViewById(R.id.itemNameDialogView);
        TextView textViewDescription = dialogDeleteItem.findViewById(R.id.descriptionDialogTextView);
        TextView textViewAmount=dialogDeleteItem.findViewById(R.id.amountDialogTextView);
        Button buttonDelete=dialogDeleteItem.findViewById(R.id.buttonDeleteDialog);
        Button buttonCancel=dialogDeleteItem.findViewById(R.id.buttonCancelDialog);

        textViewItemName.setText(dataModel.getName());
        textViewDescription.setText(dataModel.getDescription());
        textViewAmount.setText(String.valueOf(dataModel.getAmount()));

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDeleteItem.dismiss();
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delete from MyData
                MyData.itemsName.remove(dataModel.getName());
                MyData.itemsDescription.remove(dataModel.getDescription());
                MyData.itemsAmount.remove(Integer.valueOf(dataModel.getAmount()));

                //delete from dataSet too
                dataSet.remove(dataModel);

                adapter.notifyDataSetChanged();

                dialogDeleteItem.dismiss();
            }
        });

        dialogDeleteItem.show();
    }

}