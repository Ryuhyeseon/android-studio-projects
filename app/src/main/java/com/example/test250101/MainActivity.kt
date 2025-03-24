package com.example.test250101

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date
import java.util.Locale

// MainActivity : 알고리즘 작성
// activity_main.xml : layout 설정
class MainActivity : AppCompatActivity() { // MainActivity가 AppCompatActivity 클래스를 상속한다는 의미

    // 생성 시 호출되는 fun
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) { // savedInstanceState는 Bundle? 타입의 매개변수
        super.onCreate(savedInstanceState) // 부모 클래스인 AppCompatActivity의 onCreate 메서드를 호출하는 구문
        enableEdgeToEdge() // Status Bar & Navigation Bar까지 나오게 하는 설정
        setContentView(R.layout.activity_main) // 매개변수에 지정한 내용을 액티비티 화면에 출력 (res/layout/activity_main.xml)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        } // 액티비티에 출력되는 내용이 Navigation Bar 등과 겹치지 않게 하기 위한 설정

        // 출력
        Log.d("결과1 : ", "결과1") // Logcat
        println("결과2") // tag : System.out
        System.out.println("결과3") // tag : System.out

        // val 상수
        val data1: Int = 10
        val data2 = 20 // 추론 가능 시 타입 생략 가능
        val data = data1 + data2

        Log.d("data : ", data.toString())

        // var 변수
        var data3 = 30
        data3 = data

        val localDateTime: LocalDateTime = LocalDateTime.now() // 현재 날짜와 시간을 가져오는 LocalDateTime 객체 생성
        val localDate: LocalDate = LocalDate.now() // 현재 날짜를 가져오는 LocalDate 객체 생성
        val date: Date = Date() // 현재 시스템 시간을 기반으로 하는 Date 객체 생성

        Log.d("DateLog1 : ", "LocalDateTime: $localDateTime") // LocalDateTime 객체를 문자열로 변환하여 로그로 출력($: 단순 변수 삽입)
        Log.d("DateLog2 : ", "LocalDate: $localDate") // LocalDate 객체를 문자열로 변환하여 로그로 출력
        Log.d("DateLog3 : ", "Formatted Date: ${formatDate(date)}") // Date 객체를 포맷팅하여 문자열로 변환 후 로그로 출력(${}: 표현식 또는 메서드 호출을 포함할 때 사용)

        someFun()

        println("sum(10, 20) : ${sum(10, 20)}")

        lateinit var data4: String // byte, short, int, long, double, boolean lateinit 사용 불가능

        // array
        val array1:IntArray = IntArray(3, {0})
        val array2:BooleanArray = BooleanArray(3, {false})
        val array3 = intArrayOf(10, 20, 30)
        val array4 = booleanArrayOf(true, false, false)

        println("array1 size : ${array1.size}")
        println("array1 data : ${array1[0]}, ${array1[1]}, ${array1[2]}")
        println("array2 size : ${array2.size}")
        println("array2 data : ${array2[0]}, ${array2[1]}, ${array2[2]}")
        println("array3 size : ${array3.size}")
        println("array3 data : ${array3[0]}, ${array3[1]}, ${array3[2]}")
        println("array4 size : ${array4.size}")
        println("array4 data : ${array4[0]}, ${array4[1]}, ${array4[2]}")

        // list
        var list = listOf<Int>(10, 20, 30)
        println("list size : ${list.size}")
        println("list data : ${list[0]}, ${list[1]}, ${list[2]}")

        // mutableList
        var mutableList = mutableListOf<Int>(10, 20, 30)
        mutableList.add(3, 400)
        mutableList.add(4, 500)
        mutableList.set(0, 100)
        mutableList.set(1, 200)
        mutableList.set(2, 300)
        println("mutableList size : ${mutableList.size}")
        println("mutableList data : ${mutableList[0]}, ${mutableList[1]}, ${mutableList[2]}, ${mutableList[3]}, ${mutableList[4]}")

        // map
        var map = mapOf<String, String>(Pair("One", "hello"), "Two" to "world")
        println("map size : ${map.size}")
        println("map data : ${map.get("One")}, ${map.get("Two")}")

        // if
        if(mutableList[0] > 100){
            println("mutableList[0] > 100")
        }else if(mutableList[0] > 0 && mutableList[0] <= 100){
            println("mutableList[0] > 0 && mutableList[0] <= 100")
        }else{
            println("mutableList[0] <= 0")
        }

        // if 표현식
        val ifResult = if(mutableList[0] > 0){
            println("mutableList[0] > 0")
            true
        }else{
            println("mutableList[0] <= 0")
            false
        }
        println("ifResult : ${ifResult}")

        // when 1
        when(mutableList[0]){
            100 -> println("mutableList[0] is 100")
            200 -> println("mutableList[0] is 200")
            300 -> println("mutableList[0] is 300")
            400 -> println("mutableList[0] is 400")
            500 -> println("mutableList[0] is 500")
            else -> println("mutableList[0] is not valid data")
        }

        // when 2
        when(map.get("One")){
            "hello" -> println("map.get(\"One\") is hello")
            "world" -> println("map.get(\"One\") is world")
            else -> println("map.get(\"One\") is not valid data")
        }

        // when 표현식
        val whenResult = when(map.get("One")){
            "hello" -> "map.get(\"One\") is hello"
            "world" -> "map.get(\"One\") is world"
            else -> "map.get(\"One\") is not valid data"
        }
        println("whenResult : ${whenResult}")

        // for 1
        var sum:Int = 0
        for(i in 1..10){
            sum += i
        }
        println("for1 sum : ${sum}")

        // for 2
        print("list data : ")
        for(i in list.indices){
            print(list[i])
            if(i !== list.size - 1) print(", ")
        }
        println("")

        // while
        var x = 0
        sum = 0
        while(x<10){
            sum += ++x
            print(sum)
            if(x<10) print(", ")
        }
        println("")
        println("while sum : ${sum}")

        // R.layout.activity_main <Button> 객체
        val btn1:Button = findViewById(R.id.btn1)
        val btn2:Button = findViewById(R.id.btn2)
        val btn3:Button = findViewById(R.id.btn3)

    }

    // 날짜를 특정 형식으로 변환하는 함수
    fun formatDate(date: Date): String { // date 매개변수 이름, Date 매개변수 타입, String 함수 반환 타입
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) // Locale.getDefault()는 기기 설정에 따라 지역을 적용하여 "yyyy-MM-dd" 형식으로 날짜를 변환하기 위한 SimpleDateFormat 객체 생성
        return sdf.format(date) // SimpleDateFormat 클래스의 format() 메서드를 호출하여 Date 객체를 지정된 형식으로 변환하여 반환
    }

    fun someFun(){
        val data: Int // 초기화 x 타입 명시 필수
        data = 100
        println("fun someFun() data : $data")
        println("fun someFun() data + 100 : ${data + 100}")
        println("fun someFun() data.plus(100) : ${data.plus(100)}")

        var data2: Int? = null // ? 널 대입 허용(?를 작성하지 않으면 null 허용 x)
        val data3: Nothing? = null // Nullable Nothing 타입으로, 항상 null만 가질 수 있음

        // if(data3 == null) fail("throw IllegalArgumentException(message) Nothing 타입 함수 호출")

        val a1: Byte = 0b00001010
        val a2: Int = 11
        val a3: Short = 12
        val a4: Long = 13L
        val a5: Double = 14.0
        val a6: Float = 15.0f
        val a7: Boolean = true

        val a8: Char = 'a'
        val a9: String = "hello world"

        val a10: Any = 16 // Any 타입 모든 값 대입 가능
        val a11: Any = "String"
        val a12: Any = innerclass()

        println("fun someFun() a1 : $a1")
        println("fun someFun() a2 : $a2")
        println("fun someFun() a3 : $a3")
        println("fun someFun() a4 : $a4")
        println("fun someFun() a5 : $a5")
        println("fun someFun() a6 : $a6")
        println("fun someFun() a7 : $a7")
        println("fun someFun() a8 : $a8")
        println("fun someFun() a9 : $a9")
        println("fun someFun() a10 : $a10")
        println("fun someFun() a11 : $a11")
        println("fun someFun() a12 : $a12")
    }

    // Nothing 타입 함수
    fun fail(message: String): Nothing { // 절대 값을 반환하지 않으므로 함수의 반환 타입은 Nothing 타입
        throw IllegalArgumentException(message)
    }

    fun sum(a: Int, b: Int): Int{
        return a + b
    }

    class innerclass
}