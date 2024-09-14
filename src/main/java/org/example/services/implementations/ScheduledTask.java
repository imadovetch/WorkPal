package org.example.services.implementations;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledTask {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void start() {
        // Schedule the task to run every hour (adjust the period as needed)
        scheduler.scheduleAtFixedRate(this::performReminderCheck, 0, 1, TimeUnit.HOURS);
    }

    private void performReminderCheck() {
        // Example usage: Send reminders for reservations with dummy data
        Notification notification = new Notification();
        int reservationId = 123;
        String spaceName = "Meeting Room A";
        String reservationDate = "2024-09-15";

        notification.sendReservationReminder(reservationId, spaceName, reservationDate);

        System.out.println("Sent reservation reminders.");
    }

    public void stop() {
        scheduler.shutdown();
    }
    public static void main(String[] args) {
        ScheduledTask scheduledTask = new ScheduledTask();

        // Start the cron job
        scheduledTask.start();

        // Add a shutdown hook to stop the scheduler gracefully on application exit
        Runtime.getRuntime().addShutdownHook(new Thread(scheduledTask::stop));
    }
}
