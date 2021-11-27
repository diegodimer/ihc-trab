package com.example.ppgc;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_profile, container, false);
        Bundle bundle = getArguments();
        String userName = bundle.getString("name");
        String email = bundle.getString("email");
        String phone = bundle.getString("phone");
        String advisor = bundle.getString("advisor");
        TextView userNameView = (TextView) view.findViewById(R.id.textView2);
        TextView emailView = (TextView) view.findViewById(R.id.textView5);
        TextView phoneView = (TextView) view.findViewById(R.id.textView6);
        TextView advisorView = (TextView) view.findViewById(R.id.textView7);

        userNameView.setText(userName);
        emailView.setText(email);
        phoneView.setText(phone);
        advisorView.setText(advisor);

        return view;
    }
}
