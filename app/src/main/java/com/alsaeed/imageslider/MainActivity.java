package com.alsaeed.imageslider;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private LinearLayout dotLayout;

    private int[] images = {
            R.drawable.image1,
            R.drawable.image1,
            R.drawable.image1
    }; // Replace with your images
    private String[] titles = {"Title 1", "Title 2", "Title 3"};
    private String[] descriptions = {
            "Description for image 1",
            "Description for image 2",
            "Description for image 3"
    };

    private LayoutSliderAdapter sliderAdapter;
    private Handler slideHandler;
    private Runnable slideRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        dotLayout = findViewById(R.id.dotSignLayout);

        // Set Adapter for ViewPager2
        sliderAdapter = new LayoutSliderAdapter(images, titles, descriptions);
        viewPager.setAdapter(sliderAdapter);

        // Add PageTransformer for animations
        viewPager.setPageTransformer(new BookPageTransformer());

        // Initialize Dots
        setupDots(images.length);

        // Highlight Dot on Page Change
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                highlightDot(position);
            }
        });

        // Auto-Slide Functionality
        slideHandler = new Handler(Looper.getMainLooper());
        setupLayoutAutoSlide();
    }

    private void setupDots(int count) {
        for (int i = 0; i < count; i++) {
            ImageView dot = new ImageView(this);
            dot.setImageResource(R.drawable.dot_unselected); // Default dot drawable
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(8, 0, 8, 0);
            dot.setLayoutParams(params);

            final int index = i;
            dot.setOnClickListener(v -> {
                viewPager.setCurrentItem(index);
                resetAutoSlide();
            });

            dotLayout.addView(dot);
        }

        highlightDot(0);
    }

    private void highlightDot(int position) {
        for (int i = 0; i < dotLayout.getChildCount(); i++) {
            ImageView dotIv = (ImageView) dotLayout.getChildAt(i);
            if (i == position) {
                dotIv.setImageResource(R.drawable.dot_selected);
            } else {
                dotIv.setImageResource(R.drawable.dot_unselected);
            }
        }
    }

    private void setupLayoutAutoSlide() {
        slideRunnable = new Runnable() {
            @Override
            public void run() {
                int nextItem = (viewPager.getCurrentItem() + 1) % images.length;
                viewPager.setCurrentItem(nextItem, true);
                slideHandler.postDelayed(this, 3000);
            }
        };
        slideHandler.postDelayed(slideRunnable, 3000);
    }

    private void resetAutoSlide() {
        slideHandler.removeCallbacks(slideRunnable);
        slideHandler.postDelayed(slideRunnable, 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        slideHandler.removeCallbacks(slideRunnable);
    }
}
