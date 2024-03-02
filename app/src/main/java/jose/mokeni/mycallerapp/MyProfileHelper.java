package jose.mokeni.mycallerapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyProfileHelper extends SQLiteOpenHelper {

    public static final String table_profile = "profile";
    public static final String col_id = "id";
    public static final String col_firstname = "firstname";
    public static final String col_lastname = "lastname";
    public static final String col_number= "number";

    String create_req="create table "+table_profile+" ("+col_id+" Integer Primary key autoincrement,"+
            col_firstname+" text not null, "
    +col_lastname+" text not null, "
            +col_number+ " )";

    public MyProfileHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_req);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" drop table " + table_profile);
        onCreate(db);
    }
}
