package com.JanaZiaz.black_fig;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.JanaZiaz.black_fig.Adapter.RecipesAdapter;
import com.JanaZiaz.black_fig.Adapter.RecipesdetailsAdapter;
import com.JanaZiaz.black_fig.Model.recipes;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecipesdetailsActivity extends AppCompatActivity {
ImageView imageView ;
    TextView timetxt , Ingredients,nametxt;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    Context context ;
    private List<String> name=new ArrayList<>();


    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_detiles1);
        recyclerView=findViewById(R.id.recyclerView2);
nametxt=findViewById(R.id.name);
        imageView=findViewById(R.id.img);
        timetxt=findViewById(R.id.timetxt);
        Ingredients=findViewById(R.id.IngredientsData);
        String name = getIntent().getStringExtra("name");
        String img = getIntent().getStringExtra("img");
        String time = getIntent().getStringExtra("time");
        String Ingredientstxt = getIntent().getStringExtra("Ingredients");
        Picasso.get().load(img).into(imageView);
        timetxt.setText(time+"Min");
        Ingredients.setText(Ingredientstxt);
        nametxt.setText(name);



    }
}
