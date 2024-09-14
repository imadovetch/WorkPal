package org.example.repositories.implementations.UserTasks;

import org.example.Dao.Dao;
import org.example.entities.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ReservationsRepository extends Dao {

    private static final String TABLE_NAME = "Reservations";


    public int addReservation(int spaceId,  int abonnmentId) {
        HashMap<String, Object> reservationData = new HashMap<>();
        reservationData.put("userid", User.Main.getId());
        reservationData.put("spaceid", spaceId);
        if(abonnmentId != 0)
            reservationData.put("type", "plan");
        else
            reservationData.put("type", "day");

        reservationData.put("abonnmentid", abonnmentId);

        return insertData(TABLE_NAME, reservationData);
    }
    public void ShowReservations() {
        // Fetch reservations based on the user ID
        List<HashMap<String, Object>> reservations = fetchData("Reservations", "userid", User.Main.getId());

        if (reservations.isEmpty()) {
            System.out.println("No results found.");
            return;
        }

        System.out.println("Reservation Details:");

        for (HashMap<String, Object> reservation : reservations) {

            int spaceId = (int) reservation.get("spaceid");


            List<HashMap<String, Object>> spaceDetails = fetchData("Espace", "id", spaceId);

            if (!spaceDetails.isEmpty()) {
                HashMap<String, Object> space = spaceDetails.get(0);


                System.out.println("Reservation ID: " + reservation.get("id"));
                System.out.println("Reserved on: " + reservation.get("date"));
                System.out.println("Space Name: " + space.get("name"));
                System.out.println("Space Location: " + space.get("location"));
                System.out.println("Space Capacity: " + space.get("capacity"));
                System.out.println("----------------------------------");
            } else {
                System.out.println("Space details not found for space ID: " + spaceId);
            }
        }
    }



    public int deleteReservation(int reservationId) {
        return deleteData(TABLE_NAME, "id", String.valueOf(reservationId));
    }


    public List<HashMap<String, Object>> fetchReservationsByUserId(int userId) {
        return fetchData(TABLE_NAME, "userid", userId);
    }
}
