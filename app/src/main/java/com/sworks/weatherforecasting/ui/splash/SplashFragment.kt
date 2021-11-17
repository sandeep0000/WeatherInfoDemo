package com.sworks.weatherforecasting.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sworks.weatherforecasting.MainActivity
import com.sworks.weatherforecasting.R
import com.sworks.weatherforecasting.core.base.view.BaseFragment
import com.sworks.weatherforecasting.core.utils.AppConstants.SPLASH_TIME_OUT
import com.sworks.weatherforecasting.core.utils.snackBarError
import com.sworks.weatherforecasting.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
@AndroidEntryPoint
class SplashFragment (override val layoutResourceLayout: Int = R.layout.fragment_splash)
    : BaseFragment<FragmentSplashBinding>(){

    private var param1: String? = null
    private var param2: String? = null
//    private val mViewModel: HomeViewModel by viewModels()

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SplashFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        return rootView
    }

    override fun onFragmentCreated(dataBinder: FragmentSplashBinding) {
        dataBinder.apply {
            fragment = this@SplashFragment
            lifecycleOwner = this@SplashFragment
        }
    }

    override fun setUpViewModelStateObservers() {
        print("")
    }

    override fun onResume() {
        super.onResume()
        Handler(Looper.getMainLooper()).postDelayed({
            (requireActivity() as MainActivity).mViewModel.readyToFetch.observe(requireActivity(),
                { requirements ->
                    requirements?.let {
                        if ( it["permission"] == true) {
                            if ( it["gps"] == true) {
                                navController.navigate(R.id.homeFragment)
                            } else if (it["gps"] == false) {
                                showNoGps()
//                                (requireActivity() as MainActivity).turnOnGPS()
                            }
                        } else if (it["permission"] == false) {
//                            (requireActivity() as MainActivity).requestLocationPermission()
                            showTakePermissions()
                        }
                    }
                }
            )
        }, SPLASH_TIME_OUT)

    }

    /**
     * layout of No Internet
     */
    private fun showNoInternet(){

    }

    /**
     * layout of No Gps
     */
    private fun showNoGps(){
        dataBinder.apply {
            this.root.snackBarError("Gps Turned off.", "Turn On", View.OnClickListener {
                (requireActivity() as MainActivity).turnOnGPS()
            })
        }
    }

    /**
     * layout of Take Permissions
     */
    private fun showTakePermissions(){
        dataBinder.apply {
            this.root.snackBarError("Permissions needed please allow.", "Allow", View.OnClickListener {
                (requireActivity() as MainActivity).requestLocationPermission()
            })
        }
    }
}