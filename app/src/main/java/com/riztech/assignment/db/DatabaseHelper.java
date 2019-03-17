package com.riztech.assignment.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.riztech.assignment.model.Group;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "assignment.sqlite";
    private static final int VERSION = 1;

    private static final String ID = "id";

    private static final String NAME = "name";

    private static final String MEMBERS = "members";

    private static final String DESCRIPTION = "description";

    private static final String LAT = "lat";

    private static final String LON = "LON";

    private static final String GROUP_TABLE = "group_table";

    public DatabaseHelper(Context context ) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "create table " +GROUP_TABLE + " ( "+ID + " text, "+ NAME + " text, "+ MEMBERS + " text, "+ DESCRIPTION
                + " text, "+LAT + " text, "+ LON + " text ) ";

        db.execSQL(query);


    }

    public void insertGroup(Group group){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ID, group.getId());
        values.put(NAME, group.getName());
        values.put(MEMBERS, group.getMembers());
        values.put(DESCRIPTION, group.getDescription());
        values.put(LAT, group.getLat());
        values.put(LON, group.getLon());

        db.insert(GROUP_TABLE,null, values);
        db.close();
    }

    public void insertGroups(List<Group> groups){
        SQLiteDatabase db = getWritableDatabase();

        for (Group group: groups) {
            ContentValues values = new ContentValues();
            values.put(ID, group.getId());
            values.put(NAME, group.getName());
            values.put(MEMBERS, group.getMembers());
            values.put(DESCRIPTION, group.getDescription());
            values.put(LAT, group.getLat());
            values.put(LON, group.getLon());

            db.insert(GROUP_TABLE,null, values);
        }

        db.close();

    }

    public List<Group> getGroups(){
        List<Group> groups = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String query = "select * from "+GROUP_TABLE;

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                String id = cursor.getString(cursor.getColumnIndex(ID));
                String name = cursor.getString(cursor.getColumnIndex(NAME));
                String members = cursor.getString(cursor.getColumnIndex(MEMBERS));
                String description = cursor.getString(cursor.getColumnIndex(DESCRIPTION));
                String lat = cursor.getString(cursor.getColumnIndex(LAT));
                String lon = cursor.getString(cursor.getColumnIndex(LON));

                Group group = new Group(id, name, members, description, lat, lon);

                groups.add(group);


            }while (cursor.moveToNext());
        }



        return groups;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
