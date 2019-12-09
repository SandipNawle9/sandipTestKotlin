package com.mvvm.sandipteskotlin.repository.database;

import android.content.Context;
import android.os.Handler;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.mvvm.sandiptest.repository.database.dao.UserDao;
import com.mvvm.sandiptest.repository.database.entity.User;


//Write in java because in kotlin fetch some problem
//TODO: Convert it in kotlin
@Database(entities = {User.class}, version = 1)
public abstract class UserAppDatabase extends RoomDatabase {
    public abstract UserDao getUserDao();
    private static UserAppDatabase userAppDatabase;

    public static UserAppDatabase getUserDatabase(Context context){
        if(userAppDatabase == null) {
            try {
                userAppDatabase = Room.databaseBuilder(context, UserAppDatabase.class, "userdb.db").allowMainThreadQueries().build();
            }catch (Exception e){
                e.printStackTrace();
            }
        }


        return userAppDatabase;
    }



}
