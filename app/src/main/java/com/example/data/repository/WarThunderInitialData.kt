package com.example.data.repository

import com.example.data.model.*

object WarThunderInitialData {

    val vehicles: List<Vehicle> = listOf(
        Vehicle(
            id = "jas39c_gripen",
            name = "JAS39C Gripen",
            roleArExplanation = "مقاتلة تفوق جوي متعددة المهام متقدمة خفيفة الوزن مزودة برادار دوبلري نبضي وأجنحة كانارد",
            category = VehicleCategory.AIRCRAFT,
            type = VehicleType.JET_FIGHTER,
            nation = Nation.SWEDEN,
            rank = 8,
            brArcade = 13.3,
            brRealistic = 13.7,
            brSimulator = 13.7,
            maxSpeed = "Mach 2.0 (2,125 km/h at altitude)",
            engineAndMobility = "Volvo RM12 Turbofan with Reheat (80.5 kN max thrust)",
            radarAndSensors = "Ericsson PS-05/A Pulse-Doppler Radar, TWS mode, Helmet-Mounted Display (HMD), Apparat 136 RWR",
            opticsAndThermal = "Litening II Target Pod with 2nd Gen FLIR / Laser Designation",
            countermeasuresAndArmor = "360 Large Caliber Flares/Chaff + 320 BOL pod Countermeasures",
            weaponsAndAmmunition = listOf(
                "AIM-120A AMRAAM (ARH Fox-3)",
                "RB 74(M) / AIM-9M Sidewinder",
                "Rb 75 / AGM-65B Maverick",
                "GBU-12 Paveway II Guided Bomb",
                "27mm Mauser BK-27 Revolver Cannon (120 rounds)"
            ),
            recentChanges = listOf(
                VehicleChange(
                    patchVersion = "2.41.0.32",
                    date = "2024-10-08",
                    summaryEn = "Radar notch filtering gate adjusted; improved clutter rejection over sea surface.",
                    summaryAr = "تعديل بوابة حجب الرادار (Notch Filtering)؛ تحسين عزل التشويش السطحي فوق مياه البحر.",
                    type = ChangeType.BUFF,
                    previousValue = "Notch gate: 35 m/s",
                    newValue = "Notch gate: 28 m/s"
                ),
                VehicleChange(
                    patchVersion = "2.39.0.18",
                    date = "2024-09-14",
                    summaryEn = "Fuel tank seal mechanics updated and BOL pod countermeasure release rate corrected.",
                    summaryAr = "تحديث ميكانيكية إغلاق خزانات الوقود وتصحيح معدل إطلاق المشاعل من حاويات BOL.",
                    type = ChangeType.BUGFIX
                )
            ),
            overviewEn = "The JAS39C Gripen is one of the most agile modern fighters in War Thunder. Featuring exceptional delta-canard maneuverability, generous countermeasure capacity with BOL dispensers, and deadly Fox-3 AIM-120 AMRAAM capability.",
            overviewAr = "تعتبر طائرة JAS39C غريبن إحدى أكثر المقاتلات الحديثة مرونة وقدرة على المناورة في وور ثاندر. تتميز بتصميم دلتا-كانارد متفوق، وسعة ذخائر تشويش هائلة عبر حاويات BOL، مع تسليح فتاك بصواريخ AIM-120 أمرام الموجهة بالرادار النشط.",
            isTopTier = true,
            isFollowed = true,
            isFavorite = true
        ),
        Vehicle(
            id = "f16c_fighting_falcon",
            name = "F-16C Block 50",
            roleArExplanation = "مقاتلة تفوق جوي وهجوم أرضي أمريكية بمحرك F110 ورادار دوبلري مسحي متقدم",
            category = VehicleCategory.AIRCRAFT,
            type = VehicleType.JET_FIGHTER,
            nation = Nation.USA,
            rank = 8,
            brArcade = 13.7,
            brRealistic = 13.7,
            brSimulator = 13.7,
            maxSpeed = "Mach 2.05 (2,175 km/h)",
            engineAndMobility = "General Electric F110-GE-129 Afterburning Turbofan (131.6 kN)",
            radarAndSensors = "AN/APG-68(V)9 Pulse-Doppler Radar, JHMCS Helmet Cueing System, AN/ALR-56M RWR",
            opticsAndThermal = "LITENING II Advanced Targeting Pod (Gen 2 FLIR / Laser Tracker)",
            countermeasuresAndArmor = "120 Standard CM (Flares/Chaff) with AN/ALE-47 Dispenser",
            weaponsAndAmmunition = listOf(
                "6x AIM-120A AMRAAM",
                "AIM-9M Sidewinder (Smokeless Motor)",
                "AGM-65D Maverick (Thermal Seeker)",
                "GBU-24 Paveway III Penetrator",
                "20mm M61A1 Vulcan Rotary Cannon (511 rounds)"
            ),
            recentChanges = listOf(
                VehicleChange(
                    patchVersion = "2.41.0.22",
                    date = "2024-09-29",
                    summaryEn = "Elevator authority at high speeds (> Mach 1.2) increased by 4%.",
                    summaryAr = "زيادة استجابة أسطح التوجيه الأفقية (Elevator) عند السرعات العالية (> ماخ 1.2) بنسبة 4%.",
                    type = ChangeType.BUFF,
                    previousValue = "Max G limiter: 9.0G",
                    newValue = "Max G limiter: 9.35G sustained"
                )
            ),
            overviewEn = "The F-16C Block 50 represents the pinnacle of single-engine lightweight fighter engineering in the US tree, combining high thrust-to-weight, helmet-slaved missiles, and precision ground ordnance.",
            overviewAr = "تمثل مقاتلة F-16C بلوك 50 ذروة هندسة المقاتلات الخفيفة أحادية المحرك في الشجرة الأمريكية، وتجمع بين نسبة دفع لوزن هائلة، وصواريخ موجهة بخوذة الطيار، وأسلحة دقيقة للهجوم الأرضي.",
            isTopTier = true,
            isFollowed = true,
            isFavorite = true
        ),
        Vehicle(
            id = "f15e_strike_eagle",
            name = "F-15E Strike Eagle",
            roleArExplanation = "مقاتلة ضاربة ثقيلة ثنائية المحرك بقدرات حمل أسلحة استثنائية ورادار APG-70 بعيد المدى",
            category = VehicleCategory.AIRCRAFT,
            type = VehicleType.STRIKE_AIRCRAFT,
            nation = Nation.USA,
            rank = 8,
            brArcade = 14.0,
            brRealistic = 14.0,
            brSimulator = 14.0,
            maxSpeed = "Mach 2.5 (2,655 km/h)",
            engineAndMobility = "2x Pratt & Whitney F100-PW-229 Turbofans (2x 129 kN reheat)",
            radarAndSensors = "AN/APG-70 Synthetic Aperture Radar, JHMCS, AN/ALQ-135 ECM",
            opticsAndThermal = "LANTIRN Navigation & Targeting Pods, Gen 3 FLIR",
            countermeasuresAndArmor = "240 CM (AN/ALE-45 + AN/ALE-47 Dispensers)",
            weaponsAndAmmunition = listOf(
                "8x AIM-120B AMRAAM",
                "AIM-9M Sidewinder",
                "GBU-38 JDAM (GPS Guided)",
                "GBU-15 Electro-Optical Guided Bomb",
                "20mm M61A1 Vulcan (510 rounds)"
            ),
            recentChanges = listOf(
                VehicleChange(
                    patchVersion = "2.41.0.30",
                    date = "2024-10-05",
                    summaryEn = "Radar ground clutter filter refined; TWS multi-target tracking stability boosted.",
                    summaryAr = "تحسين مرشح التشويش الأرضي للرادار؛ وتعزيز ثبات تتبع الأهداف المتعددة بنمط TWS.",
                    type = ChangeType.BUFF
                )
            ),
            overviewEn = "Heavy strike fighter capable of carrying massive bomb loads while maintaining peer-level air-to-air lethality with up to 8 AMRAAM missiles.",
            overviewAr = "مقاتلة ضاربة ثقيلة قادرة على حمل حمولات ضخمة من القنابل مع الحفاظ على تفوق جوي كامل عبر ما يصل إلى 8 صواريخ أمرام.",
            isTopTier = true,
            isFollowed = true,
            isFavorite = false
        ),
        Vehicle(
            id = "su27_flanker",
            name = "Su-27 Flanker",
            roleArExplanation = "مقاتلة تفوق جوي ثقيلة سوفيتية ذات مدى عملياتي بعيد وقدرة مناورة فوق-صوتية فائقة",
            category = VehicleCategory.AIRCRAFT,
            type = VehicleType.JET_FIGHTER,
            nation = Nation.USSR,
            rank = 8,
            brArcade = 13.7,
            brRealistic = 13.7,
            brSimulator = 13.7,
            maxSpeed = "Mach 2.35 (2,500 km/h)",
            engineAndMobility = "2x Saturn AL-31F Afterburning Turbofans (2x 122.6 kN)",
            radarAndSensors = "N001 Mech Radar, OEPS-27 IRST Optical Locator, SPO-15 Beryoza RWR, Shchel-3UM HMD",
            opticsAndThermal = "OEPS-27 Thermal/Infrared Search and Track (Passive Detection)",
            countermeasuresAndArmor = "96 Large Caliber Flares/Chaff (APP-50 Dispensers)",
            weaponsAndAmmunition = listOf(
                "6x R-27ER (Extended Range SARH Fox-1)",
                "4x R-73 (High-Off Boresight TVC IR)",
                "R-77 (Fox-3 ARH)",
                "30mm GSh-30-1 Autocannon (150 rounds)"
            ),
            recentChanges = listOf(
                VehicleChange(
                    patchVersion = "2.39.0.24",
                    date = "2024-09-22",
                    summaryEn = "Pitch rate at subsonic speeds (Mach 0.6 - 0.85) harmonized with aerodynamic test data.",
                    summaryAr = "مواءمة معدل دوران مقدمة الطائرة (Pitch Rate) عند السرعات دون الصوتية مع اختبارات نفق الرياح.",
                    type = ChangeType.MECHANICS
                )
            ),
            overviewEn = "A legendary air superiority platform combining extreme high-AoA maneuverability (Pugachev's Cobra), high speed R-27ER missiles, and deadly R-73 dogfight capabilities.",
            overviewAr = "منصة تفوق جوي أسطورية تجمع بين قدرة مناورة فائقة بزوايا هجوم حادة (مناورة الكوبرا)، وصواريخ R-27ER فائقة السرعة، وصواريخ R-73 الفتاكة في القتال المتلاحم.",
            isTopTier = true,
            isFollowed = false,
            isFavorite = true
        ),
        Vehicle(
            id = "mirage_2000_5f",
            name = "Mirage 2000-5F",
            roleArExplanation = "مقاتلة دلتا فرنسية متقدمة مزودة برادار RDY متعدد الأهداف وصواريخ ميكا فائقة المناورة",
            category = VehicleCategory.AIRCRAFT,
            type = VehicleType.JET_FIGHTER,
            nation = Nation.FRANCE,
            rank = 8,
            brArcade = 13.7,
            brRealistic = 13.7,
            brSimulator = 13.7,
            maxSpeed = "Mach 2.2 (2,330 km/h)",
            engineAndMobility = "SNECMA M53-P2 Turbofan (95.1 kN reheat)",
            radarAndSensors = "Thomson-CSF RDY Multi-Target Radar, Serval RWR, Cathedrale Integrated Navigation",
            opticsAndThermal = "Damocles Targeting Pod (3rd Gen Thermal)",
            countermeasuresAndArmor = "168 Large Flares/Chaff + Eclair Dispenser",
            weaponsAndAmmunition = listOf(
                "4x MICA-EM (Active Radar Homing, 50G TVC)",
                "2x Magic 2 (All-Aspect IRCCM)",
                "PGM 500 / PGM 2000 Guided Glider Bombs",
                "2x 30mm DEFA 554 Cannons (250 rounds)"
            ),
            recentChanges = listOf(
                VehicleChange(
                    patchVersion = "2.41.0.19",
                    date = "2024-09-26",
                    summaryEn = "MICA-EM seeker gimbal angle limit increased from 55° to 60°.",
                    summaryAr = "زيادة زاوية دوران باحث صاروخ MICA-EM من 55 إلى 60 درجة.",
                    type = ChangeType.BUFF,
                    previousValue = "Gimbal: 55°",
                    newValue = "Gimbal: 60°"
                )
            ),
            overviewEn = "French tailless delta fighter famous for unmatched instantaneous turn rate and the lethal MICA-EM active radar missiles featuring thrust-vectoring 50G agility.",
            overviewAr = "مقاتلة دلتا فرنسية شهيرة بمعدل انعطاف لحظي لا يضاهى وصواريخ MICA-EM الرادارية النشطة المزودة بدفع موجه بقدرة مناورة تصل إلى 50G.",
            isTopTier = true,
            isFollowed = false,
            isFavorite = false
        ),
        Vehicle(
            id = "t90m_proryv",
            name = "T-90M \"Proryv\"",
            roleArExplanation = "دبابة قتال رئيسية روسية متقدمة مزودة بدروع ريليكت التفاعلية ونظام إدارة نيران كالينا وبطانة كبح الشظايا",
            category = VehicleCategory.GROUND,
            type = VehicleType.MBT,
            nation = Nation.USSR,
            rank = 8,
            brArcade = 11.7,
            brRealistic = 11.7,
            brSimulator = 11.7,
            maxSpeed = "60 km/h (Forward), -4 km/h (Reverse)",
            engineAndMobility = "V-92S2F V12 Diesel Engine (1,130 hp), Specific Power 23.5 hp/t",
            radarAndSensors = "Kalina Fire Control System with automatic target tracker, LWS (Laser Warning Receiver)",
            opticsAndThermal = "Sosna-U Gunner Sight + Hawkeye Commander Panoramic Sight (3rd Gen Thermal)",
            countermeasuresAndArmor = "Relikt 3rd Gen ERA (Hull + Turret), Internal Aramid Spall Liner, Nakidka Thermal Camouflage",
            weaponsAndAmmunition = listOf(
                "125mm 2A46M-5 Smoothbore Gun (22-round autoloader, 7.1s reload)",
                "3BM60 Svinets-2 APFSDS (580mm penetration at 0° 10m)",
                "9M119M1 Invar-M Tandem ATGM (Laser-guided, 850mm CE)",
                "3OF26 HE-FRAG with remote programmable fuse",
                "12.7mm Kord Remote Controlled Weapon Station (RCWS)"
            ),
            recentChanges = listOf(
                VehicleChange(
                    patchVersion = "2.41.0.28",
                    date = "2024-10-02",
                    summaryEn = "Relikt side ERA internal volumetric plate thickness confirmed and fixed to 15mm high hardness steel.",
                    summaryAr = "تأكيد وتصحيح سماكة صفائح الدروع الداخلية للريليكت الجانبي لتصبح 15 ملم من الفولاذ فائق الصلابة.",
                    type = ChangeType.BUFF,
                    previousValue = "ERA backing: 10mm structural steel",
                    newValue = "ERA backing: 15mm RHA high hardness"
                ),
                VehicleChange(
                    patchVersion = "2.39.0.12",
                    date = "2024-09-08",
                    summaryEn = "Internal spall liner splinter cone reduction verified against APFSDS penetrators.",
                    summaryAr = "التحقق من كفاءة بطانة كبح الشظايا الداخلية في تقليص مخروط تناثر الشظايا عند الاختراق بقذائف سابو.",
                    type = ChangeType.MECHANICS
                )
            ),
            overviewEn = "The latest modern Russian MBT in War Thunder, featuring exceptional all-around protection through Relikt ERA and an internal spall liner that dramatically reduces post-penetration damage.",
            overviewAr = "أحدث دبابة قتال رئيسية روسية في وور ثاندر، تتمتع بحماية شاملة استثنائية بفضل دروع ريليكت التفاعلية وبطانة الأراميد الداخلية لكبح الشظايا التي تقلل أضرار الاختراق بشكل ملحوظ.",
            isTopTier = true,
            isFollowed = true,
            isFavorite = true
        ),
        Vehicle(
            id = "leopard_2a7v",
            name = "Leopard 2A7V",
            roleArExplanation = "دبابة قتال رئيسية ألمانية بمدفع L/55A1 عالي الضغط وتدريع هيكل إضافي وحراريات جيل ثالث",
            category = VehicleCategory.GROUND,
            type = VehicleType.MBT,
            nation = Nation.GERMANY,
            rank = 8,
            brArcade = 11.7,
            brRealistic = 11.7,
            brSimulator = 11.7,
            maxSpeed = "68 km/h (Forward), -31 km/h (Reverse)",
            engineAndMobility = "MTU MB 873 Ka-501 Twin-Turbo Diesel (1,500 hp), Power-to-weight 22.5 hp/t",
            radarAndSensors = "EMES 15 Fire Control with dynamic muzzle reference sensor and laser rangefinder",
            opticsAndThermal = "ATTICA Gen 3 Thermal for Commander and Gunner",
            countermeasuresAndArmor = "Enhanced composite wedge add-on, heavy frontal hull composite array, internal spall liners",
            weaponsAndAmmunition = listOf(
                "120mm Rheinmetall L/55A1 Smoothbore (High-pressure chamber)",
                "DM53 / DM73 APFSDS (652mm penetration at 0° 10m, manual reload: 6.0s aced)",
                "DM11 Programmable Airburst HE round",
                "7.62mm MG3A1 Coaxial Machine Gun"
            ),
            recentChanges = listOf(
                VehicleChange(
                    patchVersion = "2.41.0.14",
                    date = "2024-09-20",
                    summaryEn = "Frontal lower plate composite filler modeling adjusted to prevent over-penetration by autocannons.",
                    summaryAr = "تعديل نمذجة حشوة التدريع المركب للوحة السفلية الأمامية لمنع الاختراق الزائد بواسطة المدافع الآلية.",
                    type = ChangeType.BUFF
                )
            ),
            overviewEn = "Widely regarded as the top-performing Western MBT in War Thunder, boasting impenetrable turret cheeks, the devastating L/55A1 cannon firing DM53, and 3rd Gen thermals for both crew positions.",
            overviewAr = "تعتبر على نطاق واسع أفضل دبابة قتال غربية أداءً في اللعبة، بفضل دروع وجنتي البرج الحصينة، ومدفع L/55A1 المدمر الذي يطلق قذائف DM53، ومناظير حرارية من الجيل الثالث للقائد والمدفعي.",
            isTopTier = true,
            isFollowed = false,
            isFavorite = true
        ),
        Vehicle(
            id = "m1a2_sep_v2",
            name = "M1A2 SEP v2 Abrams",
            roleArExplanation = "دبابة قتال رئيسية أمريكية مزودة بنظام أسلحة CROWS II يتم التحكم به عن بعد وحماية معززة باليورانيوم المنضب",
            category = VehicleCategory.GROUND,
            type = VehicleType.MBT,
            nation = Nation.USA,
            rank = 8,
            brArcade = 11.7,
            brRealistic = 11.7,
            brSimulator = 11.7,
            maxSpeed = "68 km/h (Forward), -40 km/h (Reverse)",
            engineAndMobility = "Honeywell AGT1500 Gas Turbine Engine (1,500 hp), 24.0 hp/t",
            radarAndSensors = "CROWS II Remote Station, Digital Fire Control, Hunter-Killer Capability",
            opticsAndThermal = "2nd Gen FLIR Commander CITV and Gunner Primary Sight",
            countermeasuresAndArmor = "Depleted Uranium Composite Inserts, TUSK II side ERA tiles option, Spall Liner",
            weaponsAndAmmunition = listOf(
                "120mm M256 Smoothbore Gun (5.0s aced manual reload)",
                "M829A2 APFSDS (629mm penetration)",
                "M830A1 HEAT-MP-T with proximity airburst capability against helicopters",
                "12.7mm M2HB Heavy MG (CROWS II) + 2x 7.62mm M240"
            ),
            recentChanges = listOf(
                VehicleChange(
                    patchVersion = "2.41.0.31",
                    date = "2024-10-06",
                    summaryEn = "Turret ring volumetric armor geometry refined to reduce shot-trap weakness.",
                    summaryAr = "تحسين الهندسة الحجمية لدروع حلقة البرج لتقليل نقطة الضعف الناتجة عن انزلاق القذائف.",
                    type = ChangeType.BUFF,
                    previousValue = "Turret ring neck: 50.8mm single plate",
                    newValue = "Volumetric composite weld: 120mm effective"
                )
            ),
            overviewEn = "Fast and punchy American main battle tank with an industry-leading 5.0s aced reload rate, superb reverse speed, and proximity-fused ammunition to counter low-flying helicopters.",
            overviewAr = "دبابة قتال أمريكية سريعة وفتاكة تتمتع بأسرع معدل تلقيم يدوي في فئتها (5 ثوانٍ لطاقم متمرس)، وسرعة رجوع خلفي ممتازة، وقذائف مزودة بصاعق تقاربي لمواجهة المروحيات المنخفضة.",
            isTopTier = true,
            isFollowed = false,
            isFavorite = false
        ),
        Vehicle(
            id = "pantsir_s1",
            name = "Pantsir-S1",
            roleArExplanation = "منظومة دفاع جوي متقدمة ذاتية الحركة مزودة برادار مصفوفة طورية ومدفعين عيار 30 ملم و12 صاروخاعتراض",
            category = VehicleCategory.GROUND,
            type = VehicleType.SPAA_SAM,
            nation = Nation.USSR,
            rank = 8,
            brArcade = 11.7,
            brRealistic = 11.7,
            brSimulator = 11.7,
            maxSpeed = "90 km/h (Wheeled KamAZ-6560 8x8)",
            engineAndMobility = "KamAZ-740.35-400 Diesel (400 hp)",
            radarAndSensors = "1RS2-1E Phased Array Acquisition Radar (360° coverage, 20km detection) + Optical/Thermal Tracker",
            opticsAndThermal = "Dual-band Thermal Camera & Laser Rangefinder",
            countermeasuresAndArmor = "Light armor cabin (resists 7.62mm), Radar Frequency Agility against Anti-Radiation threats",
            weaponsAndAmmunition = listOf(
                "12x 57E6 Two-stage Biconical Surface-to-Air Missiles (Mach 3.8, 20km range, 32G)",
                "2x 30mm 2A38M Twin Autocannons (5,000 RPM combined rate of fire, 1,400 rounds)"
            ),
            recentChanges = listOf(
                VehicleChange(
                    patchVersion = "2.39.0.15",
                    date = "2024-09-11",
                    summaryEn = "Missile booster stage smoke persistence adjusted to prevent complete line-of-sight obscuration.",
                    summaryAr = "تعديل كثافة دخان مرحلة الدفع الصاروخي المعزز لمنع حجب خط الرؤية البصري بالكامل.",
                    type = ChangeType.MECHANICS
                )
            ),
            overviewEn = "The premier long-range anti-aircraft system in War Thunder, capable of intercepting aircraft, drones, and guided munitions at extreme standoff ranges up to 20 kilometers.",
            overviewAr = "المنظومة الدفاعية المضادة للطائرات الأولى في وور ثاندر بعيدة المدى، القادرة على اعتراض الطائرات النفاثة، المسيرات، والذخائر الموجهة على مسافات قصوى تصل إلى 20 كيلومتراً.",
            isTopTier = true,
            isFollowed = false,
            isFavorite = false
        )
    )

    val weapons: List<WeaponSpec> = listOf(
        WeaponSpec(
            id = "aim120a",
            name = "AIM-120A AMRAAM",
            arabicExplanation = "صاروخ جو-جو متطور متوسط المدى موجه برادار نشط بنمط Fox-3",
            category = WeaponCategory.MISSILE_AAM,
            seekerType = "Active Radar Homing (ARH) with Datalink & Inertial (INS)",
            seekerTypeAr = "توجيه راداري نشط (ARH) مع رابط بيانات وتوجيه عطالي متكامل",
            maxRangeKm = 50.0,
            maxSpeedMach = 4.0,
            maxOverloadG = 35,
            guidanceTimeSec = 60.0,
            eccmTechnology = "Track-gate shrinkage & doppler frequency filter",
            eccmTechnologyAr = "تضييق نافذة التتبع وتصفية ترددات الدوبلر ضد الرقائق المعدنية (Chaff)",
            aspect = "All-Aspect",
            compatibleVehicles = listOf("F-16C", "JAS39C Gripen", "AV-8B Plus", "F-15E", "F-15C"),
            recentModificationsEn = listOf(
                "Patch 2.39: Motor boost/sustain curve updated for reduced transonic drag.",
                "Patch 2.37: Inertial drift error model added during datalink loss."
            ),
            recentModificationsAr = listOf(
                "تحديث 2.39: تعديل منحنى الدفع لتقليل مقاومة الهواء في المرحلة الانتقالية للصوت.",
                "تحديث 2.37: إضافة نموذج خطأ الانحراف العطالي عند فقدان رابط البيانات اللاسلكي."
            ),
            previousOverloadG = 30,
            newOverloadG = 35,
            statusBadge = "Fox-3 Standard"
        ),
        WeaponSpec(
            id = "r77",
            name = "R-77 (RVV-AE)",
            arabicExplanation = "صاروخ جو-جو روسي موجه برادار نشط بزعانف شبكية فريدة لتحكم ديناميكي فائق",
            category = WeaponCategory.MISSILE_AAM,
            seekerType = "Active Radar Homing (ARH) with INS + Datalink",
            seekerTypeAr = "توجيه راداري نشط (ARH) مع ملاحة عطالية ورابط بيانات مرحلي",
            maxRangeKm = 45.0,
            maxSpeedMach = 4.0,
            maxOverloadG = 35,
            guidanceTimeSec = 55.0,
            eccmTechnology = "Monopulse pulse-doppler seeker with Chaff rejection",
            eccmTechnologyAr = "باحث نبضي دوبلري أحادي النبضة مع عزل متطور للشراك الخداعية",
            aspect = "All-Aspect",
            compatibleVehicles = listOf("Su-27SM", "MiG-29SMT", "Su-30SM", "J-11B"),
            recentModificationsEn = listOf(
                "Patch 2.41.0.32: Lattice fin induced drag at supersonic speeds (> Mach 2.5) reduced by 6%.",
                "Patch 2.39: Autopilot responsiveness at low dynamic pressures improved."
            ),
            recentModificationsAr = listOf(
                "تحديث 2.41.0.32: تخفيض مقاومة السحب الناتجة عن الزعانف الشبكية عند السرعات الفائقة بنسبة 6%.",
                "تحديث 2.39: تحسين استجابة الطيار الآلي عند الضغوط الديناميكية المنخفضة في الارتفاعات العالية."
            ),
            previousSpeedMach = 3.8,
            newSpeedMach = 4.0,
            statusBadge = "Aerodynamics Buffed"
        ),
        WeaponSpec(
            id = "mica_em",
            name = "MICA-EM",
            arabicExplanation = "صاروخ جو-جو فرنسي متقدم للدفاع الجوي والقتال المتلاحم بدفع موجه TVC",
            category = WeaponCategory.MISSILE_AAM,
            seekerType = "Active Radar Homing (AD4A seeker) with TVC agility",
            seekerTypeAr = "توجيه راداري نشط (باحث AD4A) مع دفع موجه فائق المناورة",
            maxRangeKm = 60.0,
            maxSpeedMach = 4.0,
            maxOverloadG = 50,
            guidanceTimeSec = 65.0,
            eccmTechnology = "Adaptive frequency jamming cancellation & sidelobe blanking",
            eccmTechnologyAr = "إلغاء التشويش الترددي التكيفي وإلغاء الفصوص الجانبية للرادار",
            aspect = "All-Aspect",
            compatibleVehicles = listOf("Mirage 2000-5F", "Rafale (Upcoming)"),
            recentModificationsEn = listOf(
                "Patch 2.41.0.19: Gimbal limit increased to 60° for enhanced high-off-boresight tracking.",
                "Patch 2.37: TVC burn duration accurately set to 3.2 seconds."
            ),
            recentModificationsAr = listOf(
                "تحديث 2.41.0.19: زيادة زاوية دوران الباحث إلى 60 درجة لتعزيز التتبع خارج خط البصر.",
                "تحديث 2.37: ضبط دقيق لزمن عمل نفاثات الدفع الموجه ليصبح 3.2 ثانية."
            ),
            previousOverloadG = 40,
            newOverloadG = 50,
            statusBadge = "50G Agility"
        ),
        WeaponSpec(
            id = "r73",
            name = "R-73 (AA-11 Archer)",
            arabicExplanation = "صاروخ جو-جو سوفيتي حراري للقتال القريب بمحرك دفع موجه ومقاومة مشاعل متقدمة",
            category = WeaponCategory.MISSILE_AAM,
            seekerType = "All-Aspect Infrared (IR) with IRCCM Gate-Off & TVC",
            seekerTypeAr = "توجيه بالأشعة تحت الحمراء مع نظام إغلاق النافذة ضد المشاعل ودفع موجه",
            maxRangeKm = 30.0,
            maxSpeedMach = 2.5,
            maxOverloadG = 40,
            guidanceTimeSec = 25.0,
            eccmTechnology = "Field-Of-View Gate-Off IRCCM (Shrinks search sector when flare detected)",
            eccmTechnologyAr = "تقليص حقل الرؤية (FOV Shrinkage) فور استشعار حرارة المشاعل الخداعية",
            aspect = "All-Aspect / 60° HMD Slaved",
            compatibleVehicles = listOf("Su-27", "MiG-29", "Su-25T", "Su-30SM"),
            recentModificationsEn = listOf(
                "Patch 2.39: High off-boresight lock retention improved when target performs rapid evasive roll."
            ),
            recentModificationsAr = listOf(
                "تحديث 2.39: تحسين الحفاظ على الإقفال خارج خط البصر أثناء دوران الهدف المتفادي السريع."
            ),
            statusBadge = "High Threat IR"
        ),
        WeaponSpec(
            id = "aim9m",
            name = "AIM-9M Sidewinder",
            arabicExplanation = "صاروخ جو-جو حراري أمريكي يتميز بمحرك عديم الدخان وحماية بصرية ضد المشاعل",
            category = WeaponCategory.MISSILE_AAM,
            seekerType = "All-Aspect IR with Spatial Filtering IRCCM + Smokeless Motor",
            seekerTypeAr = "توجيه حراري شامل مع فلترة مكانية ضد المشاعل ومحرك دفع بدون دخان",
            maxRangeKm = 18.0,
            maxSpeedMach = 2.5,
            maxOverloadG = 30,
            guidanceTimeSec = 22.0,
            eccmTechnology = "Spatial / Image Strobe Filtering (Rejects point-source flares)",
            eccmTechnologyAr = "فلترة مكانية تعزل المصادر النقطية الساطعة للمشاعل",
            aspect = "All-Aspect",
            compatibleVehicles = listOf("F-16C", "F-15E", "F-14B", "JAS39C", "A-10C"),
            recentModificationsEn = listOf(
                "Smokeless motor visual signature confirmed in simulation mode."
            ),
            recentModificationsAr = listOf(
                "تأكيد البصمة البصرية الخالية من الدخان لمحرك الصاروخ في نمط المحاكاة."
            ),
            statusBadge = "Stealth Motor"
        )
    )

    val changelogEntries: List<ChangelogEntry> = listOf(
        ChangelogEntry(
            id = "ch_01",
            vehicleOrWeapon = "JAS39C Gripen",
            category = "Aircraft / Radar",
            nation = Nation.SWEDEN,
            patchVersion = "2.41.0.32",
            date = "2024-10-08",
            changeType = ChangeType.BUFF,
            previousValue = "35 m/s",
            newValue = "28 m/s",
            detailsEn = "The PS-05/A radar notch velocity gate has been adjusted from 35 m/s to 28 m/s, preventing false target drops over undulating terrain.",
            detailsAr = "تم تخفيض بوابة سرعة حجب الرادار (Notch gate) لرادار PS-05/A من 35 إلى 28 م/ث، مما يمنع فقدان تتبع الأهداف فوق التضاريس المتموجة.",
            sourceUrl = "https://warthunder.com/en/game/changelog/current/1652",
            confidence = ConfidenceLevel.OFFICIAL_CONFIRMED,
            isAiTranslated = true
        ),
        ChangelogEntry(
            id = "ch_02",
            vehicleOrWeapon = "R-77 (RVV-AE)",
            category = "Missiles / Flight Model",
            nation = Nation.USSR,
            patchVersion = "2.41.0.32",
            date = "2024-10-08",
            changeType = ChangeType.BUFF,
            previousValue = "Drag coefficient: 0.88",
            newValue = "Drag coefficient: 0.82",
            detailsEn = "Lattice fin induced drag reduced across the Mach 2.2 - 3.5 regime based on manufacturer wind-tunnel research.",
            detailsAr = "تخفيض معامل السحب المستحث للزعانف الشبكية عبر نطاق ماخ 2.2 إلى 3.5 استناداً إلى أبحاث نفق الرياح الموثقة من الشركة المصنعة.",
            sourceUrl = "https://warthunder.com/en/game/changelog/current/1652",
            confidence = ConfidenceLevel.OFFICIAL_CONFIRMED,
            isAiTranslated = true
        ),
        ChangelogEntry(
            id = "ch_03",
            vehicleOrWeapon = "T-90M \"Proryv\"",
            category = "Ground / Armor",
            nation = Nation.USSR,
            patchVersion = "2.41.0.28",
            date = "2024-10-02",
            changeType = ChangeType.BUFF,
            previousValue = "Backing: 10mm Mild Steel",
            newValue = "Backing: 15mm RHA High Hardness",
            detailsEn = "Relikt side ERA internal volumetric plate thickness corrected to 15mm high hardness armor plate.",
            detailsAr = "تصحيح سماكة صفيحة التدريع الخلفية لدروع ريليكت الجانبية لتصبح 15 ملم من الفولاذ فائق الصلابة.",
            sourceUrl = "https://warthunder.com/en/game/changelog/current/1648",
            confidence = ConfidenceLevel.OFFICIAL_CONFIRMED,
            isAiTranslated = true
        ),
        ChangelogEntry(
            id = "ch_04",
            vehicleOrWeapon = "M1A2 SEP v2",
            category = "Ground / Turret Ring",
            nation = Nation.USA,
            patchVersion = "2.41.0.31",
            date = "2024-10-06",
            changeType = ChangeType.BUFF,
            previousValue = "50.8mm flat plate",
            newValue = "Volumetric ring geometry",
            detailsEn = "Fixed an issue where the turret ring gap allowed small caliber APFSDS rounds to penetrate without striking the hull armor.",
            detailsAr = "إصلاح الخلل في فجوة حلقة البرج الذي كان يسمح لقذائف سابو الصغيرة باختراق المركبة دون الاصطدام بدروع الهيكل الأساسية.",
            sourceUrl = "https://warthunder.com/en/game/changelog/current/1650",
            confidence = ConfidenceLevel.OFFICIAL_CONFIRMED,
            isAiTranslated = true
        ),
        ChangelogEntry(
            id = "ch_05",
            vehicleOrWeapon = "AIM-120A AMRAAM",
            category = "Missiles / Guidance",
            nation = Nation.USA,
            patchVersion = "2.39.0.18",
            date = "2024-09-14",
            changeType = ChangeType.MECHANICS,
            previousValue = "Instantaneous lock drop",
            newValue = "Inertial dead-reckoning 4.5s",
            detailsEn = "Added a 4.5s dead-reckoning window when tracking target is momentarily lost in the notch filter before active seeker terminal phase.",
            detailsAr = "إضافة نافذة ملاحة تقديرية لمدة 4.5 ثانية عند انقطاع التتبع المؤقت داخل مرشح الرادار قبل دخول المرحلة النهائية للباحث النشط.",
            sourceUrl = "https://warthunder.com/en/game/changelog/current/1640",
            confidence = ConfidenceLevel.OFFICIAL_CONFIRMED,
            isAiTranslated = true
        ),
        ChangelogEntry(
            id = "ch_06",
            vehicleOrWeapon = "Leopard 2A7V",
            category = "Ground / Optics",
            nation = Nation.GERMANY,
            patchVersion = "2.39.0.10",
            date = "2024-09-06",
            changeType = ChangeType.BUFF,
            detailsEn = "ATTICA Gen 3 Commander panoramic thermal sight zoom stepped to 3x - 12x continuous magnification.",
            detailsAr = "تحديث منظار ATTICA الحراري من الجيل الثالث لقائد الدبابة ليدعم تقريباً مستمراً من 3x إلى 12x.",
            sourceUrl = "https://warthunder.com/en/game/changelog/current/1638",
            confidence = ConfidenceLevel.OFFICIAL_CONFIRMED,
            isAiTranslated = true
        )
    )

    val brShifts: List<BRShift> = listOf(
        BRShift(
            id = "br_01",
            vehicleName = "F-15E Strike Eagle",
            vehicleType = "Strike Aircraft",
            nation = Nation.USA,
            patchVersion = "2.41.0",
            date = "2024-10-01",
            oldAB = 13.7,
            newAB = 14.0,
            oldRB = 13.7,
            newRB = 14.0,
            oldSB = 13.7,
            newSB = 14.0,
            reasonEn = "Equipped with 8x AMRAAM and high ordnance payload; moved to top bracket to decompress rank VIII.",
            reasonAr = "تزويد الطائرة بـ 8 صواريخ أمرام وحمولة ذخائر ضخمة؛ نُقلت للفئة الأعلى لفك ضغط رتبة الطائرات الثامنة.",
            sourceUrl = "https://warthunder.com/en/news/battle-rating-changes"
        ),
        BRShift(
            id = "br_02",
            vehicleName = "JAS39C Gripen",
            vehicleType = "Jet Fighter",
            nation = Nation.SWEDEN,
            patchVersion = "2.39.0",
            date = "2024-09-12",
            oldAB = 13.0,
            newAB = 13.3,
            oldRB = 13.3,
            newRB = 13.7,
            oldSB = 13.3,
            newSB = 13.7,
            reasonEn = "Outstanding win rates in Realistic Battles driven by Fox-3 capability and BOL dispenser survivability.",
            reasonAr = "معدلات فوز مرتفعة للغاية في المعارك الواقعية مدفوعة بقدرات صواريخ Fox-3 وبقاء ممتاز بفضل حاويات BOL.",
            sourceUrl = "https://warthunder.com/en/news/battle-rating-changes"
        ),
        BRShift(
            id = "br_03",
            vehicleName = "Su-27 Flanker",
            vehicleType = "Jet Fighter",
            nation = Nation.USSR,
            patchVersion = "2.39.0",
            date = "2024-09-12",
            oldAB = 13.3,
            newAB = 13.7,
            oldRB = 13.3,
            newRB = 13.7,
            oldSB = 13.3,
            newSB = 13.7,
            reasonEn = "Adjusted concurrently with addition of Fox-3 capable platforms across all nations.",
            reasonAr = "تعديل متزامن مع إدخال مقاتلات صواريخ الرادار النشط Fox-3 عبر كافة الدول.",
            sourceUrl = "https://warthunder.com/en/news/battle-rating-changes"
        ),
        BRShift(
            id = "br_04",
            vehicleName = "Pantsir-S1",
            vehicleType = "SPAA / SAM",
            nation = Nation.USSR,
            patchVersion = "2.37.0",
            date = "2024-06-18",
            oldAB = 11.7,
            newAB = 11.7,
            oldRB = 11.3,
            newRB = 11.7,
            oldSB = 11.3,
            newSB = 11.7,
            reasonEn = "Superior radar engagement envelope against high-altitude laser-guided munitions.",
            reasonAr = "تفوق نطاق الاشتباك الراداري ضد الذخائر الموجهة بالليزر القادمة من ارتفاعات شاهقة.",
            sourceUrl = "https://warthunder.com/en/news/battle-rating-changes"
        )
    )

    val initialNews: List<NewsArticle> = listOf(
        NewsArticle(
            id = "news_01",
            titleEn = "Major Update \"Dance of Dragons\" Released!",
            titleAr = "إطلاق التحديث الرئيسي \"رقصة التنانين\" (Dance of Dragons)!",
            summaryEn = "War Thunder's latest major update introduces dozens of new military vehicles, including modern Chinese and Russian multi-role aircraft, advanced avionics updates, and expanded high-tier naval forces.",
            summaryAr = "يقدم أحدث تحديث رئيسي في وور ثاندر عشرات المركبات العسكرية الجديدة، بما في ذلك مقاتلات صينية وروسية حديثة متعددة المهام، وتحديثات شاملة لإلكترونيات الطيران، وتوسيع الأسطول البحري.",
            category = NewsCategory.MAJOR_UPDATE,
            version = "2.39",
            date = "2024-09-10",
            sourceUrl = "https://warthunder.com/en/news/9052-major-update-dance-of-dragons-en",
            isOfficial = true,
            isAiTranslated = true,
            isImportant = true,
            affectedVehicles = listOf("J-10A", "Su-24M", "JF-17", "ZTZ99A")
        ),
        NewsArticle(
            id = "news_02",
            titleEn = "It's Fixed! Update No. 102 - Missile Aerodynamics & Radar Clutter",
            titleAr = "تم إصلاحه! التحديث رقم 102 - ديناميكا الصواريخ والتشويش الراداري",
            summaryEn = "A comprehensive series of micro-adjustments solving target tracking jitter over sea waves, fine-tuning lattice fin drag curves on R-77 missiles, and addressing turret armor welds.",
            summaryAr = "سلسلة شاملة من التعديلات الدقيقة تعالج ارتعاش تتبع الأهداف فوق أمواج البحر، وتضبط منحنيات سحب الزعانف الشبكية لصواريخ R-77، وتصحح لحام دروع الأبراج.",
            category = NewsCategory.HOTFIX,
            version = "2.41.0.32",
            date = "2024-10-08",
            sourceUrl = "https://warthunder.com/en/game/changelog/current/1652",
            isOfficial = true,
            isAiTranslated = true,
            isImportant = false,
            affectedVehicles = listOf("JAS39C Gripen", "R-77", "T-90M \"Proryv\"")
        ),
        NewsArticle(
            id = "news_03",
            titleEn = "Development Blog: Advanced Airborne Radars & Fox-3 Guidance Systems",
            titleAr = "مدونة التطوير: رادارات المقاتلات المتقدمة وأنظمة توجيه Fox-3 النشطة",
            summaryEn = "Detailed engineering breakdown from Gaijin developers explaining the interaction between inertial navigation units, radar datalinks, and active homing pitbull phases.",
            summaryAr = "شرح هندسي تفصيلي من مطوري جايجين يوضح التفاعل بين وحدات الملاحة العطالية، وروابط بيانات الرادار، ومرحلة الإقفال الذاتي النهائي (Pitbull) للرادارات النشطة.",
            category = NewsCategory.DEV_BLOG,
            version = "Engineering Spec",
            date = "2024-08-28",
            sourceUrl = "https://warthunder.com/en/news/8980-development-fox-3-radars-en",
            isOfficial = true,
            isAiTranslated = true,
            isImportant = true,
            affectedVehicles = listOf("AIM-120A", "R-77", "MICA-EM", "F-16C", "Su-27")
        ),
        NewsArticle(
            id = "news_04",
            titleEn = "Dev Server Opening: New Ground Combat Balance & Armor Volumetrics",
            titleAr = "افتتاح خادم التطوير (Dev Server): توازن الدبابات والدروع الحجمية الجديدة",
            summaryEn = "Testing session for upcoming changes to modern MBT spall liners, automatic target tracking responsiveness, and composite plate density evaluations.",
            summaryAr = "جلسة اختبار للتعديلات القادمة على بطانات كبح الشظايا لدبابات القتال الحديثة، واستجابة التتبع التلقائي للأهداف، وكثافة صفائح التدريع المركب.",
            category = NewsCategory.DEV_SERVER,
            version = "2.41 Dev",
            date = "2024-10-04",
            sourceUrl = "https://warthunder.com/en/news/dev-server-testing",
            isOfficial = true,
            isAiTranslated = true,
            isImportant = false,
            affectedVehicles = listOf("Leopard 2A7V", "M1A2 SEP v2", "T-90M \"Proryv\"")
        )
    )

    val notifications: List<NotificationItem> = listOf(
        NotificationItem(
            id = "notif_01",
            titleEn = "Gripen Alert: Radar Clutter Notch Buffed",
            titleAr = "تنبيه غريبن: تحسين تتبع الرادار فوق التضاريس والبحار",
            messageEn = "JAS39C Gripen received a radar notch filter velocity update in patch 2.41.0.32, improving tracking lock over sea.",
            messageAr = "حصلت مقاتلة JAS39C Gripen على تحديث لمرشح حجب الرادار في التحديث 2.41.0.32 مما يحسن ثبات الإقفال فوق البحر.",
            timestamp = "2 hours ago",
            type = ChangeType.BUFF,
            vehicleId = "jas39c_gripen",
            isRead = false
        ),
        NotificationItem(
            id = "notif_02",
            titleEn = "T-90M Relikt ERA Side Backing Confirmed",
            titleAr = "تأكيد دروع ريليكت الجانبية لدبابة T-90M",
            messageEn = "Relikt side ERA plate backing fixed to 15mm high hardness steel in patch 2.41.0.28.",
            messageAr = "تم تصحيح سماكة الصفيحة الفولاذية خلف دروع ريليكت الجانبية إلى 15 ملم فولاذ صلب في التحديث 2.41.0.28.",
            timestamp = "1 day ago",
            type = ChangeType.BUFF,
            vehicleId = "t90m_proryv",
            isRead = false
        ),
        NotificationItem(
            id = "notif_03",
            titleEn = "New Battle Rating Adjustment Released",
            titleAr = "صدور جدول تعديلات تصنيف المعركة (BR) الجديد",
            messageEn = "F-15E Strike Eagle raised to BR 14.0 in Realistic Battles to ensure top-tier matchmaking balance.",
            messageAr = "رفع تصنيف مقاتلة F-15E Strike Eagle إلى 14.0 في المعارك الواقعية لضمان توازن التوفيق القتالي.",
            timestamp = "3 days ago",
            type = ChangeType.BR_CHANGE,
            vehicleId = "f15e_strike_eagle",
            isRead = true
        )
    )
}
