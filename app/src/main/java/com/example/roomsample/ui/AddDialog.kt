package com.example.roomsample.ui

import android.app.Dialog
import android.content.Context
import android.text.TextUtils
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.roomsample.R
import com.example.roomsample.async.InsertEmployeeAsyncTask
import com.example.roomsample.async.UpdateEmployeeAsyncTask
import com.example.roomsample.db.EmployeeDAO
import com.example.roomsample.models.Employee


object AddDialog {

    fun showDialog(
        employeeDAO: EmployeeDAO,
        context: Context,
        isEdit: Boolean,
        oldEmployee: Employee?,
        empPos: Int
    ) {
        val dialog = Dialog(context)
        dialog.setTitle("Add Employee")
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.employee_add_dialog)
        val edtName = dialog.findViewById(R.id.edt_name) as EditText
        val edtSalary = dialog.findViewById(R.id.edt_salary) as EditText
        val edtAge = dialog.findViewById(R.id.edt_age) as EditText

        if (isEdit) {
            edtName.setText(oldEmployee?.name)
            edtSalary.setText(oldEmployee?.salary)
            edtAge.setText(oldEmployee?.age)
        }

        val submit = dialog.findViewById(R.id.btn_add) as Button

        submit.setOnClickListener {
            val name = edtName.text.toString()
            val salary = edtSalary.text.toString()
            val age = edtAge.text.toString()
            if (!TextUtils.isEmpty(name) || !TextUtils.isEmpty(salary) || !TextUtils.isEmpty(age)) {
                val newEmployee = Employee(name, salary, age)
                if (isEdit) {
                    newEmployee.id = oldEmployee?.id!!
                    UpdateEmployeeAsyncTask(context, employeeDAO, newEmployee, empPos, dialog).execute()
                } else {
                    InsertEmployeeAsyncTask(context, employeeDAO, newEmployee, dialog).execute()
                }
            } else {
                Toast.makeText(context, "All Fields Required", Toast.LENGTH_LONG).show()
            }

        }
        dialog.show()
        val window: Window = dialog.window
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

}