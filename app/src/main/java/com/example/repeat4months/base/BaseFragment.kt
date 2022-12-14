package com.example.repeat4months.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import com.example.repeat4months.R
import com.example.repeat4months.R.*

typealias Inflate<T> = (LayoutInflater,ViewGroup?, Boolean) -> T
abstract class BaseFragment<VB : ViewBinding>(private  val inflate: Inflate<VB>) : Fragment() {


    private var _binding: VB? = null
    protected val binding get() = _binding

    private var _controller: NavController? = null
    protected val controller get() = _controller!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        val navHostFragment = requireActivity().supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        _controller = navHostFragment.navController
        setupUI()
        setupObserver()
        return binding?.root
    }

    abstract fun setupUI()
    open fun setupObserver(){ }

}