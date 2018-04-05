package com.example.web.alimentesebem.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;


import com.example.web.alimentesebem.R;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private TextView toolbarTittle;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbarTittle = findViewById(R.id.toolbar_title);
        Typeface typeFont = Typeface.createFromAsset(getAssets(),"fonts/tahu.ttf");
        toolbarTittle.setTypeface(typeFont);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        //Ações ao mudar de pagina na TabView
        mViewPager.addOnPageChangeListener(myOnPageChangeListener);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int idView = item.getItemId();

        if (idView == R.id.ic_logoff){

             //Cria o Builder do dialogo de exclusão
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Deseja mesmo Sair ?")
                    .setTitle("Logoff");

            // Cria botões ok e cancelar
            builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                   // disconnectFromFacebook();
                    LoginManager.getInstance().logOut();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    finish();
                    startActivity(intent);
                }
            });
            builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // se cancelar volta pra tela de exclusão
                }
            });
            //Cria o  dialogo de exclusão
            AlertDialog dialog = builder.create();
            dialog.show();

         }

        return true;
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
           // return PlaceholderFragment.newInstance(position + 1);

            switch (position){
                case 0:
                    TabNoticia tabNoticia = new TabNoticia();
                    return tabNoticia;
                case 1:
                    TabAgenda tabAgenda = new TabAgenda();
                    return tabAgenda;
                case 2:
                    TabForum tabForum = new TabForum();
                    return tabForum;

                case 3:
                    TabVideos tabVideos = new TabVideos();
                    return tabVideos;

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }



        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "Noticias";
                case 1:
                    return "Calendario";
                case 2:
                    return "Forúm";
                case 3:
                    return "Vídeos";
            }

            return null;
        }
    }

    ViewPager.OnPageChangeListener myOnPageChangeListener =
            new ViewPager.OnPageChangeListener() {

                @Override
                public void onPageScrollStateChanged(int state) {
                    //Called when the scroll state changes.

                }

                @Override
                public void onPageScrolled(int position,
                                           float positionOffset, int positionOffsetPixels) {
                    //This method will be invoked when the current page is scrolled,
                    //either as part of a programmatically initiated smooth scroll
                    //or a user initiated touch scroll.
                    //esconde o teclado
                    hideKeyboard();
                }

                @Override
                public void onPageSelected(int position) {
                    //This method will be invoked when a new page becomes selected.

                    //esconde o teclado
                    hideKeyboard();
                }
            };

    public  void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager)getApplicationContext().getSystemService(this.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View v = getCurrentFocus();
        if (v == null)
            return;

        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}
