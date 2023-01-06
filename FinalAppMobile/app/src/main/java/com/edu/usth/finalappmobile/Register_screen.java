package com.edu.usth.finalappmobile;

import static android.app.ProgressDialog.show;
import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register_screen extends AppCompatActivity {
    private EditText Email;
    private EditText Password;
    private EditText Username;
    private EditText Phone;
    private CheckBox Male ,Female;
    private Button Signup_btn;
    private TextView Login_link;
    private FirebaseAuth mDataUser_Signup;
    private DatabaseReference mDatabase;
    private FirebaseDatabase database;
    private static final String USER = "user";
    private static final String TAG = "Register_screen";
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        Int();



        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference(USER);
        mDataUser_Signup = FirebaseAuth.getInstance();


        Signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DangKy();
            }
        });
        Login_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent linkregister = new Intent(getApplicationContext(),Register_screen.class);
                startActivity(linkregister);
            }
        });

    }


    private void Int(){
        Email = (EditText) findViewById(R.id.email_signup);
        Password = (EditText) findViewById(R.id.password_signup);
        Username = (EditText) findViewById(R.id.username_signup);
        Phone = (EditText) findViewById(R.id.phone_signup);
        Male = (CheckBox) findViewById(R.id.male_checkbox);
        Female = (CheckBox) findViewById(R.id.female_checkbox);
        Signup_btn = (Button) findViewById(R.id.button_signup);
        Login_link = (TextView) findViewById(R.id.login_link);
    }
    private void DangKy() {
        String email = Email.getText().toString();
        String password = Password.getText().toString();
        String username = Username.getText().toString();
        String phonenumber = Phone.getText().toString();
        String male = Male.getText().toString();
        String female = Female.getText().toString();
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(), "Enter email and password", Toast.LENGTH_SHORT).show();
            return;
        }
        user = new User(email, password, username, phonenumber);
        Authentication(email, password);
    }
    public void Authentication(String email, String password){
        mDataUser_Signup.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mDataUser_Signup.getCurrentUser();
                            updateUI(user);
                            Toast.makeText(Register_screen.this, "You sign up successfully.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Register_screen.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    public void updateUI(FirebaseUser currentUser){
        String keyId = mDatabase.push().getKey();
        mDatabase.child(keyId).setValue(user);
        Intent loginIntent = new Intent(this, MainActivity.class);
        startActivity(loginIntent);
    }

}
