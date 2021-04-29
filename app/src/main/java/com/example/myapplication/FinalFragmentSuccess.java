package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import java.util.HashMap;

public class FinalFragmentSuccess extends Fragment {
    public static HashMap<String, Object> dataInFinalFrag;
    private static final String ARG_SECTIONNUMBER = "section_1";
    public FinalFragmentSuccess() {
        // Required empty public constructor
    }
    public static FinalFragmentSuccess newInstance(int section_number,HashMap<String, Object> data) {
        FinalFragmentSuccess fragment = new FinalFragmentSuccess();
        dataInFinalFrag = data;
        Bundle args = new Bundle();
        args.putSerializable(ARG_SECTIONNUMBER, section_number);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_final_fragment_success, container, false);
    }

}
