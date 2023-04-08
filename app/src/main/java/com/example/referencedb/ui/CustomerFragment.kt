package com.example.referencedb.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.referencedb.Adapter.MyEmCustAdapter
import com.example.referencedb.databinding.FragmentEmployeeBinding
import com.example.referencedb.databinding.ItemDialogBinding
import com.example.referencedb.db.MyDbHelper
import com.example.referencedb.models.MyCustomer

class CustomerFragment:Fragment() {
private lateinit var binding: FragmentEmployeeBinding
private lateinit var myDbHelper: MyDbHelper
private lateinit var myEmCustAdapter: MyEmCustAdapter<MyCustomer>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEmployeeBinding.inflate(layoutInflater)

        myDbHelper = MyDbHelper(binding.root.context)
        myEmCustAdapter = MyEmCustAdapter(myDbHelper.getAllCustomer())
        binding.rv.adapter = myEmCustAdapter
        binding.apply {
            binding.btnAdd.setOnClickListener {
                val dialog = AlertDialog.Builder(binding.root.context).create()
                val itemDialogBinding = ItemDialogBinding.inflate(layoutInflater)
                dialog.setView(itemDialogBinding.root)

                itemDialogBinding.spinnerCustomer.visibility = View.GONE
                itemDialogBinding.spinnerEmployee.visibility = View.GONE
                itemDialogBinding.edtNumber.visibility = View.VISIBLE
                itemDialogBinding.edtAddress.visibility = View.VISIBLE
                itemDialogBinding.btnSave.setOnClickListener {
                    val mycustomer = MyCustomer(
                        itemDialogBinding.edtName.text.toString(),
                        itemDialogBinding.edtNumber.text.toString(),
                        itemDialogBinding.edtAddress.text.toString(),

                        )
                    myDbHelper.addCustomer(mycustomer)
                    Toast.makeText(context, "saved", Toast.LENGTH_SHORT).show()
                }
                dialog.show()
            }
        }
            return binding.root
        }
    }
