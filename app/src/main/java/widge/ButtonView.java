package widge;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.mxymonth.R;

public class ButtonView extends LinearLayout implements View.OnClickListener {
    private Button jia;
    private Button jian;
    private TextView text_num;
    public ButtonView(Context context) {
        this(context,null);
    }


    public ButtonView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);

    }

    public ButtonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_layout, this);
        jia = findViewById(R.id.jia);
        jian = findViewById(R.id.jian);
        text_num = findViewById(R.id.text_num);
        jia.setOnClickListener(this);
        jian.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        String s = text_num.getText().toString();
        int i = Integer.parseInt(s);
        switch (v.getId()){
            case R.id.jia:

                    addAndMinusu.add();

                break;
            case R.id.jian:
                if (i>0){
                    if(addAndMinusu!=null){
                        addAndMinusu.minus();
                    }
                }else {
                    Toast.makeText(getContext(),"商品数量不能小于0",Toast.LENGTH_SHORT).show();
                }

        }
    }


    //定义的接口回调
    private   AddAndMinus addAndMinusu;
    public interface AddAndMinus{
        void add();
        void minus();
    }
    public void setAddAndMinusu(AddAndMinus addAndMinusu){
        this.addAndMinusu=addAndMinusu;
    }
}
