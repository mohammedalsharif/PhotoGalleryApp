package com.examples.photogalleryapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ImageResponse {

    @SerializedName("total")
    @Expose
    private var total: Int? = null

    @SerializedName("hits")
    @Expose
    private var imageList: List<Image>? = null


    fun getTotal(): Int? {
        return total
    }

    fun getImageList(): List<Image>? {
        return imageList
    }

}