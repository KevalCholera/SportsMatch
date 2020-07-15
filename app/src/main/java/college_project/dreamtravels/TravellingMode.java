package college_project.dreamtravels;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mpt.storage.SharedPreferenceUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import college_project.dreamtravels.Adapter.AdapterTravellingMode;
import college_project.dreamtravels.Util.CommonUtil;
import college_project.dreamtravels.Util.Constants;

public class TravellingMode extends AppCompatActivity {

    ListView lvTravellingMode;
    String[] busName = {"Patel Tours and Travels", "Neeta Tours and Travels",
            "Gujarat Travels", "Shreenath Travels", "Samrat Tours and Travels",
            "Patel Tours and Travels", "Patel Tours and Travels",
            "Patel Tours and Travels", "Neeta Tours and Travels",
            "Shreenath Tours and Travels"};

    String[] trainName = {"Gujarat Sampark Exp.", "Ashram Exp.",
            "BDTS DEE Exp.", "Swarna Raj Exp", "Dee Garibrath",
            "Hapa Svdk Exp.", "Delhi Sr Exp",
            "Haridwar Mail", "Pbr Howrah Exp",
            "Kolkata Exp"};

    String[] flightName = {"Air India AI-14", "GoAir G8-714",
            "GoAir G8-720", "IndiGo 6E-616", "IndiGo 6E-166",
            "Air India AI-18", "Air India AI-11",
            "Vistra UK-946", "Vistra UK-966",
            "Vistra UK-976"};

    String[] carName = {"Audi", "BMW",
            "Astron Martin", "Cadillac", "Chevrolet",
            "Datsun", "Ferrari",
            "Ford", "Maruti",
            "TATA Nano"};

    String[] depTime = {"7:15 PM", "7:30 PM", "7:30 PM", "8:15 PM", "10:15 PM",
            "11:15 PM", "11:30 PM", "11:45 PM", "11:30 PM", "11:45 PM"};

    String[] reachTime = {"3:00 AM", "3:15 AM", "3:00 AM", "4:00 AM", "6:00 AM",
            "7:15 AM", "7:10 AM", "7:30 AM", "8:00 AM", "7:15 AM"};

    String[] traMode = {"AC SEATER", "NON AC SEATER", "NON AC SLEEPER",
            "NON AC SEATER", "AC SLEEPER", "NON AC AC SEATER", "NON AC SEATER",
            "AC SEATER", "AC SLEEPER", "NON AC SLEEPER"};

    String[] seat = {"15 Seats", "4 Seats", "10 Seats", "2 Seats", "1 Seats",
            "No Seats", "7 Seats", "2 Seats", "15 Seats", "5 Seats"};

    String[] timeAqu = {"7:45 Hours", "7:45 Hours", "7:30 Hours", "7:30 Hours",
            "7:10 Hours", "8:00 Hours", "7:25 Hours", "7:15 Hours", "7:30 Hours",
            "7:10 Hours"};

    String[] amount = {"₹ 215", "₹ 170", "₹ 275", "₹ 215", "₹ 300", "₹ 215",
            "₹ 245", "₹ 275", "₹ 350", "₹ 290"};

    JSONArray arrayBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travelling_mode);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        arrayBus = new JSONArray();

        try {
            for (int i = 0; i < busName.length; i++) {
                JSONObject objectBus = new JSONObject();

                if (getIntent().getStringExtra(Constants.TRAVEL_TYPE).equalsIgnoreCase(Constants.BUS))
                    objectBus.put(Constants.TRAVELLING_MODE_NAME, busName[i]);
                else if (getIntent().getStringExtra(Constants.TRAVEL_TYPE).equalsIgnoreCase(Constants.TRAIN))
                    objectBus.put(Constants.TRAVELLING_MODE_NAME, trainName[i]);
                else if (getIntent().getStringExtra(Constants.TRAVEL_TYPE).equalsIgnoreCase(Constants.FLIGHT))
                    objectBus.put(Constants.TRAVELLING_MODE_NAME, flightName[i]);
                else
                    objectBus.put(Constants.TRAVELLING_MODE_NAME, carName[i]);

                objectBus.put(Constants.TRAVELLING_MODE_DEPARTURE_TIME, depTime[i]);
                objectBus.put(Constants.TRAVELLING_MODE_REACHED_TIME, reachTime[i]);
                objectBus.put(Constants.TRAVELLING_MODE_TRAVEL_MODE, traMode[i]);
                objectBus.put(Constants.TRAVELLING_MODE_SEAT, seat[i]);
                objectBus.put(Constants.TRAVELLING_MODE_TIME_AQUIRE, timeAqu[i]);
                objectBus.put(Constants.TRAVELLING_MODE_AMOUNT, amount[i]);

                arrayBus.put(objectBus);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        lvTravellingMode = (ListView) findViewById(R.id.lvTravellingMode);
        lvTravellingMode.setAdapter(new AdapterTravellingMode(this, arrayBus));

        lvTravellingMode.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                JSONObject objectAddToData = arrayBus.optJSONObject(position);
                JSONArray arrayAddToData = new JSONArray();
                arrayAddToData.put(objectAddToData);

                submit(arrayAddToData);

            }
        });
    }

    public void submit(JSONArray arrayAddToData) {
//        JSONObject objectMain = new JSONObject();
        try {
//            if (SharedPreferenceUtil.contains(Constants.All_USER_DATA) && !TextUtils.isEmpty(SharedPreferenceUtil.getString(Constants.All_USER_DATA, "")))
//                objectMain = new JSONObject(SharedPreferenceUtil.getString(Constants.All_USER_DATA, ""));
//
//
//            Iterator iterator = objectMain.keys();
//            while (iterator.hasNext()) {
//
//                String key = (String) iterator.next();
//                if (SharedPreferenceUtil.getString(Constants.CURRENT_USER_ID, "").equalsIgnoreCase(key)) {
//
//                    JSONArray arrayUserData = objectMain.optJSONArray(key);
//                    JSONObject objectUserData = arrayUserData.optJSONObject(0);

//                    objectUserData.remove(Constants.EVENT_INFO);

//                    JSONArray arrayEventInfo = new JSONArray();
//                    JSONObject objectEventInfo = new JSONObject();
//
//                    if (objectUserData.has(Constants.EVENT_INFO)) {
//                        arrayEventInfo = objectUserData.optJSONArray(Constants.EVENT_INFO);
//                        objectEventInfo = arrayEventInfo.optJSONObject(0);
//                    }
//
//                    arrayEventInfo.put(objectEventInfo);
//                    objectUserData.put(Constants.EVENT_INFO, arrayEventInfo);

            JSONArray arrayTripDetail = new JSONArray(SharedPreferenceUtil.getString(Constants.TRIP_BOOKING, ""));
            JSONObject objectTripDetail = arrayTripDetail.optJSONObject(0);
            objectTripDetail.put(Constants.TRAVELLING_MODE, arrayAddToData);

            SharedPreferenceUtil.putValue(Constants.TRIP_BOOKING, arrayTripDetail.toString());
            SharedPreferenceUtil.save();

            CommonUtil.byLogMessage(this, "Traveling Mode" + SharedPreferenceUtil.getString(Constants.TRIP_BOOKING, ""));

            startActivity(new Intent(TravellingMode.this, HotelBooking.class));
//                }
//            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        CommonUtil.closeKeyboard(this);
        super.onBackPressed();
    }

}
