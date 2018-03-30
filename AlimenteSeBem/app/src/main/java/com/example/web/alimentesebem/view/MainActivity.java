package com.example.web.alimentesebem.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.web.alimentesebem.R;
import com.example.web.alimentesebem.model.TagForumBean;
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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

    public void disconnectFromFacebook() {

        if (AccessToken.getCurrentAccessToken() == null) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            finish();
            startActivity(intent);
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                .Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {

                LoginManager.getInstance().logOut();

            }
        }).executeAsync();
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
             /*       Bundle extra = new Bundle();

                    extra.putParcelable("botao",); // TODO: passar a referência do FloatActionButton
                    tabNoticia.setArguments(extra);*/
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
}
