package rdm.dukandaar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BaseActivityForDrawer extends AppCompatActivity {


    View view_Group;
    public DrawerLayout mDrawerLayout;
    CustomExpandableListAdapter mMenuAdapter;
    ExpandableListView expandableList;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    ImageView search_icon;

    SharedPreferences sharedPreferences;

    TextView tv_header_name;

    //Icons, use as you want
    static int[] icon = {R.drawable.person_image, R.drawable.icon_mobile,
            R.drawable.icon_car, R.drawable.icon_tv_lcd,
            R.drawable.icon_laptop, R.drawable.icon_sun, R.drawable.icon_electricity, R.drawable.icon_games, R.drawable.icon_windows, R.drawable.icon_security, R.drawable.icon_music};

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            expandableList.setIndicatorBounds(expandableList.getRight()- 80, expandableList.getWidth());
        } else {
            expandableList.setIndicatorBoundsRelative(expandableList.getRight()- 80, expandableList.getWidth());
        }
    }

    NavigationView mNavigationView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_base_for_drawer);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        expandableList = (ExpandableListView) findViewById(R.id.navigationmenu);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view) ;
        mNavigationView.setItemIconTintList(null);
        search_icon = (ImageView) findViewById(R.id.search_icon);
        View headerview = mNavigationView.getHeaderView(0);


        //left_drawer = (ExpandableListView) findViewById(R.id.left_drawer);


        // get menu from navigationView
        Menu menu = mNavigationView.getMenu();

        // find MenuItem you want to change
        //navUsername = menu.findItem(R.id.nav_item_name);

        //enableExpandableList();

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();

                return false;
            }

        });




        /**
         * Setup Drawer Toggle of the Toolbar
         */

        //android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();
       // enableExpandableList();


        if (mNavigationView != null) {
            setupDrawerContent(mNavigationView);
        }
        prepareListData();
        mMenuAdapter = new CustomExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expandableList.setAdapter(mMenuAdapter);

        expandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView,
                                        View view,
                                        int groupPosition,
                                        int childPosition, long id) {
                //Log.d("DEBUG", "submenu item clicked");

                String headerPosition =  String.valueOf(groupPosition);
                String itemPosition = String.valueOf(childPosition);

                sharedPreferences = getSharedPreferences("user", 0);
                Log.e("TAG", "sss: " + sharedPreferences);
                if (sharedPreferences!=null){
                    String name = sharedPreferences.getString("name", null);
                    if (name!=null){

                        Log.e("TAG", "Header Posotion: " + headerPosition +  "\n" + "Item Position: " + itemPosition);
                        if (headerPosition.equals("0") && itemPosition.equals("0")){
                            Intent i = new Intent(BaseActivityForDrawer.this, ViewProfile.class);
                            startActivity(i);
                        }
                        if (headerPosition.equals("0") && itemPosition.equals("3")){
                            SharedPreferences.Editor edito = sharedPreferences.edit();
                            edito.clear();
                            edito.commit();
                            finish();
                            Intent i = new Intent(BaseActivityForDrawer.this, MainActivity.class);
                            startActivity(i);
                            Toast.makeText(BaseActivityForDrawer.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
                        }




                    }
                    else {

                        if (headerPosition.equals("0") && itemPosition.equals("0")){
                            Intent memberActivity = new Intent(BaseActivityForDrawer.this, Members.class);
                            memberActivity.putExtra("user_type", 0);// 0 mean whole saller
                            startActivity(memberActivity);
                            finish();
                        }
                        if (headerPosition.equals("0") && itemPosition.equals("1")){
                            Intent memberActivity = new Intent(BaseActivityForDrawer.this, Members.class);
                            memberActivity.putExtra("user_type", 1);// 1 mean Retailer
                            startActivity(memberActivity);
                            finish();
                        }
                        if (headerPosition.equals("0") && itemPosition.equals("2")){
                            Intent memberActivity = new Intent(BaseActivityForDrawer.this, Members.class);
                            memberActivity.putExtra("user_type", 2);// 2 mean Customer
                            startActivity(memberActivity);
                            finish();
                        }

                    }
                }

                //for mobile, tablest and accesories head
                if (headerPosition.equals("1") && itemPosition.equals("0")){
                   // Toast.makeText(BaseActivityForDrawer.this, "New Mobiles", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(BaseActivityForDrawer.this, MenuClickResults.class);
                    i.putExtra("cate", "New Moiles");
                    i.putExtra("result", "10");
                    startActivity(i);
                }
                if (headerPosition.equals("1") && itemPosition.equals("1")){
                    //Toast.makeText(BaseActivityForDrawer.this, "New Tablest", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(BaseActivityForDrawer.this, MenuClickResults.class);
                    i.putExtra("cate", "New Tablests");
                    i.putExtra("result", "11");
                    startActivity(i);
                }
                if (headerPosition.equals("1") && itemPosition.equals("2")){
                    //Toast.makeText(BaseActivityForDrawer.this, "Used Tablests", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(BaseActivityForDrawer.this, MenuClickResults.class);
                    i.putExtra("cate", "Used Tablests");
                    i.putExtra("result", "12");
                    startActivity(i);
                }
                if (headerPosition.equals("1") && itemPosition.equals("3")){
                   // Toast.makeText(BaseActivityForDrawer.this, "Mobile Accesories", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(BaseActivityForDrawer.this, MenuClickResults.class);
                    i.putExtra("cate", "Mobile Accesories");
                    i.putExtra("result", "13");
                    startActivity(i);
                }
                if (headerPosition.equals("1") && itemPosition.equals("4")){
                    //Toast.makeText(BaseActivityForDrawer.this, "Used And Reburicative", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(BaseActivityForDrawer.this, MenuClickResults.class);
                    i.putExtra("cate", "Used And Reburicative");
                    i.putExtra("result", "14");
                    startActivity(i);
                }

                //for car stero and accessories tab
                if (headerPosition.equals("2") && itemPosition.equals("0")){
                    //Toast.makeText(BaseActivityForDrawer.this, "Car Speakers", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(BaseActivityForDrawer.this, MenuClickResults.class);
                    i.putExtra("cate", "Car Speakers");
                    i.putExtra("result", "20");
                    startActivity(i);
                }
                if (headerPosition.equals("2") && itemPosition.equals("1")){
                    //Toast.makeText(BaseActivityForDrawer.this, "Car steroe accesoreis", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(BaseActivityForDrawer.this, MenuClickResults.class);
                    i.putExtra("cate", "Car steroe accesoreis");
                    i.putExtra("result", "21");
                    startActivity(i);
                }
                if (headerPosition.equals("2") && itemPosition.equals("2")){
                    //Toast.makeText(BaseActivityForDrawer.this, "Care Stereo Amps", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(BaseActivityForDrawer.this, MenuClickResults.class);
                    i.putExtra("cate", "Care Stereo Amps");
                    i.putExtra("result", "22");
                    startActivity(i);
                }
                if (headerPosition.equals("2") && itemPosition.equals("3")){
                    //Toast.makeText(BaseActivityForDrawer.this, "Car Stereo Cameras", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(BaseActivityForDrawer.this, MenuClickResults.class);
                    i.putExtra("cate", "Car Stereo Cameras");
                    i.putExtra("result", "23");
                    startActivity(i);
                }
                if (headerPosition.equals("2") && itemPosition.equals("4")){
                    //Toast.makeText(BaseActivityForDrawer.this, "Car Stereo Player", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(BaseActivityForDrawer.this, MenuClickResults.class);
                    i.putExtra("cate", "Car Stereo Player");
                    i.putExtra("result", "24");
                    startActivity(i);
                }

                //for TV LCD and Sound System
                if (headerPosition.equals("3") && itemPosition.equals("0")){
                    Toast.makeText(BaseActivityForDrawer.this, "China LCD", Toast.LENGTH_SHORT).show();
                }
                if (headerPosition.equals("3") && itemPosition.equals("1")){
                    Toast.makeText(BaseActivityForDrawer.this, "Used, T.V Led and lcds", Toast.LENGTH_SHORT).show();
                }
                if (headerPosition.equals("3") && itemPosition.equals("2")){
                    Toast.makeText(BaseActivityForDrawer.this, "New, TV led and lcds", Toast.LENGTH_SHORT).show();
                }

                //for Computer laptops and accessories
                if (headerPosition.equals("4") && itemPosition.equals("0")){
                    Toast.makeText(BaseActivityForDrawer.this, "Accessories", Toast.LENGTH_SHORT).show();
                }
                if (headerPosition.equals("4") && itemPosition.equals("1")){
                    Toast.makeText(BaseActivityForDrawer.this, "New laptop computer and accesories", Toast.LENGTH_SHORT).show();
                }
                if (headerPosition.equals("4") && itemPosition.equals("2")){
                    Toast.makeText(BaseActivityForDrawer.this, "Used Laptop computers and accessories", Toast.LENGTH_SHORT).show();
                }
                if (headerPosition.equals("4") && itemPosition.equals("3")){
                    Toast.makeText(BaseActivityForDrawer.this, "Wireless Routers", Toast.LENGTH_SHORT).show();
                }
                if (headerPosition.equals("4") && itemPosition.equals("4")){
                    Toast.makeText(BaseActivityForDrawer.this, "Scanners", Toast.LENGTH_SHORT).show();
                }

                if (headerPosition.equals("4") && itemPosition.equals("5")){
                    Toast.makeText(BaseActivityForDrawer.this, "Cameras", Toast.LENGTH_SHORT).show();
                }

                //for Solar System and Accessories
                if (headerPosition.equals("5") && itemPosition.equals("0")){
                    Toast.makeText(BaseActivityForDrawer.this, "12 Volt Solar Accessories", Toast.LENGTH_SHORT).show();
                }
                if (headerPosition.equals("5") && itemPosition.equals("1")){
                    Toast.makeText(BaseActivityForDrawer.this, "Batteries", Toast.LENGTH_SHORT).show();
                }
                if (headerPosition.equals("5") && itemPosition.equals("2")){
                    Toast.makeText(BaseActivityForDrawer.this, "Hybrid Inverters", Toast.LENGTH_SHORT).show();
                }
                if (headerPosition.equals("5") && itemPosition.equals("3")){
                    Toast.makeText(BaseActivityForDrawer.this, "Solar Pv Module", Toast.LENGTH_SHORT).show();
                }


                //for Electronics and Spare Parts
                if (headerPosition.equals("6") && itemPosition.equals("0")){
                    Toast.makeText(BaseActivityForDrawer.this, "Capacitors", Toast.LENGTH_SHORT).show();
                }
                if (headerPosition.equals("6") && itemPosition.equals("1")){
                    Toast.makeText(BaseActivityForDrawer.this, "LED circuit", Toast.LENGTH_SHORT).show();
                }
                if (headerPosition.equals("6") && itemPosition.equals("2")){
                    Toast.makeText(BaseActivityForDrawer.this, "LED Drivers", Toast.LENGTH_SHORT).show();
                }
                if (headerPosition.equals("6") && itemPosition.equals("3")){
                    Toast.makeText(BaseActivityForDrawer.this, "Other Spare Parts", Toast.LENGTH_SHORT).show();
                }
                if (headerPosition.equals("6") && itemPosition.equals("4")){
                    Toast.makeText(BaseActivityForDrawer.this, "Remotes", Toast.LENGTH_SHORT).show();
                }
                if (headerPosition.equals("6") && itemPosition.equals("5")){
                    Toast.makeText(BaseActivityForDrawer.this, "Transistors", Toast.LENGTH_SHORT).show();
                }

                //for Games and Accessories
                if (headerPosition.equals("7") && itemPosition.equals("0")){
                    Toast.makeText(BaseActivityForDrawer.this, "Gaming pads and accessories", Toast.LENGTH_SHORT).show();
                }
                if (headerPosition.equals("7") && itemPosition.equals("1")){
                    Toast.makeText(BaseActivityForDrawer.this, "PSP and play station", Toast.LENGTH_SHORT).show();
                }
                if (headerPosition.equals("7") && itemPosition.equals("2")){
                    Toast.makeText(BaseActivityForDrawer.this, "X Box", Toast.LENGTH_SHORT).show();
                }
                if (headerPosition.equals("7") && itemPosition.equals("3")){
                    Toast.makeText(BaseActivityForDrawer.this, "X Box Play station and PSP Games", Toast.LENGTH_SHORT).show();
                }

                //for DVD and Software
                if (headerPosition.equals("8") && itemPosition.equals("0")){
                    Toast.makeText(BaseActivityForDrawer.this, "Audio and videos", Toast.LENGTH_SHORT).show();
                }
                if (headerPosition.equals("8") && itemPosition.equals("1")){
                    Toast.makeText(BaseActivityForDrawer.this, "Movies", Toast.LENGTH_SHORT).show();
                }
                if (headerPosition.equals("8") && itemPosition.equals("2")){
                    Toast.makeText(BaseActivityForDrawer.this, "Softwares", Toast.LENGTH_SHORT).show();
                }


                //for Games and Accessories
                if (headerPosition.equals("9") && itemPosition.equals("0")){
                    Toast.makeText(BaseActivityForDrawer.this, "CCTV Cameras and equipments", Toast.LENGTH_SHORT).show();
                }
                if (headerPosition.equals("9") && itemPosition.equals("1")){
                    Toast.makeText(BaseActivityForDrawer.this, "Communication Networking", Toast.LENGTH_SHORT).show();
                }
                if (headerPosition.equals("9") && itemPosition.equals("2")){
                    Toast.makeText(BaseActivityForDrawer.this, "DVR", Toast.LENGTH_SHORT).show();
                }
                if (headerPosition.equals("9") && itemPosition.equals("3")){
                    Toast.makeText(BaseActivityForDrawer.this, "Other Accessories", Toast.LENGTH_SHORT).show();
                }

                //for Games and Accessories
                if (headerPosition.equals("10") && itemPosition.equals("0")){
                    Toast.makeText(BaseActivityForDrawer.this, "Amplifier", Toast.LENGTH_SHORT).show();
                }
                if (headerPosition.equals("10") && itemPosition.equals("1")){
                    Toast.makeText(BaseActivityForDrawer.this, "Woofers", Toast.LENGTH_SHORT).show();
                }




                /*Toast.makeText(BaseActivityForDrawer.this,
                        "Header: "+String.valueOf(groupPosition) +
                                "\nItem: "+ String.valueOf(childPosition), Toast.LENGTH_SHORT)
                        .show();
                */

                view.setSelected(true);

                /*   if (view_Group != null) {
                    view_Group.setBackgroundColor(Color.parseColor("#ffffff"));
                }
                view_Group = view;
                view_Group.setBackgroundColor(Color.parseColor("#DDDDDD"));*/
                mDrawerLayout.closeDrawers();
                return false;
            }
        });
        expandableList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                //Log.d("DEBUG", "heading clicked");
                return false;
            }
        });

        seartSearchActivity();

    }//end on Create

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();




        sharedPreferences = getSharedPreferences("user", 0);
        Log.e("TAG", "sss: " + sharedPreferences);
        if (sharedPreferences!=null){
            String name = sharedPreferences.getString("name", null);
            if (name!=null){

                listDataHeader.add(name);
                listDataHeader.add("Mobile, Tablet, Accesories");
                listDataHeader.add("Car Stereo & Accesories");
                listDataHeader.add("TV, LCD & Sound System");
                listDataHeader.add("Computer, Laptop & Accesories");
                listDataHeader.add("Solar System & Accesories");
                listDataHeader.add("Electronics & Sapre Parts");
                listDataHeader.add("Games & Accesories");
                listDataHeader.add("DVD & Softwares");
                listDataHeader.add("Security Cameras & Equipments");
                listDataHeader.add("Hifi Sound, DJ's & PA System");




                List<String> heading1 = new ArrayList<String>();
                heading1.add("View Profile");
                heading1.add("My favourites");
                heading1.add("Change Password");
                heading1.add("Logout");

                List<String> heading2 = new ArrayList<String>();
                heading2.add("New Mobiles");
                heading2.add("New Tablets");
                heading2.add("Used Tablets");
                heading2.add("Mobile Accesories");
                heading2.add("Used & Refubricative");

                List<String> heading3 = new ArrayList<String>();
                heading3.add("Car Speakers");
                heading3.add("Car Stereo Accesories");
                heading3.add("Car Stereo Amps");
                heading3.add("Car Stereo Cameras");
                heading3.add("Car Stereo Player");

                List<String> heading4 = new ArrayList<String>();
                heading4.add("Chine Lcd");
                heading4.add("Used T.V, LED & LCDs");
                heading4.add("New T.V, LED & LCDs");

                List<String> heading5 = new ArrayList<String>();
                heading5.add("Accesories");
                heading5.add("New Laptops, Computer & Accesories");
                heading5.add("Used Laptops, Computer & Accesories");
                heading5.add("Wireless Routers");
                heading5.add("Scanners");
                heading5.add("Cameras");

                List<String> heading6 = new ArrayList<String>();
                heading6.add("12Volt Solar Accessories");
                heading6.add("Batteries");
                heading6.add("Hybrid Inverters");
                heading6.add("Solar Pv Module");

                List<String> heading7 = new ArrayList<String>();
                heading7.add("Capacitors");
                heading7.add("L.E.D Circuit");
                heading7.add("LED Drivers");
                heading7.add("Other Spare Parts");
                heading7.add("Remotes");
                heading7.add("Transistors");

                List<String> heading8 = new ArrayList<String>();
                heading8.add("Gaming Pads & Accessories");
                heading8.add("PSP & Play Stations");
                heading8.add("X Box");
                heading8.add("X Box, Play Station & PSP Games");


                List<String> heading9 = new ArrayList<String>();
                heading9.add("Audios & Videos");
                heading9.add("Movies");
                heading9.add("Softwares");

                List<String> heading10 = new ArrayList<String>();
                heading10.add("CCTV Cameras");
                heading10.add("Communication Networking");
                heading10.add("DVR");
                heading10.add("Other Accesories");

                List<String> heading11 = new ArrayList<String>();
                heading11.add("Amplifier");
                heading11.add("Woofers");



                listDataChild.put(listDataHeader.get(0), heading1);
                listDataChild.put(listDataHeader.get(1), heading2);
                listDataChild.put(listDataHeader.get(2), heading3);
                listDataChild.put(listDataHeader.get(3), heading4);
                listDataChild.put(listDataHeader.get(4), heading5);
                listDataChild.put(listDataHeader.get(5), heading6);
                listDataChild.put(listDataHeader.get(6), heading7);
                listDataChild.put(listDataHeader.get(7), heading8);
                listDataChild.put(listDataHeader.get(8), heading9);
                listDataChild.put(listDataHeader.get(9), heading10);
                listDataChild.put(listDataHeader.get(10), heading11);


            }
            else {

                //listDataHeader.add("Name");
                listDataHeader.add("Login/Register");
                listDataHeader.add("Mobile, Tablet, Accesories");
                listDataHeader.add("Car Stereo & Accesories");
                listDataHeader.add("TV, LCD & Sound System");
                listDataHeader.add("Computer, Laptop & Accesories");
                listDataHeader.add("Solar System & Accesories");
                listDataHeader.add("Electronics & Sapre Parts");
                listDataHeader.add("Games & Accesories");
                listDataHeader.add("DVD & Softwares");
                listDataHeader.add("Security Cameras & Equipments");
                listDataHeader.add("Hifi Sound, DJ's & PA System");




                List<String> heading1 = new ArrayList<String>();
                heading1.add("Whole Saller");
                heading1.add("Retailer");
                heading1.add("Customer");

                List<String> heading2 = new ArrayList<String>();
                heading2.add("New Mobiles");
                heading2.add("New Tablets");
                heading2.add("Used Tablets");
                heading2.add("Mobile Accesories");
                heading2.add("Used & Refubricative");

                List<String> heading3 = new ArrayList<String>();
                heading3.add("Car Speakers");
                heading3.add("Car Stereo Accesories");
                heading3.add("Car Stereo Amps");
                heading3.add("Car Stereo Cameras");
                heading3.add("Car Stereo Player");

                List<String> heading4 = new ArrayList<String>();
                heading4.add("Chine Lcd");
                heading4.add("Used T.V, LED & LCDs");
                heading4.add("New T.V, LED & LCDs");

                List<String> heading5 = new ArrayList<String>();
                heading5.add("Accesories");
                heading5.add("New Laptops, Computer & Accesories");
                heading5.add("Used Laptops, Computer & Accesories");
                heading5.add("Wireless Routers");
                heading5.add("Scanners");
                heading5.add("Cameras");

                List<String> heading6 = new ArrayList<String>();
                heading6.add("12Volt Solar Accessories");
                heading6.add("Batteries");
                heading6.add("Hybrid Inverters");
                heading6.add("Solar Pv Module");

                List<String> heading7 = new ArrayList<String>();
                heading7.add("Capacitors");
                heading7.add("L.E.D Circuit");
                heading7.add("LED Drivers");
                heading7.add("Other Spare Parts");
                heading7.add("Remotes");
                heading7.add("Transistors");

                List<String> heading8 = new ArrayList<String>();
                heading8.add("Gaming Pads & Accessories");
                heading8.add("PSP & Play Stations");
                heading8.add("X Box");
                heading8.add("X Box, Play Station & PSP Games");


                List<String> heading9 = new ArrayList<String>();
                heading9.add("Audios & Videos");
                heading9.add("Movies");
                heading9.add("Softwares");

                List<String> heading10 = new ArrayList<String>();
                heading10.add("CCTV Cameras");
                heading10.add("Communication Networking");
                heading10.add("DVR");
                heading10.add("Other Accesories");

                List<String> heading11 = new ArrayList<String>();
                heading11.add("Amplifier");
                heading11.add("Woofers");



                listDataChild.put(listDataHeader.get(0), heading1);
                listDataChild.put(listDataHeader.get(1), heading2);
                listDataChild.put(listDataHeader.get(2), heading3);
                listDataChild.put(listDataHeader.get(3), heading4);
                listDataChild.put(listDataHeader.get(4), heading5);
                listDataChild.put(listDataHeader.get(5), heading6);
                listDataChild.put(listDataHeader.get(6), heading7);
                listDataChild.put(listDataHeader.get(7), heading8);
                listDataChild.put(listDataHeader.get(8), heading9);
                listDataChild.put(listDataHeader.get(9), heading10);
                listDataChild.put(listDataHeader.get(10), heading11);

            }
        }


    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    public void seartSearchActivity(){
        search_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent searchActivity = new Intent(BaseActivityForDrawer.this, SearchCategory.class);
                startActivity(searchActivity);
            }
        });
    }


}
