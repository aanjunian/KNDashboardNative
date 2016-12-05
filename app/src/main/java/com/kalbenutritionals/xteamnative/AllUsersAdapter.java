package com.kalbenutritionals.xteamnative;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import bl.UserRewardData;

public class AllUsersAdapter extends RecyclerView.Adapter<AllUsersAdapter.UserViewHolder>{

    private List<UserRewardData> userList;
    private Context context;
    public AllUsersAdapter(List<UserRewardData> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_reward_spg, null);
        UserViewHolder userViewHolder = new UserViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        UserRewardData user = userList.get(position);

        Picasso.with(context)
                .load(user.getImageResourceId())
                .resize(70, 70)
                .centerCrop()
                .into(holder.ivProfilePic);
        holder.tvProfileName.setText(user.getProfileName());
        holder.tvJabatan.setText(user.getJabatan());
        holder.tvRegion.setText(user.getRegion());
        holder.tvCabang.setText(user.getCabang());
        holder.tvPoints.setText(user.getPoints());
        holder.tvRanking.setText(user.getRanking());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProfilePic;
        TextView tvProfileName, tvJabatan, tvRegion, tvCabang, tvPoints, tvRanking;
        public UserViewHolder(View itemView) {
            super(itemView);
            ivProfilePic = (ImageView) itemView.findViewById(R.id.ivProfilePic);
            tvProfileName = (TextView) itemView.findViewById(R.id.tvProfileName);
            tvJabatan = (TextView) itemView.findViewById(R.id.tvJabatan);
            tvRegion = (TextView) itemView.findViewById(R.id.tvRegion);
            tvCabang = (TextView) itemView.findViewById(R.id.tvCabang);
            tvPoints = (TextView) itemView.findViewById(R.id.tvPoints);
            tvRanking = (TextView) itemView.findViewById(R.id.tvRanking);
        }
    }
}