package com.suli.myutils.fragment.performance;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.suli.myutils.R;

import java.util.HashMap;

/**
 * Created by suli on 16-9-22.
 */

public class NextActivity extends Activity {

    static HashMap<Long, Activity> hashMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        hashMap.put(System.currentTimeMillis(), this);

        findViewById(R.id.btn_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
