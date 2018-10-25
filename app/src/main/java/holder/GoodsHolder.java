package holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.mxymonth.R;

import view.MyView;
import widge.ButtonView;


public class GoodsHolder extends RecyclerView.ViewHolder {

    public TextView text_goodsname;
    public TextView text_goods_peice;
    public ImageView img_goodspic;
    public CheckBox check_box_goods;
    public ButtonView btn_view;


    public GoodsHolder(View itemView) {
        super(itemView);
        text_goodsname = itemView.findViewById(R.id.text_goodsname);
        text_goods_peice = itemView.findViewById(R.id.text_goods_peice);
        img_goodspic = itemView.findViewById(R.id.img_goodspic);
        check_box_goods = itemView.findViewById(R.id.check_box_goods);

        btn_view = itemView.findViewById(R.id.btn_view);
    }
}
