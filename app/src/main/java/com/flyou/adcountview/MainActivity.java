package com.flyou.adcountview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.flyou.AdCountView;

public class MainActivity extends AppCompatActivity implements AdCountView.OnStatusChangeListener, View.OnClickListener {


    private AdCountView adCountView1;

    private AdCountView adcountview2;
    private AdCountView adcountview3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        init1();
        init2();
        init3();
        init4();
        init5();
    }

    private void init5() {

    }

    private void init4() {

    }

    private void init3() {
        adcountview3 = (AdCountView) findViewById(R.id.ad_count_view3);
        adcountview3.setBackgroundColor(getResources().getColor(R.color.base_black_30));
        adcountview3.setOnClickListener(this);
        adcountview3.setOnStatusChangeListener(this);
        adcountview3.setOutRingColor(getResources().getColor(R.color.lightseagreen));
        adcountview3.setTextColor(getResources().getColor(R.color.base_white_100));
        adcountview3.setText("跳过广告");
        adcountview3.setTextSize(15);
        adcountview3.start();
    }

    private void init2() {
        adcountview2 = (AdCountView) findViewById(R.id.ad_count_view2);
        adcountview2.setBackgroundColor(getResources().getColor(R.color.base_white_80));
        adcountview2.setOutRingColor(getResources().getColor(R.color.colorAccent));
        adcountview2.setTextColor(getResources().getColor(R.color.base_black_100));
        adcountview2.setText("进入应用");
        adcountview2.start();
    }

    private void init1() {
        adCountView1 = (AdCountView) findViewById(R.id.ad_count_view1);
        adCountView1.start();
    }


    @Override
    public void onCountViewStart() {
        Toast.makeText(this, "动画开始", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCountViewStop() {
        Toast.makeText(this, "动画结束", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "被点击", Toast.LENGTH_SHORT).show();
    }
}
