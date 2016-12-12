package NavigationHandler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import ticketing.yodime.com.yodimeverifier.MainActivity;
import ticketing.yodime.com.yodimeverifier.TicketVerifcation;

/**
 * Created by timothykasaga on 30/11/2016.
 */
public class NavigationHandler {
    private Context context_obj;
    private String yodime_preferences = "APP_PREFS";
    private SharedPreferences.Editor preferences_editor;
    SharedPreferences sharedPreferences;

    public NavigationHandler()
    {

    }

    public NavigationHandler(Context context)
    {
        this.context_obj = context;
    }

    public void navigateToActivity(Activity current_class,Class<?> cls)
    {
        current_class.finish();
        Intent intent = new Intent(context_obj,cls);
        current_class.startActivity(intent);
    }

    public void navigateToActivity(Context current_context,Class<?> cls)
    {

        Intent intent = new Intent(context_obj,cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        current_context.startActivity(intent);
    }

    public void navigateToActivityWithBundle(Activity current_class,Class<?> cls,Bundle bundle)
    {
        Intent intent = new Intent(context_obj,cls);
        intent.putExtras(bundle);
        current_class.startActivity(intent);
    }

    public void logOut(Activity current_class)
    {
        sharedPreferences = current_class.getSharedPreferences(yodime_preferences, Context.MODE_PRIVATE);
        preferences_editor = sharedPreferences.edit();
        preferences_editor.clear();
        preferences_editor.commit();
        Intent intent = new Intent(context_obj, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        current_class.startActivity(intent);
    }

    public void GoHome(Context context)
    {
      Intent i = new Intent(context, TicketVerifcation.class) ;
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(i);
    }
}