package com.JanaZiaz.black_fig.ui.share;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.JanaZiaz.black_fig.R;

import static com.JanaZiaz.black_fig.ui.category.CategoryFragment.home;

public class ShareFragment extends Fragment {

    private ShareViewModel shareViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel =
                ViewModelProviders.of(this).get(ShareViewModel.class);
        View root = inflater.inflate(R.layout.fragment_share, container, false);
       Button share=root.findViewById(R.id.share);
        home=false;
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              /* String text = Ingredientstxt;
                Uri pictureUri = Uri.parse(img);

                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, text);
               shareIntent.putExtra(Intent.EXTRA_STREAM, );
                shareIntent.setType("image/*");
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(Intent.createChooser(shareIntent, "Share Recipes..."));
*/
                Intent shareIntent;

                shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //shareIntent.putExtra(Intent.EXTRA_TEXT,img);
                shareIntent.putExtra(Intent.EXTRA_TEXT,"Hey please check this application " + "https://play.google.com/store/apps/details?id=" +getActivity().getPackageName());
                shareIntent.setType("text/*");
                startActivity(Intent.createChooser(shareIntent,"Share with"));

            }
        });
        return root;
    }
}