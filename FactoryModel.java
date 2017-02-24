package com.shopkeep.yodaspeech.models;

import android.content.Context;

import com.shopkeep.yodaspeech.interfaces.RequestModelInterface;

public class FactoryModel {
    public static RequestModelInterface getModel(Context context, boolean mock){
        if(mock){
            return new MockModel();
        }else {
            return new ServiceModel(context);
        }
    }
}
