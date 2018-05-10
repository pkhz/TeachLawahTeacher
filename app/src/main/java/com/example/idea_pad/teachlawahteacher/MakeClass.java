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
public class MakeClass extends Fragment {

    private static final String TAG = Feedback.class.getSimpleName();
    //private TextView txtDetails; //later
    private EditText inputName, inputVenue, inputDate, inputTime, inputContact;
    private Button btnSave;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    private String userId;


    public MakeClass() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_make_class, container, false);

        inputName = (EditText) v.findViewById(R.id.name);
        inputVenue = (EditText) v.findViewById(R.id.venue);
        inputTime = (EditText) v.findViewById(R.id.time);
        inputDate = (EditText) v.findViewById(R.id.date);
        inputContact = (EditText) v.findViewById(R.id.contact);
        btnSave = (Button) v.findViewById(R.id.btn_save);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userId = currentFirebaseUser.getUid();
        mFirebaseDatabase = mFirebaseInstance.getReference("user - teachers").child(userId);;

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = inputName.getText().toString();
                String venue = inputVenue.getText().toString();
                String time = inputTime.getText().toString();
                String date = inputDate.getText().toString();
                String contact = inputContact.getText().toString();

                createClass(name, venue, time, date, contact);

                //later - toast for submitted

                // Check for already existed userId
                /* if (TextUtils.isEmpty(userId)) {
                    createUser(name, email);
                } else {
                    updateUser(name, email);
                }*/
            }
        });

        return v;
    }

    /**
     * Creating new feedback node under 'feedback'
     */
    private void createClass(String name, String venue, String time, String date, String contact) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;

        if (TextUtils.isEmpty(userId)) {
            //userId = mFirebaseDatabase.push().getKey();
            userId = currentFirebaseUser.getUid();
        }

        MakeClassData classData = new MakeClassData(name, venue, time, date, contact);

        //change other node here
        mFirebaseDatabase.child("makeclass").push().setValue(classData);

        //addUserChangeListener();
    }

}
