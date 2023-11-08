package com.example.projectprm392.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.projectprm392.R;

public class HistoryFragment extends Fragment {

    public HistoryFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

//        boolean hasHistoryOrders = checkIfHistoryOrdersExist();
//
//        TextView noOrdersTextView = view.findViewById(R.id.noOrdersTextView);
//
//        if (!hasHistoryOrders) {
//            noOrdersTextView.setVisibility(View.VISIBLE);
//        } else {
//            noOrdersTextView.setVisibility(View.GONE);
//        }

        // Rest of your onCreateView logic

        return view;
    }

}