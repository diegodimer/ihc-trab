
package com.example.ppgc;


import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.httpRequests.HttpRequests;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class CapturaFragment extends Fragment {

    private Button btnScan;
    private String userId;
    private HttpRequests httpRequester;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_teladecaptura, container, false);

        btnScan = (Button) view.findViewById(R.id.btnScan);
        Bundle bundle = getArguments();
        this.userId = bundle.getString("userId");
        this.httpRequester = (HttpRequests) bundle.getSerializable("httpRequester");

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(getActivity());
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Camera Scan");
                integrator.setCameraId(0);
                integrator.initiateScan();
            }
        });
        return view;
    }
    protected void onActionResult(int requestCode, int resultCode, Intent data) throws IOException {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents() != null){
                LocalDate date = LocalDate.now();
                httpRequester.addUserPresence(this.userId, String.valueOf(date.getDayOfMonth()),
                        String.valueOf(date.getMonth()), String.valueOf(date.getYear()), "", "PRESENT");
            }
            else{
                alert("Scan cancelado");
            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void alert (String msg){
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }
}