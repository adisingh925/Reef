package app.android.damien.reef.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import app.android.damien.reef.fragments.SingleValueType1ViewPagerFragment
import app.android.damien.reef.fragments.SingleValueType2ViewPagerFragment
import app.android.damien.reef.fragments.TwoRectangleViewPagerFragment

class PageAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 3;
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> {
                SingleValueType2ViewPagerFragment()
            }

            1 -> {
                TwoRectangleViewPagerFragment()
            }

            2 -> {
                SingleValueType1ViewPagerFragment()
            }

            else -> {
                SingleValueType2ViewPagerFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Tab 1"
            }
            1 -> {
                return "Tab 2"
            }
            2 -> {
                return "Tab 3"
            }
        }
        return super.getPageTitle(position)
    }

}
