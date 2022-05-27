package com.ifnti.yield.controller.sms;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.telephony.SmsManager;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ifnti.yield.R;
import com.ifnti.yield.controller.MainActivity;
import com.ifnti.yield.controller.RetrofitClient;
import com.ifnti.yield.model.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private TextView txt_journal;

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
        txt_journal = (TextView) root.findViewById(R.id.journal);
        btn_send_sms.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                getContacts();
            }
        });
        recordLog(MainActivity.sms_sending_log_text);
        txt_journal.setMovementMethod(new ScrollingMovementMethod());
        return root;
    }

    void sendMessage(String msg, String number, int index) {
        try {
            SmsManager sms = SmsManager.getDefault();
            ArrayList<String> parts = sms.divideMessage(msg);

            String ACTION_SMS_SENT = "SMS_SENT";
            String ACTION_SMS_DELIVERED = "SMS_DELIVERED";

            PendingIntent piSent = PendingIntent.getBroadcast(getContext(), 0, new Intent(ACTION_SMS_SENT), 0);
            PendingIntent piDel = PendingIntent.getBroadcast(getContext(), 0, new Intent(ACTION_SMS_DELIVERED),0);

            if (parts.size() == 1) {
                msg = parts.get(0);
                sms.sendTextMessage(number, null, msg, piSent, piDel);
            } else {
                ArrayList<PendingIntent> sentPis = new ArrayList<PendingIntent>();
                ArrayList<PendingIntent> delPis = new ArrayList<PendingIntent>();

                int ct = parts.size();
                for (int i = 0; i < ct; i++) {
                    sentPis.add(i, piSent);
                    delPis.add(i, piDel);
                }
                sms.sendMultipartTextMessage(number, null, parts, sentPis, delPis);
            }
            MainActivity.sms_sending_log_text = recordLog("Message " + index + " sent to your contact " + " at " + number + "\n app@yield:~$ ");
            //recordLog("\t Message " + index + " : Status " );
            Toast.makeText(getContext(),"number of messages sent " + index ,Toast.LENGTH_SHORT).show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public String recordLog(String message) {
        String log = SmsFragment.this.txt_journal.getText()  + message;
        SmsFragment.this.txt_journal.setText(log);
        return log;
    }

    private void getContacts() {

        Call<List<Result>> call = RetrofitClient.getInstance().getMyApi().getContacts();
        call.enqueue(new Callback<List<Result>>() {

            @Override
            public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
                if (!response.isSuccessful()) {
                    MainActivity.sms_sending_log_text = recordLog("No resources found on this URL!\n app@yield:~$ ");
                    Toast.makeText(getContext(), "No resources found on this URL!", Toast.LENGTH_LONG).show();
                    return;
                }

                MainActivity.sms_sending_log_text = recordLog("Fetching data on server ...\n app@yield:~$ ");

                List<Result> results = response.body();
                if (results.size() != 0) {
                    MainActivity.sms_sending_log_text = recordLog("Data collected successfully!\n app@yield:~$ ");
                }
                for (int index = 0; index < results.size(); index++) {
                    String phoneNo = results.get(index).getMobile();
                    String msg = results.get(index).getTexteMessage();
                    //String completeName = results.get(index).get Pas encore implémenté au niveau seveur
                    sendMessage(msg, phoneNo, index + 1);
                    try {
                        Thread.sleep(70);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Result>> call, Throwable t) {
                MainActivity.sms_sending_log_text = recordLog("Enable to access the server. Check your internet connection \n app@yield:~$ ");
                Toast.makeText(getContext(), "Enable to access the server. Check your internet connection \n", Toast.LENGTH_LONG).show();
            }
        });
    }

}