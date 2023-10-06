package com.maruf.contactapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Contacts")
class Contacts (
    @PrimaryKey(autoGenerate = true)
    var id : Int?=null,
    var img: String?=null,
    var name : String,
    var number : String
):Serializable
