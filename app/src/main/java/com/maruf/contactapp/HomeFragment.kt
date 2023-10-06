package com.maruf.contactapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.maruf.contactapp.adapter.ContactAdapter
import com.maruf.contactapp.databinding.FragmentHomeBinding
import com.maruf.contactapp.viewmodel.ContactViewModel


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<ContactViewModel>()
    private lateinit var contactAdapter: ContactAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.addNewContactBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addFragment)

        }
        // Observe the LiveData returned by the getAllContacts method
        viewModel.getAllContacts().observe(viewLifecycleOwner) {
            binding.totalContactsTV.text = getString(R.string.total_contact, it.size)
            // set the layout manager and the adapter for the recycler view
            binding.contactRecyclerView.apply {
                contactAdapter = ContactAdapter(it)
                this.adapter = contactAdapter
                setHasFixedSize(true)
            }
        }



        binding.searchViewId.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                contactAdapter.filter(newText.orEmpty()) // Use the adapter reference here
                return true
            }
        })


        return binding.root
    }


}