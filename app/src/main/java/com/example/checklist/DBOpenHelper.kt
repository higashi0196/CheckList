package com.example.checklist

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBOpenHelper (context: Context, private val dbname: String):
    SQLiteOpenHelper(context, DATABSE_NAME,null, DATABASE_VERSION){

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABSE_NAME = "CheckList.db"
        const val _ID = "_id"
        const val TITLE = "title"
        const val STATUS = "status"

//        const val SQL_CREATE_ENTRIES =
//            "CREATE TABLE " + TABLE_NAME + " (" +
//             _ID + "INTEGER PRIMARY KEY," +
//             TITLE + " TEXT," +
//             STATUS + " BOOLEAN)"

        // const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME
    }

    override fun onCreate(db: SQLiteDatabase) {
        val SQL_CREATE_ENTRIES = """
            CREATE TABLE $dbname (
                $_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $TITLE TEXT,
                $STATUS TEXT
            )
        """.trimIndent()
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $dbname"
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }

}