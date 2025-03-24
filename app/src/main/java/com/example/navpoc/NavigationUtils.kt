package com.example.navpoc

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.KType
import kotlin.reflect.typeOf

inline fun <reified T : Any> serializableType(
    allowNullable: Boolean = false,
) = object : NavType<T>(isNullableAllowed = allowNullable) {
    override fun get(bundle: Bundle, key: String): T?  {
       return Json.decodeFromString(bundle.getString(key) ?: return null)
    }

    override fun parseValue(value: String): T = Json.decodeFromString(Uri.decode(value))

    override fun serializeAsValue(value: T): String {
        return Uri.encode(Json.encodeToString(value))
    }

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putString(key, Json.encodeToString(value))
    }
}

inline fun <reified T : Any> navTypeSerializable(): Map<KType, NavType<T>> {
    return mapOf(typeOf<T>() to serializableType<T>())
}
