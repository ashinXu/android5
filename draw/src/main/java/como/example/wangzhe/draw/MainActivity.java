package como.example.wangzhe.draw;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private ListView lv_drawer;
    private ArrayAdapter mAdapter;
    private LinearLayout ll_drawer;
    private int currentFragmentId = 0;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDrawerLayout();

    }

    private void initDrawerLayout() {

        List<String> items = new ArrayList<>();
        items.add("新闻资讯");
        items.add("视频");
        items.add("天气");


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("侧滑菜单");
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close);

        mToggle.syncState();//实现联动

        mDrawerLayout.addDrawerListener(mToggle);

        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.ll_content, ContentFragment.newInstance("新闻资讯"));

        fragmentTransaction.commit();


        lv_drawer = (ListView) findViewById(R.id.lv_drawer);

        mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);
        lv_drawer.setAdapter(mAdapter);
        ll_drawer = (LinearLayout) findViewById(R.id.ll_drawer);
        lv_drawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switchFragment(position);
            }
        });
    }

    private void switchFragment(int fragmentId) {

        mDrawerLayout.closeDrawer(ll_drawer);
        if (currentFragmentId == fragmentId)
            return;
        currentFragmentId = fragmentId;
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        switch (fragmentId) {
            case 0:
                fragmentTransaction.replace(R.id.ll_content, ContentFragment.newInstance("新闻资讯"));
                break;
            case 1:
                fragmentTransaction.replace(R.id.ll_content, ContentFragment.newInstance("视频"));
                break;
            case 2:
                fragmentTransaction.replace(R.id.ll_content, ContentFragment.newInstance("天气"));
                break;
        }
        fragmentTransaction.commit();

    }


}
