package com.JanaZiaz.black_fig;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.JanaZiaz.black_fig.Model.Category;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class AddRecipesActivity extends AppCompatActivity {
 //   private List<String> name=new ArrayList<>();
    List<String> name = new ArrayList<String>();
    ImageView imageView ;
    Button add ;
    EditText name1 ,time ,Ingredients;
    DatabaseReference databaseReference;
    private StorageReference storageReference;
    private ProgressDialog loadingBar;
    private static final int ERROR_DIALOG_REQUEST = 9001;
    static int PReqCode = 1;
    static int REQUESCODE = 1;
    private static final int galaryn = 1;
    private Uri imageuri;
    String savedata, savetime, id, imgurl;
    String category= "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipes);
        final Spinner spin =  findViewById(R.id.sp1);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("category");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){


                    for (DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                        Category category= dataSnapshot1.getValue(Category.class);


                       name.add(category.getName());


                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        imageView=findViewById(R.id.imgcatg);
        add=findViewById(R.id.btnAddCatg);
        name1=findViewById(R.id.recipename);
        time=findViewById(R.id.time);
        Ingredients=findViewById(R.id.Ingredients);
        loadingBar = new ProgressDialog(this);
        storageReference = FirebaseStorage.getInstance().getReference().child("image");
        databaseReference = FirebaseDatabase.getInstance().getReference("recipes");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opengallry();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validaadata();
            }
        });
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, name);
        dataAdapter.setDropDownViewResource(R.layout.layout_spinner_item);
        spin.setAdapter(dataAdapter);
        //Creating the ArrayAdapter instance having the country list

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void opengallry() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUESCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUESCODE && resultCode == RESULT_OK && data != null) {
            imageuri = data.getData();
            imageView.setImageURI(imageuri);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void validaadata() {


        if (TextUtils.isEmpty(name1.getText().toString())  ) {
            Toast.makeText(this, "Please Enter Category Name", Toast.LENGTH_LONG).show();

        }
        else if (imageuri==null){
            Toast.makeText(this, "Please Chose Image", Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(time.getText().toString())){
            Toast.makeText(this, "lease Enter  Time", Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(Ingredients.getText().toString())){
            Toast.makeText(this, "Please Enter  Ingredients ", Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(category)){
            Toast.makeText(this, "Please Select Recipes Category", Toast.LENGTH_LONG).show();
        }
        else {
            saveimage();
        }
    }

    private void saveimage() {


        loadingBar.setTitle("Add Recipes");
        loadingBar.setMessage("Wait.... ");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();
        Calendar calendar = Calendar.getInstance();

        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(SimpleDateFormat.YEAR_ABBR_MONTH);
        savedata = dateFormat.format(calendar.getTime());

        java.text.SimpleDateFormat dateFormat1 = new java.text.SimpleDateFormat("HH:mm:ss a");
        savetime = dateFormat1.format(calendar.getTime());
        id = savedata + savetime;

        final StorageReference filepath = storageReference.child("img2" + id + ".jpg");
        final UploadTask uploadTask = filepath.putFile(imageuri);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message = e.toString();
                Toast.makeText(getApplication(), " error: " + message, Toast.LENGTH_LONG).show();
                loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getApplication(), "  image uploaded successfully ", Toast.LENGTH_LONG).show();
                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();


                        }
                        imgurl = filepath.getDownloadUrl().toString();
                        return filepath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            imgurl = task.getResult().toString();

                            Toast.makeText(getApplication(), "  image save to database", Toast.LENGTH_LONG).show();
                            savedatadatabase();
                        }
                    }
                });
            }
        });


    }


    private void savedatadatabase() {




        final HashMap<String, Object> objectHashMap = new HashMap<>();
        objectHashMap.put("id", category);
        objectHashMap.put("data", savedata);
        objectHashMap.put("time", time.getText().toString());
        objectHashMap.put("ingredients",Ingredients.getText().toString());



        objectHashMap.put("name", name1.getText().toString());

        objectHashMap.put("image", imgurl);



        databaseReference.child(category).push().setValue(objectHashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                    loadingBar.dismiss();
                    finish();


                    Toast.makeText(getApplication(), "Done", Toast.LENGTH_LONG).show();
                } else {
                    loadingBar.dismiss();
                    Toast.makeText(getApplication(), "error  : " + task.getException().toString(), Toast.LENGTH_LONG).show();

                }
            }
        });




    }

}
