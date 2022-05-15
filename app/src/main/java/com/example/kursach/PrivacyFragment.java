package com.example.kursach;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PrivacyFragment extends Fragment {

    public EditText editEmail, editName, editPass;
    public Button update;

    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private DatabaseReference myRef;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_privacy, container, false);

        editEmail = view.findViewById(R.id.editEmail);
        editName = view.findViewById(R.id.editName);
        editPass = view.findViewById(R.id.editPass);
        update = view.findViewById(R.id.button3);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        myRef = db.getReference();
        FirebaseUser user = mAuth.getCurrentUser();

        myRef.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                assert user != null;
                Object emailF = snapshot.child(user.getUid()).child("password").getValue();
                assert emailF != null;
                editPass.setHint(emailF.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                assert user != null;
                Object emailF = snapshot.child(user.getUid()).child("email").getValue();
                assert emailF != null;
                editEmail.setHint(emailF.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                assert user != null;
                Object nameF = snapshot.child(user.getUid()).child("name").getValue();
                assert nameF != null;
                editName.setHint(nameF.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        AuthCredential credential = EmailAuthProvider
                .getCredential("user@example.com", "password1234");

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(editName.getText().toString()) && !TextUtils.isEmpty(editPass.getText().toString())){
                    assert user != null;
                    myRef.child("Users").child(user.getUid()).child("name").setValue(editName.getText().toString());
                    myRef.child("Users").child(user.getUid()).child("password").setValue(editPass.getText().toString());
                    user.updatePassword(editPass.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getContext(), "Пароль изменён", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getContext(), "Пароль не изменён", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(getContext(), "Заполните все поля", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}