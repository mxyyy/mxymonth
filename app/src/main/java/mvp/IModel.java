package mvp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import utils.OkHttpUtils;

public class IModel implements IContract.imodel {
    private String path="http://www.zhaoapi.cn/product/getCarts?uid=71";
    @Override
    public void requestData(final calllListen calllListen) {

        OkHttpUtils instance = OkHttpUtils.getInstance();
        instance.getData(path, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String string = response.body().string();
                calllListen.responMsg(string);
            }
        });
    }
}
