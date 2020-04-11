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
import com.JanaZiaz.black_fig.RecipesdetailsActivity;
import com.JanaZiaz.black_fig.ui.home.HomeFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {
    RecipesActivity activity;
    private Context context;
    private List<String> name;
    private List<String> id;
    private List<String> img;

    private List<String> time;

    private List<String> Ingredients;
    public RecipesAdapter(Context context, RecipesActivity activity, List<String > name, List<String > img, List<String > id,List<String > time,List<String > Ingredients) {
        this.context = context;
        this.name = name;
        this.img=img;
        this.id=id;
        this.time=time;
        this.Ingredients=Ingredients;
        this.activity=activity ;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_view, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

holder.textView.setText(name.get(position));
        Picasso.get().load(img.get(position)).placeholder(R.drawable.ic_launcher_background).into(holder.imageView);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(context.getApplicationContext(), RecipesdetailsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("name",name.get(position));
                intent.putExtra("img",img.get(position));
                intent.putExtra("time",time.get(position));
                intent.putExtra("Ingredients",Ingredients.get(position));
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
            imageView=itemView.findViewById(R.id.img);
            textView=itemView.findViewById(R.id.name);
linearLayout=itemView.findViewById(R.id.ly1);

        }



        @Override
        public void onClick(View view) {


        }
    }
}
