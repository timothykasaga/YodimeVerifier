package HelperClasses;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import TicketingBeans.TicketVerification;


/**
 * Created by XT on 10/12/2015.
 */
public class JsonHandler {
    //HttpsURLConnection conn =null;
    HttpURLConnection conn =null;


    AES_security_implementations security= new AES_security_implementations();
    OutputStream os=null;
    BufferedReader br=null;
    public String sendJsonWithResponse(String url_string,JSONObject json_object)
    {
        System.out.println("url_string "+url_string);
        try {
            URL url = new URL(url_string);
           //  conn = (HttpsURLConnection) url.openConnection();
             conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setConnectTimeout(90000); //set to 3 minutes
            conn.setReadTimeout(90000);
            //conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            //conn.setChunkedStreamingMode(0);

             os = conn.getOutputStream();
           String EncodedString= security.encrypt(json_object.toString());
           // String EncodedString= json_object.toString();
            os.write(EncodedString.getBytes());
            os.flush();
            Log.d("Sending JSON:",EncodedString);
            //conn.disconnect();
            System.out.println("JSONResponse sending json to merchant finished working");
             br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String output;
            System.out.println("JSONResponse sending json to merchant output from server .... \n");
            String result = "";
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                result += output;
            }
            System.out.println("result = "+result);
            if(result == null)
            {
                return null;
            }
            else
            {
                String decodedString= security.decrypt(result);
                //String decodedString= result;

                return decodedString.trim();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("JSONHandler#sendJsonWithResponse exception : " + e.getMessage());
            try {
                System.out.println("JSONHandler#sendJsonWithResponse exception response code: " + conn.getResponseCode());
            }catch(IOException ioe){
                System.out.println("JSONHandler#sendJsonWithResponseIO exception : " + ioe.getMessage());
            }

            e.printStackTrace();
            return null;
        }finally{
            try {
                if (conn != null) {
                    conn.disconnect();
                }
                if (br != null) {
                    br.close();
                }
                if (os != null) {
                    os.flush();
                    os.close();
                }

            }catch(Exception e){
                e.printStackTrace();

            }
        }
    }


    public String getAppStatusFromJsonResponse(String response_string)
    {
        try
        {
            if(response_string == null)
            {
                return null;
            }
            else
            {
                JSONObject json_obj = new JSONObject(response_string.trim());
                if(json_obj == null)
                {
                    return null;
                }
                else
                {
                    String app_status = json_obj.getString("app_status");
                    if(app_status == null)
                    {
                        return null;
                    }
                    else
                    {
                        return app_status.trim();
                    }
                }
            }
        }
        catch(Exception exc)
        {
            System.out.println("JSONHandler#getregisterStatusFromJsonResponse exception : " + exc.getMessage());
            return null;
        }
    }

    public String getYodimeUniqueIdFromJsonResponse(String response_string)
    {
        try
        {
            if(response_string == null)
            {
                return null;
            }
            else
            {
                JSONObject json_obj = new JSONObject(response_string.trim());
                if(json_obj == null)
                {
                    return null;
                }
                else
                {
                    if(json_obj.has("yodime_unique_member_id")){
                        String yodime_unique_member_id = json_obj.getString("yodime_unique_member_id");
                        if(yodime_unique_member_id == null)
                        {
                            return null;
                        }
                        else
                        {
                            return yodime_unique_member_id.trim();
                        }
                    }else{
                        return null;
                    }

                }
            }
        }
        catch(Exception exc)
        {
            System.out.println("JSONHandler#getyodimeUniqueMemberIdFromJsonResponse exception : " + exc.getMessage());
            return null;
        }
    }

    public String getIsTicketingEnabledStatusFromJsonResponse(String response_string)
    {
        try
        {
            if(response_string == null)
            {
                return null;
            }
            else
            {
                JSONObject json_obj = new JSONObject(response_string.trim());
                if(json_obj == null)
                {
                    return null;
                }
                else
                {
                    if(json_obj.has("is_ticketing_enabled_status")){
                        String is_ticketing_enabled_status = json_obj.getString("is_ticketing_enabled_status");
                        if(is_ticketing_enabled_status.contains("true"))
                        {
                            return "true";
                        }
                        else
                        {
                            return "false";
                        }
                    }else{
                        return "false";
                    }
                }
            }
        }
        catch(Exception exc)
        {
            System.out.println("JSONHandler#getis_ticketing_enabled_statusFromJsonResponse exception : " + exc.getMessage());
            return null;
        }
    }
    public String getLogInStatusFromJsonResponse(String response_string)
    {
        try
        {
            if(response_string == null)
            {
                return null;
            }
            else
            {


                JSONObject json_obj = new JSONObject(response_string.trim());
                if(json_obj == null)
                {
                    return null;
                }
                else
                {
                    String login_status = json_obj.getString("login_status");
                    if(login_status == null)
                    {
                        return null;
                    }
                    else
                    {
                        return login_status.trim();
                    }
                }
            }
        }
        catch(Exception exc)
        {
            System.out.println("JSONHandler#getLoginStatusFromJsonResponse exception : " + exc.getMessage());
            return null;
        }
    }
    public String geAccountTypeFromJsonResponse(String response_string)
    {
        try
        {
            if(response_string == null)
            {
                return null;
            }
            else
            {
                JSONObject json_obj = new JSONObject(response_string.trim());
                if(json_obj == null)
                {
                    return null;
                }
                else
                {
                    String account_type = json_obj.getString("account_type");
                    if(account_type == null)
                    {
                        return null;
                    }
                    else
                    {
                        return account_type.trim();
                    }
                }
            }
        }
        catch(Exception exc)
        {
            System.out.println("JSONHandler#getpersonal#accountTypeFromJsonResponse exception : " + exc.getMessage());
            return null;
        }
    }

    public String getAccountResponseStatusFromJsonResponse(String response_string)
    {
        try
        {
            if(response_string == null)
            {
                return null;
            }
            else
            {

                JSONObject json_obj = new JSONObject(response_string.trim());
                if(json_obj == null)
                {
                    return null;
                }
                else
                {
                    String response_status = json_obj.getString("response_status");
                    if(response_status == null)
                    {
                        return null;
                    }
                    else
                    {
                        return response_status.trim();
                    }
                }
            }
        }
        catch(Exception exc)
        {
            System.out.println("JSONHandler#getAccountResponseStatusFromJsonResponse exception : " + exc.getMessage());
            return null;
        }
    }

    public String getErrorStatusFromJsonResponse(String response_string)
    {
        try
        {
            if(response_string == null)
            {
                return null;
            }
            else
            {
                JSONObject json_obj = new JSONObject(response_string.trim());
                if(json_obj == null)
                {
                    return null;
                }
                else
                {
                    String error_status = json_obj.getString("error");
                    if(error_status == null)
                    {
                        return null;
                    }
                    else
                    {
                        return error_status.trim();
                    }
                }
            }
        }
        catch(Exception exc)
        {
            System.out.println("JSONHandler#getLoginStatusFromJsonResponse exception : " + exc.getMessage());
            return null;
        }
    }

    public String getRegistrationDescriptionFromJsonResponse(String response_string)
    {
        try
        {
            if(response_string == null)
            {
                return null;
            }
            else
            {
                JSONObject json_obj = new JSONObject(response_string.trim());
                if(json_obj == null)
                {
                    return null;
                }
                else
                {
                    String response_status = json_obj.getString("response_description");
                    if(response_status == null)
                    {
                        return null;
                    }
                    else
                    {
                        return response_status.trim();
                    }
                }
            }
        }
        catch(Exception exc)
        {
            System.out.println("JSONHandler#getUtilityPaymentStatusFromJsonResponse exception : " + exc.getMessage());
            return null;
        }
    }


    public String getRegistrationDescriptionFromJsonResponse_reg(String response_string)
    {
        try
        {
            if(response_string == null)
            {
                return null;
            }
            else
            {
                JSONObject json_obj = new JSONObject(response_string.trim());
                if(json_obj == null)
                {
                    return null;
                }
                else
                {
                    String response_status = json_obj.getString("registration_status");
                    if(response_status == null)
                    {
                        return null;
                    }
                    else
                    {
                        return response_status.trim();
                    }
                }
            }
        }
        catch(Exception exc)
        {
            System.out.println("JSONHandler#getUtilityPaymentStatusFromJsonResponse exception : " + exc.getMessage());
            return null;
        }
    }


    public String getRegistrationStatusOnlyFromJsonResponse(String response_string)
    {
        try
        {
            if(response_string == null)
            {
                return null;
            }
            else
            {
                JSONObject json_obj = new JSONObject(response_string.trim());
                if(json_obj == null)
                {
                    return null;
                }
                else
                {
                    String response_status = json_obj.getString("response_status");
                    if(response_status == null)
                    {
                        return null;
                    }
                    else
                    {
                        return response_status.trim();
                    }
                }
            }
        }
        catch(Exception exc)
        {
            System.out.println("JSONHandler#getRegistrationStatusOnlyFromJsonResponse exception : " + exc.getMessage());
            return null;
        }
    }

    public String getPaymentErrorFromJsonResponse(String response_string)
    {
        try
        {
            if(response_string == null)
            {
                return null;
            }
            else
            {
                JSONObject json_obj = new JSONObject(response_string.trim());
                if(json_obj == null)
                {
                    return null;
                }
                else
                {
                    String response_description = json_obj.getString("error");
                    if(response_description == null)
                    {
                        return null;
                    }
                    else
                    {
                        return response_description.trim();
                    }
                }
            }
        }
        catch(Exception exc)
        {
            System.out.println("JSONHandler#getUtilityPaymentDescriptionFromJsonResponse exception : " + exc.getMessage());
            return null;
        }
    }


    public String getPersonalTopUpDescriptionromJsonResponse(String response_string)
    {
        try
        {
            if(response_string == null)
            {
                return null;
            }
            else
            {
                JSONObject json_obj = new JSONObject(response_string.trim());
                if(json_obj == null)
                {
                    return null;
                }
                else
                {
                    String response_description = json_obj.getString("response_description");
                    if(response_description == null)
                    {
                        return null;
                    }
                    else
                    {
                        return response_description.trim();
                    }
                }
            }
        }
        catch(Exception exc)
        {
            System.out.println("JSONHandler#getUtilityPaymentDescriptionFromJsonResponse exception : " + exc.getMessage());
            return null;
        }
    }

    public String getUtilityPaymentDescriptionFromJsonResponse(String response_string)
    {
        try
        {
            if(response_string == null)
            {
                return null;
            }
            else
            {
                JSONObject json_obj = new JSONObject(response_string.trim());
                if(json_obj == null)
                {
                    return null;
                }
                else
                {
                    String response_description = json_obj.getString("response_description");
                    if(response_description == null)
                    {
                        return null;
                    }
                    else
                    {
                        return response_description.trim();
                    }
                }
            }
        }
        catch(Exception exc)
        {
            System.out.println("JSONHandler#getUtilityPaymentDescriptionFromJsonResponse exception : " + exc.getMessage());
            return null;
        }
    }

    public String getNwscPaymentDescriptionFromJsonResponse(String response_string)
    {
        try
        {
            if(response_string == null)
            {
                return null;
            }
            else
            {
                JSONObject json_obj = new JSONObject(response_string.trim());
                if(json_obj == null)
                {
                    return null;
                }
                else
                {
                    String response_description = json_obj.getString("response_description");
                    if(response_description == null)
                    {
                        return null;
                    }
                    else
                    {
                        return response_description.trim();
                    }
                }
            }
        }
        catch(Exception exc)
        {
            System.out.println("JSONHandler#getNwscPaymentDescriptionFromJsonResponse exception : " + exc.getMessage());
            return null;
        }
    }
    public String getUtilityPaymentStatusFromJsonResponse(String response_string)
    {
        try
        {
            if(response_string == null)
            {
                return null;
            }
            else
            {
                JSONObject json_obj = new JSONObject(response_string.trim());
                if(json_obj == null)
                {
                    return null;
                }
                else
                {
                    String response_status = json_obj.getString("response_status");
                    if(response_status == null)
                    {
                        return null;
                    }
                    else
                    {
                        return response_status.trim();
                    }
                }
            }
        }
        catch(Exception exc)
        {
            System.out.println("JSONHandler#getUtilityPaymentStatusFromJsonResponse exception : " + exc.getMessage());
            return null;
        }
    }

    public TicketVerification getTicketVerificationResultsFromJsonString(String result) {
        try {
            if (result == null) {
                return null;
            } else {
                JSONObject json_obj = new JSONObject(result.trim());
                if (json_obj == null) {
                    return null;
                } else {

                    String ticket_id = json_obj.getString("ticket_id");
                    String ticket_status = json_obj.getString("ticket_status");
                    String ticket_name = json_obj.getString("event_name");
                    String category = json_obj.getString("category");
                    String transaction_amount = json_obj.getString("transaction_amount");
                    String expiry_date = json_obj.getString("expirely_date");
                    String reference_id = json_obj.getString("reference_id");

                    String transaction_status = json_obj.getString("transaction_status");

                    if (ticket_id == null || ticket_status == null || ticket_name == null || category == null ||
                            transaction_amount == null || expiry_date == null || reference_id == null
                            || transaction_status == null
                            ) {
                        return null;
                    } else {

                        return  new TicketVerification(ticket_name,ticket_id,category,ticket_status,expiry_date,transaction_amount,reference_id);
                    }
                }
            }
        } catch (Exception exc) {
            System.out.println("JSONHandler#getTicketSellConfirmationResultsFromJsonString exception : " + exc.getMessage());
            return null;
        }
    }


}