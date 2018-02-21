package com.example.darko.stravel;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.graphics.drawable.Drawable;
import android.content.res.AssetManager;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by Sanket on 27-Feb-17.
 * Edited by Darko on 12-Jan-18
 */

public class ViewPagerAdapter extends PagerAdapter {

    public ImageView imageView;
    public View view;
    private Context context;
    private LayoutInflater layoutInflater;
    private Integer [] images =
            {
                    R.drawable.split1,
                    R.drawable.split2,
                    R.drawable.split3,
                    R.drawable.split4,
                    R.drawable.split7,
                    R.drawable.split8,
                    R.drawable.split9
            };

    public ArrayList<Drawable> drawableImages;

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return drawableImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = layoutInflater.inflate(R.layout.custom_layout,null);

        imageView = (ImageView) view.findViewById(R.id.imageView);

        //imageView.setImageResource(images[position]);

        //drawableImages = new ArrayList<>();


        imageView.setImageDrawable(drawableImages.get(position));

        //imageView.setScaleType(ImageView.ScaleType.FIT_XY);


        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }

    public static Drawable getAssetImage(Context context, String filename) throws IOException {
        AssetManager assets = context.getResources().getAssets();
        InputStream buffer = new BufferedInputStream((assets.open("drawable/" + filename + ".png")));
        Bitmap bitmap = BitmapFactory.decodeStream(buffer);
        return new BitmapDrawable(context.getResources(), bitmap);
    }


}
