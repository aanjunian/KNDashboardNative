package com.kalbenutritionals.xteamnative;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TableLayout;

/**
 * Created by ASUS ZE on 01/12/2016.
 */

public class PushData extends AppCompatActivity{

    private TableLayout tlSOHeader;
    private TableLayout tlSODetail;
    private TableLayout tlActivity;
    private TableLayout tlCustomerBase;
    private TableLayout tlAbsen;
    private TableLayout tlLeave;
    private Button btnPush;
    private String myValue;

    clsMainActivity _clsMainActivity = new clsMainActivity();

    PackageInfo pInfo = null;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Push Data");
        setSupportActionBar(toolbar);

        Bundle bundle = new Bundle();
        String myMessage = "notMainMenu";
        bundle.putString("message", myMessage );

        FragmentPushData fragmentPushData = new FragmentPushData();
        fragmentPushData.setArguments(bundle);
        FragmentTransaction fragmentFragmentPushData = getSupportFragmentManager().beginTransaction();
        fragmentFragmentPushData.replace(R.id.frame, fragmentPushData);
        fragmentFragmentPushData.commit();
    }

//    private void loadData() {
//        clsPushData dtclsPushData=new clsHelperBL().pushData();
//
//        if(dtclsPushData!=null){
//            dataJson dtJson =dtclsPushData.getDtdataJson();
//
//            if(dtJson.getListOftSalesProductHeaderData()!=null){
//                initSOHeader(getApplicationContext(),dtJson.getListOftSalesProductHeaderData());
//            } else {
//                initSOHeader(getApplicationContext(),null);
//            }
//
//            if(dtJson.getListOftActivityData()!=null){
//                initActivity(getApplicationContext(),dtJson.getListOftActivityData());
//            } else {
//                initActivity(getApplicationContext(),null);
//            }
//
//            if(dtJson.get_ListOftCustomerBasedMobileHeaderData()!=null){
//                initCustomerBase(getApplicationContext(),dtJson.get_ListOftCustomerBasedMobileHeaderData());
//            } else {
//                initCustomerBase(getApplicationContext(),null);
//            }
//
//            if(dtJson.getListOftAbsenUserData()!=null){
//                inittAbsen(getApplicationContext(),dtJson.getListOftAbsenUserData());
//            } else {
//                inittAbsen(getApplicationContext(),null);
//            }
//
//            if(dtJson.getListOftLeaveMobileData()!=null){
//                inittLeave(getApplicationContext(),dtJson.getListOftLeaveMobileData());
//            } else {
//                inittLeave(getApplicationContext(),null);
//            }
//
//        }
//    }
//
//    private void inittLeave(Context context, List<tLeaveMobileData> listOftLeaveMobileData) {
//        tlLeave = (TableLayout) findViewById(R.id.tl_leave);
//
//        tlLeave.removeAllViews();
//
//        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
//        params.setMargins(1, 1, 1, 1);
//
//        TableRow tr = new TableRow(getApplicationContext());
//
//        TableLayout tl = new TableLayout(getApplicationContext());
//
//        String[] colTextHeader = {"No.", "Reason", "Type", "Date"};
//
//        for (String text : colTextHeader) {
//            TextView tv = new TextView(getApplicationContext());
//            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));
//
//            tv.setTextSize(14);
//            tv.setPadding(10, 10, 10, 10);
//            tv.setText(text);
//            tv.setGravity(Gravity.CENTER);
//            tv.setBackgroundColor(Color.parseColor("#4CAF50"));
//
//            tv.setTextColor(Color.WHITE);
//            tr.addView(tv,params);
//        }
//        tl.addView(tr);
//
//        if(listOftLeaveMobileData!=null){
//            int index = 1;
//            for(tLeaveMobileData dat : listOftLeaveMobileData){
//                tr = new TableRow(getApplicationContext());
//                TableLayout.LayoutParams tableRowParams=
//                        new TableLayout.LayoutParams
//                                (TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);
//
//                int leftMargin=0;
//                int topMargin=0;
//                int rightMargin=0;
//                int bottomMargin=0;
//                tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
//
//                tr.setLayoutParams(tableRowParams);
//
//                TextView tv_index = new TextView(getApplicationContext());
//                tv_index.setTextSize(12);
//                tv_index.setPadding(10, 10, 10, 10);
//                tv_index.setBackgroundColor(Color.parseColor("#f0f0f0"));
//                tv_index.setTextColor(Color.BLACK);
//                tv_index.setText(String.valueOf(index + "."));
//                tr.addView(tv_index,params);
//
//                TextView outlet_code = new TextView(getApplicationContext());
//                outlet_code.setTextSize(12);
//                outlet_code.setPadding(10, 10, 10, 10);
//                outlet_code.setBackgroundColor(Color.parseColor("#f0f0f0"));
//                outlet_code.setTextColor(Color.BLACK);
//                outlet_code.setGravity(Gravity.CENTER);
//                outlet_code.setText(dat.get_txtAlasan());
//                tr.addView(outlet_code,params);
//
//                TextView outlet_name = new TextView(getApplicationContext());
//                outlet_name.setTextSize(12);
//                outlet_name.setPadding(10, 10, 10, 10);
//                outlet_name.setBackgroundColor(Color.parseColor("#f0f0f0"));
//                outlet_name.setTextColor(Color.BLACK);
//                outlet_name.setGravity(Gravity.CENTER);
//                outlet_name.setText(dat.get_txtTypeAlasanName());
//                tr.addView(outlet_name,params);
//
//                TextView date = new TextView(getApplicationContext());
//                date.setTextSize(12);
//                date.setPadding(10, 10, 10, 10);
//                date.setBackgroundColor(Color.parseColor("#f0f0f0"));
//                date.setTextColor(Color.BLACK);
//                date.setGravity(Gravity.CENTER);
//                date.setText(new clsMainActivity().giveFormatDate2(dat.get_dtLeave()));
//                tr.addView(date,params);
//
//                tl.addView(tr, tableRowParams);
//                index ++;
//            }
//        }
//
//        tlLeave.addView(tl);
//    }
//
//    private void initCustomerBase(Context context, List<tCustomerBasedMobileHeaderData> listOftCustomerBasedMobileHeaderData) {
//
//        tlCustomerBase.removeAllViews();
//
//        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
//        params.setMargins(1, 1, 1, 1);
//
//        TableRow tr = new TableRow(getApplicationContext());
//
//        TableLayout tl = new TableLayout(getApplicationContext());
//
//        String[] colTextHeader = {"No.", "Code", "Date", "Branch Code"};
//
//        for (String text : colTextHeader) {
//            TextView tv = new TextView(getApplicationContext());
//            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));
//
//            tv.setTextSize(14);
//            tv.setPadding(10, 10, 10, 10);
//            tv.setText(text);
//            tv.setGravity(Gravity.CENTER);
//            tv.setBackgroundColor(Color.parseColor("#4CAF50"));
//
//            tv.setTextColor(Color.WHITE);
//            tr.addView(tv,params);
//        }
//        tl.addView(tr);
//
//        if(listOftCustomerBasedMobileHeaderData!=null){
//            int index = 1;
//            for(tCustomerBasedMobileHeaderData dat : listOftCustomerBasedMobileHeaderData){
//                tr = new TableRow(getApplicationContext());
//                TableLayout.LayoutParams tableRowParams=
//                        new TableLayout.LayoutParams
//                                (TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);
//
//                int leftMargin=0;
//                int topMargin=0;
//                int rightMargin=0;
//                int bottomMargin=0;
//                tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
//
//                tr.setLayoutParams(tableRowParams);
//
//                TextView tv_index = new TextView(getApplicationContext());
//                tv_index.setTextSize(12);
//                tv_index.setPadding(10, 10, 10, 10);
//                tv_index.setBackgroundColor(Color.parseColor("#f0f0f0"));
//                tv_index.setTextColor(Color.BLACK);
//                tv_index.setText(String.valueOf(index + "."));
//                tr.addView(tv_index,params);
//
//                TextView outlet_code = new TextView(getApplicationContext());
//                outlet_code.setTextSize(12);
//                outlet_code.setPadding(10, 10, 10, 10);
//                outlet_code.setBackgroundColor(Color.parseColor("#f0f0f0"));
//                outlet_code.setTextColor(Color.BLACK);
//                outlet_code.setGravity(Gravity.CENTER);
//                outlet_code.setText(dat.get_txtSubmissionId());
//                tr.addView(outlet_code,params);
//
//                TextView outlet_name = new TextView(getApplicationContext());
//                outlet_name.setTextSize(12);
//                outlet_name.setPadding(10, 10, 10, 10);
//                outlet_name.setBackgroundColor(Color.parseColor("#f0f0f0"));
//                outlet_name.setTextColor(Color.BLACK);
//                outlet_name.setGravity(Gravity.CENTER);
//                outlet_name.setText(new clsMainActivity().giveFormatDate(dat.get_dtDate()));
//                tr.addView(outlet_name,params);
//
//                TextView date = new TextView(getApplicationContext());
//                date.setTextSize(12);
//                date.setPadding(10, 10, 10, 10);
//                date.setBackgroundColor(Color.parseColor("#f0f0f0"));
//                date.setTextColor(Color.BLACK);
//                date.setGravity(Gravity.CENTER);
//                date.setText(dat.get_txtBranchCode());
//                tr.addView(date,params);
//
//                tl.addView(tr, tableRowParams);
//                index ++;
//            }
//        }
//
//        tlCustomerBase.addView(tl);
//    }
//
//    private void initActivity(Context context, List<tActivityData> listOftActivityData) {
//
//        tlActivity.removeAllViews();
//
//        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
//        params.setMargins(1, 1, 1, 1);
//
//        TableRow tr = new TableRow(getApplicationContext());
//
//        TableLayout tl = new TableLayout(getApplicationContext());
//
//        String[] colTextHeader = {"No.", "Desc.", "Date", "Outlet Code"};
//
//        for (String text : colTextHeader) {
//            TextView tv = new TextView(getApplicationContext());
//            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));
//
//            tv.setTextSize(14);
//            tv.setPadding(10, 10, 10, 10);
//            tv.setText(text);
//            tv.setGravity(Gravity.CENTER);
//            tv.setBackgroundColor(Color.parseColor("#4CAF50"));
//
//            tv.setTextColor(Color.WHITE);
//            tr.addView(tv,params);
//        }
//        tl.addView(tr);
//
//        if(listOftActivityData!=null){
//            int index = 1;
//            for(tActivityData dat : listOftActivityData){
//                tr = new TableRow(getApplicationContext());
//                TableLayout.LayoutParams tableRowParams=
//                        new TableLayout.LayoutParams
//                                (TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);
//
//                int leftMargin=0;
//                int topMargin=0;
//                int rightMargin=0;
//                int bottomMargin=0;
//                tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
//
//                tr.setLayoutParams(tableRowParams);
//
//                TextView tv_index = new TextView(getApplicationContext());
//                tv_index.setTextSize(12);
//                tv_index.setPadding(10, 10, 10, 10);
//                tv_index.setBackgroundColor(Color.parseColor("#f0f0f0"));
//                tv_index.setTextColor(Color.BLACK);
//                tv_index.setText(String.valueOf(index + "."));
//                tr.addView(tv_index,params);
//
//                TextView outlet_code = new TextView(getApplicationContext());
//                outlet_code.setTextSize(12);
//                outlet_code.setPadding(10, 10, 10, 10);
//                outlet_code.setBackgroundColor(Color.parseColor("#f0f0f0"));
//                outlet_code.setTextColor(Color.BLACK);
//                outlet_code.setGravity(Gravity.CENTER);
//                outlet_code.setText(dat.get_txtDesc());
//                tr.addView(outlet_code,params);
//
//                TextView outlet_name = new TextView(getApplicationContext());
//                outlet_name.setTextSize(12);
//                outlet_name.setPadding(10, 10, 10, 10);
//                outlet_name.setBackgroundColor(Color.parseColor("#f0f0f0"));
//                outlet_name.setTextColor(Color.BLACK);
//                outlet_name.setGravity(Gravity.CENTER);
//                outlet_name.setText(new clsMainActivity().giveFormatDate(dat.get_dtActivity()));
//                tr.addView(outlet_name,params);
//
//                TextView date = new TextView(getApplicationContext());
//                date.setTextSize(12);
//                date.setPadding(10, 10, 10, 10);
//                date.setBackgroundColor(Color.parseColor("#f0f0f0"));
//                date.setTextColor(Color.BLACK);
//                date.setGravity(Gravity.CENTER);
//                date.setText(dat.get_txtOutletCode());
//                tr.addView(date,params);
//
//                tl.addView(tr, tableRowParams);
//                index ++;
//            }
//        }
//
//        tlActivity.addView(tl);
//    }
//
//    private void initSOHeader(Context context, List<tSalesProductHeaderData> listOftSalesProductHeaderData) {
//        tlSOHeader = (TableLayout) findViewById(R.id.tlSOHeader);
//        tlSOHeader.removeAllViews();
//
//        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
//        params.setMargins(1, 1, 1, 1);
//
//        TableRow tr = new TableRow(getApplicationContext());
//
//        TableLayout tl = new TableLayout(getApplicationContext());
//
//        String[] colTextHeader = {"No.", "NO SO", "Date", "Outlet Code"};
//
//        for (String text : colTextHeader) {
//            TextView tv = new TextView(getApplicationContext());
//            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));
//
//            tv.setTextSize(14);
//            tv.setPadding(10, 10, 10, 10);
//            tv.setText(text);
//            tv.setGravity(Gravity.CENTER);
//            tv.setBackgroundColor(Color.parseColor("#4CAF50"));
//
//            tv.setTextColor(Color.WHITE);
//            tr.addView(tv,params);
//        }
//        tl.addView(tr);
//
//        if(listOftSalesProductHeaderData!=null){
//            int index = 1;
//            for(tSalesProductHeaderData dat : listOftSalesProductHeaderData){
//                tr = new TableRow(getApplicationContext());
//                TableLayout.LayoutParams tableRowParams=
//                        new TableLayout.LayoutParams
//                                (TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);
//
//                int leftMargin=0;
//                int topMargin=0;
//                int rightMargin=0;
//                int bottomMargin=0;
//                tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
//
//                tr.setLayoutParams(tableRowParams);
//
//                TextView tv_index = new TextView(getApplicationContext());
//                tv_index.setTextSize(12);
//                tv_index.setPadding(10, 10, 10, 10);
//                tv_index.setBackgroundColor(Color.parseColor("#f0f0f0"));
//                tv_index.setTextColor(Color.BLACK);
//                tv_index.setText(String.valueOf(index + "."));
//                tr.addView(tv_index,params);
//
//                TextView outlet_code = new TextView(getApplicationContext());
//                outlet_code.setTextSize(12);
//                outlet_code.setPadding(10, 10, 10, 10);
//                outlet_code.setBackgroundColor(Color.parseColor("#f0f0f0"));
//                outlet_code.setTextColor(Color.BLACK);
//                outlet_code.setGravity(Gravity.CENTER);
//                outlet_code.setText(dat.get_intId());
//                tr.addView(outlet_code,params);
//
//                TextView outlet_name = new TextView(getApplicationContext());
//                outlet_name.setTextSize(12);
//                outlet_name.setPadding(10, 10, 10, 10);
//                outlet_name.setBackgroundColor(Color.parseColor("#f0f0f0"));
//                outlet_name.setTextColor(Color.BLACK);
//                outlet_name.setGravity(Gravity.CENTER);
//                outlet_name.setText(dat.get_dtDate());
//                tr.addView(outlet_name,params);
//
//                TextView date = new TextView(getApplicationContext());
//                date.setTextSize(12);
//                date.setPadding(10, 10, 10, 10);
//                date.setBackgroundColor(Color.parseColor("#f0f0f0"));
//                date.setTextColor(Color.BLACK);
//                date.setGravity(Gravity.CENTER);
//                date.setText(new clsMainActivity().giveFormatDate(dat.get_dtDate()));
//                tr.addView(date,params);
//
//                tl.addView(tr, tableRowParams);
//                index ++;
//            }
//        }
//
//        tlSOHeader.addView(tl);
//
//    }
//
//    private class AsyncCallRole extends AsyncTask<List<dataJson>, Void, List<dataJson>> {
//        @Override
//        protected List<dataJson> doInBackground(List<dataJson>... params) {
//            android.os.Debug.waitForDebugger();
//            List<dataJson> roledata=new ArrayList<dataJson>();
//            clsPushData dtJson= new clsHelperBL().pushData();
//            dataJson dtdataJson=new dataJson();
//            if(dtJson!=null){
//                String versionName="";
//                try {
//                    versionName = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0).versionName;
////                    versionName = new clsMainActivity().getApplicationContext().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName;
//                } catch (PackageManager.NameNotFoundException e2) {
//                    // TODO Auto-generated catch block
//                    e2.printStackTrace();
//                }
//                try {
//                    JSONArray Jresult= new clsHelperBL().callPushDataReturnJson(versionName,dtJson.getDtdataJson().txtJSON().toString(),dtJson.getFileUpload());
//                } catch (Exception e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//                dtdataJson.setIntResult("1");
//
//            }
//            else
//            {
//                dtdataJson.setIntResult("0");
//                dtdataJson.setTxtMessage("No Data");
//            }
//            roledata.add(dtdataJson);
//            return roledata ;
//        }
//
//        private ProgressDialog Dialog = new ProgressDialog(getApplicationContext());
//        @Override
//        protected void onCancelled() {
//            Dialog.dismiss();
//            Toast toast = Toast.makeText(getApplicationContext(),
//                    new clsHardCode().txtMessCancelRequest, Toast.LENGTH_LONG);
//            toast.setGravity(Gravity.TOP, 25, 400);
//            toast.show();
//        }
//        @Override
//        protected void onPostExecute(List<dataJson> roledata) {
//            if(roledata.get(0).getIntResult().equals("1")){
//                new clsMainActivity().showToast(getApplicationContext(), "Success Push Data");
//            }else{
//                new clsMainActivity().showToast(getApplicationContext(), roledata.get(0).getTxtMessage());
//            }
//            Dialog.dismiss();
//        }
//        int intProcesscancel=0;
//        @Override
//        protected void onPreExecute() {
//            //Make ProgressBar invisible
//            //pg.setVisibility(View.VISIBLE);
//            Dialog.setMessage("Syncronize Data!!");
//            Dialog.setCancelable(false);
//            Dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    intProcesscancel=1;
//                }
//            });
//            Dialog.show();
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//            Dialog.dismiss();
//        }
//
//    }
//
//
//    public void inittAbsen(Context _ctx,List<tAbsenUserData> ListData){
//        tlAbsen = (TableLayout) findViewById(R.id.tl_absen);
//        tlAbsen.removeAllViews();
//
//        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
//        params.setMargins(1, 1, 1, 1);
//
//        TableRow tr = new TableRow(getApplicationContext());
//
//        TableLayout tl = new TableLayout(getApplicationContext());
//
//        String[] colTextHeader = {"No.", "Outlet Code", "Outlet Name", "Date"};
//
//        for (String text : colTextHeader) {
//            TextView tv = new TextView(getApplicationContext());
//            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));
//
//            tv.setTextSize(14);
//            tv.setPadding(10, 10, 10, 10);
//            tv.setText(text);
//            tv.setGravity(Gravity.CENTER);
//            tv.setBackgroundColor(Color.parseColor("#4CAF50"));
//
//            tv.setTextColor(Color.WHITE);
//            tr.addView(tv,params);
//        }
//        tl.addView(tr);
//
//        if(ListData!=null){
//            int index = 1;
//            for(tAbsenUserData dat : ListData){
//                tr = new TableRow(getApplicationContext());
//                TableLayout.LayoutParams tableRowParams=
//                        new TableLayout.LayoutParams
//                                (TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);
//
//                int leftMargin=0;
//                int topMargin=0;
//                int rightMargin=0;
//                int bottomMargin=0;
//                tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
//
//                tr.setLayoutParams(tableRowParams);
//
//                TextView tv_index = new TextView(getApplicationContext());
//                tv_index.setTextSize(12);
//                tv_index.setPadding(10, 10, 10, 10);
//                tv_index.setBackgroundColor(Color.parseColor("#f0f0f0"));
//                tv_index.setTextColor(Color.BLACK);
//                tv_index.setText(String.valueOf(index + "."));
//                tr.addView(tv_index,params);
//
//                TextView outlet_code = new TextView(getApplicationContext());
//                outlet_code.setTextSize(12);
//                outlet_code.setPadding(10, 10, 10, 10);
//                outlet_code.setBackgroundColor(Color.parseColor("#f0f0f0"));
//                outlet_code.setTextColor(Color.BLACK);
//                outlet_code.setGravity(Gravity.CENTER);
//                outlet_code.setText(dat.get_txtOutletCode());
//                tr.addView(outlet_code,params);
//
//                TextView outlet_name = new TextView(getApplicationContext());
//                outlet_name.setTextSize(12);
//                outlet_name.setPadding(10, 10, 10, 10);
//                outlet_name.setBackgroundColor(Color.parseColor("#f0f0f0"));
//                outlet_name.setTextColor(Color.BLACK);
//                outlet_name.setGravity(Gravity.CENTER);
//                outlet_name.setText(dat.get_txtOutletName());
//                tr.addView(outlet_name,params);
//
//                TextView date = new TextView(getApplicationContext());
//                date.setTextSize(12);
//                date.setPadding(10, 10, 10, 10);
//                date.setBackgroundColor(Color.parseColor("#f0f0f0"));
//                date.setTextColor(Color.BLACK);
//                date.setGravity(Gravity.CENTER);
//                date.setText(new clsMainActivity().giveFormatDate2(dat.get_dtDateCheckIn()));
//                tr.addView(date,params);
//
//                tl.addView(tr, tableRowParams);
//                index ++;
//            }
//        }
//
//        tlAbsen.addView(tl);
//    }
}
