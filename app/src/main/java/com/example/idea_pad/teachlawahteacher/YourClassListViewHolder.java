package com.example.idea_pad.teachlawahteacher;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Idea-Pad on 6/5/2018.
 */

public class YourClassListViewHolder extends RecyclerView.ViewHolder {

    TextView nameTxt,venueTxt,contactTxt, timeTxt, dateTxt;
    Button btnDelete;

    public YourClassListViewHolder(View itemView) {
        super(itemView);

        nameTxt=(TextView) itemView.findViewById(R.id.nameTxt);
        venueTxt=(TextView) itemView.findViewById(R.id.venueTxt);
        contactTxt=(TextView) itemView.findViewById(R.id.contactTxt);
        timeTxt=(TextView) itemView.findViewById(R.id.timeTxt);
        dateTxt=(TextView) itemView.findViewById(R.id.dateTxt);
        btnDelete=(Button) itemView.findViewById(R.id.btnDelete);
        //descTxt=(TextView) itemView.findViewById(R.id.descTxt);
    }
}
