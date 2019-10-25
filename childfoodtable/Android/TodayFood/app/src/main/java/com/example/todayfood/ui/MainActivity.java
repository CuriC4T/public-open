package com.example.todayfood.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.todayfood.R;
import com.example.todayfood.rest.PriceSearch;

public class MainActivity extends AppCompatActivity {
    private PriceSearch priceSearch;

    private Button sbutton;


    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sbutton = (Button) findViewById(R.id.button);
        editText1=(EditText) findViewById(R.id.editText);
        editText2=(EditText) findViewById(R.id.editText2);
        editText3=(EditText) findViewById(R.id.editText3);
        textView4=(TextView) findViewById(R.id.textView7);

        priceSearch= new PriceSearch(this);
        sbutton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String startdate=editText1.getText().toString();
                String enddate=editText2.getText().toString();
                String type=editText3.getText().toString();

                priceSearch.getPrice(startdate,enddate,type);
            }
        });
    }


    public Button getSbutton() {
        return sbutton;
    }


    public EditText getEditText1() {
        return editText1;
    }

    public EditText getEditText2() {
        return editText2;
    }

    public EditText getEditText3() {
        return editText3;
    }

    public TextView getTextView4() {
        return textView4;
    }
}
