package com.example.roomsample.async

import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import com.example.roomsample.MainActivity
import com.example.roomsample.adapters.EmployeeAdapter
import com.example.roomsample.db.EmployeeDAO
import com.example.roomsample.models.Employee

class GetAllEmployeesAsyncTask(var context: Context, var empDao: EmployeeDAO) :
    AsyncTask<Void, Void?, Boolean?>() {

    private var employeesList: MutableList<Employee> = mutableListOf()

    override fun doInBackground(vararg params: Void?): Boolean {
        employeesList = empDao.getAllEmployees()
        return true
    }

    override fun onPostExecute(result: Boolean?) {
        if (result!!) {
            Toast.makeText(context, "Get employees from Database", Toast.LENGTH_LONG).show()
            val activity = context as MainActivity
            activity.getAllEmployees(employeesList)
        }
    }

}