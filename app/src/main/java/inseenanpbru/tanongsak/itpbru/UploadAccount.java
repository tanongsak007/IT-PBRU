package inseenanpbru.tanongsak.itpbru;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class UploadAccount extends AppCompatActivity {

    //Explicit
    private TextView inOutTextView, nameTextView;
    private Spinner spinner;
    private EditText editText;
    private String[] loginStrings;
    private int inOutAnInt;
    private String[] inOutStrings = new String[]{"บัญชีบันทึก รายรับ", "บัญชีบันทึก รายจ่าย"};
    private String[] inComeStrings = new String[]{"เงินเดือน", "ล่วงเวลา", "สอนพิเศษ"};
    private String[] outComeStrings = new String[]{"อาหาร", "เดินทาง", "Entertrain", "การศึกษา"};
    private ArrayAdapter<String> stringArrayAdapter;
    private String categoryString, amountString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_account);

        //Bind widget
        inOutTextView = (TextView) findViewById(R.id.textView6);
        nameTextView = (TextView) findViewById(R.id.textView7);
        spinner = (Spinner) findViewById(R.id.spinner);
        editText = (EditText) findViewById(R.id.editText7);

        //Receive Value From Intent
        loginStrings = getIntent().getStringArrayExtra("Login");
        inOutAnInt = getIntent().getIntExtra("InOut", 0);

        //Show View
        inOutTextView.setText(inOutStrings[inOutAnInt]);
        nameTextView.setText(loginStrings[1] + " " + loginStrings[2]);

        //Create Spinner
        createSpinner();

    }   //Main Method

    private void createSpinner() {

        switch (inOutAnInt) {

            case 0:
                stringArrayAdapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, inComeStrings);
                break;
            case 1:
                stringArrayAdapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, outComeStrings);
                break;

        }   //switch

        spinner.setAdapter(stringArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoryString = findCategory(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                categoryString = findCategory(0);

            }
        });

    }   //creat spinner

    private String findCategory(int position) {

        String strResult = null;

        switch (inOutAnInt) {
            case 0:
                strResult = inComeStrings[position];
                break;
            case 1:
                strResult = outComeStrings[position];
                break;
        }

        return null;
    }

    public void clickUpload(View view) {
        amountString = editText.getText().toString().trim();

        if (amountString.equals("")) {

            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "มีช่องว่าง", "ให้กรอกจำนวนเงินด้วย ค่ะ");
        } else {

            uploadValueToServer();
        }

    }   //CilckUpload

    private void uploadValueToServer() {

        Log.d("8June", "ID user ==>" + loginStrings[0]);
        Log.d("8June", "Date ==>" + getIntent().getStringExtra("Date"));
        Log.d("8June", "In_Out ==>" + inOutAnInt);
        Log.d("8June", "Category ==>" + categoryString);
        Log.d("8June", "amount ==>" + amountString);

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd", "true")
                .add("id_user", loginStrings[0])
                .add("Date", getIntent().getStringExtra("Date"))
                .add("In_Out", Integer.toString(inOutAnInt))
                .add("Category", categoryString)
                .add("amount", amountString)
                .build();
        Request.Builder builder = new Request.Builder();
        Request request = builder.url("http://swiftcodingthai.com/pbru2/add_account_master.php")
                .post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.equals(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                finish();

            }
        });


    }   //uploadvalue

    public void clickCancel(View view) {
        finish();
    }

}   //Main Class
