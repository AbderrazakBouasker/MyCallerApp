package jose.mokeni.mycallerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ProfileDAO {
    Context con ;
    SQLiteDatabase mybase;

    public ProfileDAO(Context con) {
        this.con = con;
    }

    public void open(){
        MyProfileHelper helper = new MyProfileHelper(con,"mybase.db",null,1);
        mybase= helper.getWritableDatabase();
    }

    public void close(){
        mybase.close();
    }

    public void insert(String first_name, String last_name, String number){
        try{
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyProfileHelper.col_firstname,first_name);
        contentValues.put(MyProfileHelper.col_lastname,last_name);
        contentValues.put(MyProfileHelper.col_number,number);
        mybase.insert(MyProfileHelper.table_profile,null,contentValues);
        }catch (Exception e){
            System.out.println(e.toString());
        }finally {
            try{
                close();
            }catch (Exception e){
                System.out.println(e.toString());
            }
        }

    }

    ArrayList<Profil> select_all(){
        ArrayList<Profil> data = new ArrayList<Profil>();
        Cursor cr = mybase.query(MyProfileHelper.table_profile,new String[]{MyProfileHelper.col_id,MyProfileHelper.col_firstname,MyProfileHelper.col_lastname,MyProfileHelper.col_number},null,null,null,null,null);
        cr.moveToFirst();
        while(!cr.isAfterLast()){
            int id = cr.getInt(0);
            String firstname = cr.getString(1);
            String lastname = cr.getString(2);
            String number = cr.getString(3);
            data.add(new Profil(id,firstname,lastname,number));
            cr.moveToNext();
        }
        cr.close();
        close();
        return data;
    }

    int modify(int id,String first_name,String last_name, String number){
        int ret = -1;
        try{
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyProfileHelper.col_id,id);
        contentValues.put(MyProfileHelper.col_firstname,first_name);
        contentValues.put(MyProfileHelper.col_lastname,last_name);
        contentValues.put(MyProfileHelper.col_number,number);
        ret=mybase.update(MyProfileHelper.table_profile,contentValues,MyProfileHelper.col_id + "="+id,null);
        }catch (Exception e){
            System.out.println(e.toString());
        }finally {
            try{
                close();
            }catch (Exception e){
                System.out.println(e.toString());
            }
        }
        return ret;
    }

    int delete(int id){
        int ret = -1;
        try{
        ret=mybase.delete(MyProfileHelper.table_profile,MyProfileHelper.col_id+"="+id,null);
        }catch (Exception e){
            System.out.println(e.toString());
        }finally {
            try{
                close();
            }catch (Exception e){
                System.out.println(e.toString());
            }
        }
        return ret;
    }


}
