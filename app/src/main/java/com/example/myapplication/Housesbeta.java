package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Housesbeta extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housesbeta);

        final ImageView img = findViewById(R.id.imageView1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Document doc = null;
                try {
                    doc = Jsoup.connect("https://www.kijiji.ca/b-immobilier/ville-de-montreal/rue-st-dominique/k0c34l1700281")
                            .timeout(6000).get();
                    Elements image = doc.getElementsByAttribute("data-listing-id");
                    Element e = image.get(1);
                        String imgUrl = e.select("picture img").attr("data-src");


//                    ((TextView)findViewById(R.id.textView1)).setText(imgUrl);

                        //System.out.println(e.select("picture img").attr("data-src"));
                        String add1 = e.select("span.intersection").text();
                        //System.out.println(e.select("span.intersection").text());
                        String add2 = e.select("span.intersection").next().text();
                        //System.out.println(e.select("span.intersection").next().text());

                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Bitmap bitmap = null;
                            try {
                                bitmap = BitmapFactory.decodeStream((InputStream)new URL("https://i.ebayimg.com/images/g/kfcAAOSwvClgQ5l8/s-l200.jpg").getContent());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            //img.setImageBitm    ap(bitmap);
                        }
                        //ImageView imageView = (ImageView)findViewById(R.id.imageView2);

                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });




//        Document doc = null;
//        try {
//            doc = Jsoup.connect("https://www.kijiji.ca/b-immobilier/ville-de-montreal/rue-st-dominique/k0c34l1700281")
//                    .timeout(100000).get();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Elements image = doc.getElementsByAttribute("data-listing-id");
//        for (Element e : image) {
//            String imgUrl = e.select("picture img").attr("data-src");
//            //System.out.println(e.select("picture img").attr("data-src"));
//            String add1 = e.select("span.intersection").text();
//            //System.out.println(e.select("span.intersection").text());
//            String add2 = e.select("span.intersection").next().text();
//            //System.out.println(e.select("span.intersection").next().text());
//        }
//    }

}
}