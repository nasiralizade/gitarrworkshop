package admin.calendar;
import admin.appointment.EventEntityBean;
import admin.appointment.EventEntity;


import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Produces;
import jakarta.faces.annotation.View;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class CalendarApplication implements Serializable {
    private LocalDateTime date;
    private List<Date> invalidDates;
    private String headerDate = "Choose an available appointment";
    private List<EventEntity> events;
    @Produces
    @PersistenceContext(unitName = "Events")
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        /*Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        for (int i = 0; i < 200; i++) {
            invalidDates.add(i, new java.sql.Date(currentDate.getTime()));
            calendar.add(Calendar.DAY_OF_MONTH, -1); // Move to the next day
            currentDate = calendar.getTime(); // Get the updated date
        }*/
        LocalDateTime now=LocalDateTime.now().plusDays(3);
        Timestamp time =Timestamp.valueOf(LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth(),0,0,0));
        Timestamp endTime = Timestamp.valueOf(LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth(),18,0,0));
        this.events = entityManager.createQuery("SELECT e FROM EventEntity e WHERE e.title = 'Available' AND e.start_date between :time AND :endTime", EventEntity.class)
                .setParameter("time", time)
                .setParameter("endTime", endTime)
                .getResultList();


    }

    public void onDateSelect() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Timestamp time =Timestamp.valueOf(LocalDateTime.of(date.getYear(),date.getMonth(),date.getDayOfMonth(),0,0,0));
        Timestamp endTime = Timestamp.valueOf(LocalDateTime.of(date.getYear(),date.getMonth(),date.getDayOfMonth(),18,0,0));
        String sql="SELECT e FROM EventEntity e WHERE e.title = 'Available'";
        List<EventEntity> events2 = entityManager.createQuery("SELECT e FROM EventEntity e WHERE e.title = 'Available' AND e.start_date between :time AND :endTime", EventEntity.class)
                .setParameter("time", time)
                .setParameter("endTime", endTime)
                .getResultList();
        this.events=events2;

        // Update the headerDate field with the selected date
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.headerDate = "Selected date: " + dateFormat.format(Date.from(date.atZone(ZoneId.systemDefault()).toInstant()));




//        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getHeaderDate() {
        return headerDate;
    }

    public void setHeaderDate(String headerDate) {
        this.headerDate = headerDate;
    }

    public List getInvalidDates() {
        return invalidDates;
    }

    public List<EventEntity> getEvents() {
        return events;
    }

    public void setEvents(List<EventEntity> events) {
        this.events = events;
    }
}
