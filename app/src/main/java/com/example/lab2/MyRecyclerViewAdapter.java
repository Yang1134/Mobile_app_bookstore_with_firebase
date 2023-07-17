package com.example.lab2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab2.provider.Book;
import com.example.lab2.provider.BookViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

//    ArrayList<String> data;
//    ArrayList<String> data2;
    List<Book> data = new ArrayList<>();
//    public MyRecyclerViewAdapter(ArrayList<String> _data, ArrayList<String> _data2) {
//        data = _data;
//        data2 = _data2;
//    }


    public MyRecyclerViewAdapter()
    {
    }

    public void setData(List<Book> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
         ViewHolder viewHolder = new ViewHolder(v);
         return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.ViewHolder holder, int position) {
        //String[] components = data.get(position).getBookName().split("\\|");
        String id = "ID: " + data.get(position).getBOOKID1();
        String title = "Title: " + data.get(position).getBookName();
        String isbn = "ISBN: " + data.get(position).getBookISBN();
        String author = "Author: " + data.get(position).getAuthor();
        String description = "DESC: " + data.get(position).getBOOKDescription();
        double price = data.get(position).getPrice();
//        String price = components[5];
//        holder.myText.setText(data.get(position));
        holder.myText.setText(id +"\n"+ author +"\n"+description);

        holder.myText2.setText(title+"\n"+isbn+"\n"+ "Price: "+ price);

        final int fPosition = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View view) {
                 Snackbar.make(view, "Item at position " + fPosition + "was clicked", Snackbar.LENGTH_SHORT);
             }
         });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View itemView;
        public TextView myText;

        public TextView myText2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            myText = itemView.findViewById(R.id.textView7);
            myText2 = itemView.findViewById(R.id.textView8);
            //cardTv = itemView.findViewById(R.id.card_id);
        }
    }
}
