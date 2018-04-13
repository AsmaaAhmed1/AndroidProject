package com.example.technologycity.tripforlife;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView listView;
    Button add;
    TripInfo tripInfo;
    public static final String shValueString="log";


    boolean LOGOUT_CHECK=false;

    ArrayList<DataModel> dataModels;
//    CustomAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        toolbar.setTitle("Trip Planner ");

        //adding logic

        listView=(ListView)findViewById(R.id.list);

        ////////////////

        dbConnection db=new dbConnection(this);
        final ArrayList<String> trips=db.readFromTripRecent();

        listView.setAdapter(new ArrayAdapter<String>(this,R.layout.single_row,R.id.tripTitle,trips));




        //3ayzen nrg3 object mn kol wa7ed f allist 3shan lma ndoos 3leeh nft7 beeh activity gdeda feha aldata bta3to bs

        ArrayList<TripInfo> tripsInfo = new ArrayList<>();

        tripsInfo =db.getAllTripsObjRecent();

        final ArrayList<TripInfo> finalTripsInfo = tripsInfo;
//        Log.i("test",finalTripsInfo);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent=new Intent(MainActivity.this,EditTrip.class);

                TripInfo ti = (TripInfo) finalTripsInfo.get((int) id);

                intent.putExtra("id",String.valueOf(ti.getId()));
                intent.putExtra("name",finalTripsInfo.get((int) id).getName());
                intent.putExtra("end",finalTripsInfo.get((int) id).getEnd());
                intent.putExtra("start",finalTripsInfo.get((int) id).getStart());
                intent.putExtra("date",finalTripsInfo.get((int) id).getDate());
                intent.putExtra("time",finalTripsInfo.get((int) id).getTime());

                intent.putExtra("start_lang",finalTripsInfo.get((int) id).getStart_lang());
                intent.putExtra("start_alt",finalTripsInfo.get((int) id).getStrat_alt());
                intent.putExtra("end_lang",finalTripsInfo.get((int) id).getEnd_lang());
                intent.putExtra("end_alt",finalTripsInfo.get((int) id).getEnd_alt());

                intent.putExtra("note",finalTripsInfo.get((int) id).getNote());

                intent.putExtra("past",finalTripsInfo.get((int) id).getPast());

                intent.putExtra("roundTrip",finalTripsInfo.get((int) id).getRoundTrip());




                startActivity(intent);
            }
        });







        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(MainActivity.this,AddTrips.class);
                startActivity(intent);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

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

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {
            Intent go_to_past = new Intent(this, past_trips.class);
            startActivity(go_to_past);


        }  else if (id == R.id.logout) {
            LOGOUT_CHECK=true;
            SharedPreferences shval = getSharedPreferences(shValueString, 0);
            SharedPreferences.Editor shE = shval.edit();
            shE.clear();
            shE.commit();
//
            Log.i("hna f allogout", "press on log out");
            finish();
            Intent intent = new Intent(MainActivity.this,LOG.class);
            startActivity(intent);

//            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
