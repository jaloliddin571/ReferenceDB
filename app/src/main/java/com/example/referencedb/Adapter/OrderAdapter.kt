package com.example.referencedb.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.referencedb.databinding.ItemRvBinding
import com.example.referencedb.models.MyOrder

class OrderAdapter(val list: List<MyOrder>):RecyclerView.Adapter<OrderAdapter.Vh>() {
    inner class Vh(var itemRvBinding:ItemRvBinding):RecyclerView.ViewHolder(itemRvBinding.root){
        fun OnBind(myOrder: MyOrder,position: Int){
            itemRvBinding.tvName.text=myOrder.name
            itemRvBinding.tvCustomer.text=myOrder.myCustomer?.name
            itemRvBinding.tvEmployee.text=myOrder.myemployee?.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
         return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int=list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.OnBind(list[position],position)
    }
}