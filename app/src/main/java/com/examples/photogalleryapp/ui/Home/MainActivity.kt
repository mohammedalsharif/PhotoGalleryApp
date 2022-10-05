package com.examples.photogalleryapp.ui.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.examples.photogalleryapp.R
import com.examples.photogalleryapp.Util.Common
import com.examples.photogalleryapp.databinding.ActivityMainBinding
import com.examples.photogalleryapp.databinding.FragmentHomeBinding

class MainActivity : AppCompatActivity() {
    private  val TAG = "MainActivity"
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController=Navigation.findNavController(findViewById(R.id.fragment))
        navController.addOnDestinationChangedListener(listener = NavController.OnDestinationChangedListener(
            function= { controller: NavController, destination: NavDestination, arguments: Bundle? ->
                    when(destination.id){
                        R.id.homeFragment->{
                            binding.backBtn.visibility=View.GONE
                            binding.searchBar.visibility=View.VISIBLE
                        }
                    }
                }
            ))


    }


}