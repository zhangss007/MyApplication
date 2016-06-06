package demo.myapplication.main.widget;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import demo.myapplication.R;
import demo.myapplication.about.widget.AboutFragment;
import demo.myapplication.images.widget.ImageFragment;
import demo.myapplication.main.presenter.MainPresenter;
import demo.myapplication.main.presenter.MainPresenterImpl;
import demo.myapplication.main.view.MainView;
import demo.myapplication.news.widget.NewsFragment;

/**
 * Created by FT_ZSS on 2016/6/3.
 */
public class MainActivity1 extends AppCompatActivity implements MainView{

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolBar;

    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationView;
    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        mToolBar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolBar,R.string.drawer_open,R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mNavigationView = (NavigationView)findViewById(R.id.navigation_view);
        setupDrawerContent(mNavigationView);

        mMainPresenter = new MainPresenterImpl(this);
        switch2News();
    }


    private void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                mMainPresenter.switchNavigation(item.getItemId());
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public void switch2News() {

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new NewsFragment()).commit();
        mToolBar.setTitle("新闻");
    }

    @Override
    public void switch2Images() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new ImageFragment()).commit();
        mToolBar.setTitle("图片");
    }

    @Override
    public void switch2Weather() {

        mToolBar.setTitle("天气");
    }

    @Override
    public void switch2About() {

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new AboutFragment()).commit();
        mToolBar.setTitle("关于");
    }
}

