package com.example.shoppingappdemo

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.shoppingappdemo.data.Shopper
import com.example.shoppingappdemo.data.ShopperViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import androidx.navigation.fragment.findNavController


class AddFragment : Fragment() {

    private lateinit var myShopperViewModel: ShopperViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        myShopperViewModel = ViewModelProvider(this).get(ShopperViewModel::class.java)

        view.addToCart.setOnClickListener{
            insertDataToDatabase()
        }
        return view
    }

    private fun insertDataToDatabase() {
        val item = addItem_et.text.toString()
        val price = addPrice_et.text.toString()

        if(inputCheck(item, price)){
            val shopper = Shopper(0, item, Integer.parseInt(price.toString()))
            myShopperViewModel.addShopper(shopper)
            Toast.makeText(requireContext(), "Successfully Added an Item!", Toast.LENGTH_LONG).show()
           findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else {
            Toast.makeText(requireContext(), "Invalid Item", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(item: String, price: String): Boolean {
        return !(TextUtils.isEmpty(item) && price.isEmpty())
    }
}