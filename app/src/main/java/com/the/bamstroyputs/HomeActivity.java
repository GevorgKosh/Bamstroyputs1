package com.the.bamstroyputs;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.the.bamstroyputs.controller.DataController;
import com.the.bamstroyputs.databinding.ActivityHomeBinding;
import com.the.bamstroyputs.profile.ProfileFragment;
import com.the.bamstroyputs.project.ProjectFragment;
import com.the.bamstroyputs.util.ActivityUtil;
import com.the.bamstroyputs.vendor.VendorFragment;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        ActivityUtil.pushFragment(ProjectFragment.newInstance(), getSupportFragmentManager(), R.id.container, false);


        Log.d("TAKEIT", DataController.getInstance().getUser().toString());
        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.general:
                        ActivityUtil.pushFragment(ProjectFragment.newInstance(), getSupportFragmentManager(), R.id.container, true);
                        closeMenu();
                        break;
                    case R.id.vendors:
                        ActivityUtil.pushFragment(VendorFragment.newInstance(), getSupportFragmentManager(), R.id.container, true);
                        closeMenu();
                        break;
                    case R.id.profile:
                        ActivityUtil.pushFragment(ProfileFragment.newInstance(), getSupportFragmentManager(), R.id.container, true);
                        closeMenu();
                        break;
                }
                return false;
            }
        });

        binding.toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void closeMenu() {
        binding.drawer.closeDrawer(GravityCompat.START);
    }

    public void openMenu() {
        binding.drawer.openDrawer(GravityCompat.START);
    }
}
