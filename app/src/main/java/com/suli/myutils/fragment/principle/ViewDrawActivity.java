package com.suli.myutils.fragment.principle;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.suli.myutils.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import common.widget.BatteryView;

/**
 * Created by suli on 16-9-6.
 * View的绘制原理与事件体系解析
 */
public class ViewDrawActivity extends Activity {

    private static final int OFFSET = 10;

    @Bind(R.id.v_battery)
    BatteryView batteryView;

    private float percent = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_draw_activity);
        ButterKnife.bind(this);
        updateBattery();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    @OnClick(R.id.btn_sub)
    public void onSubBatteryPercents() {
        if (percent - OFFSET >= 0) {
            percent -= OFFSET;
        } else {
            percent = 0;
        }
        updateBattery();
        batteryView.requestLayout();
    }

    @OnClick(R.id.btn_plus)
    public void onPlusBatteryPercents() {
        if (percent + OFFSET <= 100) {
            percent += OFFSET;
        } else {
            percent = 100;
        }
        updateBattery();
        batteryView.invalidate();
    }

    private void updateBattery() {
        Toast.makeText(this, "set percent:" + percent, Toast.LENGTH_SHORT).show();
        batteryView.setPercent(percent / 100.0f);
    }
}
