package com.user.khanakhajana;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.user.khanakhajana.databinding.ActivityDetailImageBinding;

public class DetailImage extends AppCompatActivity {
    ActivityDetailImageBinding binding;
     int count=0;
     int pricebefore=0;
     int pricenow=0;
     int temp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailImageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DBHelper helper = new DBHelper(this);
        if (getIntent().getIntExtra("type", 0) == 1) {


            final int image = getIntent().getIntExtra("imageItems", 0);
            final int  price = Integer.parseInt(getIntent().getStringExtra("priceItems"));
            temp=price;
            String name = getIntent().getStringExtra("nameItems");
            String description = getIntent().getStringExtra("descriptionItems");
            binding.detailImage.setImageResource(image);
           binding.detailpriceItem.setText(String.format("%d", price));
            binding.detailItemName.setText(name);

            binding.orderDescription.setText(description);


            binding.orderButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (binding.userPhone.getText().toString().isEmpty()) {
                        binding.userName.setError("Enter name");
                        binding.userName.requestFocus();

                    } else if (binding.userPhone.getText().toString().isEmpty()) {
                        binding.userPhone.setError("Enter phone number");
                        binding.userPhone.requestFocus();
                    } else {
                        pricebefore=price;
                        boolean isInsert = helper.Insertorder(
                                binding.userName.getText().toString(),
                                binding.userPhone.getText().toString(),
//                                 pricebefore,
                               Integer.parseInt( binding.detailpriceItem.getText().toString()),
                                image,
                                name,
                                Integer.parseInt(binding.quantity.getText().toString()),
                                description


                        );
                        if (isInsert) {
                            Toast.makeText(DetailImage.this, "Order Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), OrderActivity.class));
                        } else {
                            Toast.makeText(DetailImage.this, "something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        } else {
            int id = getIntent().getIntExtra("id", 0);

            Cursor cursor = helper.getOrderById(id);

            final int image = cursor.getInt(4);
            binding.detailImage.setImageResource(image);
            binding.detailpriceItem.setText(String.format("%d", cursor.getInt(3)));
            binding.detailItemName.setText(cursor.getString(5));
            binding.quantity.setText(cursor.getString(6));
            binding.orderDescription.setText(cursor.getString(7));
            binding.userName.setText(cursor.getString(1));
            binding.userPhone.setText(cursor.getString(2));
            binding.orderButton.setText("Update Now");
            pricebefore=cursor.getInt(3);
            binding.orderButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean IsUpdated = helper.Updateorder(
                            binding.userName.getText().toString(),
                            binding.userPhone.getText().toString(),
                            Integer.parseInt(binding.detailpriceItem.getText().toString()),
                            image,
                            binding.detailItemName.getText().toString(),
                            Integer.parseInt(binding.quantity.getText().toString()),
                            binding.orderDescription.getText().toString(),
                            id

                    );
                    if (IsUpdated)
                        Toast.makeText(DetailImage.this, "Order Updated Successfully", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DetailImage.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
            binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Increment();

                }
            });
            binding.substract.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Decrement();

                }
            });





    }

    private void Decrement() {

            count = Integer.parseInt(binding.quantity.getText().toString());

            count--;
            if(count <=0)
            {}else {

                binding.quantity.setText("" + count);
            }
        }


    private void Increment() {

      count= Integer.parseInt( binding.quantity.getText().toString());


       count++;
       if(count == 1)
       {

           binding.quantity.setText(""+count);
       }
//       pricebefore=pricebefore+temp;
//       binding.detailpriceItem.setText(pricebefore);
       binding.quantity.setText(""+count);



        }

    }
