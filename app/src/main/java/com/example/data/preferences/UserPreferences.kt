package com.example.data.preferences

import android.content.Context
import android.content.SharedPreferences
import com.example.data.model.AppLanguage

class UserPreferences(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("wt_intel_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_LANGUAGE = "key_app_language"
        private const val KEY_FOLLOWED_VEHICLES = "key_followed_vehicles"
        private const val KEY_FAVORITE_VEHICLES = "key_favorite_vehicles"
        private const val KEY_NOTIF_MAJOR_UPDATES = "key_notif_major_updates"
        private const val KEY_NOTIF_BR_CHANGES = "key_notif_br_changes"
        private const val KEY_NOTIF_MISSILE_TWEAKS = "key_notif_missile_tweaks"
        private const val KEY_NOTIF_FOLLOWED = "key_notif_followed"
    }

    var language: AppLanguage
        get() {
            val code = prefs.getString(KEY_LANGUAGE, AppLanguage.EN.code) ?: AppLanguage.EN.code
            return if (code == AppLanguage.AR.code) AppLanguage.AR else AppLanguage.EN
        }
        set(value) {
            prefs.edit().putString(KEY_LANGUAGE, value.code).apply()
        }

    fun getFollowedVehicleIds(): Set<String> {
        return prefs.getStringSet(KEY_FOLLOWED_VEHICLES, setOf("jas39c_gripen", "f16c_fighting_falcon", "t90m_proryv")) ?: emptySet()
    }

    fun setVehicleFollowed(vehicleId: String, followed: Boolean) {
        val current = getFollowedVehicleIds().toMutableSet()
        if (followed) current.add(vehicleId) else current.remove(vehicleId)
        prefs.edit().putStringSet(KEY_FOLLOWED_VEHICLES, current).apply()
    }

    fun isVehicleFollowed(vehicleId: String): Boolean {
        return getFollowedVehicleIds().contains(vehicleId)
    }

    fun getFavoriteVehicleIds(): Set<String> {
        return prefs.getStringSet(KEY_FAVORITE_VEHICLES, setOf("jas39c_gripen", "leopard_2a7v")) ?: emptySet()
    }

    fun setVehicleFavorite(vehicleId: String, favorite: Boolean) {
        val current = getFavoriteVehicleIds().toMutableSet()
        if (favorite) current.add(vehicleId) else current.remove(vehicleId)
        prefs.edit().putStringSet(KEY_FAVORITE_VEHICLES, current).apply()
    }

    fun isVehicleFavorite(vehicleId: String): Boolean {
        return getFavoriteVehicleIds().contains(vehicleId)
    }

    var notifyMajorUpdates: Boolean
        get() = prefs.getBoolean(KEY_NOTIF_MAJOR_UPDATES, true)
        set(value) = prefs.edit().putBoolean(KEY_NOTIF_MAJOR_UPDATES, value).apply()

    var notifyBrChanges: Boolean
        get() = prefs.getBoolean(KEY_NOTIF_BR_CHANGES, true)
        set(value) = prefs.edit().putBoolean(KEY_NOTIF_BR_CHANGES, value).apply()

    var notifyMissileTweaks: Boolean
        get() = prefs.getBoolean(KEY_NOTIF_MISSILE_TWEAKS, true)
        set(value) = prefs.edit().putBoolean(KEY_NOTIF_MISSILE_TWEAKS, value).apply()

    var notifyFollowedVehicles: Boolean
        get() = prefs.getBoolean(KEY_NOTIF_FOLLOWED, true)
        set(value) = prefs.edit().putBoolean(KEY_NOTIF_FOLLOWED, value).apply()
}
