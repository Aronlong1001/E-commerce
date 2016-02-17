package com.ibailian.android.demo;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeGalleryAdapter extends BaseAdapter {

	Context context;
	int layoutId;
	List<HomeGalleryItemData> listData;

	public HomeGalleryAdapter(Context context, int layoutId,
			List<HomeGalleryItemData> listData) {
		this.context = context;
		this.layoutId = layoutId;
		this.listData = listData;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listData.size() == 0 ? 0 : listData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder;
		final int pos = position;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(layoutId, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.imageView = (ImageView) convertView.findViewById(R.id.product);
			viewHolder.price = (TextView) convertView.findViewById(R.id.price);
			viewHolder.discount = (TextView) convertView.findViewById(R.id.discount);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
			resetViewHolder(viewHolder);
		}
		viewHolder.imageView.setImageResource(R.drawable.product);
		viewHolder.price.setText(listData.get(pos).getPrice());
		viewHolder.discount.setText(listData.get(pos).getDiscount());
		return convertView;
	}

	static class ViewHolder {
		ImageView imageView;
		TextView price;
		TextView discount;
	}
	protected void resetViewHolder(ViewHolder viewHolder) {
		viewHolder.imageView.setImageBitmap(null);
		viewHolder.price.setText("");
		viewHolder.discount.setText("");
	}
}
