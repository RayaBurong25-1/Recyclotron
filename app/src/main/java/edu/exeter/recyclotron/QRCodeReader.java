package edu.exeter.recyclotron;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;

import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import info.androidhive.barcode.BarcodeReader;

public class QRCodeReader extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener {

    BarcodeReader barcodeReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrcodereader);
        barcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_scanner);
    }

    boolean decrypt(Barcode barcode)
    {
        String val = barcode.displayValue;
        if (!val.substring(0, 14).equals("<recyclotron>"))
            return false;
        int[] add = new int[6];
        for (int i = 0; i < 6; i++)
        {
            if (!val.substring(14 + i*10, 14 + i*10 + 3).equals("<"+i+">"))
                return false;
            if (!val.substring(14 + i*10 + 6, 14 + i*10 + 10).equals("</"+i+">"))
                return false;
            try {
                add[i] = Integer.valueOf(val.substring(14 + i * 10 + 3, 14 + i * 10 + 6));
            }
            catch (NumberFormatException e) {
                return false;
            }
        }
        if (!val.substring(74, 89).equals("</recyclotron>"))
            return false;
        //TODO: add this to local database
        return true;
    }

    @Override
    public void onScanned(Barcode barcode) {
        // single barcode scanned
        if (decrypt(barcode)) {
            barcodeReader.playBeep();

            //TODO: put scanned into log

            Intent intent = new Intent(QRCodeReader.this, Dashboard.class);
            startActivity(intent);
        }
    }

    @Override
    public void onScannedMultiple(List<Barcode> list) {
        // multiple barcodes scanned
    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {
        // barcode scanned from bitmap image
    }

    @Override
    public void onScanError(String s) {
        // scan error
    }

    @Override
    public void onCameraPermissionDenied() {
        // camera permission denied
        finish();
    }
}
