package com.JanaZiaz.black_fig;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.JanaZiaz.black_fig.Adapter.RecipesAdapter;
import com.JanaZiaz.black_fig.Adapter.RecipesdetailsAdapter;
import com.JanaZiaz.black_fig.Model.recipes;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class RecipesdetailsActivity extends AppCompatActivity {
ImageView imageView ;
    TextView timetxt , Ingredients,nametxt;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    Context context ;
    private List<String> name=new ArrayList<>();
Button share ;
boolean perm= false;
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
        final String name = getIntent().getStringExtra("name");
        final String img = getIntent().getStringExtra("img");
        String time = getIntent().getStringExtra("time");
        final String Ingredientstxt = getIntent().getStringExtra("Ingredients");
        Picasso.get().load(img).into(imageView);
        timetxt.setText(time+"Min");
        Ingredients.setText(Ingredientstxt);
        nametxt.setText(name);
        share=findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              /* String text = Ingredientstxt;
                Uri pictureUri = Uri.parse(img);

                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, text);
               shareIntent.putExtra(Intent.EXTRA_STREAM, );
                shareIntent.setType("image/*");
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(Intent.createChooser(shareIntent, "Share Recipes..."));
*/
                Intent shareIntent;

                shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           //     shareIntent.putExtra(Intent.EXTRA_TEXT,img);
                shareIntent.putExtra(Intent.EXTRA_TEXT,name+ "\n" +
                        "Ingredients :"+ "\n" +Ingredientstxt);
                shareIntent.setType("text/*");
                startActivity(Intent.createChooser(shareIntent,"Share with"));

            }
        });



    }




}
