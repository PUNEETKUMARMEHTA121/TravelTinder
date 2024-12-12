package com.example.traveldiary.android.firebase_config

//class FirebaseConfigViewModel constructor(
//    private val firebaseConfigUseCase: FirebaseConfigUseCase,
//    private val businessTypesUseCase: BusinessTypesUseCase,
//    private val currencyUseCase: CurrencyUseCase,
//    private val landingConfigUseCase: LandingConfigUseCase
//) : ViewModel()
//{
//
//
//    private val _countryList = MutableStateFlow(CountryDetailsState())
//    val countryList: StateFlow<CountryDetailsState> = _countryList
//
//    private val _businessTypesList = MutableStateFlow(BusinessTypesDetailsState())
//    val businessTypesList: StateFlow<BusinessTypesDetailsState> = _businessTypesList
//
//    private val _currencyList = MutableStateFlow(CurrencyDetailsState())
//    val currencyList: StateFlow<CurrencyDetailsState> = _currencyList
//
//    private val _landingConfig = MutableStateFlow(LandingConfigDetailsState())
//    val landingConfig: StateFlow<LandingConfigDetailsState> = _landingConfig
//
//    init {
//        viewModelScope.launch {
//            getCountryConfigList()
//            getBusinessTypes()
//            getCurrencyListInfo()
//            getConfigLandingInfo()
//        }
//    }
//
//    private fun getCountryConfigList() {
//        viewModelScope.launch {
//            try {
//                _countryList.value = CountryDetailsState(isLoading = true)
//                when (val resource = firebaseConfigUseCase.invoke()) {
//                    is Resource.Success -> {
//                        _countryList.value = CountryDetailsState(data = resource.data)
//                    }
//                    is Resource.Error -> {
//                        _countryList.value = CountryDetailsState(error = resource.message ?: "")
//                    }
//                    else -> {}
//                }
//            } catch (e: IOException) {
//                _countryList.value = CountryDetailsState(error = e.localizedMessage ?: "Check Connectivity")
//            } catch (e: Exception) {
//                _countryList.value = CountryDetailsState(error = "Unknown error occurred")
//            }
//        }
//    }
//
//    private fun getCurrencyListInfo() {
//        viewModelScope.launch {
//            try {
//                _currencyList.value = CurrencyDetailsState(isLoading = true)
//                when (val resource = currencyUseCase.invoke()) {
//                    is Resource.Success -> {
//                        _currencyList.value = CurrencyDetailsState(data = resource.data)
//                    }
//                    is Resource.Error -> {
//                        _currencyList.value = CurrencyDetailsState(error = resource.message ?: "")
//                    }
//                    else -> {}
//                }
//            } catch (e: IOException) {
//                _currencyList.value = CurrencyDetailsState(error = e.localizedMessage ?: "Check Connectivity")
//            } catch (e: Exception) {
//                _currencyList.value = CurrencyDetailsState(error = "Unknown error occurred")
//            }
//        }
//    }
//
//    private fun getBusinessTypes() {
//        viewModelScope.launch {
//            try {
//                _businessTypesList.value = BusinessTypesDetailsState(isLoading = true)
//                when (val resource = businessTypesUseCase.invoke()) {
//                    is Resource.Success -> {
//                        _businessTypesList.value = BusinessTypesDetailsState(data = resource.data)
//                    }
//                    is Resource.Error -> {
//                        _businessTypesList.value = BusinessTypesDetailsState(error = resource.message ?: "")
//                    }
//                    else -> {}
//                }
//            } catch (e: IOException) {
//                _businessTypesList.value = BusinessTypesDetailsState(error = e.localizedMessage ?: "Check Connectivity")
//            } catch (e: Exception) {
//                _businessTypesList.value = BusinessTypesDetailsState(error = "Unknown error occurred")
//            }
//        }
//    }
//
//    private fun getConfigLandingInfo() {
//        viewModelScope.launch {
//            try {
//                _landingConfig.value = LandingConfigDetailsState(isLoading = true)
//                when (val resource = landingConfigUseCase.invoke()) {
//                    is Resource.Success -> {
//                        _landingConfig.value = LandingConfigDetailsState(data = resource.data)
//                    }
//                    is Resource.Error -> {
//                        _landingConfig.value = LandingConfigDetailsState(error = resource.message ?: "")
//                    }
//                    else -> {}
//                }
//            } catch (e: IOException) {
//                _landingConfig.value = LandingConfigDetailsState(error = e.localizedMessage ?: "Check Connectivity")
//            } catch (e: Exception) {
//                _landingConfig.value = LandingConfigDetailsState(error = "Unknown error occurred")
//            }
//        }
//    }
//
//
//
//}