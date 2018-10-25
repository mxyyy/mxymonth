package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.mxymonth.R;

import org.greenrobot.eventbus.EventBus;


import java.util.List;

import bean.ShopCartBean;
import holder.GoodsHolder;
import utils.Https2http;
import widge.ButtonView;

public class GoodsAdapter extends RecyclerView.Adapter<GoodsHolder> {
    private Context context;
    private List<ShopCartBean.DataBean.ListBean> listBeans;
    private TextView text_num;

    public GoodsAdapter(Context context, List<ShopCartBean.DataBean.ListBean> listBeans) {
        this.context = context;
        this.listBeans = listBeans;
    }

    @NonNull
    @Override
    public GoodsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.goods_layout, null);
        GoodsHolder holder = new GoodsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final GoodsHolder holder, final int position) {
        holder.check_box_goods.setChecked(listBeans.get(position).isInnerchecked());

        Glide.with(context).load(Https2http.Https2https(listBeans.get(position).getImages().split("\\|")[0])).into(holder.img_goodspic);
        holder.text_goodsname.setText(listBeans.get(position).getTitle());
        text_num = holder.btn_view.findViewById(R.id.text_num);
        text_num.setText(listBeans.get(position).getNum()+"");
        holder.text_goods_peice.setText("￥："+listBeans.get(position).getPrice());

        //加减
        holder.btn_view.setAddAndMinusu(new ButtonView.AddAndMinus() {
            @Override
            public void add() {
                listBeans.get(position).setNum(listBeans.get(position).getNum()+1);
                notifyDataSetChanged();
                ShopCartBean shopCartBean = new ShopCartBean();
                EventBus.getDefault().post(shopCartBean);
            }

            @Override
            public void minus() {
                listBeans.get(position).setNum(listBeans.get(position).getNum()-1);
                notifyDataSetChanged();
                ShopCartBean shopCartBean = new ShopCartBean();
                EventBus.getDefault().post(shopCartBean);
            }
        });
        holder.check_box_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listBeans.get(position).setInnerchecked(holder.check_box_goods.isChecked());
                onclickchangelisten.onchecked(holder.getLayoutPosition(),holder.check_box_goods.isChecked());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }
    private onclickchangelisten onclickchangelisten;
    public void setOnclickchangelisten(GoodsAdapter.onclickchangelisten onclickchangelisten){
        this.onclickchangelisten= onclickchangelisten;
    }
    public interface onclickchangelisten{
        void onchecked(int layoutPosition, boolean checked);
    }
}
