package com.sevtinge.cemiuiler.ui.home;

import androidx.fragment.app.Fragment;

import com.sevtinge.cemiuiler.R;
import com.sevtinge.cemiuiler.ui.base.SubFragment;
import com.sevtinge.cemiuiler.ui.home.base.BaseHomeActivity;

public class HomeLayoutActivity extends BaseHomeActivity {

    @Override
    public Fragment initFragment() {
        return new HomeLayoutFragment();
    }

    public static class HomeLayoutFragment extends SubFragment {

        @Override
        public int getContentResId() {
            return R.xml.home_layout;
        }
    }
}
