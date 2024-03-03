package com.example.organicagriculture;

import static com.example.organicagriculture.R.*;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllProducts#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllProducts extends Fragment {

    View view;
    ProductAdapter adp;
    String categories[]={"Lures","Traps"};
    String catphoto []={"R.drawable.logo","R.drawable.logo"};
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AllProducts() {
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
    GridView gridView;
    RecyclerView rclv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Toast.makeText(getActivity(), "Home opened", Toast.LENGTH_SHORT).show();
        view = inflater.inflate(layout.fragment_all_products, container, false);

//        gridView = (GridView) view.findViewById(id.grid);
        rclv = (RecyclerView) view.findViewById(id.productlist);

//        initCategory();
        initProduct();

//    Inflate the layout for this fragment
        return view;
    }
    private void initProduct() {

        rclv.setLayoutManager(new GridLayoutManager(getContext(),2));
        Traps traps= new Traps();
        Lure lure=new Lure();

        ArrayList<Model> holder = new ArrayList<>();
        holder.addAll(lure.dataQueue());
        holder.addAll(traps.dataQueue());

        adp = new ProductAdapter(getContext(),holder);

        rclv.setAdapter(adp);

    }
//    private void initCategory() {
//
//        gridView.setNumColumns(2);
//        ArrayAdapter arrayAdapter=new ArrayAdapter(getContext(), layout.activity_category_design, id.catlable,categories);
//        gridView.setAdapter(arrayAdapter);
//    }

}