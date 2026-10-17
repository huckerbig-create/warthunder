package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.model.AppLanguage
import com.example.data.model.VehicleCategory
import com.example.ui.MainTab
import com.example.ui.WarThunderViewModel
import com.example.ui.components.TopAppBarWithLanguage
import com.example.ui.localization.AppStrings
import com.example.ui.screens.*
import com.example.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                WarThunderApp()
            }
        }
    }
}

data class NavItem(
    val tab: MainTab,
    val icon: ImageVector,
    val getLabel: (AppLanguage) -> String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WarThunderApp(viewModel: WarThunderViewModel = viewModel()) {
    val currentLanguage by viewModel.currentLanguage.collectAsStateWithLifecycle()
    val selectedTab by viewModel.selectedTab.collectAsStateWithLifecycle()
    val vehicles by viewModel.vehicles.collectAsStateWithLifecycle()
    val weapons by viewModel.weapons.collectAsStateWithLifecycle()
    val changelog by viewModel.changelog.collectAsStateWithLifecycle()
    val brShifts by viewModel.brShifts.collectAsStateWithLifecycle()
    val news by viewModel.news.collectAsStateWithLifecycle()
    val notifications by viewModel.notifications.collectAsStateWithLifecycle()
    val isSyncing by viewModel.isSyncing.collectAsStateWithLifecycle()
    val lastSyncTime by viewModel.lastSyncTime.collectAsStateWithLifecycle()
    val syncMessage by viewModel.syncMessage.collectAsStateWithLifecycle()
    val vehicleFilter by viewModel.vehicleFilter.collectAsStateWithLifecycle()
    val selectedVehicle by viewModel.selectedVehicle.collectAsStateWithLifecycle()

    val unreadCount = remember(notifications) {
        notifications.count { !it.isRead }
    }

    // Set correct LayoutDirection: RTL for Arabic, LTR for English
    val layoutDirection = if (currentLanguage == AppLanguage.AR) LayoutDirection.Rtl else LayoutDirection.Ltr

    // Handle Back Press
    BackHandler(enabled = selectedVehicle != null || selectedTab != MainTab.DASHBOARD) {
        if (selectedVehicle != null) {
            viewModel.selectVehicle(null)
        } else if (selectedTab != MainTab.DASHBOARD) {
            viewModel.selectTab(MainTab.DASHBOARD)
        }
    }

    val navItems = listOf(
        NavItem(MainTab.DASHBOARD, Icons.Default.Dashboard, AppStrings::tabDashboard),
        NavItem(MainTab.AIRCRAFT, Icons.Default.Flight, AppStrings::tabAircraft),
        NavItem(MainTab.TANKS, Icons.Default.Shield, AppStrings::tabTanks),
        NavItem(MainTab.ARMORY, Icons.Default.RocketLaunch, AppStrings::tabArmory),
        NavItem(MainTab.CHANGELOG, Icons.Default.History, AppStrings::tabChangelog),
        NavItem(MainTab.WATCHLIST, Icons.Default.Star, AppStrings::tabWatchlist),
        NavItem(MainTab.SETTINGS, Icons.Default.Settings, AppStrings::tabSettings)
    )

    CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
        BoxWithConstraints(modifier = Modifier.fillMaxSize().background(CarbonDark)) {
            val isExpanded = maxWidth >= 600.dp

            if (isExpanded) {
                // Adaptive layout with side NavigationRail for tablets & foldables
                Row(modifier = Modifier.fillMaxSize()) {
                    NavigationRail(
                        containerColor = CarbonSurface,
                        contentColor = TextPrimary,
                        modifier = Modifier
                            .fillMaxHeight()
                            .border(width = 0.5.dp, color = TacticalBorder)
                    ) {
                        Spacer(modifier = Modifier.height(16.dp))
                        navItems.forEach { item ->
                            NavigationRailItem(
                                selected = selectedTab == item.tab,
                                onClick = { viewModel.selectTab(item.tab) },
                                icon = {
                                    Icon(
                                        imageVector = item.icon,
                                        contentDescription = item.getLabel(currentLanguage)
                                    )
                                },
                                label = {
                                    Text(
                                        text = item.getLabel(currentLanguage),
                                        fontSize = 10.sp
                                    )
                                },
                                colors = NavigationRailItemDefaults.colors(
                                    selectedIconColor = CarbonDark,
                                    selectedTextColor = TacticalAmber,
                                    indicatorColor = TacticalAmber,
                                    unselectedIconColor = TextSecondary,
                                    unselectedTextColor = TextSecondary
                                )
                            )
                        }
                    }

                    Scaffold(
                        topBar = {
                            TopAppBarWithLanguage(
                                currentLanguage = currentLanguage,
                                isSyncing = isSyncing,
                                unreadNotificationsCount = unreadCount,
                                onToggleLanguage = { viewModel.toggleLanguage() },
                                onSyncClick = { viewModel.refreshData() },
                                onNotificationsClick = { viewModel.selectTab(MainTab.WATCHLIST) }
                            )
                        },
                        containerColor = CarbonDark
                    ) { innerPadding ->
                        MainContent(
                            selectedTab = selectedTab,
                            currentLanguage = currentLanguage,
                            viewModel = viewModel,
                            vehicles = vehicles,
                            weapons = weapons,
                            changelog = changelog,
                            brShifts = brShifts,
                            news = news,
                            notifications = notifications,
                            lastSyncTime = lastSyncTime,
                            syncMessage = syncMessage,
                            isSyncing = isSyncing,
                            vehicleFilter = vehicleFilter,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            } else {
                // Mobile layout with standard M3 bottom NavigationBar
                Scaffold(
                    topBar = {
                        TopAppBarWithLanguage(
                            currentLanguage = currentLanguage,
                            isSyncing = isSyncing,
                            unreadNotificationsCount = unreadCount,
                            onToggleLanguage = { viewModel.toggleLanguage() },
                            onSyncClick = { viewModel.refreshData() },
                            onNotificationsClick = { viewModel.selectTab(MainTab.WATCHLIST) }
                        )
                    },
                    bottomBar = {
                        NavigationBar(
                            containerColor = CarbonSurface,
                            tonalElevation = 4.dp,
                            modifier = Modifier
                                .border(width = 0.5.dp, color = TacticalBorder)
                                .testTag("bottom_nav_bar")
                        ) {
                            navItems.forEach { item ->
                                NavigationBarItem(
                                    selected = selectedTab == item.tab,
                                    onClick = { viewModel.selectTab(item.tab) },
                                    icon = {
                                        Icon(
                                            imageVector = item.icon,
                                            contentDescription = item.getLabel(currentLanguage)
                                        )
                                    },
                                    label = {
                                        Text(
                                            text = item.getLabel(currentLanguage),
                                            fontSize = 9.sp,
                                            maxLines = 1
                                        )
                                    },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = CarbonDark,
                                        selectedTextColor = TacticalAmber,
                                        indicatorColor = TacticalAmber,
                                        unselectedIconColor = TextSecondary,
                                        unselectedTextColor = TextSecondary
                                    )
                                )
                            }
                        }
                    },
                    containerColor = CarbonDark
                ) { innerPadding ->
                    MainContent(
                        selectedTab = selectedTab,
                        currentLanguage = currentLanguage,
                        viewModel = viewModel,
                        vehicles = vehicles,
                        weapons = weapons,
                        changelog = changelog,
                        brShifts = brShifts,
                        news = news,
                        notifications = notifications,
                        lastSyncTime = lastSyncTime,
                        syncMessage = syncMessage,
                        isSyncing = isSyncing,
                        vehicleFilter = vehicleFilter,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }

            // Modal Bottom Sheet for Vehicle Details
            selectedVehicle?.let { vehicle ->
                VehicleDetailModal(
                    vehicle = vehicle,
                    currentLanguage = currentLanguage,
                    onDismiss = { viewModel.selectVehicle(null) },
                    onToggleFollow = { viewModel.toggleFollowVehicle(vehicle.id) },
                    onToggleFavorite = { viewModel.toggleFavoriteVehicle(vehicle.id) }
                )
            }
        }
    }
}

@Composable
fun MainContent(
    selectedTab: MainTab,
    currentLanguage: AppLanguage,
    viewModel: WarThunderViewModel,
    vehicles: List<com.example.data.model.Vehicle>,
    weapons: List<com.example.data.model.WeaponSpec>,
    changelog: List<com.example.data.model.ChangelogEntry>,
    brShifts: List<com.example.data.model.BRShift>,
    news: List<com.example.data.model.NewsArticle>,
    notifications: List<com.example.data.model.NotificationItem>,
    lastSyncTime: String,
    syncMessage: String?,
    isSyncing: Boolean,
    vehicleFilter: com.example.ui.VehicleFilterState,
    modifier: Modifier = Modifier
) {
    val followedVehicles = remember(vehicles) { vehicles.filter { it.isFollowed } }
    val favoriteVehicles = remember(vehicles) { vehicles.filter { it.isFavorite } }

    Box(modifier = modifier.fillMaxSize()) {
        when (selectedTab) {
            MainTab.DASHBOARD -> DashboardScreen(
                currentLanguage = currentLanguage,
                news = news,
                followedVehicles = followedVehicles,
                brShifts = brShifts,
                lastSyncTime = lastSyncTime,
                onNavigateTab = { viewModel.selectTab(it) },
                onSelectVehicle = { viewModel.selectVehicle(it) }
            )
            MainTab.AIRCRAFT -> VehiclesScreen(
                currentLanguage = currentLanguage,
                category = VehicleCategory.AIRCRAFT,
                vehicles = vehicles,
                filterState = vehicleFilter,
                onUpdateQuery = { viewModel.updateVehicleQuery(it) },
                onSetNation = { viewModel.setVehicleNation(it) },
                onSetRank = { viewModel.setVehicleRank(it) },
                onSetTopTierOnly = { viewModel.setVehicleTopTierOnly(it) },
                onResetFilters = { viewModel.resetVehicleFilters() },
                onToggleFollow = { viewModel.toggleFollowVehicle(it) },
                onToggleFavorite = { viewModel.toggleFavoriteVehicle(it) },
                onSelectVehicle = { viewModel.selectVehicle(it) }
            )
            MainTab.TANKS -> VehiclesScreen(
                currentLanguage = currentLanguage,
                category = VehicleCategory.GROUND,
                vehicles = vehicles,
                filterState = vehicleFilter,
                onUpdateQuery = { viewModel.updateVehicleQuery(it) },
                onSetNation = { viewModel.setVehicleNation(it) },
                onSetRank = { viewModel.setVehicleRank(it) },
                onSetTopTierOnly = { viewModel.setVehicleTopTierOnly(it) },
                onResetFilters = { viewModel.resetVehicleFilters() },
                onToggleFollow = { viewModel.toggleFollowVehicle(it) },
                onToggleFavorite = { viewModel.toggleFavoriteVehicle(it) },
                onSelectVehicle = { viewModel.selectVehicle(it) }
            )
            MainTab.ARMORY -> ArmoryScreen(
                currentLanguage = currentLanguage,
                weapons = weapons,
                onSelectWeapon = { viewModel.selectWeapon(it) }
            )
            MainTab.CHANGELOG -> ChangelogScreen(
                currentLanguage = currentLanguage,
                changelogEntries = changelog,
                brShifts = brShifts
            )
            MainTab.WATCHLIST -> WatchlistScreen(
                currentLanguage = currentLanguage,
                followedVehicles = followedVehicles,
                favoriteVehicles = favoriteVehicles,
                notifications = notifications,
                onSelectVehicle = { viewModel.selectVehicle(it) },
                onToggleFollow = { viewModel.toggleFollowVehicle(it) },
                onToggleFavorite = { viewModel.toggleFavoriteVehicle(it) },
                onMarkAllRead = { viewModel.markNotificationsRead() }
            )
            MainTab.SETTINGS -> SettingsScreen(
                currentLanguage = currentLanguage,
                repository = viewModel.repository,
                lastSyncTime = lastSyncTime,
                syncMessage = syncMessage,
                isSyncing = isSyncing,
                onSetLanguage = { viewModel.setLanguage(it) },
                onSyncNow = { viewModel.refreshData() }
            )
        }
    }
}
