package com.webkul.mobikul.mobikulstandalonepos.activity;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.webkul.mobikul.mobikulstandalonepos.R;
import com.webkul.mobikul.mobikulstandalonepos.db.AppDatabase;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.webkul.mobikul.mobikulstandalonepos.db.AppDatabase.MIGRATION_1_2;

/**
 * Created by aman.gupta on 4/1/17. @Webkul Software Private limited
 */

public abstract class BaseActivity extends AppCompatActivity {
    @SuppressWarnings("unused")
    public static final String TAG = "BaseActivity";
    public SweetAlertDialog mSweetAlertDialog;
    public FragmentManager mSupportFragmentManager;
    public AppDatabase db;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSupportFragmentManager = getSupportFragmentManager();
        db = getDb();
        showBackButton(true);
    }

    public AppDatabase getDb() {
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "db_pos")
                .addMigrations(MIGRATION_1_2)
                .fallbackToDestructiveMigration()
                .build();
        return db;
    }

    protected void showBackButton(boolean show) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(show);
        }
    }

    public void setActionbarTitle(@Nullable String title) {
        if (title == null) {
            return;
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        mItemBag = menu.findItem(R.id.menu_item_bag);
//        LayerDrawable icon = (LayerDrawable) mItemBag.getIcon();
////        Helper.setBadgeCount(this, icon, AppSharedPref.getCartCount(this, 0));
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() == 0)
            finish();
        else
            super.onBackPressed();
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (mItemBag != null) {
//            LayerDrawable icon = (LayerDrawable) mItemBag.getIcon();
////            Helper.setBadgeCount(this, icon, AppSharedPref.getCartCount(this, 0));
//        }
//    }


//    public void updateCartBadge(int cartCount) {
//        AppSharedPref.setCartCount(this, cartCount);
//        if (mItemBag != null) {
//            LayerDrawable icon = (LayerDrawable) mItemBag.getIcon();
//            Helper.setBadgeCount(this, icon, AppSharedPref.getCartCount(this, 0));
//        }
//    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        mCompositeDisposable.clear();
//        RetrofitClient.getDispatcher().cancelAll();
//    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mSqLiteDatabase.close();
//        AlertDialogHelper.dismiss(this);
//    }
}