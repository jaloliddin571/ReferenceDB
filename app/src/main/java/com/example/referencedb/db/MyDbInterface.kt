package com.example.referencedb.db

import com.example.referencedb.models.MyCustomer
import com.example.referencedb.models.MyEmployee
import com.example.referencedb.models.MyOrder

interface MyDbInterface {

    fun addCustomer(myCustomer: MyCustomer)
    fun getAllCustomer():List<MyCustomer>

    fun addEmployee(myEmployee: MyEmployee)
    fun getAllEmployee():List<MyEmployee>

    fun addOrder(myOrder: MyOrder)
    fun getAllOrder():List<MyOrder>

    fun getEmployeById(id:Int):MyEmployee
    fun getCustomerById(id:Int):MyCustomer

}