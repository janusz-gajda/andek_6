package pl.polsl.lab6v2022

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.Toast
import java.util.*

class Prezenty : AppCompatActivity() {
    val gifts: MutableList<String> = ArrayList()
    var i = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //WebView - kontrolka wyswietlajaca html
        val page = WebView(this)

        //wlaczenie obslugi JS
        page.settings.javaScriptEnabled=true

        //dodanie interfejsu pomiędzy Kotlinem a JS
        //this - obiekt tej klasy dostarcza metody JSInterface, activity - nazwa widoczna w JS
        page.addJavascriptInterface(this, "activity")

        //zaladowanie zawartosci kontroli WebView - pliki z katalogu assests w projekcie
        page.loadUrl("file:///android_asset/Prezenty.html")

        //wstawienie kontrolki WebView jako calej fasady aktywnosci
        setContentView(page)
    }

    @JavascriptInterface //adnotacja sygnalizujaca ze metoda bedzie dostepna z poziomu JS
    fun addGift(name: String) {
//        Toast.makeText(this, name, Toast.LENGTH_SHORT).show()
        gifts.add("$i. $name")
        i++
    }

    @JavascriptInterface
    fun generate() {
        val intent = Intent(this, Kartka::class.java)
        var stringS = gifts.joinToString(separator = "\n")
        intent.putExtra("gifts", stringS);
        startActivity(intent)
    }


//    @JavascriptInterface
//    fun getDate(): String {
//        return Date().toString()
//    }
}