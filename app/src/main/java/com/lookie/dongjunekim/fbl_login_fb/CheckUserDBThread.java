package com.lookie.dongjunekim.fbl_login_fb;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by dongjunekim on 2017-12-11.
 */

public class CheckUserDBThread extends Thread {

    private UserInfo userInfo;
    private FirebaseDatabase fbDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference reference = fbDatabase.getReference();

    public CheckUserDBThread(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public void run() {
        super.run();
        Log.d("userInfo Check Thread", userInfo.getId());
        Query myTopPostsQuery = reference.child("userInfo").child(userInfo.getId()).orderByChild("id").equalTo(userInfo.getId());
        myTopPostsQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserInfo info = dataSnapshot.getValue(UserInfo.class);
                if (info != null) {
                    Log.d("Thread중복", userInfo.toString());
                } else {
                    reference.child("userInfo").child(userInfo.getId()).setValue(userInfo);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
