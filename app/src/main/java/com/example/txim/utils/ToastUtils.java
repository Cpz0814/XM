package com.example.txim.utils;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

public class ToastUtils {
    private static Toast toast = null;
    public static void toastShow(Context context, String text, int duration) {
        if (toast == null) {
            toast = Toast.makeText(context, text, duration);
        } else {
            toast.setText(text);
        }
        toast.show();
    }
    public static void toastShow(Context context, int resId, int duration)
            throws Resources.NotFoundException {
        if (toast == null) {
            toast = Toast.makeText(context, context.getResources().getText(resId), duration);
        } else {
            toast.setText(context.getResources().getText(resId));
        }
        toast.show();
    }
}
