package gholzrib.mobleechallenge;

import android.app.Application;

import okhttp3.OkHttpClient;

/**
 * Created by Gunther Ribak on 11/11/2016.
 * For more information contact me
 * through guntherhr@gmail.com
 */

public class MobLeeApplication extends Application {

    private static OkHttpClient mClient;

    public static OkHttpClient getClientInstance() {
        if (mClient == null) {
            mClient = new OkHttpClient();
        }
        return mClient;
    }

}
