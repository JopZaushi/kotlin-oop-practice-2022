package lab6

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import lab2.*
import java.io.File

class Serialization {
    private val json = Json {
        prettyPrint = true
        serializersModule = SerializersModule {
            polymorphic(ColoredShape2d::class) {
                subclass(Triangle::class)
                subclass(Circle::class)
                subclass(Square::class)
                subclass(Rectangle::class)
            }
        }
    }

    fun encode(shape: MutableList<ColoredShape2d>): String {
        return (json.encodeToString(shape))
    }

    fun decode(shape: String): MutableList<ColoredShape2d> {
        return (json.decodeFromString(shape))
    }

    fun read(fileRead: String): String {
        if (!File(fileRead).exists())
            throw IllegalArgumentException("The file does not exist")
        return File(fileRead).readText()
    }

    fun write(list: String, fileWrite: String) {
        File(fileWrite).writeText(list)
    }
}


