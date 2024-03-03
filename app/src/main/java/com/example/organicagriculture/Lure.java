package com.example.organicagriculture;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Lure#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Lure extends Fragment {
    View view;
    ProductAdapter adp;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Lure() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Lure newInstance(String param1, String param2) {
        Lure fragment = new Lure();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_lure, container, false);
        initProduct();

        // Inflate the layout for this fragment
        return view;
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.framereplace, fragment);
        ft.commit();
    }
    private void initProduct() {
        RecyclerView rclv = (RecyclerView) view.findViewById(R.id.lures);
        rclv.setLayoutManager(new GridLayoutManager(getContext(),2));
        adp = new ProductAdapter(getContext(),dataQueue());
        rclv.setAdapter(adp);
    }

    public ArrayList<Model> dataQueue(){
        ArrayList<Model> lureslist = new ArrayList<>();

        lureslist.add(new Model("Fruit Fly Lure","20 INR/lure", R.drawable.fruitflylure));
        lureslist.add(new Model("Beetle Lure","20 INR/lure", R.drawable.beetle_lure));
        lureslist.add(new Model("DBM Lure","20 INR/lure", R.drawable.dbm_lure));
        lureslist.add(new Model("FAW Lure","20 INR/lure", R.drawable.faw_lure));
        lureslist.add(new Model("Gulabi Fly Lure","20 INR/lure", R.drawable.gulabiflylure));
        lureslist.add(new Model("Brinjal Lure","20 INR/lure", R.drawable.brinjal_lure));
        lureslist.add(new Model("Red Palm Lure","20 INR/lure", R.drawable.redpalmlure));
        lureslist.add(new Model("Tu Tom Lure","20 INR/lure", R.drawable.tu_tom_lure));

        return lureslist;
    }


}