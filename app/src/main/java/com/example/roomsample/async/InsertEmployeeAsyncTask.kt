package com.example.roomsample.async

import android.app.Dialog
import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import com.example.roomsample.MainActivity
import com.example.roomsample.db.EmployeeDAO
import com.example.roomsample.models.Employee

class InsertEmployeeAsyncTask(
    var context: Context,
    var empDao: EmployeeDAO,
    var employee: Employee,
    val dialog: Dialog
) :
    AsyncTask<Void, Void?, Boolean?>() {

    var empId = 0L

    override fun doInBackground(vararg params: Void?): Boolean {
        empId = empDao.addEmployee(employee)
        return true
    }

    override fun onPostExecute(result: Boolean?) {
        if (result!!) {
            Toast.makeText(context, "Added to Database", Toast.LENGTH_LONG).show()
            val activity = context as MainActivity
            employee.id = empId.toInt()
            activity.addEmployee(employee)
            dialog.dismiss()
        }
    }

}