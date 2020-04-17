package com.JanaZiaz.black_fig.ui.category;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.JanaZiaz.black_fig.Adapter.CategoryAdapter;
import com.JanaZiaz.black_fig.Model.Category;
import com.JanaZiaz.black_fig.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {

    private CategoryViewModel categoryViewModel;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    Context context ;
    private List<String> name=new ArrayList<>();

    private List<String> img=new ArrayList<>();

    private List<String> key=new ArrayList<>();
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    RelativeLayout nodata ;
   public static  boolean home=false;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        categoryViewModel =
                ViewModelProviders.of(this).get(CategoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView=root.findViewById(R.id.recyclerViewCatg);
        home=true;
        nodata=root.findViewById(R.id.nodatafound);
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

             adapter = new CategoryAdapter(getActivity().getApplicationContext(), CategoryFragment.this,name,img,key);

             linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());


             dividerItemDecoration = new DividerItemDecoration(getActivity().getApplicationContext(), linearLayoutManager.getOrientation());

             recyclerView.setHasFixedSize(true);
             recyclerView .setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 2));
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
 /*name.add(0,"breakfast");
 img.add(0,"https://www.thedailymeal.com/sites/default/files/2019/01/18/0-Utah-MAIN2.jpg");
        name.add(1,"lunch");
        img.add(1,"https://i.ndtvimg.com/i/2015-04/lunch-recipes_625x350_81429708181.jpg");
        name.add(2,"diner");
        img.add(2,"https://goodluckgrill.com/wp-content/uploads/2012/08/american-homestyle-food-slider-2.jpg");
        name.add(3,"snack");
        img.add(3,"https://www.supermarketperimeter.com/ext/resources/2019/5/SnackVariety_Lead.jpg?1558559321");
        name.add(4,"sweets");
        img.add(4,"https://www.zalatimosweets.com/wp-content/uploads/2017/05/IMG_5930_1.jpg");
        name.add(5,"Healthy food");
        img.add(5,"https://res.cloudinary.com/sanitarium/image/fetch/q_auto/https://www.sanitarium.com.au/getmedia%2Fae51f174-984f-4a70-ad3d-3f6b517b6da1%2Ffruits-vegetables-healthy-fats.jpg%3Fwidth%3D1180%26height%3D524%26ext%3D.jpg");*/

        return root;
    }

}