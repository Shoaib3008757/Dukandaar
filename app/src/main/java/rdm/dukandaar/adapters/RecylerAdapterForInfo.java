package rdm.dukandaar.adapters;

import android.app.Activity;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

import rdm.dukandaar.MainActivity;
import rdm.dukandaar.R;
import rdm.dukandaar.TouchImageView;
import rdm.dukandaar.loader.ImagesLoader;

/**
 * Created by User-10 on 03-Nov-17.
 */

public class RecylerAdapterForInfo extends RecyclerView.Adapter<RecylerAdapterForInfo.MyViewHolder> {


    private int lastPosition = -1;

    private Activity activity;
    private String[] data;
   int[] icon;
    private static LayoutInflater inflater=null;
    public ImagesLoader imageLoader;

    ProgressBar progressBar;

    private Bitmap image = null;
    boolean mExpanded;

    public RecylerAdapterForInfo(Activity a, int[] icon) {
        activity = a;
        //data=d;
        this.icon = icon;

       // imageLoader=new ImagesLoader(activity.getApplicationContext());

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.info_image, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {




          //imageLoader.DisplayImage(icon.length, holder.imageView);
        holder.imageView.setImageResource(icon[position]);

    /*    Glide.with(activity)
                .load(contactList.get(position).get("imageurl"))
                .placeholder(R.drawable.default_image)
                //.error(R.drawable.)
                .override(200, 200)
                .centerCrop()
                .into(holder.imageView);

*/

/*
        Animation animation = AnimationUtils.loadAnimation(activity, (position > lastPosition) ? R.anim.down_from_top : R.anim.wave_scaling);
        holder.imageView.startAnimation(animation);*/
        lastPosition = position;



/*
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                final String url = contactList.get(position).get("imageurl");
                String fileName = url.substring( url.lastIndexOf('/')+1, url.length() );

                final String fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));

                Glide
                        .with(activity)
                        .load(url)
                        .asBitmap()
                        .into(new SimpleTarget<Bitmap>(300,300) {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                                showSingleImage(url, resource);

                            }
                        });




                Toast.makeText(activity, "Please Wait...", Toast.LENGTH_SHORT).show();
            }
        });
*/




    }

    @Override
    public int getItemCount() {

        return icon.length;


    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TouchImageView imageView;
        ViewGroup transitionsContainer;
        TouchImageView imageView1;
        TextView single_image;

        public MyViewHolder(View view) {
            super(view);

            imageView = (TouchImageView)view.findViewById(R.id.single_image);
           // transitionsContainer = (ViewGroup) view.findViewById(R.id.transitions_container);

            // single_image = (TextView) view.findViewById(R.id.single_image);



        }
    }

/*

    public void showSingleImage(final String imageUrl, Bitmap bitmap){


        final Dialog dialog = new Dialog(activity);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.single_property_image);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        RelativeLayout rr_dialog = (RelativeLayout)dialog.findViewById(R.id.rr_dialog);

   */
/*     final Animation myAnim = AnimationUtils.loadAnimation(activity, R.anim.fade_out);
        rr_dialog.startAnimation(myAnim);*//*


        TouchImageView singleImagea = (TouchImageView) dialog.findViewById(R.id.im_single);
        Button bt_image_save = (Button) dialog.findViewById(R.id.bt_image_save);


        singleImagea.setImageBitmap(bitmap);


        dialog.show();


    }
*/


}
