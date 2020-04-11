package com.JanaZiaz.black_fig;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminPageActivity extends AppCompatActivity {
Button  Category ,recipes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
Category=findViewById(R.id.btncatg);
recipes=findViewById(R.id.btnrecipe);
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
}
