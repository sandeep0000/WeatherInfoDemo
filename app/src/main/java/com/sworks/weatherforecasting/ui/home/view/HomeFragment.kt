package com.sworks.weatherforecasting.ui.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sworks.weatherforecasting.MainActivity
import com.sworks.weatherforecasting.R
import com.sworks.weatherforecasting.core.base.view.BaseFragment
import com.sworks.weatherforecasting.core.utils.setSnackBar
import com.sworks.weatherforecasting.core.utils.snackBarError
import com.sworks.weatherforecasting.core.utils.toast
import com.sworks.weatherforecasting.data.ResultData
import com.sworks.weatherforecasting.databinding.FragmentHomeBinding
import com.sworks.weatherforecasting.domain.model.WeatherCityResponse
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeFragment(override val layoutResourceLayout: Int = R.layout.fragment_home) :
    BaseFragment<FragmentHomeBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return rootView
    }

    override fun onFragmentCreated(dataBinder: FragmentHomeBinding) {
        dataBinder.apply {
            fragment = this@HomeFragment
            lifecycleOwner = this@HomeFragment
        }
    }

    override fun setUpViewModelStateObservers() {
        (requireActivity() as MainActivity).mViewModel.apply {
            readyToFetch.observe(requireActivity(), { requirements ->
                requirements?.let {
                    if (it["gps"] == true) {
                        if (this.currentCity!=null){

                            fetchWeatherByCity(this.currentCity!!).observe(requireActivity(), { result -> fetchResult(result) })

//                            fetchWeatherLatLong().observe(requireActivity(), {
//                                    result -> fetchResult(result)
//                            })
                        }
                    }
                }
            })
        }
    }

    private fun fetchResult(result: ResultData<WeatherCityResponse>) {
        when (result) {
            is ResultData.Success -> { result.data?.let { data -> showMainView(data)  } }
            is ResultData.Failure -> result.msg?.let { msg -> setSnackBar(msg).show() }
            is ResultData.Loading -> { showLoadingView()}
            is ResultData.Internet -> { showNoInternetView()}
        }
    }

    private fun showNoInternetView(){
        dataBinder.apply {
            this.root.snackBarError("No internet available.")
        }
    }

    private fun showLoadingView(){
        dataBinder.apply {
            viewFlipper.displayedChild = viewFlipper.indexOfChild(viewLoading.root)
        }
    }

    private fun showMainView(models: WeatherCityResponse) {
        dataBinder.apply {
            viewFlipper.displayedChild = viewFlipper.indexOfChild(viewInfo.root)
            viewInfo.txtCity.text = models.name
            viewInfo.txtTemp.text = models.main?.temp.toString()
            viewInfo.txtDetail.text = models.weather?.get(0)?.description
            viewInfo.txtDate.text = "Today"
        }
    }
}