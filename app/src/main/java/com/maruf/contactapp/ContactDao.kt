package com.maruf.contactapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

import androidx.lifecycle.LiveData
import androidx.room.*
import com.maruf.contactapp.model.Contacts

@Dao
interface ContactDao {
    @Query("Select * from Contacts")
    fun getAllContacts(): LiveData<List<Contacts>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContact(contact: Contacts)

    @Delete
    fun delete(contact: Contacts)
}

