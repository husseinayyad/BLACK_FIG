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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;
import java.util.HashMap;

public class AddCategoryActivity extends AppCompatActivity {
ImageView imageView ;
Button add ;
EditText name ;
    DatabaseReference databaseReference;
    private StorageReference storageReference;
    private ProgressDialog loadingBar;
    private static final int ERROR_DIALOG_REQUEST = 9001;
    static int PReqCode = 1;
    static int REQUESCODE = 1;
    private static final int galaryn = 1;
    private Uri imageuri;
    String savedata, savetime, id, imgurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        imageView=findViewById(R.id.imgcatg);
        add=findViewById(R.id.btnAddCatg);
        name=findViewById(R.id.categoryname);
        loadingBar = new ProgressDialog(this);
        storageReference = FirebaseStorage.getInstance().getReference().child("image");
        databaseReference = FirebaseDatabase.getInstance().getReference("category");
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


        if (TextUtils.isEmpty(name.getText().toString())  ) {
            Toast.makeText(this, "Please Enter Category Name", Toast.LENGTH_LONG).show();

        }
        else if (imageuri==null){
            Toast.makeText(this, "Please Chose Image", Toast.LENGTH_LONG).show();
        }
        else {
            saveimage();
        }
    }

    private void saveimage() {


        loadingBar.setTitle("Add Category");
        loadingBar.setMessage("Wait.... ");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();
        Calendar calendar = Calendar.getInstance();

        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(SimpleDateFormat.YEAR_ABBR_MONTH);
        savedata = dateFormat.format(calendar.getTime());

        java.text.SimpleDateFormat dateFormat1 = new java.text.SimpleDateFormat("HH:mm:ss a");
        savetime = dateFormat1.format(calendar.getTime());
        id = savedata + savetime;

        final StorageReference filepath = storageReference.child("img" + id + ".jpg");
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
                            saveprointodatabase();
                        }
                    }
                });
            }
        });


    }

    private void saveprointodatabase() {




        final HashMap<String, Object> objectHashMap = new HashMap<>();
        objectHashMap.put("id", name.getText().toString());
        objectHashMap.put("data", savedata);
        objectHashMap.put("time", savetime);



        objectHashMap.put("name", name.getText().toString());

        objectHashMap.put("image", imgurl);



        databaseReference.push().setValue(objectHashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
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




    }}

