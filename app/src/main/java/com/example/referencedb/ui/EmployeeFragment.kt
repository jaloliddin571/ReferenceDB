package com.example.referencedb.ui

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.referencedb.Adapter.MyEmCustAdapter
import com.example.referencedb.R
import com.example.referencedb.databinding.FragmentEmployeeBinding
import com.example.referencedb.databinding.ItemDialogBinding
import com.example.referencedb.db.MyDbHelper
import com.example.referencedb.models.MyCustomer
import com.example.referencedb.models.MyEmployee

class EmployeeFragment : Fragment() {
  private lateinit var binding: FragmentEmployeeBinding
  private lateinit var myDbHelper:MyDbHelper
  private lateinit var myEmployeeAdapter:MyEmCustAdapter<MyEmployee>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding= FragmentEmployeeBinding.inflate(layoutInflater)

        myDbHelper = MyDbHelper(binding.root.context)
        myEmployeeAdapter = MyEmCustAdapter(myDbHelper.getAllEmployee())
        binding.rv.adapter = myEmployeeAdapter

        binding.apply {
            binding.btnAdd.setOnClickListener {
                val dialog = AlertDialog.Builder(binding.root.context).create()
                val itemDialogBinding = ItemDialogBinding.inflate(layoutInflater)
                dialog.setView(itemDialogBinding.root)

                itemDialogBinding.spinnerCustomer.visibility = View.GONE
                itemDialogBinding.spinnerEmployee.visibility = View.GONE
                itemDialogBinding.edtNumber.visibility = View.VISIBLE
                itemDialogBinding.btnSave.setOnClickListener {
                    val myEmployee = MyEmployee(
                        itemDialogBinding.edtName.text.toString(),
                        itemDialogBinding.edtNumber.text.toString()

                        )
                    myDbHelper.addEmployee(myEmployee)
                    Toast.makeText(context, "saved", Toast.LENGTH_SHORT).show()
                }
                dialog.show()
            }
        }
        return binding.root
    }

}