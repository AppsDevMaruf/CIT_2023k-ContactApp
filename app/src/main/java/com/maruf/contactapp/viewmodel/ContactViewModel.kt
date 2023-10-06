package com.maruf.contactapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.maruf.contactapp.ContactDatabase
import com.maruf.contactapp.model.Contacts
import com.maruf.contactapp.repo.ContactRepository

class ContactViewModel(application: Application) : AndroidViewModel(application) {
    private val repository : ContactRepository
    init {
        val dao = ContactDatabase.getDatabaseInstance(application).contactsDao()
        repository = ContactRepository(dao)
    }
    fun addContacts(contact : Contacts){
        repository.insertContact(contact)
    }
    fun getAllContacts() : LiveData<List<Contacts>> = repository.getAllContacts()
}
