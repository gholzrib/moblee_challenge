package gholzrib.mobleechallenge.ui.activities;

import android.app.ProgressDialog;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import gholzrib.mobleechallenge.R;
import gholzrib.mobleechallenge.core.api.ApiListener;
import gholzrib.mobleechallenge.core.api.TagSearchApi;
import gholzrib.mobleechallenge.core.models.Question;
import gholzrib.mobleechallenge.core.utils.CheckConnection;
import gholzrib.mobleechallenge.core.utils.Constants;
import gholzrib.mobleechallenge.ui.adapters.TagResultsAdapter;

public class TagResults extends AppCompatActivity implements ApiListener {

    ProgressDialog mApiProgressDialog;

    TagResultsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_results);

        mApiProgressDialog = new ProgressDialog(this);

        bindRecyclerView();

        if (CheckConnection.hasInternetConnection(this, true)) {
            Bundle extras = getIntent().getExtras();
            if (extras != null && extras.containsKey(Constants.EXTRA_REQUEST_PARAMS)) {
                TagSearchApi api = new TagSearchApi(this, this, 0);
                api.doRequest(extras.getString(Constants.EXTRA_REQUEST_PARAMS), null);
            } else {
                Toast.makeText(this, R.string.error_request_unsuccessful, Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    /**
     * Method used to bind the RecyclerView and set it's adapter
     */
    private void bindRecyclerView() {
        RecyclerView mRvTagResults = (RecyclerView) findViewById(R.id.act_tag_results_rv);
        mRvTagResults.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new TagResultsAdapter(this, new ArrayList<Question>());
        mRvTagResults.setAdapter(mAdapter);
        final int recyclerDivider = getResources().getDimensionPixelSize(R.dimen.recycler_view_divider);
        mRvTagResults.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(recyclerDivider, recyclerDivider, recyclerDivider, recyclerDivider);
            }
        });
    }

    @Override
    public void onPreExecute() {
        if (mApiProgressDialog != null && !mApiProgressDialog.isShowing()) {
            mApiProgressDialog.setMessage(getString(R.string.loading_tag_results));
            mApiProgressDialog.setCancelable(false);
            mApiProgressDialog.show();
        }
    }

    @Override
    public void onRequestEnds(int operation, boolean isSuccess, String parsedData) {
        if (isSuccess) {
            try {
                JSONObject jsonData = new JSONObject(parsedData);
                JSONArray jsonItems = jsonData.getJSONArray("items");
                Gson gson = new Gson();
                ArrayList<Question> questions = new ArrayList<>();
                for (int i = 0; i < jsonItems.length(); i++) {
                    Question question = gson.fromJson(jsonItems.get(i).toString(), Question.class);
                    questions.add(question);
                }
                mAdapter.update(questions);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, R.string.error_request_unsuccessful, Toast.LENGTH_SHORT).show();
        }
        if (mApiProgressDialog != null && mApiProgressDialog.isShowing()) {
            mApiProgressDialog.dismiss();
        }
    }
}
