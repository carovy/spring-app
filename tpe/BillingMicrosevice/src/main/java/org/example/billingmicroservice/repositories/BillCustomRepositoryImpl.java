package org.example.billingmicroservice.repositories;

import org.example.billingmicroservice.entities.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class BillCustomRepositoryImpl implements BillCustomRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void modify(String id, double price, double additionalPrice) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update()
                .set("price", price)
                .set("additionalPrice", additionalPrice);
        mongoTemplate.updateFirst(query, update, Bill.class);
    }

    @Override
    public void setNewPrice(String id, double price) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update().set("price", price);
        mongoTemplate.updateFirst(query, update, Bill.class);
    }

    @Override
    public Bill getLastOne() {
        Query query = new Query().with(Sort.by(Sort.Direction.DESC, "fecha")).limit(1);
        Bill latestBill = mongoTemplate.findOne(query, Bill.class);
        return latestBill != null ? latestBill : null;
    }
}
