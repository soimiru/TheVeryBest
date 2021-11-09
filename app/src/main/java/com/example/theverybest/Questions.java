package com.example.theverybest;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "questions_table")
public class Questions implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name= "question")
    private String question;

    @ColumnInfo(name= "sound")
    private int sound;

    @ColumnInfo(name= "opt1")
    private String opt1;

    @ColumnInfo(name= "opt2")
    private String opt2;

    @ColumnInfo(name= "opt3")
    private String opt3;

    @ColumnInfo(name= "opt4")
    private String opt4;

    @ColumnInfo(name= "answer")
    private int answer;

    public Questions(String question, int sound, String opt1, String opt2, String opt3, String opt4, int answer) {
        this.question = question;
        this.sound = sound;
        this.opt1 = opt1;
        this.opt2 = opt2;
        this.opt3 = opt3;
        this.opt4 = opt4;
        this.answer = answer;
    }

    public Questions() {
        this.question = question;
        this.sound = sound;
        this.opt1 = opt1;
        this.opt2 = opt2;
        this.opt3 = opt3;
        this.opt4 = opt4;
        this.answer = answer;
    }

    protected Questions(Parcel in) {
        id = in.readInt();
        question = in.readString();
        opt1 = in.readString();
        opt2 = in.readString();
        opt3 = in.readString();
        opt4 = in.readString();
        answer = in.readInt();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOpt1() {
        return opt1;
    }

    public void setOpt1(String opt1) {
        this.opt1 = opt1;
    }

    public String getOpt2() {
        return opt2;
    }

    public void setOpt2(String opt2) {
        this.opt2 = opt2;
    }

    public String getOpt3() {
        return opt3;
    }

    public void setOpt3(String opt3) {
        this.opt3 = opt3;
    }

    public String getOpt4() {
        return opt4;
    }

    public void setOpt4(String opt4) {
        this.opt4 = opt4;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getSound() {
        return sound;
    }

    public void setSound(int sound) {
        this.sound = sound;
    }

    public static final Creator<Questions> CREATOR = new Creator<Questions>() {
        @Override
        public Questions createFromParcel(Parcel in) {
            return new Questions(in);
        }

        @Override
        public Questions[] newArray(int size) {
            return new Questions[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(question);
        parcel.writeString(opt1);
        parcel.writeString(opt2);
        parcel.writeString(opt3);
        parcel.writeString(opt4);
        parcel.writeInt(answer);
    }
}
