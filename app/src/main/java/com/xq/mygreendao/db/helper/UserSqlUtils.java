package com.xq.mygreendao.db.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * User数据库操作工具类
 */
public class UserSqlUtils {

    private Context context;

    public UserSqlUtils(Context context) {
        this.context = context;
    }

    /**
     * 查：按姓名
     */
    public Cursor querySQL(String name) {
        SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
        //String sql = "select * from user where name='许强'";
        String strSql = "select * from " + UserTableUtil.TABLE_NAME +
                " where " + UserTableUtil.NAME + "='" + name + "'";
        Log.e("UserSqlUtils", "查询数据（按姓名）====" + strSql);
        return db.rawQuery(strSql, null);
    }

    /**
     * 查询全部
     */
    public Cursor querySQL() {
        SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
        //String sql = "select * from user";
        String strSql = "select * from " + UserTableUtil.TABLE_NAME;
        Log.e("UserSqlUtils", "查询全部====" + strSql);
        return db.rawQuery(strSql, null);
    }

    /**
     * 插入数据
     */
    public void insertSQL(String name, String age, String sex, String salary) {
        SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
        //String stu_sql="insert into user(name,age,sex,salary) values('许强','18','男','12700')";
        String strSql = "insert into " + UserTableUtil.TABLE_NAME +
                "(" + UserTableUtil.NAME + "," + UserTableUtil.AGE + "," + UserTableUtil.SEX + "," + UserTableUtil.SALARY + ")" +
                " values('" + name + "','" + age + "','" + sex + "','" + salary + "')";
        Log.e("UserSqlUtils", "插入数据====" + strSql);
        db.execSQL(strSql);
    }

    /**
     * 修改:按name
     */
    public void updateSQLByName(String name, String age, String sex, String salary) {
        SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
        //String sql = "update user set name='许强',age='18',sex='男',salary='16000' where name='许强'";
        String strSql = "update " + UserTableUtil.TABLE_NAME +
                " set " + UserTableUtil.NAME + "='" + name + "'," + UserTableUtil.AGE + "='" + age + "'," + UserTableUtil.SEX + "='" + sex + "'," + UserTableUtil.SALARY + "='" + salary +
                "' where " + UserTableUtil.NAME + "='" + name + "'";
        Log.e("UserSqlUtils", "修改数据====" + strSql);
        db.execSQL(strSql);
    }

    /**
     * 全部修改
     */
    public void updateSQL(String name, String age, String sex, String salary) {
        SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
        //String sql = "update stu_table set snumber = 654321 where id = 1";
        String strSql = "update " + UserTableUtil.TABLE_NAME +
                " set " + UserTableUtil.NAME + "='" + name + "'," + UserTableUtil.AGE + "='" + age + "'," + UserTableUtil.SEX + "='" + sex + "'," + UserTableUtil.SALARY + "='" + salary + "'";
        Log.e("UserSqlUtils", "修改数据====" + strSql);
        db.execSQL(strSql);
    }

    /**
     * 删除:按姓名
     */
    public void deleteSQL(String name) {
        SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
        //String sql = "delete from stu_table where _id = 6";
        String strSql = "delete from " + UserTableUtil.TABLE_NAME +
                " where " + UserTableUtil.NAME + "='" + name + "'";
        Log.e("UserSqlUtils", "删除数据（按姓名）====" + strSql);
        db.execSQL(strSql);
    }

    /**
     * 删除全部
     */
    public void deleteSQL() {
        SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
        //String sql = "delete from user";
        String strSql = "delete from " + UserTableUtil.TABLE_NAME;
        Log.e("UserSqlUtils", "删除全部====" + strSql);
        db.execSQL(strSql);
    }

    /**
     * 关闭数据库
     */
    public static void closeCursor(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
    }
}

