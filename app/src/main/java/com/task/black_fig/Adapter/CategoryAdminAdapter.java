package com.task.black_fig.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.task.black_fig.AdminEditCategoryInformationActivity;
import com.task.black_fig.AdminEditRecipesActivity;
import com.task.black_fig.R;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdminAdapter extends RecyclerView.Adapter<CategoryAdminAdapter.ViewHolder> {
    Activity  activity;
    private Context context;
    private List<String> name;

    private List<String> img;
    private List<String> key;
    public CategoryAdminAdapter(Context context, Activity activity, List<String> name, List<String> img, List<String> key) {
        this.context = context;
        this.name = name;
        this.img=img;
        this.key=key;
        this.activity=activity ;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_view_list_item_admin_category, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

holder.textView.setText(name.get(position));
        Picasso.get().load(img.get(position)).placeholder(R.drawable.ic_launcher_background).into(holder.imageView);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(context.getApplicationContext(), AdminEditRecipesActivity.class);
                intent.putExtra("id",key.get(position));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("category").child(key.get(position));
                mDatabase.removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                        notifyDataSetChanged();
                    }
                });
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(context.getApplicationContext(), AdminEditCategoryInformationActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("name",name.get(position));
                intent.putExtra("img",img.get(position));
                intent.putExtra("key",key.get(position));

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
ImageView imageView , edit,remove ;
TextView textView ;
LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.cardViewImageView);
            textView=itemView.findViewById(R.id.cardViewTextView);
linearLayout=itemView.findViewById(R.id.ly1);
edit=itemView.findViewById(R.id.edit);
remove=itemView.findViewById(R.id.remove);

        }



        @Override
        public void onClick(View view) {


        }
    }
}
