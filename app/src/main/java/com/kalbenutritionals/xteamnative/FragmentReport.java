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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
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
import bl.mBranchBL;
import bl.mUserRoleBL;
import bl.tUserLoginBL;
import library.common.mBannerData;
import library.common.mBranchData;
import library.common.mUserRoleData;
import library.common.tUserLoginData;
import library.dal.clsHardCode;

/**
 * Created by ASUS ZE on 26/10/2016.
 */

public class FragmentReport extends Fragment implements ViewPagerEx.OnPageChangeListener, BaseSliderView.OnSliderClickListener {

    View v;
    List<String> arrTypeReport, arrRegion, arrBranch, arrDataReport;
    Spinner spnTypeReport, spnRegion, spnBranch;
    String selectedRegion, selectedTypeReport, selectedBranch;
    Button btnViewwData;
    List<ReportData> dtListReportData;
    List<ReportDataOutstanding> dtListReportDataOutstanding;
    TableLayout tReport;
    FloatingActionButton fab;
    List<mBannerData> dtListdataBanner;
    private SliderLayout mDemoSlider;
    List<mBranchData> _mBranchData;
    tUserLoginData _tUserLoginData;

    int IndexSpinnerRegion, IndexSpinnerBranch = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.coordinator_layout, container, false);
        spnTypeReport = (Spinner) v.findViewById(R.id.spn_report);
        spnRegion = (Spinner) v.findViewById(R.id.spn_region);
        spnBranch = (Spinner)  v.findViewById(R.id.spn_branch);
        btnViewwData = (Button) v.findViewById(R.id.btn_view_data);
        tReport = (TableLayout) v.findViewById(R.id.tl_data_report);

//        AsyncCallBanner task1 = new AsyncCallBanner();
//        task1.execute();

        fab = (FloatingActionButton) v.findViewById(R.id.fab);

        final ScrollView scrollView = (ScrollView) v.findViewById(R.id.sv_report);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View vi, MotionEvent event) {
                LinearLayout lnLayout = (LinearLayout) v.findViewById(R.id.ll_report);

//                int coords[]={0,0};
//                lnLayout.getLocationInWindow(coords);
//
//                int absoluteTop = coords[1];
//
//                if(absoluteTop < 90){
//                    fab.setVisibility(View.VISIBLE);
//                }
//                else{
//                    fab.setVisibility(View.INVISIBLE);
//                }
//
//                return false;
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

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.fullScroll(ScrollView.FOCUS_UP);
                fab.setVisibility(View.INVISIBLE);
            }
        });

        arrTypeReport = new ArrayList<>();

        arrTypeReport.add("Data Sales");
        arrTypeReport.add("Data Outstanding Advance");

        spnTypeReport.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrTypeReport));

//        AsyncCallRegion task2 = new AsyncCallRegion();
//        task2.execute();

        spnTypeReport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedTypeReport = spnTypeReport.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        _mBranchData = new ArrayList<>();
        _mBranchData = new mBranchBL().GetAllData();

        _tUserLoginData = new tUserLoginBL().getUserActive();
        selectedRegion = _tUserLoginData.get_txtRegionMenu();
        selectedBranch = _tUserLoginData.get_txtCabangMenu();

        arrRegion = new ArrayList<>();
        arrRegion = new clsMainActivity().getArrayRegion(selectedRegion);

        spnRegion.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrRegion));

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

        spnBranch.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrBranch));

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

        spnRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedRegion = spnRegion.getSelectedItem().toString();
//                new AsyncCallBranch().execute();
                arrBranch = new ArrayList<>();
                arrBranch = new clsMainActivity().getArrayBranchByRegion(selectedRegion);

                if (!selectedRegion.equalsIgnoreCase(_tUserLoginData.get_txtRegionMenu())){
                    spnBranch.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrBranch));
                } else {
                    spnBranch.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrBranch));
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

        btnViewwData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectedTypeReport.equals(arrTypeReport.get(0).toString())){
//                    new clsMainActivity().showCustomToast(getContext(), "yes", true);
                    tReport.removeAllViews();
                    AsyncCallReportSalesAPIByTxtCabang task = new AsyncCallReportSalesAPIByTxtCabang();
                    task.execute();
                } else if (selectedTypeReport.equals(arrTypeReport.get(1).toString())){
//                    new clsMainActivity().showCustomToast(getContext(), "no", false);
                    tReport.removeAllViews();
                    AsyncCallReportOutstandingAdvance task = new AsyncCallReportOutstandingAdvance();
                    task.execute();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), "Please Select Typer Report...", false);
                }
            }
        });

        initBanner(getContext());

        return  v;

    }

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
////                    else if (IntResult == 0){
////                        String IntBannerID = (String) innerObj.get("IntBannerID").toString();
////                        String TxtFileName = (String) innerObj.get("TxtFileName").toString();
////                        String TxtName = (String) innerObj.get("TxtName").toString();
////                        String TxtPathActual = (String) innerObj.get("TxtPathActual").toString();
////                        String TxtPathThumbnail = (String) innerObj.get("TxtPathThumbnail").toString();
////                        String txtDesc = (String) innerObj.get("txtDesc").toString();
////                        dtListdataBanner.add(new dataBanner(IntBannerID, TxtFileName, TxtName, TxtPathActual, TxtPathThumbnail, txtDesc));
//////
////
////                    }
//
//                }
//
//                initBanner(getContext(), dtListdataBanner);
//
//
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
//            //Make ProgressBar invisible
//            //pg.setVisibility(View.VISIBLE);
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

        dtListdataBanner = new mBannerBL().GetAllData();

        mDemoSlider = (SliderLayout) v.findViewById(R.id.slider);

        final HashMap<String,String> url_maps = new HashMap<String, String>();

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

    public class MyAdapter extends ArrayAdapter<String> {

        List<String> arrObject;

        public MyAdapter(Context context, int textViewResourceId, List<String> objects) {
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
            if (regiondata!=null) {
                if(regiondata.size()>0) {
                    Iterator i = regiondata.iterator();

                    arrRegion = new ArrayList<String>();

                    while (i.hasNext()) {
                        JSONObject innerObj = (JSONObject) i.next();
                        Long IntResult = (Long) innerObj.get("_pboolValid");

                        if (IntResult == 1) {
                            arrRegion.add((String) innerObj.get("TxtName").toString());
//                        selectedRegion = (String) innerObj.get("TxtName").toString();
                        }
                    }

                    spnRegion.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrRegion));
                }

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

                spnBranch.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrBranch));

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

    private class AsyncCallReportSalesAPIByTxtCabang extends AsyncTask<JSONArray, Void, JSONArray> {

        mUserRoleData mUserRoleData;
        mUserRoleBL userRoleBL;


        @Override
        protected JSONArray doInBackground(JSONArray... params) {
//            android.os.Debug.waitForDebugger();
            tUserLoginData data = new tUserLoginBL().getUserActive();
            JSONArray Json = null;
            try {
                Json = new clsMainBL().getReportSalesAPIByTxtCabang(selectedBranch, data.get_txtRoleId());
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
        protected void onPostExecute(JSONArray regiondata) {
            if(regiondata!=null){
                if (regiondata.size() > 0) {
                    Iterator i = regiondata.iterator();

                    dtListReportData = new ArrayList<>();

                    while (i.hasNext()) {
                        JSONObject innerObj = (JSONObject) i.next();
                        Long IntResult = (Long) innerObj.get("_pboolValid");

                        if (IntResult == 1) {
                            String _txtGroupKN = (String) innerObj.get("_txtGroupKN").toString();
                            String _txtUmbBrand = (String) innerObj.get("_txtUmbBrand").toString();
                            String _decPercentMTD = (String) innerObj.get("_decPercentMTD").toString();
                            String _decPercentYTD = (String) innerObj.get("_decPercentYTD").toString();
                            dtListReportData.add(new ReportData(_txtGroupKN, _txtUmbBrand, _decPercentMTD, _decPercentYTD));
                        } else {
                            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                        }
                    }
                    initTableReport(getContext(), dtListReportData);

                }
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

    private void initTableReport(Context context, List<ReportData> dtListReportData) {

        tReport = (TableLayout) v.findViewById(R.id.tl_data_report);

        tReport.removeAllViews();

        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1f);
        params.setMargins(1, 1, 1, 1);

        TableRow tr = new TableRow(getContext());

        String[] colTextHeader = {"KN", "Brand", "Ach. MTD", "Ach. YTD"};

        for (String text : colTextHeader) {
            TextView tv = new TextView(getContext());

            tv.setTextSize(14);
            tv.setPadding(10, 10, 10, 10);
            tv.setText(text);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundColor(Color.parseColor("#4CAF50"));
            tv.setTextColor(Color.WHITE);
            tv.setLayoutParams(params);

            tr.addView(tv);
        }
        tReport.addView(tr);

        if(dtListReportData!=null){
            int index = 1;
            for(ReportData dat : dtListReportData){
                tr = new TableRow(getContext());

                TextView kn = new TextView(getContext());
                kn.setTextSize(12);
                kn.setPadding(10, 10, 10, 10);
                kn.setBackgroundColor(dat.get_txtGroupKN().contains("TOTAL") ? Color.parseColor("#d2fc47") : Color.parseColor("#f0f0f0"));
                kn.setTextColor(Color.BLACK);
                kn.setGravity(Gravity.CENTER);
                kn.setText(dat.get_txtGroupKN());
                kn.setLayoutParams(params);

                tr.addView(kn);

                TextView brand = new TextView(getContext());
                brand.setTextSize(12);
                brand.setPadding(10, 10, 10, 10);
                brand.setBackgroundColor(dat.get_txtGroupKN().contains("TOTAL") ? Color.parseColor("#d2fc47") : Color.parseColor("#f0f0f0"));
                brand.setTextColor(Color.BLACK);
                brand.setGravity(Gravity.CENTER);
                brand.setText(dat.get_txtUmbBrand());
                brand.setLayoutParams(params);

                tr.addView(brand);

                TextView ach_mtd = new TextView(getContext());
                ach_mtd.setTextSize(12);
                ach_mtd.setPadding(10, 10, 10, 10);
                ach_mtd.setBackgroundColor(dat.get_txtGroupKN().contains("TOTAL") ? Color.parseColor("#d2fc47") : Color.parseColor("#f0f0f0"));
                ach_mtd.setTextColor(Color.BLACK);
                ach_mtd.setGravity(Gravity.CENTER);
                ach_mtd.setText(dat.get_decPercentMTD());
                ach_mtd.setLayoutParams(params);

                tr.addView(ach_mtd);

                TextView ach_ytd = new TextView(getContext());
                ach_ytd.setTextSize(12);
                ach_ytd.setPadding(10, 10, 10, 10);
                ach_ytd.setBackgroundColor(dat.get_txtGroupKN().contains("TOTAL") ? Color.parseColor("#d2fc47") : Color.parseColor("#f0f0f0"));
                ach_ytd.setTextColor(Color.BLACK);
                ach_ytd.setGravity(Gravity.CENTER);
                ach_ytd.setText(dat.get_decPercentYTD());
                ach_ytd.setLayoutParams(params);

                tr.addView(ach_ytd);

                tReport.addView(tr,index++);
            }
        }
    }

    private class AsyncCallReportOutstandingAdvance extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
//            android.os.Debug.waitForDebugger();
            tUserLoginData data = new tUserLoginBL().getUserActive();
            JSONArray Json = null;
            try {
                Json = new clsMainBL().getReportOutstandingAdvance(selectedBranch, data.get_txtRoleId());
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
        protected void onPostExecute(JSONArray regiondata) {
            if(regiondata!=null){
                if (regiondata.size() > 0) {
                    Iterator i = regiondata.iterator();

                    dtListReportDataOutstanding = new ArrayList<>();

                    while (i.hasNext()) {
                        JSONObject innerObj = (JSONObject) i.next();
                        Long IntResult = (Long) innerObj.get("_pboolValid");

                        if (IntResult == 1) {
                            String _txtNoDokumen = (String) innerObj.get("_txtNoDokumen").toString();
                            String _txtCabang = (String) innerObj.get("_txtCabang").toString();
                            String _txtAging = (String) innerObj.get("_txtAging").toString();
                            String _txtRequester = (String) innerObj.get("_txtRequester").toString();
                            String _txtSource = (String) innerObj.get("_txtSource").toString();
                            String _pstrMessage = (String) innerObj.get("_pstrMessage").toString();
//                            initTableReportDataOutstanding(getContext(), dtListReportDataOutstanding);
                            dtListReportDataOutstanding.add(new ReportDataOutstanding(_txtNoDokumen, _txtCabang, _txtAging, _txtRequester, _txtSource, _pstrMessage));
                        } else if (IntResult == 0){
                            String _txtNoDokumen = (String) innerObj.get("_txtNoDokumen").toString();
                            String _txtCabang = (String) innerObj.get("_txtCabang").toString();
                            String _txtAging = (String) innerObj.get("_txtAging").toString();
                            String _txtRequester = (String) innerObj.get("_txtRequester").toString();
                            String _txtSource = (String) innerObj.get("_txtSource").toString();
                            String _pstrMessage = (String) innerObj.get("_pstrMessage").toString();
                            dtListReportDataOutstanding.add(new ReportDataOutstanding(_txtNoDokumen, _txtCabang, _txtAging, _txtRequester, _txtSource, _pstrMessage));
//                            initTableReportDataOutstanding(getContext(), dtListReportDataOutstanding);
//                            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                        }
                    }
                    initTableReportDataOutstanding(getContext(), dtListReportDataOutstanding);

                }
            } else {
//                initTableReportDataOutstanding(getContext(), dtListReportDataOutstanding);
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

    private void initTableReportDataOutstanding(Context context, List<ReportDataOutstanding> dtListReportDataOutstanding) {
        tReport = (TableLayout) v.findViewById(R.id.tl_data_report);

        tReport.removeAllViews();
//        tReport.setPadding(0,5,0,0);

        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1f);
        params.setMargins(1, 1, 1, 1);

        TableRow tr = new TableRow(getContext());

        String[] colTextHeader = {"No Dokumen", "Cabang", "Aging", "Requester", "Source"};

        Iterator i = dtListReportDataOutstanding.iterator();

        while (i.hasNext()){
            ReportDataOutstanding dt = (ReportDataOutstanding) i.next();

            List<String> arrData ;
            arrData = new ArrayList<>();
            arrData.add(dt.get_txtNoDokumen());
            arrData.add(dt.get_txtCabang());
            arrData.add(dt.get_txtAging());
            arrData.add(dt.get_txtRequester());
            arrData.add(dt.get_txtSource());
            arrData.add(dt.get_pstrMessage());

            int index = 0;
            for(String text : colTextHeader){
                tr = new TableRow(getContext());

                TextView kn = new TextView(getContext());
                kn.setTextSize(14);
                kn.setPadding(10, 10, 10, 10);
                kn.setBackgroundColor(text.contains("No Dokumen") ? Color.parseColor("#ff7e33") : Color.parseColor("#4CAF50"));
                kn.setTextColor(Color.WHITE);
                kn.setGravity(Gravity.CENTER);
                kn.setText(text);
                kn.setLayoutParams(params);

                tr.addView(kn);

                TextView data = new TextView(getContext());
                data.setTextSize(12);
                data.setPadding(10, 10, 10, 10);
                data.setBackgroundColor(text.contains("No Dokumen") ? Color.parseColor("#ff7e33") : Color.parseColor("#f0f0f0"));
                data.setTextColor(text.contains("No Dokumen") ? Color.WHITE : Color.BLACK);
                data.setGravity(Gravity.CENTER);
                data.setText(arrData.get(index).equals("") ? arrData.get(5): arrData.get(index));
                data.setLayoutParams(params);

                tr.addView(data);

                tReport.addView(tr,index++);
            }
        }

//        List<String> arrData ;
//        arrData = new ArrayList<>();
//        arrData.add(dtListReportDataOutstanding.get(0)._txtNoDokumen);
//        arrData.add(dtListReportDataOutstanding.get(0)._txtCabang);
//        arrData.add(dtListReportDataOutstanding.get(0)._txtAging);
//        arrData.add(dtListReportDataOutstanding.get(0)._txtRequester);
//        arrData.add(dtListReportDataOutstanding.get(0)._txtSource);
//        arrData.add(dtListReportDataOutstanding.get(0)._pstrMessage);


//        if(dtListReportDataOutstanding!=null){
//            int index = 0;
//            for(String text : colTextHeader){
//                tr = new TableRow(getContext());
//
//                TextView kn = new TextView(getContext());
//                kn.setTextSize(14);
//                kn.setPadding(10, 10, 10, 10);
//                kn.setBackgroundColor(Color.parseColor("#4CAF50"));
//                kn.setTextColor(Color.WHITE);
//                kn.setGravity(Gravity.CENTER);
//                kn.setText(text);
//                kn.setLayoutParams(params);
//
//                tr.addView(kn);
//
//                TextView data = new TextView(getContext());
//                data.setTextSize(12);
//                data.setPadding(10, 10, 10, 10);
//                data.setBackgroundColor(text.contains("No Dokumen") ? Color.parseColor("#ff7e33") : Color.parseColor("#f0f0f0"));
//                data.setTextColor(Color.BLACK);
//                data.setGravity(Gravity.CENTER);
//                data.setText(arrData.get(index).equals("") ? arrData.get(5): arrData.get(index));
//                data.setLayoutParams(params);
//
//                tr.addView(data);
//
//                tReport.addView(tr,index++);
//            }
//        }
    }


    public class ReportData {
        private String _txtGroupKN;
        private String _txtUmbBrand;
        private String _decPercentMTD;
        private String _decPercentYTD;

        public String get_txtGroupKN() {
            return _txtGroupKN;
        }
        public String get_txtUmbBrand() {
            return _txtUmbBrand;
        }
        public String get_decPercentMTD(){
            return _decPercentMTD;
        }
        public String get_decPercentYTD(){
            return _decPercentYTD;
        }

        public ReportData(String _txtGroupKN, String _txtUmbBrand, String _decPercentMTD, String _decPercentYTD) {
            this._txtGroupKN = _txtGroupKN;
            this._txtUmbBrand = _txtUmbBrand;
            this._decPercentMTD = _decPercentMTD;
            this._decPercentYTD = _decPercentYTD;
        }
    }
    public class ReportDataOutstanding {
        private String _txtNoDokumen;
        private String _txtCabang;
        private String _txtAging;
        private String _txtRequester;
        private String _txtSource;
        private String _pstrMessage;

        public String get_txtNoDokumen() {
            return _txtNoDokumen;
        }
        public String get_txtCabang() {
            return _txtCabang;
        }
        public String get_txtAging(){
            return _txtAging;
        }
        public String get_txtRequester(){
            return _txtRequester;
        }
        public String get_txtSource(){
            return _txtSource;
        }
        public String get_pstrMessage(){
            return _pstrMessage;
        }

        public ReportDataOutstanding(String _txtNoDokumen, String _txtCabang, String _txtAging, String _txtRequester, String _txtSource,String _pstrMessage) {
            this._txtNoDokumen = _txtNoDokumen;
            this._txtCabang = _txtCabang;
            this._txtAging = _txtAging;
            this._txtRequester = _txtRequester;
            this._txtSource = _txtSource;
            this._pstrMessage = _pstrMessage;
        }
    }
}
