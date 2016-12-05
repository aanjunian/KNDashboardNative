package com.kalbenutritionals.xteamnative;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import bl.clsMainBL;
import bl.mBannerBL;
import bl.mBranchBL;
import bl.tUserCheckinBL;
import bl.tUserLoginBL;
import library.common.mBannerData;
import library.common.mBranchData;
import library.common.tTypeOutletData;
import library.common.tUserCheckinData;
import library.common.tUserLoginData;
import library.dal.clsHardCode;

import static android.content.Context.LOCATION_SERVICE;

public class FragmentCheckinGeolocation extends Fragment implements ConnectionCallbacks, OnConnectionFailedListener, LocationListener, ViewPagerEx.OnPageChangeListener, BaseSliderView.OnSliderClickListener {

    private GoogleMap mMap;
    private Location mLastLocation;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
    private static final String TAG = FragmentCheckinGeolocation.class.getSimpleName();
    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;


    View v;

    private static TextView tvOutletType, tvOutletNameResullt;
    private Button btn_checkin, btn_back;

    private Button btn_getPosition;
    private static EditText et_longitude, et_latitude, et_accuracy, et_search;
    private RadioGroup rgTypeSearch;
    private RadioButton rbOutletId;
    private RadioButton rbOutletName;

    static List<String> arrRegion, arrBranch, arrTypeOutlet;
    static List<tTypeOutletData> dtList;
    String selectedRegion, selectedBranch, selectedTypeOutlet, getLongitude, getLatitude, getAcc;
    private CharSequence keyword;
    Spinner spnRegion, spnBranch, spnTypeOutlet, spn_type_outlet_pop;
    AllOutletAdapter allOutletAdapter;
    RecyclerView recyclerView;
    clsMainActivity  clsMainActivity;
    private static int code = 0;

    private static View promptView = null ;
    private static  ViewGroup viewGroup = null;
    private static AlertDialog alertD = null;

    private tUserCheckinData _tUserCheckinData;

    GoogleMap googleMap;
    private String address;
    private String city;
    private String country;
    private TextView tvLocation;
    private GoogleApiClient client;

    private TableLayout tlPosition;
    private Button btnRefreshMaps, btnPopupMap;

    List<mBannerData> dtListdataBanner;
    private SliderLayout mDemoSlider;

    List<mBranchData> _mBranchData;
    tUserLoginData _tUserLoginData;

    int IndexSpinnerRegion, IndexSpinnerBranch = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_checkin_detail, container, false);
        et_search = (EditText) v.findViewById(R.id.et_searchData);

        clsMainActivity = new clsMainActivity();
        _tUserCheckinData = new tUserCheckinData();

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        promptView = layoutInflater.inflate(R.layout.popup_searchdata, null);
        recyclerView = (RecyclerView) promptView.findViewById(R.id.rvAllOtlet);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);

        dtList = new ArrayList<>();
        dtList = new clsMainBL().getDefaultList();

        initMenuMap();

            return v;
        }

    private void initMenuMap() {
        RelativeLayout rl_showMap = (RelativeLayout) v.findViewById(R.id.rl_showMap);
        final RelativeLayout rl_mnCheckinDetail = (RelativeLayout) v.findViewById(R.id.rl_mnCheckinDetail);

        rl_showMap.setVisibility(View.VISIBLE);
        rl_mnCheckinDetail.setVisibility(View.GONE);


        btn_getPosition = (Button) v.findViewById(R.id.btn_getPosition);
        btn_checkin = (Button) v.findViewById(R.id.btn_checkin);
        btnRefreshMaps = (Button) v.findViewById(R.id.btnRefreshMaps);
        btnPopupMap = (Button) v.findViewById(R.id.viewMap);

        getLocation();

        if(mLastLocation!=null){
            displayLocation(mLastLocation);
        }

        btnRefreshMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
                if(mLastLocation!=null){
                    displayLocation(mLastLocation);
                    Toast.makeText(getContext(), new clsHardCode().txtMessLocationUpdate, Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnPopupMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(_tUserCheckinData.get_txtLat()!=null&&_tUserCheckinData.get_txtLat()!=null){
                    LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                    final View promptView = layoutInflater.inflate(R.layout.popup_map, null);

                    GoogleMap mMap = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                        mMap = ((MapFragment) (getActivity()).getFragmentManager().findFragmentById(R.id.map)).getMap();

                        if (mMap == null) {
                            mMap = ((MapFragment) (getActivity()).getFragmentManager().findFragmentById(R.id.map)).getMap();
                        }

                        double latitude = Double.parseDouble(_tUserCheckinData.get_txtLat());
                        double longitude = Double.parseDouble(_tUserCheckinData.get_txtLong());

                        MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Your Location");

                        marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 16));

                        final LatLngBounds.Builder builder = new LatLngBounds.Builder();
                        builder.include(marker.getPosition());

                        mMap.clear();
                        mMap.addMarker(marker);
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                        alertDialogBuilder.setView(promptView);
                        alertDialogBuilder
                                .setCancelable(false)
                                .setPositiveButton("OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                MapFragment f = null;
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                                                    f = (MapFragment) (getActivity()).getFragmentManager().findFragmentById(R.id.map);
                                                }
                                                if (f != null) {
                                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                                                        (getActivity()).getFragmentManager().beginTransaction().remove(f).commit();
                                                    }
                                                }

                                                dialog.dismiss();
                                            }
                                        });
                        final AlertDialog alertD = alertDialogBuilder.create();
                        alertD.show();
                    }
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCheckGPS, false);
                }
            }
        });

        client = new GoogleApiClient.Builder(getContext()).addApi(AppIndex.API).build();

        if (checkPlayServices()) {
            buildGoogleApiClient();
        }

        btn_getPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(_tUserCheckinData.get_txtLat()!=null&&_tUserCheckinData.get_txtLat()!=null){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    builder.setMessage("Latitude :" + _tUserCheckinData.get_txtLat() + "\n" + "Longitude :" + _tUserCheckinData.get_txtLong() + "\n" + "Accuracy :" + _tUserCheckinData.get_txtAcc());
                    builder.setCancelable(false);

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            showCheckinGeoDetail();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessCheckGPS, false);
                }
            }
        });

        btn_checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dtList.size()>0){
                    if(!dtList.get(0).get_txtSumberDataID().equals("Please Insert Keyword")){
                        AsyncDataExist task = new AsyncDataExist();
                        task.execute();
                    } else {
                        new clsMainActivity().showCustomToast(getContext(), "Please Choose Outlet First...", false);
                    }
                } else {
                    new clsMainActivity().showCustomToast(getContext(), "Please Choose Outlet First...", false);
                }

            }
        });

        initBanner(getContext());
    }

    private void initBanner(Context context) {

        dtListdataBanner = new mBannerBL().GetAllData();

        mDemoSlider = (SliderLayout) v.findViewById(R.id.slider);

        final HashMap<String,String> url_maps = new HashMap<String, String>();

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
                                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessImageNotFound + name, false);
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

    public Location getLocation() {
        try {
            LocationManager locationManager = (LocationManager) getActivity()
                    .getSystemService(LOCATION_SERVICE);

            // getting GPS status
            boolean isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            boolean isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            boolean canGetLocation=false;
            Location location = null;

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessnoNetworkProviderIsEnabled, false );
            } else {
                canGetLocation = true;
                if (isNetworkEnabled) {
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    }
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            1000,
                            0, this);
                    Log.d("Network", "Network Enabled");
                    if (locationManager != null) {
                        mLastLocation = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();
                        }
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (mLastLocation == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                1000,
                                0, this);
                        Log.d("GPS", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                double latitude = location.getLatitude();
                                double longitude = location.getLongitude();
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mLastLocation;
    }

    private void initTablePosition(Context context, tUserCheckinData _tUserCheckinData) {
        tlPosition = (TableLayout) v.findViewById(R.id.tl_position);

        tlPosition.removeAllViews();

        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1f);
        params.setMargins(1, 1, 1, 1);

        TableRow tr = new TableRow(getContext());

        String[] colTextHeader = {"Position"};

        for (String text : colTextHeader) {
            TextView tv = new TextView(context);

            tv.setTextSize(14);
            tv.setPadding(10, 10, 10, 10);
            tv.setText(text);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundColor(Color.parseColor("#4CAF50"));
            tv.setTextColor(Color.WHITE);
            tv.setLayoutParams(params);

            tr.addView(tv);
        }
        tlPosition.addView(tr);

        if(_tUserCheckinData.get_txtLat()!=null){
            tr = new TableRow(context);

            String colname = "Latitude";
                TextView tv = new TextView(context);

                tv.setTextSize(12);
                tv.setPadding(10, 10, 10, 10);
                tv.setText(colname);
                tv.setGravity(Gravity.LEFT);
                tv.setBackgroundColor(Color.parseColor("#f0f0f0"));
                tv.setTextColor(Color.BLACK);
                tv.setLayoutParams(params);

                tr.addView(tv);

                TextView lat = new TextView(context);
                lat.setTextSize(12);
                lat.setPadding(10, 10, 10, 10);
                lat.setBackgroundColor(Color.parseColor("#f0f0f0"));
                lat.setTextColor(Color.BLACK);
                lat.setGravity(Gravity.LEFT);
                lat.setText(_tUserCheckinData.get_txtLat());
                lat.setLayoutParams(params);

                tr.addView(lat);

            tlPosition.addView(tr);
        }

        if(_tUserCheckinData.get_txtLong()!=null){
            tr = new TableRow(context);

            String colname = "Longitude";
            TextView tv = new TextView(context);

            tv.setTextSize(12);
            tv.setPadding(10, 10, 10, 10);
            tv.setText(colname);
            tv.setGravity(Gravity.LEFT);
            tv.setBackgroundColor(Color.parseColor("#f0f0f0"));
            tv.setTextColor(Color.BLACK);
            tv.setLayoutParams(params);

            tr.addView(tv);

            TextView lat = new TextView(context);
            lat.setTextSize(12);
            lat.setPadding(10, 10, 10, 10);
            lat.setBackgroundColor(Color.parseColor("#f0f0f0"));
            lat.setTextColor(Color.BLACK);
            lat.setGravity(Gravity.LEFT);
            lat.setText(_tUserCheckinData.get_txtLong());
            lat.setLayoutParams(params);

            tr.addView(lat);

            tlPosition.addView(tr);
        }

        if(_tUserCheckinData.get_txtAcc()!=null){
            tr = new TableRow(context);

            String colname = "Accuracy";
            TextView tv = new TextView(context);

            tv.setTextSize(12);
            tv.setPadding(10, 10, 10, 10);
            tv.setText(colname);
            tv.setGravity(Gravity.LEFT);
            tv.setBackgroundColor(Color.parseColor("#f0f0f0"));
            tv.setTextColor(Color.BLACK);
            tv.setLayoutParams(params);

            tr.addView(tv);

            TextView lat = new TextView(context);
            lat.setTextSize(12);
            lat.setPadding(10, 10, 10, 10);
            lat.setBackgroundColor(Color.parseColor("#f0f0f0"));
            lat.setTextColor(Color.BLACK);
            lat.setGravity(Gravity.LEFT);
            lat.setText(_tUserCheckinData.get_txtAcc());
            lat.setLayoutParams(params);

            tr.addView(lat);

            tlPosition.addView(tr);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Absen Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.kalbenutritionals.xteamnative/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Absen Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.kalbenutritionals.xteamnative/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SupportMapFragment mapFragment = ((SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map));

        if (mapFragment != null) {
            FragmentManager fM = getFragmentManager();
            fM.beginTransaction().remove(mapFragment).commit();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        SupportMapFragment mapFragment = ((SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map));

        if (mapFragment != null) {
            FragmentManager fM = getFragmentManager();
            fM.beginTransaction().remove(mapFragment).commit();

        }
    }

    private void showCheckinGeoDetail() {
        tlPosition.removeAllViews();
        RelativeLayout rl_showMap = (RelativeLayout) v.findViewById(R.id.rl_showMap);
        final RelativeLayout rl_mnCheckinDetail = (RelativeLayout) v.findViewById(R.id.rl_mnCheckinDetail);
        final RelativeLayout rl_typeOutletName = (RelativeLayout) v.findViewById(R.id.rl_typeoutletgrup);

        rl_showMap.setVisibility(View.GONE);
        rl_mnCheckinDetail.setVisibility(View.VISIBLE);

        et_longitude = (EditText) v.findViewById(R.id.et_long);
        et_latitude = (EditText) v.findViewById(R.id.et_lat);
        et_accuracy = (EditText) v.findViewById(R.id.et_acc);

        et_longitude.setText(_tUserCheckinData.get_txtLong());
        et_latitude.setText(_tUserCheckinData.get_txtLat());
        et_accuracy.setText(_tUserCheckinData.get_txtAcc());

        et_longitude.setEnabled(false);
        et_latitude.setEnabled(false);
        et_accuracy.setEnabled(false);

        rgTypeSearch = (RadioGroup) v.findViewById(R.id.rg_type_search);
        rbOutletId = (RadioButton) v.findViewById(R.id.rb_outletID);
        rbOutletName = (RadioButton) v.findViewById(R.id.rb_outletName);
        tvOutletType = (TextView) v.findViewById(R.id.tv_outletID);
        spnRegion = (Spinner) v.findViewById(R.id.spn_region);
        spnBranch = (Spinner) v.findViewById(R.id.spn_branch);
        spnTypeOutlet = (Spinner) v.findViewById(R.id.spn_type_outlet);
        et_search = (EditText) v.findViewById(R.id.et_searchData);
        tvOutletNameResullt = (TextView) v.findViewById(R.id.tv_outlet_name);
        btn_back = (Button) v.findViewById(R.id.btn_back);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initMenuMap();
            }
        });

        rgTypeSearch.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                dtList = new ArrayList<>();
                dtList = new clsMainBL().getDefaultList();

                if (rbOutletId.isChecked()){
                    tvOutletType.setText("Outlet ID");
                    rl_typeOutletName.setVisibility(View.GONE);
                    et_search.setInputType(InputType.TYPE_CLASS_NUMBER);
                    et_search.setText("");
                    et_search.setHint(dtList.get(0).get_txtSumberDataID());
                    tvOutletNameResullt.setText("Outlet Name :");
                } else if (rbOutletName.isChecked()){
                    tvOutletType.setText("Outlet Name");
                    rl_typeOutletName.setVisibility(View.VISIBLE);
                    et_search.setText("");
                    et_search.setHint(dtList.get(0).get_txtSumberDataID());
                    tvOutletNameResullt.setText("Outlet Name :");
                    et_search.setInputType(InputType.TYPE_CLASS_TEXT);
                    AsyncCallTypeOutlet task = new AsyncCallTypeOutlet();
                    task.execute();
                }
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

        spnBranch.setAdapter(new MyAdapterBranch(getContext(), R.layout.custom_spinner, arrBranch));

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
                et_search.setText("");

                dtList = new ArrayList<>();
                dtList = new clsMainBL().getDefaultList();
                et_search.setHint(dtList.get(0).get_txtSumberDataID());
                tvOutletNameResullt.setText("Outlet Name :");

                arrBranch = new ArrayList<>();
                arrBranch = new clsMainActivity().getArrayBranchByRegion(selectedRegion);

                if (!selectedRegion.equalsIgnoreCase(_tUserLoginData.get_txtRegionMenu())){
                    spnBranch.setAdapter(new MyAdapterBranch(getContext(), R.layout.custom_spinner, arrBranch));
                } else {
                    spnBranch.setAdapter(new MyAdapterBranch(getContext(), R.layout.custom_spinner, arrBranch));
                    spnBranch.setSelection(IndexSpinnerBranch);
                }

                if (rbOutletName.isChecked()){
                    spnBranch.setAdapter(new MyAdapterBranch(getContext(), R.layout.custom_spinner, arrBranch));
                    spnBranch.setSelection(IndexSpinnerBranch);
                    new AsyncCallTypeOutlet().execute();
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
                tvOutletNameResullt.setText("Outlet Name : " );
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spnTypeOutlet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedTypeOutlet = spnTypeOutlet.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et_search.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(et_search) {
            @Override
            public boolean onDrawableClick() {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(et_search.getWindowToken(), 0);
                keyword = et_search.getText();
                if(rbOutletId.isChecked()){
                    if (keyword.length()>=3){
                        code = 1;
                        AsyncCallSumberData task = new AsyncCallSumberData();
                        task.execute();
                    } else {
                        new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessMinChar, false);
                    }
                } else if(rbOutletName.isChecked()){
                    if (keyword.length()>0){
                        code = 2;
                        AsyncCallSumberData task = new AsyncCallSumberData();
                        task.execute();
                    } else {
                        new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessChar, false);
                    }
                }

                return true;
            }
        });

        et_search.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(et_search.getWindowToken(), 0);
                    keyword = et_search.getText();
                    if(rbOutletId.isChecked()){
                        if (keyword.length()>=3){
                            code = 1;
                            AsyncCallSumberData task = new AsyncCallSumberData();
                            task.execute();
                        } else {
                            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessMinChar, false);
                        }
                    } else if(rbOutletName.isChecked()){
                        if (keyword.length()>0){
                            code = 2;
                            AsyncCallSumberData task = new AsyncCallSumberData();
                            task.execute();
                        } else {
                            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessChar, false);
                        }
                    }

                    return true;
                }
                return false;
            }
        });
    }

    public boolean showPopUpSearch(Boolean open, final Context context, List<tTypeOutletData> data) {

        if (open == true){

            allOutletAdapter = new AllOutletAdapter(dtList, getContext());
            recyclerView.setAdapter(allOutletAdapter);

            et_search.setText("");
            et_search.setHint("");
            final TextView tv_region = (TextView) promptView.findViewById(R.id.tv_region_pop);
            final  TextView tv_branch = (TextView) promptView.findViewById(R.id.tv_branch_pop);
            final RadioGroup rg_typesearchpop = (RadioGroup) promptView.findViewById(R.id.rg_type_search_pop);
            final RadioButton rb_outletIDpop = (RadioButton) promptView.findViewById(R.id.rb_outletID_pop);
            final RadioButton rb_outletNamePop = (RadioButton) promptView.findViewById(R.id.rb_outletName_pop);
            final RelativeLayout rl_typeOutletNamePop = (RelativeLayout) promptView.findViewById(R.id.rl_typeoutletgrup_pop);
            final TextView tv_type_outlet_pop = (TextView) promptView.findViewById(R.id.tv_outletID_pop);
            spn_type_outlet_pop = (Spinner) promptView.findViewById(R.id.spn_type_outlet_pop);
            final EditText et_searchPopUp = (EditText) promptView.findViewById(R.id.et_searchDataPop);
            final View v1 = (View) promptView.findViewById(R.id.view1);
            final View v2 = (View) promptView.findViewById(R.id.view2);
            final View v3 = (View) promptView.findViewById(R.id.view3);
            final View v4 = (View) promptView.findViewById(R.id.view4);
            final TextView tv_typesearch = (TextView) promptView.findViewById(R.id.tv_type_search);
            final TextView tv_typeoutlet = (TextView) promptView.findViewById(R.id.tv_type_outlet_pop);
            final Spinner spn_outlet = (Spinner) promptView.findViewById(R.id.spn_type_outlet_pop);
            final RelativeLayout rl_text = (RelativeLayout) promptView.findViewById(R.id.relativeLayout);



            tv_region.setVisibility(View.GONE);
            tv_branch.setVisibility(View.GONE);
            rg_typesearchpop.setVisibility(View.GONE);
            rb_outletIDpop.setVisibility(View.GONE);
            rb_outletNamePop.setVisibility(View.GONE);
            rl_typeOutletNamePop.setVisibility(View.GONE);
            rl_text.setVisibility(View.GONE);
            tv_type_outlet_pop.setVisibility(View.GONE);
            et_searchPopUp.setVisibility(View.GONE);
            v1.setVisibility(View.GONE);
            v2.setVisibility(View.GONE);
            v3.setVisibility(View.GONE);
            v4.setVisibility(View.GONE);
            tv_typesearch.setVisibility(View.GONE);
            tv_typeoutlet.setVisibility(View.GONE);
            spn_outlet.setVisibility(View.GONE);

            rg_typesearchpop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (rb_outletIDpop.isChecked()){
                        tv_type_outlet_pop.setText("Outlet ID");
                        rl_typeOutletNamePop.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.GONE);
                        et_searchPopUp.setText("");
                        et_searchPopUp.setInputType(InputType.TYPE_CLASS_NUMBER);

                    } else if (rb_outletNamePop.isChecked()){
                        et_searchPopUp.setText("");
                        et_searchPopUp.setInputType(InputType.TYPE_CLASS_TEXT);
                        tv_type_outlet_pop.setText("Outlet Name");
                        rl_typeOutletNamePop.setVisibility(View.VISIBLE);
                    }


                }
            });

            spn_type_outlet_pop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    selectedTypeOutlet = spn_type_outlet_pop.getSelectedItem().toString();

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            int selectedID = rgTypeSearch.getCheckedRadioButtonId();
            RadioButton radioFlag = (RadioButton) v.findViewById(selectedID);
            String rb_type = radioFlag.getText().toString();
            if (rb_type.equals("Outlet Name")){
                rb_outletNamePop.setChecked(true);
                rl_typeOutletNamePop.setVisibility(View.VISIBLE);
                tv_type_outlet_pop.setText("Outlet ID");

            } else {
                rb_outletIDpop.setChecked(true);
                tv_type_outlet_pop.setText("Outlet Name");
            }

            tv_region.setText(selectedRegion);
            tv_branch.setText(selectedBranch);

            if(arrTypeOutlet!=null){
                spn_type_outlet_pop.setAdapter(new MyAdapterTypeOutlet(getContext(), R.layout.custom_spinner, arrTypeOutlet));
            }

            et_searchPopUp.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(et_searchPopUp) {
                @Override
                public boolean onDrawableClick() {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(et_searchPopUp.getWindowToken(), 0);
                    keyword = et_searchPopUp.getText();
                    if(rb_outletIDpop.isChecked()){
                        if (keyword.length()>=3){
                            code = 1;
                            AsyncCallSumberData task = new AsyncCallSumberData();
                            task.execute();
                        } else {
                            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessMinChar, false);
                        }
                    } else if(rb_outletNamePop.isChecked()){
                        if (keyword.length()>0){
                            code = 2;
                            AsyncCallSumberData task = new AsyncCallSumberData();
                            task.execute();
                        } else {
                            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessChar, false);
                        }
                    }

                    return true;
                }
            });

            et_searchPopUp.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if(event.getAction()==KeyEvent.ACTION_DOWN){
                        if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER ||
                                keyCode == KeyEvent.KEYCODE_ENTER){
                            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(et_searchPopUp.getWindowToken(), 0);
                            keyword = et_searchPopUp.getText();
                            if(rb_outletIDpop.isChecked()){
                                if (keyword.length()>=3){
                                    code = 1;
                                    AsyncCallSumberData task = new AsyncCallSumberData();
                                    task.execute();
                                } else {
                                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessMinChar, false);
                                }
                            } else if(rb_outletNamePop.isChecked()){
                                if (keyword.length()>0){
                                    code = 2;
                                    AsyncCallSumberData task = new AsyncCallSumberData();
                                    task.execute();
                                } else {
                                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessChar, false);
                                }
                            }

                            return true;
                        }
                    }

                    return false;
                }
            });

            et_searchPopUp.setOnEditorActionListener(new EditText.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(et_searchPopUp.getWindowToken(), 0);
                        keyword = et_searchPopUp.getText();
                        if(rb_outletIDpop.isChecked()){
                            if (keyword.length()>=3){
                                code = 1;
                                AsyncCallSumberData task = new AsyncCallSumberData();
                                task.execute();
                            } else {
                                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessMinChar, false);
                            }
                        } else if(rb_outletNamePop.isChecked()){
                            if (keyword.length()>0){
                                code = 2;
                                AsyncCallSumberData task = new AsyncCallSumberData();
                                task.execute();
                            } else {
                                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessChar, false);
                            }
                        }

                        return true;
                    }
                    return false;
                }
            });

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setView(promptView);
            alertDialogBuilder
                    .setCancelable(false)

                    .setNegativeButton("Close",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    ViewGroup viewGroup = (ViewGroup) promptView.getParent();
                                    viewGroup.removeView(promptView);
                                    dialog.cancel();

                                }
                            });
            alertD = alertDialogBuilder.create();
            alertD.show();

        }

        if (open == false){

            dtList = new ArrayList<>();
            dtList = data;
            et_search.setHint("Outlet ID  : " + data.get(0).get_txtSumberDataID());
            tvOutletNameResullt.setText("Outlet Name : " + data.get(0).get_txtNamaInstitusi());
            tvOutletNameResullt.setTextColor(Color.BLACK);
            viewGroup = (ViewGroup) promptView.getParent();
            viewGroup.removeView(promptView);
            alertD.dismiss();
            return true;
        }

        return true;
    }

    @SuppressWarnings("deprecation")
    private void displayLocation(Location mLastLocation) {

        if (mLastLocation != null) {
            double latitude = mLastLocation.getLatitude();
            double longitude = mLastLocation.getLongitude();
            double accurate = mLastLocation.getAccuracy();

            _tUserCheckinData = new tUserCheckinData();

            _tUserCheckinData.set_txtLat(String.valueOf(latitude).toString());
            _tUserCheckinData.set_txtLong(String.valueOf(longitude).toString());
            _tUserCheckinData.set_txtAcc(String.valueOf(accurate).toString());

            getLongitude = String.valueOf(longitude);
            getLatitude = String.valueOf(latitude);
            getAcc = String.valueOf(accurate);
            initTablePosition(getContext(), _tUserCheckinData);
        }

    }


    private void buildGoogleApiClient() {
        // TODO Auto-generated method stub
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();

    }

    @SuppressWarnings("deprecation")
    private boolean checkPlayServices() {
        // TODO Auto-generated method stub
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getContext());
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, getActivity(), PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
            }
            return false;
        }
        return true;
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {
        // TODO Auto-generated method stub
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = "
                + result.getErrorCode());

    }

    @Override
    public void onConnected(@Nullable Bundle arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onConnectionSuspended(int arg0) {
        // TODO Auto-generated method stub
        mGoogleApiClient.connect();

    }

    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

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

    private class AsyncCallTypeOutlet extends AsyncTask<JSONArray, Void, JSONArray> {

        @Override
        protected JSONArray doInBackground(JSONArray... params) {
//            android.os.Debug.waitForDebugger();
            JSONArray Json = null;
            try {
                Json = new clsMainBL().getTypeOutletByBranch(selectedBranch);
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
        protected void onPostExecute(JSONArray branchdata) {
            if(branchdata!=null){
                if (branchdata.size() > 0) {
                    Iterator i = branchdata.iterator();

                    arrTypeOutlet = new ArrayList<String>();

                    while (i.hasNext()) {
                        JSONObject innerObj = (JSONObject) i.next();
                        Long IntResult = (Long) innerObj.get("_pboolValid");

                        if (IntResult == 1) {
                            arrTypeOutlet.add((String) innerObj.get("tipeSumberData").toString());
                        } else if (IntResult == 0){
                            arrTypeOutlet.add((String) "-");
                        }
                    }

                    spnTypeOutlet.setAdapter(new MyAdapterTypeOutlet(getContext(), R.layout.custom_spinner, arrTypeOutlet));

                } else {
                    arrTypeOutlet = new ArrayList<String>();
                    arrTypeOutlet.add((String) "-");
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                    spnTypeOutlet.setAdapter(new MyAdapterTypeOutlet(getContext(), R.layout.custom_spinner, arrTypeOutlet));
                }
            } else {
                arrTypeOutlet = new ArrayList<String>();
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessUnableToConnect, false);
                arrTypeOutlet.add((String) "-");
                spnTypeOutlet.setAdapter(new MyAdapterTypeOutlet(getContext(), R.layout.custom_spinner, arrTypeOutlet));
            }

            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            Dialog.setMessage("Getting Data Type Outlet");
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

    private class AsyncCallSumberData extends AsyncTask<JSONArray, Void, JSONArray> {

        @Override
        protected JSONArray doInBackground(JSONArray... params) {
//            android.os.Debug.waitForDebugger();

            JSONArray Json = null;
            if (code == 1){
                try {
                    Json = new clsMainBL().getDataSumberDataforWebDashboard("", selectedBranch, keyword);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else if (code == 2){
                try {
                    Json = new clsMainBL().getDataSumberDataforWebDashboard(selectedBranch, selectedTypeOutlet, keyword);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
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
            if (regiondata!=null){
                if (regiondata.size()!=0 && regiondata.size() > 0 &&code == 1) {
                    Iterator i = regiondata.iterator();

                    dtList = new ArrayList<>();

                    while (i.hasNext()) {
                        JSONObject innerObj = (JSONObject) i.next();
                        Long IntResult = (Long) innerObj.get("_pboolValid");

                        if (IntResult == 1) {
                            String sumberDataID = (String) innerObj.get("txtSumberDataID").toString();
                            String tipeSumberData = (String) innerObj.get("tipeSumberData").toString();
                            String namaInstitusi = (String) innerObj.get("txtNamaInstitusi").toString();
                            String namaPropinsi = (String) innerObj.get("txtNamaPropinsi").toString();
                            String alamat = (String) innerObj.get("txtAlamat").toString();
                            String telepon = (String) innerObj.get("txtTelepon").toString();
                            String kabKota = (String) innerObj.get("txtNamaKabKota").toString();

                            dtList = new ArrayList<tTypeOutletData>();

                            dtList.add(new tTypeOutletData(sumberDataID, tipeSumberData, namaInstitusi, namaPropinsi, alamat, telepon, kabKota));

                            _tUserCheckinData.set_txtOutletId(sumberDataID);
                            _tUserCheckinData.set_txtTypeOutlet(tipeSumberData);
                            _tUserCheckinData.set_txtOutletName(namaInstitusi);

                            et_search.setHint("Outlet ID  : " + sumberDataID);
                            tvOutletNameResullt.setText("Outlet Name : " + namaInstitusi);
                            tvOutletNameResullt.setTextColor(Color.BLACK);

                            if (promptView != null){
                                viewGroup = (ViewGroup) promptView.getParent();
                                if (viewGroup != null){
                                    viewGroup.removeView(promptView);
                                    alertD.dismiss();
                                }
                            }

                            et_search.setText("");
                            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(et_search.getWindowToken(), 0);

                        } else {
                            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                            dtList = new ArrayList<>();
                            dtList = new clsMainBL().getDefaultList();
                            et_search.setHint(dtList.get(0).get_txtSumberDataID());
                            tvOutletNameResullt.setText("Outlet Name :");
                        }
                    }
                }
                else if (regiondata.size() > 0&&code==2){
                    Iterator i = regiondata.iterator();

                    dtList = new ArrayList<>();

                    while (i.hasNext()) {
                        JSONObject innerObj = (JSONObject) i.next();
                        Long IntResult = (Long) innerObj.get("_pboolValid");

                        if (IntResult == 1) {
                            String sumberDataID = (String) innerObj.get("txtSumberDataID").toString();
                            String tipeSumberData = (String) innerObj.get("tipeSumberData").toString();
                            String namaInstitusi = (String) innerObj.get("txtNamaInstitusi").toString();
                            String namaPropinsi = (String) innerObj.get("txtNamaPropinsi").toString();
                            String alamat = (String) innerObj.get("txtAlamat").toString();
                            String telepon = (String) innerObj.get("txtTelepon").toString();
                            String kabKota = (String) innerObj.get("txtNamaKabKota").toString();
                            dtList.add(new tTypeOutletData(sumberDataID, tipeSumberData, namaInstitusi, namaPropinsi, alamat, telepon, kabKota));
                            Boolean codec = true;

                            ViewGroup viewGroup = (ViewGroup) promptView.getParent();
                            if(viewGroup!=null){
                                viewGroup.removeView(promptView);
                                alertD.dismiss();
                            }
                            List<tTypeOutletData> data=null;
                            showPopUpSearch(codec, getContext(), data);
                        } else {
                            new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                            tvOutletNameResullt.setText("Outlet Name :");
                            dtList = new ArrayList<>();
                            dtList = new clsMainBL().getDefaultList();
                            et_search.setHint(dtList.get(0).get_txtSumberDataID());
                        }
                    }
                } else {
                    new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                }
            } else {
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessUnableToConnect, false);
            }

            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
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

    private class AsyncDataExist extends AsyncTask<JSONArray, Void, JSONArray> {

        @Override
        protected JSONArray doInBackground(JSONArray... params) {
//            android.os.Debug.waitForDebugger();

            JSONArray Json = null;
                try {
                    Json = new clsMainBL().getDataExistsInmGeolocationOutlet(dtList.get(0).get_txtSumberDataID(), selectedBranch);
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
            if (array.size()!=0 && array.size() > 0) {
                Iterator i = array.iterator();

                while (i.hasNext()) {
                    JSONObject innerObj = (JSONObject) i.next();
                    Long IntResult = (Long) innerObj.get("_pboolValid");

                    if (IntResult == 1) {
                        String message = (String) innerObj.get("_pstrMessage").toString();
                        String messageFinal = message.replaceAll("@enter@", "\n");

                        final AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());

                        builder1.setTitle("Confirmation");
                        builder1.setMessage(messageFinal);

                        builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {

                                String message = "Are you sure to checkin?";
                                String messageFinal = message.replaceAll("@enter@", "\n");

                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                                builder.setTitle("Confirmation");
                                builder.setMessage(message);

                                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int which) {
                                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        UUID uuid = UUID.randomUUID();
                                        String randomUUIDString = uuid.toString();
                                        Calendar cal = Calendar.getInstance();
                                        tUserLoginData userActive = new tUserLoginBL().getUserActive();
                                        _tUserCheckinData.set_UuId(randomUUIDString);
                                        _tUserCheckinData.set_txtUserID(userActive.get_txtUserId());
                                        _tUserCheckinData.set_txtUserName(userActive.get_txtUserName());
                                        _tUserCheckinData.set_txtGType("Android");
                                        _tUserCheckinData.setDtCheckin(dateFormat.format(cal.getTime()));
                                        _tUserCheckinData.set_txtRegionName(selectedRegion);
                                        _tUserCheckinData.set_txtTypeOutlet(selectedTypeOutlet);
                                        _tUserCheckinData.set_txtOutletId(dtList.get(0).get_txtSumberDataID());
                                        _tUserCheckinData.set_txtOutletName(dtList.get(0).get_txtNamaInstitusi());
                                        _tUserCheckinData.set_TxtAlamat(dtList.get(0).get_txtAlamat());
                                        _tUserCheckinData.set_TxtNamaPropinsi(dtList.get(0).get_txtNamaPropinsi());
                                        _tUserCheckinData.set_TxtNamaKabKota(dtList.get(0).get_txtNamaPropinsi());
                                        _tUserCheckinData.set_txtBranchName(selectedBranch);
                                        _tUserCheckinData.set_txtLong(getLongitude);
                                        _tUserCheckinData.set_txtLat(getLatitude);
                                        _tUserCheckinData.set_txtAcc(getAcc);
                                        _tUserCheckinData.set_intSubmit("1");
                                        _tUserCheckinData.set_intSync("0");
                                        _tUserCheckinData.set_intFlag("0");

                                        new tUserCheckinBL().saveData(_tUserCheckinData);
                                        startNextScreen();
                                        dialog.dismiss();

                                    }
                                });

                                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });

                                AlertDialog alert = builder.create();
                                alert.show();
                            }
                        });

                        builder1.setNegativeButton("No", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String message = "Are you sure to checkin?";
                                String messageFinal = message.replaceAll("@enter@", "\n");

                                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                                builder.setTitle("Confirmation");
                                builder.setMessage(message);

                                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int which) {
                                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        UUID uuid = UUID.randomUUID();
                                        String randomUUIDString = uuid.toString();
                                        Calendar cal = Calendar.getInstance();
                                        tUserLoginData userActive = new tUserLoginBL().getUserActive();
                                        _tUserCheckinData.set_UuId(randomUUIDString);
                                        _tUserCheckinData.set_txtUserID(userActive.get_txtUserId());
                                        _tUserCheckinData.set_txtUserName(userActive.get_txtUserName());
                                        _tUserCheckinData.set_txtGType("Android");
                                        _tUserCheckinData.setDtCheckin(dateFormat.format(cal.getTime()));
                                        _tUserCheckinData.set_txtRegionName(selectedRegion);
                                        _tUserCheckinData.set_txtTypeOutlet(selectedTypeOutlet);
                                        _tUserCheckinData.set_txtOutletId(dtList.get(0).get_txtSumberDataID());
                                        _tUserCheckinData.set_txtOutletName(dtList.get(0).get_txtNamaInstitusi());
                                        _tUserCheckinData.set_TxtAlamat(dtList.get(0).get_txtAlamat());
                                        _tUserCheckinData.set_TxtNamaPropinsi(dtList.get(0).get_txtNamaPropinsi());
                                        _tUserCheckinData.set_TxtNamaKabKota(dtList.get(0).get_txtNamaPropinsi());
                                        _tUserCheckinData.set_txtBranchName(selectedBranch);
                                        _tUserCheckinData.set_txtLong(getLongitude);
                                        _tUserCheckinData.set_txtLat(getLatitude);
                                        _tUserCheckinData.set_txtAcc(getAcc);
                                        _tUserCheckinData.set_intSubmit("1");
                                        _tUserCheckinData.set_intSync("0");
                                        _tUserCheckinData.set_intFlag("1");

                                        new tUserCheckinBL().saveData(_tUserCheckinData);
                                        startNextScreen();
                                        dialog.dismiss();

                                    }
                                });

                                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                AlertDialog alert = builder.create();
                                alert.show();
                            }
                        });

                        AlertDialog alert = builder1.create();
                        alert.show();

                    }
                    else if (IntResult==0){
//                        new clsMainActivity().showCustomToast(getContext(), "No Data Exist", false);
                        String message = "No Data Exist" + "\n" + "Are you sure to checkin?";

                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                        builder.setTitle("Confirmation");
                        builder.setMessage(message);

                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                UUID uuid = UUID.randomUUID();
                                String randomUUIDString = uuid.toString();
                                Calendar cal = Calendar.getInstance();
                                tUserLoginData userActive = new tUserLoginBL().getUserActive();
                                _tUserCheckinData.set_UuId(randomUUIDString);
                                _tUserCheckinData.set_txtUserID(userActive.get_txtUserId());
                                _tUserCheckinData.set_txtUserName(userActive.get_txtUserName());
                                _tUserCheckinData.set_txtGType("Android");
                                _tUserCheckinData.setDtCheckin(dateFormat.format(cal.getTime()));
                                _tUserCheckinData.set_txtRegionName(selectedRegion);
                                _tUserCheckinData.set_txtTypeOutlet(selectedTypeOutlet);
                                _tUserCheckinData.set_txtOutletId(dtList.get(0).get_txtSumberDataID());
                                _tUserCheckinData.set_txtOutletName(dtList.get(0).get_txtNamaInstitusi());
                                _tUserCheckinData.set_TxtAlamat(dtList.get(0).get_txtAlamat());
                                _tUserCheckinData.set_TxtNamaPropinsi(dtList.get(0).get_txtNamaPropinsi());
                                _tUserCheckinData.set_TxtNamaKabKota(dtList.get(0).get_txtNamaPropinsi());
                                _tUserCheckinData.set_txtBranchName(selectedBranch);
                                _tUserCheckinData.set_txtLong(getLongitude);
                                _tUserCheckinData.set_txtLat(getLatitude);
                                _tUserCheckinData.set_txtAcc(getAcc);
                                _tUserCheckinData.set_intSubmit("1");
                                _tUserCheckinData.set_intSync("0");
                                _tUserCheckinData.set_intFlag("1");

                                new tUserCheckinBL().saveData(_tUserCheckinData);
                                startNextScreen();
                                dialog.dismiss();
                                return;

                            }
                        });

                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        AlertDialog alert = builder.create();
                        alert.show();

                    }
                    else

                    {
                        new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
                    }
                }
            } else {
                new clsMainActivity().showCustomToast(getContext(), new clsHardCode().txtMessDataNotFound, false);
            }
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            Dialog.setMessage("Please wait... Getting Data Exist");
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

    private void startNextScreen() {
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("History Checkin");

        FragmentHistoryCheckin fragmentFragmentHistoryCheckin = new FragmentHistoryCheckin();
        FragmentTransaction fragmentTransactionFragmentHistoryCheckin = getFragmentManager().beginTransaction();
        fragmentTransactionFragmentHistoryCheckin.replace(R.id.frame, fragmentFragmentHistoryCheckin);
        fragmentTransactionFragmentHistoryCheckin.commit();
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
            row.setBackgroundColor(new Color().TRANSPARENT);
            return row;
        }

    }

    public class MyAdapterTypeOutlet extends ArrayAdapter<String> {
        public MyAdapterTypeOutlet(Context context, int textViewResourceId, List<String> objects) {
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
            label.setText(arrTypeOutlet.get(position));
            TextView sub = (TextView) row.findViewById(R.id.tvDesc);
            sub.setVisibility(View.INVISIBLE);
            sub.setVisibility(View.GONE);
            row.setBackgroundColor(new Color().TRANSPARENT);
            return row;
        }
    }
}







