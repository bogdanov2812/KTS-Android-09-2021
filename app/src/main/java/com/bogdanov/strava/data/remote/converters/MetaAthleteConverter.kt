package com.bogdanov.strava.data.remote.converters

import androidx.room.TypeConverter
import com.bogdanov.strava.data.remote.dto.MetaAthleteDto

class MetaAthleteConverter {

    @TypeConverter
    fun metaAthleteToLong(user: MetaAthleteDto): Long{
        return user.id
    }

    @TypeConverter
    fun longToMetaAthlete(long: Long): MetaAthleteDto {
        return MetaAthleteDto(long, 1)
    }
}