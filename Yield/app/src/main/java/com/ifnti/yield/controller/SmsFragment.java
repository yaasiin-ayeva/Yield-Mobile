package com.ifnti.yield.controller;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.ifnti.yield.R;
import com.ifnti.yield.model.Sending;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SmsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SmsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button btn_send_sms;

    public SmsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SmsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SmsFragment newInstance(String param1, String param2) {
        SmsFragment fragment = new SmsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_sms, container, false);
        btn_send_sms = (Button) root.findViewById(R.id.btn_send_sms);
        btn_send_sms.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendAll(Static.sendings, Static.senderAdress);
            }
        });
        return root;
    }

    private void sendAll(ArrayList<Sending> sendings, String senderAddress) {
        String sendLog = "";

        SmsManager smsManager = SmsManager.getDefault();
        String contactName, contactPhoneNo, textMessage;
        for (Sending sending : sendings) {
            contactName = sending.getPerson().getFirstName() + " " + sending.getPerson().getSurName();
            contactPhoneNo = sending.getPerson().getPhoneNumber();
            textMessage = sending.getMessage().getTextMessage();

            smsManager.sendTextMessage(contactPhoneNo, senderAddress, textMessage, null, null);

            sendLog += "SMS Successfully sent to " + contactName + " (" + contactPhoneNo + ")\n";
        }
        Toast.makeText(this.getContext(), "Finished", Toast.LENGTH_LONG).show();
    }
}