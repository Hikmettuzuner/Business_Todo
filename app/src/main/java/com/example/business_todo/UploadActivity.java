package com.example.business_todo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class UploadActivity extends AppCompatActivity {
    EditText postCommentText;
    EditText postCommentText2;
    EditText postCommentText3;
    EditText postCommentText4;
    EditText postCommentText5;
    Button btnSaveTaskk;
    Button btnCancel;

    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference myRef;
    public FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        firebaseDatabase = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        myRef = firebaseDatabase.getReference();

        postCommentText = (EditText)findViewById(R.id.postCommentText);
        postCommentText2 = (EditText)findViewById(R.id.postCommentText2);
        postCommentText3 = (EditText)findViewById(R.id.postCommentText3);
        postCommentText4 = (EditText)findViewById(R.id.postCommentText4);
        postCommentText5 = (EditText)findViewById(R.id.postCommentText5);

        btnSaveTaskk = (Button)findViewById(R.id.btnUpdateTask);
        btnCancel = (Button)findViewById(R.id.btnDelete);

        // Write a message to the database
        // FirebaseDatabase database = FirebaseDatabase.getInstance();
        // DatabaseReference myRef = database.getReference("message");
        //  myRef.setValue("Hello, World!");
        btnSaveTaskk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = auth.getCurrentUser();
                String userEmail = user.getEmail();
                String userComment = postCommentText.getText().toString();
                String userComment2 = postCommentText2.getText().toString();
                String userComment3 = postCommentText3.getText().toString();
                String userComment4 = postCommentText4.getText().toString();
                String userComment5 = postCommentText5.getText().toString();


                if (TextUtils.isEmpty(userComment)) {
                    Toast.makeText(getApplicationContext(), "Lütfen Şirket İsmini Giriniz", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(userComment2)) {
                    Toast.makeText(getApplicationContext(), "Lütfen Temsilci İsmini Giriniz", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(userComment3)) {
                    Toast.makeText(getApplicationContext(), "Lütfen Temsilci Telefonunu Giriniz", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(userComment4)) {
                    Toast.makeText(getApplicationContext(), "Lütfen Tarih Giriniz", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(userComment5)) {
                    Toast.makeText(getApplicationContext(), "Lütfen Kişi Sayısını Giriniz", Toast.LENGTH_SHORT).show();
                    return;
                }

                UUID uuid = UUID.randomUUID();
                String uuidString =uuid.toString();
                myRef.child("Posts").child(uuidString).child("userId").setValue(uuidString);
                myRef.child("Posts").child(uuidString).child("userEmail").setValue(userEmail);
                myRef.child("Posts").child(uuidString).child("userEventName").setValue(userComment);
                myRef.child("Posts").child(uuidString).child("userRepresentName").setValue(userComment2);
                myRef.child("Posts").child(uuidString).child("userRepresentNumber").setValue(userComment3);
                myRef.child("Posts").child(uuidString).child("userEventDate").setValue(userComment4);
                myRef.child("Posts").child(uuidString).child("userEventPeople").setValue(userComment5);

                Toast.makeText(UploadActivity.this,"Paylaşım Başarılı",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),ListActivity.class);
                startActivity(intent);
            }
        });
    }

    public void bgbtncancel(View view) {
        Intent intent = new Intent(getApplicationContext(),ListActivity.class);
        startActivity(intent);

    }
}
