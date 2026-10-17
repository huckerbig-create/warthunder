package com.example.data.model

enum class ChangeType(
    val labelEn: String,
    val labelAr: String
) {
    BUFF("Buff / Improvement", "تحسين / تقوية"),
    NERF("Nerf / Reduction", "تخفيض / إضعاف"),
    BUGFIX("Bug Fix", "إصلاح خطأ"),
    MECHANICS("Mechanics / Flight Model", "ميكانيكيات / نموذج طيران"),
    BR_CHANGE("Battle Rating Shift", "تعديل تصنيف المعركة")
}

enum class ConfidenceLevel(
    val labelEn: String,
    val labelAr: String,
    val isOfficial: Boolean
) {
    OFFICIAL_CONFIRMED("Official Patch Notes", "ملاحظات التحديث الرسمية", true),
    DEV_SERVER("Dev Server Testing", "خادم التطوير التجريبي", true),
    DATAMINE("Datamined Community Report", "بيانات مستخرجة غير رسمية", false),
    UNVERIFIED_COMMUNITY("Community Feedback / Unverified", "تقارير مجتمعية قيد التحقق", false)
}

data class ChangelogEntry(
    val id: String,
    val vehicleOrWeapon: String,
    val category: String,
    val nation: Nation,
    val patchVersion: String,
    val date: String,
    val changeType: ChangeType,
    val previousValue: String? = null,
    val newValue: String? = null,
    val detailsEn: String,
    val detailsAr: String,
    val sourceUrl: String = "https://warthunder.com/en/game/changelog/",
    val confidence: ConfidenceLevel = ConfidenceLevel.OFFICIAL_CONFIRMED,
    val isAiTranslated: Boolean = true
) {
    fun localizedDetails(language: AppLanguage): String =
        if (language == AppLanguage.AR) detailsAr else detailsEn
}
