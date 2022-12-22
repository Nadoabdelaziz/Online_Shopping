//package com.example.online_shopping.fragment;
//
//
//import android.Manifest;
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.pm.PackageManager;
//import android.database.Cursor;
//import android.location.Location;
//import android.location.LocationManager;
//import android.os.Bundle;
//
//import androidx.core.app.ActivityCompat;
//import androidx.fragment.app.Fragment;
//
//import android.provider.Settings;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.online_shopping.Adabter.CartAdapter;
//import com.example.online_shopping.MyDatabase;
//import com.example.online_shopping.Model.ProductModel;
//import com.example.online_shopping.R;
//import com.example.online_shopping.MainActivity;
////import com.google.gson.Gson;
//
//import java.util.ArrayList;
//
///**
// * A simple {@link Fragment} subclass.
// */
//
//
//public class Cart extends Fragment {
//
//    private ListView cart_products;
//    private CartAdapter adapter;
//    private ArrayList<ProductModel> data = new ArrayList<>();
//    Button btn;
//    private MyDatabase database;
//    private SharedPreferences sharedPreferences;
//    private static final int REQUEST_LOCATION = 1;
//    LocationManager locationManager;
//
//
//    TextView orignal_price,delivery_cost,total_cost;
//    double cost=0;
//
//    int PERMISSION_ID = 44;
//
//    public Cart() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_cart, container, false);
//
//
//        cart_products = view.findViewById(R.id.cart_product);
//        database = new MyDatabase(getContext());
//
//        orignal_price=view.findViewById(R.id.order_price);
//        delivery_cost=view.findViewById(R.id.delivery_cost);
//        total_cost=view.findViewById(R.id.total_cost);
//
//
//        getProductsids();
//
//        btn=view.findViewById(R.id.confirm_order);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LocationManager nManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
//                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//                    OnGPS();
//                } else {
//                    getLocation();
//                }
//            }
//        });
//        return view;
//    }
//    private void OnGPS() {
//        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
//            }
//        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
//        final AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }
//    private void getLocation() {
//        if (ActivityCompat.checkSelfPermission(
//                getContext(),Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
//        } else {
//            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//            if (locationGPS != null) {
//                double lat = locationGPS.getLatitude();
//                double longi = locationGPS.getLongitude();
//                //latitude = String.valueOf(lat);
//                //longitude = String.valueOf(longi);
//                //showLocation.setText("Your Location: " + "\n" + "Latitude: " + latitude + "\n" + "Longitude: " + longitude);
//            } else {
//                Toast.makeText(getContext(), "Unable to find location.", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    private void getProductsids() {
//        sharedPreferences = this.getActivity().getSharedPreferences("cart", Context.MODE_PRIVATE);
//        String ids = sharedPreferences.getString("lastorder", null);
//        if (ids != null) {
//            Gson gson = new Gson();
//            ArrayList id = gson.fromJson(ids, ArrayList.class);
//            getCartProduct(id);
//
//
//            adapter = new CartAdapter(getContext(), data);
//            adapter.setTotal_cost(cost);
//            cart_products.setAdapter(adapter);
//
//
//            orignal_price.setText(String.valueOf(adapter.getTotal_cost()) + " $");
//            delivery_cost.setText("20.0 $");
//            total_cost.setText(cost + 20.0 + "$");
//        }
//
//
//    }
//
//    private void getCartProduct(ArrayList<Integer> ids) {
//
//        data.clear();
//        for (int i = 0; i < ids.size(); i++) {
//            Cursor cursor = database.getProductbyId(String.valueOf(ids.get(i)));
//            if (cursor != null) {
//                ProductModel productModel = new ProductModel(Integer.parseInt(cursor.getString(4)),
//                        Integer.parseInt(cursor.getString(5)),
//                        cursor.getString(1), cursor.getBlob(2),
//                        Double.parseDouble(cursor.getString(3)));
//                productModel.setPro_id(Integer.parseInt(cursor.getString(0)));
//                data.add(productModel);
//                cost+=Double.parseDouble(cursor.getString(3));
//            }
//        }
//
//    }
//
//
//
//
//
//
//    private boolean checkPermissions(){
//        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
//                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
//            return true;
//        }
//        return false;
//    }
//
//    private void requestPermissions(){
//        ActivityCompat.requestPermissions(
//                getActivity(),
//                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
//                PERMISSION_ID
//        );
//    }
//
//    private boolean isLocationEnabled() {
//        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
//        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
//                LocationManager.NETWORK_PROVIDER
//        );
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == PERMISSION_ID) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Granted. Start getting the location information
//            }
//        }
//
//}
//}
