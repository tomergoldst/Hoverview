package com.tomergoldst.hoverviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tomergoldst.hoverview.HoverView;
import com.tomergoldst.hoverview.HoverViewManager;

public class MainActivity extends AppCompatActivity implements
        HoverViewManager.HoverViewListener,
        View.OnClickListener
{
    private static final String TAG = MainActivity.class.getSimpleName();

    HoverViewManager mHoverViewManager;
    RelativeLayout mRootLayout;
    RelativeLayout mParentLayout;
    TextView mTextView;

    Button mAboveBtn;
    Button mBelowBtn;
    Button mLeftToBtn;
    Button mRightToBtn;

    RadioButton mAlignRight;
    RadioButton mAlignLeft;
    RadioButton mAlignCenter;

    @HoverView.Align int mAlign = HoverView.ALIGN_CENTER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRootLayout = findViewById(R.id.root_layout);
        mParentLayout = findViewById(R.id.parent_layout);
        mTextView = findViewById(R.id.text_view);

        mHoverViewManager = new HoverViewManager(this);

        mAboveBtn = findViewById(R.id.button_above);
        mBelowBtn = findViewById(R.id.button_below);
        mLeftToBtn = findViewById(R.id.button_left_to);
        mRightToBtn = findViewById(R.id.button_right_to);

        mAboveBtn.setOnClickListener(this);
        mBelowBtn.setOnClickListener(this);
        mLeftToBtn.setOnClickListener(this);
        mRightToBtn.setOnClickListener(this);

        mAlignCenter = findViewById(R.id.button_align_center);
        mAlignLeft = findViewById(R.id.button_align_left);
        mAlignRight = findViewById(R.id.button_align_right);

        mAlignCenter.setOnClickListener(this);
        mAlignLeft.setOnClickListener(this);
        mAlignRight.setOnClickListener(this);

        mAlignCenter.setChecked(true);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        View view = LayoutInflater.from(this).inflate(R.layout.hover_view, mRootLayout, false);

        HoverView.Builder builder = new HoverView.Builder(this,
                mTextView,
                mRootLayout,
                view,
                HoverView.POSITION_ABOVE);
        builder.setAlign(mAlign);
        mHoverViewManager.show(builder.build());
    }



    @Override
    public void onHoverViewDismissed(View view, int anchorViewId, boolean byUser) {
        Log.d(TAG, "hoverview near anchor view " + anchorViewId + " dismissed");

        if (anchorViewId == R.id.text_view) {
            // Do something when an hoverview near view with id "R.id.text_view" has been dismissed
        }
    }

    @Override
    public void onClick(View view) {
        HoverView.Builder builder;

        View hoverView = LayoutInflater.from(this).inflate(R.layout.hover_view, mRootLayout, false);

        switch (view.getId()){
            case R.id.button_above:
                mHoverViewManager.findAndDismiss(mTextView);
                builder = new HoverView.Builder(this, mTextView, mRootLayout, hoverView, HoverView.POSITION_ABOVE);
                builder.setAlign(mAlign);
                mHoverViewManager.show(builder.build());
                break;
            case R.id.button_below:
                mHoverViewManager.findAndDismiss(mTextView);
                builder = new HoverView.Builder(this, mTextView, mRootLayout, hoverView, HoverView.POSITION_BELOW);
                builder.setAlign(mAlign);
                mHoverViewManager.show(builder.build());
                break;
            case R.id.button_left_to:
                mHoverViewManager.findAndDismiss(mTextView);
                builder = new HoverView.Builder(this, mTextView, mRootLayout, hoverView, HoverView.POSITION_LEFT_TO);
                mHoverViewManager.show(builder.build());
                break;
            case R.id.button_right_to:
                mHoverViewManager.findAndDismiss(mTextView);
                builder = new HoverView.Builder(this, mTextView, mRootLayout, hoverView, HoverView.POSITION_RIGHT_TO);
                mHoverViewManager.show(builder.build());
                break;
            case R.id.button_align_center:
                mAlign = HoverView.ALIGN_CENTER;
                break;
            case R.id.button_align_left:
                mAlign = HoverView.ALIGN_LEFT;
                break;
            case R.id.button_align_right:
                mAlign = HoverView.ALIGN_RIGHT;
                break;
        }
    }
}
