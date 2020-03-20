package com.example.roomsample.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomsample.models.Employee

@Database(entities = [Employee::class],version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getDAO() : EmployeeDAO

    companion object{
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(ctx: Context) : AppDataBase{
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    ctx.applicationContext,
                    AppDataBase::class.java,
                    "emp_database.db"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }

    }

}