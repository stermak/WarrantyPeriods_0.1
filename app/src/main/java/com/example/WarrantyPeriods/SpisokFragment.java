package com.example.WarrantyPeriods;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SpisokFragment extends Fragment {

    private RecyclerView recyclerView;
    spisokAdapter adapter;
    DatabaseReference mbase;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mbase = FirebaseDatabase.getInstance().getReference();
        recyclerView = view.findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        FirebaseRecyclerOptions<spisok> options = new FirebaseRecyclerOptions.Builder<spisok>().setQuery(mbase, spisok.class).build();
        adapter = new spisokAdapter(options);
        recyclerView.setAdapter(adapter);
        Button BtnCreate = view.findViewById(R.id.BtnCreate);
        BtnCreate.setOnClickListener(viewCreate -> {
            Bundle bundleCreate = new Bundle();
            Navigation.findNavController(view).navigate(R.id.action_spisokFragment_to_createFragment, bundleCreate);
        });
    }

    @Override
    public void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_spisok, container, false);
    }
}