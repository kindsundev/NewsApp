package com.example.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityNewsBinding
import com.example.newsapp.db.ArticleDatabase
import com.example.newsapp.repository.NewsRepository

class NewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsBinding
    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        setupBottomNav()
    }

    private fun initViewModel() {
        val newsRepository = NewsRepository(ArticleDatabase(this))
        val newsModelProviderFactory = NewsViewModelProviderFactory(application ,newsRepository)
        viewModel = ViewModelProvider(this, newsModelProviderFactory)[NewsViewModel::class.java]
    }

    private fun setupBottomNav() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.newsNavHostFragment)
        navHostFragment?.let {
            val navController = navHostFragment.findNavController()
            binding.bottomNavigationView.setupWithNavController(navController)
        }
    }
}