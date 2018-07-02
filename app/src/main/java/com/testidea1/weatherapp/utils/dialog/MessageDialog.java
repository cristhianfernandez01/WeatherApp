package com.testidea1.weatherapp.utils.dialog;

public class MessageDialog {
    private String setTitle;
    private String message;
    private int ProgressStyle;

    public MessageDialog(String setTitle, String message, int progressStyle) {
        this.setTitle = setTitle;
        this.message = message;
        ProgressStyle = progressStyle;
    }

    public MessageDialog(){}

    public String getSetTitle() {
        return setTitle;
    }

    public void setSetTitle(String setTitle) {
        this.setTitle = setTitle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getProgressStyle() {
        return ProgressStyle;
    }

}