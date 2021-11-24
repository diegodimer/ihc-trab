package com.example.ppgc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class TelaDeCapturaActivity extends AppCompatActivity {

    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teladecaptura);

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

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                switch (id) {
                    case R.id.btnScan:
                        Toast.makeText(TelaDeCapturaActivity.this, "Clicou em scam bb", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.toolbar4:
                        Toast.makeText(TelaDeCapturaActivity.this, "Clicou em toolbar4 bb", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.textView2:
                        Toast.makeText(TelaDeCapturaActivity.this, "Clicou em textview2 bb", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return toggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}