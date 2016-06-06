package demo.myapplication.news.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import demo.myapplication.MainActivity;
import demo.myapplication.R;

/**
 * Created by FT_ZSS on 2016/6/6.
 */
public class NewsFragment extends Fragment {


    public static final int NEWS_TYPE_TOP = 0;
    public static final int NEWS_TYPE_NBA = 1;
    public static final int NEWS_TYPE_CARS = 2;
    public static final int NEWS_TYPE_JOKES = 3;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news,null);
        mTabLayout = (TabLayout)view.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager)view.findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(3);
        setupViewPage(mViewPager);

        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.top));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.nba));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.cars));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.jokes));
        mTabLayout.setupWithViewPager(mViewPager);
        return view;
    }


    private void setupViewPage(ViewPager viewPager){

        MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager());
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_TOP),"头条");
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_NBA),"NBA");
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_CARS),"汽车");
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_JOKES),"笑话");
        viewPager.setAdapter(adapter);
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}

