
package com.sus.callbackdemo;

import com.sus.callbackdemo.utils.ToastUtils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/*public class MainActivity extends Activity implements android.view.View.OnClickListener {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ToastUtils.toast(this, "perform onclick");

    }

}*/

public class MainActivity extends Activity {

    private Button btn;

    private OnClickListener clickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            ToastUtils.toast(getApplication(), "perform onclick");

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(clickListener);

    }
}

/*public class MainActivity extends Activity {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                ToastUtils.toast(getApplication(), "perform onclick");

            }
        });

    }

}*/
