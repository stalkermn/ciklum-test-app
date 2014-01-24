package com.ciklum.test.service;

import com.ciklum.test.models.Address;
import com.ciklum.test.models.Phone;
import com.ciklum.test.models.PhoneType;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.UUID;

//import static org..Assert;
/**
 *
 */
public class AddressManagerServiceTest{
    AddressManagerService addressManagerService;
    private Address addressE = new Address(){{
        setCity("Dnipropetrovsk");
        setCountry("Ukraine");
        setState("DP");
        setPostalCode("49000");
        setPhones(new HashSet<Phone>(){{
            add(new Phone(){{
                setPhoneNumber("+380993794067");
                setPhoneType(PhoneType.MOBILE);
            }});
        }});
    }};
    private Address addressT;
    private Phone phone;

    @Before
    public void setup() throws Exception {
        addressManagerService = new AddressManagerService();
        addressManagerService.run(new String[]{"server", this.getClass().getClassLoader().getResource("test-config.yml").getPath()});
        addressT = new Address(){{
            setCity(UUID.randomUUID().toString());
            setCountry(UUID.randomUUID().toString());
            setState(UUID.randomUUID().toString());
            setPostalCode(UUID.randomUUID().toString());
            setPhones(new HashSet<Phone>(){{
                add(new Phone(){{
                    setPhoneNumber("+380993794067");
                    setPhoneType(PhoneType.MOBILE);
                }});
            }});
        }};
        phone = new Phone(){{
            setPhoneNumber("0993704067");
            setPhoneType(PhoneType.MOBILE);
        }};

    }

    @Test
    public void testAddAddress(){
        Address address1 = addressManagerService.addAddress(addressE);
        Address address2 = addressManagerService.addAddress(addressT);
        Assert.assertNotSame(address1.getId(), address2.getId());
        clear(address1, address2);
    }

    @Test
    public void testUpdateAddresses(){
        Address addressForUpdate = addressManagerService.addAddress(addressE);
        Address updatedAddress = addressManagerService.updateAddress(addressForUpdate.getId(), addressT);
        Assert.assertNotSame(addressForUpdate.getId(), updatedAddress.getId());
        Assert.assertSame(addressForUpdate, addressE);
        Assert.assertSame(updatedAddress, addressT);
        clear(addressForUpdate);
        clear(updatedAddress);
    }

    @Test
    public void testUpdateOrAddPhone(){
        Address address = addressManagerService.addAddress(addressE);
        Address addressWithUpdatedPhone =addressManagerService.addPhone(address.getId(), phone);
        Assert.assertSame(true, addressWithUpdatedPhone.getPhones().contains(phone));
        clear(address);
    }

    @Test
    public void testDeletePhone(){
        Address address = addressManagerService.addAddress(addressE);
        Address addressWithAddedPhone =addressManagerService.addPhone(address.getId(), phone);
        Address addressWithDeletedPhone = addressManagerService.deletePhone(address.getId(), phone);
        Assert.assertSame(true, addressWithAddedPhone.getPhones().contains(phone));
        Assert.assertSame(false, addressWithDeletedPhone.getPhones().contains(phone));
        clear(address);
    }

    @Test
    public void testGetAddress(){
        Address address = addressManagerService.addAddress(addressE);
        Assert.assertSame(address, addressManagerService.getAddress(address.getId()));
        clear(address);
    }

    @Test
    public void testGetPhones(){
        Address address = addressManagerService.addAddress(addressE);
        Assert.assertSame(address.getPhones(), addressManagerService.getPhones(address.getId()));
        clear(address);
    }

    private void clear(Address... addresses) {
        for(Address address : addresses){
            addressManagerService.deleteAddress(address.getId());
        }
    }

}
