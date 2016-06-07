package inseenanpbru.tanongsak.itpbru;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by lab324-20 on 6/7/2016 AD.
 */
public class MyManage {

    //Explict
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public MyManage(Context context) {

        myOpenHelper = new MyOpenHelper(context);
        sqLiteDatabase = myOpenHelper.getWritableDatabase();

    }   //Construtor

} //Main Class
