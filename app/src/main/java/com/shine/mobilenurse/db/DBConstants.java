package com.shine.mobilenurse.db;

/**
 * <描述当前文件类名功能>
 *
 * @author : yangxuzhong
 * @date : 2016-03-24 9:32
 */
public class DBConstants {

    public static final String DB_NAME = "mobilenurse.db";//数据库名称

    public static final int DB_VERSION = 1;//版本号

    public static final String SQL_OPTION = "CREATE TABLE IF NOT EXISTS Option(" +
            "ID INTEGER  PRIMARY KEY ," +//对应ID 主键
            "NAME TEXT," +//提示名字
            "SORT INTEGER," +//对应的排序编号
            "TYPE INTEGER);";//当前显示的类型 0.顶部 1.中间 2.底部部分 3.不显示


    /*
    public static final String SQL_TUser = "CREATE TABLE IF NOT EXISTS LoginUser (" +
            "USERID VARCHAR2(10) NOT NULL PRIMARY KEY," +
            "PHONE VARCHAR2(10),MBRID VARCHAR2(10)," +
            "MBRNAME VARCHAR2(30)," +
            "MBRPHOTOID VARCHAR2(10)," +
            "LOGINTIME VARCHAR2(20)," +
            "ONLINE VARCHAR2(10)," +
            "SVRID VARCHAR2(10)," +
            "DYNAMICCODE VARCHAR2(20)," +
            "STATE VARCHAR2(20)," +
            "AUTO VARCHAR2(20))";

    public static final String SQL_TMember = "CREATE TABLE IF NOT EXISTS Member (" +
            "MBRID VARCHAR2(10) NOT NULL PRIMARY KEY," +
            "MBRNAME VARCHAR2(30)," +
            "SEX VARCHAR2(10)," +
            "BIRTHDAY VARCHAR2(20)," +
            "HCARDNO VARCHAR2(20)," +
            "TELNO VARCHAR2(20)," +
            "USERID VARCHAR2(10))";

    public static final String SQL_MainPage = "CREATE TABLE IF NOT EXISTS MainPage (" +
            "ITEMID VARCHAR2(10) NOT NULL PRIMARY KEY," +
            "INSTID VARCHAR2(10)," +
            "TITLE VARCHAR2(100)," +
            "IMAGEFID VARCHAR2(10)," +
            "TEXTFID VARCHAR2(10)," +
            "ITEMTYPE VARCHAR2(10)," +
            "ITEMVERSION VARCHAR2(10)," +
            "IMGAGEVERSION VARCHAR2(10)," +
            "TEXTVERSION VARCHAR2(10)," +
            "NO NUMBER(10));";

    public static final String SQL_TNews = "CREATE TABLE IF NOT EXISTS News (" +
            "NEWSID VARCHAR2(10) NOT NULL PRIMARY KEY," +
            "TITLE VARCHAR2(500)," +
            "PUBTIME VARCHAR2(20)," +
            "CLASS VARCHAR2(10)," +
            "IMGFID VARCHAR2(10)," +
            "FID VARCHAR2(10)," +
            "DISPTYPE VARCHAR2(10))";

    public static final String SQL_TNotice = "CREATE TABLE IF NOT EXISTS NoticeFragment (" +
            "NOTICEID VARCHAR2(10) NOT NULL PRIMARY KEY, " +
            "NOTICECLASS VARCHAR2(10), " +
            "PUBTIME VARCHAR2(20), " +
            "SIGN VARCHAR2(10), " +
            "TITLE VARCHAR2(256), " +
            "MSGTYPE VARCHAR2(10), " +
            "MSGTEXT VARCHAR2(1000)," +
            "MEMID VARCHAR2(10)," +
            "USERID VARCHAR2(10)) ";

    public static final String SQL_TSChat = "CREATE TABLE IF NOT EXISTS SingleChat (" +
            "CHATID VARCHAR2(32) NOT NULL PRIMARY KEY, " +
            "LOCAL_USER_ID VARCHAR2(10), " +
            "LOCAL_USER_NAME VARCHAR2(30), " +
            "USER_ID VARCHAR2(10), " +
            "USER_NAME VARCHAR2(30), " +
            "IS_READ VARCHAR2(10), " +
            "CREATE_TIME VARCHAR2(20), " +
            "DIRECTION VARCHAR2(10), " +
            "IS_SEND VARCHAR2(10), " +
            "TYPE VARCHAR2(10), " +
            "TEXT VARCHAR2(1000), " +
            "IMAGE_PATH VARCHAR2(100), " +
            "MAP_PATH VARCHAR2(100), " +
            "AUDIO_PATH VARCHAR2(100))";

    public static final String SQL_TGChat = "CREATE TABLE IF NOT EXISTS GroupChat (" +
            "CHATID VARCHAR2(32) NOT NULL PRIMARY KEY, " +
            "GROUP_ID VARCHAR2(10), " +
            "LOCAL_USER_ID VARCHAR2(10), " +
            "LOCAL_USER_NAME VARCHAR2(30), " +
            "USER_ID VARCHAR2(10), " +
            "USER_NAME VARCHAR2(30), " +
            "IS_READ VARCHAR2(10), " +
            "CREATE_TIME VARCHAR2(20), " +
            "DIRECTION VARCHAR2(10), " +
            "IS_SEND VARCHAR2(10), " +
            "TYPE VARCHAR2(10), " +
            "TEXT VARCHAR2(1000), " +
            "IMAGE_PATH VARCHAR2(100), " +
            "MAP_PATH VARCHAR2(100), " +
            "AUDIO_PATH VARCHAR2(100))";

    public static final String SQL_TSvr = "CREATE TABLE IF NOT EXISTS SvrDepart (" +
            "SVRID VARCHAR2(10) NOT NULL PRIMARY KEY," +
            "SVRNAME VARCHAR2(30)," +
            "IMAGEFID VARCHAR2(10)," +
            "ONCOUNT VARCHAR2(10)," +
            "SVRDESC VARCHAR2(500))";

    public static final String SQL_TRecord = "CREATE TABLE IF NOT EXISTS Record (" +
            "RECID VARCHAR2(10) NOT NULL PRIMARY KEY," +
            "RECVTIME VARCHAR2(20)," +
            "RECFILE VARCHAR2(10)," +
            "RECCOUNT VARCHAR2(10)," +
            "RECDESC VARCHAR2(1000)," +
            "USERID VARCHAR2(10)," +
            "MEMID VARCHAR2(10))";

    public static final String SQL_TBSugar = "CREATE TABLE IF NOT EXISTS TBSugar (" +
            "SID INTEGER PRIMARY KEY ," +
            "ISUPLOAD TEXT," +
            "CHECKTYPE TEXT," +
            "MEMBERID VARCHAR2(10)," +
            "USERID VARCHAR2(10)," +
            "BLOODSUGAR VARCHAR2(10)," +
            "SAVETIME VARCHAR2(20))";

    public static final String SQL_TBPressure = "CREATE TABLE IF NOT EXISTS TBPressure (" +
            "SID INTEGER PRIMARY KEY ," +
            "USERID VARCHAR2(10)," +
            "ISUPLOAD TEXT," +
            "MEMBERID VARCHAR2(10)," +
            "DIASTOLICPRESSURE VARCHAR2(10)," +
            "SYSTOLICPRESSURE VARCHAR2(10)," +
            "PULSE VARCHAR2(10)," +
            "SAVETIME VARCHAR2(20))";
    */
}
