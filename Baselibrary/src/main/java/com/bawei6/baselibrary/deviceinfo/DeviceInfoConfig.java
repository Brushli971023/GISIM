package com.bawei6.baselibrary.deviceinfo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import androidx.annotation.RequiresApi;
/**
 * @author AZhung
 * @date 2019/12/27
 * @description Phone设备信息获取
 */

public class DeviceInfoConfig {

    private static volatile DeviceInfoConfig singleton;

    private DeviceInfoConfig() {
    }

    public static DeviceInfoConfig getInstance() {
        if (singleton == null) {
            synchronized (DeviceInfoConfig.class) {
                if (singleton == null) {
                    singleton = new DeviceInfoConfig();
                }
            }
        }
        return singleton;
    }

    private Context context;
    public void init(Context context){
        this.context = context;
    }
    /**
     * 获取厂商信息
     * @return
     */
    public String getManufacturer(){
        return Build.MANUFACTURER;
    }

    /**
     * 获取机型信息
     * @return
     */
    public String getModel(){
        return Build.MODEL;
    }

    /**
     * 获取Android系统版本
     * @return
     */
    public String getOsVersion(){
        return String.valueOf(Build.VERSION.SDK_INT);
    }

    /**
     * 获取设备号 包括: GSM - IMEI  CDMA - MEID
     * @return
     */
    @SuppressLint("MissingPermission")
    @RequiresApi(api = Build.VERSION_CODES.M)
    public String getDeviceID(){
        TelephonyManager systemService = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        assert systemService != null;
        @SuppressLint("HardwareIds") String deviceID = systemService.getDeviceId(TelephonyManager.PHONE_TYPE_GSM);
        if (TextUtils.isEmpty(deviceID)){
            deviceID = systemService.getDeviceId(TelephonyManager.PHONE_TYPE_CDMA);
        }
        return deviceID;
    }

    /**
     * 获取UTTID
     */

//    public String getUTDID(){
//        return UMUtils.getUTDID(context);
//    }
//
//
//    public String getDisPlay(){
//        return UMUtils.getDisplayResolution(context);
//    }
//
//    public String getMac(){
//        return UMUtils.getMac(context);
//    }

    public String getLocation(){
        LocationManager systemService = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        assert systemService != null;
        @SuppressLint("MissingPermission") Location lastKnownLocation = systemService.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        assert lastKnownLocation != null;
        return lastKnownLocation.getLongitude()+","+lastKnownLocation.getLatitude();
    }
}