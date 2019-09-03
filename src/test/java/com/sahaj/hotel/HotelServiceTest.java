package com.sahaj.hotel;

import com.sahaj.hotel.exception.InvalidHotelRequest;
import com.sahaj.hotel.exception.InvalidMotionRequest;
import com.sahaj.hotel.model.hotel.Hotel;
import com.sahaj.hotel.model.request.HotelRequest;
import com.sahaj.hotel.model.request.MotionEvent;
import com.sahaj.hotel.service.HotelFactory;
import com.sahaj.hotel.service.hotel.HotelService;
import com.sahaj.hotel.service.hotel.HotelServiceImpl;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class HotelServiceTest {

    private HotelService hotelService = new HotelServiceImpl();
    private HotelFactory factory = new HotelFactory();

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void createHotelValidRequest() throws InvalidHotelRequest {
        Hotel hotel = factory.getHotel(new HotelRequest(2, 1, 2));
        Assert.assertNotNull(hotel);
        Assert.assertEquals(2, hotel.getFloors().size());
        Assert.assertEquals(1, hotel.getFloors().get(0).getMainCorridors().size());
        Assert.assertEquals(1, hotel.getFloors().get(1).getMainCorridors().size());
        Assert.assertEquals(2, hotel.getFloors().get(0).getSubCorridors().size());
        Assert.assertEquals(2, hotel.getFloors().get(1).getSubCorridors().size());
    }

    @Test
    public void printHotelState() throws InvalidHotelRequest {
        Hotel hotel = factory.getHotel(new HotelRequest(2, 1, 2));
        Assert.assertNotNull(hotel);
        hotelService.printHotelState(hotel);
    }

    @Test
    public void hotelStateAfterFirstMotion() throws InvalidHotelRequest, InvalidMotionRequest {
        Hotel hotel = factory.getHotel(new HotelRequest(2, 1, 2));
        Assert.assertNotNull(hotel);
        System.out.println("Hotel Initial State");
        hotelService.printHotelState(hotel);
        System.out.println("\n\nHotel State After Motion on 1st Floor 2nd SubCorridor");
        hotel = hotelService.handleMotionEvent(new MotionEvent(hotel, 1, 2));
        hotelService.printHotelState(hotel);
    }

    @Test
    public void hotelStateAfterFirstMotionAndOneMinute() throws InvalidHotelRequest, InvalidMotionRequest, InterruptedException {
        Hotel hotel = factory.getHotel(new HotelRequest(2, 1, 2));
        Assert.assertNotNull(hotel);
        System.out.println("Hotel Initial State");
        hotelService.printHotelState(hotel);
        System.out.println("\n\nHotel State After Motion on 1st Floor 2nd SubCorridor");
        hotel = hotelService.handleMotionEvent(new MotionEvent(hotel, 1, 2));
        hotelService.printHotelState(hotel);
        Thread.sleep(1000L * 65);
        System.out.println("\n\nHotel State After one minute of First Motion");
        hotelService.printHotelState(hotel);
    }

    @Test
    public void hotelStateAfterFirstMotionAndSecondMotionInOneMinute() throws InvalidHotelRequest, InvalidMotionRequest, InterruptedException {
        Hotel hotel = factory.getHotel(new HotelRequest(2, 1, 2));
        Assert.assertNotNull(hotel);
        System.out.println("Hotel Initial State");
        hotelService.printHotelState(hotel);
        System.out.println("\n\nHotel State After Motion on 1st Floor 2nd SubCorridor");
        hotel = hotelService.handleMotionEvent(new MotionEvent(hotel, 1, 2));
        hotelService.printHotelState(hotel);
        Thread.sleep(20000L);
        System.out.println("\n\nHotel State after Second Movement on 1st Floor 2nd SubCorridor before completion of one minute");
        hotel = hotelService.handleMotionEvent(new MotionEvent(hotel, 1, 2));
        hotelService.printHotelState(hotel);
    }

    @Test
    public void hotelStateAfterFirstMotionAndSecondMotionAfterOneMinute() throws InvalidHotelRequest, InvalidMotionRequest, InterruptedException {
        Hotel hotel = factory.getHotel(new HotelRequest(2, 1, 2));
        Assert.assertNotNull(hotel);
        System.out.println("Hotel Initial State");
        hotelService.printHotelState(hotel);
        System.out.println("\n\nHotel State After Motion on 1st Floor 2nd SubCorridor");
        hotel = hotelService.handleMotionEvent(new MotionEvent(hotel, 1, 2));
        hotelService.printHotelState(hotel);
        Thread.sleep(20000L);
        System.out.println("\n\nHotel State after Second motion  on 1st Floor 2nd SubCorridor");
        hotel = hotelService.handleMotionEvent(new MotionEvent(hotel, 1, 2));
        hotelService.printHotelState(hotel);
        Thread.sleep(1000L * 65);
        System.out.println("\n\nHotel State after Second Motion and no motion for next 1 minute");
        hotelService.printHotelState(hotel);
    }

    @Test
    public void createHotelInValidRequestWithFloorEqualZero() throws InvalidHotelRequest {
        exceptionRule.expect(InvalidHotelRequest.class);
        exceptionRule.expectMessage("No. of Floors must be positive numbers");
        factory.getHotel(new HotelRequest(0, 1, 2));
    }

    @Test
    public void createHotelInValidRequestWithMainCorridorLessThanZero() throws InvalidHotelRequest {
        exceptionRule.expect(InvalidHotelRequest.class);
        exceptionRule.expectMessage("No. of MainCorridor must be positive numbers");
        factory.getHotel(new HotelRequest(1, -1, 2));
    }

    @Test
    public void createHotelInValidRequestWithSubCorridorEqualZero() throws InvalidHotelRequest {
        exceptionRule.expect(InvalidHotelRequest.class);
        exceptionRule.expectMessage("No. of SubCorridor must be positive numbers");
        factory.getHotel(new HotelRequest(1, 1, 0));
    }

    @Test
    public void inValidMotionRequestWithHoteNull() throws InvalidMotionRequest {
        exceptionRule.expect(InvalidMotionRequest.class);
        exceptionRule.expectMessage("Hotel cannot be null");
        hotelService.handleMotionEvent(new MotionEvent(null, 1, 1));
    }

    @Test
    public void inValidMotionRequestWithNegativeFloorNo() throws InvalidMotionRequest, InvalidHotelRequest {
        Hotel hotel = factory.getHotel(new HotelRequest(2, 1, 2));
        exceptionRule.expect(InvalidMotionRequest.class);
        exceptionRule.expectMessage("Floor number must be a positive number");
        hotelService.handleMotionEvent(new MotionEvent(hotel, -1, 1));
    }

    @Test
    public void inValidMotionRequestWithFloorNoGreater() throws InvalidMotionRequest, InvalidHotelRequest {
        Hotel hotel = factory.getHotel(new HotelRequest(2, 1, 2));
        exceptionRule.expect(InvalidMotionRequest.class);
        exceptionRule.expectMessage("Invalid Floor number, as Hotel has only 2 Floors");
        hotelService.handleMotionEvent(new MotionEvent(hotel, 5, 1));
    }

    @Test
    public void inValidMotionRequestWithZeroSubCorridor() throws InvalidMotionRequest, InvalidHotelRequest {
        Hotel hotel = factory.getHotel(new HotelRequest(2, 1, 2));
        exceptionRule.expect(InvalidMotionRequest.class);
        exceptionRule.expectMessage("SubCorridor number must be a positive number");
        hotelService.handleMotionEvent(new MotionEvent(hotel, 1, 0));
    }

    @Test
    public void inValidMotionRequestWithSubCorridorGreate() throws InvalidMotionRequest, InvalidHotelRequest {
        Hotel hotel = factory.getHotel(new HotelRequest(2, 1, 2));
        exceptionRule.expect(InvalidMotionRequest.class);
        exceptionRule.expectMessage("Invalid Sub Corridor, as Hotel has only 2 SubCorridors");
        hotelService.handleMotionEvent(new MotionEvent(hotel, 1, 4));
    }
}
