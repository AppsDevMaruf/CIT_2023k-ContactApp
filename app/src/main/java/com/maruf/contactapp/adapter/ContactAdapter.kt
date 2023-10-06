package com.maruf.contactapp.adapter

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.maruf.contactapp.R
import com.maruf.contactapp.databinding.RowContactItemBinding

import com.maruf.contactapp.model.Contacts


class ContactAdapter(var contacts: List<Contacts>) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    private var filteredContacts: List<Contacts> = contacts.toList()

    // create an inner class with name ViewHolder
    // It takes a view argument, in which pass the generated class of single_item.xml
    // ie SingleItemBinding and in the RecyclerView.ViewHolder(binding.root) pass it like this
    inner class ContactViewHolder(val binding: RowContactItemBinding) : ViewHolder(binding.root)

    // inside the onCreateViewHolder inflate the view of SingleItemBinding
    // and return new ViewHolder object containing this layout

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding =
            RowContactItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }
    // bind the items with each item
    // of the list languageList
    // which than will be
    // shown in recycler view
    // to keep it simple we are
    // not setting any image data to view

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        with(holder) {
            val contactModel= filteredContacts[position]
            with(contactModel) {
                binding.nameTV.text = this.name
                binding.numberTV.text = this.number
                // URL
                img?.let {
                    binding.profileImage.load(it)
                }

                binding.callIV.setOnClickListener {
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + this.number))

                    try {
                        binding.root.context.startActivity(intent)
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(binding.root.context, e.localizedMessage, Toast.LENGTH_SHORT)
                            .show()
                    }

                }
                binding.messageIV.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW,Uri.parse("sms:" + this.number))
                    try {
                       binding.root.context.startActivity(intent)
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(binding.root.context, e.localizedMessage, Toast.LENGTH_SHORT)
                            .show()
                    }

                }
                binding.infoIV.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putSerializable("contact", contactModel)
                    it.findNavController().navigate(R.id.action_homeFragment_to_detailsFragment, bundle)

                }

                }
            }
        }
    // return the size of languageList
    override fun getItemCount(): Int {
        return filteredContacts.size
    }

    // method for filtering our recyclerview items.
    @SuppressLint("NotifyDataSetChanged")
    fun filter(query: String) {
        filteredContacts = contacts.filter {
                contact -> contact.name.contains(query, ignoreCase = true)
                || contact.number.contains(query)
        }
        notifyDataSetChanged()
    }


}