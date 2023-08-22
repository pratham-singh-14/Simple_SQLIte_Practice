package com.user.khanakhajana.Adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.user.khanakhajana.DBHelper;
import com.user.khanakhajana.DetailImage;
import com.user.khanakhajana.Models.OrderModel;
import com.user.khanakhajana.OrderActivity;
import com.user.khanakhajana.R;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.orderViewHolder>{
    ArrayList<OrderModel> list;
    Context context;

    public OrderAdapter(ArrayList<OrderModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public orderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.order_sample,parent,false);
        return new orderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull orderViewHolder holder, @SuppressLint("RecyclerView") int position) {
   final OrderModel model=list.get(position);
   holder.orderImage.setImageResource(model.getOrderImage());
   holder.orderName.setText(model.getOrderName());
 holder.orderPrice.setText(model.getOrderPrice());
 holder.orderNumber.setText(model.getOrderNumber());

 holder.itemView.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
         Intent intent = new Intent(context, DetailImage.class);
         intent.putExtra("id",Integer.parseInt(model.getOrderNumber()));
         intent.putExtra("type",2);
         context.startActivity(intent);
     }
 });
 holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
     @Override
     public boolean onLongClick(View view) {
    new AlertDialog.Builder(context)
            .setTitle("Delete Order")
            .setMessage("Are you sure you want to delete this order!")
            .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    DBHelper helper=new DBHelper(context);
                    if(helper.deleteOrder(model.getOrderNumber()) > 0){
                        ((OrderActivity)context).showorder(helper);
                        Toast.makeText(context, "Order Deleted", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            }).show();

         return false;
     }
 });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class orderViewHolder extends RecyclerView.ViewHolder{

        ImageView orderImage;
        TextView orderName,orderNumber,orderPrice;


        public orderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderImage=itemView.findViewById(R.id.orderImage);
            orderName=itemView.findViewById(R.id.orderName);
            orderNumber=itemView.findViewById(R.id.orderNumber);
            orderPrice=itemView.findViewById(R.id.orderPrice);

        }
    }
}
