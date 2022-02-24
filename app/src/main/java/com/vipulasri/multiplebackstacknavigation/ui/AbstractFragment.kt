package com.vipulasri.multiplebackstacknavigation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vipulasri.multiplebackstacknavigation.databinding.FragmentAbstractModelBinding

private const val TEXT = "TEXT"
private const val COUNT = "COUNT"

abstract class AbstractFragment : Fragment() {

    private var _binding: FragmentAbstractModelBinding? = null
    private val binding get() = _binding!!

    private var name: String = ""
    private var num: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            name = arguments?.getString(TEXT) ?: "0"
            num = arguments?.getInt(COUNT) ?: 1
        } else {
            savedInstanceState.run {
                name = getString(TEXT)!!
                num = getInt(COUNT)
            }
        }
    }

    protected fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        navId: Int
    ): View {

        _binding = FragmentAbstractModelBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.textHome.text = name
        binding.button.setOnClickListener {
            val bundle = Bundle()
            bundle.apply {
                putString(TEXT, "${binding.textHome.text} ->  $num")
                putInt(COUNT, num + 1)
            }
            findNavController().navigate(navId, bundle)
        }
        return root
    }


    override fun onSaveInstanceState(outState: Bundle) {
        outState.apply {
            putString(TEXT, name)
            outState.putInt(COUNT, num)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}