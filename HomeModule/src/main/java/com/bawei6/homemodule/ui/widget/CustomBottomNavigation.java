package com.bawei6.homemodule.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bawei6.homemodule.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AZhung
 * @date 2020/1/2
 * @description 自定义底部导航栏
 */
public class CustomBottomNavigation extends RelativeLayout implements View.OnClickListener {
    private ImageView navigation_image_first;
    private ImageView navigation_image_second;
    private ImageView navigation_image_third;
    private ImageView navigation_image_fourth;
    private ImageView navigation_mid_image;
    private List<Integer> trueDrawableIds = new ArrayList<>();
    private List<Integer> falseDrawableIds = new ArrayList<>();
    private List<Integer> drawableIds = new ArrayList<>();

    public CustomBottomNavigation(Context context) {
        super(context);
    }

    public CustomBottomNavigation(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
        initData();
    }

    private void initData() {
        falseDrawableIds.add(R.drawable.ic_action_contact_false);
        falseDrawableIds.add(R.drawable.ic_action_stars_false);
        falseDrawableIds.add(R.drawable.ic_action_mail_false);
        falseDrawableIds.add(R.drawable.ic_action_camera_false);
        falseDrawableIds.add(R.drawable.ic_action_share_false);
        trueDrawableIds.add(R.drawable.ic_action_contact_true);
        trueDrawableIds.add(R.drawable.ic_action_stars_true);
        trueDrawableIds.add(R.drawable.ic_action_mail_true);
        trueDrawableIds.add(R.drawable.ic_action_camera_true);
        trueDrawableIds.add(R.drawable.ic_action_share_true);
        drawableIds.add(R.drawable.ic_action_contact_false);
        drawableIds.add(R.drawable.ic_action_stars_false);
        drawableIds.add(R.drawable.ic_action_mail_true);
        drawableIds.add(R.drawable.ic_action_camera_false);
        drawableIds.add(R.drawable.ic_action_share_false);

        setNavigationDrawable();
    }

    @SuppressLint("InflateParams")
    private void initView(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.bottom_navition, null);
        navigation_image_first = view.findViewById(R.id.navigation_image_first);
        navigation_image_second = view.findViewById(R.id.navigation_image_second);
        navigation_image_third = view.findViewById(R.id.navigation_image_third);
        navigation_image_fourth = view.findViewById(R.id.navigation_image_fourth);
        navigation_mid_image = view.findViewById(R.id.navigation_mid_image);

    }

    public CustomBottomNavigation(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.navigation_image_first) {
            for (int i = 0;i < drawableIds.size()-1;i++){
                int stemp = trueDrawableIds.get(0);
                drawableIds.set(0,falseDrawableIds.get(2));
                drawableIds.set(2,stemp);

                int trueStemp = trueDrawableIds.get(0);
                trueDrawableIds.set(0,trueDrawableIds.get(2));
                trueDrawableIds.set(2,trueStemp);

                int falseStemp = falseDrawableIds.get(0);
                falseDrawableIds.set(0,falseDrawableIds.get(2));
                falseDrawableIds.set(2,falseStemp);
            }
            setNavigationDrawable();
        }
    }

    private void setNavigationDrawable() {
        navigation_image_first.setImageResource(drawableIds.get(0));
        navigation_image_second.setImageResource(drawableIds.get(1));
        navigation_mid_image.setImageResource(drawableIds.get(2));
        navigation_image_third.setImageResource(drawableIds.get(3));
        navigation_image_fourth.setImageResource(drawableIds.get(4));
    }
}
