package com.example.business_todo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailsActivity extends AppCompatActivity {

    TextView companyname, representname,representnumber,datE,howmanypeople;
     Button btnUpdateTask, btnDelete;
     DatabaseReference ref;
    public FirebaseAuth auth;
    Posts posts= null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            posts = bundle.getParcelable("object");
            //Toast.makeText(this,"" + posts.getUserId(),Toast.LENGTH_LONG).show();
        }

        String userId =posts.getUserId();
        auth = FirebaseAuth.getInstance();


        ref = FirebaseDatabase.getInstance().getReference().child("Posts").child(userId);

        companyname =(TextView)findViewById(R.id.postCommentText);
        representname =(TextView)findViewById(R.id.postCommentText2);
        representnumber =(TextView)findViewById(R.id.postCommentText3);
        datE =(TextView)findViewById(R.id.postCommentText4);
        howmanypeople =(TextView)findViewById(R.id.postCommentText5);
        btnDelete = (Button)findViewById(R.id.btnDelete);
        btnUpdateTask = (Button)findViewById(R.id.btnUpdateTask);




       if(posts != null)
       {
           companyname.setText(posts.getUserEventName());
           representname.setText(posts.getUserRepresentName());
           representnumber.setText(posts.getUserRepresentNumber());
           datE.setText(posts.getUserEventDate());
           howmanypeople.setText(posts.getUserEventPeople());
       }
    }


    public void btndelete(View view) {
        ref.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(DetailsActivity.this,"Silme İşlemi Başarılı",Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(DetailsActivity.this, ListActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }



    public void btnupdate(View view) {
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataSnapshot.getRef().child("userEventName").setValue(companyname.getText().toString());
                dataSnapshot.getRef().child("userRepresentName").setValue(representname.getText().toString());
                dataSnapshot.getRef().child("userRepresentNumber").setValue(representnumber.getText().toString());
                dataSnapshot.getRef().child("userEventDate").setValue(datE.getText().toString());
                dataSnapshot.getRef().child("userEventPeople").setValue(howmanypeople.getText().toString());
                Toast.makeText(DetailsActivity.this,"Düzenleme Başarılı",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),ListActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(DetailsActivity.this, ListActivity.class);
        startActivity(intent);
        finish();
    }



}
