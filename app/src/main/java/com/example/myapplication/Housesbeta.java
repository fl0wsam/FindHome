package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;

public class Housesbeta extends AppCompatActivity {



    String newString;
    private DatabaseReference mDatabase;
    private RecyclerView mBlogList;
    private RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<kijijiData> houseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housesbeta);

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

        newString = newString.replace(' ','-');

        mDatabase = FirebaseDatabase.getInstance().getReference("kijiji");
        mDatabase.keepSynced(true);

        mBlogList = (RecyclerView)findViewById(R.id.myrecycleview);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));





        new Content().execute();

        Intent intent = new Intent(this,ShowHouses.class);
        intent.putExtra("streetName",newString);
        startActivity(intent);


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Document doc = null;
//                try {
//                    doc = Jsoup.connect("https://www.kijiji.ca/b-immobilier/ville-de-montreal/rue-st-dominique/k0c34l1700281")
//                            .timeout(6000).get();
//                    Elements image = doc.getElementsByAttribute("data-listing-id");
//                    for (Element e : image) {
//                        String imgUrl = (e.select("picture img").attr("data-src"));
//                        String add1 = (e.select("span.intersection").text());
//                        String add2 = (e.select("span.intersection").next().text());
//
//                        String id = mDatabase.getKey();
//                        kijijiData user = new kijijiData(add1, add2, imgUrl);
//
//                        mDatabase.child(id).setValue(user);
//                        houseList.add(user);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//        });

        recyclerViewAdapter = new RecyclerViewAdapter(houseList);

        mBlogList.setAdapter(recyclerViewAdapter);
}


@Override
    protected void onStart() {

    super.onStart();


}

    private class Content extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Document doc = null;
            try {
                doc = Jsoup.connect("https://www.kijiji.ca/b-immobilier/ville-de-montreal/rue-st-dominique/k0c34l1700281")
                        .timeout(6000).get();
                Elements image = doc.getElementsByAttribute("data-listing-id");
                for (Element e : image) {
                    String imgUrl = (e.select("picture img").attr("data-src"));
                    String add1 = (e.select("span.intersection").text());
                    String add2 = (e.select("span.intersection").next().text());

                    String id = mDatabase.push().getKey();
                    kijijiData user = new kijijiData(add1, add2, imgUrl);

                    mDatabase.child(id).setValue(user);
                    houseList.add(user);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
    }
}}