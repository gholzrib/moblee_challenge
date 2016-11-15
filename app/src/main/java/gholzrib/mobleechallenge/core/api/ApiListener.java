package gholzrib.mobleechallenge.core.api;

/**
 * Created by Gunther Ribak on 11/11/2016.
 * For more information contact me
 * through guntherhr@gmail.com
 */

public interface ApiListener {
    /**
     * Call-back used to show some UI to the user while the server handling the
     * request
     */
    public void onPreExecute();
    /**
     * Call-back used when the request ends. It is called even if some error
     * happens.
     *
     * @param isSuccess
     * @param parsedData
     */
    public void onRequestEnds(int operation, boolean isSuccess, String parsedData);
}
