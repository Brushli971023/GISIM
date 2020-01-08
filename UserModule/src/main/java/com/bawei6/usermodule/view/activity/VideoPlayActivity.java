package com.bawei6.usermodule.view.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.Nullable;

import com.bawei6.baselibrary.base.BaseActivity;
import com.bawei6.usermodule.R;

/**
 * @author AZhung
 * @date 2020/1/8
 * @description
 */
public class VideoPlayActivity extends BaseActivity {
    private VideoView video_view;
    private MediaController mediaController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        initView();


        String videopath = getIntent().getStringExtra("videopath");
        if (videopath != null){
            video_view.setVideoPath(videopath);
            mediaController = new MediaController(this);
            video_view.setMediaController(mediaController);
            video_view.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
        }
    }

    private void initView() {
        video_view = (VideoView) findViewById(R.id.video_view);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        video_view.stopPlayback();
    }
}
