package com.example.data.model

enum class WeaponCategory(val nameEn: String, val nameAr: String) {
    MISSILE_AAM("Air-to-Air Missile (AAM)", "صاروخ جو-جو"),
    MISSILE_AGM("Air-to-Ground Missile (AGM)", "صاروخ جو-أرض"),
    MISSILE_ATGM("Anti-Tank Guided Missile (ATGM)", "صاروخ مضاد للدبابات"),
    MISSILE_SAM("Surface-to-Air Missile (SAM)", "صاروخ أرض-جو"),
    CANNON("Autocannon / Gun", "مدفع آلي / أسلحة رشاشة"),
    GUIDED_BOMB("Guided Bomb (GBU)", "قنبلة موجهة ذكية")
}

data class WeaponSpec(
    val id: String,
    val name: String,                   // Official designation (e.g. "AIM-120A AMRAAM", "R-77", "R-73")
    val arabicExplanation: String,      // Arabic explanation (e.g. "صاروخ جو-جو موجه برادار نشط مع رابط بيانات")
    val category: WeaponCategory,
    val seekerType: String,             // e.g. "Active Radar Homing (ARH) + Datalink + Inertial"
    val seekerTypeAr: String,           // e.g. "توجيه راداري نشط (ARH) مع رابط بيانات وتوجيه عطالي"
    val maxRangeKm: Double,
    val maxSpeedMach: Double,
    val maxOverloadG: Int,
    val guidanceTimeSec: Double,
    val eccmTechnology: String,         // e.g. "Track-Gate Shrinkage / Frequency Hopping"
    val eccmTechnologyAr: String,       // e.g. "تضييق نافذة التتبع والقفز الترددي ضد التشويش"
    val aspect: String,                 // "All-Aspect" / "Rear-Aspect"
    val compatibleVehicles: List<String>,
    val recentModificationsEn: List<String>,
    val recentModificationsAr: List<String>,
    val previousSpeedMach: Double? = null,
    val newSpeedMach: Double? = null,
    val previousOverloadG: Int? = null,
    val newOverloadG: Int? = null,
    val statusBadge: String = "Current Baseline",
    val sourceUrl: String = "https://warthunder.com/en/game/changelog/"
) {
    fun localizedExplanation(language: AppLanguage): String =
        if (language == AppLanguage.AR) arabicExplanation else name

    fun localizedSeeker(language: AppLanguage): String =
        if (language == AppLanguage.AR) seekerTypeAr else seekerType

    fun localizedEccm(language: AppLanguage): String =
        if (language == AppLanguage.AR) eccmTechnologyAr else eccmTechnology

    fun localizedModifications(language: AppLanguage): List<String> =
        if (language == AppLanguage.AR) recentModificationsAr else recentModificationsEn
}
