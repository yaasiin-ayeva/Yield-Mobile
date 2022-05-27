package com.ifnti.yield.controller.whatsapp;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.provider.Settings;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.ifnti.yield.R;
import com.ifnti.yield.controller.Static;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WhatsappFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WhatsappFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button btn_send_whatsapp;
    private String send_type = "text/plain";
    private String wha_package = "com.whatsapp.w4b";

    private final int ERROR_PHONE_NUMBER_FORMAT = R.string.ERROR_PHONE_NUMBER_FORMAT;

    public WhatsappFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WhatsappFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WhatsappFragment newInstance(String param1, String param2) {
        WhatsappFragment fragment = new WhatsappFragment();
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

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_whatsapp, container, false);
        btn_send_whatsapp = (Button) root.findViewById(R.id.btn_send_whathsapp);
        btn_send_whatsapp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    //send(Static.sendings, Static.senderAdress);
                } catch (NumberFormatException e) {
                    Toast.makeText(WhatsappFragment.this.getContext(), e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
        return root;
    }

    /*private void send(ArrayList<Sending> sendings, String senderAddress) throws NumberFormatException {
        String sendLog = "";
        Intent sendIntent = null;
        String contactName, contactPhoneNo, textMessage;
        for (Sending sending : sendings) {
            sendIntent = new Intent(Intent.ACTION_SEND);
            contactPhoneNo = sending.getPhoneNo();
            textMessage = sending.getTextMessage();

            sendIntent.setType(send_type);
            sendIntent.putExtra(Intent.EXTRA_TEXT, textMessage + "\n" + getString(R.string.whatsapp_suffix));
            sendIntent.putExtra("jid", reformat(contactPhoneNo) + "@s.whatsapp.net"); //phone number without "+" prefix
            sendIntent.setPackage(wha_package);

            startActivity(sendIntent);

            if (!isAccessibilityOn (WhatsappFragment.this.getActivity(), WhatsappAccessibilityService.class)) {
                Intent intent = new Intent (Settings.ACTION_ACCESSIBILITY_SETTINGS);
                this.startActivity (intent);
            }

            sendLog += "SMS Successfully sent to " + contactPhoneNo + ")\n";
        }
        Toast.makeText(this.getContext(), sendLog,Toast.LENGTH_LONG).show();
    }*/

    private boolean isAccessibilityOn (FragmentActivity fragmentActivity, Class<? extends AccessibilityService> clazz) {
        int accessibilityEnabled = 0;
        final String service = fragmentActivity.getPackageName () + "/" + clazz.getCanonicalName ();
        try {
            accessibilityEnabled = Settings.Secure.getInt (fragmentActivity.getApplicationContext ().getContentResolver (), Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException ignored) {  }

        TextUtils.SimpleStringSplitter colonSplitter = new TextUtils.SimpleStringSplitter(':');

        if (accessibilityEnabled == 1) {
            String settingValue = Settings.Secure.getString (fragmentActivity.getApplicationContext ().getContentResolver (), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (settingValue != null) {
                colonSplitter.setString (settingValue);
                while (colonSplitter.hasNext ()) {
                    String accessibilityService = colonSplitter.next ();

                    if (accessibilityService.equalsIgnoreCase (service)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // reformat a phone number
    private String reformat(String phoneNo) throws RuntimeException {
        phoneNo = phoneNo.replaceAll("\\s", ""); // To remove all white spaces from String
        if (phoneNo.length() == 8) {
            return "228" + phoneNo;
        }
        throw new NumberFormatException(getString(ERROR_PHONE_NUMBER_FORMAT) + " : " + phoneNo);
    }
}