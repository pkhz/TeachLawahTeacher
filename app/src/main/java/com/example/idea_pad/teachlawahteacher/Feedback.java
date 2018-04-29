package com.example.idea_pad.teachlawahteacher;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class Feedback extends Fragment {


    private static final String TAG = Feedback.class.getSimpleName();
    //private TextView txtDetails; //later
    private EditText inputName, inputFeed;
    private Button btnSave;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    private String userId;

    public Feedback() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Displaying toolbar icon
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        //txtDetails = (TextView) findViewById(R.id.txt_user);
        View v = inflater.inflate(R.layout.fragment_feedback, container, false);

        inputName = (EditText) v.findViewById(R.id.name);
        inputFeed = (EditText) v.findViewById(R.id.feedbac);
        btnSave = (Button) v.findViewById(R.id.btn_save);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        //get reference to 'uid' node
        //first change node here
        //then at createnode function add name.child("othernodename")
        //can it create child().child().....?
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userId = currentFirebaseUser.getUid();
        mFirebaseDatabase = mFirebaseInstance.getReference(userId);

        //get reference to 'feedback' node
        //mFirebaseDatabase2 = mFirebaseInstance.getReference("feedback");

        // store app title to 'app_title' node
        /* mFirebaseInstance.getReference("app_title").setValue("TeachLawahTeacher");

        // app_title change listener
        mFirebaseInstance.getReference("app_title").addValueEventListener(new ValueEventListener() {
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
        mFirebaseDatabase.child("feedbacks").setValue(feedb);

        //addUserChangeListener();
    }

    /**
     * User data change listener
     */ /*
    private void addUserChangeListener() {
        // User data change listener
        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                FeedbackData fbd = dataSnapshot.getValue(FeedbackData.class);

                // Check for null
                if (fbd == null) {
                    Log.e(TAG, "Feedback data is null!");
                    return;
                }

                Log.e(TAG, "Feedback is saved!" + fbd.name + ", " + fbd.feedbacks);

                // Display newly updated name and email
                txtDetails.setText(user.name + ", " + user.email);

                // clear edit text
                inputEmail.setText("");
                inputName.setText("");

                toggleButton();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });
    }

    private void updateUser(String name, String feedbacks) {
        // updating the user via child nodes
        if (!TextUtils.isEmpty(name))
            mFirebaseDatabase.child(userId).child("name").setValue(name);

        if (!TextUtils.isEmpty(email))
            mFirebaseDatabase.child(userId).child("email").setValue(email);
    }  */


}
