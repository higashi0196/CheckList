package com.example.checklist

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBOpenHelper internal constructor(context: Context?): SQLiteOpenHelper(context, DATABSE_NAME,null, DATABASE_VERSION){

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }
    
    companion object {
        const val DATABASE_VERSION = 1
        const val DATABSE_NAME = "Checklist.db"
        const val TABLE_NAME = ""
        const val _ID = "_id"
        const val TITLE = "title"
        const val STATUS = "status"

        const val SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
             _ID + "INTEGER PRIMARY KEY," +
             TITLE + " TEXT," +
             STATUS + " BOOLEAN)"

        const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME
    }

}