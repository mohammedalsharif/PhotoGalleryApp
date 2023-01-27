package com.examples.photogalleryapp.ui.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import com.examples.photogalleryapp.R
import com.examples.photogalleryapp.data.api.ApiClient
import com.examples.photogalleryapp.databinding.ActivityMainBinding
import com.examples.photogalleryapp.data.model.ImageResponse
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        navController = Navigation.findNavController(findViewById(R.id.fragment))
        navController.addOnDestinationChangedListener(listener = NavController.OnDestinationChangedListener(
            function = { controller: NavController, destination: NavDestination, arguments: Bundle? ->
                when (destination.id) {
                    R.id.homeFragment -> {
                        binding.backBtn.visibility = View.GONE
                        binding.searchBar.visibility = View.VISIBLE
                    }
                }
            }
        ))


    }


}