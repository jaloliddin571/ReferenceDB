package com.example.referencedb.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.referencedb.db.MyConstant.CUSTOMER_ADDRESS
import com.example.referencedb.db.MyConstant.CUSTOMER_ID
import com.example.referencedb.db.MyConstant.CUSTOMER_NAME
import com.example.referencedb.db.MyConstant.CUSTOMER_NUMBER
import com.example.referencedb.db.MyConstant.CUSTOMER_TABLE
import com.example.referencedb.db.MyConstant.DB_NAME
import com.example.referencedb.db.MyConstant.EMPLOYEE_ID
import com.example.referencedb.db.MyConstant.EMPLOYEE_NAME
import com.example.referencedb.db.MyConstant.EMPLOYEE_NUMBER
import com.example.referencedb.db.MyConstant.EMPLOYEE_TABLE
import com.example.referencedb.db.MyConstant.ORDER_CUSTOMER_ID
import com.example.referencedb.db.MyConstant.ORDER_DATE
import com.example.referencedb.db.MyConstant.ORDER_EMPLOYEE_ID
import com.example.referencedb.db.MyConstant.ORDER_ID
import com.example.referencedb.db.MyConstant.ORDER_NAME
import com.example.referencedb.db.MyConstant.ORDER_PRICE
import com.example.referencedb.db.MyConstant.ORDER_TABLE
import com.example.referencedb.db.MyConstant.VERSION
import com.example.referencedb.models.MyCustomer
import com.example.referencedb.models.MyEmployee
import com.example.referencedb.models.MyOrder

class MyDbHelper(context: Context):SQLiteOpenHelper(context,DB_NAME,null, VERSION),MyDbInterface {
    override fun onCreate(db: SQLiteDatabase?) {
        val queryCustomer="create table  $CUSTOMER_TABLE($CUSTOMER_ID integer not null primary key autoincrement unique,$CUSTOMER_NAME text not null,$CUSTOMER_NUMBER text not null, $CUSTOMER_ADDRESS text not null );"
        val queryEmployee="create table  $EMPLOYEE_TABLE($EMPLOYEE_ID integer not null primary key autoincrement unique,$EMPLOYEE_NAME text not null,$EMPLOYEE_NUMBER text not null);"
        val queryOrder="create table  $ORDER_TABLE($ORDER_ID integer not null primary key autoincrement unique,$ORDER_NAME text not null,$ORDER_DATE text not null,$ORDER_PRICE integer not null , $ORDER_CUSTOMER_ID integer not null ,$ORDER_EMPLOYEE_ID integer not null , foreign key ($ORDER_CUSTOMER_ID) references $CUSTOMER_TABLE($CUSTOMER_ID) , foreign key ($ORDER_EMPLOYEE_ID) references $EMPLOYEE_TABLE($EMPLOYEE_ID));"

        db?.execSQL(queryCustomer)
        db?.execSQL(queryEmployee)
        db?.execSQL(queryOrder)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    override fun addCustomer(myCustomer: MyCustomer) {
        val database=writableDatabase
        val contentValues=ContentValues()
        contentValues.put(CUSTOMER_NAME, myCustomer.name)
        contentValues.put(CUSTOMER_NUMBER, myCustomer.number)
        contentValues.put(CUSTOMER_ADDRESS, myCustomer.address)
        database.insert(CUSTOMER_TABLE,null,contentValues)
        database.close()

    }

    override fun getAllCustomer(): List<MyCustomer> {
        val list=ArrayList<MyCustomer>()
        val database=readableDatabase
        val query="select * from $CUSTOMER_TABLE"
        val cursor=database.rawQuery(query,null)
        if (cursor.moveToFirst()){
            do {
                list.add(
                    MyCustomer(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                    )
                )

            }while (cursor.moveToNext())
        }
        return list
    }

    override fun addEmployee(myEmployee: MyEmployee) {
        val database=writableDatabase
        val contentValues=ContentValues()
        contentValues.put(EMPLOYEE_NAME, myEmployee.name)
        contentValues.put(EMPLOYEE_NUMBER, myEmployee.number)
        database.insert(EMPLOYEE_TABLE,null,contentValues)
        database.close()
    }

    override fun getAllEmployee(): List<MyEmployee> {
        val list=ArrayList<MyEmployee>()
        val database=readableDatabase
        val query="select * from $EMPLOYEE_TABLE"
        val cursor=database.rawQuery(query,null)
        if (cursor.moveToFirst()){
            do {
                list.add(
                    MyEmployee(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                    )
                )

            }while (cursor.moveToNext())
        }
        return list
    }

    override fun addOrder(myOrder: MyOrder) {
        val database=writableDatabase
        val contentValues=ContentValues()
        contentValues.put(ORDER_NAME, myOrder.name)
        contentValues.put(ORDER_DATE, myOrder.date)
        contentValues.put(ORDER_PRICE, myOrder.price)
        contentValues.put(ORDER_EMPLOYEE_ID, myOrder.myemployee?.id)
        contentValues.put(ORDER_CUSTOMER_ID, myOrder.myCustomer?.id)
        database.insert(ORDER_TABLE,null,contentValues)
        database.close()
    }

    override fun getAllOrder(): List<MyOrder> {
        val list=ArrayList<MyOrder>()
        val database=readableDatabase
        val query="select * from $ORDER_TABLE"
        val cursor=database.rawQuery(query,null)
        if (cursor.moveToFirst()){
            do {
                list.add(
                    MyOrder(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(3),
                        getEmployeById(cursor.getInt(4)),
                        getCustomerById(cursor.getInt(5)),
                        cursor.getString(2)

                    )
                )

            }while (cursor.moveToNext())
        }
        return list
    }

    override fun getEmployeById(id: Int): MyEmployee {
        val database=readableDatabase
        val cursor=database.query(
            EMPLOYEE_TABLE,
            arrayOf(EMPLOYEE_ID, EMPLOYEE_NAME, EMPLOYEE_NUMBER),
            "$EMPLOYEE_ID=?",
            arrayOf(id.toString()),
            null , null, null
        )
        cursor.moveToFirst()
        val myEmployee=MyEmployee(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2)
        )
        return  myEmployee
    }

    override fun getCustomerById(id: Int): MyCustomer {
        val database=readableDatabase
        val cursor=database.query(
            CUSTOMER_TABLE,
            arrayOf(CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_NUMBER, CUSTOMER_ADDRESS),
            "$CUSTOMER_ID=?",
            arrayOf(id.toString()),
            null , null, null
        )
        cursor.moveToFirst()
        val myCustomer=MyCustomer(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3)
        )
        return  myCustomer
    }
}