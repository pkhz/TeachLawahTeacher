package com.example.idea_pad.teachlawahteacher;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class YourClassLists extends Fragment {

    DatabaseReference mFirebaseDatabase;
    private String userId;

    //DatabaseReference mFirebaseDatabase2 = FirebaseDatabase.getInstance().getReference(userID).child("makeclass");
    //FirebaseHelper helper; get db
    YourClassListAdapter adapter;
    //private FirebaseRecyclerAdapter<YourClassListData, YourClassListViewHolder> adapter2;
    RecyclerView rv;
    //EditText nameEditTxt,propTxt,descTxt;
    TextView txtDetails;

    ArrayList<YourClassListData> classlist=new ArrayList<>();


    public YourClassLists() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_your_class_lists, container, false);


        //INITIALIZE RV
        rv= (RecyclerView) v.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        //INITIALIZE FB
        mFirebaseDatabase= FirebaseDatabase.getInstance().getReference();

        //ADAPTER
        adapter=new YourClassListAdapter(getActivity(),retrieve());

        //String name = inputName.getText().toString();
        //String venue = inputFeed.getText().toString();

        //createListClasses(name, venue);

        //INITIALIZE RV
        //rv= (RecyclerView) v.findViewById(R.id.rv);
        //rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        rv.setAdapter(adapter);

        return v;
    }

    private void createListClasses(String name, String venue, String contact, String time, String date) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
        //FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;

        //if (TextUtils.isEmpty(userId)) {
            //userId = mFirebaseDatabase.push().getKey();
            //userId = currentFirebaseUser.getUid();
        //}

        YourClassListData cd = new YourClassListData(name, venue, contact, time, date);

        //change other node here
        mFirebaseDatabase.child("listclasses").push().setValue(cd);

        //whhen retrieve how to get key?/just take child()?

        //addUserChangeListener();
    }

    //recyclerview


    public ArrayList<YourClassListData> retrieve()
    {
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userId = currentFirebaseUser.getUid();
        DatabaseReference mFirebaseDatabase2 = FirebaseDatabase.getInstance().getReference().child("user - teachers").child(userId).child("makeclass");

        mFirebaseDatabase2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return classlist;
    }

    private void fetchData(DataSnapshot dataSnapshot)
    {
        classlist.clear();

        for (DataSnapshot ds : dataSnapshot.getChildren())
        {

            //to fix

            YourClassListData classlists=dataSnapshot.getValue(YourClassListData.class);


            classlist.add(classlists);
            //Toast.makeText()
            //String name = dataSnapshot.child("name").getValue(String.class);
            //txtDetails.setText(name);
            //Log.e(TAG, "Name: " + name);
        }
        String name = dataSnapshot.child("name").getValue(String.class);
        String venue = dataSnapshot.child("venue").getValue(String.class);
        String contact = dataSnapshot.child("contact").getValue(String.class);
        String time = dataSnapshot.child("time").getValue(String.class);
        String date = dataSnapshot.child("date").getValue(String.class);
        //YourClassListData classlists=dataSnapshot.getValue(YourClassListData.class);
        //txtDetails.setText(name);
        //YourClassListData cd = dataSnapshot.getValue(YourClassListData.class);
        //classlist.add(cd);


        //Log.e(TAG, "Name: " + classlist.toString());



        createListClasses(name, venue, contact, time, date);



        //txtDetails.setText(cd);


    }





}
