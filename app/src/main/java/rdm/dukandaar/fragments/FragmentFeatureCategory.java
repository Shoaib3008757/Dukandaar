package rdm.dukandaar.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import rdm.dukandaar.R;


/**
 * Created by User-10 on 30-Oct-17.
 */

public class FragmentFeatureCategory extends Fragment {

public static RelativeLayout rl_share;
public static RelativeLayout rl_buy;
    public static RelativeLayout rl_info;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.page_one, container, false);


        rl_share = (RelativeLayout)view.findViewById(R.id.rl_share);
        rl_buy = (RelativeLayout) view.findViewById(R.id.rl_buy);
        rl_info = (RelativeLayout) view.findViewById(R.id.rl_info);

        rl_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Sample Text Share");
                startActivity(Intent.createChooser(intent, "Share"));
            }
        });

        return view;
    }



}
