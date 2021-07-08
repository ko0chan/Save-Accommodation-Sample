package com.kychan.saveaccommodation.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kychan.saveaccommodation.ui.AccommodationItem

@Entity(tableName = "accommodation_table")
data class AccommodationEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "thumbnail") val thumbnail: String,
    @ColumnInfo(name = "imagePath") val imagePath: String,
    @ColumnInfo(name = "subject") val subject: String,
    @ColumnInfo(name = "price") val price: Int,
    @ColumnInfo(name = "rate") val rate: Float
) {
    companion object {
        fun of(accommodationItem: AccommodationItem): AccommodationEntity {
            return AccommodationEntity(
                id = accommodationItem.id,
                name = accommodationItem.title,
                thumbnail = accommodationItem.thumbnail,
                imagePath = accommodationItem.description.imagePath,
                subject = accommodationItem.description.subject,
                price = accommodationItem.description.price,
                rate = accommodationItem.rate
            )
        }
    }
}