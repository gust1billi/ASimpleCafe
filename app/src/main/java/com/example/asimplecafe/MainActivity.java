package com.example.asimplecafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asimplecafe.fragment.HomeFragment;
import com.example.asimplecafe.fragment.ReceiptFragment;
import com.example.asimplecafe.fragment.StubFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MAIN ACTIVITY";

    public DrawerLayout drawerLayout;
    public NavigationView navView;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    FrameLayout fragment_frame;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if ( actionBarDrawerToggle.onOptionsItemSelected( item ) ) { return true; }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_home ){
//            setFragmentWeights( 0.615F );
            replaceMainFragment( new HomeFragment( ) );
        } else if (item.getItemId() == R.id.nav_receipt){
            replaceMainFragment( new ReceiptFragment( ) );
        } else if (item.getItemId() == R.id.nav_stub ) {
            replaceMainFragment( new StubFragment( ) );
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityInits(); drawerLayoutInits();

        fragment_frame = findViewById(R.id.main_frame_layout);
    }

//    private void setFragmentWeights( float fragmentWeight ) {
//        fragment_frame.setLayoutParams( Utils.editMainLinearLayoutWeight( fragmentWeight ));
//    }

    private void drawerLayoutInits(){
        drawerLayout = findViewById(R.id.main_drawer_layout);
        navView = findViewById(R.id.main_navbar);
        navView.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.open, R.string.close );
        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        Objects.requireNonNull(
                getSupportActionBar( ) )
                .setDisplayHomeAsUpEnabled(true);

        navView.setNavigationItemSelectedListener(this);
    }

    private void activityInits() {
        Log.e(TAG, "Post");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        replaceMainFragment( new HomeFragment() );
    }

    private void replaceMainFragment ( Fragment fragment ) {
        // Get a reference to the FragmentManager. This whole function can be written in 1 line
        FragmentManager fragmentManager
                = getSupportFragmentManager();

        // Start a new FragmentTransaction
        FragmentTransaction fragmentTransaction
                = fragmentManager.beginTransaction();

        // Replace the current fragment with the new
        // fragment
        fragmentTransaction.replace(R.id.main_frame_layout,
                fragment);

        // Commit the FragmentTransaction
        fragmentTransaction.commit();
    }
}