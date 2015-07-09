
package com.sus.callbackdemo.utils;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.view.Gravity;
import android.widget.Toast;

public class ToastUtils {

    private static Toast toast;

    public static void showToast(Context context, String message) {
        if (toast == null) {
            toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    public static void showToast(Context context, int res) {
        if (toast == null) {
            toast = Toast.makeText(context, res, Toast.LENGTH_LONG);
        } else {
            toast.setText(res);
        }
        toast.show();
    }

    public static void toast(Context context, CharSequence text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void toast(Context context, int resId) {
        try {
            Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void toastCenter(Context context, int resId) {
        Toast toast = Toast.makeText(context, resId, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);// 设置Toast显示位置居中
        toast.show();
    }

    public static void toastLong(Context context, CharSequence text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public static void toastLong(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
    }
}
