package com.example.a35b_crud.model

import android.os.Parcel
import android.os.Parcelable

data class CartItem(
    val name: String,
    val price: Double,
    val imageUrl: Int, // ✅ Ensure imageUrl is an Int
    val quantity: Int,
    val totalPrice: Double = price * quantity
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readInt(), // ✅ Read Int instead of String
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeDouble(price)
        parcel.writeInt(imageUrl) // ✅ Write Int instead of String
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
