package com.example.mod_kotlin_project


import android.content.Intent
import android.graphics.PointF
import android.os.Bundle
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.ImageView

import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.kakao.sdk.user.UserApiClient
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.NaverMap.OnMapClickListener
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons


class BarActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    OnMapReadyCallback {
    private val LOCATION_PERMISSION_REQUEST_CODE = 1000
    private var locationSource: FusedLocationSource? = null
    private val naverMap: NaverMap? = null

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

        //네이버 지도
        val mapView = findViewById<MapView>(R.id.map_view)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)


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
        val goreservation = Intent(this, Reservation::class.java)
        when (item.itemId) {
            R.id.account -> Toast.makeText(this, "account clicked", Toast.LENGTH_SHORT).show()

            R.id.reservation -> {
            startActivity(goreservation)
            Toast.makeText(this, "예약확인 페이지입니다.", Toast.LENGTH_SHORT).show()
        }
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>, grantResults: IntArray
    ) {
        if (locationSource!!.onRequestPermissionsResult(
                requestCode, permissions, grantResults
            )
        ) {
            if (!locationSource!!.isActivated) { // 권한 거부됨
             naverMap!!.setLocationTrackingMode(LocationTrackingMode.None)
            }
            return
        }
        super.onRequestPermissionsResult(
            requestCode, permissions, grantResults
        )
    }

    override fun onMapReady(naverMap: NaverMap) {
        //BarActivity.naverMap = naverMap
        naverMap.uiSettings.isLocationButtonEnabled = true
        val cameraPosition = CameraPosition(
            LatLng(36.839496, 127.184083),  // 위치 지정
            16.0,  // 줌 레벨
            45.0,  //기울임 각도
            45.0 //방향
        )
        naverMap.cameraPosition = cameraPosition
        naverMap.locationSource = locationSource
        val marker1 = Marker()
        marker1.position = LatLng(36.839252, 127.185959)
        marker1.map = naverMap
        marker1.width = 70
        marker1.height = 90
        marker1.isIconPerspectiveEnabled = true //마커 원근효과
        marker1.captionText = "본부동"
        marker1.captionTextSize = 16f
        val marker2 = Marker()
        marker2.position = LatLng(36.839659, 127.184795)
        marker2.map = naverMap
        marker2.width = 70
        marker2.height = 90
        marker2.isIconPerspectiveEnabled = true //마커 원근효과
        marker2.icon = MarkerIcons.BLUE
        marker2.captionText = "교수회관"
        marker2.captionTextSize = 16f
        val marker3 = Marker()
        marker3.position = LatLng(36.839461, 127.182542)
        marker3.map = naverMap
        marker3.width = 70
        marker3.height = 90
        marker3.isIconPerspectiveEnabled = true //마커 원근효과
        marker3.icon = MarkerIcons.YELLOW
        marker3.captionText = "백석홀"
        marker3.captionTextSize = 16f
        val infoWindow = InfoWindow()
        val infoWindow1 = InfoWindow()
        val infoWindow2 = InfoWindow()

        //지도 클릭시 정보창 닫기
        naverMap.onMapClickListener = OnMapClickListener { coord: PointF?, point: LatLng? ->
            infoWindow.close()
            infoWindow1.close()
            infoWindow2.close()
        }

        //마커1 클릭시
        marker1.onClickListener = Overlay.OnClickListener { overlay: Overlay? ->
            val rootView = findViewById<ViewGroup>(R.id.map_view)
            val adapter = PointAdapter(this@BarActivity, rootView)
            infoWindow.adapter = adapter
            if (marker1.infoWindow == null) {
                //현재 마커에 정보창이 없을 경우 열기
                infoWindow.open(marker1)
            } else {
                infoWindow.close()
            }
            true
        }
        infoWindow.onClickListener = Overlay.OnClickListener { overlay: Overlay? ->
            val intent = Intent(applicationContext, ShopInfo::class.java)
            startActivity(intent)
            false
        }

        //마커2 클릭시
        marker2.onClickListener = Overlay.OnClickListener { overlay: Overlay? ->
            val rootView = findViewById<ViewGroup>(R.id.map_view)
            val adapter = PointAdapter1(this@BarActivity, rootView)
            infoWindow1.adapter = adapter
            if (marker2.infoWindow == null) {
                //현재 마커에 정보창이 없을 경우 열기
                infoWindow1.open(marker2)
            } else {
                infoWindow1.close()
            }
            true
        }

        //마커3 클릭시
        marker3.onClickListener = Overlay.OnClickListener { overlay: Overlay? ->
            val rootView = findViewById<ViewGroup>(R.id.map_view)
            val adapter = PointAdapter2(this@BarActivity, rootView)
            infoWindow2.adapter = adapter
            if (marker3.infoWindow == null) {
                //현재 마커에 정보창이 없을 경우 열기
                infoWindow2.open(marker3)
            } else {
                infoWindow2.close()
            }
            true
        }
    } //OnMapReady


}

