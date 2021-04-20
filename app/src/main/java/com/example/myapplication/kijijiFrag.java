package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class kijijiFrag extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String streetName = "rue-saint-dominique";
        ArrayList<CustomList> housesList = new ArrayList<>();



        View view = inflater.inflate(R.layout.fragment_kijiji, container, false);
        // Inflate the layout for this fragment

        ListView listView = (ListView) view.findViewById(R.id.kijiji_view);

//        new MyTask().execute();
//
//
//        try {
//            Document doc = Jsoup.connect("https://www.kijiji.ca/b-immobilier/ville-de-montreal/rue-st-dominique/k0c34l1700281")
//                    .timeout(100000).get();
//            Elements image = doc.getElementsByAttribute("data-listing-id");
//            for (Element e : image){
//                String imgUrl = e.select("picture img").attr("data-src");
//                //System.out.println(e.select("picture img").attr("data-src"));
//                String add1 = e.select("span.intersection").text();
//                //System.out.println(e.select("span.intersection").text());
//                String add2 = e.select("span.intersection").next().text();
//                //System.out.println(e.select("span.intersection").next().text());
//                housesList.add(new CustomList(imgUrl,add1,add2));
//            }
//
//            CustomArrayAdapter arrayAdapter = new CustomArrayAdapter(kijijiFrag.this.getContext(),0,housesList);
//            listView.setAdapter(arrayAdapter);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }






        return view;
        

    }
//    class MyTask extends AsyncTask<Void, Void, Void> {
//
//        private Exception exception;
//
//        protected Void doInBackground(Void... params) {
//            String title = "";
//            ArrayList<CustomList> housesList = new ArrayList<>();
//
//            try {
//                Document doc = Jsoup.connect("https://www.kijiji.ca/b-immobilier/ville-de-montreal/rue-st-dominique/k0c34l1700281")
//                        .timeout(100000).get();
//                Elements image = doc.getElementsByAttribute("data-listing-id");
//                for (Element e : image) {
//                    String imgUrl = e.select("picture img").attr("data-src");
//                    //System.out.println(e.select("picture img").attr("data-src"));
//                    String add1 = e.select("span.intersection").text();
//                    //System.out.println(e.select("span.intersection").text());
//                    String add2 = e.select("span.intersection").next().text();
//                    //System.out.println(e.select("span.intersection").next().text());
//                    housesList.add(new CustomList(imgUrl, add1, add2));
//                }
//                } catch(Exception e){
//                    this.exception = e;
//
//                    return null;
//            }
//
//        }}
}