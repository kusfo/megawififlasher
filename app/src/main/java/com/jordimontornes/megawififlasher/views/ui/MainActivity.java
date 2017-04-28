package com.jordimontornes.megawififlasher.views.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.jordimontornes.megawififlasher.R;

public class MainActivity extends AppCompatActivity implements FileManagerFragment.OnFileManagerFragmentInteractionListener, SettingsFragment.OnSettingsFragmentInteractionListener {

    private BottomNavigationView navigationBottomBar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            selectFragment(item);
            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationBottomBar = (BottomNavigationView) findViewById(R.id.navigation);
        navigationBottomBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(FileManagerFragment.newInstance());
    }

    private void selectFragment(MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_file_manager:
                fragment = FileManagerFragment.newInstance();
                break;
            case R.id.navigation_settings:
                fragment = SettingsFragment.newInstance();
                break;
        }


        // uncheck the other items.
        for (int i = 0; i < navigationBottomBar.getMenu().size(); i++) {
            MenuItem menuItem = navigationBottomBar.getMenu().getItem(i);
            menuItem.setChecked(menuItem.getItemId() == item.getItemId());
        }

        updateToolbarText(item.getTitle());

        loadFragment(fragment);

    }

    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content, fragment, fragment.getTag());
            ft.commit();
        }
    }

    private void updateToolbarText(CharSequence text) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(text);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //NOP
    }

    @Override
    public void onFragmentInteraction2(Uri uri) {
        //NOP
    }

}

