package com.xq.mygreendao.db.util;

import android.database.sqlite.SQLiteDatabase;

import com.xq.mygreendao.MyApplication;
import com.xq.mygreendao.db.gen.DaoMaster;
import com.xq.mygreendao.db.gen.DaoSession;

/**
 * GreenDao管理类
 */

public class DbManager {

    // 是否加密
    public static final boolean ENCRYPTED = true;

    private static final String DB_NAME = "test.db";
    private static DbManager mDbManager;
    private static DaoMaster.DevOpenHelper mDevOpenHelper;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;

    private DbManager() {
        //第一步：创建数据库
        mDevOpenHelper = new DaoMaster.DevOpenHelper(MyApplication.getApplication(), DB_NAME, null);
        getDaoMaster();
        getDaoSession();
    }

    public static DbManager getInstance() {
        if (null == mDbManager) {
            synchronized (DbManager.class) {
                if (null == mDbManager) {
                    mDbManager = new DbManager();
                }
            }
        }
        return mDbManager;
    }

    /**
     * 获取可读数据库
     **/
    public static SQLiteDatabase getReadableDatabase() {
        if (null == mDevOpenHelper) {
            getInstance();
        }
        return mDevOpenHelper.getReadableDatabase();
    }

    /**
     * 第二步：获取可写数据库
     **/
    public static SQLiteDatabase getWritableDatabase() {
        if (null == mDevOpenHelper) {
            getInstance();
        }
        return mDevOpenHelper.getWritableDatabase();
    }

    /**
     * 第三步：获取DaoMaster：数据库对象
     **/
    public static DaoMaster getDaoMaster() {
        if (null == mDaoMaster) {
            synchronized (DbManager.class) {
                if (null == mDaoMaster) {
                    mDaoMaster = new DaoMaster(getWritableDatabase());
                }
            }
        }
        return mDaoMaster;
    }

    /**
     * 第四步：获取DaoSession：Dao对象管理者：执行增删改查
     **/
    public static DaoSession getDaoSession() {
        if (null == mDaoSession) {
            synchronized (DbManager.class) {
                mDaoSession = getDaoMaster().newSession();
            }
        }
        return mDaoSession;
    }

}
