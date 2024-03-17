package com.nayya.workhub.data

import com.nayya.workhub.domain.entity.filter_category.CategoryVacanciesEntity
import com.nayya.workhub.domain.repo.CategoryVacanciesRepo

class CategoryVacanciesRepoImpl : CategoryVacanciesRepo {
    override fun getCategoryVacancies(callback: (List<CategoryVacanciesEntity>) -> Unit) {
        val fakeData = getCategory1()
        callback(fakeData)
    }

//    private fun getCategory(): List<CategoryVacanciesEntity> {
//        return listOf(
//            CategoryVacanciesEntity(1, level = 1, categoryName = "IT", parentId = null),
//            CategoryVacanciesEntity(2, level = 2, categoryName = "Android", parentId = null),
//            CategoryVacanciesEntity(3, level = 2, categoryName = "Java", parentId = null),
//            CategoryVacanciesEntity(
//                4,
//                level = 2,
//                categoryName = "Analiza biznesowa i systemowa",
//                parentId = null
//            ),
//            CategoryVacanciesEntity(5, level = 2, categoryName = "Architektura", parentId = null),
//            CategoryVacanciesEntity(
//                6,
//                level = 2,
//                categoryName = "Kontrola jakości",
//                parentId = null
//            ),
//            CategoryVacanciesEntity(7, level = 2, categoryName = "Programowanie", parentId = null),
//            CategoryVacanciesEntity(8, level = 2, categoryName = "Testowanie", parentId = null),
//            CategoryVacanciesEntity(
//                9,
//                level = 2,
//                categoryName = "Zarządzanie projektem/produktem",
//                parentId = null
//            ),
//        )
//    }

    private fun getCategory1(): List<CategoryVacanciesEntity> {
        return listOf(
            CategoryVacanciesEntity(
                id = 5001,
                level = 1,
                categoryName = "Administracja biurowa",
                parentId = null
            ),
            CategoryVacanciesEntity(
                id = 5001001,
                level = 2,
                categoryName = "Sekretariat / Recepcja",
                parentId = 5001
            ),
            CategoryVacanciesEntity(
                id = 5001002,
                level = 2,
                categoryName = "Stanowiska asystenckie",
                parentId = 5001
            ),
            CategoryVacanciesEntity(
                id = 5001003,
                level = 2,
                categoryName = "Tłumaczenia / Korekta",
                parentId = 5001
            ),
            CategoryVacanciesEntity(
                id = 5001004,
                level = 2,
                categoryName = "Wprowadzanie / Przetwarzanie danych",
                parentId = 5001
            ),
            CategoryVacanciesEntity(
                id = 5001005,
                level = 2,
                categoryName = "Wsparcie administracyjne",
                parentId = 5001
            ),
            CategoryVacanciesEntity(
                id = 5001006,
                level = 2,
                categoryName = "Zarządzanie biurem i administracją",
                parentId = 5001
            ),
            CategoryVacanciesEntity(
                id = 5002,
                level = 1,
                categoryName = "Badania i rozwój",
                parentId = null
            ),
            CategoryVacanciesEntity(
                id = 5002001,
                level = 2,
                categoryName = "Business Intelligence / Data Warehouse",
                parentId = 5002
            ),
            CategoryVacanciesEntity(
                id = 5002002,
                level = 2,
                categoryName = "Chemia przemysłowa",
                parentId = 5002
            ),
            CategoryVacanciesEntity(
                id = 5002003,
                level = 2,
                categoryName = "Farmaceutyka / Biotechnologia",
                parentId = 5002
            ),
            CategoryVacanciesEntity(
                id = 5002004,
                level = 2,
                categoryName = "FMCG",
                parentId = 5002
            ),
            CategoryVacanciesEntity(
                id = 5002006,
                level = 2,
                categoryName = "Tworzywa sztuczne",
                parentId = 5002
            ),
            CategoryVacanciesEntity(
                id = 5003,
                level = 1,
                categoryName = "Bankowość",
                parentId = null
            ),
            CategoryVacanciesEntity(
                id = 5003001,
                level = 2,
                categoryName = "Analiza / Ryzyko",
                parentId = 5003
            ),
            CategoryVacanciesEntity(
                id = 5003002,
                level = 2,
                categoryName = "Bankowość detaliczna",
                parentId = 5003
            ),
            CategoryVacanciesEntity(
                id = 5003003,
                level = 2,
                categoryName = "Bankowość inwestycyjna",
                parentId = 5003
            ),
            CategoryVacanciesEntity(
                id = 5003004,
                level = 2,
                categoryName = "Bankowość korporacyjna / SME",
                parentId = 5003
            ),
            CategoryVacanciesEntity(
                id = 5003005,
                level = 2,
                categoryName = "Pośrednictwo finansowe",
                parentId = 5003
            ),
            CategoryVacanciesEntity(
                id = 5004,
                level = 1,
                categoryName = "BHP / Ochrona środowiska",
                parentId = null
            ),
            CategoryVacanciesEntity(
                id = 5004001,
                level = 2,
                categoryName = "Inżynieria",
                parentId = 5004
            ),
            CategoryVacanciesEntity(
                id = 5004002,
                level = 2,
                categoryName = "Nadzór",
                parentId = 5004
            ),
            CategoryVacanciesEntity(
                id = 5004003,
                level = 2,
                categoryName = "Specjaliści / Konsultanci",
                parentId = 5004
            ),
            CategoryVacanciesEntity(
                id = 5005,
                level = 1,
                categoryName = "Budownictwo",
                parentId = null
            ),
            CategoryVacanciesEntity(
                id = 5005001,
                level = 2,
                categoryName = "Architektura / Projektowanie",
                parentId = 5005
            ),
            CategoryVacanciesEntity(
                id = 5005002,
                level = 2,
                categoryName = "Ekologiczne",
                parentId = 5005
            ),
            CategoryVacanciesEntity(
                id = 5005003,
                level = 2,
                categoryName = "Energetyczne",
                parentId = 5005
            ),
            CategoryVacanciesEntity(
                id = 5005004,
                level = 2,
                categoryName = "Infrastrukturalne",
                parentId = 5005
            ),
            CategoryVacanciesEntity(
                id = 5005005,
                level = 2,
                categoryName = "Instalacje",
                parentId = 5005
            ),
            CategoryVacanciesEntity(
                id = 5005006,
                level = 2,
                categoryName = "Mieszkaniowe / Przemysłowe",
                parentId = 5005
            ),
            CategoryVacanciesEntity(
                id = 5006,
                level = 1,
                categoryName = "Call Center",
                parentId = null
            ),
            CategoryVacanciesEntity(
                id = 5037,
                level = 1,
                categoryName = "Doradztwo / Konsulting",
                parentId = null
            ),
            CategoryVacanciesEntity(
                id = 5006001,
                level = 2,
                categoryName = "Przychodzące",
                parentId = 5006
            ),
            CategoryVacanciesEntity(
                id = 5006002,
                level = 2,
                categoryName = "Wychodzące",
                parentId = 5006
            ),
            CategoryVacanciesEntity(
                id = 5036,
                level = 1,
                categoryName = "Energetyka",
                parentId = null
            ),
            CategoryVacanciesEntity(
                id = 5007,
                level = 1,
                categoryName = "Edukacja / Szkolenia",
                parentId = null
            ),
            CategoryVacanciesEntity(
                id = 5007001,
                level = 2,
                categoryName = "Nauka języków obcych",
                parentId = 5007
            ),
            CategoryVacanciesEntity(
                id = 5007002,
                level = 2,
                categoryName = "Szkolenia / Rozwój osobisty",
                parentId = 5007
            ),
            CategoryVacanciesEntity(
                id = 5007003,
                level = 2,
                categoryName = "Szkolnictwo",
                parentId = 5007
            ),
            CategoryVacanciesEntity(
                id = 5008,
                level = 1,
                categoryName = "Finanse / Ekonomia",
                parentId = null
            ),
            CategoryVacanciesEntity(
                id = 5008001,
                level = 2,
                categoryName = "Audyt / Podatki",
                parentId = 5008
            ),
            CategoryVacanciesEntity(
                id = 5008002,
                level = 2,
                categoryName = "Doradztwo / Konsulting",
                parentId = 5008
            ),
            CategoryVacanciesEntity(
                id = 5008004,
                level = 2,
                categoryName = "Kontroling",
                parentId = 5008
            ),
            CategoryVacanciesEntity(
                id = 5008005,
                level = 2,
                categoryName = "Księgowość",
                parentId = 5008
            ),
            CategoryVacanciesEntity(
                id = 5008006,
                level = 2,
                categoryName = "Rynki kapitałowe",
                parentId = 5008
            ),
            CategoryVacanciesEntity(
                id = 5009,
                level = 1,
                categoryName = "Franczyza / Własny biznes",
                parentId = null
            ),
            CategoryVacanciesEntity(
                id = 5009001,
                level = 2,
                categoryName = "Artykuły przemysłowe / AGD / RTV",
                parentId = 5009
            ),
            CategoryVacanciesEntity(
                id = 5009002,
                level = 2,
                categoryName = "Artykuły spożywcze",
                parentId = 5009
            ),
            CategoryVacanciesEntity(
                id = 5009003,
                level = 2,
                categoryName = "Chemia / Kosmetyki",
                parentId = 5009
            ),
            CategoryVacanciesEntity(
                id = 5009004,
                level = 2,
                categoryName = "Doradztwo / Konsulting",
                parentId = 5009
            ),
            CategoryVacanciesEntity(
                id = 5009005,
                level = 2,
                categoryName = "Edukacja / Szkolenia",
                parentId = 5009
            ),
            CategoryVacanciesEntity(
                id = 5009006,
                level = 2,
                categoryName = "Finanse / Bankowość",
                parentId = 5009
            ),
            CategoryVacanciesEntity(
                id = 5009007,
                level = 2,
                categoryName = "Gastronomia",
                parentId = 5009
            ),
            CategoryVacanciesEntity(
                id = 5009008,
                level = 2,
                categoryName = "Hotelarstwo / Turystyka",
                parentId = 5009
            ),
            CategoryVacanciesEntity(
                id = 5009009,
                level = 2,
                categoryName = "IT / Telekomunikacja",
                parentId = 5009
            ),
            CategoryVacanciesEntity(
                id = 5009010,
                level = 2,
                categoryName = "Motoryzacja",
                parentId = 5009
            ),
            CategoryVacanciesEntity(
                id = 5009011,
                level = 2,
                categoryName = "Nieruchomości",
                parentId = 5009
            ),
            CategoryVacanciesEntity(
                id = 5009012,
                level = 2,
                categoryName = "Odzież / Dodatki / Biżuteria",
                parentId = 5009
            ),
            CategoryVacanciesEntity(
                id = 5009013,
                level = 2,
                categoryName = "Ubezpieczenia",
                parentId = 5009
            ),
            CategoryVacanciesEntity(
                id = 5009014,
                level = 2,
                categoryName = "Usługi dla klienta indywidualnego",
                parentId = 5009
            ),
            CategoryVacanciesEntity(
                id = 5009015,
                level = 2,
                categoryName = "Inne",
                parentId = 5009
            ),
            CategoryVacanciesEntity(
                id = 5010,
                level = 1,
                categoryName = "Hotelarstwo / Gastronomia / Turystyka",
                parentId = null
            ),
            CategoryVacanciesEntity(
                id = 5010001,
                level = 2,
                categoryName = "Hotelarstwo",
                parentId = 5010
            ),
            CategoryVacanciesEntity(
                id = 5010002,
                level = 2,
                categoryName = "Katering / Restauracje / Gastronomia",
                parentId = 5010
            ),
            CategoryVacanciesEntity(
                id = 5010003,
                level = 2,
                categoryName = "Turystyka",
                parentId = 5010
            ),
            CategoryVacanciesEntity(
                id = 5011,
                level = 1,
                categoryName = "Human Resources / Zasoby ludzkie",
                parentId = null
            ),
            CategoryVacanciesEntity(id = 5011001, level = 2, categoryName = "BHP", parentId = 5011),
            CategoryVacanciesEntity(
                id = 5011002,
                level = 2,
                categoryName = "Kadry / Wynagrodzenia",
                parentId = 5011
            ),
            CategoryVacanciesEntity(
                id = 5011003,
                level = 2,
                categoryName = "Rekrutacja / Employer Branding",
                parentId = 5011
            ),
            CategoryVacanciesEntity(
                id = 5011004,
                level = 2,
                categoryName = "Szkolenia / Rozwój",
                parentId = 5011
            ),
            CategoryVacanciesEntity(
                id = 5011005,
                level = 2,
                categoryName = "Zarządzanie HR",
                parentId = 5011
            ),
            CategoryVacanciesEntity(
                id = 5013,
                level = 1,
                categoryName = "Internet / e-Commerce / Nowe media",
                parentId = null
            ),
            CategoryVacanciesEntity(
                id = 5013001,
                level = 2,
                categoryName = "E-marketing / SEM / SEO",
                parentId = 5013
            ),
            CategoryVacanciesEntity(
                id = 5013002,
                level = 2,
                categoryName = "Media społecznościowe",
                parentId = 5013
            ),
            CategoryVacanciesEntity(
                id = 5013003,
                level = 2,
                categoryName = "Projektowanie",
                parentId = 5013
            ),
            CategoryVacanciesEntity(
                id = 5013004,
                level = 2,
                categoryName = "Sprzedaż / e-Commerce",
                parentId = 5013
            ),
            CategoryVacanciesEntity(
                id = 5013005,
                level = 2,
                categoryName = "Tworzenie stron WWW / Technologie internetowe",
                parentId = 5013
            ),
            CategoryVacanciesEntity(
                id = 5014,
                level = 1,
                categoryName = "Inżynieria",
                parentId = null
            ),
            CategoryVacanciesEntity(
                id = 5014001,
                level = 2,
                categoryName = "Automatyka",
                parentId = 5014
            ),
            CategoryVacanciesEntity(
                id = 5014002,
                level = 2,
                categoryName = "Elektronika / Elektryka",
                parentId = 5014
            ),
            CategoryVacanciesEntity(
                id = 5014003,
                level = 2,
                categoryName = "Energetyka konwencjonalna i odnawialna",
                parentId = 5014
            ),
            CategoryVacanciesEntity(
                id = 5014005,
                level = 2,
                categoryName = "Mechanika",
                parentId = 5014
            ),
            CategoryVacanciesEntity(
                id = 5014006,
                level = 2,
                categoryName = "Monterzy / Serwisanci",
                parentId = 5014
            ),
            CategoryVacanciesEntity(
                id = 5014007,
                level = 2,
                categoryName = "Telekomunikacja",
                parentId = 5014
            ),
            CategoryVacanciesEntity(
                id = 5014008,
                level = 2,
                categoryName = "Motoryzacja",
                parentId = 5014
            ),
            CategoryVacanciesEntity(
                id = 5015,
                level = 1,
                categoryName = "IT - Administracja",
                parentId = null
            ),
            CategoryVacanciesEntity(
                id = 5015001,
                level = 2,
                categoryName = "Administrowanie bazami danych i storage",
                parentId = 5015
            ),
            CategoryVacanciesEntity(
                id = 5015002,
                level = 2,
                categoryName = "Administrowanie sieciami",
                parentId = 5015
            ),
            CategoryVacanciesEntity(
                id = 5015003,
                level = 2,
                categoryName = "Administrowanie systemami",
                parentId = 5015
            ),
            CategoryVacanciesEntity(
                id = 5015004,
                level = 2,
                categoryName = "Bezpieczeństwo / Audyt",
                parentId = 5015
            ),
            CategoryVacanciesEntity(
                id = 5015005,
                level = 2,
                categoryName = "Wdrożenia ERP",
                parentId = 5015
            ),
            CategoryVacanciesEntity(
                id = 5015006,
                level = 2,
                categoryName = "Wsparcie techniczne / Helpdesk",
                parentId = 5015
            ),
            CategoryVacanciesEntity(
                id = 5015007,
                level = 2,
                categoryName = "Zarządzanie usługami",
                parentId = 5015
            ),
            CategoryVacanciesEntity(
                id = 5016,
                level = 1,
                categoryName = "IT - Rozwój oprogramowania",
                parentId = null
            ),
            CategoryVacanciesEntity(
                id = 5016001,
                level = 2,
                categoryName = "Analiza biznesowa i systemowa",
                parentId = 5016
            ),
            CategoryVacanciesEntity(
                id = 5016002,
                level = 2,
                categoryName = "Architektura",
                parentId = 5016
            ),
            CategoryVacanciesEntity(
                id = 5034,
                level = 1,
                categoryName = "Kontrola jakości",
                parentId = null
            ),
            CategoryVacanciesEntity(
                id = 5016003,
                level = 2,
                categoryName = "Programowanie",
                parentId = 5016
            ),
            CategoryVacanciesEntity(
                id = 5016004,
                level = 2,
                categoryName = "Testowanie",
                parentId = 5016
            ),
            CategoryVacanciesEntity(
                id = 5016005,
                level = 2,
                categoryName = "Zarządzanie projektem/produktem",
                parentId = 5016
            ),
            CategoryVacanciesEntity(
                id = 5017,
                level = 1,
                categoryName = "Łańcuch dostaw",
                parentId = null
            ),
            CategoryVacanciesEntity(
                id = 5016006,
                level = 2,
                categoryName = "Projektowanie UX/UI",
                parentId = 5016
            ),
            CategoryVacanciesEntity(
                id = 5017001,
                level = 2,
                categoryName = "Logistyka / Optymalizacja",
                parentId = 5017
            ),
            CategoryVacanciesEntity(
                id = 5017002,
                level = 2,
                categoryName = "Magazynowanie",
                parentId = 5017
            ),
            CategoryVacanciesEntity(
                id = 5017003,
                level = 2,
                categoryName = "Planowanie / Prognozowanie",
                parentId = 5017
            ),
            CategoryVacanciesEntity(
                id = 5017004,
                level = 2,
                categoryName = "Transport i zarządzanie flotą",
                parentId = 5017
            ),
            CategoryVacanciesEntity(
                id = 5018,
                level = 1,
                categoryName = "Marketing",
                parentId = null
            ),
            CategoryVacanciesEntity(
                id = 5018001,
                level = 2,
                categoryName = "Badania marketingowe",
                parentId = 5018
            ),
            CategoryVacanciesEntity(
                id = 5018002,
                level = 2,
                categoryName = "Kampanie marketingowe / Eventy",
                parentId = 5018
            ),
            CategoryVacanciesEntity(
                id = 5018003,
                level = 2,
                categoryName = "Komunikacja marketingowa",
                parentId = 5018
            ),
            CategoryVacanciesEntity(
                id = 5018004,
                level = 2,
                categoryName = "Marketing bezpośredni",
                parentId = 5018
            ),
            CategoryVacanciesEntity(
                id = 5018005,
                level = 2,
                categoryName = "Marketing handlowy",
                parentId = 5018
            ),
            CategoryVacanciesEntity(
                id = 5018006,
                level = 2,
                categoryName = "Marketing internetowy i mobilny",
                parentId = 5018
            ),
            CategoryVacanciesEntity(
                id = 5018007,
                level = 2,
                categoryName = "Zarządzanie marką",
                parentId = 5018
            ),
            CategoryVacanciesEntity(
                id = 5018008,
                level = 2,
                categoryName = "Zarządzanie marketingiem",
                parentId = 5018
            ),
            CategoryVacanciesEntity(
                id = 5018009,
                level = 2,
                categoryName = "Zarządzanie produktem",
                parentId = 5018
            ),
            CategoryVacanciesEntity(
                id = 5019,
                level = 1,
                categoryName = "Media / Sztuka / Rozrywka",
                parentId = null
            ),
            CategoryVacanciesEntity(
                id = 5019001,
                level = 2,
                categoryName = "Organizacja i obsługa imprez",
                parentId = 5019
            ),
            CategoryVacanciesEntity(
                id = 5019002,
                level = 2,
                categoryName = "Planowanie i zakup mediów",
                parentId = 5019
            ),
            CategoryVacanciesEntity(
                id = 5019003,
                level = 2,
                categoryName = "Produkcja i realizacja",
                parentId = 5019
            ),
            CategoryVacanciesEntity(
                id = 5019004,
                level = 2,
                categoryName = "Redakcja / Dziennikarstwo",
                parentId = 5019
            ),
            CategoryVacanciesEntity(
                id = 5020,
                level = 1,
                categoryName = "Nieruchomości",
                parentId = null
            ),
            CategoryVacanciesEntity(
                id = 5020001,
                level = 2,
                categoryName = "Ekspansja / Rozwój / Zarządzanie projektem",
                parentId = 5020
            ),
            CategoryVacanciesEntity(
                id = 5020002,
                level = 2,
                categoryName = "Wynajem/Wycena",
                parentId = 5020
            ),
            CategoryVacanciesEntity(
                id = 5020003,
                level = 2,
                categoryName = "Utrzymanie / Zarządzanie nieruchomościami",
                parentId = 5020
            ),
            CategoryVacanciesEntity(
                id = 5021,
                level = 1,
                categoryName = "Obsługa klienta",
                parentId = null
            ),
            CategoryVacanciesEntity(
                id = 5021001,
                level = 2,
                categoryName = "Energia / Środowisko",
                parentId = 5021
            ),
            CategoryVacanciesEntity(
                id = 5021002,
                level = 2,
                categoryName = "Farmacja / Medycyna",
                parentId = 5021
            ),
            CategoryVacanciesEntity(
                id = 5021003,
                level = 2,
                categoryName = "Finanse / Bankowość / Ubezpieczenia",
                parentId = 5021
            ),
            CategoryVacanciesEntity(
                id = 5021004,
                level = 2,
                categoryName = "Inżynieria / Technika / Produkcja",
                parentId = 5021
            ),
            CategoryVacanciesEntity(
                id = 5021005,
                level = 2,
                categoryName = "IT / Telekomunikacja",
                parentId = 5021
            ),
            CategoryVacanciesEntity(
                id = 5021006,
                level = 2,
                categoryName = "Sprzedawcy",
                parentId = 5021
            ),
            CategoryVacanciesEntity(
                id = 5021007,
                level = 2,
                categoryName = "Marketing / Reklama / Media",
                parentId = 5021
            ),
            CategoryVacanciesEntity(
                id = 5021008,
                level = 2,
                categoryName = "Motoryzacja / Transport",
                parentId = 5021
            ),
            CategoryVacanciesEntity(
                id = 5021009,
                level = 2,
                categoryName = "Turystyka / Hotelarstwo / Katering",
                parentId = 5021
            ),
            CategoryVacanciesEntity(
                id = 5021010,
                level = 2,
                categoryName = "Usługi profesjonalne",
                parentId = 5021
            ),
            CategoryVacanciesEntity(
                id = 5022,
                level = 1,
                categoryName = "Praca fizyczna",
                parentId = null
            ),
            CategoryVacanciesEntity(
                id = 5022008,
                level = 2,
                categoryName = "Kurierzy / Dostawcy",
                parentId = 5022
            ),
            CategoryVacanciesEntity(
                id = 5022003,
                level = 2,
                categoryName = "Pracownicy budowlani",
                parentId = 5022
            ),
            CategoryVacanciesEntity(
                id = 5022004,
                level = 2,
                categoryName = "Pracownicy magazynowi",
                parentId = 5022
            ),
            CategoryVacanciesEntity(
                id = 5022005,
                level = 2,
                categoryName = "Pracownicy ochrony",
                parentId = 5022
            ),
            CategoryVacanciesEntity(
                id = 5022006,
                level = 2,
                categoryName = "Pracownicy rolni",
                parentId = 5022
            ),
            CategoryVacanciesEntity(
                id = 5022007,
                level = 2,
                categoryName = "Pracownicy gastronomii",
                parentId = 5022
            ),
            CategoryVacanciesEntity(id = 5023, level = 1, categoryName = "Prawo", parentId = null),
            CategoryVacanciesEntity(
                id = 5023001,
                level = 2,
                categoryName = "Prawnik",
                parentId = 5023
            ),
            CategoryVacanciesEntity(
                id = 5023002,
                level = 2,
                categoryName = "Specjaliści",
                parentId = 5023
            ),
            CategoryVacanciesEntity(
                id = 5023003,
                level = 2,
                categoryName = "Windykacja",
                parentId = 5023
            ),
            CategoryVacanciesEntity(
                id = 5023004,
                level = 2,
                categoryName = "Wsparcie usług prawnych",
                parentId = 5023
            ),
            CategoryVacanciesEntity(
                id = 5024,
                level = 1,
                categoryName = "Produkcja",
                parentId = null
            ),
            CategoryVacanciesEntity(
                id = 5024001,
                level = 2,
                categoryName = "Optymalizacja procesu produkcji",
                parentId = 5024
            ),
            CategoryVacanciesEntity(
                id = 5024002,
                level = 2,
                categoryName = "Pracownicy produkcyjni",
                parentId = 5024
            ),
            CategoryVacanciesEntity(
                id = 5024003,
                level = 2,
                categoryName = "Utrzymanie ruchu",
                parentId = 5024
            ),
            CategoryVacanciesEntity(
                id = 5024004,
                level = 2,
                categoryName = "Zarządzanie produkcją",
                parentId = 5024
            ),
            CategoryVacanciesEntity(
                id = 5025,
                level = 1,
                categoryName = "Public Relations",
                parentId = null
            ),
            CategoryVacanciesEntity(
                id = 5025001,
                level = 2,
                categoryName = "Wewnętrzny PR",
                parentId = 5025
            ),
            CategoryVacanciesEntity(
                id = 5025002,
                level = 2,
                categoryName = "Zewnętrzny PR",
                parentId = 5025
            ),
            CategoryVacanciesEntity(
                id = 5026,
                level = 1,
                categoryName = "Reklama / Grafika / Kreacja / Fotografia",
                parentId = null
            ),
            CategoryVacanciesEntity(
                id = 5026001,
                level = 2,
                categoryName = "Animacja komputerowa / Webdesign",
                parentId = 5026
            ),
            CategoryVacanciesEntity(
                id = 5026002,
                level = 2,
                categoryName = "Grafika / Fotografia / DTP",
                parentId = 5026
            ),
            CategoryVacanciesEntity(
                id = 5026003,
                level = 2,
                categoryName = "Reklama / Copywriting / Kreacja",
                parentId = 5026
            ),
            CategoryVacanciesEntity(
                id = 5027,
                level = 1,
                categoryName = "Sektor publiczny",
                parentId = null
            ),
            CategoryVacanciesEntity(
                id = 5027001,
                level = 2,
                categoryName = "Bezpieczeństwo / Porządek Publiczny",
                parentId = 5027
            ),
            CategoryVacanciesEntity(
                id = 5027002,
                level = 2,
                categoryName = "Kontrola / Nadzór",
                parentId = 5027
            ),
            CategoryVacanciesEntity(
                id = 5027003,
                level = 2,
                categoryName = "Specjaliści / Urzędnicy",
                parentId = 5027
            ),
            CategoryVacanciesEntity(
                id = 5028,
                level = 1,
                categoryName = "Sprzedaż",
                parentId = null
            ),
            CategoryVacanciesEntity(
                id = 5028001,
                level = 2,
                categoryName = "Energia / Środowisko",
                parentId = 5028
            ),
            CategoryVacanciesEntity(
                id = 5028002,
                level = 2,
                categoryName = "Farmacja / Medycyna",
                parentId = 5028
            ),
            CategoryVacanciesEntity(
                id = 5028003,
                level = 2,
                categoryName = "Finanse / Bankowość / Ubezpieczenia",
                parentId = 5028
            ),
            CategoryVacanciesEntity(
                id = 5028004,
                level = 2,
                categoryName = "Inżynieria / Technika / Produkcja",
                parentId = 5028
            ),
            CategoryVacanciesEntity(
                id = 5028005,
                level = 2,
                categoryName = "IT / Telekomunikacja",
                parentId = 5028
            ),
            CategoryVacanciesEntity(
                id = 5028006,
                level = 2,
                categoryName = "Marketing / Reklama / Media",
                parentId = 5028
            ),
            CategoryVacanciesEntity(
                id = 5028012,
                level = 2,
                categoryName = "Merchandising",
                parentId = 5028
            ),
            CategoryVacanciesEntity(
                id = 5028007,
                level = 2,
                categoryName = "Motoryzacja / Transport",
                parentId = 5028
            ),
            CategoryVacanciesEntity(
                id = 5028008,
                level = 2,
                categoryName = "Nieruchomości / Budownictwo",
                parentId = 5028
            ),
            CategoryVacanciesEntity(
                id = 5028013,
                level = 2,
                categoryName = "Sieci handlowe",
                parentId = 5028
            ),
            CategoryVacanciesEntity(
                id = 5028014,
                level = 2,
                categoryName = "Artykuły przemysłowe / AGD / RTV",
                parentId = 5028
            ),
            CategoryVacanciesEntity(
                id = 5028009,
                level = 2,
                categoryName = "Rolnictwo",
                parentId = 5028
            ),
            CategoryVacanciesEntity(
                id = 5028015,
                level = 2,
                categoryName = "Artykuły spożywcze / Alkohol / Tytoń",
                parentId = 5028
            ),
            CategoryVacanciesEntity(
                id = 5028010,
                level = 2,
                categoryName = "Turystyka / Hotelarstwo / Katering",
                parentId = 5028
            ),
            CategoryVacanciesEntity(
                id = 5028016,
                level = 2,
                categoryName = "Chemia / Kosmetyki",
                parentId = 5028
            ),
            CategoryVacanciesEntity(
                id = 5028011,
                level = 2,
                categoryName = "Usługi profesjonalne",
                parentId = 5028
            ),
            CategoryVacanciesEntity(
                id = 5028017,
                level = 2,
                categoryName = "Odzież / Dodatki / Biżuteria",
                parentId = 5028
            ),
            CategoryVacanciesEntity(
                id = 5028018,
                level = 2,
                categoryName = "Wyposażenie domu i biura",
                parentId = 5028
            ),
            CategoryVacanciesEntity(
                id = 5028019,
                level = 2,
                categoryName = "Inne",
                parentId = 5028
            ),
            CategoryVacanciesEntity(
                id = 5031,
                level = 1,
                categoryName = "Transport / Spedycja / Logistyka",
                parentId = null
            ),
            CategoryVacanciesEntity(
                id = 5031001,
                level = 2,
                categoryName = "Agencje celne",
                parentId = 5031
            ),
            CategoryVacanciesEntity(
                id = 5031002,
                level = 2,
                categoryName = "Spedycja",
                parentId = 5031
            ),
            CategoryVacanciesEntity(
                id = 5031003,
                level = 2,
                categoryName = "Usługi kurierskie",
                parentId = 5031
            ),
            CategoryVacanciesEntity(
                id = 5032,
                level = 1,
                categoryName = "Ubezpieczenia",
                parentId = null
            ),
            CategoryVacanciesEntity(
                id = 5032001,
                level = 2,
                categoryName = "Analizy / Ryzyko / Aktuariat",
                parentId = 5032
            ),
            CategoryVacanciesEntity(
                id = 5032002,
                level = 2,
                categoryName = "Ubezpieczenia majątkowe",
                parentId = 5032
            ),
            CategoryVacanciesEntity(
                id = 5032003,
                level = 2,
                categoryName = "Ubezpieczenia na życie",
                parentId = 5032
            ),
            CategoryVacanciesEntity(id = 5033, level = 1, categoryName = "Zakupy", parentId = null),
            CategoryVacanciesEntity(
                id = 5033001,
                level = 2,
                categoryName = "Category Management",
                parentId = 5033
            ),
            CategoryVacanciesEntity(
                id = 5033002,
                level = 2,
                categoryName = "Nieprodukcyjne / Usługi",
                parentId = 5033
            ),
            CategoryVacanciesEntity(
                id = 5033003,
                level = 2,
                categoryName = "Produkcyjne",
                parentId = 5033
            ),
            CategoryVacanciesEntity(
                id = 5034001,
                level = 2,
                categoryName = "Zarządzanie jakością",
                parentId = 5034
            ),
            CategoryVacanciesEntity(
                id = 5034002,
                level = 2,
                categoryName = "Zapewnienie jakości",
                parentId = 5034
            ),
            CategoryVacanciesEntity(
                id = 5035,
                level = 1,
                categoryName = "Zdrowie / Uroda / Rekreacja",
                parentId = null
            ),
            CategoryVacanciesEntity(
                id = 5035001,
                level = 2,
                categoryName = "Apteka",
                parentId = 5035
            ),
            CategoryVacanciesEntity(
                id = 5035003,
                level = 2,
                categoryName = "Kosmetyka / Fryzjerstwo",
                parentId = 5035
            ),
            CategoryVacanciesEntity(
                id = 5036001,
                level = 2,
                categoryName = "Budownictwo",
                parentId = 5036
            ),
            CategoryVacanciesEntity(
                id = 5035004,
                level = 2,
                categoryName = "Lekarze / Opieka medyczna",
                parentId = 5035
            ),
            CategoryVacanciesEntity(
                id = 5036002,
                level = 2,
                categoryName = "Konwencjonalna",
                parentId = 5036
            ),
            CategoryVacanciesEntity(
                id = 5035005,
                level = 2,
                categoryName = "Sport / Rekreacja",
                parentId = 5035
            ),
            CategoryVacanciesEntity(
                id = 5036003,
                level = 2,
                categoryName = "Nafta i gaz",
                parentId = 5036
            ),
            CategoryVacanciesEntity(
                id = 5036004,
                level = 2,
                categoryName = "Odnawialna",
                parentId = 5036
            ),
            CategoryVacanciesEntity(
                id = 5037001,
                level = 2,
                categoryName = "Finanse",
                parentId = 5037
            ),
            CategoryVacanciesEntity(
                id = 5037002,
                level = 2,
                categoryName = "Podatki / prawo",
                parentId = 5037
            ),
            CategoryVacanciesEntity(
                id = 5037003,
                level = 2,
                categoryName = "Sektor publiczny",
                parentId = 5037
            ),
            CategoryVacanciesEntity(
                id = 5037004,
                level = 2,
                categoryName = "IT/Telekomunikacja",
                parentId = 5037
            ),
            CategoryVacanciesEntity(
                id = 5037005,
                level = 2,
                categoryName = "Biznes/Strategia",
                parentId = 5037
            ),
            CategoryVacanciesEntity(
                id = 5037006,
                level = 2,
                categoryName = "Inne",
                parentId = 5037
            ),
            CategoryVacanciesEntity(
                id = 5014009,
                level = 2,
                categoryName = "Lotnictwo",
                parentId = 5014
            ),
            CategoryVacanciesEntity(
                id = 5014010,
                level = 2,
                categoryName = "Projektowanie",
                parentId = 5014
            ),
            CategoryVacanciesEntity(
                id = 5014011,
                level = 2,
                categoryName = "Konstrukcja / Technologie",
                parentId = 5014
            ),
            CategoryVacanciesEntity(
                id = 5027004,
                level = 2,
                categoryName = "Zamówienia publiczne",
                parentId = 5027
            ),
            CategoryVacanciesEntity(
                id = 5008007,
                level = 2,
                categoryName = "Analiza",
                parentId = 5008
            ),
            CategoryVacanciesEntity(
                id = 5018010,
                level = 2,
                categoryName = "Inne",
                parentId = 5018
            ),
            CategoryVacanciesEntity(
                id = 5022009,
                level = 2,
                categoryName = "Mechanicy / Blacharze / Lakiernicy",
                parentId = 5022
            ),
            CategoryVacanciesEntity(
                id = 5022010,
                level = 2,
                categoryName = "Monterzy / Serwisanci / Elektrycy",
                parentId = 5022
            ),
            CategoryVacanciesEntity(
                id = 5022011,
                level = 2,
                categoryName = "Kasjerzy",
                parentId = 5022
            ),
            CategoryVacanciesEntity(
                id = 5022012,
                level = 2,
                categoryName = "Pracownicy produkcji",
                parentId = 5022
            ),
            CategoryVacanciesEntity(
                id = 5022013,
                level = 2,
                categoryName = "Fryzjer / Kosmetyczka",
                parentId = 5022
            ),
            CategoryVacanciesEntity(
                id = 5022014,
                level = 2,
                categoryName = "Obsługa hotelowa",
                parentId = 5022
            ),
            CategoryVacanciesEntity(
                id = 5022015,
                level = 2,
                categoryName = "Utrzymanie czystości",
                parentId = 5022
            ),
            CategoryVacanciesEntity(
                id = 5031004,
                level = 2,
                categoryName = "Kierowcy",
                parentId = 5031
            ),
            CategoryVacanciesEntity(
                id = 5002005,
                level = 2,
                categoryName = "Inne",
                parentId = 5002
            ),
            CategoryVacanciesEntity(
                id = 5008003,
                level = 2,
                categoryName = "Inne",
                parentId = 5008
            ),
            CategoryVacanciesEntity(id = 5012, level = 1, categoryName = "Inne", parentId = null),
            CategoryVacanciesEntity(
                id = 5014004,
                level = 2,
                categoryName = "Inne",
                parentId = 5014
            ),
            CategoryVacanciesEntity(
                id = 5022001,
                level = 2,
                categoryName = "Inne",
                parentId = 5022
            ),
            CategoryVacanciesEntity(
                id = 5035002,
                level = 2,
                categoryName = "Inne",
                parentId = 5035
            ),
        )
    }
}