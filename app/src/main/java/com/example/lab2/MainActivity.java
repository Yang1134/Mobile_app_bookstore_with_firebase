package com.example.lab2;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.lab2.provider.Book;
import com.example.lab2.provider.BookViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    String ID;
    String Title;
    String Author;
    String Description;
    Double price;
    String ISBN;

    DatabaseReference myRef;





    //ArrayList<String> MyList = new ArrayList<>();
//    ArrayAdapter myadapter;

    DrawerLayout drawerlayout;

    ////latest
//    RecyclerView recyclerView;
//    RecyclerView.LayoutManager layoutManager;
//    //RecyclerView.Adapter adapter;
//    MyRecyclerViewAdapter adapter;

    //ArrayList<String> data = new ArrayList<>();
    //ArrayList<String> data2 = new ArrayList<>();

//    int counter = 0;

 //   int position = 0;

    private BookViewModel mItemViewModel;

    GestureDetector gestureDetector;

    int x_down;
    int y_down;

    int ST = 0;
    int LP = 0;


    ////latest

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        gestureDetector = new GestureDetector(this, new MyGestureDetector());
        setContentView(R.layout.drawer);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Book/detail");

        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS}, 0);

        SMSReceiver mySMS = new SMSReceiver();
        IntentFilter intfil = new IntentFilter("mySMS");
        registerReceiver(myreceiver,intfil);

//        ListView listview = findViewById(R.id.mylist);
//        myadapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,MyList);
//        listview.setAdapter(myadapter);

        ////latest
//        recyclerView = findViewById(R.id.recycleView);
//
//        layoutManager = new LinearLayoutManager(this);  //A RecyclerView.LayoutManager implementation which provides similar functionality to ListView.
//        recyclerView.setLayoutManager(layoutManager);   // Also StaggeredGridLayoutManager and GridLayoutManager or a custom Layout manager


        //adapter = new MyRecyclerViewAdapter(data, data2);
        //adapter = new MyRecyclerViewAdapter();

        mItemViewModel = new ViewModelProvider(this).get(BookViewModel.class);//here
//        mItemViewModel.getAllItems().observe(this, newData -> {//here
//            adapter.setData(newData);//here
//            adapter.notifyDataSetChanged();//here
//            //           TextView tv = findViewById(R.id.textView8);//here
//            //           tv.setText(newData.size() + "");//here
//        });//here
//
//        recyclerView.setAdapter(adapter);
        ////latest
        drawerlayout = findViewById(R.id.drawer1);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerlayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerlayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nv);
        navigationView.setNavigationItemSelectedListener(new MyNavigationListener());

        getSupportFragmentManager().beginTransaction().replace(R.id.frame2, new Frag1()).commit();

        FloatingActionButton FAB = findViewById(R.id.floatingActionButton);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_LONG).show();

                EditText Titletoast = findViewById(R.id.inputTitle);
                EditText pricetoast = findViewById(R.id.inputprice);
                String message = "added book(" + Titletoast.getText().toString() +") price ("+ pricetoast.getText().toString() + ")";
                Toast myMessage = Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT);
                myMessage.show();

                EditText IDget = findViewById(R.id.inputID);
                ID = IDget.getText().toString();
                EditText Titleget = findViewById(R.id.inputTitle);
                Title = Titleget.getText().toString();
                EditText Authorget = findViewById(R.id.inputAuthor);
                Author = Authorget.getText().toString();
                EditText Descriptionget = findViewById(R.id.inputDescription);
                Description = Descriptionget.getText().toString();
                EditText priceget = findViewById(R.id.inputprice);
                price = Double.parseDouble(priceget.getText().toString());
                EditText ISBNget = findViewById(R.id.inputISBN);
                ISBN = ISBNget.getText().toString();
//                String messagetoadd = Title + " | " + price;
//                //MyList.add(messagetoadd);
//                //                myadapter.notifyDataSetChanged();
//
//                ////latest
//                //data.add(counter +"");  test here
//                String formattedStr = "ID: " + ID + "\n" + "Author: " + Author + "\n" + "DESC: " + Description;
//                String formattedStr2 ="Position: " + position + "\n"+"Title: " + Title + "\n" +  "ISBN: " + ISBN + "\n" + "Price: " + price;
//

//                data.add(formattedStr);
//                data2.add(formattedStr2);
//                counter++;
//                position++;
//                adapter.notifyDataSetChanged();
                ////latest
                //String Titletoaddd = ID + "|" + Title  + "|" +Author  + "|" +Description + "|" + ISBN;
                Book item = new Book(Title,  price, Author,ID, ISBN, Description);

        //        data.add(item);
                mItemViewModel.insert(item);
                Map<String, Object> bookValues = item.toMap();
                myRef.push().setValue(bookValues);

                //myRef.setValue(item);



            }
        });



        View view=findViewById(R.id.framew10);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });





    }



    private class MyGestureDetector extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onSingleTapUp(@NonNull MotionEvent e) {
            //on a single tap: generate a new random Book ID (see the supplementary material)
            //Toast.makeText(getApplicationContext(), "singletap", Toast.LENGTH_SHORT).show();
//            ST += 1;
//            Toast.makeText(getApplicationContext(), "ST: "+ST + " LP: "+ LP , Toast.LENGTH_SHORT).show();

            EditText IDget = findViewById(R.id.inputID);
            IDget.setText("ID");
            EditText Titleget = findViewById(R.id.inputTitle);
            Titleget.setText("TITLE");
            EditText Authorget = findViewById(R.id.inputAuthor);
            Authorget.setText("AUTHOR");
            EditText Descriptionget = findViewById(R.id.inputDescription);
            Descriptionget.setText("DESCRIPTION");
            EditText priceget = findViewById(R.id.inputprice);
            priceget.setText(String.valueOf(100));
            EditText ISBNget = findViewById(R.id.inputISBN);
            String ISBN = randomisbn.generateNewRandomString(9);
            ISBNget.setText(ISBN);
            return super.onSingleTapUp(e);
        }

        @Override
        public boolean onDoubleTapEvent(@NonNull MotionEvent e) {
            clearinput();
            //Toast.makeText(getApplicationContext(), "doubletap", Toast.LENGTH_SHORT).show();

            return super.onDoubleTapEvent(e);
        }

        @Override
        public void onLongPress(@NonNull MotionEvent e) {

            load();
            //Toast.makeText(getApplicationContext(), "longpress", Toast.LENGTH_SHORT).show();
//            LP += 1;
//            Toast.makeText(getApplicationContext(), "ST: "+ST + " LP: "+ LP, Toast.LENGTH_SHORT).show();

            super.onLongPress(e);
        }

        @Override
        public boolean onScroll(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float distanceX, float distanceY) {
            //System.out.println(distanceX);
            if(Math.abs(e1.getY()-e2.getY()) < 40) {
                if (e1.getX()-e2.getX() < -10) {
                    EditText priceEditText = findViewById(R.id.inputprice);
                    double price = Double.parseDouble(priceEditText.getText().toString());
                    price -= distanceX;
                    priceEditText.setText(String.format("%.2f", price));


                }else if(e1.getX()-e2.getX() > 10){
                    EditText priceEditText = findViewById(R.id.inputprice);
                    double price = Double.parseDouble(priceEditText.getText().toString());

                    price -= distanceX;
                    priceEditText.setText(String.format("%.2f", price));

                }
            } else if(Math.abs(e1.getX()-e2.getX()) < 40) {
                if (Math.abs(distanceY) < 40) {
                    EditText Titleget = findViewById(R.id.inputTitle);
                    Titleget.setText("untitled");
                }
            }
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onFling(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
            if(Math.abs(velocityX ) > 2500 || Math.abs(velocityY ) > 2500) {
                moveTaskToBack(true);
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    BroadcastReceiver myreceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
//            EditText ID = findViewById(R.id.inputID);
//            ID.setText(intent.getStringExtra("KEY1"));
            EditText IDget = findViewById(R.id.inputID);
            IDget.setText(intent.getStringExtra("ID"));
            EditText Titleget = findViewById(R.id.inputTitle);
            Titleget.setText(intent.getStringExtra("TITLE"));
            EditText Authorget = findViewById(R.id.inputAuthor);
            Authorget.setText(intent.getStringExtra("AUTHOR"));
            EditText Descriptionget = findViewById(R.id.inputDescription);
            Descriptionget.setText(intent.getStringExtra("DESCRIPTION"));
            EditText priceget = findViewById(R.id.inputprice);
//            priceget.setText(String.valueOf(intent.getStringExtra("PRICE")));
            boolean addPrice = intent.getBooleanExtra("ADDPRICE", false);
            if (addPrice) {
                int currentPrice = Integer.parseInt(intent.getStringExtra("PRICE"));
                int newPrice = currentPrice + 100;
                priceget.setText(String.valueOf(newPrice));
            } else {
                int currentPrice = Integer.parseInt(intent.getStringExtra("PRICE"));
                int newPrice = currentPrice + 5;
                priceget.setText(String.valueOf(newPrice));
            }
            EditText ISBNget = findViewById(R.id.inputISBN);
            ISBNget.setText(intent.getStringExtra("ISBN"));


        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("lab3", "onStart");

        SharedPreferences myDataID = getSharedPreferences("f2",0);
        ID = myDataID.getString("key8","");

        SharedPreferences myDataTitle = getSharedPreferences("f3",0);
        Title = myDataTitle.getString("key9","");

        SharedPreferences myDataAuthor = getSharedPreferences("f4",0);
        Author = myDataAuthor.getString("key10","");

        SharedPreferences myDataDescription = getSharedPreferences("f5",0);
        Description = myDataDescription.getString("key11","");

        SharedPreferences myDataprice = getSharedPreferences("f6", 0);
        String priceString = myDataprice.getString("key12", "");
        if (!priceString.isEmpty()) {
            price = Double.parseDouble(priceString);
        }

        SharedPreferences myDataISBN = getSharedPreferences("f7", 0);
        ISBN = myDataISBN.getString("key13", "");


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("lab3", "onResume");
//        System.out.println(message);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("lab3", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("lab3", "onStop");

        EditText myID = findViewById(R.id.inputID);
        String msgID = myID.getText().toString();
        SharedPreferences myDataID = getSharedPreferences("f2", 0);
        SharedPreferences.Editor myeditorID = myDataID.edit();
        myeditorID.putString("key8", msgID);
        myeditorID.commit();

        EditText myTitle = findViewById(R.id.inputTitle);
        String msgTitle = myTitle.getText().toString();
        SharedPreferences myDataTitle = getSharedPreferences("f3", 0);
        SharedPreferences.Editor myeditorTitle = myDataTitle.edit();
        myeditorTitle.putString("key9", msgTitle);
        myeditorTitle.commit();

        EditText myAuthor = findViewById(R.id.inputAuthor);
        String msgAuthor = myAuthor.getText().toString();
        SharedPreferences myDataAuthor = getSharedPreferences("f4", 0);
        SharedPreferences.Editor myeditorAuthor = myDataAuthor.edit();
        myeditorAuthor.putString("key10", msgAuthor);
        myeditorAuthor.commit();

        EditText myDescription = findViewById(R.id.inputDescription);
        String msgDescription = myDescription.getText().toString();
        SharedPreferences myDataDescription = getSharedPreferences("f5", 0);
        SharedPreferences.Editor myeditorDescription = myDataDescription.edit();
        myeditorDescription.putString("key11", msgDescription);
        myeditorDescription.commit();

        EditText myprice = findViewById(R.id.inputprice);
        String msgprice = myprice.getText().toString();
        SharedPreferences myDataprice = getSharedPreferences("f6", 0);
        SharedPreferences.Editor myeditorprice = myDataprice.edit();
        myeditorprice.putString("key12", msgprice);
        myeditorprice.commit();

        EditText myISBN = findViewById(R.id.inputISBN);
        String msgISBN = myISBN.getText().toString();
        SharedPreferences myDataISBN = getSharedPreferences("f7", 0);
        SharedPreferences.Editor myeditorISBN = myDataISBN.edit();
        myeditorISBN.putString("key13", msgISBN);
        myeditorISBN.commit();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("lab3", "onDestroy");
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("lab3", "onSaveInstanceState");

        outState.putString("key",ID);
        outState.putString("key3",Title);
        outState.putString("key4",Author);
        outState.putString("key5",Description);
        outState.putString("key6", String.valueOf(price));
        outState.putString("key7", ISBN);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("lab3", "onRestoreInstanceState");


        ID= savedInstanceState.getString("key");
        Title= savedInstanceState.getString("key3");
        Author= savedInstanceState.getString("key4");
        Description= savedInstanceState.getString("key5");
        if (price != null) {
            price = Double.parseDouble(savedInstanceState.getString("key6"));
        }
        ISBN = savedInstanceState.getString("key7");


        EditText ID = findViewById(R.id.inputID);
        EditText Author = findViewById(R.id.inputAuthor);
        EditText Description = findViewById(R.id.inputDescription);
        EditText price = findViewById(R.id.inputprice);
        ID.getText().clear();
        Author.getText().clear();
        Description.getText().clear();
        price.getText().clear();
    }
//////////////////////////////////
    public void showToast(View v)
    {
        EditText Titletoast = findViewById(R.id.inputTitle);
        EditText pricetoast = findViewById(R.id.inputprice);
        String message = "added book(" + Titletoast.getText().toString() +") price ("+ pricetoast.getText().toString() + ")";
        Toast myMessage = Toast.makeText(this,message, Toast.LENGTH_SHORT);
        myMessage.show();

        EditText IDget = findViewById(R.id.inputID);
        ID = IDget.getText().toString();
        EditText Titleget = findViewById(R.id.inputTitle);
        Title = Titleget.getText().toString();
        EditText Authorget = findViewById(R.id.inputAuthor);
        Author = Authorget.getText().toString();
        EditText Descriptionget = findViewById(R.id.inputDescription);
        Description = Descriptionget.getText().toString();
        EditText priceget = findViewById(R.id.inputprice);
        price = Double.parseDouble(priceget.getText().toString());
        EditText ISBNget = findViewById(R.id.inputISBN);
        ISBN = ISBNget.getText().toString();
        ///
    }

    public void load()
    {
        EditText IDget = findViewById(R.id.inputID);
        IDget.setText(ID);
        EditText Titleget = findViewById(R.id.inputTitle);
        Titleget.setText(Title);
        EditText Authorget = findViewById(R.id.inputAuthor);
        Authorget.setText(Author);
        EditText Descriptionget = findViewById(R.id.inputDescription);
        Descriptionget.setText(Description);
        EditText priceget = findViewById(R.id.inputprice);
        priceget.setText(String.valueOf(price));
        EditText ISBNget = findViewById(R.id.inputISBN);
        ISBNget.setText(ISBN);
        ///

    }

    public void clearinput()
    {
        EditText ID = findViewById(R.id.inputID);
        EditText Title = findViewById(R.id.inputTitle);
        EditText Author = findViewById(R.id.inputAuthor);
        EditText Description = findViewById(R.id.inputDescription);
        EditText price = findViewById(R.id.inputprice);
        EditText ISBN = findViewById(R.id.inputISBN);
        ID.getText().clear();
        Title.getText().clear();
        Author.getText().clear();
        Description.getText().clear();
        price.getText().clear();
        ISBN.getText().clear();
    }

    class MyNavigationListener implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // get the id of the selected item
            int id = item.getItemId();

            if (id == R.id.delete_all) {
                // Do something
                //MyList.clear();
                //myadapter.notifyDataSetChanged();
//                data.clear();
//                data2.clear();
//                adapter.notifyDataSetChanged();
//                position = 0;
                mItemViewModel.deleteAll();
                myRef.removeValue();

            } else if (id == R.id.add_book) {
                // Do something
                EditText Titletoast = findViewById(R.id.inputTitle);
                EditText pricetoast = findViewById(R.id.inputprice);
                String message = "added book(" + Titletoast.getText().toString() +") price ("+ pricetoast.getText().toString() + ")";
                Toast myMessage = Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT);
                myMessage.show();

                EditText IDget = findViewById(R.id.inputID);
                ID = IDget.getText().toString();
                EditText Titleget = findViewById(R.id.inputTitle);
                Title = Titleget.getText().toString();
                EditText Authorget = findViewById(R.id.inputAuthor);
                Author = Authorget.getText().toString();
                EditText Descriptionget = findViewById(R.id.inputDescription);
                Description = Descriptionget.getText().toString();
                EditText priceget = findViewById(R.id.inputprice);
                price = Double.parseDouble(priceget.getText().toString());
                EditText ISBNget = findViewById(R.id.inputISBN);
                ISBN = ISBNget.getText().toString();

//                String messagetoadd = Title + " | " + price;
//                //MyList.add(messagetoadd);
//                //myadapter.notifyDataSetChanged();
//
//                String formattedStr = "ID: " + ID + "\n" + "Author: " + Author + "\n" + "DESC: " + Description;
//                String formattedStr2 ="Position: " + position + "\n"+"Title: " + Title + "\n" +  "ISBN: " + ISBN + "\n" + "Price: " + price;
//

//                data.add(formattedStr);
//                data2.add(formattedStr2);
//                counter++;
//                position++;
//                adapter.notifyDataSetChanged();
                String Titletoaddd = ID + "|" + Title  + "|" +ISBN  + "|" +Author + "|" + Description;

                //Book item1 = new Book(Titletoaddd, price);
                Book item1 = new Book(Title,  price, Author,ID, ISBN, Description);
//        data.add(item);
                mItemViewModel.insert(item1);
                Map<String, Object> bookValues = item1.toMap();
                myRef.push().setValue(bookValues);
                //counter++;

            }else if (id == R.id.delete_last) {
                // Do something
                //MyList.remove(MyList.size()-1);
                //myadapter.notifyDataSetChanged();
//                data.remove(data.size()-1);
//                data2.remove(data2.size()-1);
//                adapter.notifyDataSetChanged();
//                counter--;
//                position--;
                //counter--;
                mItemViewModel.deleteLast();
                DatabaseReference booksRef = FirebaseDatabase.getInstance().getReference("Book/detail");


                Query lastItemQuery = booksRef.orderByKey().limitToLast(1);


                lastItemQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        DataSnapshot lastItemSnapshot = null;
                        DataSnapshot todelete = null;
                        do {
                            todelete = lastItemSnapshot;
                            lastItemSnapshot = dataSnapshot.getChildren().iterator().hasNext() ? dataSnapshot.getChildren().iterator().next() : null;
                        }while(lastItemSnapshot != null);
                        if (todelete != null) {
                            todelete.getRef().removeValue();

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });





            }else if (id == R.id.list_all) {
                //getSupportFragmentManager().beginTransaction().replace(R.id.frame1)
                Intent i = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(i);
            }
            drawerlayout.closeDrawers();
            // tell the OS
            return true;


        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.load_all) {
            // Do something
            EditText IDget = findViewById(R.id.inputID);
            IDget.setText(ID);
            EditText Titleget = findViewById(R.id.inputTitle);
            Titleget.setText(Title);
            EditText Authorget = findViewById(R.id.inputAuthor);
            Authorget.setText(Author);
            EditText Descriptionget = findViewById(R.id.inputDescription);
            Descriptionget.setText(Description);
            EditText priceget = findViewById(R.id.inputprice);
            priceget.setText(String.valueOf(price));
            EditText ISBNget = findViewById(R.id.inputISBN);
            ISBNget.setText(ISBN);
        } else if (id == R.id.clear_all) {
            // Do something
            EditText ID = findViewById(R.id.inputID);
            EditText Title = findViewById(R.id.inputTitle);
            EditText Author = findViewById(R.id.inputAuthor);
            EditText Description = findViewById(R.id.inputDescription);
            EditText price = findViewById(R.id.inputprice);
            EditText ISBN = findViewById(R.id.inputISBN);
            ID.getText().clear();
            Title.getText().clear();
            Author.getText().clear();
            Description.getText().clear();
            price.getText().clear();
            ISBN.getText().clear();
        }

        return super.onOptionsItemSelected(item);
    }

}