package com.shopkeep.yodaspeech.interfaces;

public interface RequestModelInterface {

    interface RequestCallback {
        void onRequestCompleted(String text,Throwable t);
    }

    void requestText(String text,RequestCallback callback);
}
