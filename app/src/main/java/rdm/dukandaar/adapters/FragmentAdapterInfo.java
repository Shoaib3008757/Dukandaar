package rdm.dukandaar.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import rdm.dukandaar.fragments.FragmentFeatureCategory;
import rdm.dukandaar.fragments.FragmentInfoDescription;
import rdm.dukandaar.fragments.FragmentInfoSpecification;
import rdm.dukandaar.fragments.FragmentMostView;
import rdm.dukandaar.fragments.FragmentSeller;


public class FragmentAdapterInfo extends FragmentStatePagerAdapter {

    int mNumberOfTabs;

    public FragmentAdapterInfo(FragmentManager fm, int NumberOfTabs){
        super(fm);
        this.mNumberOfTabs = NumberOfTabs;
    }

    @Override
    public Fragment getItem(int position){

        switch (position){
            case  0:
                FragmentInfoDescription pageOne = new FragmentInfoDescription();
                return pageOne;
            case 1:
                FragmentInfoSpecification pageTwo = new FragmentInfoSpecification();
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
