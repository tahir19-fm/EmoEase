package com.example.emoease.screens.exerciseScreen.modal

import com.google.gson.annotations.SerializedName

data class YogaCategories (
    @SerializedName("id"                   ) var id                  : Int?             = null,
    @SerializedName("category_name"        ) var categoryName        : String?          = null,
    @SerializedName("category_description" ) var categoryDescription : String?          = null,
    @SerializedName("poses"                ) var poses               : ArrayList<Poses> = arrayListOf()

)
data class Poses (
    @SerializedName("id"                    ) var id                  : Int?    = null,
    @SerializedName("category_name"         ) var categoryName        : String? = null,
    @SerializedName("english_name"          ) var englishName         : String? = null,
    @SerializedName("sanskrit_name_adapted" ) var sanskritNameAdapted : String? = null,
    @SerializedName("sanskrit_name"         ) var sanskritName        : String? = null,
    @SerializedName("translation_name"      ) var translationName     : String? = null,
    @SerializedName("pose_description"      ) var poseDescription     : String? = null,
    @SerializedName("pose_benefits"         ) var poseBenefits        : String? = null,
    @SerializedName("url_svg"               ) var urlSvg              : String? = null,
    @SerializedName("url_png"               ) var urlPng              : String? = null,
    @SerializedName("url_svg_alt"           ) var urlSvgAlt           : String? = null

)