package me.stupidme.console.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import me.stupidme.console.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private AndroidArticleFragment mAndroidFragment;
    private GolangArticleFragment mGolangFragment;
    private JavaArticleFragment mJavaFragment;
    private PythonArticleFragment mPythonFragment;
    private FragmentManager mFragmentManager;
    private Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mFragmentManager = getSupportFragmentManager();
        mAndroidFragment = new AndroidArticleFragment();
        mFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, mAndroidFragment)
                .commit();
        mCurrentFragment = mAndroidFragment;
        BottomNavigationView bottomNavigationView = findViewById(R.id.activity_main_bottom_nav);
        bottomNavigationView.setSelectedItemId(0);
        bottomNavigationView.getMenu().getItem(3).setEnabled(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.activity_main_bottom_android:
                        if (mAndroidFragment == null) {
                            mAndroidFragment = new AndroidArticleFragment();
                        }
                        switchFragment(mAndroidFragment);
                        break;
                    case R.id.activity_main_bottom_go:
                        if (mGolangFragment == null) {
                            mGolangFragment = new GolangArticleFragment();
                        }
                        switchFragment(mGolangFragment);
                        break;
                    case R.id.activity_main_bottom_java:
                        if (mJavaFragment == null) {
                            mJavaFragment = new JavaArticleFragment();
                        }
                        switchFragment(mJavaFragment);
                        break;
                    case R.id.activity_main_bottom_python:
                        if (mPythonFragment == null) {
                            mPythonFragment = new PythonArticleFragment();
                        }
                        switchFragment(mPythonFragment);
                        break;
                }
                return true;
            }
        });
    }

    private void switchFragment(Fragment to) {
        if (!to.isAdded()) {
            mFragmentManager.beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .add(R.id.fragment_container, to)
                    .commit();
        }
        mFragmentManager.beginTransaction()
                .hide(mCurrentFragment)
                .show(to)
                .commit();
        mCurrentFragment = to;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
