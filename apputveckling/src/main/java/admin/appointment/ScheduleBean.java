package admin.appointment;

import jakarta.annotation.PostConstruct;

import jakarta.enterprise.inject.Produces;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class ScheduleBean implements Serializable {
    @Produces
    @PersistenceContext(unitName = "APPOINTMENT")
    private EntityManager entityManager;
    private ScheduleModel model;
    private ScheduleEvent<?> event = new DefaultScheduleEvent<>();

    @PostConstruct
    public void init() {
        try {
            loadEventsFromDatabase();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void test() {
        /*model = new DefaultScheduleModel();
        DefaultScheduleEvent event = DefaultScheduleEvent.builder()
                .title("title")
                .startDate(LocalDateTime.of(2019, 7, 27, 12, 00))
                .endDate(LocalDateTime.of(2019, 7, 27, 12, 30))
                .build();

        model.addEvent(event);*/
        loadEventsFromDatabase();
    }

    private void loadEventsFromDatabase() {
        model = new DefaultScheduleModel();
        List<EventEntity> events = entityManager.createQuery("SELECT e FROM EventEntity e", EventEntity.class)
                .getResultList();
        if (events != null && !events.isEmpty()) {
            for (EventEntity eventEntity : events) {
                ScheduleEvent<?> event = DefaultScheduleEvent.builder()
                        .id(String.valueOf(eventEntity.getId()))
                        .title(eventEntity.getTitle())
                        .startDate(eventEntity.getStart_date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                        .endDate(eventEntity.getEnd_date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                        .allDay(eventEntity.isAll_day())
                        .build();
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
        event = DefaultScheduleEvent.builder()
                .startDate(selectEvent.getObject())
                .endDate(selectEvent.getObject().plusHours(1))
                .build();
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
                EventEntity eventEntity = convertToEntity(event);
                //model.addEvent(event);
                entityManager.persist(eventEntity);
                saveToDatabase(eventEntity);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } else {
            model.updateEvent(event);
        }

        event = new DefaultScheduleEvent<>();
    }



    private void saveToDatabase(EventEntity eventEntity) throws SQLException {

        entityManager.persist(eventEntity);

    }

    private EventEntity convertToEntity(ScheduleEvent<?> event) {
        EventEntity eventEntity = new EventEntity();
        eventEntity.setTitle(event.getTitle());

        Date startDate = Date.from(event.getStartDate().atZone(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(event.getEndDate().atZone(ZoneId.systemDefault()).toInstant());
        eventEntity.setStart_date(startDate);
        eventEntity.setEnd_date(endDate);
        eventEntity.setAll_day(event.isAllDay());
        return eventEntity;
    }

    public void onEventSelect(SelectEvent<ScheduleEvent<?>> selectEvent) {
        event = selectEvent.getObject();
    }

}
