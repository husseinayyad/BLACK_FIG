package com.JanaZiaz.black_fig.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.JanaZiaz.black_fig.R;
import com.JanaZiaz.black_fig.RecipesActivity;
import com.JanaZiaz.black_fig.ui.category.CategoryFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    CategoryFragment activity;
    private Context context;
    private List<String> name;
    private List<String> key;
    private List<String> img;
    public CategoryAdapter(Context context, CategoryFragment activity, List<String> name, List<String> img, List<String> key) {
        this.context = context;
        this.name = name;
        this.img=img;
        this.key=key;
        this.activity=activity ;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_view_list_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

holder.textView.setText(name.get(position));
        Picasso.get().load(img.get(position)).placeholder(R.drawable.ic_launcher_background).into(holder.imageView);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(context.getApplicationContext(), RecipesActivity.class);
                intent.putExtra("id",key.get(position));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
ImageView imageView ;
TextView textView ;
LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.cardViewImageView);
            textView=itemView.findViewById(R.id.cardViewTextView);
linearLayout=itemView.findViewById(R.id.ly);

        }



        @Override
        public void onClick(View view) {


        }
    }
}
