package com.example.fithub.main.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.fithub.R;
import com.example.fithub.databinding.FragmentTrainingDayBinding;
import com.example.fithub.main.components.Item;
import com.example.fithub.main.prototypes.data.DatabaseManager;
import com.example.fithub.main.prototypes.data.TrainingDay;

import java.util.Date;
import java.util.List;

public class TrainingDayFragment extends Fragment {

  private FragmentTrainingDayBinding binding;

  private View view;
  private TextView dateTextView;
  private EditText wellBeingView;
  private Fragment trainingPlanFragment;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    this.view = inflater.inflate(R.layout.fragment_training_day, container, false);

    final FragmentManager fragmentManager = getChildFragmentManager();
    final List<Fragment> fragmentList = fragmentManager.getFragments();
    this.wellBeingView = this.view.findViewById(R.id.well_being_value);

    setDate();

    final TextView dateTextView = this.view.findViewById(R.id.dateText);
    final Date date = DateConverter.parseStringToDate(dateTextView.getText().toString());

    // possibility of training day not existing
    TrainingDay trainingDay = DatabaseManager.appDatabase.trainingDayDao().getByDate(date);
    if (trainingDay == null) {
      trainingDay = new TrainingDay(date, 1, 1);
    }

    this.wellBeingView.setText(String.valueOf(trainingDay.getWellBeing()));

    final Bundle b = new Bundle();
    b.putInt("trainingPlanId", trainingDay.getTrainingPlanId());
    b.putInt("actionId", 1);

    fragmentList.get(0).setArguments(b);
    this.trainingPlanFragment = fragmentList.get(0);

    final Button saveButton = this.view.findViewById(R.id.save_training_day);
    saveButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {

            final String dateString = dateTextView.getText().toString();
            final int wellBeing = Integer.parseInt(wellBeingView.getText().toString());

            final Spinner spinner =
                trainingPlanFragment.getView().findViewById(R.id.spinner_training_plan);
            final Item item = (Item) spinner.getSelectedItem();
            final int id = item.getId();

            DatabaseManager.appDatabase
                .trainingDayDao()
                .insert(
                    new TrainingDay(DateConverter.parseStringToDate(dateString), id, wellBeing));
          }
        });
    return view;
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

    super.onViewCreated(view, savedInstanceState);
  }

  /** Set the text view date value. */
  private void setDate() {
    Bundle bundle = getArguments();
    Date date = null;
    if (bundle != null) {
      date = (Date) bundle.getSerializable("date");
    }
    this.dateTextView = this.view.findViewById(R.id.dateText);
    final String dateString = DateConverter.parseDateToString(date);
    this.dateTextView.setText(dateString);
  }
}
