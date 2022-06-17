package com.example.university.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FirstRecyclerModel (
    @SerializedName("header")
    val header: List<Header>,
    @SerializedName("priem")
    val priem: List<Priem>
): Parcelable

@Parcelize
data class Header(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("imgMin") val imgMin: String,
):Parcelable

@Parcelize
data class Priem(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("imgMin") val imgMin: String,
):Parcelable



