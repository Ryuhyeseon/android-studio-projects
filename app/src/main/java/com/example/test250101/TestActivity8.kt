// 패키지 선언: 이 파일이 속한 패키지를 정의합니다.
package com.example.test250101

// 필요한 안드로이드 클래스 임포트
import android.content.BroadcastReceiver // 브로드캐스트 이벤트를 수신하는 기본 클래스
import android.content.Context           // 앱 환경 정보 및 시스템 서비스 접근에 사용되는 컨텍스트
import android.content.Intent             // 컴포넌트 간 통신 및 작업 요청에 사용되는 메시징 객체
import android.content.IntentFilter      // 수신할 인텐트 유형(액션 등)을 지정하는 필터
import android.os.Bundle                // 액티비티 상태 저장 및 복원에 사용되는 데이터 컨테이너
import android.util.Log                 // 로그 메시지 출력을 위한 유틸리티
import androidx.appcompat.app.AppCompatActivity // 하위 호환성을 제공하는 액티비티 기본 클래스

/**
 * 화면 켜짐(ACTION_SCREEN_ON)과 꺼짐(ACTION_SCREEN_OFF) 시스템 이벤트를 감지하는
 * 브로드캐스트 리시버를 동적으로 등록하고 해제하는 예제 액티비티입니다.
 * 리시버는 onCreate에서 등록되고 onPause에서 해제됩니다.
 */
class TestActivity8 : AppCompatActivity() {

    // 동적으로 등록하고 나중에 해제할 BroadcastReceiver 객체를 저장할 변수 선언.
    // lateinit 키워드는 이 변수가 나중에 (onCreate에서) 초기화될 것임을 나타냅니다.
    lateinit var receiver: BroadcastReceiver

    /**
     * 액티비티가 처음 생성될 때 호출되는 메소드입니다.
     * 레이아웃 설정, 리시버 인스턴스 생성 및 시스템 등록을 수행합니다.
     * @param savedInstanceState 액티비티가 비정상 종료 후 재생성될 때 이전 상태 정보를 담고 있는 Bundle 객체. 처음 생성 시에는 null입니다.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        // 반드시 부모 클래스(AppCompatActivity)의 onCreate를 먼저 호출해야 합니다.
        super.onCreate(savedInstanceState)
        // activity_test8.xml 레이아웃 파일을 이 액티비티의 사용자 인터페이스로 설정합니다.
        setContentView(R.layout.activity_test8)

        // BroadcastReceiver의 익명 object(객체)를 생성하여 receiver 변수에 할당합니다.
        receiver = object : BroadcastReceiver(){
            /**
             * 등록된 IntentFilter와 일치하는 브로드캐스트 Intent가 수신될 때 시스템에 의해 호출됩니다.
             * @param context 브로드캐스트를 수신한 컨텍스트. 일반적으로 애플리케이션 컨텍스트입니다.
             * @param intent 수신된 브로드캐스트 인텐트. 액션 및 추가 데이터를 포함할 수 있습니다.
             */
            override fun onReceive(context: Context?, intent: Intent?) {
                // 수신된 인텐트의 액션(action) 문자열을 기준으로 분기합니다. (null 안전 호출 ?. 사용)
                when(intent?.action){
                    // 인텐트 액션이 시스템 정의 상수인 화면 켜짐(ACTION_SCREEN_ON)과 일치하는 경우
                    Intent.ACTION_SCREEN_ON -> Log.d("omrs", "screen on") // "omrs" 태그로 로그캣에 메시지 출력
                    // 인텐트 액션이 시스템 정의 상수인 화면 꺼짐(ACTION_SCREEN_OFF)과 일치하는 경우
                    Intent.ACTION_SCREEN_OFF -> Log.d("omrs", "screen off") // "omrs" 태그로 로그캣에 메시지 출력
                }
            }
        }

        // 이 리시버가 어떤 종류의 브로드캐스트를 수신할지 정의하는 IntentFilter 객체를 생성합니다.
        // 생성자에서 첫 번째 액션(ACTION_SCREEN_ON)을 지정합니다.
        val filter = IntentFilter(Intent.ACTION_SCREEN_ON).apply {
            // apply 확장 함수를 사용하여 filter 객체에 추가 설정을 합니다.
            // addAction 메소드를 호출하여 화면 꺼짐(ACTION_SCREEN_OFF) 액션도 수신하도록 필터에 추가합니다.
            addAction(Intent.ACTION_SCREEN_OFF)
        }

        // 생성한 BroadcastReceiver 객체(receiver)와 IntentFilter 객체(filter)를 사용하여
        // 시스템에 리시버를 동적으로 등록합니다. 이 호출 이후부터 해당 액션들을 수신하게 됩니다.
        // API 레벨 33 이상에서는 export 상태 플래그를 명시적으로 지정해야 할 수 있습니다.
        registerReceiver(receiver, filter)
        // 등록 완료 로그 (디버깅 목적)
        Log.d("omrs", "Receiver registered in onCreate")
    }

    /**
     * 액티비티가 포커스를 잃고 일시정지 상태로 들어갈 때 호출됩니다.
     * (예: 다른 액티비티가 전면에 표시되거나, 전화 수신 등)
     * 이 메소드에서는 동적으로 등록했던 브로드캐스트 리시버를 해제합니다.
     * 리시버를 onPause에서 해제하면 액티비티가 화면에 보이지 않는 동안 불필요한 시스템 이벤트를
     * 수신하지 않아 시스템 리소스 및 배터리를 절약할 수 있습니다.
     */
    override fun onPause() {
        // 반드시 부모 클래스(AppCompatActivity)의 onPause를 먼저 호출해야 합니다.
        super.onPause()
        // onCreate에서 등록했던 BroadcastReceiver(receiver)를 시스템에서 해제합니다.
        // 해제된 이후에는 더 이상 해당 액션의 브로드캐스트를 수신하지 않습니다.
        unregisterReceiver(receiver)
        // 해제 완료 로그 (디버깅 목적)
        Log.d("omrs", "Receiver unregistered in onPause")
    }

    // 참고: 이 코드에서는 onResume()에서 다시 registerReceiver를 호출하지 않으므로,
    // 액티비티가 onPause 상태에 들어갔다가 다시 onResume 상태로 돌아와도
    // 화면 켜짐/꺼짐 이벤트는 더 이상 수신되지 않습니다.
    // 만약 액티비티가 화면에 보이는 동안 계속 수신해야 한다면,
    // 리시버 등록은 onResume()에서, 해제는 onPause()에서 하는 것이 일반적인 패턴입니다.
    // 또는 onCreate()에서 등록하고 onDestroy()에서 해제하는 패턴도 있습니다.
    // 현재 코드는 onCreate() 등록, onPause() 해제 패턴입니다.

}
