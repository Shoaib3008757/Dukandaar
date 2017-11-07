package rdm.dukandaar;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MenuClickResults extends AppCompatActivity {

    ImageView img_11, img_22, img_33;
    RelativeLayout rl_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_click_results);

        init();


    }

    public void init(){

        img_11 = (ImageView) findViewById(R.id.img_11);
        img_22 = (ImageView) findViewById(R.id.img_22);
        img_33 = (ImageView) findViewById(R.id.img_33);
        rl_info = (RelativeLayout) findViewById(R.id.rl_info);

        Bundle bundle = getIntent().getExtras();
        String cate = bundle.getString("cate");
        String result = bundle.getString("result");
        Log.e("TAG", "result: " + result);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(MenuClickResults.this ,R.color.colorOrangeYellow)));
        getSupportActionBar().setTitle(cate);

        if (result.equals("10")){
            img_11.setImageResource(R.drawable.new_samsung);
            img_22.setImageResource(R.drawable.new_iphone);
            img_33.setImageResource(R.drawable.new_windows_phone);
            infoClickHandler(cate, result);
        }
        if (result.equals("11")){
            img_11.setImageResource(R.drawable.new_samsung_tab);
            img_22.setImageResource(R.drawable.new_apple_tab);
            img_33.setImageResource(R.drawable.new_new_windows_tab);
            infoClickHandler(cate, result);
        }
        if (result.equals("12")){
            img_11.setImageResource(R.drawable.used_samsung_tab);
            img_22.setImageResource(R.drawable.used_apple_tab);
            img_33.setImageResource(R.drawable.used_windows_tab);
            infoClickHandler(cate, result);
        }
        if (result.equals("13")){
            img_11.setImageResource(R.drawable.mobile_accessories_handsfree);
            img_22.setImageResource(R.drawable.mobile_accessories_batry);
            img_33.setImageResource(R.drawable.mobile_asscesories_datacable);
            infoClickHandler(cate, result);
        }
        if (result.equals("14")){
            img_11.setImageResource(R.drawable.new_windows_phone);
            img_22.setImageResource(R.drawable.mobile_accessories_batry);
            img_33.setImageResource(R.drawable.used_apple_tab);
            infoClickHandler(cate, result);
        }

        if (result.equals("20")){
            img_11.setImageResource(R.drawable.car_speaker_1);
            img_22.setImageResource(R.drawable.car_speaker_2);
            img_33.setImageResource(R.drawable.car_stereo_player_1);
            infoClickHandler(cate, result);
        }
        if (result.equals("21")){
            img_11.setImageResource(R.drawable.car_seterio_accessories_2);
            img_22.setImageResource(R.drawable.car_sterio_accessories_3);
            img_33.setImageResource(R.drawable.car_speaker_2);
            infoClickHandler(cate, result);
        }
        if (result.equals("22")){
            img_11.setImageResource(R.drawable.car_stereo_amp_1);
            img_22.setImageResource(R.drawable.car_stereo_apm_2);
            img_33.setImageResource(R.drawable.car_stereo_amp_3);
            infoClickHandler(cate, result);
        }
        if (result.equals("23")){
            img_11.setImageResource(R.drawable.car_stereo_camera_1);
            img_22.setImageResource(R.drawable.car_stereo_camera_2);
            img_33.setImageResource(R.drawable.car_stereo_camera_1);
            infoClickHandler(cate, result);
        }
        if (result.equals("24")){
            img_11.setImageResource(R.drawable.car_stereo_player_1);
            img_22.setImageResource(R.drawable.car_stereo_player_2);
            img_33.setImageResource(R.drawable.car_stereo_player_3);
            infoClickHandler(cate, result);
        }

    }

    public void infoClickHandler(final String categaorty, final String result){

        rl_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MenuClickResults.this, ProductInfo.class);
                i.putExtra("cate", categaorty);
                i.putExtra("result", result);
                startActivity(i);
            }
        });

    }
}
