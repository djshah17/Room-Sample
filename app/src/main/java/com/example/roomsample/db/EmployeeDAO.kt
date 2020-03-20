package com.example.roomsample.db

import androidx.room.*
import com.example.roomsample.models.Employee

@Dao
interface EmployeeDAO {

    @Insert
    fun addEmployee(employee: Employee) : Long

    @Update
    fun updateEmployee(employee: Employee)

    @Query("select * from Employees WHERE id LIKE :emp_id")
    fun getEmployee(emp_id: Int) : Employee

    @Query("select * from Employees")
    fun getAllEmployees() : MutableList<Employee>

    @Delete
    fun deleteEmployee(employee: Employee)

    @Query("DELETE FROM Employees")
    fun deleteAllEmployees()

}