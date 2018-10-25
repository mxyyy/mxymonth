package holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.mxymonth.R;

public class ShopHolder extends RecyclerView.ViewHolder {

    public TextView text_shopname;
    public CheckBox check_box_shop;
    public RecyclerView recyc_view_goods;

    public ShopHolder(View itemView) {
        super(itemView);
        text_shopname = itemView.findViewById(R.id.text_shopname);
        check_box_shop = itemView.findViewById(R.id.check_box_shop);
        recyc_view_goods = itemView.findViewById(R.id.recyc_view_goods);
    }
}
