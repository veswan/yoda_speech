package com.shopkeep.yodaspeech.models;

import com.shopkeep.yodaspeech.interfaces.RequestModelInterface;

public class MockModel implements RequestModelInterface {
    private String TAG = "YodaServiceModel";
    private boolean fail;

    public void setFail(boolean fail) {
        this.fail = fail;
    }

    @Override
    public void requestText(String text, final RequestCallback callback) {
        if(!fail){
            callback.onRequestCompleted(text,null);
        }else{
            callback.onRequestCompleted(text,new Exception("FAIL"));
        }
    }
}
