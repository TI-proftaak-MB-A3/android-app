package nl.avans.ti;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class Slider extends FragmentStatePagerAdapter {

    private List<Fragment> FragmenList;

    public Slider(FragmentManager fragmentManager, List<Fragment> fragmenList) {
        super(fragmentManager);

        this.FragmenList = fragmenList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return this.FragmenList.get(position);
    }

    @Override
    public int getCount() {
        return this.FragmenList.size();
    }
}
