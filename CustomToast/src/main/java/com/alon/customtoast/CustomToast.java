package com.alon.customtoast;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;

public class CustomToast {

    // Durations
    public static final int LENGTH_SHORT = Toast.LENGTH_SHORT;
    public static final int LENGTH_LONG = Toast.LENGTH_LONG;

    // Side attributes
    public static final int RIGHT_IMAGE = 0;
    public static final int LEFT_IMAGE = 1;

    private Context context;
    private String message = "";
    private int duration = LENGTH_LONG;
    private int cornerRadius = 80;
    private int backgroundColor = Color.LTGRAY;
    private float textSize = 14;
    private int textColor = Color.BLACK;
    private boolean rightIcon = false;
    private Drawable rightIconDrawable;
    private boolean leftIcon = false;
    private Drawable leftIconDrawable;
    private boolean backgroundBlink = false;
    private int backgroundBlinkColor;
    private int backgroundBlinkInterval;
    private boolean textBlink = false;
    private int textBlinkColor;
    private int textBlinkInterval;
    private boolean iconBlink = false;
    private int iconBlinkColor;
    private int iconBlinkColor2;
    private int iconBlinkInterval;

    /**
     *
     */
    public CustomToast() {
    }

    /**
     * @return CustomToast
     */
    public static CustomToast init() {
        return new CustomToast();
    }

    /**
     * @param context The context to use.  Usually your {@link android.app.Application}
     *                or {@link android.app.Activity} object.
     * @return CustomToast
     */
    public CustomToast setContext(Context context) {
        this.context = context;
        return this;
    }

    /**
     * @param message The message to show in your custom toast.
     * @return CustomToast
     */
    public CustomToast setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * @param duration How long to display the message.  Either {@link #LENGTH_SHORT} or
     *                 {@link #LENGTH_LONG}
     * @return CustomToast
     */
    public CustomToast setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    /**
     * @param cornerRadius The corner radius to use.
     * @return CustomToast
     */
    public CustomToast setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        return this;
    }

    /**
     * @param backgroundColor The background color to use.
     * @return CustomToast
     */
    public CustomToast setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    /**
     * @param textSize The text size to use.
     * @return CustomToast
     */
    public CustomToast setTextSize(float textSize) {
        this.textSize = textSize;
        return this;
    }

    /**
     * @param textColor The text color to use.
     * @return CustomToast
     */
    public CustomToast setTextColor(int textColor) {
        this.textColor = textColor;
        return this;
    }

    /**
     * @param icon The icon resource file.
     * @return CustomToast
     */
    public CustomToast setRightIcon(Drawable icon) {
        this.rightIconDrawable = icon;
        this.rightIcon = true;
        return this;
    }

    /**
     * @param icon The icon resource file.
     * @return CustomToast
     */
    public CustomToast setLeftIcon(Drawable icon) {
        this.leftIconDrawable = icon;
        this.leftIcon = true;
        return this;
    }

    /**
     * @param color    The blinking color to use.
     * @param interval The interval time of blinking.
     * @return CustomToast
     */
    public CustomToast setBackgroundBlink(int color, int interval) {
        this.backgroundBlinkColor = color;
        this.backgroundBlinkInterval = interval;
        this.backgroundBlink = true;
        return this;
    }

    /**
     * @param color    The blinking color to use.
     * @param interval The interval time of blinking.
     * @return CustomToast
     */
    public CustomToast setTextBlink(int color, int interval) {
        this.textBlinkColor = color;
        this.textBlinkInterval = interval;
        this.textBlink = true;
        return this;
    }

    /**
     * @param color    The blinking color to use.
     * @param color2   The second blinking color to use.
     * @param interval The interval time of blinking.
     * @return CustomToast
     */
    public CustomToast setIconBlink(int color, int color2, int interval) {
        this.iconBlinkColor = color;
        this.iconBlinkColor2 = color2;
        this.iconBlinkInterval = interval;
        this.iconBlink = true;
        return this;
    }


    /**
     * @return Fully custom toast.
     */
    public Toast buildToast() {
        Toast toast = Toast.makeText(context, message, duration);
        View toastLayout = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.toast_layout, null);
        TextView toastText = toastLayout.findViewById(R.id.toast_LBL_message);
        toastText.setText(message);
        toastText.setTextSize(textSize);
        toastText.setTextColor(textColor);
        ImageView rightImage = toastLayout.findViewById(R.id.toast_IMG_right);
        ImageView leftImage = toastLayout.findViewById(R.id.toast_IMG_left);
        if (rightIcon) {
            rightImage.setImageDrawable(rightIconDrawable);
        } else {
            rightImage.setVisibility(View.GONE);
        }
        if (leftIcon) {
            leftImage.setImageDrawable(leftIconDrawable);
        } else {
            leftImage.setVisibility(View.GONE);
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setTint(backgroundColor);
        gradientDrawable.setCornerRadius(cornerRadius);
        if (backgroundBlink) {
            startBlink(gradientDrawable, "tint", backgroundBlinkInterval, backgroundBlinkColor, backgroundColor).start();
        }
        if (textBlink) {
            startBlink(toastText, "textColor", textBlinkInterval, textBlinkColor, textColor).start();
        }
        if (iconBlink) {
            if (rightIcon) {
                startBlink(rightImage, "colorFilter", iconBlinkInterval, iconBlinkColor, iconBlinkColor2).start();
            }
            if (leftIcon) {
                startBlink(leftImage, "colorFilter", iconBlinkInterval, iconBlinkColor, iconBlinkColor2).start();
            }
        }
        toastLayout.setBackground(gradientDrawable);
        toast.setView(toastLayout);
        return toast;
    }

    /**
     * @param object   The blinking object.
     * @param prop     The blinking object's prop.
     * @param interval The blinking interval.
     * @param color    The first blinking color.
     * @param color2   The second blinking color.
     * @return ObjectAnimator
     */
    private ObjectAnimator startBlink(Object object, String prop, int interval, int color, int color2) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(object, prop, color, color2);
        objectAnimator.setDuration(interval);
        objectAnimator.setEvaluator(new ArgbEvaluator());
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        return objectAnimator;
    }

    /**
     * @param context  The context to use.  Usually your {@link android.app.Application}
     *                 or {@link android.app.Activity} object.
     * @param message  The message to show in your positive toast.
     * @param duration How long to display the message.  Either {@link #LENGTH_SHORT} or
     *                 {@link #LENGTH_LONG}
     * @param side     The side to show the icon.  Either {@link #RIGHT_IMAGE} or
     *                 {@link #LEFT_IMAGE}
     * @return Positive toast with custom message.
     */
    public Toast positiveToast(Context context, String message, int duration, int side) {
        setContext(context);
        setMessage(message);
        setDuration(duration);
        setBackgroundColor(ContextCompat.getColor(context, R.color.light_green));
        setTextColor(Color.WHITE);
        if (side == RIGHT_IMAGE)
            setRightIcon(AppCompatResources.getDrawable(context, R.drawable.ic_success));
        else if (side == LEFT_IMAGE)
            setLeftIcon(AppCompatResources.getDrawable(context, R.drawable.ic_success));
        return buildToast();
    }

    /**
     * @param context  The context to use.  Usually your {@link android.app.Application}
     *                 or {@link android.app.Activity} object.
     * @param message  The message to show in your negative toast.
     * @param duration How long to display the message.  Either {@link #LENGTH_SHORT} or
     *                 {@link #LENGTH_LONG}
     * @param side     The side to show the icon.  Either {@link #RIGHT_IMAGE} or
     *                 {@link #LEFT_IMAGE}
     * @return Negative toast with custom message.
     */
    public Toast negativeToast(Context context, String message, int duration, int side) {
        setContext(context);
        setMessage(message);
        setDuration(duration);
        setBackgroundColor(ContextCompat.getColor(context, R.color.medium_red));
        setTextColor(Color.WHITE);
        if (side == RIGHT_IMAGE)
            setRightIcon(AppCompatResources.getDrawable(context, R.drawable.ic_fail));
        else if (side == LEFT_IMAGE)
            setLeftIcon(AppCompatResources.getDrawable(context, R.drawable.ic_fail));
        return buildToast();
    }

    /**
     * @param context  The context to use.  Usually your {@link android.app.Application}
     *                 or {@link android.app.Activity} object.
     * @param message  The message to show in your negative toast.
     * @param duration How long to display the message.  Either {@link #LENGTH_SHORT} or
     *                 {@link #LENGTH_LONG}
     * @param side     The side to show the icon.  Either {@link #RIGHT_IMAGE} or
     *                 {@link #LEFT_IMAGE}
     * @return Like toast with custom message.
     */
    public Toast likeToast(Context context, String message, int duration, int side) {
        setContext(context);
        setMessage(message);
        setDuration(duration);
        setBackgroundColor(ContextCompat.getColor(context, R.color.light_blue));
        setTextColor(Color.WHITE);
        if (side == RIGHT_IMAGE)
            setRightIcon(AppCompatResources.getDrawable(context, R.drawable.ic_like));
        else if (side == LEFT_IMAGE)
            setLeftIcon(AppCompatResources.getDrawable(context, R.drawable.ic_like));
        return buildToast();
    }

    /**
     * @param context  The context to use.  Usually your {@link android.app.Application}
     *                 or {@link android.app.Activity} object.
     * @param message  The message to show in your negative toast.
     * @param duration How long to display the message.  Either {@link #LENGTH_SHORT} or
     *                 {@link #LENGTH_LONG}
     * @param side     The side to show the icon.  Either {@link #RIGHT_IMAGE} or
     *                 {@link #LEFT_IMAGE}
     * @return Smile toast with custom message.
     */
    public Toast smileToast(Context context, String message, int duration, int side) {
        setContext(context);
        setMessage(message);
        setDuration(duration);
        setBackgroundColor(Color.BLACK);
        setTextColor(Color.YELLOW);
        if (side == RIGHT_IMAGE)
            setRightIcon(AppCompatResources.getDrawable(context, R.drawable.ic_smile));
        else if (side == LEFT_IMAGE)
            setLeftIcon(AppCompatResources.getDrawable(context, R.drawable.ic_smile));
        return buildToast();
    }


}
