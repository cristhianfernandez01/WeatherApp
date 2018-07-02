package com.testidea1.weatherapp.utils.dialog;

import android.app.ProgressDialog;
import android.content.Context;

import com.testidea1.weatherapp.R;

public class DialogUtils {
    private static String Message;
    private static String Title;
    private static int ProgressStyle;
    private static ProgressDialog m_Dialog;

    public static ProgressDialog showProgressDialog(
            Context context, MessageDialog msgDialog){

        BuildParameterProgressDialog(context, msgDialog);
        m_Dialog = new ProgressDialog(context);

        m_Dialog.setTitle(Title);
        m_Dialog.setMessage(Message);
        m_Dialog.setProgressStyle(ProgressStyle);
        m_Dialog.setCancelable(false);
        m_Dialog.show();
        return m_Dialog;
    }

    public static ProgressDialog getDialog(){
        return m_Dialog;
    }

    private static void BuildParameterProgressDialog(Context context, MessageDialog msgDialog) {
        if (msgDialog != null) {
            Message = msgDialog.getMessage();
            Title = msgDialog.getSetTitle();
            ProgressStyle = msgDialog.getProgressStyle();
            return;
        }

        Message = context.getResources().getString(R.string.progress_bar_title);
        Title = context.getResources().getString(R.string.loading);
        ProgressStyle = ProgressDialog.STYLE_SPINNER;
    }
}
