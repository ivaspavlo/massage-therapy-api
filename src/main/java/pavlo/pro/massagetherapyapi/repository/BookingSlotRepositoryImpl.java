package pavlo.pro.massagetherapyapi.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import pavlo.pro.massagetherapyapi.model.BookingSlot;

import java.time.LocalDateTime;
import java.util.List;

public class BookingSlotRepositoryImpl implements BookingSlotRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<BookingSlot> getBookingSlotsPerMassageId(
        LocalDateTime dateFrom,
        LocalDateTime dateTo,
        String massageId
    ) {
        // @Query("{ 'age' : { $gt: ?0, $lt: ?1 } }")
        Query query = new Query();
        query.addCriteria(Criteria.where("massageId").is(massageId));
        query.addCriteria(Criteria.where("end").gte(dateFrom).lte(dateTo));
        List<BookingSlot> bookingSlotsPerMassageId = mongoTemplate.find(query, BookingSlot.class);
        return bookingSlotsPerMassageId;
    }

}
