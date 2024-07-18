package ps.safiya.ptuk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {
static String DB_name ="students";

static int DB_version =1 ;
    public MyDatabase(@Nullable Context context) {
        super(context, DB_name, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE  stud(id INTEGER PRIMARY KEY AUTOINCREMENT ,name TEXT ,mark INTEGER )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addStudent(student st) {

        SQLiteDatabase writer=getWritableDatabase();
        String sql="INSERT INTO stud (name, mark) VALUES (?, ?)" ;

        writer.execSQL(sql,new String[]{st.name,st.mark+""});


    }

    @SuppressLint("Range")
    public ArrayList<student> getAll(){

        ArrayList<student> students=new ArrayList<>();
        SQLiteDatabase reader=getReadableDatabase();
        String sql="SELECT * FROM stud";

        Cursor cursor= reader.rawQuery(sql,null);
        cursor.moveToFirst();

       while(!cursor.isAfterLast())
       {
           students.add(new student(
                   cursor.getInt(cursor.getColumnIndex("mark")),
                   cursor.getString(cursor.getColumnIndex("name"))
           ));

           cursor.moveToNext();
       }
        cursor.close();
        return  students;
    }
    @SuppressLint("Range")
    public  ArrayList<student> getSorted() {
        ArrayList<student> students=new ArrayList<>();
        SQLiteDatabase reader=getReadableDatabase();
        String sql="SELECT * FROM stud ORDER BY name";
        Cursor cursor= reader.rawQuery(sql,null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast())
        {
            students.add(new student(

                    cursor.getInt(cursor.getColumnIndex("mark")),
                    cursor.getString(cursor.getColumnIndex("name"))
            ));

            cursor.moveToNext();


        }
        cursor.close();
        return  students;

    }
    @SuppressLint("Range")
    public student getLast() {

        ArrayList<student> students=new ArrayList<>();
        SQLiteDatabase reader=getReadableDatabase();
        String sql="SELECT * FROM stud ORDER BY mark ASC";

        Cursor cursor= reader.rawQuery(sql,null);
        cursor.moveToFirst();
        student st=new student(cursor.getInt(cursor.getColumnIndex("mark")),cursor.getString(cursor.getColumnIndex("name")));
        cursor.close();
        return  st;


    }
    @SuppressLint("Range")
    public student getFirst() {

        ArrayList<student> students=new ArrayList<>();
        SQLiteDatabase reader=getReadableDatabase();
        String sql="SELECT * FROM stud ORDER BY mark DESC";
        Cursor cursor= reader.rawQuery(sql,null);
        cursor.moveToFirst();
        student st=new student(cursor.getInt(cursor.getColumnIndex("mark")),cursor.getString(cursor.getColumnIndex("name")));
        cursor.close();
        return  st;
    }
    @SuppressLint("Range")
    public double getAVG() {
        double avg=0,sum=0;
        int count=0;

        SQLiteDatabase reader=getReadableDatabase();
        String sql="SELECT * FROM stud";


        Cursor cursor= reader.rawQuery(sql,null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast())
        {

                   sum+= cursor.getInt(cursor.getColumnIndex("mark"));
                  count++;

            cursor.moveToNext();


        }
        cursor.close();
        avg=sum/count;
        return avg;
    }
}
