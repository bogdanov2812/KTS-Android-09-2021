package com.bogdanov.strava.models

import androidx.room.TypeConverter

class MetaAthleteConverter {

    @TypeConverter
    fun metaAthleteToLong(user: MetaAthlete): Long{
        return user.id
    }

    @TypeConverter
    fun longToMetaAthlete(long: Long): MetaAthlete{
        return MetaAthlete(long)
    }
}