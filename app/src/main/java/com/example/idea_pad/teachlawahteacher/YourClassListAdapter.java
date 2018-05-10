package com.example.idea_pad.teachlawahteacher;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Idea-Pad on 6/5/2018.
 */

public class YourClassListAdapter extends RecyclerView.Adapter<YourClassListViewHolder> {


    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
    private String userId;
    DatabaseReference mFirebaseDatabase;

    Context c;
    ArrayList<YourClassListData> classlist;

    public YourClassListAdapter(Context c, ArrayList<YourClassListData> classlist) {
        this.c = c;
        this.classlist = classlist;
    }


    @Override
    public YourClassListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(c).inflate(R.layout.model_list_class,parent,false);
        return new YourClassListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(YourClassListViewHolder holder, final int position) {
        holder.nameTxt.setText(classlist.get(position).getName());
        holder.venueTxt.setText(classlist.get(position).getVenue());
        holder.contactTxt.setText(classlist.get(position).getContact());
        holder.timeTxt.setText(classlist.get(position).getTime());
        holder.dateTxt.setText(classlist.get(position).getDate());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //point databaseReference to Movies
                userId = currentFirebaseUser.getUid();
                mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("user - teachers").child(userId).child("makeclass");
                //remove value of child movie.getKey()
                //mFirebaseDatabase.child(push().getKey()).setValue(null);
                //String key = holder.getRef(position).getKey();
                //String k;

                //mFirebaseDatabase.setValue(null); //delete all, how to delete only one??
                mFirebaseDatabase.removeValue();



                //holder.databaseReference = getRef(position);
                //remove item from list
                classlist.remove(position);
                //notofy datachanged to adapter
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, classlist.size());
                //Toast.makeText(mContext, "Item Deleted", Toast.LENGTH_SHORT).show();
            }
        });
        //holder.descTxt.setText(spacecrafts.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return classlist.size();
    }
}
