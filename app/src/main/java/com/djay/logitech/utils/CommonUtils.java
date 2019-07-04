package com.djay.logitech.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Common Utility class
 */
public class CommonUtils {

  /**
   * Calculates no of columns which can fit on screen for RecyclerView
   *
   * @param context Context
   * @param columnWidthDp column width in dp
   * @return no of columns
   */
  public static int calculateNoOfColumns(Context context, float columnWidthDp) {
    DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
    float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
    return (int) (screenWidthDp / columnWidthDp + 0.5);
  }
}
