package com.example.referencedb.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.referencedb.R
import com.example.referencedb.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(layoutInflater)

        binding.apply {
            btnEmployee.setOnClickListener {findNavController().navigate(R.id.employeeFragment)}
            btnCustomer.setOnClickListener {findNavController().navigate(R.id.customerFragment)}
            btnOrders.setOnClickListener {findNavController().navigate(R.id.ordersFaragment)}
        }


        return binding.root
    }

}