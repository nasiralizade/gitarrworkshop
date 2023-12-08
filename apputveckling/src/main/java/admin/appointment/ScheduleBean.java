package admin.appointment;

import jakarta.annotation.PostConstruct;

import jakarta.el.MethodExpression;
import jakarta.enterprise.inject.Produces;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletContext;
import jakarta.transaction.Transactional;

import org.primefaces.event.MoveEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.schedule.ScheduleEntryMoveEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import java.io.Serializable;
import java.net.http.HttpRequest;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class ScheduleBean implements Serializable {
    @Produces
    @PersistenceContext(unitName = "APPOINTMENT")
    private EntityManager entityManager;
    private ScheduleModel model;
    private ScheduleEvent<?> event = new DefaultScheduleEvent<>();
    private LocalDateTime time_from;
    private LocalDateTime time_to;
    private LocalDate date;
    private Date dateFrom;
    private Date dateTo;
    private int duration;

    private HttpRequest.Builder response;
    private ServletContext request;

    @PostConstruct
    public void init() {

        try {
            loadEventsFromDatabase();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    private void loadEventsFromDatabase() {
        model = new DefaultScheduleModel();
        List<EventEntity> events = entityManager.createQuery("SELECT e FROM EventEntity e", EventEntity.class).getResultList();
        if (events != null && !events.isEmpty()) {
            int i = 0;
            for (EventEntity eventEntity : events) {

                String color;
                color = "Available".equals(events.get(i).getTitle()) ? "green" : "red";
                ScheduleEvent<?> event = DefaultScheduleEvent.builder()
                        .id(String.valueOf(eventEntity.getId()))
                        .title(eventEntity.getTitle())
                        .backgroundColor(color)
                        .startDate(eventEntity.getStart_date().toLocalDateTime())
                        .endDate(eventEntity.getEnd_date().toLocalDateTime())
                        .allDay(eventEntity.isAll_day())
                        .build();
                i++;

                model.addEvent(event);
            }
        }

    }

    public ScheduleModel getModel() {
        return model;
    }

    public ScheduleEvent<?> getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent<?> event) {
        this.event = event;
    }

    public void onDateSelect(SelectEvent<LocalDateTime> selectEvent) {
        event = DefaultScheduleEvent.builder().startDate(selectEvent.getObject())
                .endDate(selectEvent.getObject().plusHours(1))
                .build();
        loadEventsFromDatabase();
    }

    @Transactional
    public void addEvent() {
        if (event.isAllDay()) {
            if (event.getStartDate().toLocalDate().equals(event.getEndDate().toLocalDate())) {
                event.setEndDate(event.getEndDate().plusDays(1));
            }
        }
        if (event.getId() == null) {
            try {
                EventEntity ny = new EventEntity();
                ny.setTitle(event.getTitle());
                Timestamp start = Timestamp.valueOf(event.getStartDate());
                Timestamp end = Timestamp.valueOf(event.getEndDate());
                ny.setStart_date(start);
                ny.setEnd_date(end);
                ny.setAll_day(event.isAllDay());
                model.addEvent(event);
                entityManager.persist(ny);
                loadEventsFromDatabase();
                //response.setHeader("Refresh", "0; URL=" + request.getContextPath());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            model.updateEvent(event);
        }
        event = new DefaultScheduleEvent<>();
    }


    public void onEventSelect(SelectEvent<ScheduleEvent<?>> selectEvent) {
        event = selectEvent.getObject();
    }

    @Transactional
    public void onUpdate(EventEntity event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Event updated"));
        int id = event.getId();
        EventEntity ny = new EventEntity();
        String title = event.getTitle();
        Timestamp start = event.getStart_date();
        Timestamp end = event.getEnd_date();
        boolean all_day = event.isAll_day();

    }


    @Transactional
    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Event moved"));
        ScheduleEvent<?> movedEvent = event.getScheduleEvent();

        // Find the existing EventEntity in the database
        EventEntity eventEntity = entityManager.find(EventEntity.class, Integer.parseInt(movedEvent.getId()));
        // Update the fields of the EventEntity
        eventEntity.setStart_date(Timestamp.valueOf(movedEvent.getStartDate()));
        eventEntity.setEnd_date(Timestamp.valueOf(movedEvent.getEndDate()));
        eventEntity.setAll_day(movedEvent.isAllDay());
        // Merge the updated EventEntity
        entityManager.merge(eventEntity);

    }

    /**
     *
     */
    @Transactional
    public void deleteEvent() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Event delete"));
        String query =  "DELETE FROM EventEntity e WHERE e.id = " + event.getId();
        entityManager.createQuery(query)
                .executeUpdate();
        model.deleteEvent(event);
    }

    @Transactional
    public void setAvailableEvents() {
        List<LocalDateTime> timeSlots = new ArrayList<>();

        LocalDateTime slot = time_from;

       while(!slot.isAfter(time_to)){
           timeSlots.add(slot);
           slot=slot.plusMinutes(duration).plusMinutes(15);
           EventEntity nySlot= new EventEntity();
           nySlot.setTitle("Ledig");
           nySlot.setEnd_date(Timestamp.valueOf(slot.plusMinutes(45)));
           nySlot.setStart_date(Timestamp.valueOf(slot));
           nySlot.setAll_day(false);
           entityManager.persist(nySlot);
       }




/*
            ;
        for (LocalDate i = date_from; i <= date_to; i.plusDays(1)){
            
            addEvent();
        }
        */

    }



    public LocalDateTime getTime_from() {
        return time_from;
    }

    public void setTime_from(LocalDateTime time_from) {
        this.time_from = time_from;
    }

    public LocalDateTime getTime_to() {
        return time_to;
    }

    public void setTime_to(LocalDateTime time_to) {
        this.time_to = time_to;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
}
