package com.example.emoease.screens.accountScreen.util

import androidx.lifecycle.ViewModel
import com.example.emoease.screens.accountScreen.data.AccountItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(private val repository: AccountRepository):ViewModel(){

//    private val _accoutDetails = MutableLiveData<ApiResult<List<ProfileDtoItem>>>()
//    val accountDetails : MutableLiveData<ApiResult<List<ProfileDtoItem>>>
//    get() = _accoutDetails
//
//
//        fun getAccountDetails(){
//            _accoutDetails.value = ApiResult.Loading
//            viewModelScope.launch {
//                _accoutDetails.value= repository.getProfileDetails()
//            }
//        }





//    fun getAccountDetails(){
//        _accoutDetails.value = ApiResult.Loading
//        viewModelScope.launch {
//            repository.getProfileDetails()
//            _accoutDetails.value = repository.getProfileDetails()
//        }
//    }
    fun getListOfGeneralItems():List<AccountItem>{
        return genralList
    }
    fun getListOfApplicationItems():List<AccountItem>{
        return applicationList
    }
    fun getListOfOtherItems():List<AccountItem>{
        return othersList
    }



}

//class HistoryViewModal @Inject constructor(private val repository: HistroyRepository) :
//    ViewModel() {
//
//
//
//
//    private val _bookingDetails = MutableLiveData<ApiResult<BookingHistory>>()
//    val bookingDetails : MutableLiveData<ApiResult<BookingHistory>>
//    get() = _bookingDetails
//
////    fun getBookingDetails(){
////        _bookingDetails.value = ApiResult.Loading
////      viewModelScope.launch {
//////          repository.getBookingDetails()
////          _bookingDetails.value = repository.getBookingDetails()
////      }
////
////    }