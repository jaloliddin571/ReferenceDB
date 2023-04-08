package com.example.referencedb.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.referencedb.Adapter.OrderAdapter
import com.example.referencedb.databinding.FragmentEmployeeBinding
import com.example.referencedb.databinding.ItemDialogBinding
import com.example.referencedb.db.MyDbHelper
import com.example.referencedb.models.MyOrder

class OrdersFaragment:Fragment() {
    private lateinit var binding: FragmentEmployeeBinding
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var orderAdapter: OrderAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentEmployeeBinding.inflate(layoutInflater)

        myDbHelper=MyDbHelper(binding.root.context)
        binding.rv.adapter=OrderAdapter(myDbHelper.getAllOrder())
        binding.btnAdd.setOnClickListener {
            val dialog=AlertDialog.Builder(binding.root.context).create()
            val itemDialogBinding=ItemDialogBinding.inflate(layoutInflater)
            dialog.setView(itemDialogBinding.root)
            dialog.show()

            itemDialogBinding.apply {
                 val emList=ArrayList<String>()
                val employeList=myDbHelper.getAllEmployee()
                for (myEmployee in employeList){
                    emList.add(myEmployee.name!!)
                }
                spinnerEmployee.adapter=
                    ArrayAdapter<String>(binding.root.context,android.R.layout.simple_list_item_1,emList)

                val custLest=ArrayList<String>()
                val cutomerList=myDbHelper.getAllCustomer()
                for (myCustomer in cutomerList ){
                    custLest.add(myCustomer.name!!)
                    spinnerCustomer.adapter=
                        ArrayAdapter<String>(binding.root.context,android.R.layout.simple_list_item_1,custLest)
                    edtNumber.visibility=View.VISIBLE
                    btnSave.setOnClickListener {
                        val myOrder=MyOrder(
                            edtName.text.toString(),
                            edtNumber.text.toString().toInt(),
                            employeList[spinnerEmployee.selectedItemPosition],
                            cutomerList[spinnerCustomer.selectedItemPosition]
                        )
                        myDbHelper.addOrder(myOrder)
                        Toast.makeText(context, "Order saved", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }


        return binding.root
    }
}