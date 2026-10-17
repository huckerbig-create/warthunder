package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.*
import com.example.data.repository.WarThunderRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

enum class MainTab {
    DASHBOARD,
    AIRCRAFT,
    TANKS,
    ARMORY,
    CHANGELOG,
    WATCHLIST,
    SETTINGS
}

data class VehicleFilterState(
    val query: String = "",
    val selectedNation: Nation? = null,
    val selectedRank: Int? = null,
    val minBr: Double = 1.0,
    val maxBr: Double = 14.3,
    val onlyTopTier: Boolean = false
)

data class ChangelogFilterState(
    val query: String = "",
    val selectedType: ChangeType? = null,
    val selectedNation: Nation? = null,
    val selectedConfidence: ConfidenceLevel? = null
)

class WarThunderViewModel(application: Application) : AndroidViewModel(application) {
    val repository = WarThunderRepository(application.applicationContext)

    val currentLanguage: StateFlow<AppLanguage> = repository.currentLanguage
    val vehicles: StateFlow<List<Vehicle>> = repository.vehicles
    val weapons: StateFlow<List<WeaponSpec>> = repository.weapons
    val changelog: StateFlow<List<ChangelogEntry>> = repository.changelog
    val brShifts: StateFlow<List<BRShift>> = repository.brShifts
    val news: StateFlow<List<NewsArticle>> = repository.news
    val notifications: StateFlow<List<NotificationItem>> = repository.notifications
    val isSyncing: StateFlow<Boolean> = repository.isSyncing
    val lastSyncTime: StateFlow<String> = repository.lastSyncTime
    val syncMessage: StateFlow<String?> = repository.syncMessage

    private val _selectedTab = MutableStateFlow(MainTab.DASHBOARD)
    val selectedTab: StateFlow<MainTab> = _selectedTab.asStateFlow()

    private val _vehicleFilter = MutableStateFlow(VehicleFilterState())
    val vehicleFilter: StateFlow<VehicleFilterState> = _vehicleFilter.asStateFlow()

    private val _changelogFilter = MutableStateFlow(ChangelogFilterState())
    val changelogFilter: StateFlow<ChangelogFilterState> = _changelogFilter.asStateFlow()

    private val _selectedVehicle = MutableStateFlow<Vehicle?>(null)
    val selectedVehicle: StateFlow<Vehicle?> = _selectedVehicle.asStateFlow()

    private val _selectedWeapon = MutableStateFlow<WeaponSpec?>(null)
    val selectedWeapon: StateFlow<WeaponSpec?> = _selectedWeapon.asStateFlow()

    init {
        // Trigger initial network sync
        viewModelScope.launch {
            repository.syncData()
        }
    }

    fun selectTab(tab: MainTab) {
        _selectedTab.value = tab
    }

    fun setLanguage(lang: AppLanguage) {
        repository.setLanguage(lang)
    }

    fun toggleLanguage() {
        repository.toggleLanguage()
    }

    fun selectVehicle(vehicle: Vehicle?) {
        _selectedVehicle.value = vehicle
    }

    fun selectWeapon(weapon: WeaponSpec?) {
        _selectedWeapon.value = weapon
    }

    fun toggleFollowVehicle(vehicleId: String) {
        repository.toggleFollowVehicle(vehicleId)
        // Refresh selected vehicle if open
        _selectedVehicle.value?.let { current ->
            if (current.id == vehicleId) {
                _selectedVehicle.value = current.copy(isFollowed = !current.isFollowed)
            }
        }
    }

    fun toggleFavoriteVehicle(vehicleId: String) {
        repository.toggleFavoriteVehicle(vehicleId)
        _selectedVehicle.value?.let { current ->
            if (current.id == vehicleId) {
                _selectedVehicle.value = current.copy(isFavorite = !current.isFavorite)
            }
        }
    }

    fun updateVehicleQuery(query: String) {
        _vehicleFilter.value = _vehicleFilter.value.copy(query = query)
    }

    fun setVehicleNation(nation: Nation?) {
        _vehicleFilter.value = _vehicleFilter.value.copy(selectedNation = nation)
    }

    fun setVehicleRank(rank: Int?) {
        _vehicleFilter.value = _vehicleFilter.value.copy(selectedRank = rank)
    }

    fun setVehicleTopTierOnly(onlyTop: Boolean) {
        _vehicleFilter.value = _vehicleFilter.value.copy(onlyTopTier = onlyTop)
    }

    fun resetVehicleFilters() {
        _vehicleFilter.value = VehicleFilterState()
    }

    fun updateChangelogQuery(query: String) {
        _changelogFilter.value = _changelogFilter.value.copy(query = query)
    }

    fun setChangelogChangeType(type: ChangeType?) {
        _changelogFilter.value = _changelogFilter.value.copy(selectedType = type)
    }

    fun refreshData() {
        viewModelScope.launch {
            repository.syncData()
        }
    }

    fun markNotificationsRead() {
        repository.markAllNotificationsRead()
    }
}
