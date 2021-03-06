package com.zhonglushu.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.zhonglushu.example.hovertab.HoverTabActivity;
import com.zhonglushu.example.hovertab.fragment.ObservableBaseFragment;

public class MainActivity extends HoverTabActivity {

    private View tab1;
    private View tab2;
    private View tab3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //headerview
        View adView = inflater.inflate(R.layout.header_layout, null);
        ViewPager viewPager = (ViewPager) adView.findViewById(R.id.header_pager);
        AdPagerAdapter pagerAdapter = new AdPagerAdapter();
        viewPager.setAdapter(pagerAdapter);
        setHoverHeaderView(adView);

        //tabview
        View tabview = inflater.inflate(R.layout.tab_layout, null);
        tab1 = tabview.findViewById(R.id.tab1);
        tab2 = tabview.findViewById(R.id.tab2);
        tab3 = tabview.findViewById(R.id.tab3);
        tab1.setOnClickListener(mListener);
        tab2.setOnClickListener(mListener);
        tab3.setOnClickListener(mListener);
        tab1.setSelected(true);
        setHoverTabView(tabview);

        //viewpager adapter
        setmPagerAdapter(new LocalPagerAdapter(this.getSupportFragmentManager()));

        setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    tab1.setSelected(true);
                    tab2.setSelected(false);
                    tab3.setSelected(false);
                } else if (position == 1) {
                    tab1.setSelected(false);
                    tab2.setSelected(true);
                    tab3.setSelected(false);
                } else if (position == 2) {
                    tab1.setSelected(false);
                    tab2.setSelected(false);
                    tab3.setSelected(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //auto refresh
        setManualRefreshing();
    }

    @Override
    public void onRefreshHeader() {
        super.onRefreshHeader();
    }

    private View.OnClickListener mListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.tab1:
                    setViewPagerCurrentItem(0);
                    break;
                case R.id.tab2:
                    setViewPagerCurrentItem(1);
                    break;
                case R.id.tab3:
                    setViewPagerCurrentItem(2);
                    break;
            }
        }
    };

    private class LocalPagerAdapter extends HoverTabFragmentStatePagerAdapter{

        public LocalPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getTabCount() {
            return 3;
        }

        @Override
        public ObservableBaseFragment createTab(int position) {
            ObservableBaseFragment f;
            if(position == 0){
                f = new Test1Fragment();
            }else if(position == 1){
                f = new Test1Fragment();
            }else{
                f = new Test2Fragment();
            }
            return f;
        }
    }

    public class AdPagerAdapter extends AbstractViewPagerAdapter {

        @Override
        public View newView(int position) {
            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(R.drawable.ad);
            imageView.setTag(position);
            return imageView;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
