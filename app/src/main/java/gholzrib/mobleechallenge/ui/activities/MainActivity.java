package gholzrib.mobleechallenge.ui.activities;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import gholzrib.mobleechallenge.R;
import gholzrib.mobleechallenge.ui.adapters.TagsAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindRecyclerView();
    }

    /**
     * Finds the RecyclerView created on the layout xml and set it`s proprieties
     */
    private void bindRecyclerView() {
        RecyclerView mRvTags = (RecyclerView) findViewById(R.id.act_main_rv);
        mRvTags.setLayoutManager(new LinearLayoutManager(this));
        mRvTags.setAdapter(new TagsAdapter(this, getResources().getStringArray(R.array.tags)));
        mRvTags.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(0, 8, 0, 8);
            }
        });
    }

}
