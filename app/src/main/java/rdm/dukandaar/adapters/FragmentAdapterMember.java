package rdm.dukandaar.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import rdm.dukandaar.fragments.FragmentLogin;
import rdm.dukandaar.fragments.FragmentRegister;

/**
 * Created by User-10 on 01-Nov-17.
 */

public class FragmentAdapterMember extends FragmentStatePagerAdapter {

    int mNumberOfTabs;

    public FragmentAdapterMember(FragmentManager fm, int NumberOfTabs){
        super(fm);
        this.mNumberOfTabs = NumberOfTabs;
    }

    @Override
    public Fragment getItem(int position){

        switch (position){
            case  0:
                FragmentLogin frgLogin = new FragmentLogin();
                return frgLogin;
            case 1:
                FragmentRegister frgRegister = new FragmentRegister();
                return frgRegister;
            default:
                FragmentLogin Default = new FragmentLogin();
                return Default;
        }
    }

    @Override
    public int getCount(){
        return mNumberOfTabs;
    }
}
