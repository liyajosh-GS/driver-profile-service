package com.ridemanagement.driverservice;

import com.ridemanagement.driverservice.dto.Document;
import com.ridemanagement.driverservice.dto.Driver;
import com.ridemanagement.driverservice.dto.Vehicle;
import com.ridemanagement.driverservice.entity.*;

import java.time.LocalDate;
import java.util.UUID;

public class DtoEntityHelperClass {

    public static Driver getRequestDriverObj(){
        Driver driver = new Driver();
        driver.setName("John Doe");
        driver.setPhoneCountryCode("+1");
        driver.setPhoneNumber("1234567890");
        driver.setEmail("john.doe@example.com");
        driver.setDob(LocalDate.of(1990, 5, 15));
        driver.setAddressLine("123 Main Street");
        driver.setCity("Cityville");
        driver.setState("Stateville");
        driver.setPostalCode("12345");
        driver.setCountry("Countryland");
        driver.setVerificationStatus(VerificationStatus.SUCCESS);
        driver.setRating(4.5);
        driver.setAvailable(true);

        return driver;
    }

    public static Driver getResponseDriverObj(){
        Driver driver = new Driver();
        driver.setName("John Doe");
        driver.setPhoneCountryCode("+1");
        driver.setPhoneNumber("1234567890");
        driver.setEmail("john.doe@example.com");
        driver.setDob(LocalDate.of(1990, 5, 15));
        driver.setAddressLine("123 Main Street");
        driver.setCity("Cityville");
        driver.setState("Stateville");
        driver.setPostalCode("12345");
        driver.setCountry("Countryland");
        driver.setVerificationStatus(VerificationStatus.SUCCESS);
        driver.setRating(4.5);
        driver.setAvailable(true);

        return driver;
    }

    public static DriverKey getDriverKey(){
        DriverKey driverKey = new DriverKey();
        driverKey.setCity("Cityville");
        driverKey.setState("Stateville");
        driverKey.setPostalCode("12345");
        driverKey.setCountry("Countryland");
        driverKey.setId(UUID.fromString("ce5d1cae-9e96-44b0-bd3b-dae275800015"));

        return driverKey;
    }

    public static DriverEntity getDriverEntity(){
        DriverEntity driver = new DriverEntity();
        driver.setName("John Doe");
        driver.setPhoneCountryCode("+1");
        driver.setPhoneNumber("1234567890");
        driver.setEmail("john.doe@example.com");
        driver.setDob(LocalDate.of(1990, 5, 15));
        driver.setAddressLine("123 Main Street");
        driver.setVerificationStatus(VerificationStatus.SUCCESS);
        driver.setRating(4.5);
        driver.setAvailable(true);
        driver.setKey(getDriverKey());

        return driver;
    }

    public static Document getRequestDocument(){
        Document document = new Document();
        document.setOwnerId(UUID.fromString("ce5d1cae-9e96-44b0-bd3b-dae275800015"));
        document.setDocumentType(DocumentType.ID);
        document.setVerificationStatus(VerificationStatus.PENDING);
        return document;
    }

    public static Document getResponseDocument(){
        Document document = new Document();
        document.setOwnerId(UUID.fromString("ce5d1cae-9e96-44b0-bd3b-dae275800015"));
        document.setId(UUID.fromString("ce5d1cae-9e96-44b0-bd3b-dae275800015"));
        document.setDocumentType(DocumentType.ID);
        document.setVerificationStatus(VerificationStatus.PENDING);
        return document;
    }

    public static DocumentKey getDocumentKey(){
        DocumentKey documentKey = new DocumentKey();
        documentKey.setOwnerId(UUID.fromString("ce5d1cae-9e96-44b0-bd3b-dae275800015"));
        documentKey.setId(UUID.fromString("ce5d1cae-9e96-44b0-bd3b-dae275800015"));
        return documentKey;
    }

    public static DocumentEntity getDocumentEntity(){
        DocumentEntity document = new DocumentEntity();
        document.setKey(getDocumentKey());
        document.setDocumentType(DocumentType.ID);
        document.setDocument(new byte[5]);
        return document;
    }

    public static Vehicle getRequestVehicleObj() {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleType(VehicleType.FOUR_WHEELER);
        vehicle.setVehicleModel(VehicleModel.SEDAN);
        vehicle.setOwnedBy("John Doe");
        vehicle.setRented(false);
        vehicle.setRegistrationId("ABC123");
        vehicle.setSeatingCapacity(5);
        vehicle.setVerificationStatus(VerificationStatus.SUCCESS);
        return vehicle;
    }


    public static VehicleKey getVehicleKey() {
        VehicleKey key = new VehicleKey();
        key.setVehicleType(VehicleType.FOUR_WHEELER);
        key.setVehicleModel(VehicleModel.SEDAN);
        key.setId(UUID.fromString("ce5d1cae-9e96-44b0-bd3b-dae275800015"));
        return key;
    }


    public static VehicleEntity getVehicleEntity() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setKey(getVehicleKey());
        vehicle.setOwnedBy("John Doe");
        vehicle.setRented(false);
        vehicle.setRegistrationId("ABC123");
        vehicle.setSeatingCapacity(5);
        vehicle.setVerificationStatus(VerificationStatus.SUCCESS);
        return vehicle;
    }

    public static DocumentEntity getDocumentEntityForCreateService(){
        DocumentEntity document = new DocumentEntity();
        document.setKey(getDocumentKey());
        document.setDocumentType(DocumentType.ID);
        return document;
    }
}
