package com.bwie.mxymonth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import adapter.ShopAdapter;
import bean.Bean;
import bean.ShopCartBean;
import mvp.IContract;
import mvp.IPresenter;

public class ShopCartActivity extends AppCompatActivity implements IContract.iview {
    private RecyclerView recyc_view_shop;
    private CheckBox check_box_all;
    private TextView text_total;
    private TextView text_total_nums;
    private IPresenter iPresenter;
    private ShopAdapter adapter;
    private List<ShopCartBean.DataBean> data;
    private ShopCartBean shopCartBean;
    private ShopCartBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_cart);
        EventBus.getDefault().register(this);
        //初始化控件
        recyc_view_shop = (RecyclerView) findViewById(R.id.recyc_view_shop);
        check_box_all = (CheckBox) findViewById(R.id.check_box_all);
        text_total = (TextView) findViewById(R.id.text_total);
        text_total_nums = (TextView) findViewById(R.id.text_total_nums);

        iPresenter = new IPresenter();
        iPresenter.attachView(this);
        iPresenter.requestInfo();

        check_box_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_box_all.isChecked()) {
                    for (int i = 0; i < bean.getData().size(); i++) {
                        bean.getData().get(i).setOutchecked(true);
                        for (int j = 0; j < bean.getData().get(i).getList().size(); j++) {
                            bean.getData().get(i).getList().get(j).setInnerchecked(true);
                        }
                    }
                } else {
                    for (int i = 0; i < bean.getData().size(); i++) {
                        bean.getData().get(i).setOutchecked(false);
                        for (int j = 0; j < bean.getData().get(i).getList().size(); j++) {
                            bean.getData().get(i).getList().get(j).setInnerchecked(false);
                        }
                    }

                }
                //总价的方法
                initTotal();
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initTotal() {
        int total = 0;
        int nums = 0;
        for (int i = 0; i < bean.getData().size(); i++) {
            for (int j = 0; j < bean.getData().get(i).getList().size(); j++) {
                if (bean.getData().get(i).getList().get(j).isInnerchecked()) {
                    total += bean.getData().get(i).getList().get(j).getNum() * bean.getData().get(i).getList().get(j).getPrice();
                }
            }
        }
        text_total.setText("￥" + total + "元");
        adapter.notifyDataSetChanged();
    }

    //接收加减的方法，，，，，主线程
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void msg(Bean ha) {
        initTotal();
    }

    @Override
    public void ShowData(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                bean = gson.fromJson(message, ShopCartBean.class);
                data = bean.getData();
                LinearLayoutManager manager = new LinearLayoutManager(ShopCartActivity.this, LinearLayoutManager.VERTICAL, false);
                recyc_view_shop.setLayoutManager(manager);
                adapter = new ShopAdapter(data,ShopCartActivity.this);
                recyc_view_shop.setAdapter(adapter);
                //控制商家条目
                adapter.setOnclickchangelisten(new ShopAdapter.onclickchangelisten() {
                    @Override
                    public void onchecked(int layoutPosition, boolean checked) {
                        boolean b = true;
                        for (int i = 0; i < bean.getData().size(); i++) {
                            boolean outchecked = bean.getData().get(i).isOutchecked();
                            for (int j = 0; j < bean.getData().get(i).getList().size(); j++) {
                                boolean innerchecked = bean.getData().get(i).getList().get(j).isInnerchecked();
                                b = (b & outchecked & innerchecked);
                            }

                        }
                        check_box_all.setChecked(b);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onitemchecked(int layoutPosition, boolean ischecked) {
                        //设置外层的选中状态
                        bean.getData().get(layoutPosition).setOutchecked(ischecked);
                        boolean b = true;
                        for (int i = 0; i < bean.getData().size(); i++) {
                            boolean outchecked = bean.getData().get(i).isOutchecked();
                            for (int j = 0; j < bean.getData().get(i).getList().size(); j++) {
                                boolean innerchecked = bean.getData().get(i).getList().get(j).isInnerchecked();
                                b = (b & outchecked & innerchecked);
                            }
                        }
                        check_box_all.setChecked(b);
                        adapter.notifyDataSetChanged();

                    }
                });
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.detachView(this);
        EventBus.getDefault().unregister(this);
    }
}
