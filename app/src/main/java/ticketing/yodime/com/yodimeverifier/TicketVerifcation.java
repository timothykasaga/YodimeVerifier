package ticketing.yodime.com.yodimeverifier;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import HelperClasses.GeneralMethods;
import HelperClasses.JsonHandler;
import HelperClasses.YoDimeGeneralProperties;
import NavigationHandler.NavigationHandler;
import TicketingBeans.TicketVerification;

public class TicketVerifcation extends AppCompatActivity {
    private TextView ticket_verification_validate_results_ticket_name_txtfield;
    private TextView ticket_verification_validate_results_ticket_category_txtfield;
    private TextView ticket_verification_validate_results_ticket_expires_txtfield;
    private TextView ticket_verification_validate_results_ticket_id_txtfield;
    private TextView ticket_verification_validate_results_ticket_status_txtfield;
    private TextView ticket_verification_validate_results_ticket_amount_paid_txtfield;

    private String yodime_preferences = "APP_PREFS";
    private SharedPreferences.Editor preferences_editor;
    SharedPreferences sharedPreferences;

    AlertDialog dialog_view;
    ProgressDialog progressDialog;
    GeneralMethods generalMethods;
    Toolbar toolbar2;
    TextView ticket_number_txt_field;
    Button ticket_verification_btn;
    private String ticket_number_string;
    private String member_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket_verifcation);
        initializeFields();
        buttonHandler();

    }




    private void initializeFields() {
        sharedPreferences = getSharedPreferences(yodime_preferences, Context.MODE_PRIVATE);
        generalMethods = new GeneralMethods();
        member_email = sharedPreferences.getString("member_email","");
        ticket_number_txt_field = (TextView)findViewById(R.id.ticket_number_txt_field);
        ticket_verification_btn =(Button)findViewById(R.id.ticket_verification_btn);
        toolbar2 = (Toolbar) findViewById(R.id.toolbar);
        toolbar2.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        setSupportActionBar(toolbar2);
    }

    private void buttonHandler(){
        ticket_verification_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ticket_number_txt_field.getText().toString().equalsIgnoreCase("") || ticket_number_txt_field.getText().toString().length() == 0 ){
                    generalMethods.displayToastToUser(TicketVerifcation.this,"Please enter a Ticket number");
                }else{
                    progressDialog = generalMethods.showProgressDialog(TicketVerifcation.this,"Verifying","Please wait ...");
                    progressDialog.show();
                    ticket_number_string = ticket_number_txt_field.getText().toString();
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("member_email",member_email);
                        jsonObject.put("ticket_number",ticket_number_string);
                        new VerificationAsyncTask().execute(jsonObject);
                    } catch (JSONException e) {
                        progressDialog.dismiss();
                        generalMethods.displayToastToUser(TicketVerifcation.this,"Error verifying results, Check Internet connection");
                        Log.d("Timo Error","Error Getting Member Mail and Ticket Number");
                    }

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_logout:{
                AlertDialog.Builder builder = new AlertDialog.Builder(TicketVerifcation.this);
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
                        Intent intent = new Intent(TicketVerifcation.this, MainActivity.class);
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

    private class VerificationAsyncTask extends AsyncTask<JSONObject, Integer, String> {
        JsonHandler jsonHandler = new JsonHandler();

        @Override
        protected String doInBackground(JSONObject... params) {
            try {

                JSONObject sell_ticket_json = params[0];
                String response = jsonHandler.sendJsonWithResponse(YoDimeGeneralProperties.getYodime_merchant_ticket_verification_url(), sell_ticket_json);
                return response;
            } catch (Exception exc) {
                Toast.makeText(getApplicationContext(), "Transaction not initiated, please try again", Toast.LENGTH_LONG).show();
                System.out.println("VerifyTicketFragment#VerificationAsyncTask#doInBackground exc : " + exc.getMessage());
                return null;
            }
        }

        protected void onPostExecute(String result) {
            progressDialog.dismiss();
            if(result == null)
            {
                generalMethods.showLocationDialog(TicketVerifcation.this,"Verification Error",
                        "Verification failed, check your internet connection","OK","EXIT");
            }
            else {

                String response_status = jsonHandler.getUtilityPaymentStatusFromJsonResponse(result);

                if(response_status.trim().equalsIgnoreCase("success"))
                {
                    Log.d("Verified Results",result);
                    final TicketVerification ticketVerification = jsonHandler.getTicketVerificationResultsFromJsonString(result);
                    if(ticketVerification == null)
                    {
                        generalMethods.showLocationDialog(TicketVerifcation.this,"sell Error","Transaction failed, error retrieving Ticket Sell details","OK","CLOSE");
                        Toast.makeText(getApplicationContext(), "Transaction failed, error retrieving Ticket Sell details", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                          /* try to create a custom dialog*/
                        LayoutInflater linf = LayoutInflater.from(TicketVerifcation.this);
                        final View inflator = linf.inflate(R.layout.ticket_sell_verification_results_dialog, null);
                        AlertDialog.Builder builder = new AlertDialog.Builder(TicketVerifcation.this);
                        builder.setTitle("Validation Results");
                        builder.setView(inflator);
                        String positiveText = "Confirm";
                        builder.setPositiveButton(positiveText,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        try {
                                            // positive button logic
                                            JSONObject jsonObject = new JSONObject();
                                            jsonObject.put("member_email", member_email);
                                            jsonObject.put("reference_id",ticketVerification.getTicket_reference_id());
                                            new VerificationConfirmationAsyncTask().execute(jsonObject);
                                            progressDialog = generalMethods.showProgressDialog(TicketVerifcation.this,"Clearing Ticket","Please wait");
                                            progressDialog.show();
                                        } catch (JSONException e) {
                                            Log.d("#JSON Exception at: ","#ticket_verification_validate_results_confirm_btn");
                                        }
                                        dialog_view.dismiss();

                                    }
                                });

                        String negativeText = "Decline";
                        builder.setNegativeButton(negativeText,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog_view.dismiss();
                                        // negative button logic
                                    }
                                });

                        dialog_view = builder.create();
                        ticket_verification_validate_results_ticket_name_txtfield = (TextView)inflator.findViewById(R.id.ticket_verification_validate_results_ticket_name_text_field);
                        ticket_verification_validate_results_ticket_category_txtfield = (TextView)inflator.findViewById(R.id.ticket_verification_validate_results_category_text_field); ;
                        ticket_verification_validate_results_ticket_expires_txtfield = (TextView)inflator.findViewById(R.id.ticket_verification_results_validate_expiry_date_text_field);
                        ticket_verification_validate_results_ticket_id_txtfield = (TextView)inflator.findViewById(R.id.ticket_verification_validate_resultsticket_id_text_field);
                        ticket_verification_validate_results_ticket_status_txtfield = (TextView)inflator.findViewById(R.id.ticket_verification_results_validate_ticket_status_text_field);
                        ticket_verification_validate_results_ticket_amount_paid_txtfield = (TextView)inflator.findViewById(R.id.ticket_verification_validate_results_amount_paid_text_field);;

                        ticket_verification_validate_results_ticket_name_txtfield.setText(ticketVerification.getTicket_name());
                        ticket_verification_validate_results_ticket_category_txtfield.setText(ticketVerification.getTicket_category());
                        ticket_verification_validate_results_ticket_expires_txtfield.setText(ticketVerification.getExpire_date());
                        ticket_verification_validate_results_ticket_id_txtfield.setText(ticketVerification.getTicket_id());
                        ticket_verification_validate_results_ticket_status_txtfield.setText(ticketVerification.getTicket_status());
                        ticket_verification_validate_results_ticket_amount_paid_txtfield.setText(ticketVerification.getAmount_paid());

                        // display dialog
                        dialog_view.show();
                    }
                } else if(response_status.trim().equalsIgnoreCase("failed"))
                {
                    //Check response_description
                    generalMethods.showLocationDialog(TicketVerifcation.this,"Clearance Message",jsonHandler.getUtilityPaymentDescriptionFromJsonResponse(result),"OK","CLOSE");

                } else
                {
                    generalMethods.showLocationDialog(TicketVerifcation.this,"Verification Error","Transaction failed, check your internet connection","OK","CLOSE");
                    Toast.makeText(getApplicationContext(), "Transaction failed, check your internet connection", Toast.LENGTH_LONG).show();
                }

            }
        }
    }


    private class VerificationConfirmationAsyncTask extends AsyncTask<JSONObject, Integer, String> {
        JsonHandler jsonHandler = new JsonHandler();

        @Override
        protected String doInBackground(JSONObject... params) {
            try {

                JSONObject sell_ticket_json = params[0];
                String response = jsonHandler.sendJsonWithResponse(YoDimeGeneralProperties.getYodime_ticket_verification_url(), sell_ticket_json);
                return response;
            } catch (Exception exc) {
                Toast.makeText(getApplicationContext(), "Transaction not initiated, please try again", Toast.LENGTH_LONG).show();
                System.out.println("VerifyTicketFragment#VerificationConfirmationAsyncTask#doInBackground exc : " + exc.getMessage());
                return null;
            }
        }

        protected void onPostExecute(String result) {
            progressDialog.dismiss();
            if (result == null) {

                generalMethods.showLocationDialog(TicketVerifcation.this,"Clearance Error","Transaction failed, check your internet connection","OK","CLOSE");

            } else {

                String response_status = jsonHandler.getUtilityPaymentStatusFromJsonResponse(result);

                if(response_status.trim().equalsIgnoreCase("success"))
                {
                    generalMethods.showLocationDialog(TicketVerifcation.this,"Clearance Message","Ticket cleared,\n" + "Thank you for using our service","OK","CLOSE");

                }else if (response_status.trim().equalsIgnoreCase("failed")){
                    //Check response_description
                    generalMethods.showLocationDialog(TicketVerifcation.this,"Clearance Error",jsonHandler.getUtilityPaymentDescriptionFromJsonResponse(result),"OK","CLOSE");

                } else
                {

                    generalMethods.showLocationDialog(TicketVerifcation.this,"Clearance Error","Transaction failed, check your internet connection","OK","CLOSE");

                }

            }
        }
    }



}
