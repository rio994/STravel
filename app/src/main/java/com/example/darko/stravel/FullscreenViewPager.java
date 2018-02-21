package com.example.darko.stravel;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.app.Activity;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class FullscreenViewPager extends Activity {

    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_view_pager);

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);

        viewPagerAdapter = new ViewPagerAdapter(this);

        viewPagerAdapter.drawableImages = new ArrayList<>();

        viewPager.setAdapter(viewPagerAdapter);

        initImages();

        viewPagerAdapter.notifyDataSetChanged();

        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {

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

                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public static Drawable getAssetImage(Context context, String filename) throws IOException {
        AssetManager assets = context.getResources().getAssets();
        InputStream buffer = new BufferedInputStream((assets.open( filename + ".jpg")));
        Bitmap bitmap = BitmapFactory.decodeStream(buffer);
        return new BitmapDrawable(context.getResources(), bitmap);
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

}

