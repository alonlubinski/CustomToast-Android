package com.alon.testthetoast;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alon.customtoast.CustomToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button main_BTN_positive, main_BTN_negative, main_BTN_like, main_BTN_smile,
                    main_BTN_custom, main_BTN_right, main_BTN_left, main_BTN_both,
                    main_BTN_background, main_BTN_text, main_BTN_icons, main_BTN_all;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findAll();
        setClickListeners();
    }

    // Method that sets click listeners.
    private void setClickListeners() {
        main_BTN_positive.setOnClickListener(this);
        main_BTN_negative.setOnClickListener(this);
        main_BTN_like.setOnClickListener(this);
        main_BTN_smile.setOnClickListener(this);
        main_BTN_custom.setOnClickListener(this);
        main_BTN_right.setOnClickListener(this);
        main_BTN_left.setOnClickListener(this);
        main_BTN_both.setOnClickListener(this);
        main_BTN_background.setOnClickListener(this);
        main_BTN_text.setOnClickListener(this);
        main_BTN_icons.setOnClickListener(this);
        main_BTN_all.setOnClickListener(this);
    }

    // Method that finds all views by id.
    private void findAll() {
        main_BTN_positive = findViewById(R.id.main_BTN_positive);
        main_BTN_negative = findViewById(R.id.main_BTN_negative);
        main_BTN_like = findViewById(R.id.main_BTN_like);
        main_BTN_smile = findViewById(R.id.main_BTN_smile);
        main_BTN_custom = findViewById(R.id.main_BTN_custom);
        main_BTN_right = findViewById(R.id.main_BTN_right);
        main_BTN_left = findViewById(R.id.main_BTN_left);
        main_BTN_both = findViewById(R.id.main_BTN_both);
        main_BTN_background = findViewById(R.id.main_BTN_background);
        main_BTN_text = findViewById(R.id.main_BTN_text);
        main_BTN_icons = findViewById(R.id.main_BTN_icons);
        main_BTN_all = findViewById(R.id.main_BTN_all);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.main_BTN_positive:
                CustomToast.init().positiveToast(this, "This is positive toast!", CustomToast.LENGTH_LONG, CustomToast.LEFT_IMAGE).show();
                break;
            case R.id.main_BTN_negative:
                CustomToast.init().negativeToast(this, "This is negative toast!", CustomToast.LENGTH_LONG, CustomToast.LEFT_IMAGE).show();
                break;
            case R.id.main_BTN_like:
                CustomToast.init().likeToast(this, "This is like toast!", CustomToast.LENGTH_LONG, CustomToast.LEFT_IMAGE).show();
                break;
            case R.id.main_BTN_smile:
                CustomToast.init().smileToast(this, "This is smile toast!", CustomToast.LENGTH_LONG, CustomToast.LEFT_IMAGE).show();
                break;
            case R.id.main_BTN_custom:
                CustomToast
                        .init()
                        .setContext(this)
                        .setMessage("This is custom toast")
                        .setBackgroundColor(Color.CYAN)
                        .setCornerRadius(30)
                        .setTextColor(Color.RED)
                        .setTextSize(17)
                        .buildToast()
                        .show();
                break;
            case R.id.main_BTN_right:
                CustomToast
                        .init()
                        .setContext(this)
                        .setMessage("Custom toast with right icon")
                        .setBackgroundColor(Color.BLUE)
                        .setTextColor(Color.WHITE)
                        .setRightIcon(getResources().getDrawable(R.drawable.ic_android))
                        .buildToast()
                        .show();
                break;
            case R.id.main_BTN_left:
                CustomToast
                        .init()
                        .setContext(this)
                        .setMessage("Custom toast with left icon")
                        .setBackgroundColor(Color.BLUE)
                        .setTextColor(Color.WHITE)
                        .setLeftIcon(getResources().getDrawable(R.drawable.ic_android))
                        .buildToast()
                        .show();
                break;
            case R.id.main_BTN_both:
                CustomToast
                        .init()
                        .setContext(this)
                        .setMessage("Custom toast with right and left icons")
                        .setBackgroundColor(Color.BLUE)
                        .setTextColor(Color.WHITE)
                        .setRightIcon(getResources().getDrawable(R.drawable.ic_android))
                        .setLeftIcon(getResources().getDrawable(R.drawable.ic_android))
                        .buildToast()
                        .show();
                break;
            case R.id.main_BTN_background:
                CustomToast
                        .init()
                        .setContext(this)
                        .setMessage("Custom blinking toast")
                        .setBackgroundColor(Color.BLUE)
                        .setTextColor(Color.WHITE)
                        .setRightIcon(getResources().getDrawable(R.drawable.ic_android))
                        .setLeftIcon(getResources().getDrawable(R.drawable.ic_android))
                        .setBackgroundBlink(Color.CYAN, 300)
                        .buildToast()
                        .show();
                break;
            case R.id.main_BTN_text:
                CustomToast
                        .init()
                        .setContext(this)
                        .setMessage("Custom blinking toast")
                        .setBackgroundColor(Color.BLUE)
                        .setTextColor(Color.WHITE)
                        .setRightIcon(getResources().getDrawable(R.drawable.ic_android))
                        .setLeftIcon(getResources().getDrawable(R.drawable.ic_android))
                        .setTextBlink(Color.BLACK, 100)
                        .buildToast()
                        .show();
                break;
            case R.id.main_BTN_icons:
                CustomToast
                        .init()
                        .setContext(this)
                        .setMessage("Custom blinking toast")
                        .setBackgroundColor(Color.BLUE)
                        .setTextColor(Color.WHITE)
                        .setRightIcon(getResources().getDrawable(R.drawable.ic_android))
                        .setLeftIcon(getResources().getDrawable(R.drawable.ic_android))
                        .setIconBlink(Color.WHITE, Color.BLACK, 200)
                        .buildToast()
                        .show();
                break;
            case R.id.main_BTN_all:
                CustomToast
                        .init()
                        .setContext(this)
                        .setMessage("Custom blinking toast")
                        .setBackgroundColor(Color.BLUE)
                        .setTextColor(Color.WHITE)
                        .setRightIcon(getResources().getDrawable(R.drawable.ic_android))
                        .setLeftIcon(getResources().getDrawable(R.drawable.ic_android))
                        .setBackgroundBlink(Color.CYAN, 300)
                        .setTextBlink(Color.RED, 200)
                        .setIconBlink(Color.WHITE, Color.BLACK, 200)
                        .buildToast()
                        .show();
                break;
        }
    }
}