package com.alsaeed.imageslider;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

public class BookPageTransformer implements ViewPager2.PageTransformer {

    @Override
    public void transformPage(@NonNull View page, float position) {
        if (position < -1) { // Page is off-screen to the left
            page.setAlpha(0f); // Make it fully transparent
        } else if (position <= 0) { // Page is sliding out to the left
            page.setAlpha(1f); // Fully visible
            page.setTranslationX(0f); // No horizontal translation
            page.setPivotX(page.getWidth()); // Rotate around the right edge
            page.setRotationY(-90f * Math.abs(position)); // Rotate to the left
        } else if (position <= 1) { // Page is sliding in from the right
            page.setAlpha(1f); // Fully visible
            page.setTranslationX(0f); // No horizontal translation
            page.setPivotX(0f); // Rotate around the left edge
            page.setRotationY(90f * Math.abs(position)); // Rotate to the right
        } else { // Page is off-screen to the right
            page.setAlpha(0f); // Make it fully transparent
        }
    }
}
