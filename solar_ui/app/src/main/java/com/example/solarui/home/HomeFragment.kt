package com.example.solarui.home

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.solarui.R
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    var pos = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        
//        Item(R.drawable.image2, "Earth", "Earth is the third planet from the Sun and the only astronomical object known to harbor life. According to radiometric dating and other sources of evidence, Earth formed over 4.5 billion years ago.",
//            R.drawable.earth),
        val item = arrayListOf<Item>(
            Item(R.drawable.image1, "Mars", "Mars is the fourth planet from the Sun and the second-smallest planet in the Solar System after Mercury. In English, Mars carries a name of the Roman god of war and is often referred to as the 'Red Planet'.",
                R.drawable.mars),
            Item(R.drawable.image1, "Mars", "Mars is the fourth planet from the Sun and the second-smallest planet in the Solar System after Mercury. In English, Mars carries a name of the Roman god of war and is often referred to as the 'Red Planet'.",
                R.drawable.mars),
            Item(R.drawable.image1, "Mars", "Mars is the fourth planet from the Sun and the second-smallest planet in the Solar System after Mercury. In English, Mars carries a name of the Roman god of war and is often referred to as the 'Red Planet'.",
                R.drawable.mars),
            Item(R.drawable.image1, "Mars", "Mars is the fourth planet from the Sun and the second-smallest planet in the Solar System after Mercury. In English, Mars carries a name of the Roman god of war and is often referred to as the 'Red Planet'.",
                R.drawable.mars),
            Item(R.drawable.image1, "Mars", "Mars is the fourth planet from the Sun and the second-smallest planet in the Solar System after Mercury. In English, Mars carries a name of the Roman god of war and is often referred to as the 'Red Planet'.",
                R.drawable.mars)
        )

        setGif(item[0].animatedImage)
        setGifNext( item[1].animatedImage)

        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

                System.out.println(positionOffset)

                if(positionOffset != 0f){
                    if(pos+1 < item.size-1){
                        setGifNext(item[pos+1].animatedImage)
                    }
                    setGif(item[pos].animatedImage)
                    System.out.println("움직임")

                    ObjectAnimator.ofFloat(solar_img1, "translationX", positionOffset * - 1600).apply {
                        duration = 0
                        start()
                    }
                    ObjectAnimator.ofFloat(solar_img2, "translationX", positionOffset * - 950).apply {
                        duration = 0
                        start()
                    }


                }



            }
            override fun onPageSelected(position: Int) {
                circleAnimIndicator.selectDot(position)

                pos = position

                System.out.println("선택완료")

                if(position+1 < item.size-1){
                    System.out.println("다음" + item[position+1].text)
                    setGifNext(item[pos+1].animatedImage)
                }
                setGif(item[pos].animatedImage)
                System.out.println("메인" + item[position].text)
            }

        })


        val photoViewPagerAdapter = VPAdapter(item as ArrayList<Item>)
        viewpager.adapter = photoViewPagerAdapter
        viewpager.offscreenPageLimit = item.size-1
        viewpager.setPageTransformer(true, ViewPagerCardTransformer())

        //원사이의 간격
        circleAnimIndicator.setItemMargin(15);
        //애니메이션 속도
        circleAnimIndicator.setAnimDuration(300);
        //indecator 생성
        circleAnimIndicator.createDotPanel(item.size, R.drawable.viewpage_indicator_off , R.drawable.viewpager_indicator_on);

    }

    fun setGif(url : Int){

        Glide
            .with(context!!)
            .load(url)
            .apply(
                RequestOptions()
                    .circleCrop())
            .into(solar_img1)

    }
    fun setGifNext(next: Int){


        Glide
            .with(context!!)
            .load(next)
            .apply(
                RequestOptions()
                    .circleCrop())
            .into(solar_img2)

    }


    class ViewPagerCardTransformer : ViewPager.PageTransformer {
        override fun transformPage(page: View, position: Float) {


            if(position <= 0){
                page.scaleX = 0.9f + 0.05f * position
                page.scaleY = 0.9f + 0.05f * position
                page.alpha = 1 + 0.3f * position
                page.translationX = page.width * position
                page.translationY = -30 * position



            }else{

                page.scaleX = 0.9f - 0.1f * position
                page.scaleY = 0.9f - 0.15f * position
                page.alpha = 1f - 0.3f * position
                page.translationX = -page.width * position
                page.translationY = 150 * position

            }


        }
    }
}