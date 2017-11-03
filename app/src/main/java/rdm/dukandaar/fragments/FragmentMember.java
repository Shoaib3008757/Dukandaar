package rdm.dukandaar.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import rdm.dukandaar.R;
import rdm.dukandaar.adapters.FragmentAdapterMember;

/**
 * Created by User-10 on 01-Nov-17.
 */

public class FragmentMember  extends Fragment {

    ViewPager mViewPager;
    FragmentTabHost abtest;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.member, container, false);

        TabLayout tabLayout = (TabLayout)view.findViewById(R.id.tab_layout);

        abtest = (FragmentTabHost) view.findViewById(R.id.abtest);
        //adding tabs
        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Sign Up"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //setting adapter to viewpager
        mViewPager = (ViewPager)view.findViewById(R.id.pager);
        final FragmentAdapterMember adapter = new FragmentAdapterMember
                (getActivity().getSupportFragmentManager(), tabLayout.getTabCount());

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

                    Fragment FragmentLogin = new FragmentLogin();
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.replace(R.id.abtest, FragmentLogin).commit();
                }
                if (position==1){

                    Fragment FragmentRegister = new FragmentRegister();
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.replace(R.id.abtest, FragmentRegister).commit();
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        Fragment FragmentRegister = new FragmentRegister();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.abtest, FragmentRegister).commit();

        return view;
    }



}
