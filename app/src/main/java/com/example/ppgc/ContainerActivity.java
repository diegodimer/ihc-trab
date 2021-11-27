package com.example.ppgc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.core.view.GravityCompat;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.httpRequests.HttpRequests;
import com.google.android.material.navigation.NavigationView;


public class ContainerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;
    private String userName;
    private String userId;
    private String userAdvisor;
    private String userMail;
    private String userPhone;

    private HttpRequests httpRequester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        Bundle bundle = getIntent().getExtras();
        this.userName = bundle.getString("name");
        this.userAdvisor = bundle.getString("advisor");
        this.userMail = bundle.getString("email");
        this.userPhone = bundle.getString("phone");
        this.userId = bundle.getString("userId");

        //Add navigation drawer to layout
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle
                (
                        this,
                        drawerLayout,
                        R.string.drawer_open,
                        R.string.drawer_close
                )
        {
        };
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //disable title and enable button on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            ProfileFragment profileFragment = new ProfileFragment();
            profileFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    profileFragment).commit();
            navigationView.setCheckedItem(R.id.profile);
        }
    }


    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item){
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Bundle bundle = new Bundle();
        bundle.putString("userId", this.userId);
        bundle.putString("name", this.userName);
        bundle.putString("advisor", this.userAdvisor);
        bundle.putString("phone", this.userPhone);
        bundle.putString("email", this.userMail);
        bundle.putSerializable("httpRequester", this.httpRequester);
        switch (item.getItemId()) {
            case R.id.profile:
                ProfileFragment profileFragment = new ProfileFragment();
                profileFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        profileFragment).commit();
                break;
            case R.id.mark_presence:
                CapturaFragment capturaFragment = new CapturaFragment();
                capturaFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        capturaFragment).commit();
                break;
            case R.id.presences:
                CalendarFragment calendarFragment = new CalendarFragment();
                calendarFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        calendarFragment).commit();
                break;
            case R.id.logout:
                this.finishAffinity();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


}
