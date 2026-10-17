package com.example.data.repository

import android.content.Context
import com.example.data.model.*
import com.example.data.preferences.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class WarThunderRepository(context: Context) {
    private val preferences = UserPreferences(context)
    private val rssFetcher = WarThunderRssFetcher()

    private val _currentLanguage = MutableStateFlow(preferences.language)
    val currentLanguage: StateFlow<AppLanguage> = _currentLanguage.asStateFlow()

    private val _vehicles = MutableStateFlow<List<Vehicle>>(emptyList())
    val vehicles: StateFlow<List<Vehicle>> = _vehicles.asStateFlow()

    private val _weapons = MutableStateFlow<List<WeaponSpec>>(WarThunderInitialData.weapons)
    val weapons: StateFlow<List<WeaponSpec>> = _weapons.asStateFlow()

    private val _changelog = MutableStateFlow<List<ChangelogEntry>>(WarThunderInitialData.changelogEntries)
    val changelog: StateFlow<List<ChangelogEntry>> = _changelog.asStateFlow()

    private val _brShifts = MutableStateFlow<List<BRShift>>(WarThunderInitialData.brShifts)
    val brShifts: StateFlow<List<BRShift>> = _brShifts.asStateFlow()

    private val _news = MutableStateFlow<List<NewsArticle>>(WarThunderInitialData.initialNews)
    val news: StateFlow<List<NewsArticle>> = _news.asStateFlow()

    private val _notifications = MutableStateFlow<List<NotificationItem>>(WarThunderInitialData.notifications)
    val notifications: StateFlow<List<NotificationItem>> = _notifications.asStateFlow()

    private val _isSyncing = MutableStateFlow(false)
    val isSyncing: StateFlow<Boolean> = _isSyncing.asStateFlow()

    private val _lastSyncTime = MutableStateFlow(formatCurrentTime())
    val lastSyncTime: StateFlow<String> = _lastSyncTime.asStateFlow()

    private val _syncMessage = MutableStateFlow<String?>(null)
    val syncMessage: StateFlow<String?> = _syncMessage.asStateFlow()

    init {
        loadVehiclesWithPreferences()
    }

    private fun loadVehiclesWithPreferences() {
        val followed = preferences.getFollowedVehicleIds()
        val favorites = preferences.getFavoriteVehicleIds()

        val updated = WarThunderInitialData.vehicles.map { v ->
            v.copy(
                isFollowed = followed.contains(v.id),
                isFavorite = favorites.contains(v.id)
            )
        }
        _vehicles.value = updated
    }

    fun setLanguage(language: AppLanguage) {
        preferences.language = language
        _currentLanguage.value = language
    }

    fun toggleLanguage() {
        val newLang = if (_currentLanguage.value == AppLanguage.EN) AppLanguage.AR else AppLanguage.EN
        setLanguage(newLang)
    }

    fun toggleFollowVehicle(vehicleId: String) {
        val currentFollowed = preferences.isVehicleFollowed(vehicleId)
        val newFollowed = !currentFollowed
        preferences.setVehicleFollowed(vehicleId, newFollowed)

        _vehicles.value = _vehicles.value.map { v ->
            if (v.id == vehicleId) v.copy(isFollowed = newFollowed) else v
        }

        // Add an immediate notification if followed
        if (newFollowed) {
            val v = _vehicles.value.find { it.id == vehicleId }
            if (v != null) {
                val notif = NotificationItem(
                    id = "follow_${System.currentTimeMillis()}",
                    titleEn = "Now Tracking: ${v.name}",
                    titleAr = "تتم الآن متابعة: ${v.name}",
                    messageEn = "You will receive immediate telemetry and BR change alerts for ${v.name}.",
                    messageAr = "ستصلك إشعارات وتنبيهات التعديلات فور صدورها لطائرة/دبابة ${v.name}.",
                    timestamp = "Just now",
                    type = ChangeType.BUFF,
                    vehicleId = v.id,
                    isRead = false
                )
                _notifications.value = listOf(notif) + _notifications.value
            }
        }
    }

    fun toggleFavoriteVehicle(vehicleId: String) {
        val currentFavorite = preferences.isVehicleFavorite(vehicleId)
        val newFavorite = !currentFavorite
        preferences.setVehicleFavorite(vehicleId, newFavorite)

        _vehicles.value = _vehicles.value.map { v ->
            if (v.id == vehicleId) v.copy(isFavorite = newFavorite) else v
        }
    }

    suspend fun syncData() {
        _isSyncing.value = true
        _syncMessage.value = null

        val result = rssFetcher.fetchLiveNews()
        if (result.isSuccess) {
            val liveArticles = result.getOrNull() ?: emptyList()
            if (liveArticles.isNotEmpty()) {
                // Combine and deduplicate
                val existingIds = liveArticles.map { it.titleEn }.toSet()
                val keptInitial = WarThunderInitialData.initialNews.filterNot { existingIds.contains(it.titleEn) }
                _news.value = (liveArticles + keptInitial).take(20)
                _syncMessage.value = "Synced ${liveArticles.size} live articles"
            }
        } else {
            _syncMessage.value = "Offline cache in use (Verified offline database)"
        }

        _lastSyncTime.value = formatCurrentTime()
        _isSyncing.value = false
    }

    fun markAllNotificationsRead() {
        _notifications.value = _notifications.value.map { it.copy(isRead = true) }
    }

    // Preferences accessors
    var notifyMajorUpdates: Boolean
        get() = preferences.notifyMajorUpdates
        set(v) { preferences.notifyMajorUpdates = v }

    var notifyBrChanges: Boolean
        get() = preferences.notifyBrChanges
        set(v) { preferences.notifyBrChanges = v }

    var notifyMissileTweaks: Boolean
        get() = preferences.notifyMissileTweaks
        set(v) { preferences.notifyMissileTweaks = v }

    var notifyFollowedVehicles: Boolean
        get() = preferences.notifyFollowedVehicles
        set(v) { preferences.notifyFollowedVehicles = v }

    private fun formatCurrentTime(): String {
        return SimpleDateFormat("HH:mm - dd MMM", Locale.US).format(Date())
    }
}
