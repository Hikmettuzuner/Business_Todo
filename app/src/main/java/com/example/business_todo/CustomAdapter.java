package com.example.business_todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
///Başta Context ve Arraylisti oluştur burda. Ondan sonra Generate yap ve Constructre hallet daha sonra extends BaseAdapter de ve uyarıdaki classları import et
public class CustomAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Posts> postsArrayList;

    public CustomAdapter(Context context, ArrayList<Posts> postsArrayList) {
        this.context = context;
        this.postsArrayList = postsArrayList;
    }

    @Override
    public int getCount() {
        return postsArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return postsArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_does,parent,false);
        }

        TextView  userEmailText =view.findViewById(R.id.userEmailTextViewCustomView);
        TextView  userEventText =view.findViewById(R.id.userEventTextViewCustomView);
        TextView  userRepresentNameText =view.findViewById(R.id.userRepresentNameTextViewCustomView);
        TextView  userRepresentNumberText =view.findViewById(R.id.userRepresentNumberTextViewCustomView);
        TextView  userDateText =view.findViewById(R.id.userDateTextViewCustomView);
        TextView  userEventPeopleText =view.findViewById(R.id.userEventPeopleTextViewCustomView);

        Posts posts = (Posts) getItem(i);

        userEmailText.setText(posts.getUserEmail());
        userEventText.setText(posts.getUserEventName());
        userRepresentNameText.setText(posts.getUserRepresentName());
        userRepresentNumberText.setText(posts.getUserRepresentNumber());
        userDateText.setText(posts.getUserEventDate());
        userEventPeopleText.setText(posts.getUserEventPeople());

        return view;
    }
}
