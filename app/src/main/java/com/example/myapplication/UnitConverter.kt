package com.example.myapplication

public class UnitConverter() {
    private val units = mutableMapOf<String, Unit>()
    init {
        addUnit("Base", Category.Volume, 1.0)
        addUnit("Teaspoon", Category.Volume, 1.0)
        addUnit("Tablespoon", Category.Volume, 3.0)
        addUnit("Cup", Category.Volume, 48.0)
        addUnit("Ounce", Category.Volume, 6.0)
        addUnit("Apple", Category.PseudoVolume, 36.0)
    }

    fun convert(value: Double, from: String, to: String): Double {
        val unitFrom = units.getValue(from)
        val unitTo = units.getValue(to)
        if (unitTo == null || unitFrom == null) {
            println("Invalid Unit used. $from:$to")
            return 0.0
        }

        val baseValue = value * unitFrom.conversionFromBase
        val conversionToBase = 1.0 / unitTo.conversionFromBase
        return conversionToBase * baseValue
    }

    private fun addUnit(name: String, category: Category, conversionFromBase: Double)
    {
        units[name] = Unit(name, category, conversionFromBase)
    }

    enum class Category {
        Volume,
        PseudoVolume
    };

    public class Unit(
        val name: String,
        val type: Category,
        var conversionFromBase: Double
    ){}
}