package rdm.dukandaar;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import rdm.dukandaar.fragments.FragmentAdapterMain;
import rdm.dukandaar.fragments.FragmentAdapterMember;
import rdm.dukandaar.fragments.FragmentFeatureCategory;
import rdm.dukandaar.fragments.FragmentMostView;
import rdm.dukandaar.fragments.FragmentSeller;

public class Members extends BaseActivityForDrawer {

    NonSwipeableViewPager mViewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_members);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_members, null, false);
        mDrawerLayout.addView(contentView, 0);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        //adding tabs
        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Sign Up"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //setting adapter to viewpager
        mViewPager = (NonSwipeableViewPager) findViewById(R.id.pager);
        final FragmentAdapterMember adapter = new FragmentAdapterMember
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


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i = new Intent(Members.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
