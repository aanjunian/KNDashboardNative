package com.kalbenutritionals.xteamnative;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
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

import bl.UserRewardData;
import bl.clsMainBL;
import bl.mBannerBL;
import bl.mBranchBL;
import bl.tUserLoginBL;
import library.common.mBannerData;
import library.common.mBranchData;
import library.common.tUserLoginData;
import library.dal.clsHardCode;

public class FragmentReward extends Fragment implements ViewPagerEx.OnPageChangeListener, BaseSliderView.OnSliderClickListener {

    View v;
    private SliderLayout mDemoSlider;
    DatePicker datePicker;
    Spinner spnRegion, spnBranch;
    Button btnView;

    List<String> arrRegion, arrBranch;
    String selectedRegion, selectedBranch, selectedMonth, selectedYears;;

    RecyclerView recyclerView;
    AllUsersAdapter allUsersAdapter;
    List<UserRewardData> dtListUser;
    List<mBannerData> dtListdataBanner;

    Spinner spnMonth, spnYears;
    List <String> arrMonths, arrYears;
    ScrollView scrollingView;

    List<mBranchData> _mBranchData;
    tUserLoginData _tUserLoginData;

    int IndexSpinnerRegion, IndexSpinnerBranch = 0;

    FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.coordinator_layout_reward, container, false);

        mDemoSlider = (SliderLayout) v.findViewById(R.id.slider);
        spnRegion = (Spinner) v.findViewById(R.id.spnRegion);
        spnBranch = (Spinner) v.findViewById(R.id.spnBranch);
        btnView = (Button) v.findViewById(R.id.btnView);
        spnMonth = (Spinner) v.findViewById(R.id.spnMonth_reward);
        spnYears = (Spinner) v.findViewById(R.id.spnYears_reward);
        scrollingView = (ScrollView) v.findViewById(R.id.sv_reward);
        fab = (FloatingActionButton) v.findViewById(R.id.fab);

        recyclerView = (RecyclerView) v.findViewById(R.id.rvAllUsers);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setNestedScrollingEnabled(false);

        final ScrollView scrollView = (ScrollView) v.findViewById(R.id.sv_reward);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View vi, MotionEvent event) {
                RelativeLayout lnLayout = (RelativeLayout) v.findViewById(R.id.rl_reward);

                int coords[]={0,0};
                lnLayout.getLocationInWindow(coords);

                int absoluteTop = coords[1];

                if(absoluteTop < 90){
                    fab.setVisibility(View.INVISIBLE);
                    fab.postDelayed(new Runnable() {
                        public void run() {
                            fab.setVisibility(View.VISIBLE);
                        }
                    }, 7000);
                }
                else {
                            fab.setVisibility(View.INVISIBLE);
                }

                return false;
            }
        });

//        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//            }
//
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//        });

//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
//        {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
//            {
//                if (dy > 0 ||dy<0 && fab.isShown())
//                {
//                    fab.hide();
//                }
//            }
//
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
//            {
//                if (newState == RecyclerView.SCROLL_STATE_IDLE)
//                {
//                    fab.show();
//                }
//
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//        });
//
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.fullScroll(ScrollView.FOCUS_UP);
                fab.setVisibility(View.INVISIBLE);
            }
        });

//        scrollingView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                view.getParent().requestDisallowInterceptTouchEvent(true);
//                return false;
//            }
//        });

        dtListUser = new ArrayList<UserRewardData>();

        arrMonths = new ArrayList<>();
        arrMonths = new clsMainActivity().getArrayMonth();

        arrYears = new ArrayList<>();
        arrYears = new clsMainActivity().getArrayYears();

        _mBranchData = new ArrayList<>();
        _mBranchData = new mBranchBL().GetAllData();

        _tUserLoginData = new tUserLoginBL().getUserActive();
        selectedRegion = _tUserLoginData.get_txtRegionMenu();
        selectedBranch = _tUserLoginData.get_txtCabangMenu();

        arrRegion = new ArrayList<>();
        arrRegion = new clsMainActivity().getArrayRegion(selectedRegion);

        spnMonth.setAdapter(new MyAdapterPeriode(getContext(), R.layout.custom_spinner, arrMonths));

        spnYears.setAdapter(new MyAdapterPeriode(getContext(), R.layout.custom_spinner, arrYears));

        spnRegion.setAdapter(new MyAdapterPeriodes(getContext(), R.layout.custom_spinner, arrRegion));

        if(!selectedRegion.equals("-")){
            int i=0;

            if (selectedRegion!="-"){
                for(String dt : arrRegion){
                    if(dt.equalsIgnoreCase(selectedRegion)){
                        IndexSpinnerRegion= i;
                    }
                    i++;
                }
            }
            spnRegion.setSelection(IndexSpinnerRegion);
        }




        arrBranch = new ArrayList<>();
        arrBranch = new clsMainActivity().getArrayBranchByRegion(selectedRegion);

        spnBranch.setAdapter(new MyAdapterPeriode(getContext(), R.layout.custom_spinner, arrBranch));

        if(!selectedBranch.equals("-")){
            int i=0;

            if (selectedBranch!="-"){
                for(String dt : arrBranch){
                    if(dt.equalsIgnoreCase(selectedBranch)){
                        IndexSpinnerBranch= i;
                    }
                    i++;
                }
            }
            spnBranch.setSelection(IndexSpinnerBranch);
        }

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

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setAdapter(null);
                AsyncCallRewardPointUser task = new AsyncCallRewardPointUser();
                task.execute();
            }
        });

//        RecyclerView rv = (RecyclerView) v.findViewById(R.id.rv);
//        rv.setHasFixedSize(true);
//
//        LinearLayoutManager llm = new LinearLayoutManager(getContext());
//        rv.setLayoutManager(llm);
//
//        initializeData();
//
//        rv.setAdapter(adapter);

        // This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.


//        hideDay();

//        HashMap<String, String> url_maps = new HashMap<String, String>();
//        url_maps.put("Promo point Reward", "http://www.kalbestore.com/Content/images/201657150632_promopoint-reward-5-2.jpg");
//        url_maps.put("Promo Bertingkat", "http://www.kalbestore.com/Content/images/201636160228_Promo-Bertingkat-2-Pilih-3-Brand-1.jpg");
//        url_maps.put("Promo Chilgo", "http://www.kalbestore.com/Content/images/201614290644_KALBE-CHILGO__1_.png");
//
//        for (String name : url_maps.keySet()) {
//            TextSliderView textSliderView = new TextSliderView(getContext());
//            // initialize a SliderLayout
//            textSliderView
//                    .description(name)
//                    .image(url_maps.get(name))
//                    .setScaleType(BaseSliderView.ScaleType.Fit)
//                    .setOnSliderClickListener(this);
//
//            //add your extra information
//            textSliderView.bundle(new Bundle());
//            textSliderView.getBundle()
//                    .putString("extra", name);
//
//            mDemoSlider.addSlider(textSliderView);
//        }
//        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
//        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
//        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
//        mDemoSlider.setDuration(4000);
//        mDemoSlider.addOnPageChangeListener(this);

//        AsyncCallBanner task1 = new AsyncCallBanner();
//        task1.execute();

        initBanner(getContext());

//        AsyncCallRegion task2 = new AsyncCallRegion();
//        task2.execute();

        spnRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedRegion = spnRegion.getSelectedItem().toString();
//                new AsyncCallBranch().execute();
                arrBranch = new ArrayList<>();
                arrBranch = new clsMainActivity().getArrayBranchByRegion(selectedRegion);

                if (!selectedRegion.equalsIgnoreCase(_tUserLoginData.get_txtRegionMenu())){
                    spnBranch.setAdapter(new MyAdapterPeriode(getContext(), R.layout.custom_spinner, arrBranch));
                } else {
                    spnBranch.setAdapter(new MyAdapterPeriode(getContext(), R.layout.custom_spinner, arrBranch));
                    spnBranch.setSelection(IndexSpinnerBranch);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spnBranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedBranch = spnBranch.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        return v;
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

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    public void hideDay() {
        try {
            java.lang.reflect.Field[] f = datePicker.getClass().getDeclaredFields();
            for (java.lang.reflect.Field field : f) {
                if (field.getName().equals("mDayPicker") || field.getName().equals("mDaySpinner")) {
                    field.setAccessible(true);
                    Object dmPicker = new Object();
                    dmPicker = field.get(datePicker);
                    ((View) dmPicker).setVisibility(View.GONE);
                }
            }
        } catch (SecurityException e) {
        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e) {
        }
    }

    private class AsyncCallRegion extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
//            android.os.Debug.waitForDebugger();
            JSONArray Json = null;
            try {
                Json = new clsMainBL().getAllRegion();
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
        protected void onPostExecute(JSONArray regiondata) {
            if (regiondata.size() > 0) {
                Iterator i = regiondata.iterator();

                arrRegion = new ArrayList<String>();

                while (i.hasNext()) {
                    JSONObject innerObj = (JSONObject) i.next();
                    Long IntResult = (Long) innerObj.get("_pboolValid");

                    if (IntResult == 1) {
                        arrRegion.add((String) innerObj.get("TxtName").toString());
                        selectedRegion = (String) innerObj.get("TxtName").toString();
                    }
                }

                spnRegion.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrRegion));

            } else {
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
            }
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            //Make ProgressBar invisible
            //pg.setVisibility(View.VISIBLE);
            Dialog.setMessage("Getting Data");
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

    private class AsyncCallBranch extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
//            android.os.Debug.waitForDebugger();
            JSONArray Json = null;
            try {
                Json = new clsMainBL().getBranchByRegion(selectedRegion);
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
        protected void onPostExecute(JSONArray regiondata) {
            if (regiondata.size() > 0) {
                Iterator i = regiondata.iterator();

                arrBranch = new ArrayList<String>();

                while (i.hasNext()) {
                    JSONObject innerObj = (JSONObject) i.next();
                    Long IntResult = (Long) innerObj.get("_pboolValid");

                    if (IntResult == 1) {
                        arrBranch.add((String) innerObj.get("TxtName").toString());
                    }
                }

                spnBranch.setAdapter(new MyAdapterBranch(getContext(), R.layout.custom_spinner, arrBranch));

            } else {
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
            }
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            //Make ProgressBar invisible
            //pg.setVisibility(View.VISIBLE);
            Dialog.setMessage("Getting Data");
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

    private class AsyncCallRewardPointUser extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
//            android.os.Debug.waitForDebugger();
            JSONArray Json = null;
            try {
                Json = new clsMainBL().getRewardUserByDateAndCabang(selectedYears+selectedMonth, selectedBranch);
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
        protected void onPostExecute(JSONArray regiondata) {
            if(regiondata!=null){
                if (regiondata.size() > 0) {
                    Iterator i = regiondata.iterator();

                    dtListUser = new ArrayList<>();

                    while (i.hasNext()) {
                        JSONObject innerObj = (JSONObject) i.next();
                        Long IntResult = (Long) innerObj.get("_pboolValid");

                        if (IntResult == 1) {
                            String imgUrl = (String) innerObj.get("_urlPhoto").toString();
                            String nama = (String) innerObj.get("_txtNama").toString();
                            String jabatan = (String) innerObj.get("_txtJabatan").toString();
                            String region = (String) innerObj.get("_txtRegion").toString();
                            String cabang = (String) innerObj.get("_txtCabang").toString();
                            String points = (String) innerObj.get("_decPoint").toString();
                            String ranking = (String) innerObj.get("_intRanking").toString();
                            dtListUser.add(new UserRewardData(imgUrl, nama, jabatan, region, cabang, points, ranking));
                        } else if (IntResult == 0){
                            String message = (String) innerObj.get("_pstrMessage").toString();
                            new clsMainActivity().showCustomToast(getContext(), message, false);
                        }
                    }

                    allUsersAdapter = new AllUsersAdapter(dtListUser, getContext());
                    recyclerView.setAdapter(allUsersAdapter);

                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }
            }
             else {
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
            }
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            //Make ProgressBar invisible
            //pg.setVisibility(View.VISIBLE);
            Dialog.setMessage("Getting Data");
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

    public class MyAdapter extends ArrayAdapter<String> {
        public MyAdapter(Context context, int textViewResourceId, List<String> objects) {
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
            label.setText(arrRegion.get(position));
            TextView sub = (TextView) row.findViewById(R.id.tvDesc);
            sub.setVisibility(View.INVISIBLE);
            sub.setVisibility(View.GONE);
            //sub.setText(mydata2[position]);
            //label.setTextColor(new Color().parseColor("#FFFFF"));
            row.setBackgroundColor(new Color().TRANSPARENT);
            return row;
        }

    }

    public class MyAdapterBranch extends ArrayAdapter<String> {
        public MyAdapterBranch(Context context, int textViewResourceId, List<String> objects) {
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
            label.setText(arrBranch.get(position));
            TextView sub = (TextView) row.findViewById(R.id.tvDesc);
            sub.setVisibility(View.INVISIBLE);
            sub.setVisibility(View.GONE);
            //sub.setText(mydata2[position]);
            //label.setTextColor(new Color().parseColor("#FFFFF"));
            row.setBackgroundColor(new Color().TRANSPARENT);
            return row;
        }

    }
//
//    public class User {
//        private String imageResourceId;
//        private String profileName;
//        private String jabatan;
//        private String region;
//        private String cabang;
//        private String points;
//        private String ranking;
//
//        public String getImageResourceId() {
//            return imageResourceId;
//        }
//        public String getProfileName() {
//            return profileName;
//        }
//        public String getJabatan(){
//            return jabatan;
//        }
//        public String getRegion(){
//            return region;
//        }
//
//        public String getCabang(){
//            return cabang;
//        }
//        public String getPoints(){
//            return points;
//        }
//
//        public String getRanking(){
//            return ranking;
//        }
//
//        public User(String imageResourceId, String profileName, String jabatan, String region, String cabang, String points, String ranking) {
//            this.imageResourceId = imageResourceId;
//            this.profileName = profileName;
//            this.jabatan = jabatan;
//            this.region = region;
//            this.cabang = cabang;
//            this.points = points;
//            this.ranking = ranking;
//        }
//    }


//    private class AsyncCallBanner extends AsyncTask<org.json.simple.JSONArray, Void, org.json.simple.JSONArray> {
//
//        @Override
//        protected org.json.simple.JSONArray doInBackground(org.json.simple.JSONArray... params) {
////            android.os.Debug.waitForDebugger();
//            String pagewidth = "360";
//            tUserLoginData data = new tUserLoginBL().getUserActive();
//            org.json.simple.JSONArray Json = null;
//            try {
//                Json = new clsMainBL().getBanner(data.get_txtUserId(), data.get_txtRoleId(), pagewidth);
//            } catch (ParseException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            return Json;
//        }
//
//        private ProgressDialog Dialog = new ProgressDialog(getContext());
//
//        @Override
//        protected void onCancelled() {
//            Dialog.dismiss();
//            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCancelRequest, false);
//        }
//
//        @Override
//        protected void onPostExecute(org.json.simple.JSONArray branchdata) {
//            if (branchdata.size() > 0) {
//                Iterator i = branchdata.iterator();
//                dtListdataBanner = new ArrayList<>();
//
//                while (i.hasNext()) {
//                    JSONObject innerObj = (JSONObject) i.next();
//                    Long IntResult = (Long) innerObj.get("_pboolValid");
//
//                    if (IntResult == 1) {
//                        String IntBannerID = (String) innerObj.get("IntBannerID").toString();
//                        String TxtFileName = (String) innerObj.get("TxtFileName").toString();
//                        String TxtName = (String) innerObj.get("TxtName").toString();
//                        String TxtPathActual = (String) innerObj.get("TxtPathActual").toString();
//                        String TxtPathThumbnail = (String) innerObj.get("TxtPathThumbnail").toString();
//                        String txtDesc = (String) innerObj.get("txtDesc").toString();
//                        dtListdataBanner.add(new dataBanner(IntBannerID, TxtFileName, TxtName, TxtPathActual, TxtPathThumbnail, txtDesc));
//                    }
//                }
//                initBanner(getContext(), dtListdataBanner);
//            } else {
//                Uri uri = Uri.parse("android.resource://com.kalbenutritionals.xteamnative/mipmap/banner_kalbe");
//                dtListdataBanner = new ArrayList<>();
//                String IntBannerID = "";
//                String TxtFileName = "";
//                String TxtName = "";
//                String TxtPathActual = String.valueOf(uri);
//                String TxtPathThumbnail = "";
//                String txtDesc = "";
//                dtListdataBanner.add(new dataBanner(IntBannerID, TxtFileName, TxtName, TxtPathActual, TxtPathThumbnail, txtDesc));
//                initBanner(getContext(), dtListdataBanner);
//                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessImageNotFound, false);
//            }
//            Dialog.dismiss();
//        }
//
//        @Override
//        protected void onPreExecute() {
//            Dialog.setMessage("Getting Data");
//            Dialog.setCancelable(false);
//            Dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                }
//            });
//            Dialog.show();
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//            Dialog.dismiss();
//        }
//    }

    private void initBanner(Context context) {
        mDemoSlider = (SliderLayout) v.findViewById(R.id.slider);

        final HashMap<String,String> url_maps = new HashMap<String, String>();

        dtListdataBanner = new mBannerBL().GetAllData();

        for( mBannerData dt : dtListdataBanner){
            url_maps.put(dt.get_txtDesc(), dt.get_TxtPathActual());
        }
//        url_maps.put("Waktu Operasional", "https://www.klik-apotek.com/Upload/Images/Slider/e82a4c1843de428f844508c587a8519f_1474469058.jpg");
//        url_maps.put("CarQ", "https://www.klik-apotek.com/Upload/Images/Slider/fcc25458778f490a8743a16f53753829_1474452222.jpg");
//        url_maps.put("Promo Truxanthin", "https://www.klik-apotek.com/Upload/Images/Slider/ae58061ed0a7462dbacee52051fc2a1d_1474385768.jpg");

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

    public class MyAdapterPeriode extends ArrayAdapter<String> {

        List<String> arrObject;

        public MyAdapterPeriode(Context context, int textViewResourceId, List<String> objects) {
            super(context, textViewResourceId, objects);
            arrObject = new ArrayList<>();
            arrObject = objects;
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
            label.setText(arrObject.get(position));
            TextView sub = (TextView) row.findViewById(R.id.tvDesc);
            sub.setVisibility(View.INVISIBLE);
            sub.setVisibility(View.GONE);
            row.setBackgroundColor(new Color().TRANSPARENT);
            return row;
        }
    }

    public class MyAdapterPeriodes extends ArrayAdapter<String> {

        List<String> arrObject;

        public MyAdapterPeriodes(Context context, int textViewResourceId, List<String> objects) {
            super(context, textViewResourceId, objects);
            arrObject = new ArrayList<>();
            arrObject = objects;
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
            label.setText(arrObject.get(position));
            TextView sub = (TextView) row.findViewById(R.id.tvDesc);
            sub.setVisibility(View.INVISIBLE);
            sub.setVisibility(View.GONE);
            row.setBackgroundColor(new Color().TRANSPARENT);
            return row;
        }
    }
}
