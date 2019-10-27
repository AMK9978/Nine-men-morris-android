package com.amk.morris;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amk.morris.Adapters.ChatBoxAdapter;
import com.amk.morris.Model.Message;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class SocketAct extends AppCompatActivity {
    public RecyclerView myRecylerView;
    public List<Message> MessageList;
    public ChatBoxAdapter chatBoxAdapter;
    public EditText messagetxt;
    public Button send;
    //declare socket object
    private Socket socket;
    private String OP_ID = "";
    public String Nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);

        messagetxt = (EditText) findViewById(R.id.message);
        send = (Button) findViewById(R.id.send);
        // get the nickame of the user
        Nickname = "AMK";
        //connect you socket client to the server
        try {
            socket = IO.socket("http://morrisfa.eu-4.evennode.com:80");
            socket.connect();
            socket.emit("join", Nickname);
        } catch (URISyntaxException e) {
            e.printStackTrace();

        }
        //setting up recyler
        MessageList = new ArrayList<>();
        myRecylerView = findViewById(R.id.messagelist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        myRecylerView.setLayoutManager(mLayoutManager);
        myRecylerView.setItemAnimator(new DefaultItemAnimator());


        // message send action
        send.setOnClickListener(v -> {
            //retrieve the nickname and the message content and fire the event messagedetection
            if (!messagetxt.getText().toString().isEmpty()) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("msg", messagetxt.getText().toString());
                    jsonObject.put("opid", OP_ID);
                    socket.emit("movement", jsonObject.toString());
                    Log.i("TAG", jsonObject.toString());
                    Log.i("TAG", "MINE");
                    messagetxt.setText(" ");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        //implementing socket listeners
        socket.on("connection", args -> runOnUiThread(() -> {
            String data = (String) args[0];
            Toast.makeText(SocketAct.this, data, Toast.LENGTH_LONG).show();
            Log.i("TAG", "Connection:" + data);
        }));
        socket.on("disconnect", args -> runOnUiThread(() -> {
            String data = (String) args[0];
            Log.i("TAG", "DISConnection:" + data);
            Toast.makeText(SocketAct.this, data, Toast.LENGTH_LONG).show();

        }));
        socket.on("message", args -> runOnUiThread(() -> {
            JSONObject data = (JSONObject) args[0];
            Log.i("TAG", "MESSAGE:" + data.toString());
        }));
        socket.on("winner", args -> runOnUiThread(() -> {
            JSONObject data = (JSONObject) args[0];
            Log.i("TAG", "WINNER:" + data.toString());
        }));
        socket.on("movement", args -> runOnUiThread(() -> {
            String data = (String) args[0];
            Log.i("TAG", "MOVEMENT:" + data);
            try {
                JSONObject jsonObject = new JSONObject(data);
                Toast.makeText(SocketAct.this, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }));
        socket.on("start", args -> runOnUiThread(() -> {
            JSONObject data = (JSONObject) args[0];
            Log.i("TAG", "START:" + data.toString());
            try {
                OP_ID = data.getString("opid");
                Log.i("TAG", OP_ID);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        socket.disconnect();
    }
}
