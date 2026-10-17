package com.example.data.model

enum class Nation(
    val code: String,
    val nameEn: String,
    val nameAr: String,
    val flagEmoji: String
) {
    USA("usa", "USA", "الولايات المتحدة", "🇺🇸"),
    USSR("ussr", "USSR / Russia", "الاتحاد السوفيتي / روسيا", "🇷🇺"),
    GERMANY("germany", "Germany", "ألمانيا", "🇩🇪"),
    UK("uk", "Great Britain", "بريطانيا العظمى", "🇬🇧"),
    JAPAN("japan", "Japan", "اليابان", "🇯🇵"),
    CHINA("china", "China", "الصين", "🇨🇳"),
    ITALY("italy", "Italy", "إيطاليا", "🇮🇹"),
    FRANCE("france", "France", "فرنسا", "🇫🇷"),
    SWEDEN("sweden", "Sweden", "السويد", "🇸🇪"),
    ISRAEL("israel", "Israel", "إسرائيل", "🇮🇱");

    fun localizedName(language: AppLanguage): String =
        if (language == AppLanguage.AR) nameAr else nameEn
}
