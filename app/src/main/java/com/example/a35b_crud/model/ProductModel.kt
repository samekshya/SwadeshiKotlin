package com.example.a35b_crud.model

import android.os.Parcel
import android.os.Parcelable

data class ProductModel(
    var productId: String = "",
    var productName: String = "",
    var productDesc: String = "",
    var price: Double = 0.0,
    var imageUrl: Int // ✅ Fix: Change from String to Int for drawable resource
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(), // ✅ Fix: Correctly reading Double value
        parcel.readInt() // ✅ Fix: Read Int instead of String for imageUrl
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(productId)
        parcel.writeString(productName)
        parcel.writeString(productDesc)
        parcel.writeDouble(price) // ✅ Fix: Correctly writing Double value
        parcel.writeInt(imageUrl) // ✅ Fix: Write Int instead of String for imageUrl
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
