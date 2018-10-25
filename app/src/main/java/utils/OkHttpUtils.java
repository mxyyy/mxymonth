package utils;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkHttpUtils {
    //成员变量
    private static OkHttpUtils okHttpUtils;

    public static OkHttpUtils getInstance() {
        if (okHttpUtils == null) {
            synchronized (OkHttpUtils.class) {
                okHttpUtils = new OkHttpUtils();
            }
        }
        return okHttpUtils;
    }

    private OkHttpClient okHttpClient;

    public OkHttpUtils() {
        okHttpClient = new OkHttpClient();
    }

    //get
    public void getData(String path, Callback callback) {
        Request request = new Request.Builder()
                .url(path)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }
}
