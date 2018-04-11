package com.example.darko.stravel;


import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import android.view.View;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.ExpandableListView;
import android.view.MotionEvent;

import org.apache.commons.io.IOUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ExpandableListView.OnGroupExpandListener;

public class About extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    Button btnListChild;
    TextView txtListChild;

    private int lastExpandedPosition = -1;
    private ExpandableListView listView;
    private ExpandableListWithButtonAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHash;
    ViewPagerAdapter viewPagerAdapter;

    //adding to todo list - change to scrollable activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        listView = (ExpandableListView)findViewById(R.id.lvExp);
        listView.setAlpha(0.75f);

        initData();

        listAdapter = new ExpandableListWithButtonAdapter(this,listDataHeader,listHash);

        listView.setAdapter(listAdapter);

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);

        viewPagerAdapter = new ViewPagerAdapter(this);

        viewPagerAdapter.drawableImages = new ArrayList<>();

        viewPager.setAdapter(viewPagerAdapter);

        initImages();

        viewPagerAdapter.notifyDataSetChanged();

        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for(int i = 0; i < dotscount; i++){

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });


        viewPager.setOnTouchListener(new View.OnTouchListener() {
            float oldX = 0, newX = 0, sens = 5;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        oldX = event.getX();
                        break;

                    case MotionEvent.ACTION_UP:
                        newX = event.getX();
                        if (Math.abs(oldX - newX) < sens) {
                            openFullscreenImage();
                            return true;
                        }
                        oldX = 0;
                        newX = 0;
                        break;
                }

                return false;
            }
        });

        listView.setOnGroupExpandListener(new OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    listView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 3000, 4000);

    }


    public static Drawable getAssetImage(Context context, String filename) throws IOException {
        AssetManager assets = context.getResources().getAssets();
        InputStream buffer = new BufferedInputStream((assets.open( filename + ".jpg")));
        Bitmap bitmap = BitmapFactory.decodeStream(buffer);
        return new BitmapDrawable(context.getResources(), bitmap);
    }

    public class MyTimerTask extends TimerTask{

        @Override
        public void run() {

            About.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    final int count = viewPager.getAdapter().getCount() - 1;

                    if(viewPager.getCurrentItem() != count)
                    {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    }
                    else
                    {
                        viewPager.setCurrentItem(0);
                    }


                }
            });

        }
    }

    private void initImages()
    {

        try {
            viewPagerAdapter.drawableImages.add(getAssetImage(this,"split3"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            viewPagerAdapter.drawableImages.add(getAssetImage(this,"split2"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            viewPagerAdapter.drawableImages.add(getAssetImage(this,"split1"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            viewPagerAdapter.drawableImages.add(getAssetImage(this,"split4"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            viewPagerAdapter.drawableImages.add(getAssetImage(this,"split7"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            viewPagerAdapter.drawableImages.add(getAssetImage(this,"split8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            viewPagerAdapter.drawableImages.add(getAssetImage(this,"split9"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        String general,history,geography,climate;
        general = history = geography = climate = null;


        try {
            general = IOUtils.toString( (getAssets().open("general.txt")),"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            history = IOUtils.toString( (getAssets().open("history.txt")),"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            geography = IOUtils.toString( (getAssets().open("geography.txt")),"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            climate = IOUtils.toString( (getAssets().open("climate.txt")),"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }


        listDataHeader.add("General");
        listDataHeader.add("History");
        listDataHeader.add("Geography");
        listDataHeader.add("Climate");

        List<String> General = new ArrayList<>();
        General.add(general);

        List<String> History = new ArrayList<>();
        History.add(history);

        List<String> Geography = new ArrayList<>();
        Geography.add(geography);

        List<String> Climate = new ArrayList<>();
        Climate.add(climate);


        listHash.put(listDataHeader.get(0),General);
        listHash.put(listDataHeader.get(1),History);
        listHash.put(listDataHeader.get(2),Geography);
        listHash.put(listDataHeader.get(3),Climate);
    }


    public void openFullscreenImage()
    {
        Intent intent = new Intent(this, FullscreenViewPager.class);
        startActivity(intent);
    }






}
