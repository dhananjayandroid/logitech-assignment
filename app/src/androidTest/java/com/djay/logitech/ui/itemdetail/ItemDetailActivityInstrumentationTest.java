package com.djay.logitech.ui.itemdetail;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.djay.logitech.ui.itemdetail.ItemDetailViewModel.ARG_ITEM;
import static org.hamcrest.Matchers.is;

import android.content.Intent;
import android.text.Html;
import androidx.appcompat.widget.Toolbar;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;
import com.djay.logitech.R;
import com.djay.logitech.service.model.Movie;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4ClassRunner.class)
public class ItemDetailActivityInstrumentationTest {

  @Rule
  public ActivityTestRule<ItemDetailActivity> rule =
      new ActivityTestRule(ItemDetailActivity.class, true, false);

  @Before
  public void setUp() {
  }

  @Test
  public void activityViewsTest() {
    Movie movie = new Movie();
    movie.setTitle("Movie Title");
    movie.setPlot("Movie Plot");
    movie.setYear("1995");

    Intent intent = new Intent();
    intent.putExtra(ARG_ITEM, movie);
    rule.launchActivity(intent);

    onView(isAssignableFrom(Toolbar.class))
        .check(matches(withToolbarTitle(is("Movie Title"))));
    onView(withId(R.id.tv_plot)).check(matches(withText("Movie Plot")));
    onView(withId(R.id.tv_year)).check(matches(withText(
        Html.fromHtml(String.format(rule.getActivity().getString(R.string.year), "1995"))
            .toString())));
  }

  private static Matcher<Object> withToolbarTitle(
      final Matcher<CharSequence> textMatcher) {
    return new BoundedMatcher<Object, Toolbar>(Toolbar.class) {
      @Override
      public boolean matchesSafely(Toolbar toolbar) {
        return textMatcher.matches(toolbar.getTitle());
      }

      @Override
      public void describeTo(Description description) {
        description.appendText("with toolbar title: ");
        textMatcher.describeTo(description);
      }
    };
  }
}
