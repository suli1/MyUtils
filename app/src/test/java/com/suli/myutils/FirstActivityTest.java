package com.suli.myutils;

import android.content.Intent;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

/**
 * Created by suli on 16-3-23.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class FirstActivityTest {

  @Test
  public void testMainActivity() {
//    MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
//    mainActivity.findViewById(R.id.textView1).performClick();
//
//    Intent expectedIntent = new Intent(mainActivity, SecondActivity.class);
//    ShadowActivity shadowActivity = Shadows.shadowOf(mainActivity);
//    Intent actualIntent = shadowActivity.getNextStartedActivity();
//    Assert.assertEquals(expectedIntent, actualIntent);
  }

}
