package com.amk.morris;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.Objects;

public class SocketAct extends AppCompatActivity {

    private Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        try {
            socket = IO.socket("http://morrisfa.eu-4.evennode.com:80");
            socket.connect();
            socket.emit("join", "Haji");
            if (socket.connected())
                Log.i("TAG", "CONNECTED");
            else
                Log.i("TAG", "NOT");
        } catch (URISyntaxException e) {
            Log.i("TAG", Objects.requireNonNull(e.getMessage()));
            e.printStackTrace();
        }
        //implementing socket listeners
        socket.on("connection", args -> runOnUiThread(() -> {
            String data = (String) args[0];

            Toast.makeText(SocketAct.this, data, Toast.LENGTH_SHORT).show();

        }));
        socket.on("disconnect", args -> runOnUiThread(() -> {
            String data = (String) args[0];
            Toast.makeText(SocketAct.this, data, Toast.LENGTH_SHORT).show();

        }));
        socket.on("message", args -> runOnUiThread(() -> {
            JSONObject data = (JSONObject) args[0];
            try {
                //extract data from fired event
                Log.i("TAG", data.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }


        }));
    }
}
