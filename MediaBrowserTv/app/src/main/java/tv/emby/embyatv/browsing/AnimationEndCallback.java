package tv.emby.embyatv.browsing;

import android.view.animation.Animation;

/**
 * Created by letroll on 16/11/16.
 */

public abstract class AnimationEndCallback implements android.view.animation.Animation.AnimationListener {
    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        onAnimationEnd();
    }

    public abstract void onAnimationEnd();

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}