package com.example.lenovo.NanBada.Nanbada;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.NanBada.R;
import com.example.lenovo.NanBada.post.LocateMe;
import com.example.lenovo.NanBada.post.PostAsyncTask;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Socket socket;
    BufferedReader in;
    static public PrintWriter out;
    String data;
    //socketThread socketthread;
    private ImageView shipImage;
    private TextView locationTextView;

    private Context mContext;
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContext = getApplicationContext();
        shipImage = findViewById(R.id.shipImageView);
        locationTextView= findViewById(R.id.location_textView);
        final MainActivity mainActivity =this;

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
            TimerTask tt = new TimerTask() {
                @Override
                public void run() {
                    new PostAsyncTask(mainActivity).execute();

                }
            };
            Timer timer = new Timer();
            timer.schedule(tt, 3000, 3000);
        }
        /*
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        socketthread = new socketThread();
        socketthread.setDaemon(true);
        socketthread.start();
        */

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "글을 씁니다.", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, Tab_Write_Form.class);
                startActivity(intent);
            }
        });
        */
        //상단 액션 바
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //네비게이션뷰
        //왼쪽 상단 메뉴탭 클릭 시 네비게이션 바 생성
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //메인메뉴 내 탭
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Info"));
        tabLayout.addTab(tabLayout.newTab().setText("Mail"));
        tabLayout.addTab(tabLayout.newTab().setText("Q&A"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //탭 클릭 시
                viewPager.setCurrentItem(tab.getPosition());
                TabFragment1 fragment1 = new TabFragment1();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    //뒤로가기
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    //네비게이션 클릭 이벤트
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // 각종 이벤트 액션
        if (id == R.id.nav_home) {
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
            final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
            final PagerAdapter adapter = new PagerAdapter
                    (getSupportFragmentManager(), tabLayout.getTabCount());
            viewPager.setAdapter(adapter);
        } else if (id == R.id.nav_notice) {
            //    Intent view_notice = new Intent(MainActivity.this, notice_view.class);
            //       startActivityForResult(view_notice,request_code);
        } else if (id == R.id.nav_help) {
            Intent email = new Intent(Intent.ACTION_SEND);
            email.setType("plain/text");
            // email setting 배열로 해놔서 복수 발송 가능
            String[] address = {"email@address.com"};
            email.putExtra(Intent.EXTRA_EMAIL, address);
            email.putExtra(Intent.EXTRA_SUBJECT, "보내질 email 제목");
            email.putExtra(Intent.EXTRA_TEXT, "보낼 email 내용을 미리 적어 놓을 수 있습니다.\n");
            startActivity(email);

        } else if (id == R.id.nav_share) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "공유 제목");
            intent.putExtra(Intent.EXTRA_TEXT, "공유 내용");
            Intent chooser = Intent.createChooser(intent, "공유");
            startActivity(chooser);
        } else if (id == R.id.nav_declare) {
            //설정된 전화번호를 다이얼 ( dial --> call 변경시 즉시전화)
            startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:010-0000-0000")));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true; //우측 상단 메뉴
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {

            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public ImageView getShipImage() {
        return shipImage;
    }

    public TextView getLocationTextView() {
        return locationTextView;
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
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
                return PlaceholderFragment.newInstance(position + 1);
            }

            //탭 개수
            @Override
            public int getCount() {
                // Show 3 total pages.
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        TabFragment1 tab1 = new TabFragment1();
                    case 1:
                        TabFragment2 tab2 = new TabFragment2();
                    case 2:
                        TabFragment3 tab3 = new TabFragment3();
                }
                return null;
            }
        }
    }

    /*
    class socketThread extends Thread {
        public void run() {
            try {
                socket = new Socket("172.30.1.50", 7777);
                //172.16.91.32
                //192.168.43.84
                //socket = new Socket("172.30.1.7", 7777);
                out = new PrintWriter(socket.getOutputStream(), true);                                                                                                                      //????.
                in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "EUC-KR"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                while (true) {
                    data = in.readLine();
                    System.out.println(data);
                    if(data.startsWith("[CHAT]")){
                        TabFragment_2.setMessage(data);
                    }
                }
            } catch (Exception e) {
            }
        }
    }
    */
    public static void send(String option, String data) {
        out.println(option + "#" + data);
        Log.v("option: ", option);
        Log.v("data: ", data);
    }

}