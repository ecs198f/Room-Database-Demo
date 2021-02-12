package com.example.shoppingappdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingappdemo.data.ShopperViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*
import androidx.lifecycle.Observer


class ListFragment : Fragment() {

    private lateinit var myShopperViewModel: ShopperViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        //Recycler View
        val shoppingAdapter = ShoppingListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = shoppingAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // ShopperViewmodel
        myShopperViewModel = ViewModelProvider(this).get(ShopperViewModel::class.java)
        myShopperViewModel.readAllShoppingData.observe(viewLifecycleOwner, Observer{ shopper ->
            shoppingAdapter.setData(shopper)
        })

        view.floatingActionButton2.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        return view
    }
}