package com.example.a35b_crud.model

import android.os.Parcel
import android.os.Parcelable

data class CartItem(
    val name: String,
    val price: Double,
    val imageUrl: String?, // Made nullable in case it's missing
    val quantity: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readString(), // Read nullable string
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeDouble(price)
        parcel.writeString(imageUrl) // Write nullable string
        parcel.writeInt(quantity)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<CartItem> {
        override fun createFromParcel(parcel: Parcel): CartItem {
            return CartItem(parcel)
        }

        override fun newArray(size: Int): Array<CartItem?> {
            return arrayOfNulls(size)
        }
    }
}
