package com.JanaZiaz.black_fig.Adapter;

import android.content.Context;
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
import com.squareup.picasso.Picasso;

import java.util.List;

public class FeedBackAdapter extends RecyclerView.Adapter<FeedBackAdapter.ViewHolder> {
    RecipesActivity activity;
    private Context context;
    private List<String> name;
    private List<String> time;
    private List<String> date;
    private List<String> img;
    private List<String> comments;

public FeedBackAdapter(Context context, List<String> name, List<String> time, List<String> img, List<String> comments,List<String> date) {
    this.context = context;
    this.name = name;
    this.time = time;
    this.img = img;
    this.date=date;
   this. comments = comments;
}



    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.feedbackview, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

holder.name.setText(name.get(position));
        Picasso.get().load(img.get(position)).placeholder(R.drawable.ic_launcher_background).into(holder.imageView);
        holder.time.setText(time.get(position));
        holder.date.setText(date.get(position));
        holder.comment.setText(comments.get(position));

    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
ImageView imageView ;
TextView name,date,time,comment;
LinearLayout linearLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img);
            name=itemView.findViewById(R.id.name);
            time=itemView.findViewById(R.id.timetxt);
           date=itemView.findViewById(R.id.datetxt);
           comment=itemView.findViewById(R.id.comment);


        }



        @Override
        public void onClick(View view) {


        }
    }
}
