package gholzrib.mobleechallenge.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import gholzrib.mobleechallenge.R;
import gholzrib.mobleechallenge.core.utils.Constants;
import gholzrib.mobleechallenge.ui.activities.TagResults;

/**
 * Created by Gunther Ribak on 10/11/2016.
 * For more information contact me
 * through guntherhr@gmail.com
 */

public class TagsAdapter extends RecyclerView.Adapter<TagsAdapter.TagHolder>  {

    Context mContext;
    String[] mArrayTags;

    public TagsAdapter(Context mContext, String[] mArrayTags) {
        this.mContext = mContext;
        this.mArrayTags = mArrayTags;
    }

    @Override
    public TagHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.adapter_tag, parent, false);
        return new TagHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TagHolder holder, int position) {
        String tagSelected = mArrayTags[position];
        tagSelected = tagSelected.toLowerCase();
        tagSelected = tagSelected.replace(" ", "-");

        final Intent intent = new Intent(mContext, TagResults.class);
        intent.putExtra(Constants.EXTRA_REQUEST_PARAMS, tagSelected);

        holder.mLnrContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(intent);
            }
        });
        holder.mTxtTag.setText(mArrayTags[position]);
    }

    @Override
    public int getItemCount() {
        return mArrayTags.length;
    }

    public class TagHolder extends RecyclerView.ViewHolder {

        LinearLayout mLnrContainer;
        TextView mTxtTag;

        public TagHolder(View itemView) {
            super(itemView);
            mLnrContainer = (LinearLayout) itemView.findViewById(R.id.adp_container);
            mTxtTag = (TextView) itemView.findViewById(R.id.adp_txt_tag);
        }
    }

}
