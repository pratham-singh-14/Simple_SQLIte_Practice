package com.user.khanakhajana;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;

import com.user.khanakhajana.Adapters.OrderAdapter;
import com.user.khanakhajana.Models.OrderModel;
import com.user.khanakhajana.databinding.ActivityOrderBinding;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
ActivityOrderBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        DBHelper helper=new DBHelper(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        binding.orderRecycler.setLayoutManager(linearLayoutManager);
        showorder(helper);







    }

    public void showorder(DBHelper helper) {
        ArrayList<OrderModel> list= helper.getOrders();
        if(list.size()>0){
            binding.linear.setVisibility(View.GONE);
            binding.orderRecycler.setVisibility(View.VISIBLE);
            binding.orderRecycler.setAdapter(new OrderAdapter(list,OrderActivity.this));
            binding.referesh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {

                    binding.orderRecycler.setAdapter(new OrderAdapter(list,OrderActivity.this));
                    binding.referesh.setRefreshing(false);

                }
            });
        }else {
            binding.linear.setVisibility(View.VISIBLE);
            binding.orderRecycler.setVisibility(View.GONE);
        }
    }
}