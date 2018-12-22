package uz.nasiba.avaz.memome.ui.create;

import android.app.DatePickerDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import uz.nasiba.avaz.memome.R;
import uz.nasiba.avaz.memome.databinding.ActivityCreateBinding;
import uz.nasiba.avaz.memome.utils.LocaleManager;

public class CreateActivity extends AppCompatActivity {
    private ActivityCreateBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle("");
        }
        CreateViewModel viewModel = ViewModelProviders
                .of(this, new ViewModelProvider.AndroidViewModelFactory(getApplication()))
                .get(CreateViewModel.class);
        binding = ActivityCreateBinding.inflate(getLayoutInflater());
        binding.setViewmodel(viewModel);
        binding.setLifecycleOwner(this);
        setContentView(binding.getRoot());
        init();
    }

    private void init() {
        binding.getRoot().findViewById(R.id.date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar myCalendar = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String myFormat = "MM/dd/yy";
                        SimpleDateFormat format = new SimpleDateFormat(myFormat, Locale.US);
                        binding.getViewmodel().date.setValue(format.format(myCalendar.getTime()));
                    }
                };
                new DatePickerDialog(CreateActivity.this, R.style.DatePickerStyle, listener,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        binding.getRoot().findViewById(R.id.pictures).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 2);
            }
        });

        binding.getViewmodel().pictures.observe(this, new Observer<ArrayList<Uri>>() {
            @Override
            public void onChanged(@Nullable final ArrayList<Uri> uris) {
                if (uris != null) {
                    RecyclerView recyclerView = findViewById(R.id.container);
                    recyclerView.setLayoutManager(new GridLayoutManager(binding.getRoot().getContext(), 3));
                    recyclerView.setAdapter(new PicturesAdapter(binding.getRoot().getContext(), uris, binding.getViewmodel()));
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2 && resultCode == AppCompatActivity.RESULT_OK) {
            ArrayList<Uri> imagesEncodedList = binding.getViewmodel().pictures.getValue();
            if (imagesEncodedList != null) {
                if (data.getData() != null) {
                    imagesEncodedList.add(data.getData());
                } else if (data.getClipData() != null) {
                    for (int i = 0; i < data.getClipData().getItemCount(); i++) {
                        imagesEncodedList.add(data.getClipData().getItemAt(i).getUri());
                    }
                }
            }
            binding.getViewmodel().pictures.setValue(imagesEncodedList);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.setLocale(base));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleManager.setLocale(this);
    }
}
