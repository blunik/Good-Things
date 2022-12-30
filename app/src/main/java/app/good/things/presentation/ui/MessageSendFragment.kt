package app.good.things.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import app.good.things.R
import app.good.things.data.model.ResultRequest
import app.good.things.databinding.FragmentMessageSendBinding
import app.good.things.presentation.viewmodel.MessageSendViewModel
import kotlinx.android.synthetic.main.fragment_message_send.*
import kotlinx.coroutines.launch

/**
 * Message sending screen
 */
internal class MessageSendFragment : BaseFragment() {

    private lateinit var viewModel: MessageSendViewModel

    private lateinit var binding: FragmentMessageSendBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_message_send, container, false)
        binding.send.setOnClickListener {
            if (binding.editTextCode.text.isNullOrEmpty() || binding.messageEditText.text.isNullOrEmpty()) {
                showEmptyString()
            } else {
                viewModel.sendMessage(
                    binding.editTextCode.text.toString(),
                    binding.messageEditText.text.toString()
                )
            }
        }
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[MessageSendViewModel::class.java]
        lifecycleScope.launch {
            viewModel.messageStatus.collect {
                when (it) {
                    ResultRequest.NOT_FOUND_USER -> showInvalidCode()
                    ResultRequest.SUCCESS -> {
                        showSuccessSendMessage()
                        binding.editTextCode.setText("")
                        binding.messageEditText.setText("")
                    }
                    ResultRequest.ERROR -> showError()
                }
            }
        }
    }

    private fun showSuccessSendMessage() =
        Toast.makeText(context, R.string.send_success_message, Toast.LENGTH_SHORT).show()

    private fun showEmptyString() =
        Toast.makeText(context, R.string.rule_edit, Toast.LENGTH_SHORT).show()
}