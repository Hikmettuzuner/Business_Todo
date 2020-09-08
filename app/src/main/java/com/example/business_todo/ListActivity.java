package com.example.business_todo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    public ListView listView;
    public ArrayList<Posts> postsArrayList;
    public FirebaseAuth auth;
    public CustomAdapter adapter;
    public String userEmail;
    public  String kullanici;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);
        getSupportActionBar().setTitle("Business ToDo List");
        toolbar.setSubtitle("Produced with Hikmet Tüzüner");
        toolbar.setLogo(R.drawable.ic_public_black_24dp);

        FirebaseDatabase database =  FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Posts");

        auth = FirebaseAuth.getInstance();
        kullanici = auth.getCurrentUser().getEmail();

        listView = (ListView) findViewById(R.id.ListView);
        postsArrayList = new ArrayList<>();
       adapter = new CustomAdapter(ListActivity.this, postsArrayList);
        listView.setAdapter(adapter);


        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull  DataSnapshot dataSnapshot,  String s) {

                Posts posts = dataSnapshot.getValue(Posts.class);
                postsArrayList.add(posts);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Posts posts = postsArrayList.get(i);

                userEmail = posts.getUserEmail();

                    if(kullanici.equals(userEmail)){
                        Intent intent = new Intent(ListActivity.this, DetailsActivity.class);
                        intent.putExtra("object",posts);
                        startActivity(intent);
                        finish();


               }else {
                        Toast.makeText(ListActivity.this,"Kendi Paylaşımınızı Düzenleyebilirsiniz..",Toast.LENGTH_LONG).show();


                    }





            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_post,menu);
        menuInflater.inflate(R.menu.profile_and_signout_post,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_post_item) {

            Intent intent = new Intent(getApplicationContext(), UploadActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }



}
