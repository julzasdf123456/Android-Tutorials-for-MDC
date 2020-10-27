package com.lopez.julz.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBHelper {

    public SQLiteDatabase db;
    public Context context;

    public DBHelper(Context context) {
        this.context = context;
        db = context.openOrCreateDatabase("dummyDB", context.MODE_PRIVATE, null);
    }

    public boolean createCoursesTable() {
        try {
            String slq = "CREATE TABLE IF NOT EXISTS Courses (Id INTEGER PRIMARY KEY AUTOINCREMENT, CourseName VARCHAR(500), CourseDescription VARCHAR(3000))";
            db.execSQL(slq);
            return true;
        } catch (Exception e) {
            Log.e("ERR_CRT_DB", e.getMessage());
            return false;
        }
    }

    public boolean insertNewCourse(String courseName, String description) {
        try {
            ContentValues content = new ContentValues();
            content.put("CourseName", courseName);
            content.put("CourseDescription", description);
            db.insert("Courses", null, content);
            return true;
        } catch (Exception e){
            Log.e("ERR_INSRT_CRS", e.getMessage());
            return false;
        }
    }

    public Cursor getCourses() {
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM Courses", null);
            return cursor;
        } catch (Exception e) {
            Log.e("ERR_GET_CRS", e.getMessage());
            return null;
        }
    }
}
