package com.djay.logitech.ui.itemlist;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

import android.app.Activity;
import android.app.Instrumentation.ActivityMonitor;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.idling.CountingIdlingResource;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;
import com.djay.logitech.R;
import com.djay.logitech.ui.itemdetail.ItemDetailActivity;
import com.djay.logitech.ui.itemlist.adapter.SimpleItemRecyclerViewAdapter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4ClassRunner.class)
public class ItemListActivityInstrumentationTest {

  private ActivityMonitor mItemDetailActivityMonitor;

  @Rule
  public ActivityTestRule<ItemListActivity> mActivityTestRule = new ActivityTestRule<>(
      ItemListActivity.class);

  @Before
  public void setUp() {
    mItemDetailActivityMonitor = new ActivityMonitor(
        ItemDetailActivity.class.getName(), null, false);
    getInstrumentation().addMonitor(mItemDetailActivityMonitor);
  }

  @Test
  public void ensureRecyclerViewIsPresentTest() {
    ItemListActivity activity = mActivityTestRule.getActivity();
    View viewById = activity.findViewById(R.id.item_list);
    assertThat(viewById, notNullValue());
    assertThat(viewById, instanceOf(RecyclerView.class));
    RecyclerView recyclerView = (RecyclerView) viewById;
    Adapter adapter = recyclerView.getAdapter();
    assertThat(adapter, instanceOf(SimpleItemRecyclerViewAdapter.class));
  }

  @Test
  public void recyclerViewItemClickTest() {
    CountingIdlingResource mainActivityIdlingResource = mActivityTestRule.getActivity()
        .getEspressoIdlingResourceForMainActivity();

    IdlingRegistry.getInstance().register(mainActivityIdlingResource);

    onView(withId(R.id.item_list))
        .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    Activity activity = mItemDetailActivityMonitor.waitForActivityWithTimeout(5 * 1000);
    assertNotNull("Activity was not started", activity);

    IdlingRegistry.getInstance().unregister(mainActivityIdlingResource);
  }

}
