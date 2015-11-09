package com.example.naveenkumar.b9_weather;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.naveenkumar.b9_weather.common.WConstants;
import com.example.naveenkumar.b9_weather.interfaces.TaskCalback;
import com.example.naveenkumar.b9_weather.parser.Info;
import com.example.naveenkumar.b9_weather.tasks.DataTask;


public class MainActivity extends ActionBarActivity {
    private TextView textView;
    private EditText cityEt,stateEt;
    private Button getdataBtn;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        getdataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd =ProgressDialog.show(MainActivity.this,"Please wait ","Loading");
                //Do operation here
                //Construct URL
                String city =cityEt.getText().toString();
                String state =stateEt.getText().toString();


                String url = WConstants.URL_HEAD+state+"/"+city+WConstants.URL_TAIL;
                String urls[]= new String[]{url};

                DataTask task =new DataTask(taskCalback);
            }
        });
    }
    public void init(){

        textView =(TextView)findViewById(R.id.textView);
        cityEt =(EditText)findViewById(R.id.cityEt);
        stateEt =(EditText)findViewById(R.id.stateEt);
        getdataBtn =(Button)findViewById(R.id.getdataBtn);

    }

    TaskCalback taskCalback =new TaskCalback() {

        @Override
        public void getData(Info info) {
            String message ="Temp in C ="+info.getCurrent_observation().getTemp_c()+"\n Temp in F"+info.getCurrent_observation().getTemp_f();
            textView.setText(message);

            if(pd !=null && pd.isShowing())
                pd.dismiss();
        }
    };


}
