package com.shopkeep.yodaspeech.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shopkeep.yodaspeech.R;
import com.shopkeep.yodaspeech.models.FactoryModel;
import com.shopkeep.yodaspeech.interfaces.RequestModelInterface;
import com.shopkeep.yodaspeech.interfaces.LoaderInterface;
import com.shopkeep.yodaspeech.models.ViewModel;

public class MainActivity extends AppCompatActivity implements LoaderInterface {
    private static final String TAG = "MainActivity";

    private ViewModel viewModel;
    private RequestModelInterface model;

    TextView resultText;
    EditText inputText;
    View progress;
    Button request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultText = (TextView) findViewById(R.id.out_text);
        inputText = (EditText) findViewById(R.id.input_text);
        progress = findViewById(R.id.input_progress);
        request = (Button) findViewById(R.id.button);

        model = FactoryModel.getModel(this,false);
        viewModel = new ViewModel(this,model);

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard();
                String text = inputText.getText().toString();
                viewModel.requestText(text);

            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();
        clear();
    }

    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
    @Override
    public void showTextLoading(boolean show) {
        resultText.setText("");
        hideSoftKeyboard();
        progress.setVisibility(show?View.VISIBLE:View.GONE);
        inputText.setVisibility(show?View.GONE:View.VISIBLE);
        request.setEnabled(!show);
    }
    public void clear() {
        inputText.setText("");
        resultText.setText("");


    }
    @Override
    public void showText(String text) {
        resultText.setText(getString(R.string.result_text,text));
    }

    @Override
    public void showLoadingError() {
        resultText.setText("ERROR!!!");
    }
}
