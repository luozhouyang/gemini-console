package me.stupidme.console.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.stupidme.console.R;
import me.stupidme.console.main.MainActivity;
import me.stupidme.console.utils.LoggerProxy;

public class LoginActivity extends AppCompatActivity implements LoginFragment.OnLoginListener,
        SignUpFragment.OnSignUpListener {

    private ViewPager mViewPager;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        mViewPager = findViewById(R.id.view_pager);
        List<BaseFragment> fragments = new ArrayList<>(2);
        fragments.add(new LoginFragment());
        fragments.add(new SignUpFragment());
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mViewPager.setCurrentItem(position);
                if (position == 0) {
                    mToolbar.setTitle(getString(R.string.action_sign_in));
                } else {
                    mToolbar.setTitle(getString(R.string.action_sign_up));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onLoginSuccess() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onLoginFailed(Exception e) {
        Toast.makeText(this, "Login failed:" + e.getMessage(), Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onSignUpSuccess() {
        LoggerProxy.w("LoginActivity", "Sign Up Success!");
        mViewPager.setCurrentItem(0);
    }

    @Override
    public void onSignUpFailed(Exception e) {
        LoggerProxy.w("LoginActivity", "Sign Up Failed!");
    }

    private static class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<BaseFragment> mFragments;

        public ViewPagerAdapter(FragmentManager fm, List<BaseFragment> fragments) {
            super(fm);
            mFragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }

}

