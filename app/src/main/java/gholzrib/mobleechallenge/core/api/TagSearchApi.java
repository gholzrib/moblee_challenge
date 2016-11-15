package gholzrib.mobleechallenge.core.api;

import android.content.Context;
import android.provider.SyncStateContract;
import android.util.Log;

import java.io.File;

import gholzrib.mobleechallenge.core.utils.Constants;

/**
 * Created by Gunther Ribak on 11/11/2016.
 * For more information contact me
 * through guntherhr@gmail.com
 */

public class TagSearchApi extends ApiCore {

    public TagSearchApi(Context context, ApiListener apiListener, int operation) {
        super(context, apiListener, operation);
    }

    @Override
    public void doRequest(String params, File picture) {
        String url = Constants.BASE_URL.replace(Constants.PLACE_HOLDER_TAGS, params);
        Log.i(TagSearchApi.class.getSimpleName(), url);
        doRequestByUsingGet(url);
    }

}
