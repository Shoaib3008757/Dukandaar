package rdm.dukandaar;

import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import rdm.dukandaar.adapters.FragmentAdapterInfo;
import rdm.dukandaar.adapters.RecylerAdapterForInfo;
import rdm.dukandaar.adapters.FragmentAdapterMember;

public class ProductInfo extends BaseActivityForDrawer {

    private RecyclerView recyclerView;
    private RecylerAdapterForInfo mAdapter;
    NonSwipeableViewPager mViewPager;
    TabLayout tabLayout;

    public int[] icon = {R.drawable.img_printer, R.drawable.saller,
            R.drawable.most_views_img};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_product_info);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_product_info, null, false);
        mDrawerLayout.addView(contentView, 0);

        init();
        recylerHandler();
        tabsHandling();

    }

    public void init(){

        recyclerView = (RecyclerView) findViewById(R.id.info_recyler_view);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

    }

    public void recylerHandler(){

        mAdapter = new RecylerAdapterForInfo(ProductInfo.this, icon);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(ProductInfo.this, LinearLayoutManager.HORIZONTAL, false);


        recyclerView.setLayoutManager(horizontalLayoutManagaer);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    public void tabsHandling(){

        //adding tabs
        tabLayout.addTab(tabLayout.newTab().setText("Description"));
        tabLayout.addTab(tabLayout.newTab().setText("Specification"));
        tabLayout.addTab(tabLayout.newTab().setText("Reviews"));
        tabLayout.addTab(tabLayout.newTab().setText("Custome Tab"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //setting adapter to viewpager
        mViewPager = (NonSwipeableViewPager) findViewById(R.id.pager);
        final FragmentAdapterInfo adapter = new FragmentAdapterInfo
                (getSupportFragmentManager(), tabLayout.getTabCount());

        mViewPager.setAdapter(adapter);

        // mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                mViewPager.setCurrentItem(tab.getPosition());

                int position = tab.getPosition();
                if (position==0){


                }
                if (position==1){

                    // Toast.makeText(MainActivity.this, "Seller", Toast.LENGTH_SHORT).show();
                }
                if (position==2){

                    // Toast.makeText(MainActivity.this, "Seller", Toast.LENGTH_SHORT).show();
                }
                if (position==3){

                    // Toast.makeText(MainActivity.this, "Seller", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

}
