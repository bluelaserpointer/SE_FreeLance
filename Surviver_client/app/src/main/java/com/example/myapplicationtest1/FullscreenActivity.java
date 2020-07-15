package com.example.myapplicationtest1;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplicationtest1.page.HomePage;
import com.example.myapplicationtest1.page.LoginInputPage;
import com.example.myapplicationtest1.page.Page;
import com.example.myapplicationtest1.utils.Utils;


import org.json.JSONException;

import java.util.ArrayList;
import java.util.Map;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {
    private static MotionEvent motionEventTemp;


    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     *
     * 该位置就是写跳转逻辑的部分。
     * 当完成一个按钮的制作时，设置适当的ID名，这样即可在findViewById(R.id.XXX)里找到该按钮控件。
     * 之后按照已写好的代码指定跳转目标(layout)即可。
     */
    private final View.OnTouchListener loginHideTouchListener = (view, motionEvent) -> {
        view.performClick();
        if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
            if (AUTO_HIDE) {
                hide();
            }
            try {
                if(Utils.identifyUser(this))
                {
                    System.out.println("Identification succeeded!!!!!!!!!!!!!!!!");
                    Page.jump(this, HomePage.class);
                }else{
                    System.out.println("Identification failed!!!!!!!!!!!!!!!");
                    Page.jump(this, LoginInputPage.class);
                    System.out.println("Identification done!!!!!!!!!!!!!!!");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    };

    public void onLoginInputClick(View view) throws JSONException {
        EditText userNameText = findViewById(R.id.usernameText);
        String userName = userNameText.getText().toString();
        EditText passwordText = findViewById(R.id.passwordText);
        String password = passwordText.getText().toString();
        System.out.println(userName);
        System.out.println(password);
        if(Utils.identifyUserInput(userName, password))
        {
            System.out.println("InputIdentification succeeded!!!!!!!!!!!!!!!!");
            Utils.saveUserInfo(this, userName, password);
            Map<String,String> userInfo= Utils.getUserInfo(this);
            String getUserName = userInfo.get("userName");
            String getPassword = userInfo.get("password");
            System.out.println(getUserName);
            System.out.println(getPassword);
            Page.jump(this, HomePage.class);
        }else{
            System.out.println("InputIdentification failed!!!!!!!!!!!!!!!!");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);

        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        findViewById(R.id.login_button).setOnTouchListener(loginHideTouchListener);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }



    /**
     * These are for further implementations of methods for calculating the data
     * and initializing specific listViewers...for horizontal scrolling
     * Usage:
     * Call calculate......() in onTouch functions
     */

    private ArrayList<String> mTexts = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();

    private void calculateBasicExp(){
        /**
         * Calculations here
         * Usage:
         * for(...)
         *  {
         *      mImages.add("http://apple.jpg");
         *      mTexts.add("+1exp");
         *  }
         */

        initRecyclerView(mTexts, mImages, R.id.cardExpList);
    }

    private void calculateBasicItem(){
        /**
         * Calculations here
         * Usage:
         * for(...)
         *  {
         *      mImages.add("http://apple.jpg");
         *      mTexts.add("+1exp");
         *  }
         */

        initRecyclerView(mTexts, mImages, R.id.basicItemList);
    }

    private void calculateFallenItem(){
        /**
         * Calculations here
         * Usage:
         * for(...)
         *  {
         *      mImages.add("http://apple.jpg");
         *      mTexts.add("+1exp");
         *  }
         */

        initRecyclerView(mTexts, mImages, R.id.fallenItemList);
    }

    private void initRecyclerView(ArrayList<String> mTexts, ArrayList<String> mImages, int id){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(id);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter;
        switch(id)
        {
            case R.id.cardExpList:
                adapter = new ExpListViewAdapter(this, mTexts, mImages);
                break;
            case R.id.basicItemList:
                adapter = new BasicItemListViewAdapter(this, mTexts, mImages);
                break;
            case R.id.fallenItemList:
                adapter = new FallenItemListViewAdapter(this, mTexts, mImages);
                break;
            default:
                adapter = null;
        }
        recyclerView.setAdapter(adapter);
    }

}