package com.JanaZiaz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.JanaZiaz.black_fig.Adapter.CategoryAdapter;
import com.JanaZiaz.black_fig.Adapter.CategoryAdminAdapter;
import com.JanaZiaz.black_fig.Model.Category;
import com.JanaZiaz.black_fig.R;
import com.JanaZiaz.black_fig.ui.category.CategoryFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminEditCategoryActivity extends AppCompatActivity {
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    Context context ;
    private List<String> name=new ArrayList<>();

    private List<String> img=new ArrayList<>();

    private List<String> key=new ArrayList<>();
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    RelativeLayout nodata ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_category);
        recyclerView=findViewById(R.id.recyclerViewCatg);

        nodata=findViewById(R.id.nodatafound);
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("category");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    name.clear();
                    img.clear();


                    for (DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                        Category category= dataSnapshot1.getValue(Category.class);


                        name.add(category.getName());
                        img.add(category.getImage());
                        key.add(dataSnapshot1.getKey());

                    }

                    adapter = new CategoryAdminAdapter(getApplicationContext(), AdminEditCategoryActivity.this,name,img,key);

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
