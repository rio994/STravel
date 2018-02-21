package com.example.darko.stravel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.View;
import android.widget.LinearLayout;
import android.os.Build;
import android.util.Log;
import android.nfc.Tag;
import android.text.Html;

public class Place extends AppCompatActivity {

    private static final String TAG = "Place";


    TextView placeName,placeAddress,placeReview,placeWorktime,placeTelNumber,placeEmail,placeWebsite,placeDescription;
    ImageView placeImg;
    boolean isImageFitToScreen;
    TablePlaces place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("id");

        //if(bundle.getString("id")!= null)
        {
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
            databaseAccess.open();
            place = databaseAccess.getPlace(id);
            databaseAccess.close();

            placeName = findViewById(R.id.place_name);
            placeAddress = findViewById(R.id.place_address);
            placeReview = findViewById(R.id.place_review);
            placeWorktime = findViewById(R.id.place_worktime);
            placeTelNumber = findViewById(R.id.place_telnumber);
            placeEmail = findViewById(R.id.place_email);
            placeWebsite = findViewById(R.id.place_website);
            placeDescription = findViewById(R.id.place_description);

            placeAddress.setPadding(Math.round((int)(1.5 * placeAddress.getPaddingLeft())),placeAddress.getPaddingTop(),placeAddress.getPaddingRight(),placeAddress.getPaddingBottom());
            placeWorktime.setPadding(Math.round((int)(0.5 * placeWorktime.getPaddingLeft())),placeWorktime.getPaddingTop(),Math.round((int)(0.5 * placeWorktime.getPaddingLeft())),placeWorktime.getPaddingBottom());
            placeTelNumber.setPadding(Math.round((int)(0.5 * placeTelNumber.getPaddingLeft())),placeTelNumber.getPaddingTop(),Math.round((int)(0.5 * placeTelNumber.getPaddingLeft())),placeTelNumber.getPaddingBottom());
            placeEmail.setPadding(Math.round((int)(0.5 * placeEmail.getPaddingLeft())),placeEmail.getPaddingTop(),Math.round((int)(0.5 * placeEmail.getPaddingLeft())),placeEmail.getPaddingBottom());
            placeWebsite.setPadding(Math.round((int)(0.5 * placeWebsite.getPaddingLeft())),placeWebsite.getPaddingTop(),Math.round((int)(0.5 * placeWebsite.getPaddingLeft())),placeWebsite.getPaddingBottom());
            placeDescription.setPadding(Math.round((int)(0.5 * placeDescription.getPaddingLeft())),placeDescription.getPaddingTop(),Math.round((int)(0.5 * placeDescription.getPaddingLeft())),placeDescription.getPaddingBottom());

            placeName.setText(place.getName());
            placeAddress.setText(place.getAddress());
            placeReview.setText(place.getReview()+"");
            placeWorktime.setText("work time: " + place.getWork_time_begin() + " - " + place.getWork_time_end());
            placeTelNumber.setText("telephone number: " + place.getTelephone_number());
            placeEmail.setText("email: " + place.getEmail());
            /*placeEmail.setText(Html.fromHtml("<a href=\"mailto:" + place.getEmail()\">Send Feedback</a>"));
            placeEmail.setMovementMethod(LinkMovementMethod.getInstance());
            feedback.setText(Html.fromHtml("<a href=\"mailto:ask@me.it\">Send Feedback</a>"));
            feedback.setMovementMethod(LinkMovementMethod.getInstance());
            */
            placeWebsite.setText("website: " + place.getWebsite());
            placeDescription.setText("description: " + place.getDescription());

            /*
            placeImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //adding to todo list
                }
            });

            placeImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isImageFitToScreen) {
                        isImageFitToScreen=false;
                        placeImg.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        placeImg.setAdjustViewBounds(true);
                    }else{
                        isImageFitToScreen=true;
                        placeImg.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                        placeImg.setScaleType(ImageView.ScaleType.FIT_XY);
                    }
                }
            });
            */

        }



    }


    public void fullScreen() {

        // BEGIN_INCLUDE (get_current_ui_flags)
        // The UI options currently enabled are represented by a bitfield.
        // getSystemUiVisibility() gives us that bitfield.
        int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
        int newUiOptions = uiOptions;
        // END_INCLUDE (get_current_ui_flags)
        // BEGIN_INCLUDE (toggle_ui_flags)
        boolean isImmersiveModeEnabled =
                ((uiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) == uiOptions);
        if (isImmersiveModeEnabled) {
            Log.i(TAG, "Turning immersive mode mode off. ");
        } else {
            Log.i(TAG, "Turning immersive mode mode on.");
        }

        // Navigation bar hiding:  Backwards compatible to ICS.
        if (Build.VERSION.SDK_INT >= 14) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }

        // Status bar hiding: Backwards compatible to Jellybean
        if (Build.VERSION.SDK_INT >= 16) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        }

        // Immersive mode: Backward compatible to KitKat.
        // Note that this flag doesn't do anything by itself, it only augments the behavior
        // of HIDE_NAVIGATION and FLAG_FULLSCREEN.  For the purposes of this sample
        // all three flags are being toggled together.
        // Note that there are two immersive mode UI flags, one of which is referred to as "sticky".
        // Sticky immersive mode differs in that it makes the navigation and status bars
        // semi-transparent, and the UI flag does not get cleared when the user interacts with
        // the screen.
        if (Build.VERSION.SDK_INT >= 18) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        }

        getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
        //END_INCLUDE (set_ui_flags)
    }
}
