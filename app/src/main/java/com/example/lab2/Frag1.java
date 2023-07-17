package com.example.lab2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab2.provider.Book;
import com.example.lab2.provider.BookViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Frag1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ////////////////////////////////////////

    MyRecyclerViewAdapter adapter;

    private BookViewModel mItemViewModel;

    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;
    ////////////////////////////////////////////////////////
    public Frag1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag1.
     */
    // TODO: Rename and change types and number of parameters
    public static Frag1 newInstance(String param1, String param2) {
        Frag1 fragment = new Frag1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

//    public static Frag1 newInstance(Book book) {
//        Frag1 fragment = new Frag1();
//        Bundle args = new Bundle();
//        args.putParcelable("book", (Parcelable) book);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        //////////////////////////////////////


        mItemViewModel = new ViewModelProvider(this).get(BookViewModel.class);//here
        mItemViewModel.getAllItems().observe(this, newData -> {//here
            adapter.setData(newData);//here
            adapter.notifyDataSetChanged();//here
            //           TextView tv = findViewById(R.id.textView8);//here
            //           tv.setText(newData.size() + "");//here

        });//here
//
//        recyclerView.setAdapter(adapter);
        ///////////////////////////////////


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_frag1, container, false);
        recyclerView = layout.findViewById(R.id.recycle);
        layoutManager = new LinearLayoutManager(getContext());  //A RecyclerView.LayoutManager implementation which provides similar functionality to ListView.
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        return layout;
    }
}