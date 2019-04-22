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
import com.example.LBGManager.Model.AppMember;
import com.example.LBGManager.Model.LBG;
import com.example.LBGManager.Model.Serializer;
import com.example.LBGManager.Model.Model;
import com.example.LBGManager.Network.Exceptions.InvalidResponseFormat;
import com.example.LBGManager.Network.Exceptions.WrongTokenException;
import com.example.LBGManager.Network.Session;

import java.io.IOException;

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
                            case (R.id.logout_button):
                                menuItem.setChecked(false);
                                try {
                                    Serializer.deleteAppMember(MainActivity.this);
                                    Serializer.deleteModel(MainActivity.this);
                                    Intent logoutIntent = new Intent(MainActivity.this, LoginActivity.class);
                                    startActivity(logoutIntent);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
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

        initializeModel();
    }

    private void initializeModel() {
        // This is going to recover the old serialized password
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 1. Opens the old model
                Model model;
                try {
                    model = Serializer.deserializeModel(MainActivity.this);
                } catch (Exception e) {
                    // Gives an empty model if the old_model cannot be recovered
                    model = new Model();
                }
                LBG.updateModel(model);

                AppMember appMember;
                try {
                    // Tries to get the model online, opens the login activity if no token exists or it's a wrong token
                    appMember = Serializer.deserializeAppMember(MainActivity.this);
                    model = Session.getInstance(appMember.getToken()).gatherModel();
                    LBG.updateModel(model);
                } catch (IOException | ClassNotFoundException | WrongTokenException | InvalidResponseFormat e) {
                    // If the old cannot be recovered launches a login activity
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(myIntent);
                        }

                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
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
