package com.ibailian.android.demo;

import java.util.ArrayList;
import java.util.List;

import com.ibailian.android.banner.BannerLayout;
import com.ibailian.android.banner.ImageLoader;
import com.ibailian.android.demo.MyScrollView.OnScrollListener;
import com.ibailian.android.view.PullToRefreshView;
import com.ibailian.android.view.PullToRefreshView.OnFooterRefreshListener;
import com.ibailian.android.view.PullToRefreshView.OnHeaderRefreshListener;
import com.zbar.lib.CaptureActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

public class Activity_home extends Activity implements OnHeaderRefreshListener,
		OnFooterRefreshListener,OnScrollListener {

	PullToRefreshView mPullToRefreshView;
	private ImageLoader imageLoader;
	private BannerLayout mBannerLayout;
	private RelativeLayout scan;
	private RelativeLayout message;
	private ImageView top;
	private MyScrollView sc;
	
	private GridView mGridView;
	private GridViewData mGridViewData;
	private List<GridViewData> mGridViewListData = new ArrayList<GridViewData>();
	private GridViewAdapter mGridViewAdapter = null;

	@SuppressWarnings("deprecation")
	private Gallery mStormGallery = null;
	private HomeGalleryItemData mItemData = null;
	private List<HomeGalleryItemData> mStormListData = new ArrayList<HomeGalleryItemData>();
	private HomeGalleryAdapter mStormAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		initData();
		initView();
		initBanner();

	}

	@SuppressWarnings("deprecation")
	private void initView() {
		mPullToRefreshView = (PullToRefreshView) findViewById(R.id.main_pull_refresh_view);
		mPullToRefreshView.setOnHeaderRefreshListener(this);
		mPullToRefreshView.setOnFooterRefreshListener(this);

		scan = (RelativeLayout) findViewById(R.id.scan);
		scan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Activity_home.this,
						CaptureActivity.class);
				startActivity(intent);
			}
		});
		message = (RelativeLayout) findViewById(R.id.message);
		message.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Activity_home.this,
						MessageActivity.class);
				startActivity(intent);
			}
		});
		mStormGallery = (Gallery) findViewById(R.id.home_gallery);
		mStormAdapter = new HomeGalleryAdapter(this,
				R.layout.activity_home_galley_item, mStormListData);

		mStormGallery.setAdapter(mStormAdapter);
		mStormGallery.setSelection(3);

		top = (ImageView) findViewById(R.id.top);
		sc = (MyScrollView) findViewById(R.id.scrollview);
		sc.setOnScrollListener(this);	
		top.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				sc.post(new Runnable() {

					@Override
					public void run() {
						sc.fullScroll(ScrollView.FOCUS_UP);
						top.setVisibility(View.GONE);
					}
				});
			}
		});
		
		mGridView = (GridView) findViewById(R.id.grid_view);
		mGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		mGridViewAdapter = new GridViewAdapter(this, mGridViewListData);
		mGridView.setAdapter(mGridViewAdapter);
	}

	private void initBanner() {
		imageLoader = new ImageLoader(this, true);
		mBannerLayout = (BannerLayout) findViewById(R.id.banner);
		UpdateBannerView();

	}

	private void UpdateBannerView() {
		mBannerLayout.removeAllViews();

		ImageView v = new ImageView(this);
		v.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		v.setAdjustViewBounds(true);
		mBannerLayout.addView(v);
		v.setBackgroundResource(R.drawable.banner);

		ImageView v2 = new ImageView(this);
		v2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		v2.setAdjustViewBounds(true);
		mBannerLayout.addView(v2);
		v2.setBackgroundResource(R.drawable.banner1);
		
		ImageView v3 = new ImageView(this);
		v3.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		v3.setAdjustViewBounds(true);
		mBannerLayout.addView(v3);
		v3.setBackgroundResource(R.drawable.banner1);

	}

	@Override
	public void onFooterRefresh(PullToRefreshView view) {
		mPullToRefreshView.postDelayed(new Runnable() {

			@Override
			public void run() {

				mPullToRefreshView.onFooterRefreshComplete();
			}
		}, 2000);

	}

	@Override
	public void onHeaderRefresh(PullToRefreshView view) {
		mPullToRefreshView.postDelayed(new Runnable() {

			@Override
			public void run() {

				mPullToRefreshView.onHeaderRefreshComplete();
			}
		}, 2000);

	}

	private void initData() {

		for (int i = 0; i < 7; i++) {
			mItemData = new HomeGalleryItemData();
			mItemData.setId(i);
			mItemData.setImage("drawable://" + R.drawable.product);
			mItemData.setPrice("￥65.00");
			mItemData.setDiscount("6.5折");
			mStormListData.add(mItemData);
		}
		for (int i = 0; i < 10; i++) {
			mGridViewData = new GridViewData();
			mGridViewData.setId(i);
			mGridViewData.setImage("drawable://" + R.drawable.product);
			mGridViewData.setName("商品名称");
			mGridViewData.setPrice("￥65.00");
			mGridViewListData.add(mGridViewData);
			
		}
	}

	@Override
	public void onScroll(int scrollY) {
		if (scrollY > 1500) {
			top.setVisibility(View.VISIBLE);
		}else {
			top.setVisibility(View.GONE);
		}
		
	}
}