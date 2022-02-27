package com.example.myapplication
import android.content.res.AssetManager
import java.io.InputStream

// This class can be used to convert units. Also, use this to retrieve all available unit types.
public class UnitConverter(private val assets: AssetManager) {
    private val units = mutableMapOf<String, Unit>()
    val types = mutableSetOf<String>()
    val categories = mutableSetOf<String>()
    init {
        // Retrieve the list of units from the /app/assets/units.txt file
        val inputStream: InputStream = assets.open("units.txt")
        inputStream.bufferedReader().useLines { lines ->
            lines.forEach { line ->
                val values = line.split(',')
                val name = values[0]
                val category = values[1]
                val conversionFromBase = values[2].toDouble()
                addUnit(name, category, conversionFromBase)
            }
        }
    }

    fun convert(value: Double, from: String, to: String): Double {
        val unitFrom = units.getValue(from.lowercase())
        val unitTo = units.getValue(to.lowercase())
        if (unitTo == null || unitFrom == null) {
            println("Invalid Unit used. $from:$to")
            return 0.0
        }

        val baseValue = value * unitFrom.conversionFromBase
        val conversionToBase = 1.0 / unitTo.conversionFromBase
        return conversionToBase * baseValue
    }

    fun getTypes(): List<String>
    {
        return types.toList()
    }

    private fun addUnit(name: String, category: String, conversionFromBase: Double)
    {
        categories.add(category)
        types.add(name)
        units[name] = Unit(name, category, conversionFromBase)
    }

    public class Unit(
        val name: String,
        val category: String,
        var conversionFromBase: Double
    ){}
}