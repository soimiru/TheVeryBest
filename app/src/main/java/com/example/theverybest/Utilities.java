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

    //SQLITE
    public static final String PLAYERS_BD = "players_bd";
    public static final String PLAYERS_ID = "id";
    public static final String PLAYERS_NAME = "name";
    public static final String PLAYERS_AVATAR = "avatar";
    public static final String PLAYERS_BESTSCORE = "bestscore";
    //QUERYS
    public static final String CREATE_PLAYERS_TABLE = "CREATE TABLE "+ PLAYERS_BD + " ("+ PLAYERS_ID + " INTEGER PRIMARY KEY, "+ PLAYERS_NAME+ " TEXT," + PLAYERS_AVATAR +" INTEGER,"+ PLAYERS_BESTSCORE + " INTEGER)";


    public static void getAvatarList(){
        avatarList = new ArrayList<AvatarVO>();

        avatarList.add(new AvatarVO(1, R.drawable.avatar1, "Avatar1"));
        avatarList.add(new AvatarVO(2, R.drawable.avatar2, "Avatar2"));
        avatarList.add(new AvatarVO(3, R.drawable.avatar3, "Avatar3"));
        avatarList.add(new AvatarVO(4, R.drawable.avatar4, "Avatar4"));

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
