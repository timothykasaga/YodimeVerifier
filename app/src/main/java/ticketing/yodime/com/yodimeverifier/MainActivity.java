package ticketing.yodime.com.yodimeverifier;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import HelperClasses.EmailValidator;
import HelperClasses.GeneralMethods;
import HelperClasses.JsonHandler;
import HelperClasses.YoDimeGeneralProperties;
import NavigationHandler.NavigationHandler;

public class MainActivity extends AppCompatActivity {
    private String yodime_preferences = "APP_PREFS";
    private SharedPreferences.Editor preferences_editor;
    SharedPreferences sharedPreferences;

    TextView login_email_address_txt_field,login_password_txt_field,forgot_password_txt_field;
    Button login_button,register_button1;

    /* Customized Classes used*/
    GeneralMethods generalMethods;
    NavigationHandler navigationHandler;
    private String member_email;
    private String member_password;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initializeFields(); //get references to the fields
        auto_fill_user_credentials(); // check for automatic login
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginButtonHandler();
            }
        });
        forgot_password_txt_field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ResetPassword.class));
            }
        });
    }

    /*
    * Method to initialize all fields in the UI layout
    * */
    public void initializeFields(){
        sharedPreferences = getSharedPreferences(yodime_preferences, Context.MODE_PRIVATE);
        generalMethods = new GeneralMethods();
        login_email_address_txt_field = (TextView)findViewById(R.id.login_email_address_txt_field);
        login_password_txt_field = (TextView)findViewById(R.id.login_password_txt_field);
        login_button = (Button)findViewById(R.id.login_button);
        register_button1 = (Button)findViewById(R.id.register_button1);
        forgot_password_txt_field = (TextView)findViewById(R.id.forgot_password);
    }

    public void auto_fill_user_credentials(){
        if (sharedPreferences.getString("member_email", null) != null) {
            if (sharedPreferences.getString("member_email", null).length() > 1) {
                login_email_address_txt_field.setText("" + sharedPreferences.getString("member_email", null).trim());
                login_email_address_txt_field.setFocusable(true);
                login_email_address_txt_field.setClickable(true);

                if(sharedPreferences.getString("member_password", null) != null)
                {
                    if(sharedPreferences.getString("member_password", null).length() > 1 &&
                            !sharedPreferences.getString("member_password", null).toString().equalsIgnoreCase("None"))
                    {
                        login_password_txt_field.setText(sharedPreferences.getString("member_password", null).toString().trim());

                        loginButtonHandler();
                        generalMethods.displayToastToUser(MainActivity.this,"Automatic Login");
                    }else
                    {
                        //generalMethods.displayToastToUser(MainActivity.this,"No password saved");
                    }

                }else
                {
                    //generalMethods.displayToastToUser(MainActivity.this,"No password saved");
                }
            }else
            {
                //generalMethods.displayToastToUser(MainActivity.this,"No mail saved");
            }
        }
    }

    private void loginButtonHandler() {

        //Validate Login details
        try
        {
            member_email = login_email_address_txt_field.getText().toString();
            member_password = login_password_txt_field.getText().toString();
            if(member_email != null && member_password != null)
            {
                if(new EmailValidator().validateEmail(member_email.trim()))
                {
                    if(member_password.trim().length() > 4)
                    {

                        progressDialog = generalMethods.showProgressDialog(MainActivity.this,"Processing Login","Please Wait");
                        progressDialog.show();

                        JSONObject login_json = new JSONObject();
                        login_json.put("member_email",""+member_email);
                        login_json.put("member_password", "" + member_password);
                        login_json.put("app_version",""+ YoDimeGeneralProperties.getYodime_app_verion());
                        String device_name=YoDimeGeneralProperties.getManufacturer()+" "+ YoDimeGeneralProperties.getModel();

                        login_json.put("device_name", "" +device_name);
                        login_json.put("device_sim", "" +this.getMobileNumber());
                        login_json.put("serial", "" +YoDimeGeneralProperties.getSerial_number());
                        login_json.put("brand", "" +YoDimeGeneralProperties.getBrand());

                        System.out.println("App_version: " + YoDimeGeneralProperties.getYodime_app_verion()+"" +
                                "device_name "+device_name+ "device_sim"+this.getMobileNumber()
                                +"brand: "+YoDimeGeneralProperties.getBrand()+"serial: "+YoDimeGeneralProperties.getSerial_number());

                        new LoginAsyncTask().execute(login_json);
                    }
                    else
                    {
                        generalMethods.showLocationDialog(MainActivity.this,"LogIn failed","LogIn failed, invalid password","OK","Close");
                       Toast.makeText(getApplicationContext(), "LogIn failed, invalid password", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    generalMethods.showLocationDialog(MainActivity.this,"LogIn failed","invalid email address","OK","Close");
                    Toast.makeText(getApplicationContext(), "LogIn failed, invalid email address", Toast.LENGTH_LONG).show();
                }
            }
        }
        catch(Exception exc)
        {
            progressDialog.dismiss();
            generalMethods.showLocationDialog(MainActivity.this,"LogIn Error","LogIn failed, please try again","OK","Close");
            Toast.makeText(getApplicationContext(), "LogIn failed, please try again", Toast.LENGTH_LONG).show();
            exc.printStackTrace();
        }
    }

    private String getMobileNumber()
    {
        TelephonyManager t = (TelephonyManager)getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        return t.getLine1Number();
    }

    private class LoginAsyncTask extends AsyncTask<JSONObject, Integer, String>
    {
        JsonHandler jsonHandler = new JsonHandler();
        @Override
        protected String doInBackground(JSONObject... params)
        {
            try
            {
                JSONObject login_json = params[0];
                String response = jsonHandler.sendJsonWithResponse(YoDimeGeneralProperties.getYodime_login_url(), login_json);
                return response;
            }
            catch(Exception exc)
            {
                Toast.makeText(getApplicationContext(), "LogIn failed, please try again", Toast.LENGTH_LONG).show();
                System.out.println("MainActivity#MyAsyncTask#doInBackground exc : "+exc.getMessage());
                return null;
            }
        }

        protected void onPostExecute(String result)
        {
            try
            {
                progressDialog.dismiss();
                if(result == null)
                {
                    generalMethods.showLocationDialog(MainActivity.this,"Login Error","Login failed, error retrieving response","OK","CLOSE");
                    System.out.println("null result string");
                    Toast.makeText(getApplicationContext(), "LogIn failed, error retrieving response", Toast.LENGTH_LONG).show();
                }
                else
                {
                    //Get Important values from Login Response after decryption

                    System.out.println("response string : "+result.trim());
                    String login_response = jsonHandler.getLogInStatusFromJsonResponse(result.trim());
                    System.out.println("login response : "+login_response);

                    String app_status = jsonHandler.getAppStatusFromJsonResponse(result.trim());
                    System.out.println("app_status : "+app_status);

                    //successful login
                    if(login_response.equalsIgnoreCase("true"))
                    {
                        System.out.println("+++++++++++++++++LOGING IN PREFERENCES++++++++++++++++++++++++++++++++++++++++++++");
                        preferences_editor = sharedPreferences.edit();
                        preferences_editor.putString("member_email", member_email);
                        preferences_editor.putString("member_password", member_password);
                        preferences_editor.putString("app_status", app_status);
                        preferences_editor.commit();

                        Toast.makeText(getApplicationContext(), "Successful Login", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,TicketVerifcation.class);
                        startActivity(intent);
                        finish();

                    }
                    //failed login, wrong credentials
                    else
                    {
                        String login_error = jsonHandler.getErrorStatusFromJsonResponse(result.trim());
                        if(login_error.trim().equalsIgnoreCase("Invalid credentials"))
                        {
                            generalMethods.showLocationDialog(MainActivity.this,"Login Error","LogIn failed, wrong login email or password","OK","CLOSE");
                            Toast.makeText(getApplicationContext(), "LogIn failed, wrong login email or password", Toast.LENGTH_LONG).show();
                        }
                        else if(login_error.trim().equalsIgnoreCase("Unverified email address"))
                        {
                            generalMethods.showLocationDialog(MainActivity.this,"Login Error","LogIn failed, unverified email address","OK","CLOSE");
                            Toast.makeText(getApplicationContext(), "LogIn failed, unverified email address", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            generalMethods.showLocationDialog(MainActivity.this,"Login Error",login_error,"OK","CLOSE");
                            Toast.makeText(getApplicationContext(), "LogIn failed, "+login_error, Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
            catch(Exception exc)
            {
                generalMethods.showLocationDialog(MainActivity.this,"Login Error","LogIn failed, please try again","OK","CLOSE");
                Toast.makeText(getApplicationContext(), "LogIn failed, please try again", Toast.LENGTH_LONG).show();
                System.out.println("MainActivity#MyAsyncTask#doInBackground exc : "+exc.getMessage());
            }
        }

        protected void onProgressUpdate(Integer... progress){
            //progressView.setProgress(progress[0]);
        }


    }
}

