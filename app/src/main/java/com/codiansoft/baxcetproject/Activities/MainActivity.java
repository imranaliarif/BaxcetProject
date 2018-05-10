package com.codiansoft.baxcetproject.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codiansoft.baxcetproject.CustomClasses.GlobalClass;
import com.codiansoft.baxcetproject.Fragments.DashBoardFragment;
import com.codiansoft.baxcetproject.Fragments.LocationFragment;
import com.codiansoft.baxcetproject.Fragments.MyOrdersFragment;

import com.codiansoft.baxcetproject.R;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static int navItemIndex = 0;

    private NavigationView navigationView;
    DrawerLayout drawer;
    // tags used to attach the fragments
    private static final String TAG_DASHBOARD = "dashboard";
    private static final String TAG_PROFILE = "profile";
    private static final String TAG_ORDERS = "orders";
    private static final String TAG_VIEW_CART = "view_cart";
    private static final String TAG_WHY_US = "why_us";
    private static final String TAG_RETURN_AND_REFUND_POLICY = "return_and_refund_policy";
    private static final String TAG_TERMS_AND_CONDITIONS = "terms_and_conditions";
    private static final String TAG_WE_VALUE_YOUR_PRIVACY = "we_value_your_privacy";
    private static final String TAG_CONTACT_US = "we_value_your_privacy";

//    public static EditText searchBar;


    public static String CURRENT_TAG = TAG_PROFILE;
    private boolean shouldLoadDashboardFragOnBackPress = true;
    private Handler mHandler;
    private String[] activityTitles=new String[]{"" , "" , "" , ""};
    Activity activity;
    Toolbar toolbar;
//    TextView toolbar_title;
    SearchBox search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        toolbar = (Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


//        toolbar_title=(TextView)findViewById(R.id.toolbar_title);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        mHandler = new Handler();
//        searchBar=(EditText)findViewById(R.id.searchBar);


        navigationView = (NavigationView) findViewById(R.id.nav_view);

        // initializing navigation menu
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_DASHBOARD;
            loadHomeFragment();
        }

        search=(SearchBox)findViewById(R.id.searchbox);
        search.enableVoiceRecognition(this);
        search.setLogoText("Search here");
        search.setLogoTextColor(R.color.gray);
        search.setMenuListener(new SearchBox.MenuListener(){

            @Override
            public void onMenuClick() {
                //Hamburger has been clicked
                drawer.openDrawer(GravityCompat.START);
//                Toast.makeText(MainActivity.this, "Menu click", Toast.LENGTH_LONG).show();
            }

        });

        search.setSearchListener(new SearchBox.SearchListener(){
            LocationFragment nextFrag;
            @Override
            public void onSearchOpened() {
                //Use this to tint the screen
                Log.e("search" ,"onSearchOpened" );
                GlobalClass.selected_option_dashboard="search";

                nextFrag= new LocationFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame, nextFrag,"location_fragment")
                        .addToBackStack(null)
                        .commit();
            }

            @Override
            public void onSearchClosed() {
                //Use this to un-tint the screen
                Log.e("search" ,"onSearchClosed" );
                DashBoardFragment dashBoardFragment= new DashBoardFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame, dashBoardFragment,TAG_DASHBOARD)
                        .addToBackStack(null)
                        .commit();

            }

            @Override
            public void onSearchTermChanged(String s) {
                Log.e("search" ,"onSearchTermChanged "+ s );
            }


            @Override
            public void onSearch(String searchTerm) {
//                Toast.makeText(MainActivity.this, searchTerm +" Searched", Toast.LENGTH_LONG).show();

                nextFrag.search(searchTerm);



            }

            @Override
            public void onResultClick(SearchResult result){
                //React to a result being clicked
            }


            @Override
            public void onSearchCleared() {

            }

        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ( requestCode == SearchBox.VOICE_RECOGNITION_CODE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            search.populateEditText(String.valueOf(matches));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        setToolbarTitle();

        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();
            return;
        }


        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {


                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);

                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();


            }
        };

        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        drawer.closeDrawers();

        invalidateOptionsMenu();
    }
    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                // dashboard
                DashBoardFragment dashBoardFragment=new DashBoardFragment();
                return dashBoardFragment;

            case 2:
                MyOrdersFragment myOrdersFragment=new MyOrdersFragment();
                return myOrdersFragment;




            default:
                return new DashBoardFragment();

        }

    }

    private void setToolbarTitle() {
//        toolbar_title.setText(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_dashboard:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_DASHBOARD;
                        break;

                    case R.id.nav_orders:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_ORDERS;
                        break;



                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }

                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadDashboardFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_DASHBOARD;
                loadHomeFragment();
                return;
            }
        }

        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.activity_main_navigation_drawer_drawer, menu);
        return false;
    }
}
