package com.brok.patapata;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ReportFragment extends Fragment {

    private RecyclerView reportList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_report, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.
                getInstance().getCurrentUser().getUid()).child("reports");
        reportList = (RecyclerView) getView().findViewById(R.id.reportrecyler);
        reportList.setHasFixedSize(true);
        reportList.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

}