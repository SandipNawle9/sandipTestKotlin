package com.mvvm.sandiptest.repository.database.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.mvvm.sandiptest.repository.model.Address
import com.mvvm.sandiptest.repository.model.Company

@Entity
data class User(
        @PrimaryKey(autoGenerate = false)
        @SerializedName("id")
        var id: Int,

        @SerializedName("name")
        @ColumnInfo(name = "full_name")
        var name: String?,

        @SerializedName("username")
        var username: String?,

        @SerializedName("email")
        var email: String?,

        @Embedded
        @SerializedName("address")
        var address: Address?,

        @Embedded
        @SerializedName("company")
        var company: Company?,

        @SerializedName("phone")
        var phone : String?,

        @SerializedName("website")
        var website: String?

)