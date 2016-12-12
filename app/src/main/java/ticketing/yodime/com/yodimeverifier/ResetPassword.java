package ticketing.yodime.com.yodimeverifier;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import HelperClasses.GeneralMethods;
import HelperClasses.JsonHandler;
import HelperClasses.YoDimeGeneralProperties;

public class ResetPassword extends AppCompatActivity {

    private Button reset_password;
    private EditText member_mail_edit_text;
    private String member_mail;

    private String yodime_preferences = "APP_PREFS";
    private SharedPreferences.Editor preferences_editor;
    SharedPreferences sharedPreferences;

    ProgressDialog progressDialog;
    GeneralMethods generalMethods;
    private Toolbar toolbar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        InitialiseFields();
        sharedPreferences = getSharedPreferences(yodime_preferences, Context.MODE_PRIVATE);

       reset_password= (Button)findViewById(R.id.forgot_password_reset_button);
        member_mail_edit_text=(EditText)findViewById(R.id.member_mail_forgot_password_txt_field);
        member_mail=member_mail_edit_text.getText().toString();

        reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = generalMethods.showProgressDialog(ResetPassword.this,"Password Reset","Please wait ...");
                progressDialog.show();
                try
                {
                    JSONObject jsonObject= new JSONObject();
                    jsonObject.put("member_email",""+member_mail);

                    new ResetPasswordAsyncTask().execute(jsonObject);
                }
                catch(Exception e)
                {
                progressDialog.dismiss();
                }
            }
        });



    }


    @Override
    public void onBackPressed() {
        // app_close_dialog.show();
        finish();

    }



    private class ResetPasswordAsyncTask extends AsyncTask<JSONObject, Integer, String>
    {
        JsonHandler jsonHandler = new JsonHandler();
        @Override
        protected String doInBackground(JSONObject... params)
        {
            try
            {
                JSONObject login_json = params[0];

                String response = jsonHandler.sendJsonWithResponse(YoDimeGeneralProperties.getYodime_reset_password_url(),login_json);
                return response;
            }
            catch(Exception exc)
            {
                progressDialog.dismiss();
                Toast.makeText(ResetPassword.this,"Reset Password request failed, please try again", Toast.LENGTH_LONG).show();
                System.out.println("Password Reset#PaymentAsyncTask#doInBackground exc : " + exc.getMessage());
                exc.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(String result)
        {
            progressDialog.dismiss();
            try
            {

                if(result == null)
                {
                    generalMethods.showLocationDialog(ResetPassword.this,"Password Reset Error",
                            "Reset Password request failed, check your internet connection","OK","CLOSE");
                    Toast.makeText(ResetPassword.this, "Reset Password request failed, check your internet connection", Toast.LENGTH_LONG).show();
                }
                else
                {
                    String response_status = jsonHandler.getUtilityPaymentStatusFromJsonResponse(result);
                    if(response_status.trim().equalsIgnoreCase("success"))
                    {

                        member_mail_edit_text.setText("");
                        String response_description = jsonHandler.getUtilityPaymentDescriptionFromJsonResponse(result);
                        generalMethods.showLocationDialog(ResetPassword.this,"Reset Password request Response",
                                "Reset Password request successful, "+response_description,"OK","CLOSE");

                        Toast.makeText(ResetPassword.this, "Reset Password request successful", Toast.LENGTH_LONG).show();
                    }
                    else if(response_status.trim().equalsIgnoreCase("failed"))
                    {
                        String response_desription = jsonHandler.getPersonalTopUpDescriptionromJsonResponse(result);
                        generalMethods.showLocationDialog(ResetPassword.this,"Password Reset Error",
                                "Reset Password request failed, "+response_desription,"OK","CLOSE");

                        Toast.makeText(ResetPassword.this, "Reset Password request failed, "+response_desription, Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        generalMethods.showLocationDialog(ResetPassword.this,"Password Reset Error",
                                "Reset Password request failed, check your internet connection","OK","CLOSE");
                        Toast.makeText(ResetPassword.this, "Reset Password request failed, check your internet connection", Toast.LENGTH_LONG).show();
                    }
                }
            }
            catch(Exception exc)
            {
                generalMethods.showLocationDialog(ResetPassword.this,"Password Reset Error",
                        "Reset Password request failed, please try again","OK","CLOSE");
                System.out.println("Password Reset#Password ResetAsyncTask#onPostExecute exc : " + exc.getMessage());
                exc.printStackTrace();
            }
        }

        protected void onProgressUpdate(Integer... progress)
        {
            //progressView.setProgress(progress[0]);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.action_logout:{
                AlertDialog.Builder builder = new AlertDialog.Builder(ResetPassword.this);
                builder.setMessage("Close Application");
                builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                builder.setNegativeButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sharedPreferences = getSharedPreferences(yodime_preferences, Context.MODE_PRIVATE);
                        preferences_editor = sharedPreferences.edit();
                        preferences_editor.clear();
                        preferences_editor.commit();
                        Intent intent = new Intent(ResetPassword.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                    }
                });

                AlertDialog dialog = builder.create();
                // display dialog
                dialog.show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void InitialiseFields(){
            generalMethods = new GeneralMethods();
        toolbar2 = (Toolbar) findViewById(R.id.toolbar);
        toolbar2.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        setSupportActionBar(toolbar2);
    }

}
