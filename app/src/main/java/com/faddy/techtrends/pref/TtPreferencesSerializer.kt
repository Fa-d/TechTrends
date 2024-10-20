package com.faddy.techtrends.pref

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.faddy.techtrends.datastore.TtPref
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class TtPreferencesSerializer @Inject constructor() : Serializer<TtPref> {
    override val defaultValue: TtPref = TtPref.getDefaultInstance()
    override suspend fun readFrom(input: InputStream): TtPref {
        try {
            return TtPref.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: TtPref, output: OutputStream) = t.writeTo(output)
}