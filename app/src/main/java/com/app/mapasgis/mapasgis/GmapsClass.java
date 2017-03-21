package com.app.mapasgis.mapasgis;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by bryan on 19/1/2017.
 */
public class GmapsClass extends Fragment implements OnMapReadyCallback {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gmaps, container, false);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MapFragment fragment = (MapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        fragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        if (ActivityCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);

        CameraUpdate ZoomCam = CameraUpdateFactory.zoomTo(14);
        googleMap.animateCamera(ZoomCam);




        googleMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {

            public void onMyLocationChange(Location pos) {
                // TODO Auto-generated method stub

                // Extraigo la Lat y Lon del Listener
                double lat = pos.getLatitude();
                double lon = pos.getLongitude();
                final LatLng lati2 = new LatLng(pos.getLatitude(), pos.getLongitude());

                // Muevo la camara a mi posicion
                CameraUpdate cam = CameraUpdateFactory.newLatLng(new LatLng(
                        lat, lon));

                googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lat, lon)));
                googleMap.moveCamera(cam);

                /*do {

                    PolylineOptions polyline = new PolylineOptions().add(lati2).add(new LatLng(lat, lon)).color(Color.BLUE).geodesic(true);
                    polyline.getPoints();
                    googleMap.addPolyline(polyline);
                } while (cam.equals(cam));*/


                // Notifico con un mensaje al usuario de su Lat y Lon
                Toast.makeText(GmapsClass.this.getActivity(),
                        "Lat: " + lat + "\nLon: " + lon, Toast.LENGTH_SHORT)
                        .show();


                // List<LatLng> points = null;

                // if (lat!=pos.getLatitude() && lon

            }



        });







    }
}
