package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.mxymonth.R;

import java.util.List;

import bean.ShopCartBean;
import holder.ShopHolder;

public class ShopAdapter extends RecyclerView.Adapter<ShopHolder> {
    private List<ShopCartBean.DataBean> list;
    private Context context;
    private GoodsAdapter goodsAdapter;

    public ShopAdapter(List<ShopCartBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ShopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShopHolder(LayoutInflater.from(context).inflate(R.layout.shop_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ShopHolder holder, final int position) {
        holder.check_box_shop.setChecked(list.get(position).isOutchecked());

        holder.text_shopname.setText(list.get(position).getSellerName());
        LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        holder.recyc_view_goods.setLayoutManager(manager);
        GoodsAdapter goodsAdapter = new GoodsAdapter(context, list.get(position).getList());
        holder.recyc_view_goods.setAdapter(goodsAdapter);
        //商家选中控制里面的子条目
        holder.check_box_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ischecked = holder.check_box_shop.isChecked();//定义商家的选中状态
                list.get(position).setOutchecked(ischecked);
                if (ischecked) {
                    for (int i = 0; i < list.get(holder.getLayoutPosition()).getList().size(); i++) {
                        list.get(holder.getLayoutPosition()).getList().get(i).setInnerchecked(true);
                    }
                } else {
                    for (int i = 0; i < list.get(holder.getLayoutPosition()).getList().size(); i++) {
                        list.get(holder.getLayoutPosition()).getList().get(i).setInnerchecked(false);
                    }
                }
                onclickchangelisten.onitemchecked(holder.getLayoutPosition(), ischecked);
            }
        });

        //获取里层条目状态
        goodsAdapter.setOnclickchangelisten(new GoodsAdapter.onclickchangelisten() {
            @Override
            public void onchecked(int layoutPosition, boolean checked) {
                boolean b = true;
                for (int i = 0; i < list.get(holder.getLayoutPosition()).getList().size(); i++) {
                    boolean innerchecked = list.get(holder.getLayoutPosition()).getList().get(i).isInnerchecked();
                    b = (b & innerchecked);
                }
                holder.check_box_shop.setChecked(b);
                list.get(position).setOutchecked(b);
                onclickchangelisten.onchecked(holder.getLayoutPosition(), checked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private onclickchangelisten onclickchangelisten;

    public void setOnclickchangelisten(onclickchangelisten onclickchangelisten) {
        this.onclickchangelisten = onclickchangelisten;
    }

    public interface onclickchangelisten {
        void onchecked(int layoutPosition, boolean checked);

        void onitemchecked(int layoutPosition, boolean ischecked);
    }
}
