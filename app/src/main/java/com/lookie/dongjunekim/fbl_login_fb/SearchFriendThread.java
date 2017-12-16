package com.lookie.dongjunekim.fbl_login_fb;

import android.os.Handler;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * Created by dongjunekim on 2017-12-12.
 */

public class SearchFriendThread extends Thread {
    private Handler handler;
    private UserInfo userInfo;
    private String fId;
    private boolean check = true;
    private FirebaseDatabase fbDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference reference = fbDatabase.getReference();

    public SearchFriendThread(Handler handler, String fId) {
        this.handler = handler;
        this.fId = fId;
    }

    @Override
    public void run() {
        super.run();
        Query myTopPostsQuery = reference.child("userInfo").orderByChild("id").equalTo(fId);
        myTopPostsQuery.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                UserInfo oUserInfo = dataSnapshot.getValue(UserInfo.class);
                if (oUserInfo.getId().equals("")) {
                    check = false;
                } else {
                    Log.d("Thread UserInfoCheck", oUserInfo.toString());
                    check = true;
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        if (!check) {
            reference.child("userInfo").push().setValue(userInfo);
        }


    }
}
