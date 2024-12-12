package com.example.traveldiary.android.onboarding

//class PlaceHolderViewModel constructor(
//    private val getIntroImageUseCase: GetIntroImageUseCase
//) : ViewModel()
//{
//
//    fun getImageUrl(index: Int): LiveData<String> {
//        val imageUrlLiveData = MutableLiveData<String>()
//
//        viewModelScope.launch(Dispatchers.Main) {
//            try {
//                val imageUrl = getIntroImageUseCase.getImageUrl(index)
//                imageUrlLiveData.value = imageUrl
//            } catch (e: Exception) {
//                // Handle the exception
//            }
//        }
//
//        return imageUrlLiveData
//    }
//}