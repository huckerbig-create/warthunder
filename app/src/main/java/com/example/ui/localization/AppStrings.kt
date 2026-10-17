package com.example.ui.localization

import com.example.data.model.AppLanguage

object AppStrings {
    // App Title & Tagline
    fun appTitle(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "War Thunder Intel"
        AppLanguage.AR -> "استخبارات وور ثاندر"
    }

    fun appSubtitle(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Combat Vehicle, Missiles & Balance Tracker"
        AppLanguage.AR -> "منصة تتبع الطائرات، الدبابات، الصواريخ وتحديثات التوازن"
    }

    // Navigation Tabs
    fun tabDashboard(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Briefing"
        AppLanguage.AR -> "الإيجاز"
    }

    fun tabAircraft(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Aircraft"
        AppLanguage.AR -> "الطائرات"
    }

    fun tabTanks(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Tanks"
        AppLanguage.AR -> "المدرعات"
    }

    fun tabArmory(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Armory"
        AppLanguage.AR -> "الترسانة"
    }

    fun tabChangelog(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Changelog"
        AppLanguage.AR -> "التحديثات"
    }

    fun tabWatchlist(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Watchlist"
        AppLanguage.AR -> "المراقبة"
    }

    fun tabSettings(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Settings"
        AppLanguage.AR -> "الإعدادات"
    }

    // Common Actions
    fun search(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Search vehicles, weapons, patches..."
        AppLanguage.AR -> "ابحث عن طائرة، دبابة، سلاح، تحديث..."
    }

    fun filter(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Filter"
        AppLanguage.AR -> "تصفية"
    }

    fun all(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "All"
        AppLanguage.AR -> "الكل"
    }

    fun rank(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Rank"
        AppLanguage.AR -> "الرتبة"
    }

    fun nation(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Nation"
        AppLanguage.AR -> "الدولة"
    }

    fun battleRating(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Battle Rating (BR)"
        AppLanguage.AR -> "تصنيف المعركة (BR)"
    }

    fun syncNow(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Sync Feeds"
        AppLanguage.AR -> "مزامنة البيانات"
    }

    fun syncing(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Syncing live feeds..."
        AppLanguage.AR -> "جاري جلب آخر التحديثات..."
    }

    fun lastSync(lang: AppLanguage, timeStr: String) = when (lang) {
        AppLanguage.EN -> "Last synced: $timeStr"
        AppLanguage.AR -> "آخر مزامنة: $timeStr"
    }

    fun officialSource(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Official Source"
        AppLanguage.AR -> "المصدر الرسمي"
    }

    fun openSourceLink(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Open Official Link"
        AppLanguage.AR -> "فتح المصدر الرسمي"
    }

    fun viewOriginalEnglish(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Show English Source"
        AppLanguage.AR -> "عرض النص الإنجليزي الأصلي"
    }

    fun viewArabicTranslation(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Show Arabic Translation"
        AppLanguage.AR -> "عرض الترجمة العربية"
    }

    fun aiTranslationBadge(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "AI-Assisted Translation"
        AppLanguage.AR -> "ترجمة مساعدة بالذكاء الاصطناعي"
    }

    fun officialStatementBadge(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Official Gaijin Note"
        AppLanguage.AR -> "بيان رسمي من جايجين"
    }

    // Vehicle Detail Labels
    fun technicalSpecs(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Tactical Telemetry & Specs"
        AppLanguage.AR -> "المواصفات والقياسات التكتيكية"
    }

    fun flightCharacteristics(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Flight Characteristics & Engine"
        AppLanguage.AR -> "خصائص الطيران والمحرك"
    }

    fun armorProtection(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Armor Protection & Mobility"
        AppLanguage.AR -> "تدريع الحماية والحركية"
    }

    fun radarAndAvionics(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Radar, Avionics & RWR"
        AppLanguage.AR -> "الرادار وإلكترونيات الطيران وإنذار الرادار"
    }

    fun targetingAndOptics(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Thermals, Optics & APS"
        AppLanguage.AR -> "المناظير الحرارية ونظام الحماية النشطة"
    }

    fun countermeasures(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Countermeasures & Defense"
        AppLanguage.AR -> "وسائل التشويش والمقذوفات الحرارية"
    }

    fun armament(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Armament & Weapon Loadouts"
        AppLanguage.AR -> "التسليح وحمولات الذخيرة"
    }

    fun balanceHistory(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Documented Balance & Patch History"
        AppLanguage.AR -> "سجل التعديلات وتاريخ التحديثات الموثقة"
    }

    fun followVehicle(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Follow Changes"
        AppLanguage.AR -> "متابعة التغييرات"
    }

    fun unfollowVehicle(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Following"
        AppLanguage.AR -> "تتم المتابعة"
    }

    fun favorite(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Favorite"
        AppLanguage.AR -> "مفضل"
    }

    // Dashboard sections
    fun breakingNews(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Official War Thunder News"
        AppLanguage.AR -> "أخبار وور ثاندر الرسمية"
    }

    fun majorPatches(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Major Game Updates"
        AppLanguage.AR -> "التحديثات الرئيسية للعبة"
    }

    fun followedAlerts(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Followed Vehicle Alerts"
        AppLanguage.AR -> "تنبيهات المركبات التي تتابعها"
    }

    fun recentBrShifts(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Recent Battle Rating Shifts"
        AppLanguage.AR -> "أحدث تعديلات تصنيف المعركة (BR)"
    }

    // Armory
    fun missileGuidance(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Guidance & Seeker"
        AppLanguage.AR -> "التوجيه والباحث"
    }

    fun maxG(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Max Overload"
        AppLanguage.AR -> "أقصى حمولة مناورة"
    }

    fun range(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Max Range"
        AppLanguage.AR -> "المدى الأقصى"
    }

    fun speed(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Top Speed"
        AppLanguage.AR -> "السرعة القصوى"
    }

    fun countermeasureResistance(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "ECCM / Flare Resistance"
        AppLanguage.AR -> "مقاومة التشويش والمشاعل (ECCM)"
    }

    fun compatibleAircraft(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Compatible Vehicles"
        AppLanguage.AR -> "المركبات المتوافقة"
    }

    fun documentedTweaks(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Documented Tweaks & Adjustments"
        AppLanguage.AR -> "التعديلات والتحسينات الموثقة"
    }

    // Settings
    fun settingsLanguageTitle(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Interface Language"
        AppLanguage.AR -> "لغة الواجهة"
    }

    fun settingsLanguageDesc(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Switch instantly between Arabic (RTL) and English (LTR)"
        AppLanguage.AR -> "التبديل الفوري بين العربية (من اليمين لليسار) والإنجليزية"
    }

    fun settingsNotifTitle(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Intelligence Alerts"
        AppLanguage.AR -> "تنبيهات الاستخبارات الحربية"
    }

    fun notifyMajorUpdates(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Major Patch Notes & Dev Blogs"
        AppLanguage.AR -> "ملاحظات التحديثات الكبرى ومدونات المطورين"
    }

    fun notifyBrChanges(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Battle Rating Adjustments (AB/RB/SB)"
        AppLanguage.AR -> "تعديلات تصنيف المعركة (أركيد / واقعي / محاكي)"
    }

    fun notifyMissileTweaks(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Missile & Weapon Flight Model Tweaks"
        AppLanguage.AR -> "تعديلات نماذج طيران الصواريخ والأسلحة"
    }

    fun notifyFollowedVehicles(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Followed Vehicle Modifications"
        AppLanguage.AR -> "تعديلات المركبات المتابعة فقط"
    }

    fun officialSourcesTitle(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Verified Official Sources"
        AppLanguage.AR -> "المصادر الرسمية المعتمدة"
    }

    fun disclaimerText(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "All vehicle names, weapon designations, and game assets are property of Gaijin Entertainment. Translations are provided for Arabic-speaking pilots and tank commanders while preserving original technical nomenclature."
        AppLanguage.AR -> "جميع أسماء المركبات وتسميات الأسلحة وبيانات اللعبة ملك لشركة Gaijin Entertainment. تم توفير الترجمات للطيارين وقادة الدبابات الناطقين بالعربية مع الحفاظ على التسميات الفنية والرموز الرسمية بدقة."
    }

    // Empty states
    fun noResults(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "No vehicles or weapons match your filter"
        AppLanguage.AR -> "لم يتم العثور على مركبات أو أسلحة مطابقة للبحث"
    }

    fun noFavorites(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "No followed vehicles yet"
        AppLanguage.AR -> "لا توجد مركبات قيد المتابعة حالياً"
    }

    fun followHint(lang: AppLanguage) = when (lang) {
        AppLanguage.EN -> "Tap 'Follow' on vehicles like the JAS39 Gripen E or T-90M to monitor incoming balance changes."
        AppLanguage.AR -> "اضغط على زر 'متابعة' في بطاقة أي مركبة مثل JAS39 Gripen أو T-90M لتلقي تنبيهات التعديلات فور صدورها."
    }
}
