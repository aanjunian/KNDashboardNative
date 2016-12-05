package com.kalbenutritionals.xteamnative;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import library.common.tTypeOutletData;

import static com.kalbenutritionals.xteamnative.R.id.tv_alamat;
import static com.kalbenutritionals.xteamnative.R.id.tv_propinsi;
import static com.kalbenutritionals.xteamnative.R.id.tv_type;
import static com.kalbenutritionals.xteamnative.R.id.tv_typeoutlet;

/**
 * Created by ASUS ZE on 27/09/2016.
 */

public class AllOutletAdapter extends RecyclerView.Adapter<AllOutletAdapter.UserViewHolderOutlet> {


    private List<tTypeOutletData> userList;
    private Context context;
    public AllOutletAdapter(List<tTypeOutletData> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

//    public interface OnItemSelectedListener {
//        void onItemSelected(AllOutletAdapter var1, View var2, int var3, long var4);
//
//        void onNothingSelected(AllOutletAdapter var1);
//    }
//
//    public void setOnItemSelectedListener(AllOutletAdapter.OnItemSelectedListener listener) {
//        throw new RuntimeException("Stub!");
//    }
//
//    public final AllOutletAdapter.OnItemSelectedListener getOnItemSelectedListener() {
//        throw new RuntimeException("Stub!");
//    }

    @Override
    public UserViewHolderOutlet onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_data_search, null);
        UserViewHolderOutlet userViewHolderOutlet = new UserViewHolderOutlet(view);
        return userViewHolderOutlet;
    }

    @Override
    public void onBindViewHolder(final AllOutletAdapter.UserViewHolderOutlet holder, final int position) {
        final tTypeOutletData user = userList.get(position);
        String a = user.get_txtNamaPropinsi();

        holder.tvPropinsi.setText(a);
        holder.tvOulet.setText(user.get_txtSumberDataID()+ "-" + user.get_txtNamaInstitusi());
        holder.tvAlamat.setText(user.get_txtAlamat() + "\n" + "No Telp : " + user.get_txtTelepon());
        holder.tvType.setText(user.get_tipeSumberData());

        holder.rl_holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                new clsMainActivity().showCustomToast(view.getContext(), String.valueOf(userList.get(position).get_txtAlamat()), false);
                Boolean code = false;
                Context context = view.getContext();
                List<tTypeOutletData> dt = new ArrayList<tTypeOutletData>();
                List<tTypeOutletData> userdata = new ArrayList<>();
                String namaPropinsi = userList.get(position).get_txtNamaPropinsi();
                String sumberDataID = userList.get(position).get_txtSumberDataID();
                String namaInstitusi = userList.get(position).get_txtNamaInstitusi();
                String alamat = userList.get(position).get_txtAlamat();
                String telepon = userList.get(position).get_txtTelepon();
                String tipeSumberData = userList.get(position).get_tipeSumberData();
                String txtKabKota = userList.get(position).get_txtNamaKabKota();
                userdata.add(new tTypeOutletData(sumberDataID, tipeSumberData, namaInstitusi, namaPropinsi, alamat, telepon, txtKabKota));
//                dt.add((tTypeOutletData) userdata);

                new FragmentCheckinGeolocation().showPopUpSearch(code, context, userdata);

            }
        });


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolderOutlet extends RecyclerView.ViewHolder{

        TextView tvPropinsi, tvOulet, tvAlamat, tvType;
        RelativeLayout rl_holder;
        public UserViewHolderOutlet(final View itemView) {
            super(itemView);

            tvPropinsi = (TextView) itemView.findViewById(tv_propinsi);
            tvOulet = (TextView) itemView.findViewById(tv_type);
            tvAlamat = (TextView) itemView.findViewById(tv_alamat);
            tvType = (TextView) itemView.findViewById(tv_typeoutlet);
            rl_holder = (RelativeLayout) itemView.findViewById(R.id.holder_id);

        }
    }
}
