package com.ojanbelajar.suitmediatest.utils


import com.ojanbelajar.suitmediatest.data.local.entity.GuestEntity
import com.ojanbelajar.suitmediatest.data.remote.Guest
import com.ojanbelajar.suitmediatest.data.remote.ListGuestResponse


object DataMapper {
    fun mapResponsesToEntities(input: List<ListGuestResponse>): List<GuestEntity> {
        val guestList = ArrayList<GuestEntity>()
        input.map {
            val guest = GuestEntity(
                id = it.id,
                name = it.name,
                birthdate = it.birthdate

            )
            guestList.add(guest)
        }
        return guestList
    }

    fun mapEntitiesToDomain(input: List<GuestEntity>): List<Guest> =
        input.map {
            Guest(
                id = it.id,
                name = it.name,
                birthdate = it.birthdate

            )
        }


}