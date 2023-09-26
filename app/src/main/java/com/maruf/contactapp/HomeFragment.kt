package com.maruf.contactapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView

import com.maruf.contactapp.adapter.ContactAdapter
import com.maruf.contactapp.databinding.FragmentHomeBinding

import com.maruf.contactapp.model.ContactModel


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val contactList = mutableListOf<ContactModel>()
        contactList.add(ContactModel(R.drawable.profile_img,"Kajol","0168742252"))
        contactList.add(ContactModel(R.drawable.profile_img,"Maruf","01307422428"))
        contactList.add(ContactModel(R.drawable.profile_img,"Porosh","0178742252"))
        contactList.add(ContactModel(R.drawable.profile_img,"Kamrun","01458742252"))

        val adapter = ContactAdapter(contactList) // Create the adapter

        binding.contactRecyclerView.apply {
            setHasFixedSize(true)
            this.adapter = adapter // Set the adapter to the RecyclerView
        }

        binding.searchViewId.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter(newText.orEmpty()) // Use the adapter reference here
                return true
            }
        })
        //binding.totalContactsTV.text = contactList.size.toString()
        binding.totalContactsTV.text = getString(R.string.total_contact,contactList.size)




        return binding.root
    }


}