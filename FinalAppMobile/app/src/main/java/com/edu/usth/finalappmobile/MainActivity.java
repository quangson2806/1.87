package com.edu.usth.finalappmobile;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.net.Authenticator;

public class MainActivity extends AppCompatActivity {
    private EditText Username;
    private EditText Password;
    private Button Login_btn;
    private TextView Signup_link;
    private FirebaseAuth mDataUser;

    private TabLayout mTabLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Int();



        mDataUser = FirebaseAuth.getInstance();

        Login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DangNhap();

            }
        });
        Signup_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent linkregister = new Intent(getApplicationContext(), Register_screen.class);
                startActivity(linkregister);
            }
        });



    }


    // khai bao doi tuong
    private void Int(){
        Username = (EditText) findViewById(R.id.username);
        Password = (EditText) findViewById(R.id.password_login);
        Login_btn = (Button) findViewById(R.id.button_login);
        Signup_link = (TextView) findViewById(R.id.signup_link);
    }
    private void DangNhap() {
        String username = Username.getText().toString();
        String password = Password.getText().toString();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter your email and password !!", Toast.LENGTH_SHORT).show();
       return;
        }
        mDataUser.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mDataUser.getCurrentUser();
                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            updateUI(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }



    public void updateUI(FirebaseUser currentUser){
        Intent profileIntent = new Intent(this, Home_Page_Screen.class);
        profileIntent.putExtra("email", "hoaiphuong@gmail.com");
        startActivity(profileIntent);
    }

}
