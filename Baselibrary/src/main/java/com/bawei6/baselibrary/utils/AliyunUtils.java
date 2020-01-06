package com.bawei6.baselibrary.utils;

import android.content.Context;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.GetObjectRequest;
import com.alibaba.sdk.android.oss.model.GetObjectResult;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.bawei6.baselibrary.common.BaseConstant;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Author:Zhangyue
 * Date:2019-12-6
 * Desc：阿里云OSS工具类
 */
public class AliyunUtils {

    private static AliyunUtils instance = new AliyunUtils();
    private OSS oss;

    private AliyunUtils() {

    }

    public static AliyunUtils getInstance() {
        return instance;
    }

    /**
     * 初始化阿里云OSS设置 一般在Application中调用
     * @param context
     */
    public void init(Context context) {
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000);
        conf.setSocketTimeout(15 * 1000);
        conf.setMaxConcurrentRequest(5);
        conf.setMaxErrorRetry(2);
        OSSLog.enableLog();

        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(BaseConstant.ACCESS_KEY_ID, BaseConstant.ACCESS_KEY_SECRET);

        oss = new OSSClient(context, BaseConstant.ALIYUN_OSS_ENDPOINT, credentialProvider, conf);
    }

    /**
     * 上传
     *
     * @param bucketName    空间名称
     * @param key           文件名称
     * @param localFilePath 本地文件类路径
     */
    public void upload(String bucketName, String key, String localFilePath,OSSCompletedCallback callback) {
        PutObjectRequest put = new PutObjectRequest(bucketName, key, localFilePath);

        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                LogUtils.d("PutObject "+ "currentSize: " + currentSize + " totalSize: " + totalSize);
            }
        });

        OSSAsyncTask task = oss.asyncPutObject(put,callback);
    }

    /**
     * 下载文件
     *
     * @param bucetName    空间名
     * @param key          服务器上的文件key 对应于上传的key 一般是文件名
     * @param downloadPath 下载存储的本地路径
     */
    public void download(String bucetName, String key, final String downloadPath) {
        GetObjectRequest get = new GetObjectRequest(bucetName, key);
        OSSAsyncTask getTask = oss.asyncGetObject(get, new OSSCompletedCallback<GetObjectRequest, GetObjectResult>() {
            @Override
            public void onSuccess(GetObjectRequest request, GetObjectResult result) {
                long length = result.getContentLength();
                byte[] buffer = new byte[(int) length];
                int readCount = 0;
                while (readCount < length) {
                    try {
                        readCount += result.getObjectContent().read(buffer, readCount, (int) length - readCount);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    FileOutputStream fout = new FileOutputStream(downloadPath);
                    fout.write(buffer);
                    fout.close();
                } catch (Exception e) {
                    OSSLog.logInfo(e.toString());
                }
            }

            @Override
            public void onFailure(GetObjectRequest request, ClientException clientException, ServiceException serviceException) {
                LogUtils.e("file download failed...");
            }
        });
        getTask.waitUntilFinished();

    }
}