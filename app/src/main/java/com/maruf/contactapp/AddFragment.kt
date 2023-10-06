package com.maruf.contactapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.maruf.contactapp.databinding.FragmentAddBinding
import com.maruf.contactapp.model.Contacts
import com.maruf.contactapp.viewmodel.ContactViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private val viewModel by viewModels<ContactViewModel>()


    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(layoutInflater, null, false)
        binding.addContactBtn.setOnClickListener {
            createContact(it)
        }
        return binding.root
    }

    // function to create new contact and call
    // the addContacts function from the ViewModel class
    private fun createContact(it: View?) {
        // read name and number from EditTexts
        val name = binding.contactNameEditText.text.toString()
        val number = binding.contactNumberEditText.text.toString()

        // create new contact object
        val data = Contacts(null, img = "https://images.pexels.com/photos/771742/pexels-photo-771742.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500", name = name, number = number)

        // call addContacts function from the ViewModel class
        viewModel.addContacts(data)

        findNavController().popBackStack()
    }


}