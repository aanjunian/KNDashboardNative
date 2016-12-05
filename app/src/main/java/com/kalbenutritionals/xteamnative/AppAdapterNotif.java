package com.kalbenutritionals.xteamnative;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import library.common.clsRowItem;

public class AppAdapterNotif extends BaseAdapter {

    private Context context;
    //private PackageMIanager packageManager;
    private List<clsRowItem> mAppList;

    /*
    public AppAdapter(Context context, PackageManager packageManager, List<String> mAppList) {
        this.context = context;
        this.packageManager = packageManager;
        this.mAppList = mAppList;
    }
    */
    
    public AppAdapterNotif(Context context, List<clsRowItem> mAppList) {
        this.context = context;
        //this.packageManager = packageManager;
        this.mAppList = mAppList;
    }

    @Override
    public int getCount() {
        return mAppList.size();
    }

    public clsRowItem getItem(String position) {
        return mAppList.get(Integer.parseInt(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        clsRowItem rowItem = getItem(position);
         
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(edu.swu.pulltorefreshswipemenulistview.library.R.layout.item_list_app2, null);
            holder = new ViewHolder();
            holder.txtDesc = (TextView) convertView.findViewById(edu.swu.pulltorefreshswipemenulistview.library.R.id.description);
            holder.txtTitle = (TextView) convertView.findViewById(edu.swu.pulltorefreshswipemenulistview.library.R.id.title);
            holder.imageView = (ImageView) convertView.findViewById(edu.swu.pulltorefreshswipemenulistview.library.R.id.icon);
            holder.txtTitle.setTypeface(holder.txtTitle.getTypeface(), Typeface.BOLD);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

//        if(rowItem.get_txtId().equals("1")){
//            holder.txtDesc.setText("Unread");
//            holder.txtTitle.setText(rowItem.get_title());
//            holder.imageView.setImageResource(Integer.parseInt(rowItem.get_imageId()));
//        } else if (rowItem.get_txtId().equals("0")){
//            holder.txtDesc.setText("Read");
//            holder.txtTitle.setText(rowItem.get_title());
//            holder.imageView.setImageResource(Integer.parseInt(rowItem.get_imageId()));
//        }
            holder.txtTitle.setText(rowItem.get_title());
            holder.txtDesc.setText(rowItem.get_desc());
            holder.imageView.setImageResource(Integer.parseInt(rowItem.get_imageId()));

        return convertView;
    }
    
    /*private view holder class*/
    private class ViewHolder {
    	ImageView imageView;
        TextView txtTitle;
        TextView txtDesc;
    }

//    class ViewHolder {
//        ImageView iv_icon;
//        TextView tv_name;
//
//        public ViewHolder(View view) {
//            iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
//            tv_name = (TextView) view.findViewById(R.id.tv_name);
//            view.setTag(this);
//        }
//    }

	@Override
	public clsRowItem getItem(int position) {
		// TODO Auto-generated method stub
		return mAppList.get(position);
	}
}