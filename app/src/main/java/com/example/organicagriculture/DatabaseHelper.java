package com.example.organicagriculture;

import static java.sql.Types.NULL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.sql.SQLException;
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "organicAgri";
    public static final String USER_TABLE= "userinfo";
    public static final String USER_ID="UID";
    public static final String USER_MOB="MOBILE";
    public static final String USER_PASS="PASS";
    public static final String USER_NAME="U_NAME";
    public static final String USER_LOCATION="LOCATION";
    public static final String LURE_TABLE= "lureinfo";
    public static final String LURE_ID="PID";
    public static final String LURE_NAME="PNAME";
    public static final String LURE_IMG="PIMG";
    public static final String LURE_PRICE="LPRICE";
    public static final String LURE_SUITABLETRAP="PTRAP";
    public static final String LURE_CROP="PCROP";
    public static final String LURE_REPLACEMENT="LREPLACEMENT";
    public static final String LURE_ACRE="PERACRE";
    public static final String TRAP_TABLE= "trapinfo";
    public static final String TRAP_ID="TID";
    public static final String TRAP_NAME="TNAME";
    public static final String TRAP_IMG="TIMG";
    public static final String TRAP_PRICE="TPRICE";
    public static final String TRAP_SUITABLELURE="TLURE";
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null,1);
    }
    public static DatabaseHelper getInstace(Context context) {
        DatabaseHelper obj = new DatabaseHelper(context);
        return obj;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //creating table for storing user info.
        sqLiteDatabase.execSQL("CREATE TABLE " + USER_TABLE +
        "("+ USER_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+ USER_MOB+" TEXT UNIQUE,"
                +USER_PASS +" TEXT,"+USER_NAME+" TEXT ,"+USER_LOCATION+ " TEXT"+")");

        //creating table for storing lure info
        sqLiteDatabase.execSQL("CREATE TABLE " +LURE_TABLE +"("+
                LURE_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+ LURE_NAME+" TEXT UNIQUE,"+LURE_IMG+" TEXT,"+LURE_PRICE+" TEXT,"
                +LURE_SUITABLETRAP +" TEXT, "+LURE_CROP+" TEXT, "+LURE_REPLACEMENT+ " TEXT, "+LURE_ACRE+" TEXT"+
                ")");

        //creating table for stroring trap info
        sqLiteDatabase.execSQL("CREATE TABLE " +TRAP_TABLE +"("+
                TRAP_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+ TRAP_NAME+" TEXT UNIQUE,"+TRAP_IMG+" TEXT,"+TRAP_PRICE+" TEXT,"
                +TRAP_SUITABLELURE+" TEXT "+
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);

        onCreate(sqLiteDatabase);
    }

    public boolean registerUser(String mobile, String password){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(USER_MOB,mobile);
        contentValues.put(USER_PASS,password);
        contentValues.put(USER_NAME,NULL);
        contentValues.put(USER_LOCATION,NULL);

        long result = db.insert(USER_TABLE,null,contentValues);

//        db.close();

        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor findTrap(String product){
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor result = db.query(TRAP_TABLE, cols,TRAP_NAME+"="+product,null,null,null,null);
//        if(result!=null){
//            return result;
//        }
//        return result;

        SQLiteDatabase db = this.getReadableDatabase();
        String cols[]={TRAP_IMG,TRAP_NAME,TRAP_PRICE,TRAP_SUITABLELURE};
        Cursor result = db.query(TRAP_TABLE,cols,TRAP_NAME+"="+product,null,null,null,null);
        if(result!=null) {
            return result;
        }
        else{
            return null;
        }
    }
}
