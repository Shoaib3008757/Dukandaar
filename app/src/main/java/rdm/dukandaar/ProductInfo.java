package rdm.dukandaar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

import rdm.dukandaar.adapters.RecylerAdapterForInfo;

public class ProductInfo extends BaseActivityForDrawer {

    private RecyclerView recyclerView;
    private RecylerAdapterForInfo mAdapter;

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

        recyclerView = (RecyclerView) findViewById(R.id.info_recyler_view);



        mAdapter = new RecylerAdapterForInfo(ProductInfo.this, icon);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(ProductInfo.this, LinearLayoutManager.HORIZONTAL, false);


        recyclerView.setLayoutManager(horizontalLayoutManagaer);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);




    }
}
