package com.kalbenutritionals.xteamnative;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.HashMap;
import java.util.List;

import bl.mBannerBL;
import library.common.mBannerData;
import library.dal.clsHardCode;

/**
 * Created by ASUS ZE on 28/10/2016.
 */

public class FragmentBanner extends Fragment implements ViewPagerEx.OnPageChangeListener, BaseSliderView.OnSliderClickListener{

    View v;
    List<mBannerData> dtListdataBanner;
    AlbumsAdapter allUsersAdapter;
    RecyclerView recyclerView;
    private SliderLayout mDemoSlider;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_main3, container, false);

//        View view = LayoutInflater.from(getContext()).inflate(R.layout.cardview_banner, null);
//
//        ImageView imgBanner = (ImageView) view.findViewById(R.id.iv_banner);
//        imgBanner.setAdjustViewBounds(true);
//        imgBanner.setScaleType(ImageView.ScaleType.FIT_XY);

//        AsyncCallBanner task1 = new AsyncCallBanner();
//        task1.execute();

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        initBanner(getContext());

        return v;
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

//    private class AsyncCallBanner extends AsyncTask<JSONArray, Void, JSONArray> {
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
//                int j=0;
//
//                while (i.hasNext()) {
//                    JSONObject innerObj = (JSONObject) i.next();
//                    Long IntResult = (Long) innerObj.get("_pboolValid");
//
//                    if (IntResult == 1) {
//                        for (j=0; j<9; j++){
//                            String IntBannerID = (String) innerObj.get("IntBannerID").toString();
//                            String TxtFileName = (String) innerObj.get("TxtFileName").toString();
//                            String TxtName = (String) innerObj.get("TxtName").toString();
//                            String TxtPathActual = (String) innerObj.get("TxtPathActual").toString();
//                            String TxtPathThumbnail = (String) innerObj.get("TxtPathThumbnail").toString();
//                            String txtDesc = (String) innerObj.get("txtDesc").toString();
//                            dtListdataBanner.add(new dataBanner(IntBannerID, TxtFileName, TxtName, TxtPathActual, TxtPathThumbnail, txtDesc));
//                        }
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

        dtListdataBanner = new mBannerBL().GetAllData();

        allUsersAdapter = new AlbumsAdapter(context, dtListdataBanner);
        recyclerView.setAdapter(allUsersAdapter);

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
}
