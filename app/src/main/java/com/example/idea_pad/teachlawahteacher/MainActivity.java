package com.example.idea_pad.teachlawahteacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }); */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_lprofile) {
            Intent intent = new Intent(this, Profile.class);
            //intent.putExtra(User.USER_SKILL_POINTS, user.skillPoints);
            startActivity(intent);
            return true;
        }else if (id == R.id.action_settings) {
            //hmmm
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //if user login
        //show profile nav
        //else if not login
        //show sign in nav

        if (id == R.id.nav_lects) {
            MakeClass m = new MakeClass();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    //.setCustomAnimations(R.anim.anim_slide_in_from_left, R.anim.anim_slide_out_from_left)
                    .replace(R.id.mains,
                            m,
                            m.getTag()
                    ).commit();

        } else if (id == R.id.nav_ylects) {
            YourClassLists y = new YourClassLists();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    //.setCustomAnimations(R.anim.anim_slide_in_from_left, R.anim.anim_slide_out_from_left)
                    .replace(R.id.mains,
                            y,
                            y.getTag()
                    ).commit();

        } else if (id == R.id.nav_notif) {
            Notifications n = new Notifications();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    //.setCustomAnimations(R.anim.anim_slide_in_from_left, R.anim.anim_slide_out_from_left)
                    .replace(R.id.mains,
                            n,
                            n.getTag()
                    ).commit();

        } else if (id == R.id.nav_share) {
            try {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                String sAux = "\nLet me recommend you this application\n\n";
                sAux = sAux + "https://play.google.com/store/apps/details?id=the.package.id \n\n";
                i.putExtra(Intent.EXTRA_TEXT, sAux);
                startActivity(Intent.createChooser(i, "Share with:"));
            } catch (Exception e) {
                //e.toString();
            }

        } else if (id == R.id.nav_feedback) {
            Feedback f = new Feedback();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    //.setCustomAnimations(R.anim.anim_slide_in_from_left, R.anim.anim_slide_out_from_left)
                    .replace(R.id.mains,
                            f,
                            f.getTag()
                    ).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
