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
    val priem: List<Priem>?,
    @SerializedName("imageText")
    val imageText: ImageText,
    @SerializedName("imageTextTwo")
    val imageTextSecond: ImageTextSecond,
    @SerializedName("projectText")
    val projectText: ProjectText,
    @SerializedName("programmsBakalavr")
    val bachelorPrograms: List<BachelorPrograms>,
    @SerializedName("programmsSpec")
    val specialtyPrograms: List<SpecialtyPrograms>,
    @SerializedName("programmsMag")
    val magistracyPrograms: List<MagistracyPrograms>,
    @SerializedName("facultyInformation")
    val facultyInformation: FacultyInformation,
    @SerializedName("contactInformation")
    val contactsInformation: ContactsInformation,
): Parcelable

@Parcelize
data class Header(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("imgMin") val imgMin: String,
    @SerializedName("imgMax") val imgMax: String,
    @SerializedName("description") val description: String,
):Parcelable

@Parcelize
data class Priem(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("imgMin") val imgMin: String,
    @SerializedName("dataInfo") val infoAdmission: ArrayList<InfoAdmission>,
):Parcelable

@Parcelize
data class InfoAdmission(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
):Parcelable

@Parcelize
data class ImageText(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("imgMin") val imgMin: String,
):Parcelable


@Parcelize
data class ContactsInformation(
    @SerializedName("photo") val photo: String,
    @SerializedName("youtube") val youtube: String,
    @SerializedName("tgChannel") val tgChannel: String,
    @SerializedName("tgBot") val tgBot: String,
    @SerializedName("vk") val vk: String,
    @SerializedName("number") val number: String,
):Parcelable

@Parcelize
data class ProjectText(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("imgMin") val imgMin: String,
):Parcelable

@Parcelize
data class ImageTextSecond(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("imgMin") val imgMin: String,
):Parcelable

@Parcelize
data class BachelorPrograms(
    @SerializedName("id") val id: String,
    @SerializedName("imgMin") val imgMin: String,
    @SerializedName("title") val title: String,
    @SerializedName("format") val format: String,
    @SerializedName("year") val year: String,
    @SerializedName("countBudz") val countBudget: String,
    @SerializedName("countPlat") val countPaid: String,
    @SerializedName("price") val price: String,
):Parcelable

@Parcelize
data class SpecialtyPrograms(
    @SerializedName("id") val id: String,
    @SerializedName("imgMin") val imgMin: String,
    @SerializedName("title") val title: String,
    @SerializedName("format") val format: String,
    @SerializedName("year") val year: String,
    @SerializedName("countBudz") val countBudget: String,
    @SerializedName("countPlat") val countPaid: String,
    @SerializedName("price") val price: String,
):Parcelable

@Parcelize
data class MagistracyPrograms(
    @SerializedName("id") val id: String,
    @SerializedName("imgMin") val imgMin: String,
    @SerializedName("title") val title: String,
    @SerializedName("format") val format: String,
    @SerializedName("year") val year: String,
    @SerializedName("countBudz") val countBudget: String,
    @SerializedName("countPlat") val countPaid: String,
    @SerializedName("price") val price: String,
):Parcelable

@Parcelize
data class InfoFaculty(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("imgMin") val imgMin: String,
    @SerializedName("description") val description: String,
):Parcelable

@Parcelize
data class FacultyInformation(
    @SerializedName("url") val url: String,
    @SerializedName("codeOne") val codeOne: String,
    @SerializedName("codeTwo") val codeTwo: String,
    @SerializedName("infoFaculty") val infoFaculty: ArrayList<InfoFaculty>,
):Parcelable








