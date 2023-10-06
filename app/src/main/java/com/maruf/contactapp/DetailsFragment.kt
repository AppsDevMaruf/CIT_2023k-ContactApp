package com.maruf.contactapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.maruf.contactapp.databinding.FragmentDetailsBinding
import com.maruf.contactapp.model.Contacts


class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val contactModel = arguments?.getSerializable("contact") as Contacts?

        if (contactModel != null) {
           //binding.profileImage.setImageResource(contactModel.image)
            // URL
            contactModel.img?.let {
                binding.profileImage.load(it)
            }
           binding.nameTV.text = contactModel.name
           binding.numberTV.text = contactModel.number
        }


        return binding.root
    }


}