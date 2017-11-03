package rdm.dukandaar;

import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import rdm.dukandaar.adapters.FragmentAdapterMain;
import rdm.dukandaar.fragments.FragmentFeatureCategory;
import rdm.dukandaar.fragments.FragmentMostView;
import rdm.dukandaar.fragments.FragmentSeller;

public class MainActivity extends BaseActivityForDrawer {

    NonSwipeableViewPager mViewPager;
    FragmentFeatureCategory pageOne;
    FragmentMostView pageTwo;
    FragmentSeller pageThree;
    int currentPosition = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_main, null, false);
        mDrawerLayout.addView(contentView, 0);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        pageOne = new FragmentFeatureCategory();
        pageTwo = new FragmentMostView();
        pageThree = new FragmentSeller();
        //adding tabs
        tabLayout.addTab(tabLayout.newTab().setText("Featured Categories"));
        tabLayout.addTab(tabLayout.newTab().setText("Seller"));
        tabLayout.addTab(tabLayout.newTab().setText("Most Viewed"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //setting adapter to viewpager
        mViewPager = (NonSwipeableViewPager) findViewById(R.id.pager);
        final FragmentAdapterMain adapter = new FragmentAdapterMain
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

                 //Toast.makeText(MainActivity.this, "Most Viewed", Toast.LENGTH_SHORT).show();
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
