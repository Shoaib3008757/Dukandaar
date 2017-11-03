package rdm.dukandaar.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import rdm.dukandaar.fragments.FragmentFeatureCategory;
import rdm.dukandaar.fragments.FragmentMostView;
import rdm.dukandaar.fragments.FragmentSeller;

/**
 * Created by User-10 on 30-Oct-17.
 */

public class FragmentAdapterMain extends FragmentStatePagerAdapter {

        int mNumberOfTabs;

public FragmentAdapterMain(FragmentManager fm, int NumberOfTabs){
        super(fm);
        this.mNumberOfTabs = NumberOfTabs;
        }

@Override
public Fragment getItem(int position){

        switch (position){
        case  0:
        FragmentFeatureCategory pageOne = new FragmentFeatureCategory();
        return pageOne;
        case 1:
        FragmentMostView pageTwo = new FragmentMostView();
        return pageTwo;
        case 2:
        FragmentSeller pageThree = new FragmentSeller();
        return pageThree;

default:
        FragmentFeatureCategory pageOneDefault = new FragmentFeatureCategory();
        return pageOneDefault;
        }
        }

@Override
public int getCount(){
        return mNumberOfTabs;
        }
        }
