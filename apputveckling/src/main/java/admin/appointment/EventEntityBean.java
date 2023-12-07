package admin.appointment;

import jakarta.enterprise.inject.Produces;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.io.Serializable;

@Named
@ViewScoped
public class EventEntityBean implements Serializable {
    @Produces
    @PersistenceContext(unitName = "APPOINTMENT")
    private EntityManager entityManager;
    EventEntity event = new EventEntity();

    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(EventEntity event) {
        this.event = event;
    }

    @Transactional
    public void saveEvent(EventEntity eventEntity) {
        entityManager.persist(eventEntity);
    }

}
