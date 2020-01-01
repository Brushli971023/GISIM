package com.bawei6.baselibrary.utils;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.bawei6.baselibrary.common.BaseConstant;
import com.bawei6.baselibrary.data.BaseApiService;
import com.bawei6.baselibrary.data.TokenBean;
import com.bawei6.baselibrary.deviceinfo.AppInfoConfig;
import com.bawei6.baselibrary.deviceinfo.DeviceInfoConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * @author AZhung
 * @date 2019/12/27
 * @description 网络请求工具类
 */
public class RetrofitUtils {
    private static final String TAG = "RetrofitUtils";
    private static final String BASEURL = "http://api.zydeveloper.com:10001/";
    private static volatile RetrofitUtils instance;
    private Retrofit mRetrofit;

    public static RetrofitUtils getInstance() {
        if (instance == null) {
            synchronized (RetrofitUtils.class) {
                if (instance == null) {
                    instance = new RetrofitUtils();
                }
            }
        }
        return instance;
    }


    private RetrofitUtils() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(createRequestInterceptor())
                .addNetworkInterceptor(createLogInterceptor())
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();


        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }





    /***
     * 创建 Log拦截器
     * @return Log拦截器
     */
    private HttpLoggingInterceptor createLogInterceptor() {
        HttpLoggingInterceptor mLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i(TAG, message);
            }
        });
        mLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        return mLoggingInterceptor;
    }




    /***
     * 自定义拦截器
     * @return Interceptor
     */
    private Interceptor createRequestInterceptor() {
        return new Interceptor() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public Response intercept(Chain chain) throws IOException {
                //拦截请求
                Request request = chain.request();
                //拦截响应
                Response response = chain.proceed(request);

                //添加头信息
                Request.Builder requestBuilder = request.newBuilder()
                        .addHeader("Content-Type", "application-json")
                        .addHeader("charest", "utf-8")
                        .addHeader("manufacturer", DeviceInfoConfig.getInstance().getManufacturer())
                        .addHeader("model",DeviceInfoConfig.getInstance().getModel())
                        .addHeader("deviceid",DeviceInfoConfig.getInstance().getDeviceID())
//                        .addHeader("utdid",DeviceInfoConfig.getInstance().getUTDID())
                        .addHeader("packagename", AppInfoConfig.getInstance().getPackageNames())
                        .addHeader("versioncode",AppInfoConfig.getInstance().getVersionCode())
                        .addHeader("versionname",AppInfoConfig.getInstance().getVersionName())
                        .addHeader("ip","169.254.111.111")
//                        .addHeader("location",DeviceInfoConfig.getInstance().getLocation())
//                        .addHeader("macadress",DeviceInfoConfig.getInstance().getMac())
//                        .addHeader("display",DeviceInfoConfig.getInstance().getDisPlay())
                        .addHeader("osversion",DeviceInfoConfig.getInstance().getOsVersion());
                //判断响应码
                if (response.code() == 401) {
                    requestBuilder.addHeader("Authorization", "bearer " + requestToken());
                }
                return chain.proceed(requestBuilder.build());
            }
        };
    }

    /***
     * 同步请求 token
     * @return 请求到的token
     */
    private String requestToken() {
        try {
            TokenBean body = mRetrofit.create(BaseApiService.class)
                    .getToken("password", BaseConstant.USER_CODE, "")
                    .execute().body();
            return body.getAccess_token();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    /***
     * @Desc 创建API
     * @param clazz
     * @param <T>
     * @return api
     */
    public <T> T create(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }
}
