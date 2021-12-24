package com.task.black_fig.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.task.black_fig.AdminPageActivity;
import com.task.black_fig.R;

import static com.task.black_fig.ui.category.CategoryFragment.home;

public class AdminFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_admin, container, false);
        home=false;
        final EditText editText1 = root.findViewById(R.id.edit_text1);
        final EditText editText2 = root.findViewById(R.id.edit_text2);
        final EditText editText3 = root.findViewById(R.id.edit_text3);
        final EditText editText4 = root.findViewById(R.id.edit_text4);
        final Button next = root.findViewById(R.id.btnnext);

        editText1.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

                TextView text = (TextView) getActivity().getCurrentFocus();

                if (editText1.getText().toString().length() == 1)     //size as per your requirement
                {
                    editText2.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });

        editText2.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

                TextView text = (TextView) getActivity().getCurrentFocus();

                if (editText2.getText().toString().length() == 1)     //size as per your requirement
                {
                    editText3.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });

        editText3.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

                TextView text = (TextView) getActivity().getCurrentFocus();

                if (editText3.getText().toString().length() == 1)     //size as per your requirement
                {
                    editText4.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editText1.getText().toString())||TextUtils.isEmpty(editText2.getText().toString()) ||TextUtils.isEmpty(editText3.getText().toString())||TextUtils.isEmpty(editText4.getText().toString()) ) {
                    Toast.makeText(getActivity(), "Please Enter Code Number", Toast.LENGTH_LONG).show();

                }
                else if (editText1.getText().toString().equals("0")&&
                        editText2.getText().toString().equals("0")
                        &&editText3.getText().toString().equals("0")&&editText4.getText().toString().equals("0")){
                    //startActivity(new Intent(getActivity(), AdminPageActivity.class));
                    Intent intent = new Intent(getActivity(),AdminPageActivity.class);
                    intent.putExtra("admin","0");
                    startActivity(intent);
                }
                else if (editText1.getText().toString().equals("1")&&
                        editText2.getText().toString().equals("1")
                        &&editText3.getText().toString().equals("1")&&editText4.getText().toString().equals("1")){
                    //startActivity(new Intent(getActivity(), AdminPageActivity.class));
                    Intent intent = new Intent(getActivity(),AdminPageActivity.class);
                    intent.putExtra("admin","1");
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getActivity(), "Invalid Code", Toast.LENGTH_LONG).show();
                }
            }
        });
        return root;

    }
}