package com.bawei6.gisim;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.baweigame.xmpplibrary.XmppManager;

/**
 * @author AZhung
 * @date 2020/1/6
 * @description
 */
public class XmppService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                XmppManager.getInstance();
            }
        }.start();
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
