package inseenanpbru.tanongsak.itpbru;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;

import com.squareup.okhttp.Request;

public class CalendarActivity extends AppCompatActivity {

    //Explicit
    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        //Bind Widget
        calendarView = (CalendarView) findViewById(R.id.calendarView);

        //Active when Click calendar
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                confirmDialog(day, month, year);

            }
        });

    }   //Main Method

    private void confirmDialog(int day, int month, int year) {

        final String strDate = Integer.toString(day) +
                "/" + Integer.toBinaryString(month + 1) +
                "/" + Integer.toBinaryString(year);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setIcon(R.drawable.rat48);
        builder.setTitle(strDate);
        builder.setMessage("คุณต้องการ บันทึก รายรับ หรือ รายจ่าย ?");
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("รายรับ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(CalendarActivity.this, UploadAccount.class);
                intent.putExtra("Login", getIntent().getBooleanArrayExtra("Login"));
                intent.putExtra("Inout", 0);
                intent.putExtra("Date", strDate);
                startActivity(intent);

            }
        });
        builder.setNeutralButton("รายจ่าย", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(CalendarActivity.this, UploadAccount.class);
                intent.putExtra("Login", getIntent().getStringArrayExtra("Login"));
                intent.putExtra("InOut", 1);
                intent.putExtra("Date", strDate);
                startActivity(intent);


            }
        });
        builder.show();

    }   //confirmDialog

}   //Main Class
