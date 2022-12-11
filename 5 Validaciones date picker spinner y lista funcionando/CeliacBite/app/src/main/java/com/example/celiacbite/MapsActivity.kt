package com.example.celiacbite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.celiacbite.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.CameraPosition

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // SE GENERA LAS LOCALIZACIONES DE LOCALES SIN GLUTTEN DE SANTIAGO
        val santiago = LatLng(-33.459229,-70.645348)

        val GraneroGoloso = LatLng(-33.437512366358234, -70.64785939343288)
        val ProductosPueblo = LatLng(-33.46225841904082, -70.61739563634454)
        val TostaduriaTalca1 = LatLng(-33.43747847358406, -70.65559577924186)
        val TostaduriaTalca2 = LatLng(-33.43814032089154, -70.64333999871049)
        val TastyFree = LatLng(-33.43598077591011, -70.6433485603649)
        val alhasSushi = LatLng(-33.445891012650705, -70.62752021313518)
        val Biomercado = LatLng(-33.43958792369919, -70.64787902041684)
        val PasteleriaDosPolos = LatLng(-33.4544355928256, -70.61750088974505)
        val BarItalia = LatLng(-33.447451387894404, -70.62460931062854)


        //SE GENERA UN TIT
        mMap.addMarker(MarkerOptions().position(GraneroGoloso).title("Granero Del Goloso"))
        mMap.addMarker(MarkerOptions().position(ProductosPueblo).title("Productos Del Pueblo"))
        mMap.addMarker(MarkerOptions().position(TostaduriaTalca1).title("Tostaduria Talca"))
        mMap.addMarker(MarkerOptions().position(TostaduriaTalca2).title("Tostaduria Talca"))
        mMap.addMarker(MarkerOptions().position(TastyFree).title("Tasty Free"))
        mMap.addMarker(MarkerOptions().position(alhasSushi).title("Alhas Sushi"))
        mMap.addMarker(MarkerOptions().position(Biomercado).title("Biomercado"))
        mMap.addMarker(MarkerOptions().position(PasteleriaDosPolos).title("Pasteleria Dos Polos"))
        mMap.addMarker(MarkerOptions().position(BarItalia).title("Bar Italia Glutten Free"))



        //POSICIONAMOS LA CAMARA EN LA LOCALIZACION
        // Mueve la cámara instantáneamente a Santiago con un zoom de 10.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(santiago,10f))

        // Construye una CameraPosition centrada en Ciisa Republica y anima la cámara a esa posición.
        val cameraPosition = CameraPosition.Builder()
            .target(santiago) // Establece el centro del mapa en Ciisa Republica
            .zoom(13f) // Establece el zoom
            .bearing(90f) // Establece la orientación de la cámara al este
            .tilt(30f) // Establece la inclinación de la cámara a 30 grados
            .build() // Crea una CameraPosition a partir del constructor
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }
}