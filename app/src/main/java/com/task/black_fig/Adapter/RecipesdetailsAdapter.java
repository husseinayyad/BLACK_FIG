package com.task.black_fig.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.task.black_fig.R;
import com.task.black_fig.RecipesActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipesdetailsAdapter extends RecyclerView.Adapter<RecipesdetailsAdapter.ViewHolder> {
    RecipesActivity activity;
    private Context context;
    private List<String> name;
    private List<String> time;
    private List<String> img;
    private List<String> Ingredients;
public RecipesdetailsAdapter(Context context, List<String> name, List<String> time, List<String> img, List<String> ingredients) {
    this.context = context;
    this.name = name;
    this.time = time;
    this.img = img;
   this. Ingredients = ingredients;
}



    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_recipes_detiles, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

holder.textView.setText(name.get(position));
        Picasso.get().load(img.get(position)).placeholder(R.drawable.ic_launcher_background).into(holder.imageView);
        holder.time.setText(time.get(position)+"Min");
        holder.Ingredients.setText(Ingredients.get(position));

    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
ImageView imageView ;
TextView textView ;
LinearLayout linearLayout;
        TextView time , Ingredients;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img);
            textView=itemView.findViewById(R.id.name);
            time=itemView.findViewById(R.id.timetxt);
            Ingredients=itemView.findViewById(R.id.IngredientsData);


        }



        @Override
        public void onClick(View view) {


        }
    }
}
