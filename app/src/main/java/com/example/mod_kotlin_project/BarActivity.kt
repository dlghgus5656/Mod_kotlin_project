package com.example.mod_kotlin_project


import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView

import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.kakao.sdk.user.UserApiClient


class BarActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var navigationView: NavigationView
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        val go_search = findViewById<ImageView>(R.id.main_search_button)
        go_search.setOnClickListener {
            val intent = Intent(this, SearchView::class.java)
            startActivity(intent)
        }

        val main_layout_toolbar: Toolbar = findViewById(R.id.main_layout_toolbar)
        setSupportActionBar(main_layout_toolbar) // 툴바를 액티비티의 앱바로 지정

        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 드로어를 꺼낼 홈 버튼 활성화
        supportActionBar?.setHomeAsUpIndicator(R.drawable.menu_icon) // 홈버튼 이미지 변경
        supportActionBar?.setDisplayShowTitleEnabled(false) // 툴바에 타이틀 안보이게

        // 네비게이션 드로어 생성
        drawerLayout = findViewById(R.id.main_drawer_layout)

        // 네비게이션 드로어 내에있는 화면의 이벤트를 처리하기 위해 생성
        navigationView = findViewById(R.id.main_navigationView)
        navigationView.setNavigationItemSelectedListener(this) //navigation 리스너
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> { // 메뉴 버튼
                drawerLayout.openDrawer(GravityCompat.START)    // 네비게이션 드로어 열기
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val godid = Intent(this, DIDpage::class.java)
        when (item.itemId) {
            R.id.account -> Toast.makeText(this, "account clicked", Toast.LENGTH_SHORT).show()
            R.id.reservation -> Toast.makeText(this, "예약확인 clicked", Toast.LENGTH_SHORT).show()
            R.id.did -> {
                startActivity(godid)
                Toast.makeText(this, "DID 인증을 해주세요", Toast.LENGTH_SHORT).show()
            }
            R.id.logout -> {
                UserApiClient.instance.logout { error ->
                    if (error != null) {
                        Toast.makeText(this, "로그아웃 실패 $error", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "로그아웃 성공", Toast.LENGTH_SHORT).show()
                    }
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                    finish()
                }
            }
            R.id.withdrawal -> {
                UserApiClient.instance.unlink { error ->
                    if (error != null) {
                        Toast.makeText(this, "회원탈퇴 실패 $error", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "회원탈퇴 성공", Toast.LENGTH_SHORT).show()
                    }
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                    finish()
                }
            }
            //R.id.logout-> Toast.makeText(this, "로그아웃", Toast.LENGTH_SHORT).show()
        }
        return false
    }

}