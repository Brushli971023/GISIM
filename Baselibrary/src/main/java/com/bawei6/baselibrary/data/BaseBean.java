package com.bawei6.baselibrary.data;
/**
 * @author AZhung
 * @date 2019/12/27
 * @description 网络请求回调基类
 */
public class BaseBean<T> {
    /**
     * code : 200
     * data : T
     * msg : 操作成功
     */

    private int code;
    private T data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
