package com.ibailian.android.demo;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridViewAdapter extends BaseAdapter {

	private Context context;
	private List<GridViewData> listData;

	public GridViewAdapter(Context context, List<GridViewData> listData) {
		this.context = context;
		this.listData = listData;
	}

	@Override
	public int getCount() {
		return listData != null ? listData.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		return listData != null ? listData.get(position) : 0;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		final GridViewData item = listData.get(position);
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.gridview_item, null);
			holder.image = (ImageView) convertView.findViewById(R.id.product_image);
			holder.name = (TextView) convertView.findViewById(R.id.product_name);
			holder.price = (TextView) convertView.findViewById(R.id.product_price);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.image.setImageResource(R.drawable.product);
		holder.name.setText(item.getName());
		holder.price.setText(item.getPrice());
		return convertView;
	}
	
	class ViewHolder{
		TextView price;
		TextView name;
		ImageView image;
	}

}
