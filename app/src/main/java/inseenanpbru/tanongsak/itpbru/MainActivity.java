package inseenanpbru.tanongsak.itpbru;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }   //Main Method

    public void clickSignUpMain(View view) { //กด shif + ctrl + enter
        startActivity(new Intent(MainActivity.this, SignUpActivity.class) );
    }   //พยายามพิมพ์ ตัวอักษรตัวแรกแล้วตามด้วยเมนูที่เลื่อนลงมาแทนการพิมพ์



}   //Main Class นี้คือคลาสหลัก
