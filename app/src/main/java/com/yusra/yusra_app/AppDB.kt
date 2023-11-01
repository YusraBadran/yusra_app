package com.yusra.yusra_app

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import kotlin.comparisons.compareValues as ContentValues

class AppDB {

    val DBNAME = "chat"
    val DBVERSION = 1
    var ctx:Context? = null

    constructor(context: Context?){ //يستقبل البيانات من اي اكتفتي
        ctx = context
    }

    companion object {

        val TABLE_USERS = "tusers" // table name

        val K_U_id = "_id"
        val K_U_FNAEM = "kufname"
        val K_U_LNAEM = "kulname"
        val K_U_USERNAEM = "kuuname"
        val K_U_EMAIL = "kuemail"
        val K_U_PASSWORD = "kupassword"


        val CREATE_TABLE_USERS = "create table $TABLE_USERS(" +
                " $K_U_id integer primary key  autoincrement , " +
                " $K_U_FNAEM text ," +
                " $K_U_LNAEM text," +
                " $K_U_USERNAEM text," +
                " $K_U_EMAIL text," +
                " $K_U_PASSWORD text" +
                ")"
    }

    //-----------------------OPEN HELPER---------------------------
    class DBHELPER(
        context: Context?,
        name: String?,
        factory: SQLiteDatabase.CursorFactory?,
        version: Int
    ) : SQLiteOpenHelper(context, name, factory, version){
        override fun onCreate(p0: SQLiteDatabase?) {
            p0!!.execSQL(CREATE_TABLE_USERS)
        }

        override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        }

    }

    var db : SQLiteDatabase? = null
    var dbr : SQLiteDatabase? = null

    //-------------open database (insert , update , delete)-----
    fun open () {
        val dbh: DBHELPER = DBHELPER(ctx,DBNAME,null,DBVERSION)
        db = dbh.writableDatabase
        dbr = dbh.readableDatabase
    }

    //------------------insert-----------------------------
    fun insertUsers (fname: String , lname:String , username:String , email : String , password : String ){
        val cv = ContentValues()
        cv.put(K_U_FNAEM,fname)
        cv.put(K_U_LNAEM,lname)
        cv.put(K_U_USERNAEM,username)
        cv.put(K_U_EMAIL,email)
        cv.put(K_U_PASSWORD,password)
        db!!.insert(TABLE_USERS,null,cv)
        Toast.makeText(ctx,"The Account Has Been Created",Toast.LENGTH_SHORT).show()
    }

//    fun readUser(username: String ,password: String) {
//        val cv = ContentValues()
//        val selection = "$K_U_USERNAEM = ? AND $K_U_PASSWORD = ?"
//        val selectionArgs = arrayOf(username, password)
//        val cursor = db!!.query(TABLE_USERS, null, selection, selectionArgs, null, null, null)
//
//        val userExists = cursor.count > 0
//        cursor.close()
//        return userExists
//
//    }


    //------------------update-----------------------------
    fun update (Uid:Int,fname: String , lname:String , username:String , email : String , password : String){
        val cv = ContentValues()
        cv.put(K_U_FNAEM,fname)
        cv.put(K_U_LNAEM,lname)
        cv.put(K_U_USERNAEM,username)
        cv.put(K_U_EMAIL,email)
        cv.put(K_U_PASSWORD,password)

        val l = db!!.update(TABLE_USERS,cv,"$K_U_id?", arrayOf(Uid.toString()))
        if(l>0)Toast.makeText(ctx,"User Has Upadted",Toast.LENGTH_SHORT).show()
    }

    //------------------delete-----------------------------

    fun deleteUserById(Uid: Int){
        val l = db!!.delete(TABLE_USERS,"$K_U_id?", arrayOf(Uid.toString()))
        if(l>0)Toast.makeText(ctx,"User Has Deleted",Toast.LENGTH_SHORT).show()
    }

    //------------------select-----------------------------
    fun Select():Cursor{
        return db!!.query(TABLE_USERS,null,null,null,null,null,null,null)
    }

}
