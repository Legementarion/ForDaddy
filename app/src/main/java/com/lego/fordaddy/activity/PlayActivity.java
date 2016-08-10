package com.lego.fordaddy.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import com.lego.fordaddy.R;
import com.lego.fordaddy.logic.Core;

public class PlayActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public ImageButton[] domino_array = new ImageButton[28];
    private int firstPick, secondPick;
    private Core core;

    @Override
    protected void onPause() {
        super.onPause();
        core.stopGame();
    }

    @Override
    protected void onResume() {
        super.onResume();
        core.startGame();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        core = Core.getInstance(this);
        for (int i = 1; i <= 28; i++) {
            if ((domino_array[i - 1] = (ImageButton) findViewById(getResources().getIdentifier("imageButton" + i, "id", getPackageName()))) == null) {
                Log.d("Cant find butt with id", "button" + i);
                return;
            }
            domino_array[i - 1].setEnabled(false);
            domino_array[i - 1].setOnClickListener(myOnClickListener);
        }

        core.startGame();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    View.OnClickListener myOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (firstPick == 0) {
                firstPick = view.getId();
            } else {
                secondPick = view.getId();
                if (firstPick != 0 && secondPick != 0){
                    if (firstPick != secondPick) {
                        core.doPick();
                    } else {
                        core.cancelPick();
                    }
                    firstPick = 0;
                    secondPick = 0;
                }
            }
        }
    };

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
        getMenuInflater().inflate(R.menu.play, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_game:
                core.startGame();
                break;
            case R.id.nav_help:
                Toast.makeText(this, "game rules", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_exit:
                System.exit(0);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
