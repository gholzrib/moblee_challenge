package gholzrib.mobleechallenge.core.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

import gholzrib.mobleechallenge.R;

public class CheckConnection {

    public static final String CONNECTION_MESSAGE_KEY = "connection_message_key";

    public static boolean hasInternetConnection(Context context, boolean shouldShowToast) {

        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm.getActiveNetworkInfo() != null
                    && cm.getActiveNetworkInfo().isAvailable()
                    && cm.getActiveNetworkInfo().isConnected()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (shouldShowToast) {
            Toast.makeText(context,
                    context.getResources().getString(R.string.error_message_no_internet_connection),
                    Toast.LENGTH_SHORT).show();
        }

        return false;
    }
}
