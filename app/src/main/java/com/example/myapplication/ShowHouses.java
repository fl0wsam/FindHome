package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import static androidx.recyclerview.widget.RecyclerView.*;

public class ShowHouses extends AppCompatActivity {

    private RecyclerView mBlogList;
    private DatabaseReference mDatabase;
    String newString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_houses);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("streetName");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("streetName");
        }

        mDatabase = FirebaseDatabase.getInstance().getReference().child("kijiji");
        mDatabase.keepSynced(true);

        mBlogList = (RecyclerView)findViewById(R.id.myrecycleview);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<kijijiData,HouseViewHolder>firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<kijijiData, HouseViewHolder>
                (kijijiData.class,R.layout.blog_row, HouseViewHolder.class,mDatabase) {
            @Override
            protected void populateViewHolder(HouseViewHolder houseViewHolder, kijijiData blog, int i) {

                houseViewHolder.setTitle(blog.getAddress1());
                houseViewHolder.setDesc(blog.getAddress2());
                houseViewHolder.setImage(getApplicationContext(),blog.getImage());



            }
        };

        mBlogList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class HouseViewHolder extends ViewHolder
    {
        View mView;

        public HouseViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setTitle(String address1){
            TextView post_title = (TextView)mView.findViewById(R.id.post_title);
            post_title.setText(address1);
        }
        public void setDesc(String address2){
            TextView post_desc = (TextView)mView.findViewById(R.id.post_desc);
            post_desc.setText(address2);
        }
        public void setImage(Context ctx,String image){
            ImageView post_Image = (ImageView)mView.findViewById(R.id.post_image);
            Picasso.get().load(image).into(post_Image);
        }
    }
}