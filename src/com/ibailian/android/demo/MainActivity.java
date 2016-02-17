package com.ibailian.android.demo;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

	private static TabHost tabHost;
	private static ImageView home;
	private static ImageView category;
	private static ImageView bailian;
	private static ImageView cart;
	private static ImageView personal;
	private String CurrentNum="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {
		tabHost = getTabHost();
        tabHost.addTab(tabHost.newTabSpec("a").setIndicator("A").setContent(new Intent(this, Activity_home.class)));
        tabHost.addTab(tabHost.newTabSpec("b").setIndicator("B").setContent(new Intent(this, Activity_category.class)));
        tabHost.addTab(tabHost.newTabSpec("c").setIndicator("C").setContent(new Intent(this, Activity_bailian.class)));
        tabHost.addTab(tabHost.newTabSpec("d").setIndicator("D").setContent(new Intent(this, Activity_cart.class)));
        tabHost.addTab(tabHost.newTabSpec("e").setIndicator("E").setContent(new Intent(this, Activity_personal.class)));
        home = (ImageView) findViewById(R.id.radio_home);
        category = (ImageView) findViewById(R.id.radio_category);
        bailian = (ImageView) findViewById(R.id.radio_bailian);
        cart = (ImageView) findViewById(R.id.radio_cart);
        personal = (ImageView) findViewById(R.id.radio_personal);
	}
	
	public void onNavClick(View v){
		toNormalNav();
		switch (v.getId()) {
		case R.id.radio_home:
			home.setImageResource(R.drawable.home_choose);
			tabHost.setCurrentTabByTag("a");
			break;
		case R.id.radio_category:
			toClickNav(category, R.drawable.category_choose);
			tabHost.setCurrentTabByTag("b");
			break;
		case R.id.radio_bailian:
			toClickNav(bailian, R.drawable.bailian_choose);
			tabHost.setCurrentTabByTag("c");
			break;
		case R.id.radio_cart:
			toClickNav(cart, R.drawable.cart_choose);
			tabHost.setCurrentTabByTag("d");
			break;
		case R.id.radio_personal:
			toClickNav(personal, R.drawable.personal_choose);
			tabHost.setCurrentTabByTag("e");
			break;

		default:
			break;
		}
	}
	
	public static void goToCurrentTab(int currentitem){
		switch (currentitem) {
		case 2:
			toNormalNav();
			toClickNav(category, R.drawable.category_choose);
			tabHost.setCurrentTabByTag("b");
			break;
		default:
			break;
		}
	}

	private static void toNormalNav(){
		home.setImageResource(R.drawable.home);
		category.setImageResource(R.drawable.category);
		bailian.setImageResource(R.drawable.bailian);
		cart.setImageResource(R.drawable.cart);
		personal.setImageResource(R.drawable.personal);
	}
	
	private static void toClickNav(ImageView view,int drawable){
		view.setImageResource(drawable);
	}
}
