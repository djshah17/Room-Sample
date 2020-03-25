# Room-Sample
This is a simple example of room library in kotlin.


## Add the below dependencies in your app level build.gradle file
```gradle
apply plugin: 'kotlin-kapt'

dependencies {
implementation "androidx.room:room-runtime:2.2.4"
kapt "androidx.room:room-compiler:2.2.4"
}
```

## Create a Entity class which represents a table in the database
```kotlin
@Entity(tableName = "Employees")
data class Employee(
    @ColumnInfo(name = "emp_name") var name: String,
    @ColumnInfo(name = "emp_salary") var salary:String,
    @ColumnInfo(name = "emp_age") var age:String)
    : Serializable {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
```    

## Create a DAO interface which contains all queries
```kotlin
@Dao
interface EmployeeDAO {

    @Insert
    fun addEmployee(employee: Employee)

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
```

## Create a Database class which contains all your DAOs
```kotlin
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
``` 

## Usage
```kotlin
val employeeDAO = AppDataBase.getDatabase(this).getDAO()
InsertEmployeeAsyncTask(context,employeeDAO,newEmployee).execute()

class InsertEmployeeAsyncTask(var context: Context, var empDao: EmployeeDAO, var employee: Employee) :
    AsyncTask<Void, Void?, Boolean?>() {

    override fun doInBackground(vararg params: Void?): Boolean {
        empDao.addEmployee(employee)
        return true
    }

    override fun onPostExecute(result: Boolean?) {
        if (result!!) {
            Toast.makeText(context, "Added to Database", Toast.LENGTH_LONG).show()
        }
    }

}
```
