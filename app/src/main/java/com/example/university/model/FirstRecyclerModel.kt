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
    val priem: List<Priem>,
    @SerializedName("imageText")
    val imageText: ImageText,
    @SerializedName("imageTextTwo")
    val imageTextSecond: ImageTextSecond,
    @SerializedName("projectText")
    val projectText: ProjectText,
    @SerializedName("programmsBakalavr")
    val bachelorPrograms: List<BachelorPrograms>
): Parcelable

@Parcelize
data class Header(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("imgMin") val imgMin: String,
    @SerializedName("imgMax") val imgMax: String,
    @SerializedName("description") val description: String,
):Parcelable

@Parcelize
data class Priem(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("imgMin") val imgMin: String,
):Parcelable

@Parcelize
data class ImageText(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("imgMin") val imgMin: String,
):Parcelable

@Parcelize
data class ProjectText(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("imgMin") val imgMin: String,
):Parcelable

@Parcelize
data class ImageTextSecond(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("imgMin") val imgMin: String,
):Parcelable

@Parcelize
data class BachelorPrograms(
    @SerializedName("id") val id: Int,
    @SerializedName("imgMin") val imgMin: String,
    @SerializedName("title") val title: String,
    @SerializedName("format") val format: String,
    @SerializedName("year") val year: String,
    @SerializedName("countBudz") val countBudget: String,
    @SerializedName("countPlat") val countPaid: String,
    @SerializedName("price") val price: String,
):Parcelable


