package com.baweigame.xmpplibrary.callback;

import com.baweigame.xmpplibrary.entity.MsgEntity;

public interface IMsgCallback {
    void onSuccess(MsgEntity msgEntity);
    void onFailed(Throwable throwable);
}
