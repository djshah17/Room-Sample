package com.example.roomsample.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Employees")
data class Employee(
    @ColumnInfo(name = "emp_name") var name: String,
    @ColumnInfo(name = "emp_salary") var salary:String,
    @ColumnInfo(name = "emp_age") var age:String)
    : Serializable {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}