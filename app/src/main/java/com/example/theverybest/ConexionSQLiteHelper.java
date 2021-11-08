package com.example.theverybest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Utilities.CREATE_PLAYERS_TABLE);
        sqLiteDatabase.execSQL(Utilities.CREATE_RANKING_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ Utilities.PLAYERS_BD);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ Utilities.RANKING_BD);
        onCreate(sqLiteDatabase);
    }
}
