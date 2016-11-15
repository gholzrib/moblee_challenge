package gholzrib.mobleechallenge.core.api;

import android.content.Context;
import android.os.AsyncTask;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;

import gholzrib.mobleechallenge.MobLeeApplication;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Gunther Ribak on 11/11/2016.
 * For more information contact me
 * through guntherhr@gmail.com
 */

public abstract class ApiCore {

    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType MEDIA_TYPE_JPG = MediaType.parse("image/jpg");

    protected WeakReference<Context> mActivity;
    protected WeakReference<ApiListener> mApiListener = null;
    protected WeakReference<Call> mCurrentCall;

    protected int mCurrentOperation;

    public ApiCore(Context context, ApiListener apiListener, int operation) {
        mActivity = new WeakReference<>(context);
        mApiListener = new WeakReference<>(apiListener);
        mCurrentOperation = operation;

        if (mApiListener.get() == null) {
            throw new IllegalArgumentException("ApiListener is required. You mustn't use apiListener parameter as null");
        }
    }

    public abstract void doRequest(String params, File picture);

    protected void doRequestByUsingGet(final String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        mCurrentCall = new WeakReference<>(MobLeeApplication.getClientInstance().newCall(request));
        ApiTask apiTask = new ApiTask();
        apiTask.execute(mCurrentCall.get());
    }

    protected void doRequestByUsingPost(final String url, final String json) {
        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        mCurrentCall = new WeakReference<>(MobLeeApplication.getClientInstance().newCall(request));
        ApiTask apiTask = new ApiTask();
        apiTask.execute(mCurrentCall.get());
    }

    protected void doRequestByUsingPost(final String url, final String json, final File picture) {

    }

    public void cancelRequest() {
        if (mCurrentCall.get() != null && !mCurrentCall.get().isExecuted()) {
            mCurrentCall.get().cancel();
        }
    }

    protected class ApiTask extends AsyncTask<Call, Integer, RequestResponse> {

        @Override
        protected void onPreExecute() {
            mApiListener.get().onPreExecute();
        }

        @Override
        protected RequestResponse doInBackground(Call... params) {
            Call mCall = params[0];
            if (mCall != null) {
                try {
                    Response response =  mCall.execute();
                    RequestResponse requestResponse = new RequestResponse();
                    requestResponse.setSuccessful(response.isSuccessful());
                    requestResponse.setBody(response.body().string());
                    return  requestResponse;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(RequestResponse requestResponse) {
            if (requestResponse != null) {
                mApiListener.get().onRequestEnds(mCurrentOperation,
                        requestResponse.isSuccessful(), requestResponse.getBody());
            }
        }
    }

    /**
     * Model created to use the request response into the main thread
     */
    private class RequestResponse {

        boolean isSuccessful;
        String body;

        public boolean isSuccessful() {
            return isSuccessful;
        }

        public void setSuccessful(boolean successful) {
            isSuccessful = successful;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }
}
