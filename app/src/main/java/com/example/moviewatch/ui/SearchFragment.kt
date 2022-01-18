package com.example.moviewatch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moviewatch.HomeViewModel
import com.example.moviewatch.adapters.SearchAdapter
import com.example.moviewatch.data.InnerResults
import com.example.moviewatch.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var searchMoviesList: ArrayList<InnerResults> = ArrayList()
    private val adapter: SearchAdapter = SearchAdapter(searchMoviesList)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.searchList.adapter = adapter


        val searchView: SearchView = binding.searchView


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                viewModel.onQueryTextSubmit(p0!!)
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }
        })

        subscribe()

        return root
    }

    fun subscribe() {
        viewModel.searchResults.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}