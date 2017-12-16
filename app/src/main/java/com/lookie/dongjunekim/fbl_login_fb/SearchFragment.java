package com.lookie.dongjunekim.fbl_login_fb;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.Profile;
import com.lookie.dongjunekim.fbl_login_fb.databinding.FragmentSearchBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static final int SET_RNAME = 0;
    public static final int CHECK_RNAME = 1;


    private final int FACEBOOKO_ID_LENGTH = 15;

    private Handler handler;
    private GetVoDataThread thread;
    FragmentSearchBinding binding;

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance(int sectionNumber) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        View view = binding.getRoot();
        setIdText();
        searchUser();
        return view;
    }

    protected void setIdText() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(Profile.getCurrentProfile().getId());
        binding.setUserInfo(userInfo);
    }

    private void searchUser() {
        binding.searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("OnClick", "check");
                String fId = binding.searchEdit.getText().toString();
                if (fId.length() == FACEBOOKO_ID_LENGTH) {
                    handler = new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            super.handleMessage(msg);
                        }
                    };
                    thread = new GetVoDataThread(handler, Profile.getCurrentProfile().getId(), SET_RNAME);
                    thread.run();
                    binding.searchEdit.setText("");
                } else {

                    Toast.makeText(getContext(), "아이디를 다시 확인해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
