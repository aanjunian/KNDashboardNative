package com.kalbenutritionals.xteamnative;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import bl.mBranchBL;
import bl.mUserRoleBL;
import bl.tUserLoginBL;
import library.common.mBannerData;
import library.common.mBranchData;
import library.common.mUserRoleData;
import library.common.tUserLoginData;
import library.dal.clsHardCode;
import library.dal.mBannerDA;

public class FragmentHome extends Fragment implements ViewPagerEx.OnPageChangeListener, BaseSliderView.OnSliderClickListener {

    View v;
    private SliderLayout mDemoSlider;
    List<mBannerData> dtListdataBanner;
    TableLayout tlHome;
    List<String> arrData, arrRole, arrBranch;
    Spinner spnRole, spnBranch;
    Button btnProcess;
    String selectedRole, selectedBranch;
    private HashMap<String, String> HMRole = new HashMap<String, String>();
    private HashMap<String, String> HMBranch = new HashMap<String, String>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_home,container,false);
        spnRole = (Spinner) v.findViewById(R.id.spn_role);
        spnBranch = (Spinner) v.findViewById(R.id.spn_branch);
        btnProcess = (Button) v.findViewById(R.id.btn_process);

        tUserLoginData dt=new tUserLoginBL().getUserActive();
        List<mBranchData> _mBranchData = new mBranchBL().GetAllData();
        List<mUserRoleData> _mUserRole = new mUserRoleBL().GetAllData();

        arrRole = new ArrayList<>();
        arrRole.add("-");

        arrBranch = new ArrayList<>();
        arrBranch.add("-");

        arrData = new ArrayList<>();
        arrData = new clsMainActivity().getArraytUserLoginData(dt);

        int indexSpnRole = 0;
        int indexSpnBranch = 0;

        if(_mUserRole.size()>0){
            arrRole = new ArrayList<>();
            for(mUserRoleData data : _mUserRole){
                arrRole.add(data.get_txtRoleName());
                HMRole.put(data.get_txtRoleName(), data.get_intRoleId());
                if(data.get_txtRoleName().equalsIgnoreCase(dt.get_txtRoleName())){
                    indexSpnRole = arrRole.size()-1;
                }
            }
        }

        if(_mBranchData.size()>0){
            arrBranch = new ArrayList<>();
            for (mBranchData data : _mBranchData){
                arrBranch.add(data.get_txtBranchName());
                if(data.get_txtBranchName().equalsIgnoreCase(dt.get_txtCabangMenu())){
                    indexSpnBranch = arrBranch.size()-1;
                }
            }
        }

        spnRole.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrRole));

        spnRole.setSelection(indexSpnRole);

        spnBranch.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrBranch));

        spnBranch.setSelection(indexSpnBranch);

        spnRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedRole = spnRole.getSelectedItem().toString();
                new AsyncCallBranch().execute();
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

        AsyncCallBanner task = new AsyncCallBanner();
        task.execute();

        initTableDataHome(getContext(), arrData);

        btnProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    builder.setTitle("Confirm");
                    builder.setMessage("Do you want to process?");

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            tUserLoginData dt=new tUserLoginBL().getUserActive();

                                tUserLoginData _UserLoginData = new tUserLoginData();
                            List<mBranchData> _mBranchData;
                            _mBranchData = new mBranchBL().GetRegionByBranchId(HMBranch.get(selectedBranch));
                                _UserLoginData.set_txtRoleId(HMRole.get(selectedRole));
                                _UserLoginData.set_txtRoleName(selectedRole);
                                _UserLoginData.set_txtCabangIdMenu(HMBranch.get(selectedBranch));
                                _UserLoginData.set_txtCabangMenu(selectedBranch);
                                _UserLoginData.set_txtRegionMenu(_mBranchData==null ? "-" :_mBranchData.get(0).get_txtRegion());

                                new tUserLoginBL().updateDataValueById(_UserLoginData, dt.get_intId());

                            tUserLoginData data=new tUserLoginBL().getUserActive();

                                arrData = new ArrayList<>();
                                arrData = new clsMainActivity().getArraytUserLoginData(data);

                                initTableDataHome(getContext(), arrData);

                                AsyncCallBanner task = new AsyncCallBanner();
                                task.execute();

                                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessProcessSuccess, true);

                        }
                    });

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();
            }
        });

        return v;
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

    private void initTableDataHome(Context context, List<String> _arrData) {

        tlHome = (TableLayout) v.findViewById(R.id.tl_datahome);

        tlHome.removeAllViews();

        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1f);
        params.setMargins(1, 1, 1, 1);

        TableRow tr = new TableRow(getContext());

        String[] colTextHeader = {"NIK", "Username", "Role", "Region", "Branch"};

            int index = 0;
            for (String text : colTextHeader) {
                tr = new TableRow(context);

                TextView kn = new TextView(context);
                kn.setTextSize(14);
                kn.setPadding(10, 10, 10, 10);
                kn.setBackgroundColor(Color.parseColor("#4CAF50"));
                kn.setTextColor(Color.WHITE);
                kn.setGravity(Gravity.LEFT);
                kn.setText(text);
                kn.setLayoutParams(params);

                tr.addView(kn);

                TextView data = new TextView(context);
                data.setTextSize(14);
                data.setPadding(10, 10, 10, 10);
                data.setBackgroundColor(Color.parseColor("#f0f0f0"));
                data.setTextColor(Color.BLACK);
                data.setGravity(Gravity.LEFT);
                data.setText(_arrData.get(index));
                data.setLayoutParams(params);

                tr.addView(data);

                tlHome.addView(tr, index++);
        }
    }

    private void initBanner(Context context, List<mBannerData> dtListBanner) {
        mDemoSlider = (SliderLayout) v.findViewById(R.id.slider);

        mDemoSlider.removeAllSliders();

        final HashMap<String,String> url_maps = new HashMap<String, String>();

        for( mBannerData dt : dtListBanner){
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

    private class AsyncCallBanner extends AsyncTask<org.json.simple.JSONArray, Void, org.json.simple.JSONArray> {

        @Override
        protected org.json.simple.JSONArray doInBackground(org.json.simple.JSONArray... params) {
//            android.os.Debug.waitForDebugger();
            String pagewidth = "360";
            tUserLoginData data = new tUserLoginBL().getUserActive();
            org.json.simple.JSONArray Json = null;
            try {
                Json = new clsMainBL().getBanner(data.get_txtUserId(), data.get_txtRoleId(), pagewidth);
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
        protected void onPostExecute(org.json.simple.JSONArray branchdata) {

            Uri uri = Uri.parse("android.resource://com.kalbenutritionals.xteamnative/mipmap/banner_kalbe");
            dtListdataBanner = new ArrayList<>();
            String IntBannerID = "";
            String TxtFileName = "";
            String TxtName = "";
            String TxtPathActual = String.valueOf(uri);
            String TxtPathThumbnail = "";
            String txtDesc = "";

            if(branchdata!=null){
                if (branchdata.size() > 0) {
                    Iterator i = branchdata.iterator();
                    dtListdataBanner = new ArrayList<>();

                    SQLiteDatabase db = new clsMainBL().getDb();
                    mBannerDA _mBannerDA = new mBannerDA(db);
                    _mBannerDA.DeleteAllDataMConfig(db);

                    while (i.hasNext()) {
                        JSONObject innerObj = (JSONObject) i.next();
                        Long IntResult = (Long) innerObj.get("_pboolValid");

                        mBannerData _data = new mBannerData();

                        if (IntResult == 1) {
                            int index = _mBannerDA.getContactsCount(db) + 1;
                            _data.set_uuId(String.valueOf(index));
                            _data.set_IntBannerID(String.valueOf(innerObj.get("IntBannerID")));
                            _data.set_TxtFileName(String.valueOf(innerObj.get("TxtFileName")));
                            _data.set_TxtName(String.valueOf(innerObj.get("TxtName")));
                            _data.set_TxtPathActual(String.valueOf(innerObj.get("TxtPathActual")));
                            _data.set_TxtPathThumbnail(String.valueOf(innerObj.get("TxtPathThumbnail")));
                            _data.set_txtDesc(String.valueOf(innerObj.get("txtDesc")));
                            _mBannerDA.SaveDataMConfig(db, _data);

                            dtListdataBanner.add(_data);
                        }
                    }

                    initBanner(getContext(), dtListdataBanner);

                } else {
                    SQLiteDatabase db = new clsMainBL().getDb();
                    mBannerDA _mBannerDA = new mBannerDA(db);
                    _mBannerDA.DeleteAllDataMConfig(db);

                    mBannerData _data = new mBannerData();

                    int index = _mBannerDA.getContactsCount(db) + 1;
                    _data.set_uuId(String.valueOf(index));
                    _data.set_IntBannerID(IntBannerID);
                    _data.set_TxtFileName(TxtFileName);
                    _data.set_TxtName(TxtName);
                    _data.set_TxtPathActual(TxtPathActual);
                    _data.set_TxtPathThumbnail(TxtPathThumbnail);
                    _data.set_txtDesc(txtDesc);
                    _mBannerDA.SaveDataMConfig(db, _data);
                    dtListdataBanner.add(new mBannerData(String.valueOf(index), IntBannerID, TxtFileName, TxtName, TxtPathActual, TxtPathThumbnail, txtDesc));
                    initBanner(getContext(), dtListdataBanner);
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessImageNotFound, false);
                }
            } else {
                SQLiteDatabase db = new clsMainBL().getDb();
                mBannerDA _mBannerDA = new mBannerDA(db);
                _mBannerDA.DeleteAllDataMConfig(db);

                mBannerData _data = new mBannerData();

                int index = _mBannerDA.getContactsCount(db) + 1;
                _data.set_uuId(String.valueOf(index));
                _data.set_IntBannerID(IntBannerID);
                _data.set_TxtFileName(TxtFileName);
                _data.set_TxtName(TxtName);
                _data.set_TxtPathActual(TxtPathActual);
                _data.set_TxtPathThumbnail(TxtPathThumbnail);
                _data.set_txtDesc(txtDesc);
                _mBannerDA.SaveDataMConfig(db, _data);
                dtListdataBanner.add(new mBannerData(String.valueOf(index), IntBannerID, TxtFileName, TxtName, TxtPathActual, TxtPathThumbnail, txtDesc));
                initBanner(getContext(), dtListdataBanner);
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessImageNotFound, false);
            }

            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            Dialog.setMessage("Getting Data Banner");
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

    private class AsyncCallBranch extends  AsyncTask<JSONArray, Void, JSONArray>{
        @Override
        protected JSONArray doInBackground(JSONArray... jsonArrays) {
            JSONArray Json=null;
            String nameRole = selectedRole;
            mUserRoleData data = new mUserRoleBL().getIntUserID();
            String intUserID = data.get_txtUserId();
            try {
                new tUserLoginBL().getMasterBranch(HMRole.get(nameRole), intUserID);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return Json ;
        }
        private ProgressDialog Dialog = new ProgressDialog(getContext());

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCancelRequest, false);
        }

        @Override
        protected void onPostExecute(JSONArray array) {
            List<mBranchData> _mBranchData = new mBranchBL().GetAllData();
            tUserLoginData dt=new tUserLoginBL().getUserActive();
            int indexSpnBranch = 0;
            arrBranch = new ArrayList<>();
            arrBranch.add("-");

            if(_mBranchData.size()>0){
                arrBranch = new ArrayList<>();
                for (mBranchData data : _mBranchData){
                    HMBranch.put(data.get_txtBranchName(), data.get_txtBranchID());
                    arrBranch.add(data.get_txtBranchName());
                    if(data.get_txtBranchName().equalsIgnoreCase(dt.get_txtCabangMenu())){
                        indexSpnBranch = arrBranch.size()-1;
                    }
                }
            }

            spnBranch.setAdapter(new MyAdapter(getContext(), R.layout.custom_spinner, arrBranch));

            spnBranch.setSelection(indexSpnBranch);

            Dialog.dismiss();

        }

        @Override
        protected void onPreExecute() {
            Dialog.setMessage("Getting Data Branch");
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
}
