package com.bawei6.baselibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bawei6.baselibrary.R;

/**
 * @author AZhung
 * @date 2019/12/27
 * @description 自定义标题栏
 */
public class CustomTitleBar extends LinearLayout {
    private ImageView tv_customview_titlebar_left;
    private TextView tv_customview_titlebar_title;
    private TextView tv_customview_titlebar_right;

    public CustomTitleBar(Context context) {
        super(context);
    }

    public CustomTitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initView(context, attrs);
    }

    public CustomTitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void initView(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.customview_titlebar, null);

        tv_customview_titlebar_left = view.findViewById(R.id.tv_customview_titlebar_left);
        tv_customview_titlebar_right = view.findViewById(R.id.tv_customview_titlebar_right);
        tv_customview_titlebar_title = view.findViewById(R.id.tv_customview_titlebar_title);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleBar);
        String titleBarName = typedArray.getString(R.styleable.CustomTitleBar_titleBarName);
        int leftSrc = typedArray.getResourceId(R.styleable.CustomTitleBar_leftSrc, 0);
        int reightSrc = typedArray.getResourceId(R.styleable.CustomTitleBar_rightSrc, 0);

        if (titleBarName != null){
            tv_customview_titlebar_title.setText(titleBarName);
        }
        if (leftSrc != 0){
            tv_customview_titlebar_left.setImageResource(leftSrc);
        }
        if (reightSrc != 0){
            tv_customview_titlebar_right.setBackgroundResource(reightSrc);
        }

        this.addView(view);
        /**
         * 释放资源
         */
        typedArray.recycle();

    }

    /**
     * @param listener
     * @Desc 左边图片点击事件
     */
    public void leftOnClick(OnClickListener listener){
        tv_customview_titlebar_left.setOnClickListener(listener);
    }

    /**
     * @param listener
     * @Desc 右边图片点击事件
     */
    public void rightOnClick(OnClickListener listener){
        tv_customview_titlebar_right.setOnClickListener(listener);
    }
}
