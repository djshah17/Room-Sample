package com.example.roomsample.async

import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import com.example.roomsample.MainActivity
import com.example.roomsample.db.EmployeeDAO
import com.example.roomsample.models.Employee

class DeleteEmployeeAsyncTask(var context: Context, var empDao: EmployeeDAO, var employee: Employee) :
    AsyncTask<Void, Void?, Boolean?>() {

    override fun doInBackground(vararg params: Void?): Boolean {
        empDao.deleteEmployee(employee)
        return true
    }

    override fun onPostExecute(result: Boolean?) {
        if (result!!) {
            Toast.makeText(context, "Deleted from Database", Toast.LENGTH_LONG).show()
            val activity = context as MainActivity
            activity.removeEmployee(employee)
        }
    }

}