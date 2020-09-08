package com.example.business_todo.AccountActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.business_todo.ListActivity;
import com.example.business_todo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;
public class LoginActivity extends AppCompatActivity {

    ConstraintLayout constraintLayout;
    TextView tvTimeMsg;
    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private Button btn_signup, btn_signin, btnReset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        constraintLayout = findViewById(R.id.container);

        tvTimeMsg = findViewById(R.id.tv_time_msg);


        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay>0 && timeOfDay <12)
        {
        constraintLayout.setBackground(getDrawable(R.drawable.good_morning_img));
        tvTimeMsg.setText("Günaydın");
        }else if(timeOfDay>=12 && timeOfDay < 16)
        {
            constraintLayout.setBackground(getDrawable(R.drawable.good_morning_img));
            tvTimeMsg.setText("İyi Günler");
        }else if(timeOfDay>=16 && timeOfDay < 21)
        {
            constraintLayout.setBackground(getDrawable(R.drawable.good_night_img));
            tvTimeMsg.setText("İyi Akşamlar");
        }else if(timeOfDay>=21 && timeOfDay < 24)
        {
        constraintLayout.setBackground(getDrawable(R.drawable.good_night_img));
        tvTimeMsg.setText("İyi Geceler");
        }


        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, ListActivity.class));
            finish();
        }

     //   setContentView(R.layout.activity_login);

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btn_signup = (Button) findViewById(R.id.btn_signup);
        btn_signin = (Button) findViewById(R.id.btn_login);
        btnReset = (Button) findViewById(R.id.btn_reset_password);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
            }
        });

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Lütfen Mail Adresinizi Giriniz", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Lütfen Şifre Giriniz", Toast.LENGTH_SHORT).show();
                    return;
                }



                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        inputPassword.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(LoginActivity.this, ListActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });



        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        }
        }
