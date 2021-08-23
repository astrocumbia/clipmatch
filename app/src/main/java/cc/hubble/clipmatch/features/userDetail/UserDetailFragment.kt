package cc.hubble.clipmatch.features.userDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import cc.hubble.clipmatch.data.model.Song
import cc.hubble.clipmatch.data.model.User
import cc.hubble.clipmatch.databinding.FragmentUserDetailBinding
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment : Fragment() {

    private val viewModel: UserDetailViewModel by viewModels()
    private val args: UserDetailFragmentArgs by navArgs()

    private lateinit var _binding: FragmentUserDetailBinding
    private val binding: FragmentUserDetailBinding
        get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureViewModel()

    }

    private fun configureViewModel() {
        viewModel.getUserDetail(args.index)
        viewModel.state.observe(viewLifecycleOwner, ::handleStates)
    }

    private fun handleStates(viewState: UserDetailViewModel.ViewState) {
        when (viewState) {
            is UserDetailViewModel.ViewState.UserDetail -> handleUserDetail(viewState)
        }
    }

    private fun handleUserDetail(viewState: UserDetailViewModel.ViewState.UserDetail) {
        val song = viewState.favoriteSong
        val user = viewState.user

        setupToolbar(user)
        setupImages(song, user)
        setupTextViews(song, user)
        setupChipGroup(viewState.hobbies)
    }

    private fun setupToolbar(user: User) {
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = user.fullName
        }
    }

    private fun setupImages(song: Song, user: User) {
        Glide.with(requireActivity())
            .load(user.thumbnail)
            .circleCrop()
            .into(binding.userImage)

        Glide.with(requireActivity())
            .load(song.coverUrl)
            .circleCrop()
            .into(binding.songImage)
    }

    private fun setupTextViews(song: Song, user: User) {
        binding.textSong.text = song.name
        binding.textArtist.text = song.artist

        binding.textName.text = user.firstName
        binding.textAge.text = user.age.toString()
        binding.textEmail.text = user.email
        binding.textPhone.text = user.phone
        binding.textGender.text = user.gender
        binding.textBirthday.text = user.birthday
        binding.textCity.text = user.city
    }

    private fun setupChipGroup(hobbies: List<String>) {
        hobbies.forEach { h ->
            val chip = Chip(requireActivity()).apply {
                text = h
            }
            binding.chipGroupHobbies.addView(chip)
        }
    }
}