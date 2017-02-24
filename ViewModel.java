package com.shopkeep.yodaspeech.models;

import com.shopkeep.yodaspeech.interfaces.RequestModelInterface;
import com.shopkeep.yodaspeech.interfaces.ActionsListenerInterface;
import com.shopkeep.yodaspeech.interfaces.LoaderInterface;

public class ViewModel implements ActionsListenerInterface.ActionsListener {

    final LoaderInterface view;
    final RequestModelInterface model;

    public ViewModel(LoaderInterface view, RequestModelInterface model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void requestText(String text) {
        view.showTextLoading(true);
        model.requestText(text, new RequestModelInterface.RequestCallback() {
            @Override
            public void onRequestCompleted(String text, Throwable t) {
                view.showTextLoading(false);
                if (t == null) {
                    view.showText(text);
                }else{
                    view.showLoadingError();
                }
            }
        });
    }


}
