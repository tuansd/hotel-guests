package com.vietez.lil.hotelguests.webservice;

import com.vietez.lil.hotelguests.business.ReservationService;
import com.vietez.lil.hotelguests.business.RoomReservation;
import com.vietez.lil.hotelguests.data.Guest;
import com.vietez.lil.hotelguests.data.Room;
import com.vietez.lil.hotelguests.util.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WebserviceController {
    private final DateUtils dateUtils;
    private final ReservationService reservationService;

    public WebserviceController(DateUtils dateUtils, ReservationService reservationService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
    }
    @RequestMapping(path="/reservations", method = RequestMethod.GET)
    public List<RoomReservation> getReservation(@RequestParam(value = "date", required = false)String dateString){
        Date date = this.dateUtils.createDateFromDateString(dateString);
        return this.reservationService.getRoomReservationsForDate(date);
    }
    @GetMapping("/guests")
    public List<Guest> GetGuests() {
        return this.reservationService.getHotelGuests();
    }
    @PostMapping("/guests")
    @ResponseStatus(HttpStatus.CREATED)
    public void addGuest(@RequestBody Guest guest){
        this.reservationService.addGuest(guest);
    }

    @GetMapping("/rooms")
    public List<Room> getRoom(){
        return this.reservationService.getRooms();
    }
}
