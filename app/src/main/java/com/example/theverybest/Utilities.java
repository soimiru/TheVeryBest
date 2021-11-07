package com.example.theverybest;

import com.example.theverybest.vo.AvatarVO;

import java.util.ArrayList;

public class Utilities {

    public static ArrayList<AvatarVO> avatarList = null;

    public static AvatarVO selectedAvatar = null;
    public static int selectedAvatarID = 0;

    public static void getAvatarList(){
        avatarList = new ArrayList<AvatarVO>();

        avatarList.add(new AvatarVO(1, R.drawable.avatar1, "Avatar1"));
        avatarList.add(new AvatarVO(2, R.drawable.avatar2, "Avatar2"));
        avatarList.add(new AvatarVO(3, R.drawable.avatar3, "Avatar3"));
        avatarList.add(new AvatarVO(4, R.drawable.avatar4, "Avatar4"));

        selectedAvatar = avatarList.get(0);

    }
}
