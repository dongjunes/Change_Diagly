package com.lookie.dongjunekim.fbl_login_fb;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.facebook.Profile;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dongjunekim on 2017-12-12.
 */

public class GetVoDataThread extends Thread {

    private Handler handler;
    private FirebaseDatabase fbDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference reference = fbDatabase.getReference();
    private String id;
    private int process;

    public GetVoDataThread(Handler handler, String id, int process) {
        this.handler = handler;
        this.id = id;
        this.process = process;
    }

    @Override
    public void run() {
        super.run();
        if (process == SearchFragment.GET_MY_ID) {
            reference.child("userInfo").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    UserInfo userInfo = dataSnapshot.getValue(UserInfo.class);
                    Log.d("userInfo MainActivity", dataSnapshot.toString());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("userInfo", userInfo);
                    Log.d("userInfo MainActivity", userInfo.toString());
                    Message msg = new Message();
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } else {
            Log.d("OnClick", "yes");
            //Map<String, Object> updateMap = new HashMap<>();
            //updateMap.put("rName", Profile.getCurrentProfile().getId());
            reference.child("userInfo").child(id).child("rName").setValue(Profile.getCurrentProfile().getId());

        }

    }
}
