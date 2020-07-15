package college_project.dreamtravels;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mpt.storage.SharedPreferenceUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import college_project.dreamtravels.Util.CommonUtil;
import college_project.dreamtravels.Util.Constants;

public class SignUp extends AppCompatActivity {
    EditText etSignUpFirstName, etSignUpLastName, etSignUpContact, etSignUpEmail,
            etSignUpPassword, etSignUpConfirmPassword;
    Button btSignUpSaveNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etSignUpFirstName = (EditText) findViewById(R.id.etSignUpFirstName);
        etSignUpLastName = (EditText) findViewById(R.id.etSignUpLastName);
        etSignUpContact = (EditText) findViewById(R.id.etSignUpContact);
        etSignUpEmail = (EditText) findViewById(R.id.etSignUpEmail);
        etSignUpPassword = (EditText) findViewById(R.id.etSignUpPassword);
        etSignUpConfirmPassword = (EditText) findViewById(R.id.etSignUpConfirmPassword);
        btSignUpSaveNext = (Button) findViewById(R.id.btSignUpSaveNext);

        etSignUpFirstName.setFilters(new InputFilter[]{CommonUtil.textFilter});
        etSignUpLastName.setFilters(new InputFilter[]{CommonUtil.textFilter});
        etSignUpLastName.setNextFocusForwardId(R.id.etSignUpContact);

        btSignUpSaveNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitConditions();
            }
        });
    }

    public boolean checkEmail() {

        boolean check = false;

        try {
            JSONObject objectMain = new JSONObject(SharedPreferenceUtil.getString(Constants.All_USER_DATA, ""));
            JSONArray arrayUserData = new JSONArray();

            for (int i = 1; i <= objectMain.length(); i++) {
                arrayUserData = objectMain.getJSONArray(i + "");

                JSONObject objectUserData = arrayUserData.optJSONObject(0);

                JSONArray arrayUserProfile = objectUserData.getJSONArray(Constants.USER_PROFILE);
                JSONObject objectUserProfile = arrayUserProfile.getJSONObject(0);

                if (objectUserProfile.optString(Constants.USER_EMAIL).equalsIgnoreCase(etSignUpEmail.getText().toString().trim())) {
                    check = false;
                    break;
                } else
                    check = true;

                CommonUtil.byLogMessage(this, true + "-->" + objectUserProfile.optString(Constants.USER_EMAIL) + "-->" + etSignUpEmail.getText().toString().trim());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean checkContact() {

        boolean check = false;

        try {
            JSONObject objectMain = new JSONObject(SharedPreferenceUtil.getString(Constants.All_USER_DATA, ""));
            JSONArray arrayUserData = new JSONArray();

            for (int i = 1; i <= objectMain.length(); i++) {
                arrayUserData = objectMain.getJSONArray(i + "");


                JSONObject objectUserData = arrayUserData.optJSONObject(0);

                JSONArray arrayUserProfile = objectUserData.getJSONArray(Constants.USER_PROFILE);
                JSONObject objectUserProfile = arrayUserProfile.getJSONObject(0);

                if (objectUserProfile.optString(Constants.USER_MO).equalsIgnoreCase(etSignUpContact.getText().toString().trim())) {
                    check = false;
                    break;
                } else
                    check = true;

                CommonUtil.byLogMessage(this, check + "-->" + objectUserProfile.optString(Constants.USER_MO) + "-->" + etSignUpContact.getText().toString().trim());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return check;
    }

    public void submitConditions() {

        try {
            if (TextUtils.isEmpty(etSignUpFirstName.getText().toString()) || TextUtils.isEmpty(etSignUpLastName.getText().toString()) || TextUtils.isEmpty(etSignUpContact.getText().toString()) || TextUtils.isEmpty(etSignUpEmail.getText().toString()) || TextUtils.isEmpty(etSignUpPassword.getText().toString()) || TextUtils.isEmpty(etSignUpConfirmPassword.getText().toString()))
                CommonUtil.byToastMessage(this, getResources().getString(R.string.enter_fields_below));
            else if (etSignUpFirstName.getText().toString().length() < 2 || etSignUpFirstName.getText().toString().length() > 20)
                CommonUtil.byToastMessage(this, getString(R.string.enter_first_name_validation));
            else if (etSignUpLastName.getText().toString().length() < 2 || etSignUpLastName.getText().toString().length() > 20)
                CommonUtil.byToastMessage(this, getString(R.string.enter_last_name_validation));
            else if (etSignUpContact.length() != 10)
                CommonUtil.byToastMessage(this, getString(R.string.enter_valid_contact));
            else if (!TextUtils.isEmpty(SharedPreferenceUtil.getString(Constants.All_USER_DATA, "")) && !checkContact())
                CommonUtil.byToastMessage(this, getString(R.string.alred_exist));
            else if (!CommonUtil.isValidEmail(etSignUpEmail.getText().toString()))
                CommonUtil.byToastMessage(this, getString(R.string.enter_valid_email_id));
            else if (!TextUtils.isEmpty(SharedPreferenceUtil.getString(Constants.All_USER_DATA, "")) && !checkEmail())
                CommonUtil.byToastMessage(this, getString(R.string.email_alred_exist));
            else if (etSignUpPassword.length() < 7 || etSignUpPassword.length() > 15 || !CommonUtil.isLegalPassword(etSignUpPassword.getText().toString()) || CommonUtil.isSpecialChar(etSignUpPassword.getText().toString()))
                CommonUtil.byToastMessage(this, getString(R.string.enter_valid_pass));
            else if (!etSignUpConfirmPassword.getText().toString().equals(etSignUpPassword.getText().toString()))
                CommonUtil.byToastMessage(this, getString(R.string.conpass_pass_not_same));

            else {
                JSONArray arrayUserData = new JSONArray("[" + SharedPreferenceUtil.getString(Constants.USER_SIGN_UP_DETAILS, "") + "]");
                if (arrayUserData.length() != 0)
                    for (int i = 0; i < arrayUserData.length(); i++) {
                        JSONObject objectUserData = arrayUserData.optJSONObject(i);

                        if (objectUserData.optString(Constants.USER_EMAIL).equalsIgnoreCase(etSignUpEmail.getText().toString().trim())) {
                            CommonUtil.byToastMessage(this, getString(R.string.email_alred_exist));
                            break;
                        } else if (objectUserData.optString(Constants.USER_MO).equalsIgnoreCase(etSignUpContact.getText().toString().trim())) {
                            CommonUtil.byToastMessage(this, getString(R.string.contact_already_exist));
                            break;
                        } else {
                            CommonUtil.closeKeyboard(SignUp.this);
                            doSignUp();
                        }
                    }
                else {
                    CommonUtil.closeKeyboard(SignUp.this);
                    doSignUp();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void doSignUp() {
        JSONObject objectUserProfile = new JSONObject();
        String userId = CommonUtil.indexId(Constants.USER_ID);

        try {
            objectUserProfile.put(Constants.USER_ID, userId)
                    .put(Constants.USER_FULL_NAME, etSignUpFirstName.getText().toString().trim() + " " + etSignUpLastName.getText().toString().trim())
                    .put(Constants.USER_FIRST, etSignUpFirstName.getText().toString().trim())
                    .put(Constants.USER_LAST, etSignUpLastName.getText().toString().trim())
                    .put(Constants.USER_MO, etSignUpContact.getText().toString().trim())
                    .put(Constants.USER_EMAIL, etSignUpEmail.getText().toString().trim())
                    .put(Constants.USER_PASS, etSignUpPassword.getText().toString().trim());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject objectMain = new JSONObject();
        try {
            if (SharedPreferenceUtil.contains(Constants.All_USER_DATA) && !TextUtils.isEmpty(SharedPreferenceUtil.getString(Constants.All_USER_DATA, "")))
                objectMain = new JSONObject(SharedPreferenceUtil.getString(Constants.All_USER_DATA, ""));

            JSONArray arrayUserProfile = new JSONArray();
            arrayUserProfile.put(objectUserProfile);

            JSONObject objectUserData = new JSONObject();
            objectUserData.put(Constants.USER_PROFILE, arrayUserProfile);

            JSONArray arrayUserData = new JSONArray();
            arrayUserData.put(objectUserData);

            objectMain.put(userId, arrayUserData);

            SharedPreferenceUtil.putValue(Constants.ACCESS_TOKEN, 1);
            SharedPreferenceUtil.putValue(Constants.CURRENT_USER_ID, userId);
            SharedPreferenceUtil.putValue(Constants.All_USER_DATA, objectMain.toString().trim());
            SharedPreferenceUtil.save();

            CommonUtil.byLogMessage(this, SharedPreferenceUtil.getString(Constants.All_USER_DATA, ""));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        startActivity(new Intent(this, DashBoard.class));
    }
}