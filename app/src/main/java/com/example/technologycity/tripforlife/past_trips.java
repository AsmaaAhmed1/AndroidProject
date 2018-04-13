package com.example.technologycity.tripforlife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class past_trips extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_trips);

        listView=(ListView)findViewById(R.id.list);
        dbConnection db=new dbConnection(this);
        final ArrayList<String> trips=db.readFromTripPast();

        listView.setAdapter(new ArrayAdapter<String>(this,R.layout.single_row,R.id.tripTitle,trips));




        //3ayzen nrg3 object mn kol wa7ed f allist 3shan lma ndoos 3leeh nft7 beeh activity gdeda feha aldata bta3to bs

        ArrayList<TripInfo> tripsInfo = new ArrayList<>();

        tripsInfo =db.getAllTripsObjPast();

        final ArrayList<TripInfo> finalTripsInfo = tripsInfo;
//        Log.i("test",finalTripsInfo);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent=new Intent(past_trips.this,ShowPastDetails.class);

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



                startActivity(intent);
            }
        });





    }
}
