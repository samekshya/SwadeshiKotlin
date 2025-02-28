package com.example.a35b_crud.model

import android.os.Parcel
import android.os.Parcelable

data class ProductModel (
    var productId: String = "",
    var productName: String = "",
    var productDesc: String = "",
    var price: Double = 0.0, // Ensure default value
    var imageUrl: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(), // Fix: Use readDouble() instead of readInt()
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(productId)
        parcel.writeString(productName)
        parcel.writeString(productDesc)
        parcel.writeDouble(price) // Fix: Use writeDouble() instead of writeInt()
        parcel.writeString(imageUrl)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<ProductModel> {
        override fun createFromParcel(parcel: Parcel): ProductModel {
            return ProductModel(parcel)
        }

        override fun newArray(size: Int): Array<ProductModel?> {
            return arrayOfNulls(size)
        }
    }
}
