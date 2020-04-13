package com.JanaZiaz.black_fig;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TabWidget;

import com.JanaZiaz.black_fig.Adapter.CategoryAdapter;
import com.JanaZiaz.black_fig.Adapter.RecipesAdapter;
import com.JanaZiaz.black_fig.Model.Category;
import com.JanaZiaz.black_fig.Model.recipes;
import com.JanaZiaz.black_fig.ui.home.HomeFragment;
import com.JanaZiaz.black_fig.ui.home.HomeViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecipesActivity extends AppCompatActivity {
    private HomeViewModel homeViewModel;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    Context context ;
    private List<String> name=new ArrayList<>();

    private List<String> idlist=new ArrayList<>();
    private List<String> img=new ArrayList<>();
    private List<String> time=new ArrayList<>();
    private List<String> ing=new ArrayList<>();
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    SearchView searchView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        recyclerView=findViewById(R.id.recyclerView2);
        final String id = getIntent().getStringExtra("id");
        searchView= findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
// do something on text submit
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {

// do something when text changes
                if (!newText.isEmpty()){
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("recipes").child(id);
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            name.clear();
                            img.clear();
                            idlist.clear();
                            ing.clear();
                            time.clear();

                            for (DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                                recipes recipes= dataSnapshot1.getValue( recipes.class);


                                name.add(recipes.getName());
                                img.add(recipes.getImage());
                                idlist.add(recipes.getId());
                                time.add(recipes.getTime());
                                ing.add(recipes.getIngredients());

                            }
                            for(int i = 0 ;i<name.size();i++)
                            {
                                if(name.get(i).toLowerCase().trim().contains(newText.toLowerCase().trim())){
                             List<String> name1=new ArrayList<>();

                           List<String> idlist1=new ArrayList<>();
                                 List<String> img1=new ArrayList<>();
                                  List<String> time1=new ArrayList<>();
                                  List<String> ing1=new ArrayList<>();
                                    name1.add(name.get(i));
                                    img1.add(img.get(i));
                                    idlist1.add(idlist.get(i));
                                    time1.add(time.get(i));
                                    ing1.add(ing.get(i));
                                    adapter = new RecipesAdapter(getApplicationContext(), RecipesActivity.this,name1,img1,idlist1,time1,ing1,"");

                                    linearLayoutManager = new LinearLayoutManager(getApplicationContext());


                                    dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), linearLayoutManager.getOrientation());

                                    recyclerView.setHasFixedSize(true);
                                    recyclerView .setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
                                    recyclerView.addItemDecoration(dividerItemDecoration);
                                    recyclerView.setAdapter(adapter);
                                }
                                else {

                                }


                            }

                           }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });}
                else {
                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("recipes").child(id);
                    mDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()){
                                name.clear();
                                img.clear();
                                idlist.clear();
                                ing.clear();
                                time.clear();

                                for (DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                                    recipes recipes= dataSnapshot1.getValue( recipes.class);


                                    name.add(recipes.getName());
                                    img.add(recipes.getImage());
                                    idlist.add(recipes.getId());
                                    time.add(recipes.getTime());
                                    ing.add(recipes.getIngredients());

                                }

                                adapter = new RecipesAdapter(getApplicationContext(), RecipesActivity.this,name,img,idlist,time,ing,"");

                                linearLayoutManager = new LinearLayoutManager(getApplicationContext());


                                dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), linearLayoutManager.getOrientation());

                                recyclerView.setHasFixedSize(true);
                                recyclerView .setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
                                recyclerView.addItemDecoration(dividerItemDecoration);
                                recyclerView.setAdapter(adapter);}

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                return false;
            }
        });
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("recipes").child(id);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    name.clear();
                    img.clear();
                    idlist.clear();
                   ing.clear();
                    time.clear();

                    for (DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                       recipes recipes= dataSnapshot1.getValue( recipes.class);


                        name.add(recipes.getName());
                        img.add(recipes.getImage());
                        idlist.add(recipes.getId());
                        time.add(recipes.getTime());
                        ing.add(recipes.getIngredients());

                    }

                    adapter = new RecipesAdapter(getApplicationContext(), RecipesActivity.this,name,img,idlist,time,ing,"");

                    linearLayoutManager = new LinearLayoutManager(getApplicationContext());


                    dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), linearLayoutManager.getOrientation());

                    recyclerView.setHasFixedSize(true);
                    recyclerView .setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
                    recyclerView.addItemDecoration(dividerItemDecoration);
                    recyclerView.setAdapter(adapter);}

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
