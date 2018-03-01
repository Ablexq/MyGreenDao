package com.xq.mygreendao.db.helper;

/**
 * User建表工具类
 */

public class UserTableUtil {
    public static String TABLE_NAME = "user";

    public static String _ID = "_id";
    public static String NAME = "name";
    public static String AGE = "age";
    public static String SEX = "sex";
    public static String SALARY = "salary";

    private static String toTable() {
        //CREATE TABLE IF NOT EXISTS user(
        // _id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(11),
        // age VARCHAR(20),sex VARCHAR(20),salary VARCHAR(11))
        String buffer;
        buffer = ("CREATE TABLE IF NOT EXISTS " + TABLE_NAME) +
                "(" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NAME + " VARCHAR(11)," +
                AGE + " VARCHAR(20)," +
                SEX + " VARCHAR(20)," +
                SALARY + " VARCHAR(11)" +
                ")";
        return buffer;
    }

    /**
     * 建表语句
     **/
    public static String CREATE_TABLE = toTable();
}
