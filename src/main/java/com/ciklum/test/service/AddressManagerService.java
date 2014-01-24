package com.ciklum.test.service;

import com.ciklum.test.MongoHealthCheck;
import com.ciklum.test.MongoManaged;
import com.ciklum.test.configuration.AddressManagerConfiguration;
import com.ciklum.test.controllers.RootRequestRouter;
import com.ciklum.test.models.Address;
import com.ciklum.test.models.Phone;
import com.ciklum.test.controllers.AddressRequestRouter;
import net.vz.mongodb.jackson.JacksonDBCollection;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

import javax.validation.Valid;
import java.util.Set;

public class AddressManagerService extends Service<AddressManagerConfiguration> {

    private JacksonDBCollection<Address, String> addresses;

    public static void main(String[] args) throws Exception {
        AddressManagerService service = new AddressManagerService();
        service .run(new String[] { "server", service.getClass().getClassLoader().getResource("config.yml").getPath() });
    }

    @Override
    public void initialize(Bootstrap<AddressManagerConfiguration> bootstrap) {
        bootstrap.setName("ciklum-test-app");
        bootstrap.addBundle(new AssetsBundle());
    }

    @Override
    public void run(AddressManagerConfiguration configuration, Environment environment) throws Exception {
        Mongo mongo = new Mongo(configuration.getMongoConfiguration().getMongohost(), configuration.getMongoConfiguration().getMongoport());
        DB db = mongo.getDB(configuration.getMongoConfiguration().getMongodbname());
        
        addresses = JacksonDBCollection.wrap(db.getCollection("addresses"), Address.class, String.class);
        MongoManaged mongoManaged = new MongoManaged(mongo);
        environment.manage(mongoManaged);
        
        environment.addHealthCheck(new MongoHealthCheck(mongo));
        environment.addResource(new AddressRequestRouter(this));
        environment.addResource(new RootRequestRouter());
    }

    public Address getAddress(String id){
        return addresses.find(new Address(id)).curr();
    }

    public Set<Phone> getPhones(String id){
        return addresses.findOne(new Address(id)).getPhones();
    }

    public Address addAddress(@Valid Address address){
        return addresses.save(address).getSavedObject();
    }

    public Address updateAddress(String id, Address address){
        return addresses.update(new Address(id), address).getSavedObject();
    }

    public Address addPhone(String id, Phone phone){
        Set<Phone> phones = addresses.findOne(new Address(id)).getPhones();
        phones.add(phone);
        return addresses.update(new Address(id), new Address(id, phones)).getSavedObject();
    }

    public Address deleteAddress(String id){
        return addresses.removeById(id).getSavedObject();
    }

    public Address deletePhone(String id, Phone phone){
        Address address = addresses.findOne(new Address(id));
        address.getPhones().remove(phone);
        return updateAddress(id, address);
    }
}
