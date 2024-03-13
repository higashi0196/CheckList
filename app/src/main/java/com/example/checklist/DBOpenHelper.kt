package com.example.checklist

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBOpenHelper (context: Context):
    SQLiteOpenHelper(context, DATABSE_NAME,null, DATABASE_VERSION){

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABSE_NAME = "CheckList.db"
        const val _ID = "_id"
        const val TITLE = "title"
        const val STATUS = "status"
    }

    override fun onCreate(db: SQLiteDatabase?) {

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//        val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $dbname"
//        db.execSQL(SQL_DELETE_ENTRIES)
//        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }

    fun createTable(dbname: String) {
        val createTableQuery = """
            CREATE TABLE IF NOT EXISTS $dbname (
                $_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $TITLE TEXT,
                $STATUS TEXT
            )
        """.trimIndent()

        val db = writableDatabase
        db.execSQL(createTableQuery)
        db.close()
    }

}