package com.JanaZiaz.black_fig;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.JanaZiaz.black_fig.Adapter.RecipesAdapter;
import com.JanaZiaz.black_fig.Model.recipes;
import com.JanaZiaz.black_fig.ui.category.CategoryViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecipesActivity extends AppCompatActivity {
    private CategoryViewModel categoryViewModel;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    Context context ;
    private List<String> name=new ArrayList<>();
ImageView imageView;
    private List<String> idlist=new ArrayList<>();
    private List<String> img=new ArrayList<>();
    private List<String> time=new ArrayList<>();
    private List<String> ing=new ArrayList<>();
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    SearchView searchView ;
    boolean isbyname =true;
    boolean isbying =false;
    RelativeLayout nodata;
    List<String> name1=new ArrayList<>();
String searchtext="";
    List<String> idlist1=new ArrayList<>();
    List<String> img1=new ArrayList<>();
    List<String> time1=new ArrayList<>();
    List<String> ing1=new ArrayList<>();
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        recyclerView=findViewById(R.id.recyclerView2);
        final String id = getIntent().getStringExtra("id");
        searchView= findViewById(R.id.search);
        imageView=findViewById(R.id.imgfilter);
        nodata=findViewById(R.id.nodatafound);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final android.app.Dialog dialog = new android.app.Dialog(RecipesActivity.this);
                dialog.setTitle("Details");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialog);

               TextView byname = dialog.findViewById(R.id.byname);
                TextView bying= dialog.findViewById(R.id.bying);
byname.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        isbyname=true;
        isbying=false;
        searchView.setQueryHint("By Name");
        if (!searchView.getQuery().toString().isEmpty()){
            searchView.setQuery(searchtext+" ",false);
        }


        dialog.dismiss();
    }
});
bying.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        isbyname=false;
        isbying=true;
        dialog.dismiss();
        searchView.setQueryHint("By Ingredients");

        if (!searchView.getQuery().toString().isEmpty()){
            searchView.setQuery(searchtext+" ",false);
        }

    }
});


                dialog.show();
            }
        });
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
                    searchtext=newText;
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
                            nodata.setVisibility(View.GONE);
                            for (DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                                recipes recipes= dataSnapshot1.getValue( recipes.class);


                                name.add(recipes.getName());
                                img.add(recipes.getImage());
                                idlist.add(recipes.getId());
                                time.add(recipes.getTime());
                                ing.add(recipes.getIngredients());

                            }
                            if (isbying){
                                name1.clear();
                                img1.clear();
                                idlist1.clear();
                                time1.clear();
                                ing1.clear();

                            for(int i = 0 ;i<ing.size();i++)
                            {
                                if(ing.get(i).toLowerCase().trim().contains(newText.toLowerCase().trim())){
                                    nodata.setVisibility(View.GONE);
                                    name1.add(name.get(i));
                                    img1.add(img.get(i));
                                    idlist1.add(idlist.get(i));
                                    time1.add(time.get(i));
                                    ing1.add(ing.get(i));

                                }
                                else {
                                   // nodata.setVisibility(View.VISIBLE);
                                }

                                if (name1.isEmpty()){
                                    nodata.setVisibility(View.VISIBLE);
                                }
                            }
                                adapter = new RecipesAdapter(getApplicationContext(), RecipesActivity.this,name1,img1,idlist1,time1,ing1,"");

                                linearLayoutManager = new LinearLayoutManager(getApplicationContext());


                                dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), linearLayoutManager.getOrientation());

                                recyclerView.setHasFixedSize(true);
                                recyclerView .setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
                                recyclerView.addItemDecoration(dividerItemDecoration);
                                recyclerView.setAdapter(adapter);

                           }

                            else {
                                name1.clear();
                                img1.clear();
                                idlist1.clear();
                                time1.clear();
                                ing1.clear();

                                for(int i = 0 ;i<name.size();i++)
                                {
                                    if(name.get(i).toLowerCase().trim().contains(newText.toLowerCase().trim())){
                                        nodata.setVisibility(View.GONE);
                                        name1.add(name.get(i));
                                        img1.add(img.get(i));
                                        idlist1.add(idlist.get(i));
                                        time1.add(time.get(i));
                                        ing1.add(ing.get(i));

                                    }
                                    else {
                                        //nodata.setVisibility(View.VISIBLE);
                                    }
                                    if (name1.isEmpty()){
                                        nodata.setVisibility(View.VISIBLE);
                                    }


                                }
                                adapter = new RecipesAdapter(getApplicationContext(), RecipesActivity.this,name1,img1,idlist1,time1,ing1,"");

                                linearLayoutManager = new LinearLayoutManager(getApplicationContext());


                                dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), linearLayoutManager.getOrientation());

                                recyclerView.setHasFixedSize(true);
                                recyclerView .setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
                                recyclerView.addItemDecoration(dividerItemDecoration);
                                recyclerView.setAdapter(adapter);
                            }
                        }
                        else {
                            nodata.setVisibility(View.VISIBLE);
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        nodata.setVisibility(View.VISIBLE);
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
                                nodata.setVisibility(View.GONE);
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
                                recyclerView.setAdapter(adapter);

                          }
                            else {
                                nodata.setVisibility(View.VISIBLE);
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            nodata.setVisibility(View.VISIBLE);
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
                    nodata.setVisibility(View.GONE);
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
                    recyclerView.setAdapter(adapter);
}
                else {
                    nodata.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                nodata.setVisibility(View.VISIBLE);
            }
        });



    }
}
