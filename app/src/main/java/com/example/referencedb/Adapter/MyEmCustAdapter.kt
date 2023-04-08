package com.example.referencedb.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.referencedb.databinding.ItemRvBinding
import com.example.referencedb.models.MyCustomer
import com.example.referencedb.models.MyEmployee

class MyEmCustAdapter<T>(val list: List<T>):RecyclerView.Adapter< MyEmCustAdapter<T>.Vh> (){

    inner class  Vh(val itemRvBinding: ItemRvBinding):RecyclerView.ViewHolder(itemRvBinding.root){
        fun onEmployeeBinding(myEmployee: MyEmployee){

            itemRvBinding.tvName.text=myEmployee.name
            itemRvBinding.tvNumber.visibility=View.VISIBLE
            itemRvBinding.tvNumber.text=myEmployee.number
            itemRvBinding.tvEmployee.visibility=View.GONE
            itemRvBinding.tvCustomer.visibility=View.GONE
        }
        fun onCustomerBinding(myCustomer: MyCustomer){
            itemRvBinding.tvName.text=myCustomer.name
            itemRvBinding.tvNumber.visibility=View.VISIBLE
            itemRvBinding.tvNumber.text=myCustomer.number
            itemRvBinding.tvAddress.visibility=View.VISIBLE
            itemRvBinding.tvAddress.text=myCustomer.address
            itemRvBinding.tvEmployee.visibility=View.GONE
            itemRvBinding.tvCustomer.visibility=View.GONE


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
    return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int =list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {

        try {
            val  myEmployeey:MyEmployee=list[position] as MyEmployee
            holder.onEmployeeBinding(myEmployeey)
        }catch (e:Exception){
            val  myCustomer:MyCustomer=list[position] as MyCustomer
            holder.onCustomerBinding(myCustomer)
        }
    }
}