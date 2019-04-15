package com.example.LBGManager;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private DrawerLayout drawerLayout;
    private NoSwipeViewPager mViewPager;
    private SectionsStatePagerAdapter mSectionStatePageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Started.");

        /* All the navigation view stuff */
        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        int id = menuItem.getItemId();

                        switch (id) {
                            case (R.id.my_tasks_button):
                                setFragment(0);
                                break;
                            case (R.id.events_button):
                                setFragment(1);
                                break;
                            case (R.id.settings_button):
                                menuItem.setChecked(false);
                                Intent myIntent = new Intent(MainActivity.this, SettingsActivity.class);
                                startActivity(myIntent);
                                break;
                        }

                        return true;
                    }
                }
        );

        navigationView.setCheckedItem(R.id.my_tasks_button);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_menu_black_18); // So we set a home button on the ActionBar


        //mSectionStatePageAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());

        // Custom viewpager to disable swipe
        mViewPager = (NoSwipeViewPager) findViewById(R.id.container);

        // setup the pager
        setupViewPager(mViewPager);
        setFragment(0);
    }

    private void setupViewPager(ViewPager pager) {
        mSectionStatePageAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        mSectionStatePageAdapter.addFragment(new TasksFragment(), "My Tasks");
        mSectionStatePageAdapter.addFragment(new EventsFragment(), "Events");
        pager.setAdapter(mSectionStatePageAdapter);
    }

    public void setFragment(int fragmentIndex) {
        mViewPager.setCurrentItem(fragmentIndex);
        String title = mSectionStatePageAdapter.getTitle(fragmentIndex);
        setTitle(title);
    }

    // This will act when option items from the actionbar are pressed
    // We are overriding the action when the home button is pressed
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
