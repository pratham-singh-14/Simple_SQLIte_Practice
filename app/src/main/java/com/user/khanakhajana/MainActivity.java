package com.user.khanakhajana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.user.khanakhajana.Adapters.MainAdapter;
import com.user.khanakhajana.Models.MainModel;
import com.user.khanakhajana.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
 ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModel> list = new ArrayList<>();
        list.add(new MainModel(R.drawable.burger, "Veg Burger", "2", "A veggie burger is a hamburger patty that does not contain meat. It may be made from ingredients like beans, especially soybeans and tofu, nuts, grains etc."));
        list.add(new MainModel(R.drawable.momos, "Veg momos", "1", "Momo are bite-size dumplings made with a spoonful of stuffing wrapped in dough. Momo are usually steamed, though they are sometimes fried or steam-fried."));
        list.add(new MainModel(R.drawable.eggoll, "Egg Roll", "4", "An egg roll is a cylindrical, savory roll with shredded cabbage, chopped pork, and other fillings inside a thickly-wrapped wheat flour skin, which is fried in hot oil."));
        list.add(new MainModel(R.drawable.tomatochat, "Tamatar chaat", "3", "An easy and simple indian street food recipe made with a combination of tomatoes and boiled potatoes with lip-smacking chaat chutneys"));
        list.add(new MainModel(R.drawable.panipuri, "Pani-puri", "2", "Pani-puri is a small round-shaped hollow unleavened bread which is deep-fried and filled with a mixture of potatoes, chickpeas, spices, flavored water,"));
        list.add(new MainModel(R.drawable.smosa, "Samosa ", "2", "A samosa or singara is a fried or baked pastry with a savory filling, including ingredients such as spiced potatoes, onions, and peas."));
        list.add(new MainModel(R.drawable.chaumin, "Chow mein", "3", " Chow mein is a popular Chinese dish of stir fried noodles with mix vegetables, soy sauce, aromatics and spices."));
        list.add(new MainModel(R.drawable.manchuriyan, "Veg Manchuriyan", "2", "Veg Manchurian is a tasty Indo Chinese dish of fried veggie balls in a spicy, sweet and tangy sauce"));
        list.add(new MainModel(R.drawable.chickenroll, "Chicken Roll", "5", "Chicken Roll is basically a wrap of spiced chicken with vegetables inside a soft golden paratha, sometimes coated with eggs or drizzled with green"));
        MainAdapter adapter = new MainAdapter(list, this);
        binding.recyclerView.setAdapter(adapter);


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(linearLayoutManager);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.orderscheck:
                startActivity(new Intent(MainActivity.this,OrderActivity.class));
                break;
        }
        return true;
    }
}