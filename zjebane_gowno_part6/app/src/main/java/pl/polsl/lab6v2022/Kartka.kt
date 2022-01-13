package pl.polsl.lab6v2022

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.content.Intent




class Kartka : AppCompatActivity() {
    private var giftString: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //WebView - kontrolka wyswietlajaca html
        val page = WebView(this)

        //wlaczenie obslugi JS
        page.settings.javaScriptEnabled=true

        //dodanie interfejsu pomiędzy Kotlinem a JS
        //this - obiekt tej klasy dostarcza metody JSInterface, activity - nazwa widoczna w JS
        page.addJavascriptInterface(this, "activity") //ODKOMENTOWAC DLA JS

        //zaladowanie zawartosci kontroli WebView - pliki z katalogu assests w projekcie
        page.loadUrl("file:///android_asset/Kartka.html")

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        val intent = intent //wywołanie na rzecz aktywności

        giftString= intent.getStringExtra("gifts").toString()

        //wstawienie kontrolki WebView jako calej fasady aktywnosci
        setContentView(page)

    }
    @JavascriptInterface //adnotacja sygnalizujaca ze metoda bedzie dostepna z poziomu JS
    fun getPresents(): String {
//        Toast.makeText(this, name, Toast.LENGTH_SHORT).show()
        return giftString
    }
}