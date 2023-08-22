package com.user.khanakhajana.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.user.khanakhajana.DetailImage;
import com.user.khanakhajana.Models.MainModel;
import com.user.khanakhajana.R;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.Viewholder>{
    ArrayList<MainModel> list;
    Context context;

    public MainAdapter(ArrayList<MainModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.activity_sample_item,parent,false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
     final MainModel model=list.get(position);
     holder.imageItem.setImageResource(model.getImage());
     holder.nameItem.setText(model.getName());
     holder.priceItem.setText(model.getPrice());
     holder.descriptionItem.setText(model.getDescription());
      setAnimation(holder.itemView,position);

     holder.itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent intent=new Intent(context, DetailImage.class);
             intent.putExtra("imageItems",model.getImage());
             intent.putExtra("nameItems",model.getName());
             intent.putExtra("priceItems",model.getPrice());
             intent.putExtra("descriptionItems",model.getDescription());

             intent.putExtra("type",1);
             context.startActivity(intent);

         }
     });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        ImageView imageItem;
        TextView nameItem,priceItem,descriptionItem;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageItem=itemView.findViewById(R.id.imageItem);
            nameItem=itemView.findViewById(R.id.nameItem);
            priceItem=itemView.findViewById(R.id.priceItem);
            descriptionItem=itemView.findViewById(R.id.descriptionItem);

        }
    }
    private void setAnimation(View viewtoanimate,int position){
        Animation load= AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        viewtoanimate.startAnimation(load);
    }


}
