package com.kalbenutritionals.xteamnative;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import library.common.tUserCheckinData;

public class AllHistoryAdapter extends RecyclerView.Adapter<AllHistoryAdapter.UserViewHolder>{

    private List<tUserCheckinData> userList;
    private Context context;
    private  Activity activity;
    public AllHistoryAdapter(List<tUserCheckinData> userList, Context context, Activity activity) {
        this.userList = userList;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_history, null);
        UserViewHolder userViewHolder = new UserViewHolder(view);
        return userViewHolder;
    }

//    @Override
//    public void onBindViewHolder(AllUsersAdapter.UserViewHolder holder, int position) {
//
//    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        final tUserCheckinData user = userList.get(position);
        String status = user.get_intSubmit().equals("1") && user.get_intSync().equals("1") ? "Sync" : "Submit";

//        Picasso.with(context)
//                .load(user.getImageResourceId())
//                .resize(70, 70)
//                .centerCrop()
//                .into(holder.ivProfilePic);
        holder.tvProfileName.setText(user.get_txtOutletName());
        holder.tvJabatan.setText(user.get_TxtAlamat());
        holder.tvRegion.setText(user.get_TxtNamaPropinsi());
        holder.tvCabang.setText(user.get_TxtNamaKabKota());
        holder.tvPoints.setText(user.getDtCheckin());
        holder.tvRanking.setText(status);
        holder.rl_holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                final View promptView = layoutInflater.inflate(R.layout.popup_map, null);

                GoogleMap mMap = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    mMap = ((MapFragment) (activity).getFragmentManager().findFragmentById(R.id.map)).getMap();

                    if (mMap == null) {
                        mMap = ((MapFragment) (activity).getFragmentManager().findFragmentById(R.id.map)).getMap();
                    }

                    double latitude = Double.parseDouble(user.get_txtLat());
                    double longitude = Double.parseDouble(user.get_txtLong());

                    MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Your Location");

                    marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 16));

                    final LatLngBounds.Builder builder = new LatLngBounds.Builder();
                    builder.include(marker.getPosition());
                    LatLngBounds bounds = builder.build();

                    mMap.clear();
                    mMap.addMarker(marker);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setView(promptView);
                    alertDialogBuilder
                            .setMessage(user.get_txtOutletName())
                            .setCancelable(false)
                            .setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            MapFragment f = null;
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                                                f = (MapFragment) (activity).getFragmentManager().findFragmentById(R.id.map);
                                            }
                                            if (f != null) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                                                    (activity).getFragmentManager().beginTransaction().remove(f).commit();
                                                }
                                            }

                                            dialog.dismiss();
                                        }
                                    });
                    final AlertDialog alertD = alertDialogBuilder.create();
                    alertD.show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProfilePic;
        TextView tvProfileName, tvJabatan, tvRegion, tvCabang, tvPoints, tvRanking;
        RelativeLayout rl_holder;
        public UserViewHolder(View itemView) {
            super(itemView);
//            ivProfilePic = (ImageView) itemView.findViewById(R.id.ivProfilePic);
            tvProfileName = (TextView) itemView.findViewById(R.id.tvProfileName);
            tvJabatan = (TextView) itemView.findViewById(R.id.tvJabatan);
            tvRegion = (TextView) itemView.findViewById(R.id.tvRegion);
            tvCabang = (TextView) itemView.findViewById(R.id.tvCabang);
            tvPoints = (TextView) itemView.findViewById(R.id.tvPoints);
            rl_holder = (RelativeLayout) itemView.findViewById(R.id.rl_history);
            tvRanking = (TextView) itemView.findViewById(R.id.tvStatus);
        }
    }
}