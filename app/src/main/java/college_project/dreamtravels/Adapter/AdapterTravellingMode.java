package college_project.dreamtravels.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import college_project.dreamtravels.R;
import college_project.dreamtravels.Util.Constants;

public class AdapterTravellingMode extends BaseAdapter {

    private LayoutInflater inflater = null;
    private JSONArray data;
    private Activity activity;

    public AdapterTravellingMode(Activity activity, JSONArray arrayPassengersDetail) {
        this.data = arrayPassengersDetail;
        this.activity = activity;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return data.length();
    }

    public Object getItem(int position) {
        return data.optJSONObject(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final JSONObject jsonObject = data.optJSONObject(position);

        if (convertView == null)
            view = inflater.inflate(R.layout.element_travelling_mode, null);

        final TextView tvElementTravellingModeName, tvElementTravellingModeDepartureTime,
                tvElementTravellingModeTimeAquire, tvElementTravellingModeReachedTime,
                tvElementTravellingModeTravelType, tvElementTravellingModeDepartureAmount,
                tvElementTravellingModeSeat;

        tvElementTravellingModeName = (TextView) view.findViewById(R.id.tvElementTravellingModeName);
        tvElementTravellingModeDepartureTime = (TextView) view.findViewById(R.id.tvElementTravellingModeDepartureTime);
        tvElementTravellingModeTimeAquire = (TextView) view.findViewById(R.id.tvElementTravellingModeTimeAquire);
        tvElementTravellingModeReachedTime = (TextView) view.findViewById(R.id.tvElementTravellingModeReachedTime);
        tvElementTravellingModeTravelType = (TextView) view.findViewById(R.id.tvElementTravellingModeTravelType);
        tvElementTravellingModeDepartureAmount = (TextView) view.findViewById(R.id.tvElementTravellingModeDepartureAmount);
        tvElementTravellingModeSeat = (TextView) view.findViewById(R.id.tvElementTravellingModeSeat);

        tvElementTravellingModeName.setText(jsonObject.optString(Constants.TRAVELLING_MODE_NAME));
        tvElementTravellingModeDepartureTime.setText(jsonObject.optString(Constants.TRAVELLING_MODE_DEPARTURE_TIME));
        tvElementTravellingModeReachedTime.setText(jsonObject.optString(Constants.TRAVELLING_MODE_REACHED_TIME));
        tvElementTravellingModeTravelType.setText(jsonObject.optString(Constants.TRAVELLING_MODE_TRAVEL_MODE));
        tvElementTravellingModeSeat.setText(jsonObject.optString(Constants.TRAVELLING_MODE_SEAT));
        tvElementTravellingModeTimeAquire.setText(jsonObject.optString(Constants.TRAVELLING_MODE_TIME_AQUIRE));
        tvElementTravellingModeDepartureAmount.setText(jsonObject.optString(Constants.TRAVELLING_MODE_AMOUNT));



        return view;
    }
}