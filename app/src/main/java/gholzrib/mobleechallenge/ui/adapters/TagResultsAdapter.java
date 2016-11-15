package gholzrib.mobleechallenge.ui.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import gholzrib.mobleechallenge.R;
import gholzrib.mobleechallenge.core.models.Question;
import gholzrib.mobleechallenge.core.utils.CheckConnection;

/**
 * Created by Gunther Ribak on 13/11/2016.
 * For more information contact me
 * through guntherhr@gmail.com
 */

public class TagResultsAdapter extends RecyclerView.Adapter<TagResultsAdapter.TagResultHolder> {

    Context context;
    ArrayList<Question> questions;

    public TagResultsAdapter(Context context, ArrayList<Question> questions) {
        this.context = context;
        this.questions = questions;
    }

    @Override
    public TagResultHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.adapter_tag_results, parent, false);
        return new TagResultHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final TagResultHolder holder, int position) {
        holder.txtTitle.setText(questions.get(position).getTitle());
        if (CheckConnection.hasInternetConnection(context, false)) {
            Picasso.with(context)
                    .load(questions.get(position).getOwner().getProfile_image())
                    .placeholder(R.drawable.placeholder_generic)
                    .into(holder.imgProfile, new Callback() {
                        @Override
                        public void onSuccess() {
                            holder.prgLoading.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {
                            holder.prgLoading.setVisibility(View.GONE);
                        }
                    });
        } else {
            holder.imgProfile.setImageResource(R.drawable.placeholder_generic);
            holder.prgLoading.setVisibility(View.GONE);
        }
        holder.txtName.setText(questions.get(position).getOwner().getDisplay_name());
        holder.txtScore.setText(questions.get(position).getScore().toString());
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    /**
     * Update the RecyclerView data
     * @param questions New items array
     */
    public void update (ArrayList<Question> questions) {
        this.questions.clear();
        this.questions.addAll(questions);
        notifyDataSetChanged();
    }

    public class TagResultHolder extends RecyclerView.ViewHolder {

        TextView txtTitle;
        ProgressBar prgLoading;
        ImageView imgProfile;
        TextView txtName;
        TextView txtScore;

        public TagResultHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.adp_tag_result_txt_title);
            prgLoading = (ProgressBar) itemView.findViewById(R.id.adp_tag_result_prg_loading);
            imgProfile = (ImageView) itemView.findViewById(R.id.adp_tag_result_img_profile);
            txtName = (TextView) itemView.findViewById(R.id.adp_tag_result_txt_name);
            txtScore = (TextView) itemView.findViewById(R.id.adp_tag_result_txt_score);
        }
    }

}
