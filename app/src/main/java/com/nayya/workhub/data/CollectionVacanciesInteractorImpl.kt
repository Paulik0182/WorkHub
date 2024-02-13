package com.nayya.workhub.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Handler
import android.os.Looper
import com.nayya.workhub.domain.entity.VacancyEntity
import com.nayya.workhub.domain.entity.filter_category.CategoryVacanciesEntity
import com.nayya.workhub.domain.entity.vacancy.CityEntity
import com.nayya.workhub.domain.entity.vacancy.ContractOptionEntity
import com.nayya.workhub.domain.entity.vacancy.CountryEntity
import com.nayya.workhub.domain.entity.vacancy.FinancialProposalEntity
import com.nayya.workhub.domain.entity.vacancy.VacancyJobEntity
import com.nayya.workhub.domain.interactor.CollectionVacanciesInteractor
import com.nayya.workhub.domain.repo.VacanciesRepo
import com.nayya.workhub.domain.repo.VacanciesTypeRepo
import java.util.Calendar

class CollectionVacanciesInteractorImpl(
    private val vacanciesTypeRepo: VacanciesTypeRepo,
    private val vacanciesRepo: VacanciesRepo,
    private val context: Context
) : CollectionVacanciesInteractor {

    override fun getCollectionVacancies(callback: (List<VacancyJobEntity>) -> Unit) {

        if (isConnected()) {
            val works = vacanciesTypeRepo.getVacanciesType()

            Handler(Looper.getMainLooper()).postDelayed({
                vacanciesRepo.getVacancies(works) { vacanciesTypes ->
                    val vacancies = mutableListOf<VacancyJobEntity>()

                    vacanciesTypes.forEach { vacancyType ->
                        vacancies.addAll(vacancyType.vacanciesList)
                    }
                    callback(vacancies)
                }
            }, 1_000)
        } else {
            val fakeData = getFakeData()
            callback(fakeData)
        }
    }

    override fun getVacancyJob(id: String, callback: (VacancyJobEntity?) -> Unit) {
        if (isConnected()) {

            // todo

        } else {
            val fakeData = getFakeData().first {
                it.key == id
            }
            callback(fakeData)
        }
    }

    private fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager
            .getNetworkCapabilities(network)
        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            ?: false
    }

    private fun getFakeData2(): List<VacancyEntity> {
        return listOf(
            VacancyEntity("1", "IT"),
            VacancyEntity("2", "Android"),
            VacancyEntity("3", "Java"),
            VacancyEntity("4", "IT"),
            VacancyEntity("5", "IT"),
            VacancyEntity("6", "Android"),
            VacancyEntity("7", "Java")
        )
    }

    private fun getFakeData(): List<VacancyJobEntity> {
        return listOf(
            VacancyJobEntity(
                key = "1",
                nameCompany = "LTE Mobail",
                labelCompany = null,
                titleVacancies = "Android Developer",
                categoryVacanciesList = listOf(CategoryVacanciesEntity("1", "IT")),
                financialProposalList = listOf(
                    FinancialProposalEntity(
                        "1",
                        listOf("B2B - 6 000 zl", "Contract - 8000zl")
                    )
                ),
                countryList = listOf(CountryEntity("1", listOf("Poland"))),
                cityList = listOf(CityEntity("1", listOf("Warsaw"))),
                offerValid = 30,
                deadlineEndOffer = Calendar.getInstance().timeInMillis,
                contractOptionList = listOf(ContractOptionEntity("1", listOf("B2B", "Contract"))),
                employmentRate = "full",
                specialistLevel = "Junior",
                typeWork = "Remote work",
                gettingStarted = "Now",
                interviewMethod = "Remote recruitment"
            ),

            VacancyJobEntity(
                key = "2",
                nameCompany = "Super star",
                labelCompany = null,
                titleVacancies = "Android",
                categoryVacanciesList = listOf(CategoryVacanciesEntity("2", "Android")),
                financialProposalList = listOf(
                    FinancialProposalEntity(
                        "2",
                        listOf("Contract - 6 800 zl", "B2B - 9 000 zl")
                    )
                ),
                countryList = listOf(CountryEntity("2", listOf("Poland"))),
                cityList = listOf(CityEntity("2", listOf("Warsaw"))),
                offerValid = 20,
                deadlineEndOffer = Calendar.getInstance().timeInMillis,
                contractOptionList = listOf(ContractOptionEntity("2", listOf("B2B", "Contract"))),
                employmentRate = "full",
                specialistLevel = "Middle",
                typeWork = "Remote work",
                gettingStarted = "Now",
                interviewMethod = "Remote recruitment"
            ),
            VacancyJobEntity(
                key = "3",
                nameCompany = "Secret Code",
                labelCompany = null,
                titleVacancies = "Programmer",
                categoryVacanciesList = listOf(CategoryVacanciesEntity("3", "Java")),
                financialProposalList = listOf(
                    FinancialProposalEntity(
                        "3",
                        listOf("B2B - 11 000 zl")
                    )
                ),
                countryList = listOf(CountryEntity("3", listOf("Poland"))),
                cityList = listOf(CityEntity("3", listOf("Gdansk"))),
                offerValid = 15,
                deadlineEndOffer = Calendar.getInstance().timeInMillis,
                contractOptionList = listOf(ContractOptionEntity("3", listOf("B2B"))),
                employmentRate = "full",
                specialistLevel = "Middle",
                typeWork = "Remote work",
                gettingStarted = "Now",
                interviewMethod = "Remote recruitment"
            ),

            VacancyJobEntity(
                key = "4",
                nameCompany = "Service services",
                labelCompany = null,
                titleVacancies = "Programmer - designer",
                categoryVacanciesList = listOf(CategoryVacanciesEntity("4", "UX/UI")),
                financialProposalList = listOf(
                    FinancialProposalEntity(
                        "4",
                        listOf("Contract - 7 500 zl")
                    )
                ),
                countryList = listOf(CountryEntity("4", listOf("Poland"))),
                cityList = listOf(CityEntity("4", listOf("Green Mountain"))),
                offerValid = 22,
                deadlineEndOffer = Calendar.getInstance().timeInMillis,
                contractOptionList = listOf(ContractOptionEntity("4", listOf("Contract"))),
                employmentRate = "full",
                specialistLevel = "Middle",
                typeWork = "Remote work",
                gettingStarted = "Now",
                interviewMethod = "Remote recruitment"
            ),
            VacancyJobEntity(
                key = "5",
                nameCompany = "Humpty Dumpty",
                labelCompany = null,
                titleVacancies = "Administrator DataBase",
                categoryVacanciesList = listOf(
                    CategoryVacanciesEntity(
                        "5",
                        "Administrator DataBase"
                    )
                ),
                financialProposalList = listOf(
                    FinancialProposalEntity(
                        "5",
                        listOf("B2B - 8 500 zl")
                    )
                ),
                countryList = listOf(CountryEntity("5", listOf("Poland"))),
                cityList = listOf(CityEntity("5", listOf("Gdansk"))),
                offerValid = 22,
                deadlineEndOffer = Calendar.getInstance().timeInMillis,
                contractOptionList = listOf(ContractOptionEntity("5", listOf("B2B"))),
                employmentRate = "full",
                specialistLevel = "Middle",
                typeWork = "Remote work",
                gettingStarted = "Now",
                interviewMethod = "Remote recruitment"
            ),
            VacancyJobEntity(
                key = "6",
                nameCompany = "Great guys",
                labelCompany = null,
                titleVacancies = "Security Administrator",
                categoryVacanciesList = listOf(CategoryVacanciesEntity("6", "Administrator")),
                financialProposalList = listOf(
                    FinancialProposalEntity(
                        "6",
                        listOf("B2B - 6 700 zl")
                    )
                ),
                countryList = listOf(CountryEntity("6", listOf("Poland"))),
                cityList = listOf(CityEntity("6", listOf("Gdansk"))),
                offerValid = 22,
                deadlineEndOffer = Calendar.getInstance().timeInMillis,
                contractOptionList = listOf(ContractOptionEntity("6", listOf("B2B"))),
                employmentRate = "full",
                specialistLevel = "Middle",
                typeWork = "Remote work",
                gettingStarted = "Now",
                interviewMethod = "Remote recruitment"
            ),
        )
    }
}