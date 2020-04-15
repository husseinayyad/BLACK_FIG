package com.JanaZiaz.black_fig;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.JanaZiaz.black_fig.ui.category.CategoryFragment;

public class AdminPageActivity extends AppCompatActivity {
Button  Category ,recipes;
String admin;
TextView name ;
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
Category=findViewById(R.id.btncatg);
recipes=findViewById(R.id.btnrecipe);
name=findViewById(R.id.adminname);
imageView=findViewById(R.id.adminimage);

admin=getIntent().getStringExtra("admin");
if (admin.equals("1")){
    name.setText("Admin : Dana Hazem");
    //here set dana image
    imageView.setImageResource(R.drawable.appicon2);

}
Category.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(),AddCategoryActivity.class));
    }
});
recipes.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(),AddRecipesActivity.class));
    }
});
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


    }
}
