package com.kalbenutritionals.xteamnative;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import bl.clsMainBL;
import bl.mBannerBL;
import bl.tUserCheckinBL;
import bl.tUserLoginBL;
import library.common.mBannerData;
import library.common.tUserCheckinData;
import library.common.tUserLoginData;
import library.dal.clsHardCode;

import static com.kalbenutritionals.xteamnative.R.id.slider;

/**
 * Created by ASUS ZE on 21/10/2016.
 */

public class FragmentHistoryCheckin extends Fragment implements ViewPagerEx.OnPageChangeListener, BaseSliderView.OnSliderClickListener{

    View v;
    private SliderLayout mDemoSlider;
    Spinner spnMonth, spnYears;
    Button btnView;
    String selectedMonth, selectedYears;

    RecyclerView recyclerView;
    AllHistoryAdapter allUsersAdapter;

    List <String> arrMonths, arrYears;
    List<UserDataCheckin> dtListUserDataCheckin;
    private tUserLoginData userActive;
    List<mBannerData> dtListdataBanner;
    List<tUserCheckinData> _tUserCheckinData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_history_checkin, container, false);

        NavigationView nv = (NavigationView) getActivity().findViewById(R.id.navigation_view);
        nv.setCheckedItem(R.id.historyCheckin);

        mDemoSlider = (SliderLayout) v.findViewById(slider);
        spnMonth = (Spinner) v.findViewById(R.id.spnMonth);
        spnYears = (Spinner) v.findViewById(R.id.spnYears);
        btnView = (Button) v.findViewById(R.id.btnView);

        recyclerView = (RecyclerView) v.findViewById(R.id.rvAllHistoryUsers);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setNestedScrollingEnabled(false);

        userActive = new tUserLoginBL().getUserActive();

        arrMonths = new ArrayList<>();
        arrMonths = new clsMainActivity().getArrayMonth();

        arrYears = new ArrayList<>();
        arrYears = new clsMainActivity().getArrayYears();

        spnMonth.setAdapter(new MyAdapterMonth(getContext(), R.layout.custom_spinner, arrMonths));

        spnMonth.setSelection(new clsMainActivity().getSelectedMoth(arrMonths));

        spnYears.setAdapter(new MyAdapterYear(getContext(), R.layout.custom_spinner, arrYears));

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setAdapter(null);
                AsyncGetDataCheckInWithPeriode task = new AsyncGetDataCheckInWithPeriode();
                task.execute();
            }
        });

        spnMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                i=i+1;
                if (String.valueOf(i).length()<2){
                    selectedMonth = "0"+String.valueOf(i);
                } else {
                    selectedMonth = String.valueOf(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spnYears.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedYears = spnYears.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        _tUserCheckinData = new ArrayList<>();
        _tUserCheckinData = new tUserCheckinBL().GetAllData();
        dtListUserDataCheckin = new ArrayList<>();

        loadDataHistory(_tUserCheckinData);

        initBanner(getContext());

        return v;
    }

    private void initBanner(Context context) {
        mDemoSlider = (SliderLayout) v.findViewById(R.id.slider);

        final HashMap<String,String> url_maps = new HashMap<String, String>();

        dtListdataBanner = new mBannerBL().GetAllData();

        for( mBannerData dt : dtListdataBanner){
            url_maps.put(dt.get_txtDesc(), dt.get_TxtPathActual());
        }

        for(final String name : url_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getContext());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {
                            Bitmap mBitmap = new clsMainActivity().getBitmapFromURL(String.valueOf(url_maps.get(name)));

                            if(mBitmap!=null){
                                new clsMainActivity().zoomImage(mBitmap, getContext());
                            } else {
                                new clsMainActivity().showCustomToast(getContext(), "Image not found : " + name, false);
                            }
                        }
                    });

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
    }

    private void loadDataHistory(List<tUserCheckinData> _tUserCheckinData) {
//        _tUserCheckinData = new ArrayList<>();
//        _tUserCheckinData = new tUserCheckinBL().GetAllData();
//        dtListUserDataCheckin = new ArrayList<>();

        if(_tUserCheckinData.size()>0){
            allUsersAdapter = new AllHistoryAdapter(_tUserCheckinData, getContext(), getActivity());
            recyclerView.setAdapter(allUsersAdapter);

        } else {
            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
        }
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    public class MyAdapterMonth extends ArrayAdapter<String> {
        public MyAdapterMonth(Context context, int textViewResourceId, List<String> objects) {
            super(context, textViewResourceId, objects);
            // TODO Auto-generated constructor stub
        }


        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View row = inflater.inflate(R.layout.custom_spinner, parent, false);
            TextView label = (TextView) row.findViewById(R.id.tvTitle);
            label.setText(arrMonths.get(position));
            TextView sub = (TextView) row.findViewById(R.id.tvDesc);
            sub.setVisibility(View.INVISIBLE);
            sub.setVisibility(View.GONE);
            //sub.setText(mydata2[position]);
            //label.setTextColor(new Color().parseColor("#FFFFF"));
            row.setBackgroundColor(new Color().TRANSPARENT);
            return row;
        }

    }
    public class MyAdapterYear extends ArrayAdapter<String> {
        public MyAdapterYear(Context context, int textViewResourceId, List<String> objects) {
            super(context, textViewResourceId, objects);
            // TODO Auto-generated constructor stub
        }


        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View row = inflater.inflate(R.layout.custom_spinner, parent, false);
            TextView label = (TextView) row.findViewById(R.id.tvTitle);
            label.setText(arrYears.get(position));
            TextView sub = (TextView) row.findViewById(R.id.tvDesc);
            sub.setVisibility(View.INVISIBLE);
            sub.setVisibility(View.GONE);
            //sub.setText(mydata2[position]);
            //label.setTextColor(new Color().parseColor("#FFFFF"));
            row.setBackgroundColor(new Color().TRANSPARENT);
            return row;
        }

    }

    private class AsyncGetDataCheckInWithPeriode extends AsyncTask<JSONArray, Void, JSONArray>{

        @Override
        protected JSONArray doInBackground(JSONArray... jsonArrays) {
            //            android.os.Debug.waitForDebugger();
            JSONArray Json = null;
            try {
                Json = new clsMainBL().getDataCheckInWithPeriode(userActive.get_txtUserId(), selectedYears+selectedMonth);
//                _tUserCheckinData.get_txtRegionName().toString();
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return Json;
        }

        private ProgressDialog Dialog = new ProgressDialog(getContext());

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCancelRequest, false);
        }

        @Override
        protected void onPostExecute(JSONArray array) {
            if(array!=null){
                if (array.size() > 0) {
                    Iterator i = array.iterator();
                    dtListUserDataCheckin = new ArrayList<>();
                    _tUserCheckinData = new ArrayList<>();

                    while (i.hasNext()) {
//                        _tUserCheckinData = new ArrayList<>();
                        org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
                        Long IntResult = (Long) innerObj.get("_pboolValid");
                        String _txtSumberDataID = (String) innerObj.get("TxtSumberDataId").toString();
                        if (IntResult == 1) {
                            JSONObject JsonArray_header = (JSONObject) innerObj.get("VwGetDatadSumberDataforWebDashboard");
                            if (JsonArray_header != null) {
                                tUserCheckinData data = new tUserCheckinData();
                                data.set_txtOutletName(String.valueOf(JsonArray_header.get("TxtNamaInstitusi")));
                                data.set_TxtAlamat(String.valueOf(JsonArray_header.get("TxtAlamat")));
                                data.set_TxtNamaPropinsi(String.valueOf(JsonArray_header.get("TxtNamaPropinsi")));
                                data.set_TxtNamaKabKota(String.valueOf(JsonArray_header.get("TxtNamaKabKota")));
                                data.setDtCheckin(String.valueOf(innerObj.get("DtInsert")));
                                data.set_txtLong(String.valueOf(innerObj.get("TxtLongitude")));
                                data.set_txtLat(String.valueOf(innerObj.get("TxtLatitude")));
                                data.set_intSubmit("1");
                                data.set_intSync("1");

                                _tUserCheckinData.add(data);

                            } else {
                                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                            }
                        }
                    }

//                    allUsersAdapter = new AllHistoryAdapter(_tUserCheckinData, getContext(), getActivity());
//                    recyclerView.setAdapter(allUsersAdapter);
                    loadDataHistory(_tUserCheckinData);
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }
            } else {
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
            }
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            Dialog.setMessage("Getting Data History Checkin");
            Dialog.setCancelable(false);
            Dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            Dialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Dialog.dismiss();
        }
    }

    public class UserDataCheckin {
        private String TxtSumberDataId;
        private String txtNamaInstitusi;
        private String txtAlamat;
        private String txtNamaPropinsi;
        private String txtNamaKabKota;
        private String DtInsert;
        private String ranking;

        public String getImageResourceId() {
            return TxtSumberDataId;
        }
        public String getProfileName() {
            return txtNamaInstitusi;
        }
        public String getJabatan(){
            return txtAlamat;
        }
        public String getRegion(){
            return txtNamaPropinsi;
        }

        public String getCabang(){
            return txtNamaKabKota;
        }
        public String getPoints(){
            return DtInsert;
        }

//        public String getRanking(){
//            return ranking;
//        }

        public UserDataCheckin(String TxtSumberDataId, String txtNamaInstitusi, String txtAlamat, String txtNamaPropinsi, String txtNamaKabKota, String DtInsert) {
            this.TxtSumberDataId = TxtSumberDataId;
            this.txtNamaInstitusi = txtNamaInstitusi;
            this.txtAlamat = txtAlamat;
            this.txtNamaPropinsi = txtNamaPropinsi;
            this.txtNamaKabKota = txtNamaKabKota;
            this.DtInsert = DtInsert;
        }
    }
}
