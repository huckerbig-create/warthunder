package com.example.data.model

data class BRShift(
    val id: String,
    val vehicleName: String,
    val vehicleType: String,
    val nation: Nation,
    val patchVersion: String,
    val date: String,
    val oldAB: Double,
    val newAB: Double,
    val oldRB: Double,
    val newRB: Double,
    val oldSB: Double,
    val newSB: Double,
    val reasonEn: String,
    val reasonAr: String,
    val sourceUrl: String = "https://warthunder.com/en/game/changelog/"
) {
    val isNetIncrease: Boolean = (newRB > oldRB) || (newAB > oldAB)
    val isNetDecrease: Boolean = (newRB < oldRB) || (newAB < oldAB)

    fun localizedReason(language: AppLanguage): String =
        if (language == AppLanguage.AR) reasonAr else reasonEn
}
