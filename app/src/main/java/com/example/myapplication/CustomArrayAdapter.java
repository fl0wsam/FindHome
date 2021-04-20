package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CustomArrayAdapter extends ArrayAdapter<CustomList> {


    public CustomArrayAdapter(@NonNull Context context, int resource, @NonNull ArrayList<CustomList> customLists) {
        super(context, resource, (List<CustomList>) customLists);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list, parent, false);

        }

        CustomList currentItem = getItem(position);
        ImageView imageView = listItemView.findViewById(R.id.img);
        Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(imageView);

        return listItemView;
    }
}
