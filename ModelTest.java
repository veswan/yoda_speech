package com.shopkeep.yodaspeech;

import com.shopkeep.yodaspeech.interfaces.RequestModelInterface;
import com.shopkeep.yodaspeech.interfaces.LoaderInterface;
import com.shopkeep.yodaspeech.models.ViewModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;


public class ModelTest {

    @Mock
    LoaderInterface view;
    ViewModel viewModel;
    @Mock
    RequestModelInterface model;
    final String SAMPLE_TEXT = "hello, how are you, hmm?";

    @Before
    public void setupYodaViewModel() {
        MockitoAnnotations.initMocks(this);
        viewModel = new ViewModel(view,model);
    }

    @Test
    public void requestTextModel_showsTextLoadingIndicator() {
        // When the viewmodel is asked to get the converted text
        viewModel.requestText(SAMPLE_TEXT);
        // Then a request made by model,
        verify(model).requestText(anyString(),any(RequestModelInterface.RequestCallback.class));
        verify(view).showTextLoading(true); // shown in the UI
    }

}