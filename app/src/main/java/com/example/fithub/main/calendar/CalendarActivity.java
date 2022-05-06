package com.example.fithub.main.calendar;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.fithub.R;
import com.example.fithub.databinding.ActivityCalendarBinding;

public class CalendarActivity extends AppCompatActivity {

  CalendarView simpleCalendarView;
  private AppBarConfiguration appBarConfiguration;
  private ActivityCalendarBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    binding = ActivityCalendarBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    setSupportActionBar(binding.toolbar);

    NavController navController =
        Navigation.findNavController(this, R.id.nav_host_fragment_content_calendar);
    appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
    NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

    // Calender code:
    simpleCalendarView =
        (CalendarView) findViewById(R.id.simpleCalendarView); // get the reference of CalendarView
    simpleCalendarView.setFocusedMonthDateColor(
        Color.RED); // set the red color for the dates of  focused month
    simpleCalendarView.setUnfocusedMonthDateColor(
        Color.BLUE); // set the yellow color for the dates of an unfocused month
    simpleCalendarView.setSelectedWeekBackgroundColor(
        Color.RED); // red color for the selected week's background
    simpleCalendarView.setWeekSeparatorLineColor(
        Color.GREEN); // green color for the week separator line
    // perform setOnDateChangeListener event on CalendarView
    simpleCalendarView.setOnDateChangeListener(
        new CalendarView.OnDateChangeListener() {
          @Override
          public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
            // display the selected date by using a toast
            Toast.makeText(
                    getApplicationContext(),
                    dayOfMonth + "/" + month + "/" + year,
                    Toast.LENGTH_LONG)
                .show();
          }
        });
  }

  @Override
  public boolean onSupportNavigateUp() {
    NavController navController =
        Navigation.findNavController(this, R.id.nav_host_fragment_content_calendar);
    return NavigationUI.navigateUp(navController, appBarConfiguration)
        || super.onSupportNavigateUp();
  }
}
