package com.muflihunnisa.githubfirstsubmission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.muflihunnisa.githubfirstsubmission.data.model.Users
import com.muflihunnisa.githubfirstsubmission.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var detailBinding: ActivityDetailBinding
    private lateinit var users: Users
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)
        users = intent.getParcelableExtra<Users>(EXTRA_USER) as Users

        showDetail()
    }

    private fun showDetail() {
        Glide.with(this).load(users.avatar).into(detailBinding.ivDetailUser)
        detailBinding.tvFollowerDetail.text = users.follower.toString()
        detailBinding.tvFollowingDetail.text = users.following.toString()
        detailBinding.tvUserNameDetail.text = users.name
        detailBinding.tvUsernameDetail.text = users.username
        detailBinding.tvCompanyDetail.text = users.company
        detailBinding.tvLocationDetail.text = users.location
    }

    companion object {
        const val EXTRA_USER = "extra_user"
    }
}