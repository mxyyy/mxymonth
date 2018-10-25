package com.bwie.mxymonth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import view.MyView;

public class MainActivity extends AppCompatActivity {

    private EditText edit_query;
    private TextView text_clear;
    private Button btn_search;
    private MyView myView;
    private List<String> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        edit_query = (EditText) findViewById(R.id.edit_query);
        text_clear = (TextView) findViewById(R.id.text_clear);
        btn_search = (Button) findViewById(R.id.btn_search);
        myView = (MyView) findViewById(R.id.myView);

        text_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myView.removeAllViews();
            }
        });
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String st = edit_query.getText().toString();
                list.add(st);
                TextView textView = new TextView(MainActivity.this);
                textView.setText(st);
                myView.addView(textView);
                myView.setPadding(5,5,5,5);
            }
        });
        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ShopCartActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }
}
