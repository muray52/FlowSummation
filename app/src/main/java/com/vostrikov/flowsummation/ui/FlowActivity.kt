package com.vostrikov.flowsummation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.vostrikov.flowsummation.databinding.ActivityFlowBinding
import kotlinx.coroutines.launch

class FlowActivity : AppCompatActivity() {

    private var _binding: ActivityFlowBinding? = null
    private val binding: ActivityFlowBinding
        get() = _binding ?: throw RuntimeException("ActivityFlowBinding doesn't bind")

    private val viewModel: FlowViewModel by viewModels<FlowViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFlowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setOnClickListeners()
        setObservers()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun setOnClickListeners() {
        binding.btnLaunch.setOnClickListener {
            viewModel.launchSummation(binding.etInputNumber.text.toString())
        }
    }

    private fun setObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.summationResult.collect {
                    binding.tvSummationResult.text = it
                }
            }
        }

    }
}