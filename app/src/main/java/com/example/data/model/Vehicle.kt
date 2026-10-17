package com.example.data.model

enum class VehicleCategory(val nameEn: String, val nameAr: String) {
    AIRCRAFT("Aircraft", "طائرات"),
    GROUND("Ground Vehicles", "مركبات برية")
}

enum class VehicleType(
    val category: VehicleCategory,
    val nameEn: String,
    val nameAr: String
) {
    // Aircraft
    JET_FIGHTER(VehicleCategory.AIRCRAFT, "Jet Fighter / Air Superiority", "مقاتلة نفاثة / تفوق جوي"),
    STRIKE_AIRCRAFT(VehicleCategory.AIRCRAFT, "Strike Aircraft", "طائرة هجوم أرضي"),
    INTERCEPTOR(VehicleCategory.AIRCRAFT, "Interceptor", "طائرة اعتراضية"),
    BOMBER(VehicleCategory.AIRCRAFT, "Bomber", "قاذفة قنابل"),
    HELICOPTER(VehicleCategory.AIRCRAFT, "Attack Helicopter", "مروحية هجومية"),

    // Ground
    MBT(VehicleCategory.GROUND, "Main Battle Tank (MBT)", "دبابة قتال رئيسية"),
    LIGHT_TANK(VehicleCategory.GROUND, "Light Tank / Recon", "دبابة خفيفة / استطلاع"),
    SPAA_SAM(VehicleCategory.GROUND, "SPAA / SAM System", "دفاع جوي ذاتي الحركة / صواريخ"),
    TANK_DESTROYER(VehicleCategory.GROUND, "Tank Destroyer / ATGM", "صائدة دبابات / صواريخ موجهة"),
    HEAVY_TANK(VehicleCategory.GROUND, "Heavy Tank", "دبابة ثقيلة")
}

data class VehicleChange(
    val patchVersion: String,
    val date: String,
    val summaryEn: String,
    val summaryAr: String,
    val type: ChangeType,
    val previousValue: String? = null,
    val newValue: String? = null,
    val sourceUrl: String = "https://warthunder.com/en/game/changelog/"
)

data class Vehicle(
    val id: String,
    val name: String,                    // Official designation (e.g. "JAS39C Gripen")
    val roleArExplanation: String,       // Arabic role explanation (e.g. "مقاتلة سويدية متقدمة متعددة المهام بجيل 4.5")
    val category: VehicleCategory,
    val type: VehicleType,
    val nation: Nation,
    val rank: Int,                       // 1 to 8
    val brArcade: Double,
    val brRealistic: Double,
    val brSimulator: Double,
    val maxSpeed: String,                // e.g. "Mach 2.0 (2,470 km/h)" or "70 km/h"
    val engineAndMobility: String,       // e.g. "Volvo RM12 turbofan (80.5 kN with reheat)" or "1500 hp MTU diesel"
    val radarAndSensors: String,         // e.g. "PS-05/A Pulse-Doppler, TWS, HMD, RWR Type 136"
    val opticsAndThermal: String,        // e.g. "Gen 3 CITV & Gunner Thermal, LWS/APS"
    val countermeasuresAndArmor: String, // e.g. "360 Countermeasures (Chaff/Flares) + BOL pods" or "Volumetric Composite + Spall Liner"
    val weaponsAndAmmunition: List<String>,
    val recentChanges: List<VehicleChange> = emptyList(),
    val wikiUrl: String = "https://wiki.warthunder.com/",
    val changelogUrl: String = "https://warthunder.com/en/game/changelog/",
    val overviewEn: String,
    val overviewAr: String,
    val isTopTier: Boolean = false,
    val isFollowed: Boolean = false,
    val isFavorite: Boolean = false
) {
    fun localizedRole(language: AppLanguage): String =
        if (language == AppLanguage.AR) roleArExplanation else type.nameEn

    fun localizedOverview(language: AppLanguage): String =
        if (language == AppLanguage.AR) overviewAr else overviewEn
}
