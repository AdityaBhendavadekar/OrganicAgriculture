package com.example.organicagriculture;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Traps#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Traps extends Fragment {

    View view;
    ProductAdapter adp;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Traps() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Traps.
     */
    // TODO: Rename and change types and number of parameters
    public static Traps newInstance(String param1, String param2) {
        Traps fragment = new Traps();
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

        view = inflater.inflate(R.layout.fragment_traps, container, false);

        Toast.makeText(getContext(), "This is traps section", Toast.LENGTH_SHORT).show();

//        RecyclerView rclv = view.findViewById(R.id.trap);
        initProduct();

        // Inflate the layout for this fragment
        return view;
    }

    private void initProduct() {
        RecyclerView rclv = (RecyclerView) view.findViewById(R.id.trapRecyclerView);
        rclv.setLayoutManager(new GridLayoutManager(getContext(),2));
        adp = new ProductAdapter(getContext(),dataQueue());
        rclv.setAdapter(adp);
    }

    public ArrayList<Model> dataQueue(){
        ArrayList<Model> trapslist = new ArrayList<>();

        trapslist.add(new Model("Bucket Trap","20 INR/trap", R.drawable.bucket_trap));
        trapslist.add(new Model("Delta Trap","20 INR/trap", R.drawable.delta_trap));
        trapslist.add(new Model("Funnel Trap","20 INR/trap", R.drawable.funnel_trap));
        trapslist.add(new Model("Max Plus Trap","20 INR/trap", R.drawable.maxplus_trap));


        return trapslist;
    }

}