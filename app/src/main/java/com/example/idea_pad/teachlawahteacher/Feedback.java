package com.example.idea_pad.teachlawahteacher;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Feedback extends Fragment {

    //model class


    private static final String TAG = Feedback.class.getSimpleName();
    //private TextView txtDetails; //later
    private EditText inputName, inputFeed;
    private TextView txtDetails;
    private Button btnSave;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    private String userId;

    //ArrayList<FeedbackData> fbd=new ArrayList<>();

    public Feedback() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Displaying toolbar icon
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setIcon(R.mipmap.ic_launcher);


        View v = inflater.inflate(R.layout.fragment_feedback, container, false);

        inputName = (EditText) v.findViewById(R.id.name);
        inputFeed = (EditText) v.findViewById(R.id.feedbac);
        btnSave = (Button) v.findViewById(R.id.btn_save);
        txtDetails = (TextView) v.findViewById(R.id.txt_user);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        //get reference to 'uid' node
        //first change node here
        //then at createnode function add name.child("othernodename")
        //can it create child().child().....?
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userId = currentFirebaseUser.getUid();
        mFirebaseDatabase = mFirebaseInstance.getReference("user - teachers").child(userId);

        //get reference to 'feedback' node
        //mFirebaseDatabase2 = mFirebaseInstance.getReference("feedback");

        // store app title to 'app_title' node
        //mFirebaseInstance.getReference("app_title").setValue("TeachLawahTeacher");

        // app_title change listener
        /*mFirebaseInstance.getReference("app_title").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e(TAG, "App title updated");

                String appTitle = dataSnapshot.getValue(String.class);

                // update toolbar title
                //getSupportActionBar().setTitle(appTitle);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read app title value.", error.toException());
            }
        }); */

        // Save / update the feedback
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = inputName.getText().toString();
                String feed = inputFeed.getText().toString();

                createFeedback(name, feed);


                //addUserChangeListener();

                //later - toast for submitted

                // Check for already existed userId
                /* if (TextUtils.isEmpty(userId)) {
                    createUser(name, email);
                } else {
                    updateUser(name, email);
                }*/
            }
        });

        //toggleButton();

        // Inflate the layout for this fragment
        return v;
    }

    // Changing button text
    /* private void toggleButton() {
        if (TextUtils.isEmpty(userId)) {
            btnSave.setText("Save");
        } else {
            btnSave.setText("Update");
        }
    } */

    /**
     * Creating new feedback node under 'feedback'
     */
    private void createFeedback(String name, String feedback) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;

        if (TextUtils.isEmpty(userId)) {
            //userId = mFirebaseDatabase.push().getKey();
            userId = currentFirebaseUser.getUid();
        }

        FeedbackData feedb = new FeedbackData(name, feedback);

        //change other node here
        mFirebaseDatabase.child("feedbacks").push().setValue(feedb);

        //whhen retrieve how to get key?/just take child()?

        //addUserChangeListener();
    }

    /**
     * User data change listener
     */ /*
    private void addUserChangeListener() {
        // User data change listener
        DatabaseReference mFirebaseDatabase2 = mFirebaseDatabase.child(userId).child("feedbacks");
        mFirebaseDatabase2.orderByChild("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                FeedbackData fbd = dataSnapshot.getValue(FeedbackData.class);

                // Check for null
                //if (fbd == null) {
                    //Log.e(TAG, "Feedback data is null!");
                    //return;
                //}

                Log.e(TAG, "Feedback is saved!" + fbd.name + ", " + fbd.feedbacks);

                // Display newly updated name and email
                txtDetails.setText(fbd.name + ", " + fbd.feedbacks);

                //if(dataSnapshot.child("feedbacks").exists()) {
                    //String name = dataSnapshot.child("name").getValue(String.class);
                    //txtDetails.setText(name);
                    //Log.e(TAG, "Feedback is saved!" + name);
                            //put value in view;
                //} else  {
                    //put 0 in view
                //}

                // clear edit text
                //inputEmail.setText("");
                //inputName.setText("");

                //toggleButton();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });
    }*/

    /*private void updateUser(String name, String feedbacks) {
        // updating the user via child nodes
        if (!TextUtils.isEmpty(name))
            mFirebaseDatabase.child(userId).child("name").setValue(name);

        if (!TextUtils.isEmpty(email))
            mFirebaseDatabase.child(userId).child("email").setValue(email);
    }  */





}
