package com.example.ppgc;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class CalendarFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_calendar, container, false);


        //@TODO: para cada presença do usuário (ou seja, cada data, selecionar a mesma e alterar visualização
        CompactCalendarView calendarView = (CompactCalendarView) view.findViewById(R.id.calendar_view); // get the reference of CalendarView
        //alterar loop para marcar presenças corretamente
        for (int i = 0; i < 3; i++) {
            long dateInMs = getDateInMs(2021, 11, 15+i);
            Date date = new Date(dateInMs);
            calendarView.setCurrentDate(date);
            Event ev = new Event(Color.parseColor("#539153"), dateInMs, "Presença confirmada");
            calendarView.addEvent(ev);
        }

        Date date = new Date();
        calendarView.setCurrentDate(date);

        return view;
    }

    private long getDateInMs(int year, int month, int dayOfMonth) {
        LocalDate ld = LocalDate.of(year, month,dayOfMonth);
        ZonedDateTime zdt = ld.atStartOfDay(ZoneId.of("America/Los_Angeles"));
        return zdt.toInstant().toEpochMilli();
    }
}
