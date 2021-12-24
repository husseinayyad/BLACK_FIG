package com.task.black_fig.ui.feedback;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.task.black_fig.Adapter.FeedBackAdapter;
import com.task.black_fig.AddCommentActivity;
import com.task.black_fig.Model.feedback;
import com.task.black_fig.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.task.black_fig.ui.category.CategoryFragment.home;

public class FeedBackFragment extends Fragment {


    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    Context context ;
    private List<String> name=new ArrayList<>();

    private List<String> img=new ArrayList<>();
    private List<String> comment=new ArrayList<>();
    private List<String>time=new ArrayList<>();
    private List<String> date=new ArrayList<>();
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_feedback, container, false);
        home=false;
        final Button button = root.findViewById(R.id.addfeedback);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddCommentActivity.class));
            }
        });
        recyclerView=root.findViewById(R.id.recyclerViewCatg);
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("FeedBack");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    name.clear();
                    img.clear();
                    comment.clear();
                    date.clear();
                    time.clear();

                    for (DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                      feedback feedback= dataSnapshot1.getValue(feedback.class);


                        name.add(feedback.getName());
                        img.add(feedback.getImage());
                        comment.add(feedback.getComment());
                        date.add(feedback.getData());
                        time.add(feedback.getTime());

                    }}
                adapter = new FeedBackAdapter(getActivity().getApplicationContext(), name,time,img,comment,date);

                linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());


                dividerItemDecoration = new DividerItemDecoration(getActivity().getApplicationContext(), linearLayoutManager.getOrientation());

                recyclerView.setHasFixedSize(true);
                recyclerView .setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 1));
                recyclerView.addItemDecoration(dividerItemDecoration);
                recyclerView.setAdapter(adapter);
            }

            @SuppressLint("WrongConstant")
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.println(0,databaseError.getMessage(),databaseError.getMessage());

            }
        });
        return root;
    }
}