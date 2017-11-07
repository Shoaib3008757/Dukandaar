package rdm.dukandaar;

import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ViewProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(ViewProfile.this ,R.color.colorOrangeYellow)));
        getSupportActionBar().setTitle("User Profile");
    }
}
