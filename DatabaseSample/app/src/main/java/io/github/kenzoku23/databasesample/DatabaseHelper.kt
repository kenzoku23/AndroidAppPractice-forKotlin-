package io.github.kenzoku23.databasesample

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//sqliteを使用する場合にはSQLiteOpenHelperを継承した、ヘルパーを用意する。
//継承するSQLiteOpenHelperのコンストラクタに引数を渡す。
//contextは自身で用意できないため、呼び出し先からもらう。
//onCreateは初期設定の1回目に呼び出される。
//第4引数はデータベースのバージョンを表しており、管理しているバージョンより大きい数が渡された場合、onUpgradeが呼び出される。
class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "cocktailmemo.db"
        private const val DATABASE_VERSION = 1
    }
    override fun onCreate(db: SQLiteDatabase) {
        val sb = StringBuilder()
        sb.append("CREATE TABLE cocktailmemos ( ")
        sb.append("_id INTEGER PRIMARY KEY,")   //_idと命名すると、Androidは主キーと自動で判断する。
        sb.append("name TEXT,")
        sb.append("note TEXT")
        sb.append(");")
        val sql = sb.toString()

        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVer: Int, newVer: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}