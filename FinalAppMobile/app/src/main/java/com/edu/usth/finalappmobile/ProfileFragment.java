package com.edu.usth.finalappmobile;
import static android.content.Intent.getIntent;
import static android.content.Intent.getIntentOld;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.fragment.app.Fragment;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView emailUser,userNameUser,phoneUser;
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private static final String USER = "user";
    private String email;
    private FirebaseAuth mAuth;
    FirebaseStorage storage;
    ImageView profileImage;
    private StorageReference storageRef;


    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();

        return fragment;
    }

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        emailUser = view.findViewById(R.id.email_profile);
        userNameUser = view.findViewById(R.id.username_profile);
        phoneUser = view.findViewById(R.id.phone_avatar);
        profileImage = view.findViewById(R.id.imageView3);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        email = auth.getCurrentUser().getEmail();
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        //Upload anh
        //final StorageReference reference = storage.getReference(USER).child("profile_pic")
        //.child(FirebaseAuth.getInstance().getUid());



        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,33);
            }
        });

        userRef = database.getReference(USER);
        userRef.addValueEventListener(new ValueEventListener() {



            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.child("email").getValue().equals(email)) {
                        emailUser.setText(ds.child("email").getValue(String.class));
                        userNameUser.setText(ds.child("username").getValue(String.class));
                        phoneUser.setText(ds.child("phone").getValue(String.class));

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data.getData() != null){
            Uri profileUri = data.getData();
            profileImage.setImageURI(profileUri);


            final StorageReference reference = storage.getReference(USER).child("profile_pic")
                    .child(FirebaseAuth.getInstance().getUid());


//            userRef.child(mAuth.getCurrentUser().getUid().toString()).setValue(profileUri);

            reference.putFile(profileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_SHORT).show();

                }
            });










        }
    }
}