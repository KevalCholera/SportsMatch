package college_project.dreamtravels;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mpt.storage.SharedPreferenceUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import college_project.dreamtravels.Adapter.AdapterListOfPassengers;
import college_project.dreamtravels.Util.CommonUtil;
import college_project.dreamtravels.Util.Constants;

public class FinalEventDetails extends AppCompatActivity {

    TextView tvFinalEventDetailsFromDate, tvFinalEventDetailsToDate,
            tvFinalEventDetailsPickUp, tvFinalEventDetailsDropOff,
            tvFinalEventDetailsPassengers, tvFinalEventDetailsLuggage,
            tvFinalEventDetailsDurationName, tvFinalEventDetailsTravellingName,
            tvFinalEventDetailsOtherReq, tvElementTravellingModeName,
            tvElementTravellingModeDepartureTime, tvElementTravellingModeTimeAquire,
            tvElementTravellingModeReachedTime, tvElementTravellingModeTravelType,
            tvElementTravellingModeDepartureAmount, tvElementTravellingModeSeat,
            tvElementHotelBookingName, tvElementHotelBookingTime,
            tvElementHotelBookingRoomType, tvElementHotelBookingAmount,
            tvElementHotelBookingRoomAvailable;
    ListView lvFinalEventDetailsPassengersList;
    Button btFinalEventDetailsAmountSubmit;
    JSONArray arrayTripData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_event_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvFinalEventDetailsFromDate = (TextView) findViewById(R.id.tvFinalEventDetailsFromDate);
        tvFinalEventDetailsToDate = (TextView) findViewById(R.id.tvFinalEventDetailsToDate);
        tvFinalEventDetailsPickUp = (TextView) findViewById(R.id.tvFinalEventDetailsPickUp);
        tvFinalEventDetailsDropOff = (TextView) findViewById(R.id.tvFinalEventDetailsDropOff);
        tvFinalEventDetailsPassengers = (TextView) findViewById(R.id.tvFinalEventDetailsPassengers);
        tvFinalEventDetailsLuggage = (TextView) findViewById(R.id.tvFinalEventDetailsLuggage);
        tvFinalEventDetailsDurationName = (TextView) findViewById(R.id.tvFinalEventDetailsDurationName);
        tvFinalEventDetailsTravellingName = (TextView) findViewById(R.id.tvFinalEventDetailsTravellingName);
        tvFinalEventDetailsOtherReq = (TextView) findViewById(R.id.tvFinalEventDetailsOtherReq);
        lvFinalEventDetailsPassengersList = (ListView) findViewById(R.id.lvFinalEventDetailsPassengersList);
        btFinalEventDetailsAmountSubmit = (Button) findViewById(R.id.btFinalEventDetailsAmountSubmit);


        tvElementTravellingModeName = (TextView) findViewById(R.id.tvElementTravellingModeName);
        tvElementTravellingModeDepartureTime = (TextView) findViewById(R.id.tvElementTravellingModeDepartureTime);
        tvElementTravellingModeTimeAquire = (TextView) findViewById(R.id.tvElementTravellingModeTimeAquire);
        tvElementTravellingModeReachedTime = (TextView) findViewById(R.id.tvElementTravellingModeReachedTime);
        tvElementTravellingModeTravelType = (TextView) findViewById(R.id.tvElementTravellingModeTravelType);
        tvElementTravellingModeDepartureAmount = (TextView) findViewById(R.id.tvElementTravellingModeDepartureAmount);
        tvElementTravellingModeSeat = (TextView) findViewById(R.id.tvElementTravellingModeSeat);


        tvElementHotelBookingName = (TextView) findViewById(R.id.tvElementHotelBookingName);
        tvElementHotelBookingTime = (TextView) findViewById(R.id.tvElementHotelBookingTime);
        tvElementHotelBookingRoomType = (TextView) findViewById(R.id.tvElementHotelBookingRoomType);
        tvElementHotelBookingAmount = (TextView) findViewById(R.id.tvElementHotelBookingAmount);
        tvElementHotelBookingRoomAvailable = (TextView) findViewById(R.id.tvElementHotelBookingRoomAvailable);
        try {
            arrayTripData = new JSONArray(SharedPreferenceUtil.getString(Constants.TRIP_BOOKING, ""));
            JSONObject objectTripData = arrayTripData.optJSONObject(0);
            JSONArray arrayPassengersData = objectTripData.getJSONArray(Constants.LIST_OF_PASSENGERS_DETAIL);


            tvFinalEventDetailsFromDate.setText("Date of Journey:- " + objectTripData.optString(Constants.EVENT_FROM_DATE));
            if (!TextUtils.isEmpty(objectTripData.optString(Constants.EVENT_TO_DATE)))
                tvFinalEventDetailsToDate.setText("Date of Return:- " + objectTripData.optString(Constants.EVENT_TO_DATE));
            else
                tvFinalEventDetailsToDate.setVisibility(View.INVISIBLE);

            tvFinalEventDetailsPickUp.setText("PickUp:- " + objectTripData.optString(Constants.EVENT_PICK_UP));
            tvFinalEventDetailsDropOff.setText("DropOff:- " + objectTripData.optString(Constants.EVENT_DROP_OFF));
            tvFinalEventDetailsPassengers.setText("No. of Passengers:- " + objectTripData.optString(Constants.EVENT_PASSENGERS_count));
            tvFinalEventDetailsLuggage.setText("Luggage:- " + objectTripData.optString(Constants.EVENT_LUGGAGE));
            tvFinalEventDetailsDurationName.setText("Trip:- " + objectTripData.optString(Constants.EVENT_DURATION_TYPE_NAME));
            tvFinalEventDetailsTravellingName.setText("Transportation:- " + objectTripData.optString(Constants.TRAVELLING_TYPE_NAME));
            if (!TextUtils.isEmpty(objectTripData.optString(Constants.EVENT_OTHERS_REQ)))
                tvFinalEventDetailsOtherReq.setText("Other Requirements:- " + objectTripData.optString(Constants.EVENT_OTHERS_REQ));
            else
                tvFinalEventDetailsOtherReq.setText("Other Requirements:- No Requirement");


            JSONArray arrayTravellingMode = objectTripData.optJSONArray(Constants.TRAVELLING_MODE);
            JSONObject objectTravellingMode = arrayTravellingMode.optJSONObject(0);


            tvElementTravellingModeName.setText(objectTravellingMode.optString(Constants.TRAVELLING_MODE_NAME));
            tvElementTravellingModeDepartureTime.setText(objectTravellingMode.optString(Constants.TRAVELLING_MODE_DEPARTURE_TIME));
            tvElementTravellingModeTimeAquire.setText(objectTravellingMode.optString(Constants.TRAVELLING_MODE_TIME_AQUIRE));
            tvElementTravellingModeReachedTime.setText(objectTravellingMode.optString(Constants.TRAVELLING_MODE_REACHED_TIME));
            tvElementTravellingModeTravelType.setText(objectTravellingMode.optString(Constants.TRAVELLING_MODE_TRAVEL_MODE));
            tvElementTravellingModeDepartureAmount.setText(objectTravellingMode.optString(Constants.TRAVELLING_MODE_AMOUNT));
            tvElementTravellingModeSeat.setText(objectTravellingMode.optString(Constants.TRAVELLING_MODE_SEAT));
            tvElementTravellingModeSeat.setVisibility(View.INVISIBLE);

            JSONArray arrayHotelBooking = objectTripData.optJSONArray(Constants.HOTEL_BOOKING);
            JSONObject objectHotelBooking = arrayHotelBooking.optJSONObject(0);


            tvElementHotelBookingName.setText(objectHotelBooking.optString(Constants.HOTEL_NAME));
            tvElementHotelBookingRoomType.setText(objectHotelBooking.optString(Constants.HOTEL_ROOM_TYPE));
            tvElementHotelBookingAmount.setText(objectHotelBooking.optString(Constants.HOTEL_AMOUNT));
            tvElementHotelBookingRoomAvailable.setText(objectHotelBooking.optString(Constants.HOTEL_ROOM_AVAILABILITY));
            tvElementHotelBookingRoomAvailable.setVisibility(View.INVISIBLE);


            lvFinalEventDetailsPassengersList.setAdapter(new AdapterListOfPassengers(this, arrayPassengersData, 1));
            setListViewHeightBasedOnChildren(lvFinalEventDetailsPassengersList);


            int amountTravel = Integer.valueOf(CommonUtil.removeSymbols(this, tvElementTravellingModeDepartureAmount.getText().toString().trim()));
            int amountHotel = Integer.valueOf(CommonUtil.removeSymbols(this, tvElementHotelBookingAmount.getText().toString().trim()));
            int passengerCount = Integer.valueOf(CommonUtil.removeSymbols(this, tvFinalEventDetailsPassengers.getText().toString().trim().replace("No. of Passengers:- ", "").trim()));
            int total = (amountHotel + amountTravel) * passengerCount;

            btFinalEventDetailsAmountSubmit.setText("PAY   ₹" + total);

            btFinalEventDetailsAmountSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    submit();
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void submit() {
        JSONObject objectMain = new JSONObject();
        try {
            if (SharedPreferenceUtil.contains(Constants.All_USER_DATA) && !TextUtils.isEmpty(SharedPreferenceUtil.getString(Constants.All_USER_DATA, "")))
                objectMain = new JSONObject(SharedPreferenceUtil.getString(Constants.All_USER_DATA, ""));

            Iterator iterator = objectMain.keys();
            while (iterator.hasNext()) {

                String key = (String) iterator.next();

                CommonUtil.byLogMessage(this, SharedPreferenceUtil.getString(Constants.CURRENT_USER_ID, "") + "--->" + key);

                if (SharedPreferenceUtil.getString(Constants.CURRENT_USER_ID, "").equalsIgnoreCase(key)) {

                    JSONArray arrayUserData = objectMain.optJSONArray(key);
                    JSONObject objectUserData = arrayUserData.optJSONObject(0);

                    JSONObject objectEventIndex = new JSONObject();
                    if (objectUserData.has(Constants.EVENT_INFO))
                        objectEventIndex = objectUserData.optJSONObject(Constants.EVENT_INFO);

                    objectEventIndex.put(CommonUtil.indexId(Constants.EVENT_ID), arrayTripData);
                    JSONArray arrayEventIndex = new JSONArray();
                    arrayEventIndex.put(objectEventIndex);

                    objectUserData.put(Constants.EVENT_INFO, arrayEventIndex);

                    SharedPreferenceUtil.putValue(Constants.All_USER_DATA, objectMain.toString());
                    SharedPreferenceUtil.save();
                    alertBox();
                    CommonUtil.byLogMessage(this, "Final Booking" + SharedPreferenceUtil.getString(Constants.All_USER_DATA, ""));
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void alertBox() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(FinalEventDetails.this);
        builder.setCancelable(false);
        builder.setTitle("Booked!");
        builder.setMessage("Confirmed your Booking");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialog, final int id) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(FinalEventDetails.this, DashBoard.class));
                    }
                }, 1000);
            }
        });
        builder.create();
        builder.show();
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
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
