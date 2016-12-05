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

import bl.dataBanner;

/**
 * Created by ASUS ZE on 28/10/2016.
 */

public class AllBannerAdapter extends RecyclerView.Adapter<AllBannerAdapter.UserViewHolder>{
    private List<dataBanner> userList;
    private Context context;
    public AllBannerAdapter(List<dataBanner> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @Override
    public AllBannerAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_banner, null);
        UserViewHolder userViewHolder = new UserViewHolder(view);
        return userViewHolder;
    }


//    @Override
//    public void onBindViewHolder(AllUsersAdapter.UserViewHolder holder, int position) {
//
//    }

    @Override
    public void onBindViewHolder(AllBannerAdapter.UserViewHolder holder, int position) {
        dataBanner user = userList.get(position);

        Picasso.with(context)
                .load(user.get_TxtPathActual())
                .resize(70, 70)
                .centerCrop()
                .into(holder.ivProfilePic);
//        holder.tvProfileName.setText(user.getProfileName());
//        holder.tvJabatan.setText(user.getJabatan());
//        holder.tvRegion.setText(user.getRegion());
//        holder.tvCabang.setText(user.getCabang());
//        holder.tvPoints.setText(user.getPoints());
//        holder.tvRanking.setText(user.getRanking());
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
            ivProfilePic = (ImageView) itemView.findViewById(R.id.iv_banner);
//            tvProfileName = (TextView) itemView.findViewById(R.id.tvProfileName);
//            tvJabatan = (TextView) itemView.findViewById(R.id.tvJabatan);
//            tvRegion = (TextView) itemView.findViewById(R.id.tvRegion);
//            tvCabang = (TextView) itemView.findViewById(R.id.tvCabang);
//            tvPoints = (TextView) itemView.findViewById(R.id.tvPoints);
//            tvRanking = (TextView) itemView.findViewById(R.id.tvRanking);
        }
    }
}
