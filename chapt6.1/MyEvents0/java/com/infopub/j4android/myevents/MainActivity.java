package com.infopub.j4android.myevents;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
public class MainActivity extends AppCompatActivity
        implements View.OnClickListener  {  // View.OnClickListener 구현을 선언 EHI
    Button showbio;
    EditText txtbirthdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showComponet();
    }
    public void showComponet(){
       //layout에 있는 콤퍼넌트 객체생성
        txtbirthdate=(EditText)findViewById(R.id.txtbirthdate);
        showbio=(Button)findViewById(R.id.showbio);
        //OnClickListener를 구현한 이벤트 핸들러 객체 -> this
        showbio.setOnClickListener(this);   //이벤트 핸들러 객체 등록
}
    @Override
    public void onClick(View v) {   //OnClickListener의 이벤트 핸들러 메서드 EHM
       if(v==showbio){
            String st=String.format("오늘은 %s다.",new Date());
           txtbirthdate.setText(st);
            Toast.makeText(getBaseContext(),st,Toast.LENGTH_LONG).show();
        }
    }
}
