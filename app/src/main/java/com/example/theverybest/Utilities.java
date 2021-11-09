package com.example.theverybest;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.theverybest.vo.AvatarVO;
import com.example.theverybest.vo.PlayerVO;

import java.util.ArrayList;

public class Utilities {

    public static ArrayList<AvatarVO> avatarList = null;
    public static ArrayList<PlayerVO> playersList = null;

    public static AvatarVO selectedAvatar = null;
    public static int selectedAvatarID = 0;

    //SQLITE TABLA JUGADORES
    public static final String PLAYERS_BD = "players_bd";   //NOMBRE TABLA
    public static final String PLAYERS_ID = "id";           //CLAVE PRIMARIA
    public static final String PLAYERS_NAME = "name";
    public static final String PLAYERS_AVATAR = "avatar";
    public static final String PLAYERS_BESTSCORE = "bestscore";
    //QUERYS
    public static final String CREATE_PLAYERS_TABLE = "CREATE TABLE "+ PLAYERS_BD + " ("+ PLAYERS_ID + " INTEGER PRIMARY KEY, "+ PLAYERS_NAME+ " TEXT," + PLAYERS_AVATAR +" INTEGER,"+ PLAYERS_BESTSCORE + " INTEGER)";


    //SQLITE TABLA RANKING
    public static final String RANKING_BD = "ranking_bd";   //NOMBRE TABLA
    public static final String RANKING_id = "ranking_id";   //CLAVE PRIMARIA
    public static final String RANKING_playerid = "playerid";   //CLAVE FORANEA
    public static final String RANKING_playername = "playername";
    public static final String RANKING_playeravatar = "playeravatar";
    public static final String RANKING_score = "score";
    public static final String RANKING_right = "right";
    public static final String RANKING_wrong = "wrong";
    public static final String RANKING_time = "time";

    public static final String CREATE_RANKING_TABLE = "CREATE TABLE " + RANKING_BD + " ("+ RANKING_id + " INTEGER PRIMARY KEY,"+
                                                                                            RANKING_playerid + " INTEGER,"+
                                                                                            RANKING_playername + " TEXT,"+
                                                                                            RANKING_playeravatar + " INTEGER,"+
                                                                                            RANKING_score + " INTEGER,"+
                                                                                            RANKING_right + " INTEGER,"+
                                                                                            RANKING_wrong + " INTEGER,"+
                                                                                            RANKING_time + " INTEGER)";


    public static void getAvatarList(){
        avatarList = new ArrayList<AvatarVO>();

        avatarList.add(new AvatarVO(1, R.drawable.avatar1, "Avatar1"));
        avatarList.add(new AvatarVO(2, R.drawable.avatar2, "Avatar2"));
        avatarList.add(new AvatarVO(3, R.drawable.avatar3, "Avatar3"));
        avatarList.add(new AvatarVO(4, R.drawable.avatar4, "Avatar4"));
        avatarList.add(new AvatarVO(5, R.drawable.avatar5, "Avatar5"));
        avatarList.add(new AvatarVO(6, R.drawable.avatar6, "Avatar6"));
        avatarList.add(new AvatarVO(7, R.drawable.avatar7, "Avatar7"));
        avatarList.add(new AvatarVO(8, R.drawable.avatar8, "Avatar8"));
        avatarList.add(new AvatarVO(9, R.drawable.avatar9, "Avatar9"));
        avatarList.add(new AvatarVO(10, R.drawable.avatar10, "Avatar10"));
        avatarList.add(new AvatarVO(11, R.drawable.avatar11, "Avatar11"));
        avatarList.add(new AvatarVO(12, R.drawable.avatar12, "Avatar12"));
        avatarList.add(new AvatarVO(13, R.drawable.avatar13, "Avatar13"));
        avatarList.add(new AvatarVO(14, R.drawable.avatar14, "Avatar14"));
        avatarList.add(new AvatarVO(15, R.drawable.avatar15, "Avatar15"));
        avatarList.add(new AvatarVO(16, R.drawable.avatar16, "Avatar16"));
        avatarList.add(new AvatarVO(17, R.drawable.avatar17, "Avatar17"));
        avatarList.add(new AvatarVO(18, R.drawable.avatar18, "Avatar18"));
        avatarList.add(new AvatarVO(19, R.drawable.avatar19, "Avatar19"));
        avatarList.add(new AvatarVO(20, R.drawable.avatar20, "Avatar20"));
        avatarList.add(new AvatarVO(21, R.drawable.avatar21, "Avatar21"));
        avatarList.add(new AvatarVO(22, R.drawable.avatar22, "Avatar22"));
        avatarList.add(new AvatarVO(23, R.drawable.avatar23, "Avatar23"));
        avatarList.add(new AvatarVO(24, R.drawable.avatar24, "Avatar24"));

        selectedAvatar = avatarList.get(0);

    }

    public static void getPlayersList(Activity activity){
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(activity, PLAYERS_BD, null, 1);
        SQLiteDatabase db = conn.getReadableDatabase(); //Leemos la BD

        PlayerVO player = null;
        playersList = new ArrayList<PlayerVO>();

        //Selecciona de la base de datos
        Cursor cursor = db.rawQuery("SELECT * FROM "+PLAYERS_BD, null);

        while(cursor.moveToNext()){
            player = new PlayerVO();
            player.setId(cursor.getInt(0));
            player.setName(cursor.getString(1));
            player.setAvatar(cursor.getInt(2));
            player.setBestScore(cursor.getInt(3));

            playersList.add(player);

        }

        db.close();

    }
}
