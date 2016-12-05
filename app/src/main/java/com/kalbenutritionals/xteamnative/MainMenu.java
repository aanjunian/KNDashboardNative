package com.kalbenutritionals.xteamnative;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.theartofdev.edmodo.cropper.CropImage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bl.clsHelperBL;
import bl.mMenuBL;
import bl.tAbsenUserBL;
import bl.tDisplayPictureBL;
import bl.tNotificationBL;
import bl.tUserLoginBL;
import come.example.viewbadger.ShortcutBadger;
import de.hdodenhof.circleimageview.CircleImageView;
import library.common.clsPushData;
import library.common.mMenuData;
import library.common.tAbsenUserData;
import library.common.tDisplayPictureData;
import library.common.tNotificationData;
import library.common.tUserLoginData;
import library.dal.clsHardCode;
import service.MyServiceNative;

public class MainMenu extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private tAbsenUserBL _tAbsenUserBL;
    private tAbsenUserData dttAbsenUserData;
    private tAbsenUserData dtAbsens;

    private TextView tvUsername, tvEmail;
    private CircleImageView ivProfile;
    private tDisplayPictureData tDisplayPictureData;

    PackageInfo pInfo = null;

    int selectedId;
    private static int menuId = 0;
    Boolean isSubMenu = false;

    clsMainActivity _clsMainActivity = new clsMainActivity();

    String[] listMenu;
    String[] linkMenu;

    private GoogleApiClient client;

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Exit");
        builder.setMessage("Do you want to exit?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                finish();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedId = 0;

        try {
            pInfo = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Intent serviceIntentMyServiceNative = new Intent(getApplicationContext(), MyServiceNative.class);
        getApplicationContext().startService(serviceIntentMyServiceNative);

        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);

        FragmentHome homeFragment = new FragmentHome();
        FragmentTransaction fragmentTransactionHome = getSupportFragmentManager().beginTransaction();
        fragmentTransactionHome.replace(R.id.frame, homeFragment);
        fragmentTransactionHome.commit();
        selectedId = 99;

        tUserLoginData dt = new tUserLoginBL().getUserActive();

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View vwHeader = navigationView.getHeaderView(0);

        ivProfile = (CircleImageView) vwHeader.findViewById(R.id.profile_image);
        tvUsername = (TextView) vwHeader.findViewById(R.id.username);
        tvEmail = (TextView) vwHeader.findViewById(R.id.email);
        tvUsername.setText("Welcome, " + dt.get_txtUserName());
        tvEmail.setText(dt.get_txtEmail());

        tDisplayPictureData = new tDisplayPictureBL().getData();

        if (tDisplayPictureData.get_image() != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(tDisplayPictureData.get_image(), 0, tDisplayPictureData.get_image().length);
            ivProfile.setImageBitmap(bitmap);
        } else {
            ivProfile.setImageBitmap(BitmapFactory.decodeResource(getApplicationContext().getResources(),
                    R.drawable.profile));
        }

        ivProfile.setOnClickListener(this);

        dtAbsens = new tAbsenUserBL().getDataCheckInActive();
        Menu header = navigationView.getMenu();

        Intent intent = getIntent();
        String i_view = intent.getStringExtra("key_view");

        int statusAbsen = 0;
        int menuActive = 0;

        if (dtAbsens == null) {
            menuActive = R.id.groupListMenu;

            if (i_view != null){

                if (i_view.equals("CheckinGeolocation")){
                    Class<?> fragmentClass = null;
                    try {
                        fragmentClass = Class.forName("com.kalbenutritionals.xteamnative.Fragment" + i_view);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    toolbar.setTitle("Checkin Geolocation");
                    Fragment fragment = null;
                    try {
                        fragment = (Fragment) fragmentClass.newInstance();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, fragment);
                    fragmentTransaction.commit();
                    selectedId = 99;
                } else {
                    try {
                        Class<?> fragmentClass = Class.forName("com.kalbenutritionals.app.kalbespgmobile.Fragment" + i_view.replaceAll("\\s+", "") + "SPG");
                        try {
                            for (int i = 0; i < listMenu.length; i++) {
                                if (("View " + listMenu[i]).equals(i_view + " SPG")) {
                                    selectedId = i;
                                    break;
                                }
                            }
                            toolbar.setTitle(i_view);
                            Fragment fragment = (Fragment) fragmentClass.newInstance();
                            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.frame, fragment);
                            fragmentTransaction.commit();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            header.removeItem(R.id.logout);
            mMenuData data = new mMenuBL().getMenuDataByMenuName("mnAbsenSPG");
            menuId = Integer.parseInt(data.get_IntMenuID());
            statusAbsen = menuId;
            menuActive = R.id.groupListMenu1;

            List<mMenuData> menu = new mMenuBL().getDatabyParentId(statusAbsen);
            listMenu = new String[menu.size()];

            for (int i = 0; i < menu.size(); i++) {
                listMenu[i] = menu.get(i).get_TxtMenuName();
            }
        }

        List<mMenuData> menu;

        if (dtAbsens == null) {
            menu = new mMenuBL().getDatabyParentId(0);
        } else {
            menu = new mMenuBL().getDatabyParentId(211);
        }

        linkMenu = new String[menu.size()];
        listMenu = new String[menu.size()];

        if (menu != null) {
            for (int i = 0; i < menu.size(); i++) {

                int resId = getResources().getIdentifier(String.valueOf(menu.get(i).get_TxtDescription().toLowerCase()), "drawable", MainMenu.this.getPackageName());
                Drawable icon = MainMenu.this.getResources().getDrawable(resId);

                header.add(menuActive, i, 1, menu.get(i).get_TxtMenuName()).setIcon(icon).setCheckable(true);

                linkMenu[i] = menu.get(i).get_TxtLink();
                listMenu[i] = menu.get(i).get_TxtMenuName();
            }
        }

//        TextView view = (TextView) navigationView.getMenu().findItem(R.id.home).getActionView();
//        view.setText("99");
            if (i_view!=null){
                if (i_view.equals("View Reso")){
                    navigationView.getMenu().findItem(0).setChecked(true);
                } else if (i_view.equals("View Actvity")){
                    navigationView.getMenu().findItem(1).setChecked(true);
                } else if (i_view.equals("View Customer Base")){
                    navigationView.getMenu().findItem(2).setChecked(true);
                } else if (i_view.equals("Notifcation")){
//                    navigationView.getMenu().findItem(R.id.information).setChecked(true);
                }
            }


//        TextView view = (TextView) navigationView.getMenu().findItem(R.id.information).getActionView();
//        List<tNotificationData> ListData=new tNotificationBL().getAllDataWillAlert("2");
//        view.setText("1");

        SubMenu subMenuVersion = header.addSubMenu(R.id.groupVersion, 0, 3, "Version");
        try {
            subMenuVersion.add(getPackageManager().getPackageInfo(getPackageName(), 0).versionName + " \u00a9 KN-IT").setIcon(R.drawable.ic_android).setEnabled(false);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                menuItem.setChecked(true);

                drawerLayout.closeDrawers();

                Fragment fragment = null;

                switch (menuItem.getItemId()) {

                    case R.id.home:
                        toolbar.setTitle("Home");

                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);

                        FragmentHome homeFragment = new FragmentHome();
                        FragmentTransaction fragmentTransactionHome = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionHome.replace(R.id.frame, homeFragment);
                        fragmentTransactionHome.commit();

                        return true;

                    case R.id.reward:
                        toolbar.setTitle("Reward");

                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);

                        FragmentReward fragmentNotifcation = new FragmentReward();
                        FragmentTransaction fragmentTransactionNotifcation = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionNotifcation.replace(R.id.frame, fragmentNotifcation);
                        fragmentTransactionNotifcation.commit();

                        return  true;
                    case R.id.banner:
                        toolbar.setTitle("Banner");

                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);

                        FragmentBanner fragmentBanner = new FragmentBanner();
                        FragmentTransaction fragmentTransactionBanner = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionBanner.replace(R.id.frame, fragmentBanner);
                        fragmentTransactionBanner.commit();

                        return  true;
                    case R.id.checkinGeo:
                        toolbar.setTitle("Checkin Geolocation");

                        FragmentCheckinGeolocation fragmentCheckinGeolocation = new FragmentCheckinGeolocation();
                        FragmentTransaction fragmentTransactionCheckinGeolocation = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionCheckinGeolocation.replace(R.id.frame, fragmentCheckinGeolocation);
                        fragmentTransactionCheckinGeolocation.commit();
                        return  true;
                    case R.id.pushData:
                        toolbar.setTitle("Push Data");

                        FragmentPushData fragmentFragmentPushData = new FragmentPushData();
                        FragmentTransaction fragmentTransactionFragmentPushData = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionFragmentPushData.replace(R.id.frame, fragmentFragmentPushData);
                        fragmentTransactionFragmentPushData.commit();
                        return  true;
                    case R.id.historyCheckin:
                        toolbar.setTitle("History Checkin");

                        FragmentHistoryCheckin fragmentHistoryCheckin = new FragmentHistoryCheckin();
                        FragmentTransaction fragmentTransactionHistoryCheckin = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionHistoryCheckin.replace(R.id.frame, fragmentHistoryCheckin);
                        fragmentTransactionHistoryCheckin.commit();
                        return  true;

                    case R.id.report:
                        toolbar.setTitle("Report");

                        FragmentReport fragmentFragmentReport = new FragmentReport();
                        FragmentTransaction fragmentTransactionFragmentReport = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionFragmentReport.replace(R.id.frame, fragmentFragmentReport);
                        fragmentTransactionFragmentReport.commit();
                        return  true;
                    case R.id.logout:
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainMenu.this);

                        builder.setTitle("Exit");
                        builder.setMessage("Do you want to exit?");

                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                new clsHelperBL().DeleteAllDB();
                                finish();
                                Intent myIntent = new Intent(MainMenu.this, Login.class);
                                startActivity(myIntent);
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
                        return  true;

                    default:
                        return true;

                }
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        toolbar.setTitle(item.getTitle());

        Class<?> fragmentClass;
        try {
            Fragment fragment;

            fragmentClass = Class.forName("com.kalbenutritionals.app.kalbespgmobile.Fragment" + String.valueOf(item.getTitle()).replaceAll("\\s+", ""));
            fragment = (Fragment) fragmentClass.newInstance();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment);
            fragmentTransaction.commit();

            selectedId=id;

            if (!isSubMenu) isSubMenu = true;
            else isSubMenu = false;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        menu.clear();
//        if (listMenu.length <= selectedId) {
//            if (toolbar.getTitle().equals("Reporting")&&!isSubMenu||isSubMenu && dtAbsens != null&&listMenu.length>0&&toolbar.getTitle()=="Reporting"){
//                for(String s : listMenu){
//                    if(s.contains("Reso SPG")) {
//                        int a = listMenu.length;
//                        for (int i=0; i<a; i++ ){
//                            if (i==1){
//                                menu.removeItem(i);
//                            } else {
//                                menu.add(0, i, 0, "Add " + listMenu[i]);
//                            }
//                        }
//                    } else {
//                        menu.add(4, 0, 0, "-");
//                        menu.setGroupEnabled(4, false);
//                        break;
//                    }
//                    break;
//                }
//            } else {
//                menu.add(4, 0, 0, "-");
//                menu.setGroupEnabled(4, false);
//            }
//        } else if (!isSubMenu && dtAbsens != null) {
//            menu.add(0, selectedId, 0, "Add " + listMenu[selectedId]);
//        } else if (isSubMenu && dtAbsens != null) {
//            menu.add(1, selectedId, 0, "View " + listMenu[selectedId]);
//        }
//        else {
//            menu.add(4, 0, 0, "-");
//            menu.setGroupEnabled(4, false);
//        }
//
//        return super.onPrepareOptionsMenu(menu);
//
//    }


    int intProcesscancel = 0;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profile_image:
                pickImage2();
                break;
        }
    }

    public void pickImage2()
    {
        CropImage.startPickImageActivity(this);
    }

    @Override
    @SuppressLint("NewApi")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            Uri imageUri = CropImage.getPickImageResultUri(this, data);

            if (CropImage.isReadExternalStoragePermissionsRequired(this, imageUri)) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE);
            } else {
                Intent intent = new Intent(this, CropDisplayPicture.class);
                String strName = imageUri.toString();
                intent.putExtra("uriPicture", strName);
                startActivity(intent);
                finish();
            }
        } else if (requestCode==100 || requestCode == 130){
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                    fragment.onActivityResult(requestCode, resultCode, data);
                }
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MainMenu Page", // TODO: Define a title for the content shown.
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
                "MainMenu Page", // TODO: Define a title for the content shown.
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

    private class AsyncCallLogOut extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
            JSONArray Json = null;

            try {
                List<tAbsenUserData> listAbsenData = new ArrayList<tAbsenUserData>();
                tAbsenUserData dtTabsenData = new tAbsenUserBL().getDataCheckInActive();
                if (dtTabsenData != null) {
                    dtTabsenData.set_dtDateCheckOut(_clsMainActivity.FormatDateComplete().toString());
                    dtTabsenData.set_intSubmit("1");
                    dtTabsenData.set_intSync("0");
                    listAbsenData.add(dtTabsenData);
                    new tAbsenUserBL().saveData(listAbsenData);
                }
                clsPushData dtJson = new clsHelperBL().pushData();
                if (dtJson != null) {
                    String versionName = "";
                    try {
                        versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
                    } catch (PackageManager.NameNotFoundException e2) {
                        // TODO Auto-generated catch block
                        e2.printStackTrace();
                    }
                    try {
                        JSONArray Jresult = null;
                        if (dtJson.getDtdataJson().getListOftAbsenUserData() != null) {
                            List<tAbsenUserData> listAbsen = dtJson.getDtdataJson().getListOftAbsenUserData();
                            if (listAbsen.get(0).get_txtAbsen().equals("0")) {
                                Jresult = new clsHelperBL().callPushDataReturnJson(versionName, dtJson.getDtdataJson().txtJSON().toString(), dtJson.getFileUpload());
                            } else {
                                Jresult = new clsHelperBL().callPushDataReturnJson(versionName, dtJson.getDtdataJson().txtJSON().toString(), null);
                            }
//                            new clsHelperBL().saveDataPush(dtJson.getDtdataJson(), Jresult);
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }

                try {
                    pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                } catch (PackageManager.NameNotFoundException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }

                Json = new tUserLoginBL().Logout(pInfo.versionName);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            }
            return Json;
        }

        private ProgressDialog Dialog = new ProgressDialog(MainMenu.this);

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            Toast toast = Toast.makeText(MainMenu.this, "cancel", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 25, 400);
            toast.show();
        }

        @Override
        protected void onPostExecute(JSONArray roledata) {
            if (roledata != null) {
                Iterator i = roledata.iterator();
                while (i.hasNext()) {
                    JSONObject innerObj = (JSONObject) i.next();
                    Long IntResult = (Long) innerObj.get("_pboolValid");
                    String PstrMessage = (String) innerObj.get("_pstrMessage");

                    if (IntResult == 1) {
                        tNotificationBL _tNotificationBL = new tNotificationBL();
                        List<tNotificationData> ListData = _tNotificationBL.getAllDataWillAlert("1");
                        if (ListData != null) {
                            for (tNotificationData dttNotificationData : ListData) {
                                NotificationManager tnotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                                tnotificationManager.cancelAll();
                                ShortcutBadger.applyCount(MainMenu.this, 0);
                                System.gc();
                            }
                        }
                        new clsHelperBL().DeleteAllDB();
                        finish();
                        Intent nextScreen = new Intent(MainMenu.this, Splash.class);
                        startActivity(nextScreen);
                    } else {
                        Toast toast = Toast.makeText(MainMenu.this,
                                PstrMessage, Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.TOP, 25, 400);
                        toast.show();
                    }
                }

            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    _clsMainActivity.showCustomToast(getApplicationContext(), "Offline", false);
                }

            }
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            Dialog.setMessage(new clsHardCode().txtMessLogOut);
            Dialog.setCancelable(false);
            Dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    intProcesscancel = 1;
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
